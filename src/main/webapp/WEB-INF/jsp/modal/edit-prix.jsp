<!-- Standard modal -->
<div id="modal-info-prix" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-scrollable">
    <form class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="standard-modalLabel">Mis &agrave; jour Prix</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>
      </div>

      <div class="modal-body">
        <div class="row">
          <div class="col-md-4 aside">
            <div class="label">
              <p class=" d-flex justify-content-center"><i class="uil-box uil-size-40"></i>
              </p>
              <p class="text-center label-designation-article">
              </p>
              <p class="text-center label-unite-article">
              </p>
              <p class="text-center label-date-prix">
              </p>
              <hr>
              <input type="hidden" id="user-id" value-id="${connectedUser.id}" >
              <div class="d-inline-flex">
                <input type="number" name="inputPrix" class="form-control text-right w-75 mr-1 input-prix-edit" placeholder="" value="0Ar">
                <button class="btn btn-success w-25 btn-enregistrer-prix-editer edition-prix"><i class="uil-money-insert"></i>
                </button>
              </div>
            </div>
          </div>
          <div class="col-md-8">
            <div class="page-title-box">
              <div class="page-title-right">
                <a class="btn btn-success btn-export-to-excel-modal bg-forest mb-1"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>
              </div>
            </div>
            <table id="table-historique-prix" class="table table-special-form table-striped norwap dt-responsive">
              <thead>
              <th>Date</th>
              <th>Montant</th>
              <th>Utilisateur</th>
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
    </form><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->

  <div class="all-modal">

  </div>
</div>
<!-- /.modal -->
