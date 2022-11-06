<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 08/08/2022
  Time: 07:55
  To change this template use File | Settings | File Templates.
--%>
<!-- Small modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="modal fade" id="modal-qtt-alert-stock" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered">
        <div class="modal-content was-validated">
            <div class="modal-header">
                <h4 class="modal-title" id="mySmallModalLabel">Quantité alert stock</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <input type="number" required id="input-quantite-stock" class="form-control" placeholder="valeur en stock"
                       aria-label="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                <button type="button" class="btn-enregistrer-qtt-alert btn btn-primary">Enregistrer</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
