<!-- Standard modal -->
<div id="nouveau-dette" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau Dette</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="row g-2">
          <div class="mb-1 col-md-6">
            <label for="date-dette" class="form-label label-date-dette">Date du dette</label>
            <input type="date" class="form-control" id="date-dette" name="dateDette">
          </div>
          <div class="mb-1 col-md-6">
            <label for="date-echeance" class="form-label">Date Echeance</label>
            <input type="date" class="form-control" id="date-echeance" name="dateEcheance">
            </select>
          </div>
        </div>
        <div class="mb-1">
          <label for="somme" class="form-label">Montant</label>
          <input name="montant" type="number" id="somme" class="form-control">
        </div>
        <div class="mb-1">
          <label for="description" class="form-label">Description</label>
          <textarea name="description" id="description" class="form-control"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-dette-fournisseur" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->