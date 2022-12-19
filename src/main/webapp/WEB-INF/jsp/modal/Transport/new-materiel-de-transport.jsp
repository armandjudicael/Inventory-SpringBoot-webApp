<%--<!-- Standard modal -->--%>
<%--<div id="nouveau-materiel-de-transport" class="modal fade" tabindex="-1" role="dialog"--%>
<%--     aria-labelledby="standard-modalLabel"--%>
<%--     aria-hidden="true">--%>
<%--  <div class="modal-dialog">--%>
<%--      <div class="modal-content was-validated">--%>
<%--          <div class="modal-header">--%>
<%--            <h4 class="modal-title" id="standard-modalLabel">Nouveau Mat&eacute;riel Transport</h4>--%>
<%--        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>--%>
<%--      </div>--%>
<%--      <div class="modal-body">--%>
<%--          <div class="mb-1">--%>
<%--                  <label for="reference" class="form-label">Reference</label>--%>
<%--                  <input name="reference" type="text" id="reference" class="form-control">--%>
<%--          </div>--%>
<%--        <div class="mb-1">--%>
<%--          <label for="type-materiel" class="form-label">Type de mat&eacute;riel</label>--%>
<%--          <select required class="form-select" id="type-materiel">--%>
<%--              <option value="CAMION">Camion</option>--%>
<%--              <option value="AVION">Avion</option>--%>
<%--              <option value="BATEAU">Bateau</option>--%>
<%--              <option value="TRAIN">Train</option>--%>
<%--              <option value="AUTRE">Autre</option>--%>
<%--          </select>--%>
<%--        </div>--%>
<%--        <div class="mb-1">--%>
<%--          <label for="select-responsable" class="form-label"> Responsable </label>--%>
<%--          <div class="input-group">--%>
<%--            <select required class="form-select" id="select-responsable">--%>
<%--              <c:forEach var="resp" items="${responsables}">--%>
<%--                <option value="${resp.id}">${resp.nom}</option>--%>
<%--              </c:forEach>--%>
<%--            </select>--%>
<%--            <a role="button" class="btn btn-primary" data-bs-toggle="modal" dat data-bs-target="#nouveau-responsable-mt-modal"  id="btn-nouveau-fournisseur"><i class="uil-plus"></i></a>--%>
<%--          </div>--%>
<%--        </div>--%>
<%--      </div>--%>

<%--          <div class="mb-3 mb-3">--%>
<%--                <div class="form-check">--%>
<%--                    <input type="checkbox" class="form-check-input" id="check-statut" checked>--%>
<%--                    <label class="form-check-label" for="check-statut"> Location </label>--%>
<%--                </div>--%>
<%--          </div>--%>

<%--      <div class="modal-footer">--%>
<%--        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>--%>
<%--        <button id="btn-enregistrer-materiel-de-transport" data-bs-dismiss="modal" type="button"--%>
<%--                class="btn btn-primary">Enregistrer--%>
<%--        </button>--%>
<%--      </div>--%>

<%--    </div><!-- /.modal-content -->--%>
<%--  </div><!-- /.modal-dialog -->--%>
<%--</div>--%>
<%--<!-- /.modal -->--%>
<!-- Standard modal -->
<div id="nouveau-materiel-de-transport" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="standard-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="standard-modalLabel">Nouveau Mat&eacute;riel Transport</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="mb-1">
                    <label for="reference" class="form-label">Reference</label>
                    <input name="reference" type="text" id="reference" class="form-control">
                </div>
                <div class="mb-1">
                    <label for="type-materiel" class="form-label">Type de mat&eacute;riel</label>
                    <select required class="form-select" id="type-materiel">
                        <option value="CAMION">Camion</option>
                        <option value="AVION">Avion</option>
                        <option value="BATEAU">Bateau</option>
                        <option value="TRAIN">Train</option>
                        <option value="AUTRE">Autre</option>
                    </select>
                </div>
                <div class="mb-1">
                    <label for="select-responsable" class="form-label"> Responsable </label>
                    <div class="input-group">
                        <select required class="form-select" id="select-responsable">
                            <c:forEach var="resp" items="${responsables}">
                                <option value="${resp.id}">${resp.nom}</option>
                            </c:forEach>
                        </select>
                        <a role="button" class="btn btn-primary" data-bs-toggle="modal" dat data-bs-target="#nouveau-responsable-mt-modal"  id="btn-nouveau-fournisseur"><i class="uil-plus"></i></a>
                    </div>
                </div>
                <div class="mb-1">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="check-statut" checked>
                        <label class="form-check-label" for="check-statut"> Location </label>
                    </div>
                </div>

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                <button id="btn-enregistrer-materiel-de-transport" data-bs-dismiss="modal" type="button"
                        class="btn btn-primary">Enregistrer
                </button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->