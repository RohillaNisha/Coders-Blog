import React, {useState} from 'react'
import {Link, useNavigate} from "react-router-dom";

function LoginForm() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate()

    async function login(event){
        event.preventDefault()


            const res =
                await fetch("http://localhost:8080/api/user/login", {
                    method: 'POST',
                    body: JSON.stringify({username: username, password: password}),
                    credentials: "include",
                    headers: {
                        'Content-Type': 'application/json'
                    }

                })

            const result = res.ok

        if(result){
            const token = await res.text();
            localStorage.setItem('authToken', token);
            setIsAuthenticated(true);
/*
            navigate("/logged-in-view")
*/
            setUsername("")
            setPassword("")
        } else alert("Login failed")

    }

    console.log("Authentication is" + isAuthenticated);


    return (
        <div className="container border border-2 rounded-2 ">
            {isAuthenticated ? (
                <div>
                    <h5> Hello {username} </h5>
                    <Link to="/logged-in-view">  <button type="submit" className="btn btn-primary"> Create/Edit your Blogs</button> </Link>
                </div>
                    ) : (
                    <form>
                        <div className="form-row">
                            <div className="col mb-3">
                                <label className="form-label">Username</label>
                                <input type="text" className="form-control" id="username" placeholder="Enter username"
                                       value={username} onChange={(event) => {
                                    setUsername(event.target.value)
                                }}/>
                            </div>
                            <div className="col mb-3">
                                <label className="form-label">Password</label>
                                <input type="password" className="form-control" id="password"
                                       placeholder="Enter password" value={password} onChange={(event) => {
                                    setPassword(event.target.value)
                                }}/>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-primary" onClick={login}>Submit</button>
                    </form>
                    )}
                </div>
            )
            }

            export default LoginForm