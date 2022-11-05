<%@ include file='template/head.jsp' %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Start Content-->
<div class="container-fluid bg-light text-dark" id="menu-caisse">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..."
                   id="input-search-operation">
            <button id="btn-search-operation" class="input-group-text btn-primary" type="submit"><i
                    class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Caisse</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <input type="hidden" id="user-id" value-id="${connectedUser.id}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
      </div>
      <div class="d-lg-inline-flex d-md-inline-flex d-sm-block">
        <input type="date" id="input-date-debut" class="form-control m-1 wm-100">
        <input type="date" id="input-date-fin" class="form-control m-1 wm-100">
        <a type="button" id="btn-search-filter" class="btn btn-success m-1 btn-40 wm-100 btn-mobile">
          <i class=""></i>Valider
        </a>
        <select required name="select-magasin" id="select-magasin" class="form-select m-1 wm-100">
          <option value="TOUTE" > Toutes </option>
          <c:forEach var="magasin" items="${magasins}">
            <option value="${magasin.id}"><c:out value="${magasin.nomMagasin}"/></option>
          </c:forEach>
        </select>
        <select required name="select-magasin" id="select-user" class="form-select wm-100 m-1">
          <c:forEach var="user" items="${users}">
            <option value="${user.id}"><c:out value="${user.nom}"/></option>
          </c:forEach>
        </select>
        </select>
        <a type="button" id="btn-creer-encaissement" class="btn btn-outline-success btn-40 m-1 wm-100 crud-caisse" data-bs-toggle="modal"
           data-bs-target="#operation-caisse">
          Encaissement
        </a>
        </select>
        <a type="button" id="btn-creer-decaissement" class="btn btn-outline-danger btn-40 m-1 wm-100 crud-caisse" data-bs-toggle="modal"
           data-bs-target="#operation-caisse">
          Decaissement
        </a>
      </div>
      <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img
              src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>

      <%--      <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>--%>

    </div>
  </div>

  <div class="all-modal">
    <%@ include file="modal/new-operation-caisse.jsp" %>
  </div>
  <!-- suite -->
  <div class="container-fluid mt-1">
    <div class="row">
      <div class="col-md-2 m-1 type-caisse" id="caisse-facture" value-filter="FACTURE">
        <div class="row">
          <div class="col-md-4 bg-primary d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light" value-filter="FACTURE">
            <span>Vente</span><br>
            <span id="montant-vente" class="label-montant" nbs>${vente}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-depense" value-filter="DECAISSEMENT">
        <div class="row">
          <div class="col-md-4 bg-warning d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Depenses</span><br>
            <span id="montant-depense" class="label-montant" nbs>${depense}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-recette" value-filter="RECETTE">
        <div class="row">
          <div class="col-md-4 bg-success d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Recette</span><br>
            <span id="montant-recette" class="label-montant" nbs>${recette}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-consommation" value-filter="CONSOMMATION">
        <div class="row">
          <div class="col-md-4 bg-primary d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Consommation</span><br>
            <span id="montant-consommation" class="label-montant" nbs>${consommation}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-avoir" value-filter="AVOIR">
        <div class="row">
          <div class="col-md-4 bg-danger d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Avoir</span><br>
            <span id="montant-avoir" class="label-montant" nbs>${avoir}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-espece" value-filter="ESPECE">
        <div class="row">
          <div class="col-md-4 bg-info d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Especes</span><br>
            <span id="montant-espece" class="label-montant" nbs>${espece}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-cheque" value-filter="CHEQUE">
        <div class="row">
          <div class="col-md-4 bg-success d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Cheque</span><br>
            <span id="montant-cheque" class="label-montant" nbs>${cheque}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-credit" value-filter="CREDIT">
        <div class="row">
          <div class="col-md-4 bg-danger d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Credit</span><br>
            <span id="montant-credit" class="label-montant" nbs>${credit}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="caisse-virement" value-filter="VIREMENT">
        <div class="row">
          <div class="col-md-4 bg-warning d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Virement</span><br>
            <span id="montant-virement" class="label-montant" nbs>${virement}</span>
          </div>
        </div>
      </div>
      <div class="col-md-2 m-1 type-caisse" id="mobile-money" value-filter="MOBILE_MONEY">
        <div class="row">
          <div class="col-md-4 bg-success d-flex justify-content-center align-content-center align-items-center text-center">
            <i class="uil-money-bill uil-size-10"></i>
          </div>
          <div class="col-md-8 p-2 card-caisse bg-secondary text-light">
            <span>Mobile Money</span><br>
            <span id="montant-mobile_money" class="label-montant" nbs>${mobileMoney}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="scroll-vertical-datatable"
               class="table-liste-operation-caisse table-special-form table table-sm dt-responsive nowrap table-hover text-dark">
          <thead>
          <tr>
            <th>reference</th>
            <th>Operation</th>
            <th>Date</th>
            <th>Description</th>
            <th>Mode de paiement</th>
            <th>Montant(Ar)</th>
            <th>Operateur</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="info" items="${caisse}">
            <tr id="${info.id}">
              <td>${info.reference}</td>
              <td>${info.operationCaisse}</td>
              <td>${info.date}</td>
              <td>${info.description}</td>
              <td>${info.modePayement}</td>
              <td nbs>${info.montantOperation}</td>
              <td>${info.user.nom}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
    </div>

  </div>

  <div class="all-modal">
    <%@ include file="modal/impression/ticket-encaissement-or-decaissement.jsp" %>
    <%@ include file="modal/impression/facture-encaissement-or-decaissement.jsp" %>
  </div>

</div>

</div> <!-- container -->

</div> <!-- content -->
</div>

</div>

</div>
</div>

</div>
</div>
<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->

<%@ include file="template/setting.jsp" %>