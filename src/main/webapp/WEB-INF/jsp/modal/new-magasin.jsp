<!-- Standard modal -->
<div id="new-magasin" data-type="nouveau" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="standard-modalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau magasin</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label for="nom-magasin" class="form-label">Nom du magasin</label>
          <input name="nomMagasin" type="text" id="nom-magasin" class="form-control">
        </div>
        <div class="mb-3">
          <label for="adresse-magasin" class="form-label">Adresse du magasin</label>
          <input name="adresseMagasin" type="text" id="adresse-magasin" class="form-control">
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-magasin" type="submit" class="btn btn-primary">Enregistrer
        </button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
