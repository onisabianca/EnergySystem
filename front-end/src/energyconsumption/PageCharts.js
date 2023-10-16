import { LineChart } from '@rsuite/charts';

import React, { useState, useEffect } from 'react'
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import {HOST} from '../commons/hosts';
import {useNavigate} from "react-router-dom";

function useForceUpdate(){
    const [value, setValue] = useState(0);
    return () => setValue(value => value + 1);
}

export default function PageCharts() {

const forceUpdate = useForceUpdate();
    //let data = []
    const[data, setData] = useState([]);
    let navigate=useNavigate()
     const [energyChart, setEnergyChart]=useState({
         id_device:"",
         timestamp:""
     })

     const{id_device, timestamp}=energyChart

useEffect(() => {
    console.log(data)
  });

         const [energyData, setEnergyData]=useState({
             id: "",
             id_device:"",
             timestamp:"",
             consumption:""
         })

     const onInputChange=(e)=>{
         setEnergyChart({...energyChart,[e.target.name]:e.target.value})
     };

     const onSubmit=async (e)=>{
        e.preventDefault();

        axios.post(HOST.backend_api +"/energyconsumption/charts", energyChart)
              .then((response) => {
              data.length = 0
                for (var key in response.data) {
                    let newObj = [];
                    let date = new Date( Date.parse(response.data[key].timestamp));
                    newObj[0]= date.getHours().toString() + ":" + date.getMinutes().toString()
                    newObj[1] = response.data[key].consumption
                    data.push(newObj)
                }
            console.log(data)
            forceUpdate()
            })

     }

const userCurr= JSON.parse(localStorage.getItem("user"));
if(userCurr.role=='client')
  return (
   <div className="container">
   <Link className="btn btn-success mx-2" to={`/device/forclient/`+userCurr.id}>See Devices</Link>
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Enter data:</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>
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

                <button type="submit" className="btn btn-outline-primary">Confirm</button>
                </form>
                 </div>
            </div>
            <LineChart name="consumption" data={data} />
        </div>
  );
  return null;
}