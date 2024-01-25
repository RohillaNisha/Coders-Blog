import {Link} from "react-router-dom";
import DOMPurify from "dompurify";
import React, {useEffect, useState} from "react";

function GetReports() {
    const [filteredData, setFilteredData] = useState([]);



    useEffect(() => {
        allReports()
    }, []);

    async function allReports() {
        try {
            const res = await fetch("http://localhost:8080/api/vulnerability/all", {
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
            console.error('Error fetching Vulnerability reports');
        }}


    return (
        <div className="container">
            {filteredData.map((element) => (
                <div className="card ">
                    <div key={element.vulId} className="card">
                        <div className="card-header">{element.vulId}</div>
                        <div className="card-body">
                            <h5 className="card-title">{element.vulCategory}</h5>
                            <p className="card-text">{element.description}</p>
                        </div>
                        <div className="card-footer text-muted">{element.reportedBy} , {element.contactDetails}</div>
                    </div>
                </div>
            ))}
        </div>
    )

}

export default GetReports