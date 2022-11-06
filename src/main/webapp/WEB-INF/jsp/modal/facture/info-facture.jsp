<!-- Standard modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="info-facture" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">

  <div class="modal-dialog modal-lg modal-full-width">

    <div class="modal-content" style="height: 90vh;">

      <div class="modal-header">
        <h4 class="modal-title">Réference : <span id="num-facture"></span></h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <input type="hidden" id="user-id" value-id="${connectedUser.id}">
      <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
      <div class="modal-body">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item" role="Details">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button"
                    role="tab" aria-controls="home" aria-selected="true">Détails
            </button>
          </li>
          <li class="nav-item" role="Artile vendu">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button"
                    role="tab" aria-controls="profile" aria-selected="false">Article vendu
            </button>
          </li>
          <li class="nav-item" role="Payement">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button"
                    role="tab" aria-controls="contact" aria-selected="false">Payements
            </button>
          </li>
        </ul>
        <div class="tab-content" id="Tab-content">
          <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
            <div class="row">
              <div class="col-md-12 m-2">
                <%=start_content_table()%>
                <div class="label">
                  <p> Client : <span id="client-facture"></span></p>
                  <hr>
                  <p>Date facture : <span id="date-facture"></span></p>
                  <hr>
                  <p>Operateur : <span id="operateur-facture"></span></p>
                  <hr>
                  <p>Montant total vente : <span id="montant-facture"></span> Ar</p>
                  <hr>
                  <p>Montant vente avec remise : <span class="montant-avec-remise"></span>Ar</p>
                  <hr>
                  <p>Réference : <span id="reference"></span></p>
                  <hr>
                </div>
                <%= end_content_table() %>
              </div>
            </div>
          </div>

          <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
            <div class="row">
              <div class="col-md-12 m-2">
                <div class="row">
                  <div class="col-md-12">
                    <div class="input-group">
                      <input type="text" required="" class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
                      <%--                      <button id="search-btn" class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>--%>
                    </div>
                    <br>
                    <%= start_content_table() %>
                    <table id="table-facture-avoir"
                           class="table table-hover table-striped norwap table-sm dt-responsive">
                      <thead>
                      <th>
                        <div class="form-checkbox form-checkbox-danger">
                          <input type="checkbox" checked class="form-check-input avoir-checkbox-all">
                        </div>
                      </th>
                      <th>Designation</th>
                      <th>Unité</th>
                      <th>Quantite</th>
                      <th>Prix Unitaire</th>
                      <th>Montant</th>
                      </thead>
                      <tbody>
                      </tbody>
                    </table>
                    <%= end_content_table() %>
                  </div>

                  <div class="btn-group-footer">

                    <button type="button" class="btn btn-creer-avoir btn-danger m-1"><i class="uil-money-withdrawal"></i>&nbsp;Créer avoir</button>

                    <button type="button" class="btn btn-print-facture btn-primary m-1"><i class="uil-print"></i>&nbsp;Imprimer</button>

                    <button class="btn btn-success btn-export-to-excel bg-forest float-end m-1"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</button>
                    <button type="button" class="btn btn-valider-avoir btn-danger float-end m-1"><i class="uil-money-withdrawal"></i>&nbsp;Valider Avoir
                    </button>

                  </div>

                </div>
              </div>
            </div>
          </div>

          <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
            <div class="row">
              <div class="col-md-12 m-2">
                <div class="row">
                  <div class="d-inline-flex float-start ">
                    <div class="text-end mt-1 me-1 label-count-footer bg-info">
                      <span> Montant facture : <span class="montant-avec-remise" nbs></span> Ar</span>
                    </div>
                    <div class="text-end mt-1 me-1 label-count-footer bg-info">
                      <span>
                        Montant total payé : <span class="montant-total-payer" nbs></span> Ar
                        <span class="montant-total-payer-pourcentage" nbs></span>
                      </span>
                    </div>
                    <div class="text-end mt-1 me-1 label-count-footer bg-primary">
                      <span>
                        Montant restant : <span class="montant-total-restant" nbs></span> Ar
                        <span class="montant-total-restant-pourcentage" nbs></span>
                      </span>
                    </div>
                  </div>

                  <div class=" mt-2 col-md-12">
                    <%= start_content_table() %>
                    <table id="table-liste-payement" class="table table-hover table-striped norwap table-sm dt-responsive">
                      <thead>
                          <th>réference</th>
                          <th>Montant(Ar)</th>
                          <th>Montant restant(Ar)</th>
                          <th>Type de payement</th>
                          <th>Date de payement</th>
                          <th>status payement</th>
                          <th>Operateur</th>
                          <th>Action</th>
                      </thead>
                      <tbody>
                      </tbody>
                    </table>
                    <%= end_content_table() %>
                  </div>

                  <div class="btn-group-footer d-flex">
                    <button type="button" class="btn btn-new-payement btn-success m-1"><i class="uil-plus"></i> Nouveau payement</button>
                    <button type="button" class="btn btn-refresh-payement-list btn-success m-1"><i class="uil-refresh"></i>Actualiser</button>
                  </div>

                </div>

              </div>

            </div>

          </div>

        </div><!-- /.modal-content -->

      </div><!-- /.modal-dialog -->

    </div>

  </div>

</div>
<!-- /.modal -->