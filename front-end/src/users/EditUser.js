import React, {useState, useEffect} from "react";
import axios from "axios";
import {Link, useNavigate, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function EditUser(){

    let navigate=useNavigate()

    const {id}=useParams()
    const [user, setUser]=useState({
        name:"",
        username:"",
        role:""
    })

    const{name, username, role}=user

    const onInputChange=(e)=>{
        setUser({...user,[e.target.name]:e.target.value})
    };

    useEffect(()=>{
        loadUser()
    },[]);

    const onSubmit=async (e)=>{
        e.preventDefault();
        await axios.post(HOST.backend_api+ `/user/update/${id}`, user)
        navigate("/user")
    }

    const loadUser = async()=>{
    const result = await axios.get(HOST.backend_api+ `/user/${id}`)
    setUser(result.data)
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Edit User</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="Name" className="form-label">
                        Name
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter name"
                    name="name"
                    value={name}
                    onChange={(e)=>onInputChange(e)}/>
                </div>
                 <div className="mb-3">
                    <label htmlFor="Username" className="form-label">
                        Username
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter username"
                    name="username"
                    value={username}
                    onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                <label htmlFor="Role" className="form-label">
                    Role
                </label>
                <input
                type={"text"}
                className="form-control"
                placeholder="Enter role"
                name="role"
                value={role}
                onChange={(e)=>onInputChange(e)}/>
            </div>
            <button type="submit" className="btn btn-outline-primary">Submit</button>
            <Link className="btn btn-danger mx-2" to="/user">Cancel</Link>
            </form>
             </div>
        </div>
    </div>;
    return null;
}