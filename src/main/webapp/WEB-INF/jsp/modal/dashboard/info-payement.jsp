<%--
  Created by IntelliJ IDEA.
  User: tombo augustin
  Date: 09/10/2022
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<div class="modal fade" id="modal-info-payement-dette" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title" id="mySmallModalLabel">Payement du dette</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 m-2">
                        <div class="row">
                            <div class="d-inline-flex float-start ">

                                <div class="text-end mt-1 me-1 label-count-footer bg-info">
                                    <span> Montant facture : <span class="montant-avec-remise" nbs></span> Ar</span>
                                </div>

                                <div class="text-end mt-1 me-1 label-count-footer bg-info">
                                    <span> Montant total payé : <span class="montant-total-payer" nbs></span> Ar</span>
                                </div>

                                <div class="text-end mt-1 me-1 label-count-footer bg-primary">
                                    <span> Montant restant : <span class="montant-total-restant" nbs></span> Ar</span>
                                </div>

                            </div>
                            <div class=" mt-2 col-md-12">
                                <table id="table-liste-payement" class="table table-hover table-striped norwap table-sm dt-responsive">
                                    <thead>
                                        <th>réference</th>
                                        <th>Montant(Ar)</th>
                                        <th>Type de payement</th>
                                        <th>Date de payement</th>
                                        <th>status payement</th>
                                        <th>Operateur</th>
                                        <th>Action</th>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>

                            <div class="btn-group-footer d-flex">
                                <button type="button" class="btn btn-new-payement btn-success m-1"><i class="uil-plus"></i> Nouveau payement</button>
                                <button type="button" class="btn btn-refresh-payement-list btn-success m-1"><i class="uil-refresh"></i>Actualiser</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </form><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
