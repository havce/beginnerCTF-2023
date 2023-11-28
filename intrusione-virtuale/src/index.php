<!DOCTYPE html>

<html lang="it">

<head>

    <meta charset="UTF-8">

    <title>Intrusione Virtuale</title>

    <link rel="stylesheet" href="stylesheet.css">

</head>

<body>

    <section>
        <section> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>        <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>

        <div class="signin">

            <div class="content">

                <h3>👾 Intrusione Virtuale</h3>

                <div class="form">
                
                	<div class="inputBox">
                    	<input type="text" name="username" value="Supreme Intruder"> <i>Username</i>
                    </div>

                    <form method="post" action="">
                        <div class="inputBox">
                            <input type="text" name="password"> <i>Password</i>
                        </div>

                        <?php
                        $FLAG = getenv('FLAG');
                        if (isset($_POST['password']) && is_string($_POST['password'])) {
                            $inp = $_POST['password'];
                            if ($inp == "") {
                                echo "<h3>Hello :)</h3>";
                                die();
                            }
                            if (!preg_match("/^[a-zA-Z0-9_\-'(),]+$/", $inp, $matches)) {
                                echo "<h3>Carattere non valido!</h3>";
                                die();
                            }
                            if (!(preg_match("/[0-9]/", $inp, $matches) && preg_match("/[a-zA-Z]/", $inp, $matches) && preg_match("/[_-]/", $inp, $matches))) {
                                echo "<h3>Password troppo debole!</h3>";
                                die();
                            }
                            if (preg_match("/[1]/", $inp, $matches)) {
                                echo "<h3>Mmmh hai già raggiunto questo punto, rendiamo la sfida più interessante</h3>";
                                die();
                            }
                            eval("\$input=$inp;");
                            if (strcmp("$input", "192014812") == 0) {
                                echo "<h3>Congratulazioni hai ottenuto la flag: $FLAG</h3>";
                                die();
                            }
                            if ($inp != "192014812") {
                                echo "<h3>Password errata</h3>";
                            }
                        }
                        ?>

                        <div class="links" style="padding-top: 10px; padding-bottom: 10px;"> <a>Password Dimenticata</a> <a>Accedi</a></div>

                        <div class="inputBox">
                            <input type="submit" value="Registrati">
                        </div>

                    </form>

                </div>

            </div>

        </div>

    </section>

</body>

</html>
