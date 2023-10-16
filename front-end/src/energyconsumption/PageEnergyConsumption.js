import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function PageEnergyConsumption() {

    const[energyconsumptions,setEnergyConsumption]=useState([]);

    const{id}=useParams()

    useEffect(()=>{
        loadEnergyConsumption();
    },[]);

    const loadEnergyConsumption=async()=>{
        const result=await axios.get(HOST.backend_api+ "/energyconsumption");
        setEnergyConsumption(result.data);
    }

    const deleteEnergyConsumption = async(id)=>{
        await axios.delete(HOST.backend_api+ `/energyconsumption/delete/${id}`);
        loadEnergyConsumption();
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return (
        <div className="container">
            <div className="py-4">
            <Link className="btn btn-primary mx-2" to="/energyconsumption/insert">Add Energy Consumption</Link>
                <table class="table border shadow">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Consumption</th>
                      <th scope="col">Device</th>
                      <th scope="col">Timestamp</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  {
                    energyconsumptions.map((energyconsumption, index)=>(
                        <tr>
                          <td>{energyconsumption.id}</td>
                          <td>{energyconsumption.consumption}</td>
                          <td>{energyconsumption.id_device}</td>
                          <td>{energyconsumption.timestamp}</td>
                          <td>
            <Link className="btn btn-primary mx-2" to={`/energyconsumption/update/${energyconsumption.id}`}>Edit</Link>
            <button className="btn btn-danger mx-2" onClick={()=>deleteEnergyConsumption(energyconsumption.id)}>Delete</button>
                          </td>
                        </tr>
                    ))
                  }
                  </tbody>
                </table>
            </div>
            <Link className="btn btn-success mx-2" to={`/user`}>See Users</Link>
            <Link className="btn btn-success mx-2" to={`/device`}>See Devices</Link>
            <Link className="btn btn-primary mx-2" to={`/chat`}>Enter Chat</Link>
        </div>
    )
    return null;
}