<!-- Large modal -->
<div class="modal fade" id="modal-liste-fournisseur" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <form class="modal-content bg-light text-dark">
      <div class="modal-header">
        <h4 class="modal-title" id="myLargeModalLabel">Fournisseur</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <input type="text" id="inpute-article-search" name="example-input-large" class="form-control w-100 d-block"
                 placeholder="fournisseur &agrave; recherch&eacute;">
        </div>
        <hr>
        <%= start_content_table() %>
        <table id="table-liste-fournisseur" class="table table-special-form table-sm dt-responsive nowrap w-100">
          <thead>
          <tr>
            <th>Nom</th>
            <th>Adresse</th>
            <th>Contact</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="cf" items="${cfList}">
            <tr id="${cf.id}">
              <td><c:out value="${cf.nom}"/></td>
              <td><c:out value="${cf.adresse}"/></td>
              <td><c:out value="${cf.numTel}"/></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table()%>
        <br>
        <div class="text-end float-end mt-1 label-count-footer">
          <span><span class="text-count-fournisseurs-entree">00</span> fournisseurs enregistrés</span>
        </div>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<!-- /.modal -->