<!-- Small modal -->
<div class="modal fade" id="modal-payement-dette" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="mySmallModalLabel">Payement du dette</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1 col-md-12">
          <label for="Montant-payer" class="form-label">Montant a payer</label>
          <input type="number" class="form-control" id="Montant-payer" name="montantPayer">
        </div>
        <div class="mb-1 col-md-12">
          <label for="type-paiement" class="form-label">Type de payement</label>
          <select type="date" class="form-control" id="type-paiement" name="typePayement">

          </select>
        </div>
        <div class="mb-1 col-md-12">
          <label for="type-paiement" class="form-label">Description de payement</label>
          <textarea name="descriptionPayement" id="description-payement" class="form-control" cols="30" rows="3"></textarea>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button type="submit" class="btn-enregistrer-payement-dette btn btn-success"> <i class="uil-money-withdrawal"></i> &nbsp; Payer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
