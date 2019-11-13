import tensorflow as tf
import pandas as pd
import numpy as np
from sklearn import preprocessing

# 1460

# Train 900
# Valid 300
# Test 260


def unique_category_values(df, columns):
    result = {}
    for column in columns:
        result[column] = unique_value_names(df, column)
    return result


def unique_value_names(df, column):
    return np.unique(df[column][~pd.isnull(df[column])].values)


def num_or_category(df, columns):
    num_columns = []
    category_columns = []

    for column in columns:
        if df[column].dtype == object or len(unique_value_names(df, column)) < 30:
            category_columns.append(column)
            continue
        num_columns.append(column)
    return num_columns, category_columns


def preprocess(df, num_columns, category_columns):
    print("Preprocess for null (numeric)")
    for column in num_columns:
        count = df[column].isnull().sum()
        if count > 0:
            df[column].fillna(df[column].median(), inplace=True)

    # Should exclude test set (issue - predict: [636.23834], real: 116050)
    # scaler = preprocessing.MinMaxScaler()
    # scaled_df = scaler.fit_transform(df[num_columns])
    # df[num_columns] = pd.DataFrame(scaled_df, columns=num_columns)


    print("Preprocess for null (category)")
    for column in category_columns:
        count = df[column].isnull().sum()
        if count > 0:
            df[column].fillna('', inplace=True)

    df[category_columns] = df[category_columns].applymap(str)


def create_numeric_feature_cols(columns):
    return [tf.feature_column.numeric_column(k) for k in columns]


def create_category_feature_cols(df, columns, dict_unique):
    return [tf.feature_column.categorical_column_with_hash_bucket(key=k, hash_bucket_size=len(dict_unique[k])) for k in columns]


def create_feature_cols(df, num_columns, category_columns, dict_unique):
    return create_numeric_feature_cols(num_columns) + create_category_feature_cols(df, category_columns, dict_unique)


def make_train_input_fn(df, num_epochs):
    return tf.estimator.inputs.pandas_input_fn(
        x=df, y=df[LABEL], batch_size=128, num_epochs=num_epochs, shuffle=True, queue_capacity=1000
    )


def make_eval_input_fn(df):
    return tf.estimator.inputs.pandas_input_fn(
        x=df, y=df[LABEL], batch_size=128, shuffle=True, queue_capacity=1000
    )


def make_prediction_input_fn(df):
    return tf.estimator.inputs.pandas_input_fn(
        x=df, y=None, batch_size=128, shuffle=True, queue_capacity=1000
    )


CSV_COLUMNS = [ 'MSZoning','MSSubClass', 'LotFrontage', 'LotArea', 'Street', 'Alley', 'LotShape', 'LandContour', 'Utilities', 'LotConfig', 'LandSlope', 'Neighborhood', 'Condition1', 'Condition2', 'BldgType', 'HouseStyle', 'OverallQual', 'OverallCond', 'YearBuilt', 'YearRemodAdd', 'RoofStyle', 'RoofMatl', 'Exterior1st', 'Exterior2nd', 'MasVnrType', 'MasVnrArea', 'ExterQual', 'ExterCond', 'Foundation', 'BsmtQual', 'BsmtCond', 'BsmtExposure', 'BsmtFinType1', 'BsmtFinSF1', 'BsmtFinType2', 'BsmtFinSF2', 'BsmtUnfSF', 'TotalBsmtSF', 'Heating', 'HeatingQC', 'CentralAir', 'Electrical', '1stFlrSF', '2ndFlrSF', 'LowQualFinSF', 'GrLivArea', 'BsmtFullBath', 'BsmtHalfBath', 'FullBath', 'HalfBath', 'BedroomAbvGr', 'KitchenAbvGr', 'KitchenQual', 'TotRmsAbvGrd', 'Functional', 'Fireplaces', 'FireplaceQu', 'GarageType', 'GarageYrBlt', 'GarageFinish', 'GarageCars', 'GarageArea', 'GarageQual', 'GarageCond', 'PavedDrive', 'WoodDeckSF', 'OpenPorchSF', 'EnclosedPorch', '3SsnPorch', 'ScreenPorch', 'PoolArea', 'PoolQC', 'Fence', 'MiscFeature', 'MiscVal', 'MoSold', 'YrSold', 'SaleType', 'SaleCondition', 'SalePrice']
FEATURES = CSV_COLUMNS[0:len(CSV_COLUMNS) - 2]
LABEL = CSV_COLUMNS[len(CSV_COLUMNS) - 1]

df = pd.read_csv('../input/train.csv', header=0)[CSV_COLUMNS]

num_columns, category_columns = num_or_category(df, FEATURES)
preprocess(df, num_columns, category_columns)
dict_unique_category_values = unique_category_values(df, category_columns)

df_train = df.iloc[:900, :]
df_valid = df.iloc[900:1200, :]
df_test = df.iloc[1200:, :]

OUTDIR = 'bid_trained'

model = tf.estimator.LinearRegressor(feature_columns=create_feature_cols(df, num_columns, category_columns, dict_unique_category_values)) # ,model_dir=OUTDIR
# model = tf.estimator.DNNClassifier(feature_columns = create_feature_cols(df, num_columns, category_columns, dict_unique_category_values), hidden_units = [32, 8, 2], model_dir=OUTDIR)

print('Start training...')
model.train(input_fn=make_train_input_fn(df_train, num_epochs=10))

print('calculate rmse')
def print_rmse(model, df):
    metrics = model.evaluate(input_fn=make_eval_input_fn(df))
    print('RMSE on dataset = {}'.format(np.sqrt(metrics['average_loss']))) # minmax -> 192762.9375 , standard -> 192770.109375, N/A -> 135530.46875
print_rmse(model, df_valid)

predictions = model.predict(input_fn=make_prediction_input_fn(df_test))
for index, p in enumerate(predictions):
    print("predict: " + str(p.get("predictions")) + ", real: " + str(df_test[LABEL].iloc[index]))