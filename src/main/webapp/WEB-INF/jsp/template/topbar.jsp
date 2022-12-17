<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="content" id="topbar">
  <!-- Topbar Start -->
  <div class="navbar-custom">
    <ul class="list-unstyled topbar-menu float-end mb-0">
      <li class="dropdown notification-list">
        <a class="nav-link dropdown-toggle arrow-none" data-bs-toggle="dropdown" href="#" role="button"
           aria-haspopup="false" aria-expanded="false">
          <i class="dripicons-bell noti-icon"></i>
          <span class="noti-icon-badge"></span>
        </a>
        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated dropdown-lg">
          <!-- item-->
          <div class="dropdown-item noti-title">
            <h5 class="m-0">
                <span class="float-end">
                    <a href="" class="text-dark">
                        <small>Effacer tout</small>
                    </a>
                </span>Notifications
            </h5>
          </div>
          <div style="max-height: 230px;" data-simplebar="">
            <!-- item-->
            <a href="" class="dropdown-item notify-item">
              <div class="notify-icon bg-primary">
                <i class="mdi mdi-comment-account-outline"></i>
              </div>
              <p class="notify-details">Un test de notification
                <small class="text-muted">environ 1mn</small>
              </p>
            </a>
          </div>
          <!-- All-->
          <a href="" class="dropdown-item text-center text-primary notify-item notify-all">
            Voir tout
          </a>

        </div>
      </li>


      <li class="notification-list">
        <a class="nav-link end-bar-toggle" href="javascript: void(0);">
          <i class="dripicons-gear noti-icon"></i>
        </a>
      </li>

      <li class="dropdown notification-list">
        <a class="nav-link dropdown-toggle nav-user arrow-none me-0" data-bs-toggle="dropdown" href="#" role="button"
           aria-haspopup="false" aria-expanded="false">
                        <span class="account-user-avatar">
                                        <img src="${pageContext.request.contextPath}/assets/images/users/avatar-1.jpg"
                                             alt="user-image" class="rounded-circle">
                        </span>
                        <span>
                            <span class="account-user-name">${connectedUser.nom}</span>
                            <span class="account-position">${connectedUser.fonction.nomFonction}</span>
                            <span id="fonction-id"  class=" d-none account-position">${connectedUser.fonction.id}</span>
                        </span>
        </a>
        <div class="dropdown-menu dropdown-menu-end dropdown-menu-animated topbar-dropdown-menu profile-dropdown">
          <!-- item-->
          <div class=" dropdown-header noti-title">
            <h6 class="text-overflow m-0">Option utilisateur</h6>
          </div>

          <!-- item-->
          <a href = "<c:url value = "admin-0.0.1-SNAPSHOT/gerer-utilisateur"/>" class="dropdown-item notify-item">
            <i class="mdi mdi-account-circle me-1"></i>
            <span>Gérer mon compte</span>
          </a>

          <!-- item-->
          <a href = "<c:url value = "admin-0.0.1-SNAPSHOT/gerer-utilisateur"/>" class="dropdown-item notify-item">
            <i class="mdi mdi-account-edit me-1"></i>
            <span>Historiques de session</span>
          </a>

          <!-- item-->
          <a href = "<c:url value = "admin-0.0.1-SNAPSHOT/user-login"/>" class="dropdown-item notify-item">
            <i class="mdi mdi-logout me-1"></i>
            <span>Deconnexion</span>
          </a>
        </div>
      </li>

    </ul>
    <button class="button-menu-mobile open-left">
      <i class="mdi mdi-menu"></i>
    </button>
    <div class="app-search dropdown d-none d-lg-block">

    </div>
  </div>
  </div>