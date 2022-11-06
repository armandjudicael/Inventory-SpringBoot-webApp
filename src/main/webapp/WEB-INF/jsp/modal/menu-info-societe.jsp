<!-- Standard modal -->
<div id="info-filiale" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Information Soci&eacute;t&eacute;</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-0">
          <label for="nomSociete" class="form-label">Nom du filiale</label>
          <input type="text" required id="nomSociete" class="form-control form-control-sm">
        </div>
        <div class="mb-0">
          <div class="row">
            <div class="col-md-6">
              <label for="adresse" class="form-label">Adresse</label>
              <input name="adresse" type="text" required id="adresse" class="form-control form-control-sm">
            </div>
            <div class="col-md-6">
              <label for="ville" class="form-label">VIlle</label>
              <input name="ville" type="text" required id="ville" class="form-control form-control-sm">
            </div>
          </div>
        </div>
        <div class="mb-0">
          <label for="contact" class="form-label">Contact</label>
          <input type="text" required id="contact" class="form-control form-control-sm">
        </div>
        <div class="mb-0">
          <div class="row">
            <div class="col-md-6">
              <label for="nif" class="form-label">NIF</label>
              <input name="nif" type="text" required id="nif" class="form-control form-control-sm">
            </div>
            <div class="col-md-6">
              <label for="stat" class="form-label">STAT</label>
              <input name="stat" type="text" required id="stat" class="form-control form-control-sm">
            </div>
          </div>
        </div>

        <div class="mb-0">
          <label for="cif" class="form-label">CIF</label>
          <input type="text" required id="cif" class="form-control form-control-sm">
        </div>
        <div class="mb-0">
          <label for="slogan" class="form-label">Slogan</label>
          <textarea name="slogan" class="form-control" id="slogan" cols="30" rows="2"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary w-100">Mettre &agrave; jour</button>
      </div>
    </div><!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->