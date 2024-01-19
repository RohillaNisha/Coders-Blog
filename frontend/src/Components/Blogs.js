import React, {useEffect, useState} from "react";

function Blogs() {

    const [filteredData, setFilteredData] = useState([]);
    const [loading, setLoading ] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        allBlogs()
    }, []);

    async function allBlogs() {
        try {
            const res = await fetch("http://localhost:8080/api/blog/all", {
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
        <div>
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

export default Blogs