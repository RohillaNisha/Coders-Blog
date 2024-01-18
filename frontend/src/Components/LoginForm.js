import React, {useState} from 'react'
import {useNavigate} from "react-router-dom";

function LoginForm() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const navigate = useNavigate()

    async function loginWithGoogle(event) {
        event.preventDefault()

        const res =
            await fetch("http://localhost:8080/api/google/login", {
                method: "GET",
                redirect: "follow",
                credentials: "include"
            }).then((res) => res);
        if (res.redirected) {
            document.location = res.url;
        }
        const data = res.json()
        console.log(data)

    }

    const handleGoogleLogin = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/google/login');
            if (response.ok) {
                const token = await response.text();
                console.log('Google Auth Token:', token);
                // Handle the token as needed
            } else {
                console.error('Failed to authenticate with Google:', response.status);
            }
        } catch (error) {
            console.error('Error during Google authentication:', error);
        }}


        async function login(event) {
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

            if (result) {
                const token = await res.text();
                localStorage.setItem('authToken', token);
                navigate("/logged-in-view")
                setUsername("")
                setPassword("")
            } else alert("Login failed")


        }


        return (
            <div className="container border border-2 pb-3 pt-2">
                <button onClick={handleGoogleLogin}>Login with Google</button>
                OR
                <div className="container border border-2 rounded-2 ">
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
                                       placeholder="Enter password"
                                       value={password} onChange={(event) => {
                                    setPassword(event.target.value)
                                }}/>
                            </div>
                        </div>
                        <button type="submit" className="btn btn-primary" onClick={login}>Submit</button>
                    </form>

                </div>
            </div>
        )
    }

export default LoginForm