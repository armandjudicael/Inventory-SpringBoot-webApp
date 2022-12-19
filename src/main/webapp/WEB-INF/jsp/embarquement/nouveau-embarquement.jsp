<%@ include file="../template/head.jsp" %>
<i class="no-title" title="Nouveau Embarquement"></i>
<div class="row d-flex justify-content-center align-items-center" id="nouveau-embarquement" data-plugin="dragula">
  <div class="col-md-11">
    <div class="card mb-0 mt-3">
      <div class="card-body was-validated">
        <blockquote class="card-bodyquote mb-0">
          <!-- vente content -->
          <input type="hidden" id="user-id" value-id="${connectedUser.id}" value-name="${connectedUser.username}">
          <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
          <div class="row">
            <h4 class="label-title">Nouveau Embarquement</h4>
            <hr>
            <div class="col-lg-4 form-embarquement">
              <div class="mb-1">
                <label for="input-reference" class="form-label">Reference</label>
                <input type="text" required id="input-reference" class="form-control" placeholder="2022/001">
              </div>
              <div class="mb-1">
                <label for="input-moyen-de-transport" class="form-label">Moyen de transport</label>
                <div class="input-group">
                  <select required class="form-select" id="input-moyen-de-transport">
                    <c:forEach var="mat_transport" items="${materiel_transportList}" >
                      <option value="${mat_transport.id}">${mat_transport.reference}</option>
                    </c:forEach>
                  </select>
                  <a type="button" role="button" class="btn btn-primary" id="btn-nouveau-moyen-de-transport"><i class="uil-plus"></i></a>
                </div>
              </div>
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
                <label for="select-trajet" class="form-label">Trajet</label>
                <div class="d-flex" >
                  <select required class="form-select" id="select-trajet">
                    <c:forEach var="trajet" items="${trajets}" >
                      <option value="${trajet.id}">${trajet.depart} - ${trajet.destination}</option>
                    </c:forEach>
                  </select>
                  <a role="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#nouveau-trajet" id="btn-nouveau-trajet"><i class="uil-plus"></i></a>
                </div>
              </div>

              <div class="mb-1">
                <label for="input-facture" class="form-label">Observation</label>
                <textarea class = "form-control" rows = "3"> </textarea>
              </div>

              <hr>

              <div class="mb-1">
                <label for="input-facture" class="form-label">Facture</label>
                <input type="text" required id="input-facture" class="form-control" placeholder="reference facture">
              </div>
              <hr>

              <div class="mb-1">
                <label for="input-moyen-de-transport" class="form-label">Camion</label>
                <div class="input-group">
                  <select required class="form-select" id="input-camion">
                    <c:forEach var="mat_transport" items="${materiel_transportList}" >
                      <option value="${mat_transport.id}">${mat_transport.reference} - ${mat_transport.responsable.nom}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

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
                  <label class="form-label">Frais de transport</label>
                  <div class="input-group">
                    <input type="text" required id="input-prix-vente-article" class="form-control" placeholder="0 Ar" aria-label="Recipient's username">
                  </div>
                </div>

              </div>

              <div class="mb-2 mt-2 peremption" >
                <label for="input-date-peremption">Date de peremption</label>
                <input type="date" name="datePeremption" class="form-control" id="input-date-peremption">
              </div>

              <div class="d-grid">
                <button type="button" class="btn btn-success mb-1 btn-ajouter-article"><i class="uil-plus"></i>&nbsp;Ajouter</button>
                <button type="button" class="btn btn-primary mb-1 btn-enregistrer-embarquement"><i class="uil-save"></i>Enregistrer</button>
              </div>
              <!-- end d-grid -->
            </div>

            <div class="col-lg-8">

              <table id="table-liste-article-embarquement" class="table table-sm table-centered mb-0">
                <thead>
                        <tr>
                          <th>Article</th>
                          <th>Unite</th>
                          <th>Quantite</th>
                          <th>Poids</th>
                          <td>Prix de transport</td>
                          <td>Prix d'achat</td>
                          <td>N° camion</td>
                          <td>Chauffeur</td>
                        </tr>
                </thead>
                <tbody>

                </tbody>
              </table>

              <div class="foot-vente d-flex justify-content-end d-none">
                <p>Nombre d'article : <span>00</span></p>&nbsp;
                <p class="">
                <p>0T</p>
                </p>
              </div>
                      <!-- modal list -->
                        <div class="all-modal">
                              <%@ include file="../modal/Transport/new-materiel-de-transport.jsp" %>
                              <%@ include file="../modal/embarquement/nouveau-trajet.jsp" %>
                              <%@ include file="../modal/fournisseur/new-fournisseur.jsp" %>
                              <%@ include file="../modal/vente/list-article.jsp" %>
                              <%@ include file="../modal/vente/prix-special.jsp" %>
                              <%@ include file="../modal/new-chauffeur.jsp" %>
                      <!-- end modal list -->
                          </div>
            </div>
          </div>
          <!-- end vente content -->
        </blockquote>
      </div> <!-- end card-body-->
    </div> <!-- end card-->
  </div> <!-- end col-->
</div>
<%@ include file="../template/setting.jsp" %>