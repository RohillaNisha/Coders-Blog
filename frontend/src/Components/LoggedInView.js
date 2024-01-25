import AddABlog from "./AddABlog";
import OwnBlogs from "./OwnBlogs";

function LoggedInView(){


    return(
        <div>Welcome user
        <AddABlog></AddABlog>
            <OwnBlogs></OwnBlogs>
        </div>
    )

}
export default LoggedInView