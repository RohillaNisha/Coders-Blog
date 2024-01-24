import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import LoginForm from "./Components/LoginForm";
import Blogs from "./Components/Blogs";
import {Route, BrowserRouter, Routes} from "react-router-dom";
import StartPage from "./Components/StartPage";
import LoggedInView from "./Components/LoggedInView";
import SingleBlog from "./Components/SingleBlog";
import GetReports from "./Components/GetReports";

function App() {
  return (
    <BrowserRouter >
        <Routes>
            <Route path="/blogs" element={<StartPage/>}> </Route>
            <Route path="/blogs/:blogId" element={<SingleBlog/>}></Route>
            <Route path="/logged-in-view" element={<LoggedInView/>}> </Route>
            <Route path="/logged-in-view/:blogId" element={<SingleBlog/>}> </Route>
            <Route path="/vulnerabilities-reported" element={<GetReports/>}></Route>


        </Routes>
    </BrowserRouter>
  );
}

export default App;
