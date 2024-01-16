import Blogs from "./Blogs";
import LoginForm from "./LoginForm";
import {useNavigate} from "react-router-dom"

function StartPage(){

    return (
        <div className="container ms-2">
            <h1 className="text-center mt-4">Coder's Blog</h1>
            <div className="row mt-5 gx-3">
                <div className="col-9">
                    <Blogs/>
                </div>
                <div className="col-3 ">
                    <LoginForm/>
                </div>
            </div>
        </div>
    );

}
export default StartPage