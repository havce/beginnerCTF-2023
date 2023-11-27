import "./Login.css"

import NavBar from "../../Components/NavBar";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const submit = async (e) => {
        e.preventDefault();

        const response = await fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                password
            }),
        });

        const data = await response.json();

        if (response.status !== 200) {
            alert(data.message)
            return
        }

        navigate("/welcome?name=" + data.name + "&surname=" + data.surname)
    }

    return (
        <div className="mainContainer">
            <NavBar />
            <div className="formContainer">
                <form className="loginForm">
                    <label className="formLabel">Username</label>
                    <input className="formInput" value={username} onChange={e => setUsername(e.target.value)} type="text" />
                    <label className="formLabel">Password</label>
                    <input className="formInput" value={password} onChange={e => setPassword(e.target.value)} type="password" />
                    <button className="formButton" onClick={submit}>Login</button>
                </form>
            </div>
        </div>
    )
}

export default Login