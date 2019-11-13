import React from 'react';
import ReactDOM from 'react-dom';
import Todo, {TodoList} from "../../component/Todo";

describe('Todo component renders todo correctly', () => {
    it('Todo renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<Todo />, div);
    });

    it('Todo list renders without crashing', () => {
        const div = document.createElement('div');
        var items = [{
            text: "My todo",
            id: 1
        },{
            text: "Your todo",
            id: 2
        }];
        ReactDOM.render(<TodoList items = {items} />, div);
    });
});