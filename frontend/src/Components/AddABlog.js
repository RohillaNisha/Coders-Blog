import {useState} from "react";

function AddABlog() {

    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")
    async function createBlog(event){
        event.preventDefault()
        const res = await fetch("http://localhost:8080/api/blog/add", {
            method: "POST",
            body: JSON.stringify( {title: title, content: content}),
            credentials: "include",
            headers: {
                'Content-Type' : 'application/json'

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