const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database(':memory:');

db.serialize(() => {
    db.run("CREATE TABLE users (" +
        "id INT auto_increment, " +
        "username TEXT, " +
        "password TEXT," +
        "name TEXT," +
        "surname TEXT, " +
        "PRIMARY KEY (id))");

    const stmt = db.prepare("INSERT INTO users VALUES (?, ?, ?, ?, ?)");
    stmt.run(1, "admin", "gabibbo", "havceCTF{E_Ad3sa_Un_Bel", "_T0nd_di_Anu3ln}");
    stmt.run(2, "user", "supersecurepassword", "Mario", "Rossi");

    stmt.finalize();
});


module.exports = db;