<!-- Large modal -->
<div class="modal fade" id="modal-liste-client" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <form class="modal-content bg-light text-dark">
      <div class="modal-header">
        <h4 class="modal-title" id="myLargeModalLabel">Client</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <input type="text" required id="input-client-search" name="example-input-large" class="form-control w-100 d-block"
                 placeholder="Client &agrave; recherch&eacute;">
        </div>
        <hr>
        <%= start_content_table() %>
        <table id="table-liste-client" class="table table-special-form table-sm dt-responsive nowrap w-100">
          <thead>
          <tr>
            <th>Nom & Prenoms</th>
            <th>Adresse</th>
            <th>Contact</th>
          </tr>
          </thead>
          <tbody>
<%--          <c:forEach var="cf" items="${cfList}">--%>
<%--            <tr id="${cf.id}">--%>
<%--              <td><c:out value="${cf.nom}"/></td>--%>
<%--              <td><c:out value="${cf.adresse}"/></td>--%>
<%--              <td><c:out value="${cf.numTel}"/></td>--%>
<%--            </tr>--%>
<%--          </c:forEach>--%>
          </tbody>
        </table>
        <%= end_content_table()%>
        <br>
        <div class="text-end float-end mt-1 label-count-footer">
          <span><span class="text-count-client-vente">00</span> client enregistrés</span>
        </div>
      </div>
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->