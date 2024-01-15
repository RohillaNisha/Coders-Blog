import React, {useState} from 'react'
import axios from "axios";

function LoginForm() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

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
            console.log("result " + result)

        if(result){
            alert("Login successful!")
            setUsername("")
            setPassword("")
        } else alert("Login failed")






    }

    return (
        <div className="container mt-4">
            <form>
                <div className="mb-3">
                    <label className="form-label">Username</label>
                    <input type="text" className="form-control" id="username" placeholder="Enter username" value={username} onChange={(event) => {setUsername(event.target.value)}}/>
                </div>
                <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input type="password" className="form-control" id="password" placeholder="Enter password" value={password} onChange={(event) => {setPassword(event.target.value)}}/>
                </div>
                <button type="submit" className="btn btn-primary" onClick={login}>Submit</button>
            </form>
        </div>
)
}

export default LoginForm