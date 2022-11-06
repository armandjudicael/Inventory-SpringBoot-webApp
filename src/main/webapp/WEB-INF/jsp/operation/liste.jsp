<%@ include file='../template/header.jsp' %>
<%@ page contentType="text/html; charset=UTF-8" %>
<i class="no-title" title="Liste"></i>

<!-- Start Content-->
<div id="liste-operation" class="container-fluid">

  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <h4 class="page-title">Listes des operations</h4>
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

      <div class="d-lg-inline-flex d-md-inline-flex d-sm-flex mt-1">
          <input type="date" id="begin-date-input" class="form-control wm-100 ms-1">
          <input type="date" id="end-date-input" class="form-control wm-100 ms-1">
          <a type="button" id="search-button" class="btn btn-success ms-1 wm-100">
              <i class="uil-search-alt"></i>
          </a>
        <select name="magasin" id="magasin-select-operation" class="form-select wm-100 ms-1">
          <option value="toutes">Toutes</option>
          <c:forEach var="magasin" items="${magasins}">
            <option value="${magasin.id}"> <c:out value="${magasin.nomMagasin}"/> </option>
          </c:forEach>
        </select>

      </div>

      <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
      <a type="button" id="refresh-btn"  class="btn btn-success float-end me-1 refresh-list-btn btn-mobile"><i
              class="uil-refresh">&nbsp;</i></a>
    </div>
  </div>


  <div class="all-modal">
    <%@ include file="../modal/new-article.jsp" %>
    <%@ include file="../modal/new-categorie.jsp" %>
  </div>

  <!-- suite -->


  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="scroll-vertical-datatable" class="table-liste-operation table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Réference</th>
            <th>Designation</th>
            <th>Unite</th>
            <th>Operation</th>
            <th>quantite</th>
            <th>Stock aprés operation</th>
            <th>Date</th>
            <th>Magasin</th>
            <th>Description</th>
            <th>Operateur</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="operation" items="${operations}">
            <tr id="${operation.id}">
              <td>${operation.reference}</td>
              <td>${operation.article.designation}</td>
              <td>${operation.unite.designation}</td>
              <td>${operation.typeOperation}</td>
              <td>${operation.quantiteAjout} ${operation.unite.designation}</td>
              <td>${operation.quantiteStockApresOperation} ${operation.unite.designation}</td>
              <td>${operation.date}</td>
              <td>${operation.magasin.nomMagasin}</td>
              <td>${operation.description}</td>
              <td>${operation.user.nom}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
    </div>
  </div>

  <div class="text-end float-end mt-1 label-count-footer">
    <span><span class="text-count-operation-list">00</span> opérations enregistrés</span>
  </div>
</div>

</div> <!-- container -->


<%@ include file="../template/footer.jsp" %>
<%@ include file="../template/setting.jsp" %>