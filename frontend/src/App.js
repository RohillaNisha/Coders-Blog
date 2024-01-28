import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import LoginForm from "./Components/LoginForm";
import {Route, BrowserRouter, Routes} from "react-router-dom";
import StartPage from "./Components/StartPage";
import LoggedInView from "./Components/LoggedInView";
import SingleBlog from "./Components/SingleBlog";
import GetReports from "./Components/GetReports";
import Navbar from "./Components/Navbar";
import ReportVulnerability from "./Components/ReportVulnerability";
import IntegrityPolicy from "./Components/IntegrityPolicy";
import {AuthProvider} from "./Context/AuthContext";

function App() {
  return (
      <AuthProvider>
      <BrowserRouter>
          <div>
              <Navbar/>
              <Routes>
                  <Route path="/blogs" element={<StartPage/>}/>
                  <Route path="/blogs/:blogId" element={<SingleBlog/>}/>
                  <Route path="/login" element={<LoginForm/>}/>
                  <Route path="/logged-in-view" element={<LoggedInView/>}/>
                  <Route path="/logged-in-view/:blogId" element={<SingleBlog/>}/>
                  <Route path="/vulnerabilities-reported" element={<GetReports/>}/>
                  <Route path="/report-vulnerabilities" element={<ReportVulnerability/>}/>
                  <Route path="/integrity-policy" element={<IntegrityPolicy/>}/>


              </Routes>
          </div>
      </BrowserRouter>
      </AuthProvider>
  );
}

export default App;
