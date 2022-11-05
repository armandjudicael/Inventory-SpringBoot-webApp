<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 24/09/2022
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->
<div id="nouveau-article-embarquement" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">

    <form class="modal-content">

      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Nouveau article (embarquement)</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>

      <div class="modal-body">

        <div class="mb-1">
          <label for="select-fournisseur" class="form-label">Nom du Fournisseur</label>
          <div class="input-group">
            <select required class="form-select" id="select-fournisseur">
              <c:forEach var="frs" items="${cfList_embarquement}" >
                <option value="${frs.id}">${frs.nom}</option>
              </c:forEach>
            </select>
            <a role="button" class="btn btn-primary" id="btn-nouveau-fournisseur"><i class="uil-plus"></i></a>
          </div>
        </div>

        <div class="mb-1">
          <label for="select-camion" class="form-label">Camion</label>
          <div class="input-group">
            <select required class="form-select" id="select-camion">
              <c:forEach var="car" items="${cars}" >
                <option value="${car.id}">${car.reference} - ${car.responsable.nom}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="mb-1">
          <label for="input-facture" class="form-label">Facture</label>
          <input type="text" required id="input-facture" class="form-control" placeholder="reference facture">
        </div>
        <hr>
        <div class="mb-1">
          <label class="designation-article">Designation</label>
          <div class="input-group">
            <input type="text" required id="designation-article" class="form-control" placeholder="Nom de l'article"
                   aria-label="Recipient's username">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#modal-liste-article"><i class="uil-search"></i></button>
          </div>
        </div>

        <div class="row g-2">
          <div class="mb-1 col-md-6">
            <label for="input-quantite-article" class="form-label">Quantite (Poids : <span class="label-poids-article">0</span>T)</label>
            <input type="number" required class="form-control" id="input-quantite-article" placeholder="00">
          </div>
          <div class="mb-1 col-md-6">
            <label for="select-unite-article" class="form-label">Unite</label>
            <select required class="form-select" id="select-unite-article">
            </select>
          </div>

        </div>

        <div class="row g-2">

          <div class="mb-1 col-md-6">
            <label class="form-label">Prix d'achat</label>
            <div class="input-group">
              <input type="text" required class="form-control" id="input-prix-achat-article" placeholder="0 Ar" aria-label="Recipient's username">
            </div>
          </div>

          <div class="mb-1 col-md-6">
            <label class="form-label">Prix de vente</label>
            <div class="input-group">
              <input type="text" required id="input-prix-vente-article" class="form-control" placeholder="0 Ar" aria-label="Recipient's username">
            </div>
          </div>
      </div>

        <div class="mb-1 peremption" >
          <label for="input-date-peremption">Date de peremption</label>
          <input type="date" name="datePeremption" class="form-control" id="input-date-peremption">
        </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="enregistrer-nouveau-article-voyage" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>
      </div>

    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
