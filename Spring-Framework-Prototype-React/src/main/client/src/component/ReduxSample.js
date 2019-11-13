import React, { Component } from 'react';
import ReactDOM from 'react-dom';
//import { combineReducers } from 'redux'

const counter = (state = 0, action) => {
    if (typeof state === 'undefined') {
        return 0;
    }
    if (action.type === 'INCREMENT') {
        return state + 1;
    } else if (action.type === 'DECREMENT') {
        return state - 1;
    } else {
        return state;
    }
}

//expect (counter(0, { type: 'INCREMENT'})).toEqual(1);
//expect (counter(1, { type: 'INCREMENT'})).toEqual(2);

const createStore = (reducer) => {
    let state;
    let listeners = [];

    const getState = () => state;
    const dispatch = (action) => {
        state = reducer(state, action);
        listeners.forEach(listener => listener());
    };
    const subscribe = (listener) => {
        listeners.push(listener);
        return () => {
            listeners = listeners.filter((l => l !== listener));
        };
    };

    dispatch({});

    return {getState, dispatch, subscribe};
};

const Counter = ({value, onIncrement, onDecrement}) => (
    <div>
        <h1>{value}</h1>
        <button onClick={onIncrement}>+</button>
        <button onClick={onDecrement}>-</button>
    </div>
);

const store = createStore(counter);

export const render = () => {
    ReactDOM.render(
        <Counter
            value={store.getState()}
            onIncrement={() => store.dispatch({
                type: 'INCREMENT'
            })}
            onDecrement={() => store.dispatch({
                type: "DECREMENT"
            })}
        />,
        document.getElementById('root')
    );
//  document.body.innerText = store.getState();
};
store.subscribe(render);

export const addCounter = (list) => {
    return [...list, 0]; // return list.concat([0]);
};

export const removeCounter = (list, index) => {
    return list
        .slice(0, index)
        .concat(list.slice(index + 1));
    return [
        ...list.slice(0, index),
        ...list.slice(index + 1)
    ];

    // below is poor way since it is mutating list object
    //list.splice(index, 1);
    //return list;
};

export const incrementCounter = (list, index) => {
    return [
        ...list.slice(0, index),
        list[index] + 1,
        ...list.slice(index + 1)
    ];
};

export const toggleTodo = (todo) => {
    //todo.completed = !todo.completed;
    //return todo;
    /*return {
        id: todo.id,
        text: todo.text,
        completed: !todo.completed
    }*/
    /*return Object.assign({}, todo, {
        completed: !todo.completed
    });*/
    return {
        ...todo,
        completed: !todo.completed
    };
};

export const todos = (state = [], action) => {

    const todo = (state, action) => {
        switch (action.type) {
            case 'ADD_TODO':
                return {
                    id: action.id,
                    text: action.text,
                    completed: false
                };
            case 'TOGGLE_TODO':
                if (state.id !== action.id) {
                    return state;
                }

                return {
                    ...state,
                    completed: !state.completed
                };
            default:
                return state;
        }
    };

    switch (action.type) {
        case 'ADD_TODO':
            return [
                ...state,
                todo(undefined, action)
            ];
        case 'TOGGLE_TODO':
            return state.map(t => todo(t, action));
        default:
            return state;
    }
};

export const visibilityFilter = (state = 'SHOW_ALL', action) => {
    switch (action.type) {
        case 'SET_VISIBILITY_FILTER':
            return action.filter;
        default:
            return state;
    }
};

/*
export const todoApp = (state = {}, action) => {
    return {
        todo: todos(state.todos,action),
        visibilityFilter: visibilityFilter(state.visibilityFilter, action)
    };
};*/

export const combineReducers = (reducers) => {
    return (state = {}, action) => {
        return Object.keys(reducers).reduce(
            (nextState, key) => {
                nextState[key] = reducers[key](state[key], action);
                return nextState;
            },
            {}
        );
    };
};

export const todoApp = combineReducers({todos, visibilityFilter});
export const storeTodoApp = createStore(todoApp);

export class TodoApp extends Component {
    render() {
        return (
            <div>
                <input ref={node => {
                    this.input = node;
                }} />
                <button onClick={() => {
                  storeTodoApp.dispatch({
                      type: 'ADD_TODO',
                      text: this.input.value,
                      id: (this.props.todos === false || this.props.todos.length === 0) ?
                          1 : this.props.todos[this.props.todos.length - 1].id + 1
                  });
                  this.input.value = '';
                }}>
                    Add Todo
                </button>
                <ul>
                    {this.props.todos.map(todo =>
                        <li key={todo.id} onClick={() => {
                            storeTodoApp.dispatch({
                                type: 'TOGGLE_TODO',
                                id: todo.id
                            });
                        }}
                        style={{
                            textDecoration: todo.completed ? 'line-through' : 'none'
                        }}
                        >
                            {todo.text}
                        </li>
                    )}
                </ul>
            </div>
        )
    }
};

export const renderTodoApp = () => {
    ReactDOM.render(
        <TodoApp todos={storeTodoApp.getState().todos} />,
        document.getElementById('root')
    );
};
storeTodoApp.subscribe(renderTodoApp);