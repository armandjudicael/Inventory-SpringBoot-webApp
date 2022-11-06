<!-- Small modal -->
<div class="modal fade" id="modal-prix-special" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="remise-title">Prix special</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">

        <div class="d-inline-flex mb-1">
          <div class="form-check">
            <input type="radio" required id="check-prix-special-valeur" name="customRadio" class="form-check-input" checked>
            <label class="form-check-label" for="check-prix-special-valeur">En valeur</label>
          </div>&nbsp;&nbsp;
          <div class="form-check">
            <input type="radio" required id="check-prix-special-remise" name="customRadio" class="form-check-input">
            <label class="form-check-label" for="check-prix-special-remise">Pourcentage (0-100%)</label>
          </div>
        </div>

        <input type="number" id="input-prix-special" class="form-control input-prix-special" placeholder="Entrer le prix sp&eacute;cial"
               aria-label="Recipient's username">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button type="button" class="btn-enregistrer-modal btn btn-primary">Enregistrer</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
