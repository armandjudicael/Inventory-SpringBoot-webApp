<!-- Standard modal -->
<div id="nouveau-trajet" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">

    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Trajet</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <label for="depart-input" class="form-label">Depart</label>
          <input name="reference" type="text" id="depart-input" class="form-control">
        </div>
        <div class="mb-1">
          <label for="destination-input" class="form-label">Destination</label>
          <input name="type-materiel" type="text" id="destination-input" class="form-control">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-trajet" data-bs-dismiss="modal" type="button"
                class="btn btn-primary">Enregistrer
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->