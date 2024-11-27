import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import StudentService from '../service/StudentService';
import toast, { Toaster } from 'react-hot-toast';
// import "react-toastify/dist/ReactToastify.css";
const ReadStudents = () => {

  const navigate = useNavigate();
  const [student, setStudent] = useState(null);
  const [ loading, setLoading ] = useState(true);

  useEffect(
    ()=>{
      const fetchData = async () =>{
        try{
          setLoading(true);
          const response = await StudentService.getStudents();
          setStudent(response.data);
          setLoading(false);
        }
        catch (error){
          toast.error(error.response.data);
        }
      }

      fetchData();
    }, []);

  const clicked = (e, id) => {
    e.preventDefault();
    navigate(`/UpdateStudent/${id}`);
  };

  const [search, setSearch] = new useState('');


	return (
		<div>
        <div className='flex  mx-96'>
        <Toaster
          position="top-center"
          reverseOrder={false}
        />
          <button onClick={ () => navigate("/AddStudents") } className="font-serif hover:bg-slate-800 text-3xl my-5 px-3 rounded-lg py-2 bg-slate-900">📝 Add Student</button>
          
          <div className="relative mx-10 py-10 text-gray-600 focus-within:text-gray-400">
            <span className="absolute inset-y-0 left-0 items-center pl-2">
              <button onClick={ (e) => clicked(e, search) } type="submit" className="my-10 py-1 rounded-lg hover:bg-slate-700 focus:outline-none focus:shadow-outline">
              🔎
              </button>
            </span>
            <input type="search" value={search} onChange={ (e) => setSearch(e.target.value) } name="q" className="px-5 py-2 text-sm text-white bg-gray-900 rounded-md pl-10 focus:outline-none focus:bg-slate-800 focus:text-gray-100" placeholder="Search...">
            </input>
          </div>
        </div>

          <div>
          <table className="text-center text-xl table-auto border-collapse border border-slate-600 mx-96 py-2"> 
            <thead>
              <tr className="bg-slate-900 rounded border border-slate-600">
                <th className="px-5 border border-slate-600">ID</th>
                <th className="px-5 border border-slate-600">Name</th>
                <th className="px-5 border border-slate-600">Class</th>
                <th className="px-5 border border-slate-600">Age</th>
                <th className="px-5 border border-slate-600">Address</th>
              </tr>
            </thead>
            {
              !loading && (
                student.map((std) => (
                  <tbody>
                <tr key={std.id} onClick={ (e) => clicked(e, std.id) } className="hover:bg-slate-800">
                  <td className="px-5 border border-slate-600">{std.id}</td>
                  <td className="px-5 border border-slate-600">{std.name}</td>
                  <td className="px-5 border border-slate-600">{std.stuClass}</td>
                  <td className="px-5 border border-slate-600">{std.age}</td>
                  <td className="px-5 border border-slate-600">{std.address.addr1}</td> 
                </tr>
              </tbody>
                ))
              )
            }
              
          </table>
          </div>
        </div>
	);
}

export default ReadStudents;