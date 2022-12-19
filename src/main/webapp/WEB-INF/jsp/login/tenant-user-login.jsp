<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description"/>
    <meta content="Coderthemes" name="author"/>
    <!-- App favicon -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">

    <!-- App css -->
    <link href="${pageContext.request.contextPath}/assets/css/icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/app.min.css" rel="stylesheet" type="text/css"
          id="light-style"/>
    <link href="${pageContext.request.contextPath}/assets/css/app-dark.min.css" rel="stylesheet" type="text/css"
          id="dark-style"/>

</head>

<body class="loading authentication-bg"
      data-layout-config='{"leftSideBarTheme":"dark","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false,"darkMode":false, "showRightSidebarOnStart": true}'>
<div class="account-pages pt-2 pt-sm-5 pb-4 pb-sm-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-xxl-4 col-lg-5">
                <div class="card">

                    <!-- Logo -->
                    <div class="card-header pt-4 pb-4 text-center bg-primary text-light">
                        <a class="text-dark fw-bold" href="#">
                            <span><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" height="18">&nbsp; I-jeery OnLine | Utilisateur filial</span>
                        </a>
                    </div>

                    <div class="card-body p-4">

                        <div class="text-center w-75 m-auto">
                            <h4 class="text-dark-50 text-uppercase text-center pb-0 fw-bold">Se Connecter</h4><br>
                            <p class="text-muted mb-4 d-none">Veuillez entrer votre nom d'utilisateur et mot de passe pour accÃ©der Ã 
                                I-jeery</p>
                        </div>

                        <div>
                            ${DISABLED_USER}
                        </div>
                        <form action="imwa-user-signup" method="post">

                            <div class="mb-3">
                                <label for="username" class="form-label">Nom d'utilisateur</label>
                                <input name="username" class="form-control" type="text" required id="username" required=""
                                       placeholder="Entrer votre nom d'utilisateur">
                            </div>

                            <div class="mb-3">
                                <a href="#" class="text-muted float-end"><small>Mot de passe oublié?</small></a>
                                <label for="password" class="form-label">Mot de passe</label>
                                <div class="input-group input-group-merge">
                                    <input type="password" name="password" id="password" class="form-control"
                                           placeholder="Entrer votre mot de passe">
                                    <div class="input-group-text" data-password="false">
                                        <span class="password-eye"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">clé</label>
                                <div class="input-group input-group-merge">
                                    <input type="password" name="key" id="filiale" class="form-control"
                                           placeholder="Entrer votre clé">
                                    <div class="input-group-text" data-password="false">
                                        <span class="password-eye"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3 mb-3">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="checkbox-signin" checked>
                                    <label class="form-check-label" for="checkbox-signin">Souviens-moi</label>
                                </div>
                            </div>

                            <div class="mb-3 mb-0 text-center">
                                <button class="btn btn-primary" type="submit"> Se connecter</button>
                            </div>

                        </form>
                    </div> <!-- end card-body -->
                </div>
                <!-- end card -->
                <!-- end row -->

            </div> <!-- end col -->
        </div>
        <!-- end row -->
    </div>
    <!-- end container -->
</div>
<!-- end page -->

<footer class="footer footer-alt">
    2022 © I-jeery
</footer>
<!-- bundle -->
<script src="${pageContext.request.contextPath}/assets/js/vendor.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.min.js"></script>

</body>
</html>