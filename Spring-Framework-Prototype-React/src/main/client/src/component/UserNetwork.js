import React, { Component } from 'react';

export class UserNetwork extends Component {
    constructor() {
        super();
        this.state = { users: [] };

        // This binding is necessary to make `this` work in the callback
        this.handleClick = this.handleClick.bind(this);
    }

    componentDidMount() {
        this.getUser();
    }

    getUser() {
        let _this = this;
        fetch("/sample/api/userList")
            .then(response => {
                if (response.status >= 400) {
                    throw new Error("Bad response from server");
                }
                return response.json();
            })
            .then(function (data) {
                _this.setState({users:data});
            });
    }

    handleClick() {
        this.getUser();
    }

    render() {
        return(
            <div>
                <button onClick={this.handleClick}>Get User List</button>
                <div>Users:</div>
                {this.state.users.map(user=> {
                    return <div key={user.id.toString()}>{user.firstName} {user.lastName}</div>
                })}
            </div>
        );
    }
}

export default UserNetwork;
