<!-- Standard modal -->
<div id="nouveau-voyage" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Voyage</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-reference1">
          <label for="reference" class="form-label">Reference</label>
          <input name="reference" type="text" id="reference" class="form-control">
        </div>
        <div class="mb-1">
          <label for="materielDeTransport" class="form-label">Materiel de transport</label>
          <select name="materielDeTransport" class="form-control" id="materielDeTransport">
            <option value="1">Auto</option>
            <option value="2">Moto</option>
          </select>
        </div>
        <div class="mb-1">
          <label for="dateVoyage" class="form-label">Date de voyage</label>
          <input name="dateVoyage" type="date" id="dateVoyage" class="form-control">
        </div>
        <div class="mb-1">
          <label for="description" class="form-label">Description du voyage</label>
          <textarea name="description" class="form-control" id="description" cols="30" rows="3"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-voyage" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->