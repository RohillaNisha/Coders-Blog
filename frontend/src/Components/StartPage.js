import Blogs from "./Blogs";
import LoginForm from "./LoginForm";
import {useNavigate} from "react-router-dom"
import ReportVulnerability from "./ReportVulnerability";

function StartPage(){

    return (
        <div className="container ms-2">
            <div className="row mt-5 gx-3">
                <div className="col-9">
                    <Blogs/>
                </div>
            </div>
        </div>
    );

}

export default StartPage