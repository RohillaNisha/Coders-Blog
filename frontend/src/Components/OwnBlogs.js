import React, {useEffect, useState} from "react";

function OwnBlogs() {

    const [filteredData, setFilteredData] = useState([]);
    const token = localStorage.getItem('authToken');


    useEffect(() => {
        allBlogs()
    }, []);

    async function allBlogs() {
        try {
            const res = await fetch("http://localhost:8080/api/blog/my-blogs", {
                credentials: "include",
                headers: {
                    'Content-Type' : 'application/json',
                    'Authorization' : `Bearer ${token}`

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

    console.log(filteredData);


    return(
        <div className= "container">
            {filteredData.map((element) => (
                <div className = "card ">
                    <div key={element.blogId} className="card">
                        <div className="card-header">{element.blogId}</div>
                        <div className="card-body">
                            <h5 className="card-title"><a href="#">{element.title}</a></h5>
                            <p className="card-text">{element.content}</p>
                        </div>
                        <div className="card-footer text-muted">{element.postDate}</div>
                    </div>
                </div>
            ))}
        </div>
    );

}

export default OwnBlogs