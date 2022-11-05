<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div id="menu-paiement" class="container-fluid">
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
        <h4 class="page-title">Paiement</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">

      </div>
      <div class="d-lg-inline-flex d-md-inline-flex d-sm-block mt-1">
        <input type="date" class="form-control m-1 wm-100">
        <input type="date" class="form-control m-1 wm-100">
        <a type="button" class="btn btn-40 btn-success m-1 wm-100">
          <i class="uil-filter"></i>
        </a>
      </div>
      <div class="float-end">
        <a href="" class="btn btn-primary"><i class="uil-print"></i></a>&nbsp;
        <a class="btn btn-success btn-export-to-excel bg-forest"><img
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
        <table id="table-paiement" class="table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Reference</th>
            <th>Client</th>
            <th>Operation</th>
            <th>Montant</th>
            <th>Mode de paiement</th>
            <th>Date</th>
            <th>Description</th>
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