<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 18/08/2022mini
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->

<%@ page contentType="text/html; charset=UTF-8" %>
<div id="info-dette-cf" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-full-width modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="num-facture"></h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            <div class="page-title-box">
              <div class="page-title-right">
              </div>
              <div>
                <a type="button" class="btn btn-outline-secondary mr-1 crud-client btn-nouveau-dette"
                   data-bs-target="#nouveau-dette"
                   data-bs-toggle="modal"><i class="uil-plus"></i>Nouveau Dette</a>
                <a type="button" id="refresh-list-btn" class="btn btn-success mr-1 btn-mobile"><i class="uil-refresh">&nbsp;</i>Actualiser</a>

                <a class="btn btn-success btn-export-to-excel bg-forest float-end"><img
                        src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image"
                        class="icon-excel"></a>
              </div>
            </div>
            <br>
            <%= start_content_table() %>
            <table id="table-dette-cf" class="table table-hover table-striped norwap table-sm dt-responsive">
              <thead>
              <th>Facture</th>
              <th>Date</th>
              <th>Montant(Ar)</th>
              <th>Payer(Ar)</th>
              <th>Reste(Ar)</th>
              <th>Mode de payement</th>
              <th>Status</th>
              <th>Date échéance</th>
              <th>Description</th>
              <th>Action</th>
              </thead>
              <tbody>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div>
        </div>
      </div>
      <div class="modal-footer d-inline-flex">
        <%--                <button type="button" class="btn btn-primary w-100 m-0"><i class="uil-print"></i>&nbsp;Imprimer</button>--%>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->