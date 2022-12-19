<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../template/administrateur/header.jsp" %>
<!-- start page title -->
<div id="dashboard-administrateur">
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <form class="d-flex">
            <div class="input-group">
              <label for="dash-daterange" class="d-none"></label>
              <input type="text" class="form-control form-control-light" id="dash-daterange"/>
              <span class="input-group-text bg-primary border-primary text-white">
                                                    <i class="mdi mdi-calendar-range font-13"></i>
                                                </span>
            </div>
            <a href="javascript: void(0);" class="btn btn-primary ms-2">
              <i class="mdi mdi-autorenew"></i>
            </a>
          </form>
        </div>
        <h4 class="page-title">Tableau de bord I-Jeery Online</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row">
    <div class="col-lg-3">
      <div class="card widget-flat">
        <div class="card-body">
          <div class="float-end">
            <i class="mdi mdi-account-multiple widget-icon"></i>
          </div>
          <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Soci&eacute;t&eacute; abonn&eacute;</h5>
          <h3 class="mt-3 mb-3">0</h3>
          <p class="mb-0 text-muted">
            <span class="text-success me-2"><i class="mdi mdi-arrow-up-bold"></i> 5.72%</span>
            <span class="text-nowrap">Par rapport &agrave; hier</span>
          </p>
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div> <!-- end col-->

    <div class="col-md-3 col-lg-3 col-sm-3">
      <div class="card widget-flat">
        <div class="card-body">
          <div class="float-end">
            <i class="mdi mdi-cart-plus widget-icon"></i>
          </div>
          <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Filial Enregistr&eacute;</h5>
          <h3 class="mt-3 mb-3">0</h3>
          <p class="mb-0 text-muted">
            <span class="text-danger me-2"><i class="mdi mdi-arrow-down-bold"></i> 1.08%</span>
            <span class="text-nowrap">Par rapport &agrave; hier</span>
          </p>
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div> <!-- end col-->

    <div class="col-lg-3">
      <div class="card widget-flat">
        <div class="card-body">
          <div class="float-end">
            <i class="mdi mdi-account-multiple widget-icon"></i>
          </div>
          <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Magasin</h5>
          <h3 class="mt-3 mb-3">0Ar</h3>
          <p class="mb-0 text-muted">
            <span class="text-success me-2"><i class="mdi mdi-arrow-up-bold"></i> 5.27%</span>
            <span class="text-nowrap">Par rapport &agrave; hier</span>
          </p>
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div> <!-- end col-->

  </div>
  <!-- end row -->

  <div class="row">
    <div class="col-12">
      <h4 class="text-dark d-none">G&eacute;rer les soci&eacute;</h4>
    </div>
    <div class="col-12">
      <button id="btn-nouveau-societe" class="btn btn-success btn-lg mb-3"><i class="uil uil-plus"></i> Nouveau Soci&eacute;t&eacute;
      </button>
    </div>
    <br><br>
  </div>

  <div class="row liste-societe">

    <c:forEach var="company" items="${companies}">

      <div id="${company.id}" class="col-3 item-societe">

        <div class="card d-block">
          <div class="card-body">

            <div class="dropdown card-widgets">
              <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="dripicons-dots-3"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-end">
                <!-- item-->
                <a class="dropdown-item"><i class="mdi mdi-cog me-1"></i>Gerer</a>
                <!-- item-->
                <a class="dropdown-item btn-editer-societe"><i class="mdi mdi-pencil me-1"></i>Modifie</a>
                <!-- item-->
                <a class="dropdown-item btn-desactiver-societe"><i class="mdi mdi-delete me-1"></i>Desactive</a>
              </div>
            </div>

            <!-- project title-->
            <h4 class="mt-0">
              <img src="http://localhost:8080/assets/images/logo.png" alt="" class="img-circle logo-entreprise img-fluid">
              <a href="" class="text-title label-nom">${company.nom}</a>
            </h4>

            <div class="badge bg-danger mb-3 label-statut">Suspendu (activation requis)</div>
            <p class="text-muted font-13 mb-3"><span class="label-adresse">${company.adresse}</span> - <span class="label-contact">${company.numTel}</span>
            <p class="text-muted font-13 mb-3"><span class="label-slogan-i">${company.slogan}</span>
            </p>
            <!-- project detail-->
            <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-format-list-bulleted-type text-muted"></i>
                                                <b>00</b> Filial
                                            </span>
              <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-comment-multiple-outline text-muted"></i>
                                                <b>00</b> Magasin
                                            </span>
            </p>

          </div>

          <ul class="list-group list-group-flush">

            <li class="list-group-item p-3">
              <!-- project progress-->
              <p class="mb-2 fw-bold">Recette <span class="float-end">100%</span></p>
              <div class="progress progress-sm">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                     style="width: 100%;">
                </div><!-- /.progress-bar -->
              </div><!-- /.progress -->
            </li>

          </ul>

        </div> <!-- end card-->
      </div>
    </c:forEach>

  <div id="company-creation" >

  </div>

  </div>

  <!-- end row -->
  <div class="all-modal">
    <%@ include file="../modal/administrateur/new-societe.jsp" %>
  </div>
</div>
<!-- container -->
</div>
<!-- content -->

</div>
<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<%@ include file="../template/setting.jsp" %>