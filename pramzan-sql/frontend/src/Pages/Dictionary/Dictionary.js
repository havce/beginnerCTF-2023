import NavBar from "../../Components/NavBar";
import "./Dictionary.css";

const Dictionary = () => {

    const translations = [
        {
            italian: "Adagio",
            parmaesan: "Adäzi",
            english: "Slow",
        },
        {
            italian: "Attento!",
            parmaesan: "Aténti!",
            english: "Careful!",
        },
        {
            italian: "Borgo",
            parmaesan: "Bórogh",
            english: "Village",
        },
        {
            italian: "Bugia",
            parmaesan: "Bala",
            english: "Lie"
        },
        {
            italian: "Caldo (umido)",
            parmaesan: "Caldùss",
            english: "Hot"
        },
        {
            italian: "Cane (grosso)",
            parmaesan: "Cagnón",
            english: "Big dog"
        },
        {
            italian: "Cognome",
            parmaesan: "Cognomm",
            english: "Surname"
        },
        {
            italian: "Da",
            parmaesan: "Da",
            english: "From"
        },
        {
            italian: "Dove",
            parmaesan: "In dua",
            english: "Where"
        },
        {
            italian: "Cappelletto",
            parmaesan: "Caplètt",
            english: "Cappelletto"
        },
        {
            italian: "Imbuto",
            parmaesan: "Lorètt",
            english: "Funnel"
        },
        {
            italian: "Nome",
            parmaesan: "Noom",
            english: "Name"
        },
        {
            english: "Select",
            parmaesan: "Chapa",
            italian: "Seleziona"
        },
        {
            english: "Union",
            parmaesan: "Union",
            italian: "Unione"
        }]

    return (
        <div className="mainContainer">
            <NavBar />
            <div className="contentContainer" style={{ marginBottom: "500px" }}>
                <div className="textContainer">
                    <h1>Dictionary</h1>
                    <p>Here are some examples of translations</p>

                    <table>
                        <thead>
                            <tr>
                                <th>Italian</th>
                                <th>Parmaesan Dialect</th>
                                <th>English</th>
                            </tr>
                        </thead>
                        <tbody>
                        {translations.map((translation, index) => {
                            return (
                                <tr key={index}>
                                    <td>{translation.italian}</td>
                                    <td>{translation.parmaesan}</td>
                                    <td>{translation.english}</td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                    <p>A thank you to our admin but, being very shy, he won't reveal his <b>name and surname</b></p>
                </div>
            </div>
        </div>
    )
}

export default Dictionary