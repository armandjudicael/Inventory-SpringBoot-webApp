<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div id="menu-livraison" class="container-fluid">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
            <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Livraison</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
        <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img
                src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image"
                class="icon-excel"></a>

      </div>
    </div>
  </div>


  <!-- suite -->

  <div><br>
    <div class="row">

      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="table-livraison" class="table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Reference</th>
            <th>Designation</th>
            <th>Operation</th>
            <th>Entr&eacute;e</th>
            <th>Sortie</th>
            <th>Date</th>
          </tr>
          </thead>
          <tbody>

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