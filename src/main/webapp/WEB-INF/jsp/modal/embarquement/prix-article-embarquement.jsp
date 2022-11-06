<!-- Standard modal -->
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="prix-article-voyage-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel" aria-hidden="true">

    <div class="modal-dialog">

        <form class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title" id="standard-modalLabel"> Prix et marge commercial </h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>

            <div class="modal-body">
                <div class="mb-3">
                    <h4> Fournisseur : <span class="fournisseur-transfert"> </span> </h4>
                    <h4> Facture N° : <span class="facture-transfert"> </span> </h4>
                    <h4> Article : <span class="article-transfert"> </span> </h4>
                    <h4> Unité : <span class="unite-transfert"> </span> </h4>
                    <h4> Quantité : <span class="quantite-transfert"> </span> </h4>
                    <h4> Date de peremption : <span class="peremption-transfert"> </span> </h4>
                    <h4 class="marge-beneficiaire" > Marge commercial : <span class="marge-commercial"> </span> </h4>
                    <br>

                    <div class="mb-3">
                        <label for="input-prix-vente-article-voyage" class="form-label">prix de vente</label>
                        <input type="number"  class="form-control" id="input-prix-vente-article-voyage" placeholder="0" value="0">
                    </div>

                    <div class="mb-3">
                        <label for="input-prix-achat-article-voyage" class="form-label">prix d'achat</label>
                        <input type="number"  class="form-control"  id="input-prix-achat-article-voyage" placeholder="0" value="0">
                    </div>

                    <div class="mb-3">
                        <label for="input-prix-transport-article-voyage" class="form-label">prix de transport/kg</label>
                        <input type="number"  class="form-control"  id="input-prix-transport-article-voyage" placeholder="0" value="0">
                    </div>

                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                <button id="enregistrer-prix-article-voyage" type="submit" class="btn btn-primary">Enregistrer</button>
            </div>

        </form><!-- /.modal-content -->

    </div><!-- /.modal-dialog -->

</div>
<!-- /.modal -->
