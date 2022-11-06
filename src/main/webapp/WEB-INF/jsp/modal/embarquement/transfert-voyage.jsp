<!-- Standard modal -->
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="transfert-voyage-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">

    <form class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel"> Transfert Article dans magasin </h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>

      <div class="modal-body">

        <div class="mb-3">

          <h4> Fournisseur : <span class="fournisseur-transfert"> </span> </h4>
          <h4> Facture N° : <span class="facture-transfert"> </span> </h4>
          <h4> Article : <span class="article-transfert"> </span> </h4>
          <h4> Unité : <span class="unite-transfert"> </span> </h4>
          <h4> Quantité : <span class="quantite-transfert"> </span> </h4>
          <h4 class="marge-beneficiaire" > Prix de vente : <span class="prix-vente-transfert"> </span> Ar </h4>
          <h4> Date de peremption : <span class="peremption-transfert"> </span></h4>
          <h4> Prix de achat : <span class="prix-achat-transfert"> </span> Ar </h4>
          <h4> Prix de de transport : <span class="prix-transport-transfert"> </span> Ar </h4>
          <h4 class="marge-beneficiaire" > Marge commercial : <span class="marge-commercial"> </span> </h4>

          <br>
          <div class="mb-3 div-select-magasin ">
            <label for="select-magasin" class="form-label">Magasin</label>
            <select  class="form-select" id="select-magasin">
            </select>
          </div>

          <div class="mb-3">
            <label for="input-quantite-article-voyage" class="form-label">Quantité a transferer</label>
            <input type="number"  class="form-control" name="quantite" id="input-quantite-article-voyage" placeholder="0" value="0">
          </div>

        </div>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="enregistrer-transfert-voyage" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>

    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
