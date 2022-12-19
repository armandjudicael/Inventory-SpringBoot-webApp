<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <title>Horizontal Layout | Hyper - Responsive Bootstrap 5 Admin Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description">
  <meta content="Coderthemes" name="author">
  <!-- App favicon -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico">
  <!-- third party css -->
  <link href="${pageContext.request.contextPath}/assets/css/vendor/jquery-jvectormap-1.2.2.css" rel="stylesheet"
        type="text/css">
  <!-- App css -->
  <link href="${pageContext.request.contextPath}/assets/css/icons.min.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/assets/css/app.min.css" rel="stylesheet" type="text/css" id="light-style">
  <link href="${pageContext.request.contextPath}/assets/css/app-dark.min.css" rel="stylesheet" type="text/css" id="dark-style">
  <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" type="text/css" id="light-style">

</head>

<body class="loading" data-layout="topnav" data-layout-config='{"layoutBoxed":false,"darkMode":true,"showRightSidebarOnStart": true}'>
<!-- Begin page -->
<div class="wrapper">
  <!-- ============================================================== -->
  <!-- Start Page Content here -->
  <!-- ============================================================== -->
<div class="content-page">
    <div class="content">
      <!-- Topbar Start -->
      <div class="navbar-custom topnav-navbar">
        <div class="container-fluid">

          <!-- LOGO -->
          <a href="" class="topnav-logo">
            <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="" height="16">
            I-jeery OnLine v1.0
          </a>

          <ul class="list-unstyled topbar-menu float-end mb-0">
            <li class="dropdown notification-list d-xl-none">
              <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#"
                 role="button" aria-haspopup="false" aria-expanded="false">
                <i class="dripicons-search noti-icon"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-animated dropdown-lg p-0">
                <form class="p-3">
                  <input type="text" class="form-control" placeholder="Search ..."
                         aria-label="Recipient's username">
                </form>
              </div>
            </li>
            <li class="dropdown notification-list">

              <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#"
                 id="topbar-notifydrop" role="button" aria-haspopup="true" aria-expanded="false">
                <i class="dripicons-bell noti-icon"></i>
                <span class="noti-icon-badge"></span>
              </a>
              <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated dropdown-lg"
                   aria-labelledby="topbar-notifydrop">

                <!-- item-->
                <div class="dropdown-item noti-title">

                  <h5 class="m-0">
                                                <span class="float-end">
                                                    <a href="javascript: void(0);" class="text-dark">
                                                        <small>Effacer tout</small>
                                                    </a>
                                                </span>Notification
                  </h5>

                </div>

                <div style="max-height: 230px;" data-simplebar="">
                  <!-- item-->
                  <a href="javascript:void(0);" class="dropdown-item notify-item">
                    <div class="notify-icon bg-primary">
                      <i class="mdi mdi-comment-account-outline"></i>
                    </div>
                    <p class="notify-details">Notification d'administration
                      <small class="text-muted">il y a 1min</small>
                    </p>
                  </a>


                  <!-- All-->
                  <a href="javascript:void(0);"
                     class="dropdown-item text-center text-primary notify-item notify-all">
                    Voir tout
                  </a>

                </div>
              </div>
            </li>
            <li class="notification-list">
              <a class="nav-link end-bar-toggle" href="javascript: void(0);">
                <i class="dripicons-gear noti-icon"></i>
              </a>
            </li>
            <li class="notification-list">
              <a class="nav-link dropdown-toggle nav-user arrow-none me-0"
                 href="/imwa-admin-log-out" role="button" aria-expanded="false">
                <i class="mdi mdi-logout me-1"></i>
                <span>Deconnexion</span>
              </a>

            </li>
          </ul>

          <a class="navbar-toggle" data-bs-toggle="collapse" data-bs-target="#topnav-menu-content">
            <div class="lines">
              <span></span>
              <span></span>
              <span></span>
            </div>
          </a>

          <div class="app-search">

            <form>
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search..." id="top-search">
                <span class="mdi mdi-magnify search-icon"></span>
                <button class="input-group-text  btn-primary" type="submit">Search</button>
              </div>
            </form>

          </div>

        </div>
      </div>
      </div>
      <!-- end Topbar -->

      <div class="bg-light text-dark">
        <div class="container-fluid">
          <nav class="navbar navbar-dark navbar-expand-lg topnav-menu">

            <div class="collapse navbar-collapse" id="topnav-menu-content">
              <ul class="navbar-nav">
                <li class="nav-item dropdown">
                  <a href="/admin/dashboard" class="dropdown-item"><i class="uil-dashboard me-1"></i>Tableau de bord</a>
                </li>
                <li class="nav-item dropdown">
                  <a href="/admin/gerer-societe" class="dropdown-item"><i class="uil-cog me-1"></i>Gérer société</a>
                </li>
                <li class="nav-item dropdown">
                  <a href="/admin/apropos" class="dropdown-item"><i class="uil-info-circle me-1"></i>A propos</a>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
      </div>

    </div>

</div>

</body>

