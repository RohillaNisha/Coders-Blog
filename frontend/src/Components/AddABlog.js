import {useEffect, useState} from "react";
import {useAuth} from "../Context/AuthContext";
import Cookies from "js-cookie";

function AddABlog() {

    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")
    const {dispatch, state} = useAuth()
    const { role, user } = state;



    /*    useEffect(() => {
            // Set 'authToken' cookie with SameSite attribute
            document.cookie = "authToken=your_token_value; path=/; secure; SameSite=None";
        }, []); // useEffect runs once when the component mounts*/

    /*
        const token = localStorage.getItem('authToken');
    */
    async function createBlog(event){
        event.preventDefault()

        const csrfRes = await fetch("http://localhost:8080/csrf", {credentials: 'include'});
        const csrfToken = await csrfRes.json();

        console.log("csrf response is: " + csrfRes)
        console.log("csrf token object is: " + csrfToken)
        console.log("csrf token  is: " + csrfToken.token)




        const res = await fetch("http://localhost:8080/api/blog/add", {
            method: "POST",
            body: JSON.stringify( {title: title, content: content}),
            credentials: "include",
            headers: {
                'Content-Type' : 'application/json',
         /*       'Authorization' : `Bearer ${token}`,*/
                'X-CSRF-TOKEN' : csrfToken.token
            }
        })
        const result = res.ok
        if(result){
            alert("Blog added!")
            setTitle("")
            setContent("")
        }else alert("Something went wrong")
    }

    return (
        <div className="container">
            <div className="mb-3">
                <label className="form-label">Blog title</label>
                <input type="text" className="form-control" value={title} onChange={(event) => {setTitle(event.target.value)}}/>
            </div>
            <div className="mb-3">
                <label className="form-label">Blog content</label>
                <textarea className="form-control" rows="3" value={content} onChange={(event) => {setContent(event.target.value)}}></textarea>
            </div>
            <div className="mb-3">
                <button type="submit" className="btn btn-primary mb-3" onClick={createBlog}>Add blog</button>
            </div>
        </div>
    )

}

export default AddABlog