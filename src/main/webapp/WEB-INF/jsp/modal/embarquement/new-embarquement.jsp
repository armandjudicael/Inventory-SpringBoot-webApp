<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 24/09/2022
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->

<%@ page contentType="text/html; charset=UTF-8" %>
<div id="nouveau-embarquement" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg ">

    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="nouveau-embarquement-title">Nouveau embarquement</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>

      <div class="modal-body">

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
          <label for="input-moyen-de-transport" class="form-label">Moyen de transport</label>
          <div class="input-group">
            <select required class="form-select" id="input-moyen-de-transport">
              <c:forEach var="boat" items="${boats}" >
                <option value="${boat.id}">${boat.reference}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="mb-1">
          <label for="input-moyen-de-transport" class="form-label">Status embarquement</label>
          <div class="input-group">
            <select required class="form-select" id="status-embarquement">
                <option value="0"> En cours de chargement </option>
                <option value="1"> Chargement Terminé </option>
                <option value="2"> En mer (route)  </option>
                <option value="3"> Arrivé a destination </option>
            </select>
          </div>
        </div>

        <div class="mb-1">
          <label for="observation" class="form-label">Observation</label>
          <textarea id="observation"  class = "form-control" rows = "3"> </textarea>
        </div>

      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
        <button id="enregistrer-update-voyage-btn" type="submit" class="btn btn-primary">Enregistrer</button>
      </div>

    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
