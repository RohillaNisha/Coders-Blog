import React, {useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";

function SingleBlog() {

    const [filteredData, setFilteredData] = useState();
    const [loading, setLoading ] = useState(true);
    const [error, setError] = useState(null);
const {blogId} = useParams()

    useEffect( () => {
        getBlog()
    }, []);


    async function getBlog() {
        try {
            const res = await fetch(`http://localhost:8080/api/blog/${blogId}`, {
                credentials: "include"

            })
            if (!res.ok) {
                throw new Error('Http error')
            }

            const body = await res.json()
            setFilteredData(body);
            setLoading(false);
        }
        catch(error)
        {
            console.error('Error fetching blogs', error);
            setError("Error fetching blogs");
            setLoading(false);
        }}
    if(loading){
        return <p>Loading.....</p>
    }

    if(error){
        return <p>{error}</p>
    }



    return(
        <div className = "container mt-5">
            <div className="card text-center">
                <div key={filteredData.blogId} className="card">
                    <div className="card-header">BlogId: {filteredData.blogId}</div>
                    <div className="card-body">
                        <h5 className="card-title">{filteredData.title}</h5>
                        <p className="card-text">{filteredData.content}</p>
                    </div>
                    <div className="card-footer text-muted">{filteredData.postDate}</div>
                </div>
            </div>
        </div>
    )

}

export default SingleBlog