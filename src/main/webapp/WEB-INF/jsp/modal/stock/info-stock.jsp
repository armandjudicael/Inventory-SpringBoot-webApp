<!-- Standard modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="info-stock" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-full-width modal-dialog-scrollable">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Information Stock</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
      <input type="hidden" id="user-id" value-id="${connectedUser.id}">
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4 aside">
            <div class="label">
              <p class="label-magasin"></p>
              <p class="label-designation-article"></p>
              <p class="label-unite-article"></p>
              <p class="label-categorie-article"></p>
              <p class="label-stock-initial-article"></p>
              <p class="label-stock-final-article"></p>
              <p class="label-quantite-alerte-article">Quantit&eacute; d'alerte : <%= "00"%></p>
            </div>
            <div class="filter-date">
              <label for="input-date-debut">Date debut</label>
              <input id="input-date-debut" type="date" class="form-control" value="22/11/19">
              <label for="input-date-fin">Date Fin</label>
              <input id="input-date-fin" type="date" class="form-control">
            </div>
            <button id="info-stock-search-btn" class="btn btn-success btn-block w-100 mt-2"><i class="uil-check-square"></i>Valider</button>
          </div>
          <div class="col-md-8">
            <div class="page-title-box">
              <div class="page-title-right">
                <a class="btn btn-success btn-export-to-excel bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
              </div>
            </div>
            <%= start_content_table() %>
            <table id="table-operation-stock" class="table table-hover table-striped norwap table-sm dt-responsive">
              <thead>
              <th>reference</th>
              <th>Operation</th>
              <th>Quantité</th>
              <th>Quantité en stock</th>
              <th>Date</th>
              <th>Operateur</th>
              </thead>
              <tbody>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div>
        </div>
        <!-- inventaire -->
        <!-- modifier alerte -->
      </div>
      <div class="modal-footer d-inline-flex">
        <button type="button" class="btn btn-warning w-50 m-0 btn-update-stock">Mettre à jour stock</button>
        <button type="button" class="btn btn-primary w-50 m-0 btn-update-alert">Modifier Quantité d'alerte</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->