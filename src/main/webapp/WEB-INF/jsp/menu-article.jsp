<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-article">
  <!-- start page title -->
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
            <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Article</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
        <div class="d-block">
          <a id="newArticleBtn" class="btn btn-success mt-1 mr-1 btn-mobile crud-article"
             data-bs-toggle="modal"
             data-bs-target="#new-article"><i class="uil-file-plus">&nbsp;</i>Nouveau Article</a>
          <a id="btn-barcode-toggle" class="btn btn-info bg-forest mt-1 btn-mobile"><i
                  class="uil uil-eye-slash"></i> Barcode articles</a>
          <a class="btn btn-success btn-export-to-excel bg-forest float-end mt-1"><img
                  src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
          <a id="refresh-btn" type="button" class="btn btn-success float-end mt-1 me-1 btn-mobile"><i
                  class="uil-refresh">&nbsp;</i></a>
        </div>
      </div>
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/new-article.jsp" %>
    <%@ include file="modal/new-categorie.jsp" %>
    <%@ include file="modal/info-barcode.jsp" %>
  </div>
  <!-- suite -->
  <div class=""><br>
    <div class="row">
      <div class="col-lg-3 t-list-75">
        <%= start_content_table() %>
        <table id="categorieTabList" class="table table-sm table-hover">
          <thead>
          <th>Listes des categories</th>
          <th class="crud-article"></th>
          </thead>
          <tbody>
          <tr>
            <td>Toutes</td>
            <td class="crud-article"></td>
          </tr>
          <c:forEach var="operation" items="${categories}">
            <tr id="${operation.id}">
              <td><c:out value="${operation.libelle}"/></td>
              <td class="crud-article">
                <div class="d-inline-flex justify-content-center">
                  <a id="${operation.id}" href="#"
                     class="editCategorie"><i class="uil-pen"></i></a>
                  <a id="${operation.id}" href="#" class="deleteCategorie"><i class="uil-trash-alt"></i></a>
                </div>&nbsp;
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
        <tfoot class="crud-article">
        <tr>
          <th class="border-bottom-0">
            <div class="d-flex justify-content-start">
              <a href="" class="btn btn-success" id="btn-new-categorie" data-bs-toggle="modal" data-bs-target="#standard-modal2"><i
                      class="uil-plus"></i></a>&nbsp;
            </div>
          </th>
        </tr>
        </tfoot>
        <br>
      </div>
      <div class="col-lg-9 t-list-75">
        <%= start_content_table() %>
        <table id="articleTable" class="table table-sm dt-responsive nowrap table-hover text-uppercase">
          <thead>
          <tr>
            <th class="barcode-list-articles">Code article</th>
            <th>Designation</th>
            <th>categorie</th>
            <th>Poids(Kg)</th>
            <th>unite</th>
            <th>quantite</th>
            <th class="text-center crud-article">Action</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="au" items="${articles}">
            <tr au-id=${au.id}  id="${au.article.id}">
              <td class="barcode-list-articles"><span
                      class="text d-none">1-${au.article.categorie.id}-${au.article.id}-${au.niveau}</span></td>
              <td value-id="${au.article.id}">${au.article.designation}</td>
              <td value-id="${au.article.categorie.id}">${au.article.categorie.libelle}</td>
              <td nbs>${au.poids}</td>
              <td value-id="${au.niveau}">${au.unite.designation}</td>
              <td nbs>${au.quantiteNiveau}</td>
              <td class="td-action crud-article">
                <div>
                  <a id="${au.article.id}" data-bs-toggle="modal" data-bs-target="#new-article"
                     class="btn-sm btn-info editArticleBtn"><i class="uil-pen"></i></a>
                  <a id="${au.article.id}" class="btn-sm btn-danger deleteArticleBtn "><i
                          class="uil-trash-alt"></i></a>
                  <a id="${au.article.id}" class="btn-sm btn-warning hideArticleBtn"><i
                          class="uil-eye-slash"></i></a>
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
    <span><span class="text-count-articles">00</span> d'articles enregistrés</span>
  </div>
</div>
</div> <!-- container -->
<%@ include file="template/footer.jsp" %>
<%@ include file="template/setting.jsp" %>