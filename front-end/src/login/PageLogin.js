import React, {useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function PageLogin(){

    let navigate=useNavigate()
    const [credentials, setCredentials]=useState({
        username:"",
        password:""
    })

    const [user, setUser]=useState({
        id: "",
        usernameu:"",
        passwordu:"",
        name:"",
        role:""
    })

    const{username, password}=credentials

    const onInputChange=(e)=>{
        setCredentials({...credentials,[e.target.name]:e.target.value})
    };

    const onSubmit=async (e)=>{
        e.preventDefault();

              axios.post(HOST.backend_api+ '/login', credentials)
              .then((response) => {
              localStorage.clear()
                const user = response.data
                  localStorage.setItem('user', JSON.stringify(user))
                  if(user.role=='admin')  navigate("/user");
                    else if (user.role=='client') navigate("/device/forclient/"+user.id);
                    else navigate("/");
            })
              .catch((error) => {
              })
    }

    return <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Login</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
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
                <label htmlFor="Password" className="form-label">
                    Password
                </label>
                <input
                type={"password"}
                className="form-control"
                placeholder="Enter password"
                name="password"
                value={password}
                onChange={(e)=>onInputChange(e)}/>
                </div>

            <button type="submit" className="btn btn-outline-primary">Login</button>
            <Link className="btn btn-danger mx-2" to="/">Cancel</Link>
            </form>
             </div>
        </div>
    </div>;
}