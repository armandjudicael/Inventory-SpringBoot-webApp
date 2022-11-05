<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 13/08/2022
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<!-- Large modal -->
<div class="modal fade" id="modal-liste-article" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content bg-light text-dark">
            <div class="modal-header">
                <h4 class="modal-title" id="myLargeModalLabel">Article</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="d-flex">
                    <input type="text" id="input-article-search" name="example-input-large" class="form-control w-100 d-block" placeholder="article &agrave; recherch&eacute;">
                </div>
                <hr>
                <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
                <%= start_content_table() %>
                <table id="table-liste-article" class="table table-special-form table-sm dt-responsive nowrap w-100">
                    <thead>
                        <tr>
                            <th>Designation</th>
                            <th>Unite</th>
                            <th class="quantite-niveau">Quantite(Niveau)</th>
                            <th class="quantite-stock" >Quantité en stock </th>
                            <th>Prix de vente </th>
                            <th>Poids </th>
                        </tr>
                    </thead>
                    <tbody>
<%--                    <c:forEach var="au" items="${articles}">--%>
<%--                        <tr id ="${au.itemId}-${au.uniteId}">--%>
<%--                            <td>${au.itemName}</td>--%>
<%--                            <td value-id ="${au.uniteId}">${au.uniteName}</td>--%>
<%--                            <td>${au.quantiteNiveau}</td>--%>
<%--                            <td>${au.stock}</td>--%>
<%--                            <td>${au.price}</td>--%>
<%--                            <td>${au.poids}</td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
                    </tbody>
                </table>
                <%= end_content_table() %>
                <br>
                <div class="text-end float-end mt-1 label-count-footer">
                    <span><span class="text-count-article-entree">00</span> article enregistrés</span>
                </div>
            </div>
        </div>
    </div>
</div>