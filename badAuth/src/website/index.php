<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorization Check</title>
</head>
<body>
    <h1>TODO: Implement a Client Side</h1>
</body>
<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST["authCode"])) {
        $authorizationCode = $_POST["authCode"];

        if ($authorizationCode == "true") {
            $response = array("Autenticato" => true, "Flag" => "flag{sn1fFiNg_4_b1n4rY_1s_tH3_La5t_r3s0rT!}");
        } else {
            $response = array("Autenticato" => false, "Messaggio" => "Chiave non valida");
        }

        header('Content-Type: application/json');
        echo json_encode($response, JSON_PRETTY_PRINT);
        exit();
    } else {
        header("HTTP/1.1 400 Bad Request");
        echo json_encode(array("error" => "Il parametro 'authCode' è obbligatorio."));
        exit();
    }
} else {
    header("HTTP/1.1 405 Method Not Allowed");
    echo json_encode(array("error" => "TODO: Implement a Client Side. Solo richieste di tipo POST sono consentite."));
    exit();
}

?>
</html>
