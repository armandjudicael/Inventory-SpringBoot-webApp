<%@ include file='template/header.jsp' %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-peremption">

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
        <h4 class="page-title">Peremption</h4>
      </div>
    </div>
  </div>
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
<%--          <a href="" class="btn btn-primary" role="button" data-bs-toggle="modal" data-bs-target="#modal-liste-article"><i--%>
<%--                  class="uil-plus"></i>&nbsp;</a>--%>
        </div>
      </div>
      <div class="row">
        <div class="col-md-11 d-lg-inline-flex d-md-inline-flex d-sm-block">
          <a role="btn" class="btn btn-outline-primary m-1 btn-status btn-40 wm-100">Tout</a>
          <a role="btn" class="btn btn-outline-info m-1 btn-status btn-40 wm-100">Forte</a>
          <a role="btn" class="btn btn-outline-success m-1 btn-status btn-40 wm-100" >Moyenne</a>
          <a role="btn" class="btn btn-outline-warning m-1 btn-status btn-40 wm-100">Faible</a>
          <a role="btn" class="btn btn-outline-danger m-1 btn-status btn-40 wm-100">P&eacute;rim&eacute;</a>
          <select name="magasin" id="magasin-select-item" class="form-select m-1 wm-100 w-25">
            <option value="toutes" selected > TOUTES </option>
            <c:forEach var="magasin" items="${magasins}">
              <option value="${magasin.id}"><c:out value="${magasin.nomMagasin}"/></option>
            </c:forEach>
          </select>&nbsp;
        </div>
        <div class="col-md-1 d-inline-flex justify-content-end">
          <a class="btn btn-success btn-export-to-excel bg-forest btn-40 float-end"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>

        </div>
      </div>

      </div>
    </div>
  </div>

  <div class="all-modal">
<%--    <%@ include file="modal/vente/list-article.jsp" %>--%>
    <%@ include file="modal/date-peremption.jsp" %>
  </div>

  <!-- suite -->


  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <table id="expiration-table" class="table-peremption table table-sm dt-responsive nowrap table-hover table-striped">
          <thead>
          <tr>
            <th>Designation</th>
            <th>Unite</th>
            <th>Quantite en stock</th>
            <th>Date de peremption</th>
            <th>Etat</th>
          </tr>
          </thead>
          <tbody>
              <c:forEach var="au" items="${expirations}">
                <tr id="${au.magasinId}-${au.articleId}-${au.uniteId}">
                  <td>${au.nomArticle}</td>
                  <td>${au.nomUnite}</td>
                  <td>${au.quantitePeremetion}</td>
                  <td>${au.datePeremption}</td>
                  <c:if test = "${au.expirationStatus eq 'périmé' }">
                    <td><span class="badge badge-danger-lighten">périmé</span></td>
                  </c:if>
                  <c:if test = "${au.expirationStatus eq 'faible' }">
                    <td><span class="badge badge-warning-lighten">faible</span></td>
                  </c:if>
                  <c:if test = "${au.expirationStatus eq 'moyenne' }">
                    <td><span class="badge badge-primary-lighten">moyenne</span></td>
                  </c:if>
                  <c:if test = "${au.expirationStatus eq 'forte' }">
                    <td><span class="badge badge-success-lighten">forte</span></td>
                  </c:if>
                </tr>
              </c:forEach>
          </tbody>
        </table>

      </div>
    </div>

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