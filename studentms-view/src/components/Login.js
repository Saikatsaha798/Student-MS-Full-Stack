import React from 'react'
import { useState } from 'react';
import toast, { Toaster } from 'react-hot-toast';
import axios from 'axios';
import keycloak from '../config/keycloak';

const Login = ({isOpen,setIsOpen}) => {
    const [userData, setUserData] = useState({
        username : "",
        password : ""
      });

    const handleChange  = (e) => {
        const {name, value} = e.target;

        setUserData({
                ...userData,
                [name] : value
        });
    }

    const handleLogin = async () => {

        try {
            const response = await axios.get('http://localhost:8080/users/verify',
                {
                    auth : {
                    username : userData.username,
                    password : userData.password
                }}
            );

            if (response.status == 200){
                sessionStorage.setItem('username', userData.username);
                sessionStorage.setItem('password', userData.password);

                toast.success("Logged in successfully");

            
                setTimeout(() => {setIsOpen(false)}, 750);

            }
            else{
                toast.error("Login failed");
            }
        }
        catch (error){
            console.log("Error message: ",error)
            toast.error("Login failed");
        }

    }

    
  return (
    <div className='flex justify-center'>
        <Toaster
            position="top-center"
            reverseOrder={false}
        />
        <div className='bg-slate-800 px-5 py-1 rounded-md'>
            <p className='text-center text-3xl py-3'>User</p>
            <div className='flex py-2'>
                <p className='w-24'>Username : </p>
                <input className='rounded bg-slate-900 px-2' name='username' onChange={handleChange} value={userData.username} type='text'>
                </input>
            </div>
            <div className='flex py-2'>
                <p className='w-24'>Password : </p>
                <input className='rounded bg-slate-900 px-2' name='password' onChange={handleChange} value={userData.password} type='password'>
                </input>
            </div>
            <div className='flex justify-center py-2'>
                <button className='ml-2 hover:bg-green-500 bg-green-600 px-2 py-1 rounded-xl' onClick={handleLogin}>
                    login
                </button>

                <button className='ml-8 hover:bg-blue-500 bg-blue-600 px-2 py-1 rounded-xl' onClick={()=> keycloak.login()}>
                    Keycloak
                </button>
            </div>
            
        </div>
    </div>
  )
}   

export default Login