import React, {useEffect, useState} from "react";

function Blogs() {

    const [filteredData, setFilteredData] = useState([]);
    const [searchedValue, setSearchedValue] = useState("");

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
        console.log(body);
        setFilteredData(body);
    }
    catch(error)
    {
        console.error('Error fetching blogs');
    }}

    console.log(filteredData);

    async function handleSearch() {
        if(searchedValue === ""){
            await allBlogs()
        }
        try {
            const res = await fetch(`http://localhost:8080/api/blog/search/${searchedValue}`, {
                credentials: "include",
            });

            if (!res.ok) {
                throw new Error('Http error');
            }

            const body = await res.json();
            console.log(body);
            setFilteredData(body);
        } catch (error) {
            console.error('Error fetching blogs:', error.message);
        }
    }


    return(
        <div>
            <div className="input-group">
                <input type="search" className="form-control rounded" placeholder="Search" aria-label="Search"
                       aria-describedby="search-addon" value={searchedValue} onChange={(event) => {setSearchedValue(event.target.value)}}/>
                <button type="button" className="btn btn-outline-primary" onClick={handleSearch} data-mdb-ripple-init>search</button>
            </div>

            {filteredData.map((element) => (
                <div className="card ">
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