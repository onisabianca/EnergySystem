import React, {useState, useEffect} from "react";
import axios from "axios";
import {Link, useNavigate, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function EditDevice(){

    let navigate=useNavigate()

    const {id}=useParams()

    const [device, setDevice]=useState({
        address:"",
        description:"",
        id_user:"",
        max_hourly_consumption:""
    })

    const{address,description, id_user, max_hourly_consumption}=device

    const onInputChange=(e)=>{
        setDevice({...device,[e.target.name]:e.target.value})
    };

    useEffect(()=>{
        loadDevice()
    },[]);

    const onSubmit=async (e)=>{
        e.preventDefault();
        await axios.post(HOST.backend_api+`/device/update/${id}`, device)
        navigate("/device")
    }

    const loadDevice = async()=>{
    const result = await axios.get(HOST.backend_api+`/device/${id}`)
    setDevice(result.data)
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Edit Device</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="Address" className="form-label">
                        Address
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter address"
                    name="address"
                    value={address}
                    onChange={(e)=>onInputChange(e)}/>
                </div>
                 <div className="mb-3">
                    <label htmlFor="Description" className="form-label">
                        Description
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter description"
                    name="description"
                    value={description}
                    onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                <label htmlFor="User" className="form-label">
                    User
                </label>
                <input
                type={"text"}
                className="form-control"
                placeholder="Enter user"
                name="id_user"
                value={id_user}
                onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                <label htmlFor="Maximum Hourly Consumption" className="form-label">
                    Maximum Hourly Consumption
                </label>
                <input
                type={"text"}
                className="form-control"
                placeholder="Enter maximum hourly consumption"
                name="max_hourly_consumption"
                value={max_hourly_consumption}
                onChange={(e)=>onInputChange(e)}/>
            </div>
            <button type="submit" className="btn btn-outline-primary">Submit</button>
            <Link className="btn btn-danger mx-2" to="/device">Cancel</Link>
            </form>
             </div>
        </div>
    </div>;
    return null;
}