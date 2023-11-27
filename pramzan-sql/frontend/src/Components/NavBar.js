import "./NavBar.css"
import {Link} from "react-router-dom";
import {LuBookOpen, LuHome, LuLogIn} from "react-icons/lu";

const NavBar = () => {

    return (
        <div className="navbarContainer">
            <h1 className="logo">Parmesan Dialect</h1>
            <div className="navbarLinks">
                <Link to="/"><LuHome size={24} color="#257cff" /></Link>
                <Link to="/dictionary"><LuBookOpen size={24} color="#257cff" /></Link>
                <Link to="/login"><LuLogIn size={24} color="#257cff" ></LuLogIn></Link>
            </div>
        </div>
    )
}

export default NavBar