import React from 'react';
import LogoutButton from './LogoutButton';
import { Link } from 'react-router-dom';
import { useAuth } from '../Context/AuthContext';

const Navbar = () => {
    const { state } = useAuth();

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-dark-subtle">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">
                    Coder's Blog
                </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <Link className="nav-link" to="/blogs">
                            Home
                        </Link>
                        <Link className="nav-link" to="/report-vulnerabilities">
                            Report Vulnerabilities
                        </Link>

                        {state.isAuthenticated ? (
                            <LogoutButton />
                        ) : (
                            <Link className="nav-link" to="/login">
                                Login
                            </Link>
                        )}

                        <Link className="nav-link" to="/integrity-policy">
                            Integrity Policy
                        </Link>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;