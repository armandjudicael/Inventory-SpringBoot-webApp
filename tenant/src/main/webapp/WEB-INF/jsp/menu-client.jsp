<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div id="menu-client" class="container-fluid">
  <input type="hidden" id="user-id" value-id="${connectedUser.id}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
            <button id="search-btn" class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Client</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
      </div>
      <div class="d-block mt-1">
        <a type="button" class="btn btn-success mr-1 btn-nouveau-client btn-mobile crud-client"><i
                class="uil-plus">&nbsp;</i>Nouveau Client</a>
        <div class="float-end">
          <a id="refresh-btn" type="button"  class="btn btn-success mr-1 refresh-list-btn btn-mobile"><i
                  class="uil-refresh">&nbsp;</i></a>
          <a class="btn btn-success btn-export-to-excel bg-forest"><img
                  src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image"
                  class="icon-excel"></a>
        </div>
      </div>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/client/new-client.jsp" %>
    <%@ include file="modal/client/info-dette-cf.jsp" %>
    <%@ include file="modal/fournisseur/new-dette.jsp" %>
    <%@ include file="modal/fournisseur/payement-dette.jsp" %>
  </div>
  <!-- suite -->
  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="table-client" class="table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Nom & Prenoms</th>
            <th>Adresse</th>
            <th>Contact</th>
            <th>Total crédit (Ar)</th>
            <th class="crud-client">Action</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="cf" items="${cfList}">
            <tr id="${cf.id}">
              <td>${cf.nom}</td>
              <td>${cf.adresse}</td>
              <td>${cf.numTel}</td>
              <td>${cf.totalTrosa}</td>
              <td class="crud-client">
                <div class="action-client">
                  <a class="btn-sm btn-info editClient "><i class="uil-pen"></i></a>
                  <a class="btn-sm btn-danger deleteClient "><i class="uil-trash-alt"></i></a>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
    </div>
  </div>
</div>
</div> <!-- container -->
<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>