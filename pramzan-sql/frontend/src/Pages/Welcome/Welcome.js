import NavBar from "../../Components/NavBar";
import {useSearchParams} from "react-router-dom";

const Welcome = () => {

    const [searchParams] = useSearchParams();
    const name = searchParams.get("name");
    const surname = searchParams.get("surname");

    return (
        <div className="mainContainer">
            <NavBar />
            <div className="contentContainer" style={{ marginBottom: "500px"}}>
                <h1>Welcome {name}{surname}</h1>
            </div>
        </div>
    )
}

export default Welcome