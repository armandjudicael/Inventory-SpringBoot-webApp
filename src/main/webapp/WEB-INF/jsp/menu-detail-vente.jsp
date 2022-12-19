<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file='template/header.jsp' %>
<!-- Start Content-->
<div class="container-fluid" id="menu-detail-vente">

  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="d-flex flex-grow-0" >
            <select id="type-filter" class="form-select">
              <option value="CLIENT">CLIENT</option>
              <option value="REFERENCE">REFERENCE</option>
              <option value="ARTICLE">ARTICLE</option>
            </select>
            <div class="input-group">
              <input type="text" required class="form-control dropdown-toggle" placeholder="Search..."
                     id="input-search-operation">
              <button id="btn-search-operation" class="input-group-text btn-primary" type="submit"><i
                      class="uil-search"></i></button>
            </div>
          </div>
        </div>
        <h4 class="page-title">DETAIL DE VENTE</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
      </div>
      <div class="d-lg-inline-flex d-md-inline-flex d-sm-block mt-1">
        <input type="date" id="begin-date-input" class="form-control m-1">
        <input type="date" id="end-date-input" class="form-control m-1">
        <a type="button" id="search-button" class="btn btn-success m-1 wm-100"><i class="uil-search-alt"></i></a>
        <select name="magasin" id="magasin-select-operation" class="form-select m-1">
          <c:forEach var="magasin" items="${magasins}">
            <option value="${magasin.id}"><c:out value="${magasin.nomMagasin}"/></option>
          </c:forEach>
        </select>
      </div>
      <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/new-article.jsp" %>
    <%@ include file="modal/new-categorie.jsp" %>
  </div>
  <!-- suite -->
  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="details-vente-table"
               class="table-detail-vente table-special-form table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Réference</th>
            <th>Client</th>
            <th>Article</th>
            <th>Unite</th>
            <th>Quantite</th>
            <th>Prix Unitaire (Ar)</th>
            <th>Montant (Ar)</th>
            <th>Magasin</th>
            <th>Date</th>
          </tr>
          </thead>
          <tbody>

          <c:forEach var="vente" items="${sales}">

            <c:forEach var="info" items="${vente.infoArticleMagasin}">
              <tr id="${vente.id}">
                <td>${vente.refVente}</td>
                <td>${vente.client.nom}</td>
                <td>${info.article.designation}</td>
                <td>${info.unite.designation}</td>
                <td nbs>${info.quantiteAjout}</td>
                <td nbs>${info.montantArticle/info.quantiteAjout}</td>
                <td nbs>${info.montantArticle}</td>
                <td>${info.magasin.nomMagasin}</td>
                <td>${info.date}</td>
              </tr>
            </c:forEach>

           <c:if test="${vente.avoir!=null}">
                <c:forEach var="info" items="${vente.avoir.infoArticleMagasin}">
                  <tr id="${info.id}">
                    <td>${vente.avoir.refAvoir}</td>
                    <td>${vente.client.nom}</td>
                    <td>${info.article.designation}</td>
                    <td>${info.unite.designation}</td>
                    <td nbs>${info.quantiteAjout}</td>
                    <td nbs>${info.montantArticle/info.quantiteAjout}</td>
                    <td nbs>-${info.montantArticle}</td>
                    <td>${info.magasin.nomMagasin}</td>
                    <td>${vente.avoir.date}</td>
                  </tr>
                </c:forEach>
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
      <span><span class="label-sum-ventes" nbs>${TOTAL_MONTANT_VENTE}</span> Ar</span>
    </div>
    <div class="text-end mt-1 me-1 label-count-footer">
      <span><span class="text-count-vente" nbs>00</span> vente enregistrés</span>
    </div>
  </div>
</div>

</div> <!-- container -->
<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>