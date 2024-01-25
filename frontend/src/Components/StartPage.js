import Blogs from "./Blogs";

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