import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css';
import LoginForm from "./Components/LoginForm";
import Blogs from "./Components/Blogs";

function App() {
  return (
    <div className="Coders Blog">
        <Blogs/>
      <LoginForm/>
    </div>
  );
}

export default App;
