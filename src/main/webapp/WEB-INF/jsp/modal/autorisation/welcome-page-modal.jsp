<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 09/09/2022
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="welcome-page-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <form class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="standard-modalLabel">Changer page d'acceuil fonction </h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <label for="welcome-page-select"  class="form-label"> Page  </label>
                <select class="form-select" id="welcome-page-select">
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                <button id="save-welcome-page-btn" type="submit" class="btn btn-primary">Enregistrer</button>
            </div>
        </form><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->