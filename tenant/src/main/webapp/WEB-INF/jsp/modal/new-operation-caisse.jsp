<!-- Standard modal -->
<div id="operation-caisse" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content bg-light text-dark">
      <div class="modal-header">
        <h4 class="modal-title label-title" id="standard-modalLabel">Encaissement/Decaissement</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <label for="input-montant" class="form-label">Montant</label>
          <input name="montant" type="number" id="input-montant" class="form-control" value="0">
        </div>
        <div class="mb-1">
          <label for="area-description" class="form-label">Description</label>
          <textarea name="description" id="area-description" cols="30" rows="3" class="form-control"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <a type="button" id="btn-enregistrer-operation-caisse" class="btn btn-success mr-1">
          Enregistrer
        </a>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->