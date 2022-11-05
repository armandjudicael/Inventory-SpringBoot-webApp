<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="signup" method="post">
    <input name="username" class="form-control" type="text" required id="emailaddress"
           placeholder="Entrer votre nom d'utilisateur">
    <input type="password" name="password" id="password" class="form-control" placeholder="Entrer votre mot de passe">
    <input type="text" name="filialekey" id="filiale" class="form-control"
           placeholder="Entrer votre clé">
    <button class="btn btn-primary" type="submit"> Se connecter</button>
</form>
</body>
</html>