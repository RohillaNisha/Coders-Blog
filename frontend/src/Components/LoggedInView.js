import AddABlog from "./AddABlog";
import OwnBlogs from "./OwnBlogs";
import {useAuth} from "../Context/AuthContext";

function LoggedInView(){

/*
    const token = localStorage.getItem('authToken');
*/




    return(
        <div>Welcome user
        <AddABlog></AddABlog>
            <OwnBlogs></OwnBlogs>
        </div>
    )

}
export default LoggedInView