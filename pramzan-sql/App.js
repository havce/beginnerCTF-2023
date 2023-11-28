const express = require('express');
const app = express();
const http = require('http')

const db = require('./objects/sqlite3');

const {pramzanDecoder} = require('./objects/pramzanSql');
const {join} = require("path");

app.use('/', express.static(join(__dirname, 'frontend/build')));

app.use(express.json());

app.post('/login', (req, res) => {

    try {
        const {username, password} = req.body;

       const query =
            "SELECT " +
               "name, surname " +
            "FROM " +
                "users " +
            "WHERE " +
                "username = '" + pramzanDecoder(username) + "' " +
                "AND password = '" + pramzanDecoder(password)  + "'"


        console.log(query)

        db.get(query, (err, row) => {
            if (err) {
                res.status(400).json({message: err.message})
                return
            }

            if (row) {
                console.log(row)
                res.status(200).json({message: "Login successful", name: row.name, surname: row.surname})
                return
            }

            res.status(400).json({message: "Wrong username or password"})

        });

    } catch (err) {
        res.status(400).json({message: err.message})
        console.log(err)
    }

})

const httpServer = http.createServer(app);
httpServer.listen(80, () => {
    console.log('HTTP Server running on port 80');
});
