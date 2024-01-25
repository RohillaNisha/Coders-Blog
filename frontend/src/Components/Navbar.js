import React from 'react';

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-dark-subtle">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Coder's Blog</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <a className="nav-link active" aria-current="page" href="/blogs">Home</a>
                        <a className="nav-link" href="/report-vulnerabilities">Report Vulnerabilities</a>
                        <a className="nav-link" href="/login">Login</a>
                        <a className="nav-link" href="/integrity-policy">Integrity Policy</a>
                    </div>
                </div>
            </div>
        </nav>
    )

};

export default Navbar;