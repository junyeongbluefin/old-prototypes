import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import AppOld from './component/AppOld';
import Todo from './component/Todo.js';
import WelcomeApp from './component/WelcomeApp.js';
import Comment from './component/Comment.js';
import Clock, {AppClock} from './component/Clock.js';
import Toggle from './component/Toggle.js';
import LoggingButton from './component/LoggingButton.js';
import Page, {Greeting, LoginControl, Mailbox} from './component/Page.js';
import NumberList, {Blog} from './component/NumberList.js';
import FilterableProductTable from './component/FilterableProductTable.js';
import UserNetwork from './component/UserNetwork.js';
import registerServiceWorker from './registerServiceWorker';

import {render, renderTodoApp} from './component/ReduxSample.js'
import RouteExample from './component/RouteExample.js'
import AuthExample from './component/AuthExample.js';

ReactDOM.render(<AppOld />, document.getElementById('root'));
ReactDOM.render(<Todo />, document.getElementById('root'));
ReactDOM.render(
    <h1>Hello, world!</h1>,
    document.getElementById('root')
);
ReactDOM.render(
    <WelcomeApp />,
    document.getElementById('root')
);
const comment = {
    date: new Date(),
    text: 'I hope you enjoy learning React!',
    author: {
        name: 'Hello Kitty',
        avatarUrl: 'http://placekitten.com/g/64/64'
    }
};
ReactDOM.render(
    <Comment
        date={comment.date}
        text={comment.text}
        author={comment.author} />,
    document.getElementById('root')
);
ReactDOM.render(
    <Clock />,
    document.getElementById('root')
);

ReactDOM.render(
    <AppClock />,
    document.getElementById('root')
);
ReactDOM.render(
    <Toggle />,
    document.getElementById('root')
);
ReactDOM.render(
    <LoggingButton />,
    document.getElementById('root')
);
ReactDOM.render(
    // Try changing to isLoggedIn={true}:
    <Greeting isLoggedIn={false} />,
    document.getElementById('root')
);
ReactDOM.render(
    <LoginControl />,
    document.getElementById('root')
);
const messages = ['React', 'Re: React', 'Re:Re: React'];
ReactDOM.render(
    <Mailbox unreadMessages={messages} />,
    document.getElementById('root')
);
ReactDOM.render(
    <Page />,
    document.getElementById('root')
);
const numbers = [1, 2, 3, 4, 5];
ReactDOM.render(
    <NumberList numbers={numbers} />,
    document.getElementById('root')
);
const posts = [
    {id: 1, title: 'Hello World', content: 'Welcome to learning React!'},
    {id: 2, title: 'Installation', content: 'You can install React from npm.'}
];
ReactDOM.render(
    <Blog posts={posts} />,
    document.getElementById('root')
);
var PRODUCTS = [
    {category: 'Sporting Goods', price: '$49.99', stocked: true, name: 'Football'},
    {category: 'Sporting Goods', price: '$9.99', stocked: true, name: 'Baseball'},
    {category: 'Sporting Goods', price: '$29.99', stocked: false, name: 'Basketball'},
    {category: 'Electronics', price: '$99.99', stocked: true, name: 'iPod Touch'},
    {category: 'Electronics', price: '$399.99', stocked: false, name: 'iPhone 5'},
    {category: 'Electronics', price: '$199.99', stocked: true, name: 'Nexus 7'}
];
ReactDOM.render(
    <FilterableProductTable products={PRODUCTS} />,
    document.getElementById('root')
);
ReactDOM.render(
    <UserNetwork />,
    document.getElementById('root')
);

render();
renderTodoApp();

ReactDOM.render(
    <RouteExample />,
    document.getElementById('root')
);


ReactDOM.render(
    <AuthExample />,
    document.getElementById('root')
);

registerServiceWorker();