<!-- Standard modal -->
<div id="nouvelle-fonction-autorisation" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="standard-modalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouvelle Fonction</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label for="libelle" class="form-label">Libelle</label>
          <input name="libelle" type="text" id="libelle" class="form-control">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-save-function" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->