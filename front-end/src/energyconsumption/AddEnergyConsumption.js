import React, {useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {HOST} from '../commons/hosts';

export default function AddEnergyConsumption(){

    let navigate=useNavigate()
    const [energyconsumption, setEnergyConsumption]=useState({
        consumption:"",
        id_device:"",
        timestamp:""
    })

    const{consumption, id_device, timestamp}=energyconsumption

    const onInputChange=(e)=>{
        setEnergyConsumption({...energyconsumption,[e.target.name]:e.target.value})
    };

    const onSubmit=async (e)=>{
        e.preventDefault();
        await axios.post(HOST.backend_api+ "/energyconsumption/insert", energyconsumption)
        navigate("/energyconsumption")
    }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='admin')
    return <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Register Energy Consumption</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="Consumption" className="form-label">
                        Consumption
                    </label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter consumption"
                    name="consumption"
                    value={consumption}
                    onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                <label htmlFor="Device" className="form-label">
                    Device
                </label>
                <input
                type={"text"}
                className="form-control"
                placeholder="Enter device"
                name="id_device"
                value={id_device}
                onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                <label htmlFor="Timestamp" className="form-label">
                    Timestamp
                </label>
                <input
                type={"text"}
                className="form-control"
                placeholder="Enter timestamp"
                name="timestamp"
                value={timestamp}
                onChange={(e)=>onInputChange(e)}/>
            </div>
            <button type="submit" className="btn btn-outline-primary">Submit</button>
            <Link className="btn btn-danger mx-2" to="/energyconsumption">Cancel</Link>
            </form>
             </div>
        </div>
    </div>;
return null;
}