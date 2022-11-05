<!-- Standard modal -->
<div id="nouveau-client" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <form id="form-nouveau-client" class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Client</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
      <div class="modal-body">
        <div class="mb-1">
          <label for="numCIN" class="form-label">Numero CIN</label>
          <input name="numCIN" type="text" id="numCIN" class="form-control">
          <label for="" class="invalid-feedback">Le numero CIN doit être rempli</label>
        </div>
        <div class="mb-1">
          <label for="nomClient" class="form-label">Nom du Client</label>
          <input name="nomClient" type="text" id="nomClient" class="form-control">
          <label for="" class="invalid-feedback">Le nom du client doit être rempli</label>
        </div>

        <div class="mb-1">
          <div class="row">
            <div class="col-md-6">
              <label for="adresse" class="form-label">Adresse</label>
              <input name="adresse" type="text" id="adresse" class="form-control">
              <label for="" class="invalid-feedback">L'adresse doit être rempli</label>
            </div>
            <div class="col-md-6">
              <label for="contact" class="form-label">Contact</label>
              <input name="contact" type="text" id="contact" class="form-control">
              <label for="" class="invalid-feedback">Le contact doit être rempli</label>
            </div>
          </div>

        </div>

        <div class="mb-1">
          <div class="row">
            <div class="col-md-6">
              <label for="nif" class="form-label">NIF</label>
              <input name="nif" type="text" id="nif" class="form-control">
              <label for="" class="valid-feedback">Le NIF est facultatif</label>
            </div>
            <div class="col-md-6">
              <label for="stat" class="form-label">STAT</label>
              <input name="stat" type="text" id="stat" class="form-control">

              <label for="" class="valid-feedback">Le STAT est facultatif</label>
            </div>
          </div>
        </div>

        <div class="mb-1">
          <label for="cif" class="form-label">CIF</label>
          <input name="cif" type="text" id="cif" class="form-control">

          <label for="" class="valid-feedback">Le CIF est facultatif</label>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-client" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->