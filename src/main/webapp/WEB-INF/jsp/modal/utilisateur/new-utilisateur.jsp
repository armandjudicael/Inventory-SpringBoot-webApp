<!-- Standard modal -->
<div id="nouveau-utilisateur" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau utilisateur</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-1">
          <div class="row">
            <div class="col-12">
              <label for="input-nom" class="form-label">Nom & Prenoms</label>
              <input name="nomEtPrenoms" type="text" id="input-nom" class="form-control">
            </div>
          </div>
          <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
        </div>
        <div class="mb-1">
          <div class="row">
            <div class="col-6">
              <label for="input-adresse" class="form-label">Adresse</label>
              <input name="adresse" type="text" id="input-adresse" class="form-control">
            </div>
            <div class="col-6">
              <label for="input-contact" class="form-label">Contact</label>
              <input name="contact" type="text" id="input-contact" class="form-control">
            </div>
          </div>

        </div>
        <div class="mb-1">
          <div class="row">
            <div class="col-6">
              <label for="select-fonction" class="form-label">Fonction</label>
              <select name="fonction" id="select-fonction" class="form-select">
              </select>
            </div>
            <div class="col-6">
              <label for="select-magasin" class="form-label">Magasin</label>
              <select name="magasin" id="select-magasin" class="selectpicker" multiple data-live-search="true">
                <c:forEach var="magasin" items="${magasins}">
                  <option value="${magasin.id}">
                    <c:out value="${magasin.nomMagasin}"/>
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>

        </div>

        <div id="username-and-password">
          <div class="mb-1">
            <label for="input-username" class="form-label">Nom d'utilisateur</label>
            <input name="username" type="text" id="input-username" class="form-control">
          </div>
          <div class="mb-1">
            <label for="input-password" class="form-label">Mot de passe</label>
            <input name="password" type="password" id="input-password" class="form-control">
          </div>
        </div>

        <div class="mb-1">
          <div class="mt-3">
            <div class="form-check">
              <input type="checkbox" class="form-check-input" id="check-statut" checked="">
              <label class="form-check-label" for="check-statut">Statut utilisateur</label>
            </div>
          </div>
        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="btn-enregistrer-utilisateur" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->