<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="head.jsp" %>

<body class="loading" data-layout-config='{"leftSideBarTheme":"dark","layoutBoxed":false, "leftSidebarCondensed":false, "leftSidebarScrollable":false,"darkMode":true, "showRightSidebarOnStart": true}'>

<!-- Begin page -->

<div class="wrapper">
  <!-- ========== Left Sidebar Start ========== -->
  <div class="leftside-menu">
    <!-- LOGO -->
    <a href="/dashboard" class="logo text-center logo-light">
        <span class="logo-lg">
            <img src="../../../assets/images/logo.png" alt="" height="16">&nbsp; I-jeery OnLine v1.0
        </span>
        <span class="logo-sm">
            <img src="../../../assets/images/logo.png" alt="" height="16">
        </span>
    </a>

    <!-- LOGO -->
    <a href="../dashboard.jsp" class="logo text-center logo-dark">
        <span class="logo-lg">
            <img src="../../../assets/images/logo-dark.png" alt="" height="16">
        </span>
        <span class="logo-sm">
            <img src="../../../assets/images/logo_sm_dark.png" alt="" height="16">
        </span>
    </a>

    <div class="h-100" id="leftside-menu-container" data-simplebar="">

      <!--- Sidemenu -->
      <ul class="side-nav" id="navigation-ul">


        <li class="side-nav-title side-nav-item">Menu de Navigation</li>
        <li class="side-nav-item menu-dashboard">
          <a href="/dashboard" class="side-nav-link">
            <i class="uil-dashboard"></i>
            <span> Tableau de bord </span>
          </a>
        </li>
        <li class="side-nav-item menu-article">
          <a href="/articles" class="side-nav-link">
            <i class="uil-box"></i>
            <span> Article </span>
          </a>
        </li>
        <li class="side-nav-item menu-vente">
          <a href="/ventes" class="side-nav-link"
             target="_blank">
            <i class="uil-shopping-cart-alt"></i>
            <span> Vente </span>
          </a>
        </li>
        <li class="side-nav-item menu-detail-vente">
          <a href="/detail-ventes" class="side-nav-link">
            <i class="uil-calender"></i>
            <span> Detail de vente </span>
          </a>
        </li>
        <li class="side-nav-item menu-magasin">
          <a href="/magasin" class="side-nav-link">
            <i class="uil-building"></i>
            <span> Magasin </span>
          </a>
        </li>
        <li class="side-nav-item menu-stock">
          <a href="/stock" class="side-nav-link">
            <i class="uil-archive"></i>
            <span> Stock </span>
          </a>
        </li>
        <li class="side-nav-item menu-facture">
          <a href="/facture" class="side-nav-link">
            <i class="uil-invoice"></i>
            <span> Facture </span>
          </a>
        </li>
        <li class="side-nav-item menu-prix">
          <a href="/prix" class="side-nav-link">
            <i class="uil-usd-circle"></i>
            <span> Prix </span>
          </a>
        </li>
        <li class="side-nav-item menu-embarquement">
          <a href="/embarquement" class="side-nav-link">
            <i class="uil-notes"></i>
            <span> Embarquement </span>
          </a>
        </li>
        <li class="side-nav-item menu-peremption">
          <a href="/peremption" class="side-nav-link">
            <i class="uil-calendar-alt"></i>
            <span> Peremption </span>
          </a>
        </li>
        <li class="side-nav-item menu-client">
          <a href="/client" class="side-nav-link">
            <i class="uil-user-square"></i>
            <span> Client </span>
          </a>
        </li>
        <li class="side-nav-item menu-fournisseur">
          <a href="/fournisseur" class="side-nav-link">
            <i class="uil-store-alt"></i>
            <span> Fournisseur </span>
          </a>
        </li>
        <li class="side-nav-item menu-operation">
          <a data-bs-toggle="collapse" href="#sidebarEcommerce" aria-expanded="false"
             aria-controls="sidebarEcommerce" class="side-nav-link">
            <i class="uil-th"></i>
            <span> Operation </span>
            <span class="menu-arrow"></span>
          </a>
          <div class="collapse" id="sidebarEcommerce">
            <ul class="side-nav-second-level nav-operation">
              <li class="menu-operation-liste">
                <a href="operation-liste">Liste</a>
              </li>
              <li class="menu-operation-entree">
                <a href="operation-entree" target="_blank">Entr&eacute;e</a>
              </li>
              <li class="menu-operation-sortie">
                <a href="operation-sortie" target="_blank">Sortie</a>
              </li>
              <li class="menu-operation-transfert">
                <a href="operation-transfert" target="_blank">Transfert</a>
              </li>
            </ul>
          </div>
        <li class="side-nav-item menu-caisse">
          <a href="/caisse" class="side-nav-link" target="_blank">
            <i class="uil-money-stack"></i>
            <span> Caisse </span>
          </a>
        </li>
        <li class="side-nav-item menu-utilisateur">
          <a href="/utilisateur" class="side-nav-link">
            <i class="uil-users-alt"></i>
            <span> Utilisateur </span>
          </a>
        </li>
        <li class="side-nav-item menu-choix-magasin">
          <a class="side-nav-link" role="button" data-bs-toggle="modal" data-bs-target="#choix-magasin">
            <i class="uil-building"></i>
            <span> Choix de magasin </span>
          </a>
        </li>

        <li class="side-nav-item menu-autorisation">
          <a href="/autorisation" class="side-nav-link">
            <i class="uil-lock-access"></i>
            <span> Autorisation </span>
          </a>
        </li>

        <li class="side-nav-item menu-materiel-transport">
          <a href="/transports" class="side-nav-link">
            <i class="uil-car"></i>
            <span> Materiel Transport </span>
          </a>
        </li>

      </ul>

      <!-- End Sidebar -->

      <div class="clearfix"></div>

    </div>
    <!-- Sidebar -left -->

  </div>
  <!-- Left Sidebar End -->

  <!-- ============================================================== -->
  <!-- Start Page Content here -->
  <!-- ============================================================== -->

  <div class="content-page">


    <%@ include file="topbar.jsp" %>


    <!-- inclusion des modals -->

    <%@ include file="../modal/menu-choix-magasin.jsp" %>
    <%@ include file="../modal/menu-choix-de-bdd.jsp" %>
    <%@ include file="../modal/menu-sauvegarde.jsp" %>

    <!-- fin d'inclusion des modals -->

    <!-- Start Content-->


    <!-- Modal list  -->

    <!-- End Modal list -->

    <div class="container-fluid">

      <!-- start page title -->
      <div class="row">
        <div class="col-12">
        </div>
      </div>
      <!-- end page title -->
