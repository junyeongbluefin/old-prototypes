import React from 'react';
import ReactDOM from 'react-dom';
import deepFreeze from 'deep-freeze';
import {render, addCounter, removeCounter, incrementCounter, toggleTodo, todos, todoApp} from "../../component/ReduxSample";


describe('ReduxSample component renders and works properly', () => {
    it('This tests addCounter immutably', () => {
        const testAddCounter = () => {
            const listBefore = [];
            const listAfter = [0];
            expect(addCounter(listBefore)).toEqual(listAfter);
        };

        testAddCounter();
    });
    it('This tests addCounter immutably', () => {
        const testAddCounter = () => {
            const listBefore = [];
            const listAfter = [0];
            expect(addCounter(listBefore)).toEqual(listAfter);
        };

        testAddCounter();
    });
    it('This tests removeCounter immutably', () => {
        const testRemoveCounter = () => {
            const listBefore = [0, 10, 20];
            const listAfter = [0, 20];
            expect(removeCounter(listBefore, 1)).toEqual(listAfter);
        };

        testRemoveCounter();
    });

    it('This tests incrementCounter immutably', () => {
        const testIncrementCounter = () => {
            const listBefore = [0, 10, 20];
            const listAfter = [0, 11, 20];
            expect(incrementCounter(listBefore, 1)).toEqual(listAfter);
        };

        testIncrementCounter();
    });
    it('This tests toggleTodo immutably', () => {
        const todoBefore = {
            id: 0,
            text: 'Learn Redux',
            completed: false
        };
        const todoAfter = {
            id: 0,
            text: 'Learn Redux',
            completed: true
        };

        deepFreeze(todoBefore);

        expect(toggleTodo(todoBefore)).toEqual(todoAfter);
    });

    it('This tests todos immutably', () => {
        const stateBefore = [];
        const action = {
            type: 'ADD_TODO',
            id: 0,
            text: 'Learn Redux'
        };
        const stateAfter = [
            {
                id: 0,
                text: 'Learn Redux',
                completed: false
            }
        ];

        deepFreeze(stateBefore);
        deepFreeze(action);

        expect(todos(stateBefore, action)).toEqual(stateAfter);
    });

    it('This tests toggleTodo immutably', () => {
        const stateBefore = [
            {
                id: 0,
                text: 'Learn Redux',
                completed: false
            },
            {
                id: 1,
                text: 'Go shopping',
                completed: false
            }
        ];
        const action = {
            type: 'TOGGLE_TODO',
            id: 1,
            text: 'Learn Redux'
        };
        const stateAfter = [
            {
                id: 0,
                text: 'Learn Redux',
                completed: false
            },
            {
                id: 1,
                text: 'Go shopping',
                completed: true
            }
        ];

        deepFreeze(stateBefore);
        deepFreeze(action);

        expect(todos(stateBefore, action)).toEqual(stateAfter);
    });

    /*it('Todo renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<Todo />, div);
    });*/
});