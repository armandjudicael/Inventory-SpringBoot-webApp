<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 23/08/2022
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="mode-payement-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered ">
        <form class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="standard-modalLabel">Nouveau payement</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="mt-2">
                    <label for="description" class="form-label">Type de payement</label>
                    <select class="form-select" id="type-payement">
                        <option value="CREDIT">Credit</option>
                        <option value="ESPECE">Espèces</option>
                        <option value="MOBILE_MONEY">Mobile Money</option>
                        <option value="CHEQUE">Chèques</option>
                        <option value="VIREMENT">Virement</option>
                    </select>
                </div>

                <div class="mt-2">
                        <label for="montant" class="form-label">Montant</label><br>
                        <div class="d-inline-flex mb-1">
                            <div class="form-check">
                                <input type="radio" required id="montant-valeur" name="customRadio" class="form-check-input" checked>
                                <label class="form-check-label" for="montant-valeur">En valeur</label>
                            </div>&nbsp;&nbsp;
                            <div class="form-check">
                                <input type="radio" required id="montant-pourcentage" name="customRadio" class="form-check-input">
                                <label class="form-check-label" for="montant-pourcentage">Pourcentage (0-100%)</label>
                            </div>
                        </div>
                        <input type="number" id="montant" class="form-control">
                        <div id="montant-en-pourcentage-div">
                            <p> <span id="pourcentage-entrer" ></span> % * <span class="montant-total-restant" ></span> = <span id="valeur-restant-en-pourentage"></span> Ar</p>
                        </div>
                </div>

                <div class="mt-2" id="credit-echeance">
                    <label for="date-echeance" class="form-label">Date Echeance</label>
                    <input type="date" class="form-control" id="date-echeance" name="date-credit">
                </div>


            <div class="mt-2">
                <label for="description" class="form-label">description</label>
                <textarea name="description" id="description" class="form-control"></textarea>
            </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                <button id="save-payement-mode-btn" type="submit" class="btn btn-primary">Enregistrer</button>
            </div>
        </form><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->