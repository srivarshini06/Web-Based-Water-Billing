import { createContext, useState } from "react";

export const AuthContext = createContext();

const AuthProvider = ({ children }) => {

    const [user, setUser] = useState({
        token: localStorage.getItem("token"),
        role: localStorage.getItem("role"),
        fullName: localStorage.getItem("fullName"),
    });

    const login = (data) => {

        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);
        localStorage.setItem("fullName", data.fullName);

        setUser(data);
    };

    const logout = () => {

        localStorage.clear();

        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;