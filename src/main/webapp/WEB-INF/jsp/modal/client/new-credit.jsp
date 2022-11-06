<!-- Standard modal -->
<div id="nouveau-credit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Credit</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <label for="nomClient" class="form-label">Nom du Client</label>
          <input name="nomClient" type="text" id="nomClient" class="form-control" value="Nomduclient">
        </div>
        <div class="mb-1">
          <label for="somme" class="form-label">Montant</label>
          <input name="somme" type="number" required id="somme" class="form-control">
        </div>

        <div class="mb-1">
          <label for="description" class="form-label">Description</label>
          <textarea name="description" id="description" class="form-control"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-credit-client" data-bs-dismiss="modal" type="button" class="btn btn-primary">Enregistrer</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->