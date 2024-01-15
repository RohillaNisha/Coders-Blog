import React, {useState} from "react";

function Blogs() {

    const [owner , setOwner] = useState("")
    const [title , setTitle] = useState("")
    const [content , setContent] = useState("")
    const [date , setDate] = useState("")


    async function allBlogs(){
        const res = await fetch("http://localhost:8080/api/blog/all", {
            credentials: "include"

        })
        const body = await res.json()
        console.log(body)

    }


    return(
        <div className="card">
            <div className="card-header">
                {owner}
            </div>
            <div className="card-body">
                <h5 className="card-title"><a href="#">{title}</a></h5>
                <p className="card-text">{content}</p>
            </div>
            <div className="card-footer text-muted">
                {date}
            </div>
            <button type="submit" className="btn btn-primary" onClick={allBlogs}>Submit</button>
        </div>

    )

}

export default Blogs