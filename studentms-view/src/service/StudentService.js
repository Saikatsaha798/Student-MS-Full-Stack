import axios from 'axios';
import { useState } from 'react';
import keycloak from '../config/keycloak';

const STUDENT_API_URL = "http://localhost:8080/students/";

class StudentService{
    // username = "";
    // password = "";

    // constructor(){
    //     this.username = sessionStorage.getItem('username');
    //     this.password = sessionStorage.getItem("password");
    // }

    // saveStudent(Student){
    //     return axios.post(STUDENT_API_URL+"create", Student, 
    //         {   auth : {
    //         username : this.username,
    //         password : this.password}
    //         }
    //     );
    // }

    saveStudent(Student){
        return axios.post(STUDENT_API_URL+"create", Student, 
            {   headers : {
            Authorization : `Bearer ${keycloak.token}`
        }}
        );
    }

    getStudents(){
        return axios.get(STUDENT_API_URL+"read", {   headers : {
            Authorization : `Bearer ${keycloak.token}`
        }}
        );
    }

    getStudentById(id){
        // console.log(STUDENT_API_URL+"read/"+id);
        return axios.get(STUDENT_API_URL+"read/"+id, {   headers : {
            Authorization : `Bearer ${keycloak.token}`
        }
            });
    }

    updateStudent(Student, id){
        return axios.put(STUDENT_API_URL+"update/"+id, Student, {   headers : {
            Authorization : `Bearer ${keycloak.token}`
        }
            });
    }

    deleteStudent(id){
        return axios.delete(STUDENT_API_URL+"delete/"+id, {   headers : {
            Authorization : `Bearer ${keycloak.token}`
        }
            });
    }
}

export default new StudentService();