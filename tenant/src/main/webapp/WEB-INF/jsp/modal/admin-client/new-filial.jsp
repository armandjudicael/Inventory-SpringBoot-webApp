<!-- Standard modal -->
<div id="nouveau-filial" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <form id="form-nouveau-filial" class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Nouveau filial</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <div class="row">
            <div class="col-12">
              <label for="input-nom" class="form-label">Nom du filial</label>
              <input name="nom" type="text" id="input-nom" class="form-control">
            </div>
          </div>

        </div>
        <div class="mb-1">
          <div class="row">
            <div class="col-6">
              <label for="input-adresse" class="form-label">Adresse</label>
              <input name="adresse" type="text" id="input-adresse" class="form-control">
            </div>
            <div class="col-6">
              <label for="input-contact" class="form-label">Contact</label>
              <input name="contact" type="text" id="input-contact" class="form-control">
            </div>
          </div>

        </div>
        <div class="mb-1">
          <label for="input-username" class="form-label">Nom d'utilisateur</label>
          <input name="username" type="text" id="input-username" class="form-control">
        </div>
        <div class="mb-1">
          <label for="input-password" class="form-label">Mot de passe</label>
          <input name="password" type="password" id="input-password" class="form-control">
        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-filial" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->