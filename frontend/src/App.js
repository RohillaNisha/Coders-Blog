import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import LoginForm from "./Components/LoginForm";
import Blogs from "./Components/Blogs";

function App() {
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

export default App;
