import './App.css';
import { useEffect, useState } from 'react';
import ReadStudents from './components/ReadStudents'
import AddStudents from './components/AddStudents';
import UpdateStudent from './components/UpdateStudent';
import Login from './components/Login';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import toast, { Toaster } from 'react-hot-toast';
// import "react-toastify/dist/ReactToastify.css";
import student from './images/student.png';
import { useAuth } from './config/AuthContext';

function App() {

  const {isAuthenticated, keycloak} = useAuth();

  const [isOpen, setIsOpen] = useState(true);
  console.log(keycloak, isOpen);

  useEffect(()=>{
    setIsOpen(!isAuthenticated);
  }, [isAuthenticated])

  const handleLogout = () => {
    toast.success("Logged out successfully");

    // setTimeout( () => {setIsOpen(true);}, 750);
    setTimeout( () => {keycloak.logout();}, 750);
  }

  return (
    <div className="App">

      <Toaster
        position="top-center"
        reverseOrder={false}
      />
      
        <div className="Header flex ml-44">
          <div className="flex rounded-lg tracking-wider text-center font-serif text-5xl mx-32 px-2 my-5 py-4 bg-slate-900">
            <img src = {student} style={{ width: '50px', height: '50px' }}/>
            <p className='px-2'>Student Management System</p>
          </div>

          {!isOpen &&  <div className='group relative hover:bg-slate-950 bg-slate-800 text-xl my-10 px-3 py-2 rounded-xl h-12 '>
              <button onClick={handleLogout} className='transition duration-300 ease-in-out'>
                
              <span className="block group-hover:hidden">{keycloak?.tokenParsed.given_name}</span>
              <span className="hidden group-hover:block">logout</span>
                
              </button>
            </div>}
        </div>

        { isOpen && < Login isOpen = {isOpen} setIsOpen={setIsOpen} />}

      { !isOpen && <BrowserRouter>
        <Routes>
          <Route index element={ <ReadStudents/> } />
          <Route path="/" element={ <ReadStudents/> } />
          <Route path='/AddStudents' element={ <AddStudents/> } />
          <Route path='/UpdateStudent/:id' element={ <UpdateStudent/> } />
        </Routes>
      </BrowserRouter> }

    </div>
  );
};

export default App;
