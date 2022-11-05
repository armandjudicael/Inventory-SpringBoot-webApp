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

    <div class="col-lg-3">
      <div class="card widget-flat">
        <div class="card-body">
          <div class="float-end">
            <i class="mdi mdi-cart-plus widget-icon"></i>
          </div>
          <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Chiffres d'Affaires</h5>
          <h3 class="mt-3 mb-3">0Ar</h3>
          <p class="mb-0 text-muted">
            <span class="text-danger me-2"><i class="mdi mdi-arrow-down-bold"></i> 1.08%</span>
            <span class="text-nowrap">Par rapport &agrave; hier</span>ss
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