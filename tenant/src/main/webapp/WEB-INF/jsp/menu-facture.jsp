<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file='template/header.jsp' %>
<!-- Start Content-->
<div class="container-fluid" id="menu-facture">
  <input type="hidden" id="user-id" value-id="${connectedUser.id}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
          <div class="page-title-right">
            <div class="d-inline-flex">
                <select id="type-filter" class="form-select">
                  <option value="CLIENT">CLIENT</option>
                  <option value="REFERENCE">REFERENCE</option>
                </select>
                <div class="input-group">
                      <input type="text" required="" class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
                      <button id="search-btn" class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
                </div>
            </div>
          </div>
        <h4 class="page-title">Facture</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          </div>
      </div>
      <div class="d-lg-inline-flex d-md-inline-flex d-sm-block mt-1">
        <input id="input-date-debut" type="date" class="form-control btn-40 wm-100 ms-1">
        <input id="input-date-fin" type="date" class="form-control btn-40 wm-100 ms-1">
        <a type="button" id="btn-search-filter" class="btn btn-success btn-40 wm-100 btn-mobile ms-1">
          <i class="uil-check-circle"></i>
        </a>
      </div>
      <div class="float-end">
        <a href="" class="btn btn-warning d-none"><i class="uil-meh-closed-eye"></i></a>
        <button id="refresh-btn" class="btn btn-success"><i class="uil-refresh">&nbsp;</i></button>
        <a class="btn btn-success btn-export-to-excel bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
      </div>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/facture/info-facture.jsp" %>
<%--    <%@ include file="modal/facture/nouveau-info-facture.jsp" %>--%>
    <%@ include file="modal/impression/facture-vente.jsp" %>
    <%@ include file="modal/impression/ticket-caisse.jsp" %>
    <%@ include file="modal/impression/ticket-acceptation.jsp" %>
    <%@ include file="modal/impression/facture-acceptation.jsp" %>
    <%@ include file="modal/facture/mode-payement.jsp" %>
  </div>
  <!-- suite -->
  <div><br>

    <div class="row">

      <div class="col-lg-12">

        <%= start_content_table() %>

        <table id="scroll-vertical-datatable"
               class="table-facture table table-sm dt-responsive nowrap table-hover">
          <thead>

          <tr>
            <th>Réference</th>
            <th>Client</th>
            <th>Montant (Ar)</th>
            <th>Operateur</th>
            <th>Date et heure</th>
            <th class="d-none">Action</th>
          </tr>
          </thead>
          <tbody>

          <c:forEach var="facture" items="${factures}">

            <tr type-facture = "VENTE" concerned-by-invoice-regulation="${facture.isConcernedByInvoiceRegulation}" payement-mode-modified ="${facture.isPayementModeChanged}"  id="${facture.refVente}">
              <td>${facture.refVente}</td>
              <td client-id ="${facture.client.id}">${facture.client.nom}</td>
              <td nbs>${facture.montantVente}</td>
              <td>${facture.user.nom}</td>
              <td>${facture.date}</td>
              <td class="d-flex justify-content-center td-action d-none">
                <div><a class="btn-sm btn-info info-facture"><i class="uil-info-circle"></i></a></div>
              </td>
            </tr>

            <c:if test="${facture.avoir!=null}">
              <tr type-facture ="AVOIR">
                <td>${facture.avoir.refAvoir}</td>
                <td client-id ="${facture.client.id}">${facture.client.nom}</td>
                <td nbs>-${facture.avoir.montant}</td>
                <td>${facture.user.nom}</td>
                <td>${facture.avoir.date}</td>
                <td class="d-flex justify-content-center td-action d-none">
                  <div><a class="btn-sm btn-info info-facture"><i class="uil-info-circle"></i></a></div>
                </td>
              </tr>
            </c:if>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
    </div>
  </div>
  <div class="d-inline-flex float-end ">
    <div class="text-end mt-1 me-1 label-count-footer bg-primary">
      <span><span class="label-sum-facture" nbs>00</span> Ar</span>
    </div>
    <div class="text-end mt-1 me-1 label-count-footer">
      <span><span class="text-count-vente" nbs>00</span> facture enregistrés</span>
    </div>
  </div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>