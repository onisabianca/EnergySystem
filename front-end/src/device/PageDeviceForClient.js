import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function PageDeviceForClient() {

    const[devices,setDevice]=useState([]);

    const{id}=useParams()

    useEffect(()=>{
        loadDevice();
    },[]);


const userCurr= JSON.parse(localStorage.getItem("user"));

    const loadDevice=async()=>{
        const result=await axios.get(HOST.backend_api+"/device/forclient/"+ userCurr.id);
        setDevice(result.data);
    }

if(userCurr.role=='client')
    return (
        <div className="container">
            <div className="py-4">
            <label htmlFor="Devices" className="form-label">Devices</label>
            <Link className="btn btn-success mx-2" to={`/energyconsumption/charts`}>See Charts</Link>
            <Link className="btn btn-primary mx-2" to={`/chat`}>Enter Chat</Link>
                <table class="table border shadow">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Address</th>
                      <th scope="col">Description</th>
                      <th scope="col">Maximum hourly consumption</th>
                    </tr>
                  </thead>
                  <tbody>
                  {
                    devices.map((device, index)=>(
                        <tr>
                          <td>{device.id}</td>
                          <td>{device.address}</td>
                          <td>{device.description}</td>
                          <td>{device.max_hourly_consumption}</td>
                          <td>
                          </td>
                        </tr>
                    ))
                  }
                  </tbody>
                </table>
            </div>
        </div>
    )
    return null;
}