<!-- Standard modal -->
<div id="modal-dette-fournisseur" class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="standard-modalLabel_ii"
     aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <div class="modal-content was-validated">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel_ii">Info Fournisseur</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4 aside">
            <div class="label">
              <p class=" d-flex justify-content-center"><i class="uil-user-square uil-size-40"></i>
              </p>
              <p class="text-center label-nom-fournisseur">

              </p>
              <p class="text-center label-contact-fournisseur">

              </p>
              <p class="text-center label-adresse-fournisseur">

              </p>
              <p class="text-center">
                <button id="btn-bloquer-fournisseur" class="btn btn-warning"><i class="uil-user-times"></i> Bloquer
                  Fournisseur
                </button>
                <button id="btn-debloquer-fournisseur" class="btn btn-primary d-none"><i class="uil-user-plus"></i> D&eacute;bloquer
                  Fournisseur
                </button>
              </p>

              <hr>

              <p class="text-center">Credit impay&eacute; : <span class="label-dette-impaye"></span></p>
              <p class="text-center">
              <div class="d-inline-flex">
                <input type="text" class="form-control text-right w-75 mr-1 input-dette-edit" placeholder="" value="">
                <button id="btn-payer-credit-fournisseur" class="btn btn-primary">Rembourser</button>
              </div>

              </p>

            </div>
          </div>
          <div class="col-md-8">
            <div class="page-title-box">
              <div class="page-title-right">
                <a class="btn btn-success btn-export-to-excel bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
              </div>
            </div>
            <table id="table-historique-operation-fournisseur"
                   class="table table-special-form table-striped norwap dt-responsive">
              <thead>
              <th>Date</th>
              <th>Operation</th>
              <th>Credit</th>
              <th>Payer</th>
              <th>Reste D&ucirc;</th>
              <th>Echeance</th>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="modal-footer d-inline-flex">
        <button type="button" class="btn btn-default w-100 m-0" data-bs-dismiss="modal"><i class="uil-exit"></i>&nbsp;Fin
          de modification
        </button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->

</div>
<!-- /.modal -->
