from flask import Flask, render_template, request, redirect, make_response
from flask_sqlalchemy import SQLAlchemy
import random
import string
import secrets
import os
import requests

db = SQLAlchemy()
app = Flask(__name__)
app.config["SQLALCHEMY_DATABASE_URI"] = "sqlite:///sqlite.db"
db.init_app(app)
FLAG = os.getenv("FLAG") 
app.secret_key = os.getenv("SECRET_KEY", secrets.token_bytes(32))


class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String, unique=True, nullable=False)
    password = db.Column(db.Text, nullable=False)
    quote = db.Column(db.Text, nullable=False)

    @property
    def to_json(self):
        return {
            "id": self.id,
            "username": self.username,
            "quote": self.quote
        }


def init_db():
    with app.open_resource('schema.sql', mode='r') as f:
        db.cursor().executescript(f.read())
    db.commit()

@app.before_first_request
def init_app():
    db.create_all()
    if not User.query.filter_by(username="admin").first():
        password = "".join(random.choices(string.ascii_letters + string.digits, k=12))
        print('Admin '+ password,flush=True)
        admin = User(username="admin", password=password, quote=FLAG)
        db.session.add(admin)
        db.session.commit()

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/signup", methods=["GET", "POST"])
def signup():
    errors=''
    if "username" in request.cookies:
        return redirect("/profile")
    if request.method == "POST":
        try: 
            username = request.form["username"]
            password = request.form["password"]
            quote = requests.get('https://api.quotable.io/random').json()
            quote = '"' + quote['content'] + '" - ' + quote['author']
            user = User(username=username, password=password, quote=quote)
            db.session.add(user)
            db.session.commit()
            resp = make_response(redirect("/profile"))
            resp.set_cookie('username',username)
            return resp
        except Exception as e:
            errors = "User already exists"
            print('Error',e,flush=True)
    return render_template("signup.html", error=errors)

@app.route("/profile")
def details():
    if "username" not in request.cookies:
        return redirect("/signup")
    details = User.query.filter_by(username=request.cookies.get("username")).all()
    print(details[0],flush=True)
    return render_template("profile.html", data=details[0])


@app.route("/login", methods=["GET", "POST"])
def login():
    errors=''
    if request.method == "POST":
        username = request.form["username"]
        password = request.form["password"]
        user = User.query.filter_by(username=username, password=password).first()
        if user:
            resp = make_response(redirect("/profile"))
            resp.set_cookie('username',username)
            return resp
        else:
            errors = "Login failed"
    return render_template("login.html", action="login", error=errors)

@app.route("/logout")
def logout():
    resp = make_response(redirect("/"))
    if "username" in request.cookies:
        resp.set_cookie('username','',expires=0)
    return resp


@app.route("/users", methods=["GET"])
def users():
    users = User.query.all()
    '''if request.args['page']:
        page = int(request.args.get('page'))-1
    else:
        page = 0
    users = users.limit(20)
    users = users.offset(page*20)'''
    print([u.username for u in users],flush=True)
    return render_template("users.html", users=users)