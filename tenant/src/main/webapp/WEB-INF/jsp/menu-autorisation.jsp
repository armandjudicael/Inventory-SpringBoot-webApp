<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div id="menu-autorisation" class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <h4 class="page-title">Autorisation</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
      </div>
      <div class="d-block mt-1">
        <div class="page-title-right float-end">
          <a href="" class="btn btn-primary float-end"><i class="uil-refresh"></i>&nbsp;</a>
        </div>
      </div>
    </div>
  </div>
  <!-- suite -->
  <input type="hidden" id="user-id" value-id="${connectedUser.id}" value-name="${connectedUser.id}">
  <div><br>
    <div class="row">
      <div class="col-lg-2">
        <%= start_content_table() %>
        <table id="fonction-tab" class="table table-sm table-hover">
          <thead>
          <th>Listes des fonctions</th>
          </thead>
          <tbody>
          <c:forEach var="fonction" items="${fonctions}">
            <tr id="${fonction.id}">
              <td>${fonction.nomFonction}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
      <div class="col-lg-10">
        <ul class="nav nav-tabs mb-3">
          <li class="nav-item">
            <a href="#auto-acceuil" data-bs-toggle="tab" aria-expanded="true" class="nav-link active">
              <i class="mdi mdi-home-variant d-md-none d-block"></i>
              <span class="d-none d-md-block">Page d'acceuil</span>
            </a>
          </li>
          <li class="nav-item">
            <a href="#auto-menu" data-bs-toggle="tab" aria-expanded="false" class="nav-link">
              <i class="mdi mdi-account-circle d-md-none d-block"></i>
              <span class="d-none d-md-block">Menu</span>
            </a>
          </li>
          <li class="nav-item">
            <a href="#auto-creation" data-bs-toggle="tab" aria-expanded="false" class="nav-link">
              <i class="mdi mdi-settings-outline d-md-none d-block"></i>
              <span class="d-none d-md-block">Creation et Modification </span>
            </a>
          </li>
        </ul>

        <div class="tab-content">
          <div class="tab-pane show active" id="auto-acceuil">
            <div class="content m-1 col-6">
              <tr>
                 <td> Page d'acceuil : <span id="page-acceuil"></span> </td>
                 <td> <button data-bs-toggle="modal" data-bs-target="#welcome-page-modal" class="btn btn-success" ><i class="uil-edit" ></i>Modifier </button> </td>
              </tr>
            </div>
          </div>

          <div class="tab-pane" id="auto-menu">
            <%= start_content_table() %>
            <table id="auto-tab" class="list-menu table table-hover">
              <tbody>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div>
          <div class="tab-pane" id="auto-creation">
            <%= start_content_table() %>
            <table id="creation-tab" class="tab-creation table table-hover">
              <tbody>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- inclusion of modal -->
  <div class="all-modal">
    <%@ include file="modal/new-fonction-autorisation.jsp" %>
    <%@ include file="modal/autorisation/welcome-page-modal.jsp" %>
  </div>

</div>


</div> <!-- container -->

<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>