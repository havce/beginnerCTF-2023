import NavBar from "../../Components/NavBar";
import "./Home.css";
import battistero from "../../Images/battistero.jpg";

const Home = () => {
    return (
        <div className="mainContainer">
            <NavBar />
            <div className="contentContainer">
                <div className="textContainer">
                    <h1>Home</h1>
                    <p>The Parmigiano dialect, sometimes anglicized as the Parmesan dialect, (al djalètt pramzàn) is a variety of the Emilian language spoken in the Province of Parma, the western-central portion of the Emilia-Romagna administrative region.</p>
                    <h2>Terminology</h2>
                    <p>The term dialetto, usually translated as dialect in English, is commonly used in reference to all local Romance languages native to Italy, many of which are not mutually intelligible with Standard Italian and all of which have developed from Vulgar Latin independently. Parmigiano is no exception and is a variety of Emilian, not of Italian.</p>
                    <h2>History</h2>
                    <p>Parmigiano has much of the history as Emilian, but at some point, it diverged from other versions of that linguistic group. It now lies somewhere between Western Emilian, which includes Piacentino, and Central Emilian, which includes Reggiano and Modenese. Like the other Emilian dialects, it has fewer speakers than ever because of political, social and economic factors, but La Repubblica has suggested that it is changing.[2] It is still declining but more slowly, as parents are keen to preserve their ancestral roots.[3] Its origins are with Gauls, who occupied the Parma area in around 400 BC, who had stayed there after the invasion of the Romans. The lexicon was therefore a type of Latin influenced by Gaulish. The Gauls, or Celts, left their mark on modern Parmigiano in some words today, such as gozèn "pig", scrana "chair" and sôga "rope". As a result of Spanish and especially French invasions, Parmigiani began to use words which came from a French language that had Latin roots. That is seen in tirabusòn "corkscrew" (similar to Modern French's tire-bouchon) vert "open" (French: ouvert), pòmm da téra "potato" (French: pomme de terre) and many other words.</p>
                </div>
                <div className="imagesContainer">
                   <img src={battistero} alt="Battistero di (Parma) Havce"/>
                </div>

            </div>
        </div>
    );
}

export default Home;