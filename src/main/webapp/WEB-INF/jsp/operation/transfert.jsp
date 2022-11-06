<%@ include file="../template/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<i class="no-title" title="Transfert"></i>
<div class="row d-flex justify-content-center align-items-center bg-light text-dark" id="transfert-article"
     data-plugin="dragula">
  <div class="col-md-11">
    <form class="card mb-0 mt-3">
      <div class="card-body bg-light text-dark">
        <blockquote class="card-bodyquote mb-0">
          <!-- vente content -->
          <div class="row">
            <h4>Transfert article</h4>
            <hr>
            <input type="hidden" id="user-id" value-id="${connectedUser.id}" value-name="${connectedUser.username}">
            <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
            <div class="col-lg-5">
              <div class="mt-1 mb-1 select-type-transfert">
                <label for="source-destination" class="form-label">Source - Destination</label>
                <select name="selectSrcDst" class="form-select" id="source-destination" disabled>
                  <option value="MM">Magasin - Magasin</option>
                  <option value="MV">Magasin - Voyage</option>
                  <option value="VM">Voyage - Magasin</option>
                  <option value="VV">Voyage - Voyage</option>
                </select>
              </div>
              <div class="mt-1 mb-1 select-src">
                <label class="form-label">De (Source) : </label>
                <select name="selectMagasinSrc" id="select-magasin-source" class="form-select MM MV refMagasinSrc">
                  <c:forEach var="magasin" items="${magasins}">
                    <option value="${magasin.id}"> <c:out value="${magasin.nomMagasin}"/> </option>
                  </c:forEach>
                </select>
              </div>
              <div class="mt-1 mb-1 select-dst">
                <label class="form-label">&agrave; (Destination) : </label>
                <select name="selectMagasinDst" id="select-magasin-dest" class="form-select MM VM">
                  <c:forEach var="magasin" items="${magasins}">
                    <option value="${magasin.id}"> <c:out value="${magasin.nomMagasin}"/> </option>
                  </c:forEach>
                </select>
              </div>
              <div class="mb-1 d-none">
                <label class="form-label">référence</label>
                <input type="text" name="reference" id="input-reference" class="form-control" value="ref-0">
              </div>
              <br>
              <h4>Ajouter Article
                <hr>
              </h4>
              <div class="mb-1">
                <label class="form-label">Designation</label>
                <div class="input-group">
                  <input id="input-designation-article" type="text" name="iDesignation" class="form-control designation-article" placeholder="Designation article"
                         aria-label="Designation article">
                  <button type="button" class="btn btn-primary btn-chercher-article" data-bs-toggle="modal"
                          data-bs-target="#modal-liste-article"><i class="uil-search"></i></button>
                </div>
              </div>

              <div class="row g-2">
                <div class="mb-1 col-md-6">
                  <label for="input-quantite" class="form-label">Quantite</label>
                  <input type="number" name="iQuantite" class="form-control" id="input-quantite" placeholder="00">
                </div>
                <div class="mb-1 col-md-6">
                  <label for="select-unite" class="form-label">Unite</label>
                  <select name="selectUnite" class="form-select" id="select-unite">
                  </select>
                </div>
              </div>

              <div class="mb-1">
                <label class="form-label">Chauffeur</label>
                <input type="text" name="chauffeur" id="input-chauffeur" class="form-control">
              </div>

              <div class="mb-1">
                <label class="form-label">Description</label>
                <textarea name="description" class="form-control" id="area-description" cols="30" rows="3"></textarea>
              </div>

              <div class="d-grid">
                <button type="submit" class="btn btn-success mb-1 mt-3 btn-ajouter-article"><i class="uil-plus"></i>&nbsp;Ajouter
                </button>
                <button type="button" class="btn btn-primary mb-1 btn-enregistrer-article"><i class="uil-save"></i>Enregistrer
                </button>
              </div>
              <!-- end d-grid -->
            </div>
            <div class="col-lg-7">
              <table class="table-liste-article-transfert table-special-form table table-sm table-centered mb-0">
                <thead>
                <tr>
                  <th>Article</th>
                  <th>Unite</th>
                  <th>Quantite</th>
                  <th>Description</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
              </table>

              <div class="foot-vente d-none">
                <p>Nombre d'article : <span>00</span></p>
              </div>

            </div>

          </div>
          <!-- end vente content -->
        </blockquote>
      </div> <!-- end card-body-->
    </form> <!-- end card-->
  </div> <!-- end col-->


  <div class="all-modal">
    <!-- modal list -->
    <%@ include file="../modal/vente/list-article.jsp" %>
    <%@ include file="../modal/vente/list-client.jsp" %>
    <%@ include file="../modal/vente/prix-special.jsp" %>
    <%@ include file="../modal/impression/bon-entree-ou-sortie.jsp" %>
    <!-- end modal list -->
  </div>

</div>


<%@ include file="../template/setting.jsp" %>