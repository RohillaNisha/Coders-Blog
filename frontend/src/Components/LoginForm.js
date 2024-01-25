import React, {useState} from 'react'
import {Link, useNavigate} from "react-router-dom";
import {useAuth} from "../Context/AuthContext";

function LoginForm() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const {dispatch, state} = useAuth()


    const navigate = useNavigate()

    function handleShowReports() {
        navigate("/vulnerabilities-reported")
    }

    async function handleDeleteAllBlogs(event){

        const csrfRes = await fetch("http://localhost:8080/csrf", {credentials: 'include'});
        const csrfToken = await csrfRes.json();


        try {
            const res = await fetch (`http://localhost:8080/api/blog/delete-all`, {
                method: 'DELETE',
                credentials: "include",
                headers: {
                    'Content-Type' : 'application/json',
                    'X-CSRF-TOKEN' : csrfToken.token
                }
            })
            if (!res.ok) {
                throw new Error('Http error')
            }

            alert("All blogs deleted")
        }
        catch(error)
        {
            console.error('Error deleting blogs');
        }
    }

    async function login(event){
        event.preventDefault()

        if (!username || !password) {
            alert("Please fill out both credentials.");
            return;
        }

            const res =
                await fetch("http://localhost:8080/api/user/login", {
                    method: 'POST',
                    body: JSON.stringify({username: username, password: password}),
                    credentials: "include",
                    headers: {
                        'Content-Type': 'application/json'
                    }

                })


        if(res.ok){

            const result = await res.json();

            // Access role, username, and token from the response
            const { role, username, token } = result;

           dispatch({
               type: 'LOGIN',
               payload: {
                   user: { username: username},
                   token: token,
                   role: role,
               },
           });
            alert("Login successful")
            setPassword("")
        } else {
            // Check for specific HTTP status codes that indicate login failure
            if (res.status === 401) {
                alert("Invalid credentials. Login failed.");
            } else {
                alert("Login failed due to an unexpected error.");
            }
        }

    }

    async function handleGoogleLogin(event){
        event.preventDefault()

        try{
            const response = await fetch("http://localhost:8080/api/google/login", {
                method: "GET",
                credentials: "include"
            })
            if(response.ok){
                const data = await response.json()
                console.log("Login response: " + data)
            }
            else {
                console.error("Error " + response.status)
            }
        }
        catch (error){
            console.error("Error fetching data " + error)
        }
    }


    return (
        <div className="container mt-5 border border-secondary rounded p-4">

            {state.isAuthenticated ? (
                <div>
                    <h5>Hello {username}</h5>
                    {state.role === "ROLE_ADMIN" && (
                        <div>
                            <button
                                type="button"
                                className="btn btn-danger"
                                onClick={handleDeleteAllBlogs}
                            >
                                Delete All Blogs
                            </button>
                            <button
                                type="button"
                                className="btn btn-warning"
                                onClick={handleShowReports}
                            >
                                See reported vulnerabilities
                            </button>
                        </div>
                    )}
                    <Link to="/logged-in-view">
                        <button type="button" className="btn btn-primary">
                            Create/Edit your Blogs
                        </button>
                    </Link>
                </div>
            ) : (
                <form>
                   <h3 className= "text-center">Login </h3>
                    <div className="form-row">
                        {/*<button><a href="http://localhost:8080/api/google/login">Login via Google</a></button>*/}
                        <button onClick={handleGoogleLogin}>Login via Google</button>
                        <div className="col mb-3">
                            <label className="form-label">Username</label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                placeholder="Enter username"
                                value={username}
                                onChange={(event) => {
                                    setUsername(event.target.value);
                                }}
                            />
                        </div>
                        <div className="col mb-3">
                            <label className="form-label">Password</label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                placeholder="Enter password"
                                value={password}
                                onChange={(event) => {
                                    setPassword(event.target.value);
                                }}
                            />
                        </div>
                    </div>
                    <button type="button" className="btn btn-primary" onClick={login}>
                        Submit
                    </button>
                </form>
            )}
        </div>
    )
}

export default LoginForm