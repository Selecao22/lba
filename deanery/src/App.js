import "bootstrap/dist/css/bootstrap.css"
import React from 'react';
import logo from './logo.svg';
import Request from "./Services/Request";
import './App.css';

import {Table, Button} from "react-bootstrap";

class JournalTable extends React.Component {
    render() {
        let journal = this.props.journal;
        return (
            <Table bordered>
        <thead className="text-white">
            <tr>
            <th>#</th>
            <th>ФИО</th>
            <th>ПрИС</th>
            <th>СИИ</th>
            <th>Пересдачи</th>
            </tr>
            </thead>
            <tbody className="text-white">
            {this.props.students.map((student) => {
                        var groupId = this.props.group;
                        if (student.studyGroupId !== groupId)
                            return;
                        var PrIS;
                        var SII;
                        journal.map(row => {
                            if (row.studentId !== student.id) return;
                            else if (row.studyPlanId === 1) PrIS = 6 - row.markId;
                            else if (row.studyPlanId === 2) SII = 6 - row.markId;

                        });
                        let color1 = PrIS < 3 ? "text-danger" : "";
                        let color2 = SII < 3 ? "text-danger" : "";
                        let count = PrIS < 3 ? 1 : 0;
                        if (SII < 3) count++;
                        return (
                            <tr key={student.id}>
                        <th>{student.id}</th>
                        <th>{student.surName + " " + student.name + " " + student.secondName}</th>
                        <th className={color1}>{PrIS}</th>
                        <th className={color2}>{SII}</th>
                        <th>{count}</th>
                        </tr>
                    )
                    }
                )}
            </tbody>
            </Table>
    )
    }
}

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            activeGroup: null,
            students: null,
            journal: null
        };
        this.requestFromServer = this.requestFromServer.bind(this);
    }

    componentDidMount() {
        this.requestFromServer();
    }

    requestFromServer() {
        Request.getJournal().then(j => this.setState({journal: j}));
        Request.getStudents().then(s => this.setState({students: s}));
    }

    componentWillUnmount() {
        this.setState({journal: null});
        this.setState({students: null});
    }

    render() {
        return (
            <div className="App">
            <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <br/><br/>
            <div>
            {this.state.students
                    ? <JournalTable
        students={this.state.students}
        group={this.state.activeGroup}
        journal={this.state.journal}/>
    : null}
    <Button variant="primary" size="lg" onClick={() => {
            this.setState({activeGroup: 1});
        }}>
        ИКБО-03-16
        </Button>
        <Button variant="primary" size="lg" style={{marginLeft: 10}} onClick={() => {
            this.setState({activeGroup: 2});
        }}>
        ИКБО-02-16
        </Button>
        </div>
        </header>
        </div>
    );
    }
}
export default App;