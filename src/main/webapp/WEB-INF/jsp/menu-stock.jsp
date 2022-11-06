<%@ include file='template/header.jsp' %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-stock">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="stock-search">
            <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Stock</h4>
      </div>
    </div>
  </div>
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">

      </div>
      <div class="d-inline-flex mt-1">
        <a type="button" id="toutes-button" class="btn s-no-value btn-40 btn-success mr-1 btn-all-stock btn-mobile"><i
                class="uil-table">&nbsp;</i>Total</a>&nbsp;
        <a id="btn-article-alert" type="button" class="btn s-no-value btn-40 btn-warning mr-1 btn-alert-stock btn-mobile"><i
                class="uil-apps">&nbsp;</i>Alertes</a>&nbsp;
        <h4 class="s-value label-valeur-stock">Montant total : <%= "0Ar" %>
        </h4>
        &nbsp;
        <div class="d-inline-flex s-value">
          <div class="mb-3 mr-1">
            <select required class="form-select" id="magasin-select">
              <option value="">Total</option>
              <c:forEach var="magasin" items="${magasins}">
                <option value="${magasin.id}"><c:out value="${magasin.nomMagasin}"/></option>
              </c:forEach>
            </select>
          </div>
        </div>&nbsp;
        <button class="btn s-value btn-40 btn-success mr-1 btn-alert-stock btn-stock-valider"><i
                class="uil-check-square">&nbsp;</i>Valider
        </button>
      </div>
      <div class="float-end">
        <a href="" class="btn btn-default s-no-value">Toute niveau 1</a>
        <button class="btn btn-info s-no-value btn-stock-valeur btn-mobile"><i class="uil-dollar-alt"></i>Valeur</button>
        <a id="refresh-btn" type="button" class="btn btn-success btn-mobile"><i class="uil-refresh"></i></a>
        <a class="btn btn-success btn-export-to-excel bg-forest"><img
                src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>

      </div>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/stock/info-stock.jsp" %>
    <%@ include file="modal/stock/inventaire-stock.jsp" %>
    <%@ include file="modal/stock/quantite-alert-stock.jsp" %>
  </div>
  <!-- suite -->
  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="inventory-table"
               class="table-article-stock table table-special-form table-sm dt-responsive nowrap table-hover table-50">
          <thead>
          <tr>
            <th>Designation</th>
            <th>Unite</th>
            <th>Categorie</th>
            <th>Magasin</th>
            <th>Stock</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="stock" items="${stocks}">
            <tr id="${stock.magasinId}-${stock.articleId}-${stock.uniteId}">
              <td>${stock.article}</td>
              <td>${stock.unite}</td>
              <td>${stock.categorie}</td>
              <td>${stock.nomMagasin}</td>
              <td class="td-info-stock">
                <a type="button" class="btn-default mr-1 btn-info-stock"><span nbs>${stock.quantite}</span> ${stock.unite}</a>
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
    <span><span class="text-count-stock">00</span> stock d'articles enregistrés</span>
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