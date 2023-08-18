import { useSelector } from "react-redux";
import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import Auth from "./pages/Auth/Auth";
import Profile from "./pages/Profile/Profile";
import Home from "./pages/home/Home";
function App() {
    const user = useSelector((state) => state.authReducer.authData)
    return (
        <div className="App" >
            <Routes>
                <Route path="/" element={user ? <Navigate to="home" /> : <Navigate to="auth" />} />
                <Route path="/home" element={user ? <Home /> : <Navigate to="../auth" />} />
                <Route path="/profile" element={user ? <Profile /> : <Navigate to="../auth" />} />
                <Route path="/auth" element={user ? <Navigate to="../home" /> : <Auth />} />
            </Routes>
        </div>
    );
}
export default App;
