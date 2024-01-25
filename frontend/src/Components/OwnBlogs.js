import React, {useEffect, useState} from "react";
import DOMPurify from "dompurify";
import {Link} from "react-router-dom";

function OwnBlogs() {

    const [filteredData, setFilteredData] = useState([]);


    useEffect(() => {
        allBlogs()
    }, []);

    async function allBlogs() {
        try {
            const res = await fetch("http://localhost:8080/api/blog/my-blogs", {
                credentials: "include",
                headers: {
                    'Content-Type' : 'application/json'

                }

            })
            if (!res.ok) {
                throw new Error('Http error')
            }

            const body = await res.json()
            console.log(body);
            setFilteredData(body);
        }
        catch(error)
        {
            console.error('Error fetching blogs');
        }}

    async function handleDelete(blogId){

        const csrfRes = await fetch("http://localhost:8080/csrf", {credentials: 'include'});
        const csrfToken = await csrfRes.json();

        try {
            const res = await fetch (`http://localhost:8080/api/blog/${blogId}/delete`, {
                method: 'DELETE',
                credentials: "include",
                headers: {
                    'Content-Type' : 'application/json',
                    'X-CSRF-TOKEN' : csrfToken.token
                }
            })
            if (!res.ok) {
                throw new Error('Htt error')
            }

            alert("Blog deleted")
        }
        catch(error)
        {
            console.error('Error deleting blog');
        }
    }



    return(
        <div className= "container">
            {filteredData.map((element) => (
                <div className = "card ">
                    <div key={element.blogId} className="card">
                        <div className="card-header">{element.blogId}</div>
                        <div className="card-body">
                            <h5 className="card-title"><Link to={`${element.blogId}`}>{DOMPurify.sanitize(element.title)}</Link></h5>
                            <p className="card-text">{element.content}</p>
                        </div>
                        <div className="card-footer text-muted">{element.postDate}</div>
                    </div>
                    <button onClick={() => { handleDelete(element.blogId)}}>Delete</button>
                </div>
            ))}
        </div>
    );

}

export default OwnBlogs