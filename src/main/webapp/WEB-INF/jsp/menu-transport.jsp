<%@ include file='template/header.jsp' %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-transport">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <h4 class="page-title">Materiel Transport</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="d-block">
        <a id="btn-nouveau-magasin" type="button" class="btn btn-success mr-1 btn-mobile crud-magasin" data-bs-toggle="modal"
           data-bs-target="#nouveau-materiel-de-transport"><i
                class="uil-file-plus">&nbsp;</i> Materiel Transport </a>
        <a type="button" id="refresh-btn"  class="btn btn-success refresh-list-btn btn-mobile"><i
                class="uil-refresh">&nbsp;</i>Actualiser</a>
        <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img
                src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
      </div>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/Transport/new-materiel-de-transport.jsp" %>
    <%@ include file="modal/Transport/new-responsable.jsp" %>
  </div>
  <!-- suite -->
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <div class="all-modal"></div>
  <div><br>
    <div class="row ">
      <div class="col-lg-12">
        <%--        <h4 class="text-decoration-underline text-uppercase">Listes des utilisateurs du magasin</h4>--%>
        <%= start_content_table() %>
        <table id="table-materiel-transport"
               class="table table-special-form table-sm dt-responsive nowrap">
          <thead>
          <tr>
            <th>reference</th>
            <th>Categorie</th>
            <th>Chauffeur</th>
            <th>Type</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="item" items="${transports}">
            <tr id="${item.id}">
              <td>${item.reference}</td>
              <td>${item.typeMateriel.name()}</td>
              <td>${item.responsable.nom}</td>
              <td>
                <c:if test = "${item.isLocation eq true}">
                   <span class="badge badge-primary-lighten">Location</span>
                </c:if>
                <c:if test = "${item.isLocation eq false }">
                  <span class="badge badge-success-lighten">Propriété</span>
                </c:if>
              </td>
              <td class="td-action justify-content-center crud-embarquement">
                <div class="d-flex">
<%--                  <a data-bs-toggle="modal" data-bs-target="" class="btn-sm btn-info about-materiel"><i class="uil-info-circle"></i></a>--%>
                  <a data-bs-toggle="modal" data-bs-target="#nouveau-materiel-de-transport"  class="btn-sm btn-info edit-materiel"><i class="uil-pen"></i></a>
                  <a class="btn-sm btn-danger delete-materiel "><i class="uil-trash-alt"></i></a>
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
  <div class="text-end float-end mt-1 label-count-footer">
    <span><span class="text-count-utilisateurs">00</span> Materiel enregistrés</span>
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
<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>