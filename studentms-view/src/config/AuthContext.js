import toast from "react-hot-toast";
import keycloak from "./keycloak";
import { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();


export const AuthProvider = ({children}) => {
    
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [keycloakObject, setKeycloakObject] = useState(null);

    useEffect(()=>{
        keycloak.init({
            onLoad: 'check-sso'
        })
        .then( (authenticated) => {
            if (keycloak.authenticated == true){
                setIsAuthenticated(authenticated);
                setKeycloakObject(keycloak);
                toast.success("Logged in successfully");
            }
        }
        )
        .catch((error) => {
            toast.error("Login failed");
        })
    }, []);

    return <AuthContext.Provider value = {{
        isAuthenticated:isAuthenticated,
        keycloak: keycloakObject
        }}>
        {children}
    </AuthContext.Provider>
}

export const useAuth = () => {
    return useContext(AuthContext);
}