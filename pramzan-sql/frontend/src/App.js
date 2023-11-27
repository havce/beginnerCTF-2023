import './App.css';
import { Routes, Route } from 'react-router-dom';
import "@fontsource/jetbrains-mono";

import Home from "./Pages/Home/Home"
import Login from "./Pages/Login/Login";
import Dictionary from "./Pages/Dictionary/Dictionary";
import Welcome from "./Pages/Welcome/Welcome";
import {useEffect} from "react";

function App() {

    useEffect(() => {
        document.title = 'Parmesan Dialect';
    }, []);

  return (
      <div className="App">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/dictionary" element={<Dictionary />} />
            <Route path="/login" element={<Login />} />
            <Route path="/welcome" element={<Welcome />} />
          </Routes>
      </div>
  );
}

export default App;
