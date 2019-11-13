import React from 'react';
/*import ReactDOM from 'react-dom';


function tick() {
    const element = (
        <div>
            <h1>Hello, world!</h1>
            <h2>It is {new Date().toLocaleTimeString()}.</h2>
        </div>
    );
    ReactDOM.render(
        element,
        document.getElementById('root')
    );
}
setInterval(tick, 1000);*/

function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
}

/*
const element = <Welcome name="Sara" />;
ReactDOM.render(
    element,
    document.getElementById('root')
);
*/

function WelcomeApp() {
    return (
        <div>
            <Welcome name="Sara" />
            <Welcome name="Cahal" />
            <Welcome name="Edite" />
        </div>
    );
}

export default WelcomeApp;
