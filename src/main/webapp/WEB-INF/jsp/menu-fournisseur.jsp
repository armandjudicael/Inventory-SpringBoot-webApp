<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-fournisseur">
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
        <h4 class="page-title">Fournisseur</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
      </div>
      <div class="d-block mt-1">
        <a type="button" class="btn btn-success mr-1 btn-nouveau-fournisseur btn-mobile crud-fournisseur"><i
                class="uil-plus">&nbsp;</i>Nouveau Fournisseur</a>
        <div class="float-end">
          <a type="button" id="refresh-btn"  class="btn btn-success mr-1 refresh-list-btn btn-mobile"><i
                  class="uil-refresh">&nbsp;</i></a>
          <a class="btn btn-success btn-export-to-excel bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel"></a>
        </div>
      </div>
    </div>
  </div>

  <div class="all-modal">
    <%@ include file="modal/client/info-dette-cf.jsp" %>
    <%@ include file="modal/fournisseur/new-fournisseur.jsp" %>
    <%@ include file="modal/fournisseur/new-dette.jsp" %>
    <%@ include file="modal/fournisseur/payement-dette.jsp" %>
  </div>
  <!-- suite -->
  <div><br>
    <div class="row">

      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="table-fournisseur" class="table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Nom & Prenoms</th>
            <th>Adresse</th>
            <th>Contact</th>
            <th>Total dette (Ar)</th>
            <th class="crud-fournisseur">Action</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="cf" items="${cfList}">
            <tr id="${cf.id}">
              <td><c:out value="${cf.nom}"/></td>
              <td><c:out value="${cf.adresse}"/></td>
              <td><c:out value="${cf.numTel}"/></td>
              <td><c:out value="${cf.totalTrosa}"/></td>
              <td class="crud-fournisseur td-action">
                <div class="action-client">
                  <a  class="btn-sm btn-info editFournisseur"><i class="uil-pen"></i></a>
                  <a  class="btn-sm btn-danger deleteFournisseur"><i class="uil-trash-alt"></i></a>
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