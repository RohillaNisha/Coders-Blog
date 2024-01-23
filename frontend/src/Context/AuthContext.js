import React, { createContext, useContext, useReducer } from 'react';

const AuthContext = createContext();

const initialState = {
    isAuthenticated: false,
    user: null,
    token: null,
    role: null,
};

const authReducer = (state, action) => {
    switch (action.type) {
        case 'LOGIN':
            return {
                isAuthenticated: true,
                user: action.payload.user,
                token: action.payload.token,
                role: action.payload.role,
            };
        case 'LOGOUT':
            return initialState;
        default:
            return state;
    }
};

export const AuthProvider = ({ children }) => {
    const [state, dispatch] = useReducer(authReducer, initialState);

    return (
        <AuthContext.Provider value={{ state, dispatch }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};