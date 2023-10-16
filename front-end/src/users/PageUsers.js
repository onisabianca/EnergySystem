import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function PageUsers() {

    const[users,setUsers]=useState([]);

    const{id}=useParams()

    useEffect(()=>{
        loadUsers();
    },[]);

    const loadUsers=async()=>{
        const result=await axios.get(HOST.backend_api+ "/user");
        setUsers(result.data);
    }

    const deleteUser = async(id)=>{
        await axios.delete(HOST.backend_api+`/user/delete/${id}`);
        loadUsers();
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return (
        <div className="container">
            <div className="py-4">
            <Link className="btn btn-primary mx-2" to="/user/insert">Add User</Link>
                <table class="table border shadow">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">Name</th>
                      <th scope="col">Username</th>
                      <th scope="col">Role</th>
                      <th scope="col">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  {
                    users.map((user, index)=>(
                        <tr>
                          <td>{user.id}</td>
                          <td>{user.name}</td>
                          <td>{user.username}</td>
                          <td>{user.role}</td>
                          <td>
            <Link className="btn btn-primary mx-2" to={`/user/update/${user.id}`}>Edit</Link>
            <button className="btn btn-danger mx-2" onClick={()=>deleteUser(user.id)}>Delete</button>
                          </td>
                        </tr>
                    ))
                  }
                  </tbody>
                </table>
            </div>
            <Link className="btn btn-success mx-2" to={`/device`}>See Devices</Link>
            <Link className="btn btn-success mx-2" to={`/energyconsumption`}>See Energy Consumptions</Link>
            <Link className="btn btn-primary mx-2" to={`/chat`}>Enter Chat</Link>
        </div>
    )
    return null;
}