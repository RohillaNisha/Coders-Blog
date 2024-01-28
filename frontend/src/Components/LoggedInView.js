import AddABlog from "./AddABlog";
import OwnBlogs from "./OwnBlogs";

function LoggedInView(){


    return(
        <div>
        <AddABlog></AddABlog>
            <OwnBlogs></OwnBlogs>
        </div>
    )

}
export default LoggedInView