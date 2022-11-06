<!-- Standard modal -->
<div id="new-chauffeur" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Chauffeur</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <label for="nomEtPrenoms" class="form-label">Nom & prenoms</label>
          <input name="nomEtPrenoms" type="text" required id="nomEtPrenoms" class="form-control">
        </div>
        <div class="mb-1">
          <label for="numCIN" class="form-label">Numero CIN</label>
          <input name="numCIN" type="text" required id="numCIN" class="form-control">
        </div>
        <div class="mb-1">
          <label for="adresse" class="form-label">Adresse</label>
          <input name="adresse" type="text" required id="adresse" class="form-control">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="saveMagasinBtn" data-bs-dismiss="modal" type="button" class="btn btn-primary">Enregistrer</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->