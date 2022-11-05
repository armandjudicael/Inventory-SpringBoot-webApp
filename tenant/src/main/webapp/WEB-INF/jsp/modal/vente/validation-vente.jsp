<!-- Small modal -->
<div class="modal fade" id="validation-vente-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="mySmallModalLabel">Validation vente</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <li id="nb-article"></li>
        <li id="montant-vente"></li>
        <li id="remise-partiel"></li>
        <li id="remise-global"></li>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button type="button" id="btn-remise-global" class="btn btn-primary"><i class="uil-dollar-alt"></i>remise global </button>
        <button id="enregistrer-vente-btn" type="button" class="btn-enregistrer-modal btn btn-primary">Enregistrer</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
