<%@ include file='template/header.jsp' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Start Content-->
<div id="menu-prix" class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="prices-search">
            <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Prix</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2 p-0 m-0">
    <div class="col-12">
      <div class="page-title-box float-end">
        <a id="refresh-btn" class="btn btn-success"><i class="uil-refresh"></i></a>
        <a class="btn btn-success btn-export-to-excel bg-forest"><img
                src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
      </div>
    </div>
  </div>

  <div class="all-modal">
    <%@ include file="modal/edit-prix.jsp" %>
  </div>

  <!-- suite -->
  <input type="hidden" id="user-id" value-id="${connectedUser.id}">
  <input type="hidden" id="user-name" value-id="${connectedUser.nom}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <div><br>

    <div>
      <div class="row">
        <div class="col-lg-12">
          <%= start_content_table() %>
          <table id="table-prix" class="table-article-prix table table-sm table-special-form">
            <thead>
            <tr>
              <th>Designation</th>
              <th>Unite</th>
              <th>Prix</th>
              <th>Date mis à jour</th>
              <th class="crud-prix">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="price" items="${prices}">
              <tr id="${price.filiale.id}-${price.article.id}-${price.unite.id}">
                <td>${price.article.designation}</td>
                <td>${price.unite.designation}</td>
                <td>${price.prixVente}</td>
                <td>${price.dateEnregistrement}</td>
                <td class="td-action crud-prix">
                  <div><a role="button" class="btn-sm btn-info info-prix"><i
                          class="uil-pen"></i></a></div></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <%= end_content_table() %>
        </div>
      </div>

    </div>


  </div>

  <div class="text-end float-end mt-1 label-count-footer">
    <span><span class="text-count-prix-articles">00</span> d'articles avec prix enregistrés</span>
  </div>

</div>
<!-- container -->

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
