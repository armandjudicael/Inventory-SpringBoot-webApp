<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: tombo augustin--%>
<%--  Date: 18/08/2022mini--%>
<%--  Time: 09:11--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<!-- Standard modal -->--%>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<%--<div id="info-embarquement" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"--%>
<%--     aria-hidden="true">--%>
<%--    <div class="modal-dialog modal-full-width modal-dialog-scrollable">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h4 class="modal-title" id="num-facture"></h4>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <div class="row">--%>
<%--                    <div class="col-md-12">--%>
<%--                        <div class="page-title-box">--%>
<%--                            <div class="page-title-right">--%>
<%--                                <div class="d-flex">--%>
<%--                                    <a class="btn btn-success btn-export-to-excel-info bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>--%>
<%--                                    <div class="input-group">--%>
<%--                                        <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="top-search-iav">--%>
<%--                                        <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="page-title-left" >--%>
<%--                                <a type="button" class="btn btn-outline-secondary mr-1 crud-client btn-nouveau-dette"--%>
<%--                                   data-bs-target="#nouveau-article-embarquement"--%>
<%--                                   data-bs-toggle="modal"><i class="uil-plus"></i>Ajouter article</a>--%>
<%--                                <a type="button" id="refresh-item-voyage-btn" class="btn btn-success mr-1 btn-mobile"><i class="uil-refresh">&nbsp;</i>Actualiser</a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <br>--%>
<%--                        <%= start_content_table() %>--%>
<%--                        <table id="table-liste-article-embarquement-info" class="table table-sm table-centered mb-0">--%>
<%--                            <thead>--%>
<%--                                <tr>--%>
<%--                                    <th>N° facture</th>--%>
<%--                                    <th>Fournisseur</th>--%>
<%--                                    <th>Article</th>--%>
<%--                                    <th>Unite</th>--%>
<%--                                    <th>Quantite</th>--%>
<%--                                    <th>Poids</th>--%>
<%--                                    <td>Prix d'achat (Ar) </td>--%>
<%--                                    <td class="d-none marge-beneficiaire">Prix de vente (Ar)</td>--%>
<%--                                    <td class="d-none marge-beneficiaire">Marge (Ar)</td>--%>
<%--                                    <td>Utilisateur</td>--%>
<%--                                    <td>Date enregistrement </td>--%>
<%--                                    <td>Date peremption</td>--%>
<%--                                    <td>Prix Transport (Ar)</td>--%>
<%--                                    <td>Camion</td>--%>
<%--                                    <td>Chauffeur</td>--%>
<%--                                    <td>Action</td>--%>
<%--                                </tr>--%>
<%--                            </thead>--%>
<%--                            <tbody>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
<%--                        <%= end_content_table() %>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="modal-footer d-inline-flex">--%>
<%--                &lt;%&ndash;                <button type="button" class="btn btn-primary w-100 m-0"><i class="uil-print"></i>&nbsp;Imprimer</button>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div><!-- /.modal-content -->--%>
<%--    </div><!-- /.modal-dialog -->--%>
<%--</div>--%>
<%--<!-- /.modal -->--%>
<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 18/08/2022mini
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<!-- Standard modal -->
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="info-embarquement" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-full-width modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="num-facture"></h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <div class="d-flex">
                                    <a class="btn btn-success btn-export-to-excel-info bg-forest btn-40"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
                                    <div class="input-group">
                                        <input type="text" required class="form-control dropdown-toggle btn-40" placeholder="Search..." id="top-search-iav">
                                        <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
                                    </div>
                                </div>
                            </div>
                            <div class="page-title-left" >
                                <a type="button" class="btn btn-outline-secondary mr-1 crud-client btn-nouveau-dette"
                                   data-bs-target="#nouveau-article-embarquement"
                                   data-bs-toggle="modal"><i class="uil-plus"></i>Ajouter article</a>
                                <a type="button" id="refresh-item-voyage-btn" class="btn btn-success mr-1 btn-mobile btn-40"><i class="uil-refresh">&nbsp;</i>Actualiser</a>
                                <div class="float-end mt-3">
                                    <label for="filtre-table" class="form-label">Filtre tableau</label>

                                    <select name="filtre-table" id="filtre-table" class="selectpicker" multiple data-live-search="true">
                                        <option class="t1" selected>N° facture</option>
                                        <option class="t2" selected>Fournisseur</option>
                                        <option class="t3" selected>Article</option>
                                        <option class="t4" selected>Unite</option>
                                        <option class="t5" selected>Quantite</option>
                                        <option class="t6" selected>Poids</option>
                                        <option class="t7" selected>Prix d'achat (Ar) </option>
                                        <option class="t8" selected>Prix de vente (Ar)</option>
                                        <option class="t9" selected>Marge (Ar)</option>
                                        <option class="t10" selected>Utilisateur</option>
                                        <option class="t11" selected>Date enregistrement </option>
                                        <option class="t12" selected>Date peremption</option>
                                        <option class="t13" selected>Prix Transport (Ar)</option>
                                        <option class="t14" selected>Camion</option>
                                        <option class="t15" selected>Chauffeur</option>
                                    </select>

                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <br>
                    <div class="row p-0 m-0">
                        <div class="col-md-12">
                            <br>
                            <%= start_content_table() %>
                            <table id="table-liste-article-embarquement-info" class="table table-sm table-centered mb-0">
                                <thead>
                                <tr>
                                    <td class="t1">N° facture</td>
                                    <td class="t2">Fournisseur</td>
                                    <td class="t3">Article</td>
                                    <td class="t4">Unite</td>
                                    <td class="t5">Quantite</td>
                                    <td class="t6">Poids</td>
                                    <td class="t7">Prix d'achat (Ar) </td>
                                    <td class="t8 d-none marge-beneficiaire">Prix de vente (Ar)</td>
                                    <td class="t9 d-none marge-beneficiaire">Marge (Ar)</td>
                                    <td class="t10">Utilisateur</td>
                                    <td class="t11">Date enregistrement </td>
                                    <td class="t12">Date peremption</td>
                                    <td class="t13">Prix Transport (Ar)</td>
                                    <td class="t14">Camion</td>
                                    <td class="t15">Chauffeur</td>
                                    <td>Action</td>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <%= end_content_table() %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer d-inline-flex">
                <%--                <button type="button" class="btn btn-primary w-100 m-0"><i class="uil-print"></i>&nbsp;Imprimer</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->