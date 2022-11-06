<%@ include file="template/head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<i class="no-title" title="Vente"></i>
<div class="row d-flex justify-content-center align-items-center bg-ligth" id="menu-vente" data-plugin="dragula">
  <div class="col-md-11 col-sm-11">
    <div class="card mt-3 mb-3 bg-light">
      <form id="form-vente" class="card-body bg-light text-dark">
        <blockquote class="card-bodyquote mb-0">
          <!-- vente content -->
          <div class="row form-vente">
            <h4>VENTE</h4>
            <hr>
            <br>
            <input type="hidden" id="user-id" value-id="${connectedUser.id}" value-name="${connectedUser.username}">
            <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
            <div class="col-lg-5">
              <div class="mb-1">
                <label for="select-magasin" class="form-label">Magasin</label>
                <select class="form-select" id="select-magasin">
                  <c:forEach var="magasin" items="${magasins}">
                    <option value="${magasin.id}"> <c:out value="${magasin.nomMagasin}"/> </option>
                  </c:forEach>
                </select>
              </div>
              <div class="mb-1">
                <label class="form-label">Client</label>
                <div class="input-group">
                  <input id="name-client" disabled name="nameClient" type="text" class="form-control" placeholder="Nom du client"
                         aria-label="Recipient's username">
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                          data-bs-target="#modal-liste-client" id="btn-search-client"><i class="uil-search"></i>
                  </button>
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                          data-bs-target="#nouveau-client" id="btn-nouveau-client"><i
                          class="uil-plus-circle"></i></button>
                </div>
              </div>
              <hr>
              <br>
              <div class="mt-1 d-inline-flex d-none">
                <div class="form-check">
                  <input type="checkbox" class="form-check-input" id="check-bon">
                  <label class="form-check-label" for="check-bon">Bon</label>
                </div>&nbsp;&nbsp;&nbsp;
                <div class="form-check ml-1">
                  <input type="checkbox" class="form-check-input" id="check-a-livrer">
                  <label class="form-check-label" for="check-a-livrer">A livrer</label>
                </div>
              </div>
              <div class="mb-1">
                <label class="form-label">Designation</label>
                <div class="input-group">
                  <input name="designation" disabled type="text" class="form-control" placeholder="Nom de l'article"
                         aria-label="Recipient's username" id="designation-article">
                  <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                          data-bs-target="#modal-liste-article" id="btn-search-article"><i class="uil-search"></i>
                  </button>
                  <button type="button" class="btn btn-primary" id="btn-scan-barcode"><i class="fa fa-barcode"></i>
                  </button>
                </div>
              </div>

              <div class="row g-2">
                <div class="mb-1 col-md-6">
                  <label for="input-quantite-article" class="form-label">Quantite</label>
                  <input name="inputQuantiteArticle" type="number" class="form-control" id="input-quantite-article" value="0">
                </div>
                <div class="mb-1 col-md-6">
                  <label for="input-unite-article" class="form-label">Unite</label>
                  <select name="unite" class="form-select" id="input-unite-article">
                  </select>
                </div>
              </div>

              <daiv class="mb-1">
                <label class="form-label">Prix Unitaire (Ar)</label>
                <div class="input-group">
                  <input name="inputPrixUnitaire" id="input-prix-unitaire" disabled type="number" class="form-control" value="0">
                  <button type="button" id="btn-prix-special" class="btn btn-primary"><i class="uil-dollar-alt"></i>&nbsp;Prix special
                  </button>
                </div>
              </daiv>

              <div class="d-grid mb-1">
                <button type="input" class="btn btn-ajouter-article-vente btn-success mb-1 mt-3"><i
                        class="uil-plus"></i>&nbsp;Ajouter
                </button>
                <button type="button" class="btn btn-enregistrer-vente btn-primary mb-1"><i class="uil-save"></i>Enregistrer</button>
              </div>
              <!-- end d-grid -->
            </div>
            <div class="col-lg-7">
              <%= start_content_table() %>
              <table id="table-liste-article-vente" class="table table-special-form table-sm table-centered mb-0">
                <thead>
                <tr>
                  <th>Article</th>
                  <th>Unite</th>
                  <th>Quantite</th>
                  <th>Prix Unitaire</th>
                  <th>Montant</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
              <%= end_content_table() %>
            </div>
            <div class="d-flex justify-content-end text-decoration-underline">
              <p class="m-1">Nombre d'article : <span class="label-nombre-article">00</span></p>
              <p class="m-1">
              <p class="m-1">Somme : <span class="label-somme-ariary">0</span>Ar (<span class="label-somme-fmg">0</span>Fmg)</p>
              </p>
            </div>
          </div>
          <!-- end vente content -->
        </blockquote>
      </form> <!-- end card-body-->
    </div> <!-- end card-->
  </div> <!-- end col-->
  <!-- modal list -->
  <div class="all-modal">
    <div class="temporally"></div>
    <%@ include file="modal/vente/validation-vente.jsp" %>
    <%@ include file="modal/vente/prix-special.jsp" %>
    <%@ include file="modal/vente/prix-special-article.jsp" %>
    <%@ include file="modal/vente/list-article-info.jsp" %>
    <%@ include file="modal/vente/list-client.jsp" %>
    <%@ include file="modal/client/new-client.jsp" %>
    <%@ include file="modal/impression/ticket-caisse.jsp" %>
    <%@ include file="modal/impression/facture-vente.jsp" %>
  </div>
  <!-- end modal list -->
</div>

<%@ include file="template/setting.jsp" %>