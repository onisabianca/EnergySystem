import React from 'react'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import SockJsClient from 'react-stomp'
import {Client} from '@stomp/stompjs'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PageUsers from './users/PageUsers'
import AddUser from "./users/AddUser"
import EditUser from "./users/EditUser"

import PageDevice from './device/PageDevice'
import AddDevice from "./device/AddDevice"
import EditDevice from "./device/EditDevice"
import PageDeviceForClient from "./device/PageDeviceForClient"

import PageEnergyConsumption from './energyconsumption/PageEnergyConsumption'
import AddEnergyConsumption from "./energyconsumption/AddEnergyConsumption"
import EditEnergyConsumption from "./energyconsumption/EditEnergyConsumption"

import PageLogin from "./login/PageLogin"

import PageCharts from "./energyconsumption/PageCharts"

import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';

import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";

import ChatRoom2 from './chat/ChatRoom2'


class App extends React.Component {

      constructor() {
        super();
        this.state = {
          messages: 'No new messages...',
        };
      };



    render() {

        return (
            <div className={styles.back}>
                  <div>
                    <div>{this.state.messages}</div>
                  </div>
            <Router>
                <div>
                    <NavigationBar />
                    <Routes>

                        <Route path="/" element={<Home/>}>
                        </Route>

                        <Route path="/login" element={<PageLogin/>}>
                        </Route>

                        <Route path="/user/insert" element={<AddUser/>}>
                        </Route>

                        <Route path="/user/update/:id" element={<EditUser/>}>
                        </Route>

                        <Route path="/user" element={<PageUsers/>}>
                        </Route>

                        <Route path="/device/insert" element={<AddDevice/>}>
                        </Route>

                        <Route path="/device/update/:id" element={<EditDevice/>}>
                        </Route>

                        <Route path="/device" element={<PageDevice/>}>
                        </Route>

                        <Route path="/device/forclient/:id" element={<PageDeviceForClient/>}>
                        </Route>

                        <Route path="/energyconsumption/insert" element={<AddEnergyConsumption/>}>
                        </Route>

                        <Route path="/energyconsumption/update/:id" element={<EditEnergyConsumption/>}>
                        </Route>

                        <Route path="/energyconsumption" element={<PageEnergyConsumption/>}>
                        </Route>

                        <Route path="/energyconsumption/charts" element={<PageCharts/>}>
                        </Route>

                        <Route path="/chat" element={<ChatRoom2/>}>
                        </Route>

                        {/*Error*/}
                         <Route path="/error" element={<ErrorPage/>}>
                         </Route>

                        <Route render={() =><ErrorPage/>} />
                    </Routes>
                </div>
            </Router>
            </div>
        )
    };
}

export default App
