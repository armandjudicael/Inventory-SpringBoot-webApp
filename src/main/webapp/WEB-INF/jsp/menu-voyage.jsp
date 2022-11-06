<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-voyage">
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
        <h4 class="page-title">Voyage</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
      </div>
      <div class="d-block mt-1">
        <a type="button" class="btn btn-success mr-1 btn-mobile" id="btn-nouveau-voyage"><i
                class="uil-plus">&nbsp;</i>Enregistrer un voyage</a>
        <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img
                src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image"
                class="icon-excel"></a>
      </div>

    </div>
  </div>


  <div class="all-modal">
    <%@ include file="modal/new-voyage.jsp" %>
  </div>

  <!-- suite -->

  <div><br>
    <div class="row">

      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="table-liste-voyage" class="table table-sm dt-responsive nowrap table-hover">
          <thead>
          <tr>
            <th>Reference</th>
            <th>Materiel de transport</th>
            <th>Montant d&eacute;pense</th>
            <th>Date Op&eacute;ration</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>ref-00000</td>
            <td>materieldetransport</td>
            <td>0Ar</td>
            <td>05/06/2022</td>
            <td>
              <div class="action-voyage">
                <a class="btn-sm btn-info editVoyage "><i class="uil-pen"></i></a>
                <a class="btn-sm btn-danger deleteVoyage "><i class="uil-trash-alt"></i></a>
              </div>
            </td>
          </tr>
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