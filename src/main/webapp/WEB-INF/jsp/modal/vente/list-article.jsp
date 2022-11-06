<!-- Large modal -->
<div class="modal fade" id="modal-liste-article" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content bg-light text-dark">
      <div class="modal-header">
        <h4 class="modal-title" id="myLargeModalLabel">Article</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <input type="text" id="inpute-article-search" name="example-input-large" class="form-control w-100 d-block"
                 placeholder="article &agrave; recherch&eacute;">
        </div>
        <hr>
        <%= start_content_table() %>
        <table id="table-liste-article" class="table table-special-form table-sm dt-responsive nowrap w-100">
          <thead>
              <tr>
                <th>Designation</th>
                <th>Unite</th>
                <th>quantite(Niveau)</th>
                <th> Poids </th>
              </tr>
          </thead>
          <tbody>
          <c:forEach var="au" items="${articles}">
            <tr id ="${au.article.id}">
              <td>${au.article.designation}</td>
              <td value-id ="${au.unite.id}">${au.unite.designation}</td>
              <td>${au.quantiteNiveau}</td>
              <td>${au.poids}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
        <br>
        <div class="text-end float-end mt-1 label-count-footer">
          <span><span class="text-count-article-entree">00</span> articles enregistrés</span>
        </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->