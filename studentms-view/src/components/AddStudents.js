import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import StudentService from '../service/StudentService';
import toast, { Toaster } from 'react-hot-toast';
// import "react-toastify/dist/ReactToastify.css";


const AddStudents = () => {
    const navigate = useNavigate();

    const [student, setStudent] = useState({
        name : "",
        stuClass : "",
        age : "",
        address : {
            addr1 : "",
            city : "",
            pin : "",
            street : ""
        }
    });

    const resetFields = (e) =>{
        e.preventDefault();
        setStudent({
            name : "",
            stuClass : "",
            age : "",
            address : {
                addr1 : "",
                city : "",
                pin : "",
                street : ""
            }
        });
    }

    const handleChange = (e) => {
        const { name, value } = e.target;

        if (name.startsWith("address.")){
            const addressField = name.split(".")[1];

            setStudent({
                ...student, 
                address : {
                    ...student.address,
                    [addressField] : value
                }
            });
        }
        else{
            setStudent({
                ...student,
                [name] : value
            })
        }
    }

    const saveStudent = (e, std) => {
        e.preventDefault();
        StudentService.saveStudent(std)
        .then(() => {
            toast.success("Data has been saved.");
        })
        .catch(
            (error) =>{
                toast.error(error.response.data);
            }
        );
    };

    return (
    <div className='font-serif mx-96 my-4 text-slate-100'>
        <Toaster
            position="top-center"
            reverseOrder={false}
        />

        <p className='text-xl tracking-wide italic text-slate-400'>Enter the details below :</p>
        <div className='text-2xl my-4'>
            <div className='flex my-2'>
                <p className='py-1 w-28'>Name :</p>
                <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.name} type='text' name='name'></input>
            </div>

            <div className='flex my-2'>
                <p className='py-1 w-28'>Class :</p>
                <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.stuClass} type='number' name='stuClass'></input>
            </div>

            <div className='flex my-2'>
                <p className='py-1 w-28'>Age :</p>
                <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.age} type='number' name='age'></input>
            </div>

            <div className='flex my-2'>
                <p className='py-1 w-28'>Address :</p>
            </div>

            <div className='mx-14 text-xl'>
                <div className='flex my-2'>
                    <p className='py-1 w-28'>Address 1 :</p>
                    <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.address.addr1} type='text' name='address.addr1'></input>
                </div>

                <div className='flex my-2'>
                    <p className='py-1 w-28'>City :</p>
                    <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.address.city} type='text' name='address.city'></input>
                </div>

                <div className='flex my-2'>
                    <p className='py-1 w-28'>Pin :</p>
                    <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.address.pin} type='number' name='address.pin'></input>
                </div>

                <div className='flex my-2'>
                    <p className='py-1 w-28'>Street :</p>
                    <input className='mx-3 px-2 py-1 border border-slate-950 bg-slate-900' onChange={handleChange} value={student.address.street} type='text' name='address.street'></input>
                </div>
            </div>

        </div>

        <div className='text-xl flex mx-24'>
            <button onClick={ (e) => saveStudent(e, student) } className='mx-5 hover:bg-green-500 my-4 bg-green-600 px-5 py-2 rounded-lg'>Save</button>
            <button onClick={ resetFields } className='mx-5 hover:bg-red-500 my-4 bg-red-600 px-5 py-2 rounded-lg'>Reset</button>
            <button onClick={ ()=> navigate("/")} className='mx-5 my-4 hover:bg-blue-500 bg-blue-600 px-5 py-2 rounded-lg'>Cancel</button>
        </div>
        
    </div>
  );
}

export default AddStudents