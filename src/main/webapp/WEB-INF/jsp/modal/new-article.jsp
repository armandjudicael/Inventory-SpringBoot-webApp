<!-- Standard modal -->
<div id="new-article" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Article</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label for="designation" class="form-label">Designation</label>
          <input name="designation" type="text" id="designation" class="form-control">
        </div>
        <div class="mb-3">
          <label for="categorie" class="form-label">Categorie</label>
          <select name="categorie" class="form-select" id="categorie">
          </select>
        </div>
          <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
          <input type="hidden" id="user-id" value-id="${connectedUser.id}">
        <div class="mb-3">
          <label for="table-unite" class="form-label text-underline">Unite</label>
          <table id="table-unite"
                 class="table w-100 d-block table-unit table-bordered border-light table-centered table-hover">
            <thead>
            <tr>
              <th>Niveau</th>
              <th>Designation</th>
              <th>Quantite</th>
              <th>Poids(Kg)</th>
              <th>Action</th>
            </tr>
            <tr>
            </tr>
            </thead>
            <tbody>
            <tr class="default-unite">
              <td><input type="text" class="form-control input-sm not-editable" value="1"></td>
              <td><input type="text" class="form-control input-sm" value="designation"></td>
              <td><input type="text" class="form-control input-sm not-editable" value="1"></td>
              <td><input type="text" class="form-control input-sm" value="1"></td>
              <td class="d-inline-flex">
                <a class="btn btn-primary btn-sm btn-edit-unite"><i class="uil-pen"></i></a>&nbsp;
                <a class="btn btn-success btn-sm btn-add-unite"><i class="uil-check-square"></i></a>
              </td>
            </tr>
            </tbody>
          </table>
          <div class="d-flex justify-content-end">
            <a class="btn btn-success" id="btn-new-unite"><i class="uil-plus"></i></a>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button type="submit" id="saveArticleBtn" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->