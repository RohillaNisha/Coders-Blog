import {useEffect, useState} from "react";
import {useAuth} from "../Context/AuthContext";
import DOMPurify from "dompurify";

function AddABlog() {

    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")
    const {dispatch, state} = useAuth()
    const { role, user } = state;

    async function createBlog(event){
        event.preventDefault()

        if (!title || !content) {
            alert("Please fill out both Title and Content fields.");
            return;
        }

        const csrfRes = await fetch("http://localhost:8080/csrf", {credentials: 'include'});
        const csrfToken = await csrfRes.json();


        const res = await fetch("http://localhost:8080/api/blog/add", {
            method: "POST",
            body: JSON.stringify( {title: title, content: content}),
            credentials: "include",
            headers: {
                'Content-Type' : 'application/json',
                'X-CSRF-TOKEN' : csrfToken.token
            }
        })
        const result = res.ok
        if(result){
            alert("Blog added!")
            setTitle("")
            setContent("")
        }else alert("Both title and content is mandatory")
    }


    return (
        <div className="container">

            <div className="mb-3">
                <label className="form-label">Blog title</label>
                <input type="text" className="form-control" value={DOMPurify.sanitize(title)} onChange={(event) => {setTitle(event.target.value)}}/>
            </div>
            <div className="mb-3">
                <label className="form-label">Blog content</label>
                <textarea className="form-control" rows="3" value={DOMPurify.sanitize(content)} onChange={(event) => {setContent(event.target.value)}}></textarea>
            </div>
            <div className="mb-3">
                <button type="submit" className="btn btn-primary mb-3" onClick={createBlog}>Add blog</button>
            </div>
        </div>
    )

}

export default AddABlog