import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function PageDevice() {

    const[devices,setDevice]=useState([]);

    const{id}=useParams()

    useEffect(()=>{
        loadDevice();
    },[]);

    const loadDevice=async()=>{
        const result=await axios.get(HOST.backend_api+"/device");
        setDevice(result.data);
    }

    const deleteDevice = async(id)=>{
        await axios.delete(HOST.backend_api+`/device/delete/${id}`);
        loadDevice();
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return (
        <div className="container">
            <div className="py-4">
            <Link className="btn btn-primary mx-2" to="/device/insert">Add Device</Link>
                <table class="table border shadow">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Address</th>
                      <th scope="col">Description</th>
                      <th scope="col">User</th>
                      <th scope="col">Maximum hourly consumption</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  {
                    devices.map((device, index)=>(
                        <tr>
                          <td>{device.id}</td>
                          <td>{device.address}</td>
                          <td>{device.description}</td>
                          <td>{device.id_user}</td>
                          <td>{device.max_hourly_consumption}</td>
                          <td>
            <Link className="btn btn-primary mx-2" to={`/device/update/${device.id}`}>Edit</Link>
            <button className="btn btn-danger mx-2" onClick={()=>deleteDevice(device.id)}>Delete</button>
                          </td>
                        </tr>
                    ))
                  }
                  </tbody>
                </table>
            </div>
             <Link className="btn btn-success mx-2" to={`/user`}>See Users</Link>
             <Link className="btn btn-success mx-2" to={`/energyconsumption`}>See Energy Consumptions</Link>
             <Link className="btn btn-primary mx-2" to={`/chat`}>Enter Chat</Link>
        </div>
    )
    return null;
}