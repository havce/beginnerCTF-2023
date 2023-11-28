const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database(':memory:');
const crypto = require("node:crypto");

db.serialize(() => {
    db.run("CREATE TABLE users (" +
        "id INT auto_increment, " +
        "username TEXT, " +
        "password TEXT," +
        "name TEXT," +
        "surname TEXT, " +
        "PRIMARY KEY (id))");

    const stmt = db.prepare("INSERT INTO users VALUES (?, ?, ?, ?, ?)");
    stmt.run(1, "admin", crypto.randomBytes(20).toString('hex'), process.env.FLAG.substring(0, 14), process.env.FLAG.substring(14));
    stmt.run(2, "user", "supersecurepassword", "Mario", "Rossi");

    stmt.finalize();
});


module.exports = db;
