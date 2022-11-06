<!-- Standard modal -->
<div id="avoir-facture" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Avoir</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4 aside">
            <div class="label">
              <br>
              <p>Reference avoir : <%= "ref A-000 000 000"%>
              </p>
              <p>Reference facture : <%= "ref 000 000 000"%>
              </p>
              <p>Du : <%= "30/05/2022"%>
              </p>
              <p>Montant : <%= "0Ar"%>
              </p>
              <p>Client : <%= "NomClient"%>
              </p>
            </div>
          </div>
          <div class="col-md-8">
            <table id="table-facture-avoir" class="table table-hover table-striped norwap table-sm dt-responsive">
              <thead>
              <th>Select</th>
              <th>Designation</th>
              <th>Unite</th>
              <th>Quantite</th>
              <th>Prix Unitaire</th>
              <th>Montant</th>
              </thead>
              <tbody>
              <% for (int c = 0; c < 5; c++) { %>
              <tr>
                <th><input type="checkbox" class="form-check-input" id="customCheck1"></th>
                <td>nomarticle</td>
                <td>unite</td>
                <td>0</td>
                <td>0Ar</td>
                <td>0Ar</td>
              </tr>
              <% } %>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="modal-footer d-inline-flex">
        <button type="button" id="btn-valider-avoir" class="btn btn-primary w-100 m-0"><i class="uil-check-square"></i>&nbsp;Valider</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->