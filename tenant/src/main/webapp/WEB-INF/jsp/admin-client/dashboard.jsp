<%@ include file="../template/admin-client/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- start page title -->
<div id="dashboard-admin-client">
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
            <a  id="refresh-btn" class="btn btn-primary ms-2">
              <i class="mdi mdi-autorenew"></i>
              Actualiser
            </a>
          </form>
        </div>
        <h4 class="page-title">Tableau de bord societe</h4>
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
          <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Caisse update</h5>
          <h3 class="mt-3 mb-3">0Ar</h3>
          <p class="mb-0 text-muted">
            <span class="text-success me-2"><i class="mdi mdi-arrow-up-bold"></i> 5.72%</span>
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
          <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Credit</h5>
          <h3 class="mt-3 mb-3">0Ar</h3>
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
          <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Encaissement</h5>
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
          <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Decaissement</h5>
          <h3 class="mt-3 mb-3">0Ar</h3>
          <p class="mb-0 text-muted">
            <span class="text-danger me-2"><i class="mdi mdi-arrow-down-bold"></i> 1.08%</span>
            <span class="text-nowrap">Par rapport &agrave; hier</span>
          </p>
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div> <!-- end col-->

  </div>
  <!-- end row -->

  <div class="row">
    <div class="col-12">
      <h4 class="text-dark d-none">G&eacute;rer les filiales</h4>
    </div>
    <div class="col-12">
      <button id="btn-nouveau-filial" class="btn btn-success btn-lg mb-3"><i class="uil uil-plus"></i> Nouveau filiale
      </button>
    </div>
    <br><br>
  </div>

  <div class="row liste-filial">
    <c:forEach var="sb" items="${subsdiaries}">
      <div id='${sb.id}' class="col-md-3 col-lg-3 col-sm-3 item-filial">
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
                <a class="dropdown-item btn-editer-filial"><i class="mdi mdi-pencil me-1"></i>Modifie</a>
                <!-- item-->
                <a class="dropdown-item btn-activer-filial"><i class="mdi mdi-key me-1"></i>Activer</a>
                <!-- item-->
                <a class="dropdown-item btn-desactiver-filial"><i class="mdi mdi-delete me-1"></i>Desactive</a>
              </div>
            </div>
            <!-- project title-->
            <h4 class="mt-0">
              <a href="" class="text-title label-nom"><c:out value="${sb.nom}"/></a>
            </h4>
            <div class="badge bg-danger mb-3 label-statut">Suspendu (activation requis)</div>
            <p class="text-muted font-13 mb-3"><span class="label-adresse"><c:out value="${sb.adresse}"/></span> - <span
                    class="label-contact"><c:out value="${sb.numTel}"/></span>
            </p>
            <!-- project detail-->
            <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-format-list-bulleted-type text-muted"></i>
                                                <b><c:out value="${sb.users.size()}"/></b> Utilisateur
                                            </span>
              <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-comment-multiple-outline text-muted"></i>
                                                <b><c:out value="${sb.magasins.size()}"/></b> Magasin
                                            </span>
            </p>
            <div id="tooltip-container">
              <span>Chiffre d'affaire : </span>
              <a class="d-inline-block text-muted fw-bold ms-2">
                <c:out value="${sb.chiffreAffaire}"/> Ar
              </a>
            </div>
          </div> <!-- end card-body-->
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
  </div>
  <!-- end row -->
  <div class="all-modal">
    <%@ include file="../modal/admin-client/activation-filial.jsp" %>
    <%@ include file="../modal/admin-client/new-filial.jsp" %>
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