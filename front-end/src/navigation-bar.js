import React from 'react'
import logo from './commons/images/energyicon.jpg';
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavLink,
    UncontrolledDropdown
} from 'reactstrap';

const textStyle = {
    color: 'white',
    textDecoration: 'none'
};


export default function NavigationBar(){
    let navigate=useNavigate()
    const onSubmit=async (e)=>{
        e.preventDefault();
        localStorage.clear();
        navigate("/")
    }

    return<div>
        <Navbar color="dark" light expand="md">
            <NavbarBrand>
                     <form onSubmit={(e)=>onSubmit(e)}>
                              <div>
                                  <button type="submit" className="btn btn-danger mx-2">Logout</button>
                              </div>
                     </form>
            </NavbarBrand>
            <Nav className="mr-auto" navbar>

            </Nav>
        </Navbar>
    </div>;
    }
