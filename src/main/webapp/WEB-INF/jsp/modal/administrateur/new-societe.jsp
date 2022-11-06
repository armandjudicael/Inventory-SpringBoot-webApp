<!-- Standard modal -->
<div id="nouveau-societe" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">

  <div class="modal-dialog">

    <form id="form-nouveau-societe" class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Nouveau Societe</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>

      <div class="modal-body">

        <div class="mb-1">
          <div class="row">
            <div class="col-12">
              <label for="input-nom" class="form-label">Nom du societe</label>
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

          <div class="row">
            <div class="col-12">
              <label for="slogan" class="form-label">Slogan </label>
              <textarea id="slogan" class="form-control" rows="3" ></textarea>
            </div>
          </div>

        </div>

        <div class="mb-1">

          <div class="row">
            <div class="col-12">
              <label for="verset" class="form-label">Verset</label>
              <textarea id="verset" class = "form-control" rows = "3"> </textarea>
            </div>
          </div>

        </div>

        <div class="mb-1 pt-1">
          <div class="row">
            <div class="col-12">
              <label for="input-logo">T&eacute;l&eacute;verser le logo du soci&eacute;t&eacute;</label>
              <input name="logo" type="file" id="input-logo" class="form-control pt-1">
            </div>
          </div>
        </div>

        <br>
        <p class="h4" >  Administrateur  </p>
        <br>

        <div class="mb-1">
          <label for="input-username" class="form-label">Nom d'utilisateur (Root)</label>
          <input name="username" type="text" id="input-username" class="form-control">
        </div>

        <div class="mb-1">
          <label for="input-password" class="form-label">Mot de passe</label>
          <input name="password" type="password" id="input-password" class="form-control">
        </div>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-societe" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>

    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->

</div>
<!-- /.modal -->