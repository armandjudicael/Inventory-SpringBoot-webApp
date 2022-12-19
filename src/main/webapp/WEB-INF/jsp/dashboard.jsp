<%@ include file="template/header.jsp" %>

<br>
<div id="dashboard">
  <input type="hidden" id="user-id" value-id="${connectedUser.id}" value-name="${connectedUser.username}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
      <div class="row">
        <div class="col-lg-3">
          <div class="card widget-flat">
            <div class="card-body">
              <div class="float-end">
                <i class="mdi mdi-account-multiple widget-icon"></i>
              </div>
              <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Caisse</h5>
              <h3 class="mt-3 mb-3" nbs>${espece}</h3>
              <p class="mb-0 text-muted">
                <span class="text-success me-2"><i class="mdi mdi-arrow-up-bold"></i> <span nbs>5.72</span>%</span>
                <span class="text-nowrap">Par rapport &agrave; hier</span>
              </p>
            </div> <!-- end card-body-->
          </div> <!-- end card-->
        </div> <!-- end col-->

        <div class="col-lg-3">
          <div class="card widget-flat">
            <div class="card-body">
              <div class="float-end">
                <i class="mdi mdi-cart-plus widget-icon"></i>
              </div>
              <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Credit</h5>
              <h3 class="mt-3 mb-3" nbs>${credit}</h3>
              <p class="mb-0 text-muted">
                <span class="text-danger me-2"><i class="mdi mdi-arrow-down-bold"></i> <span nbs>1.08</span>%</span>
                <span class="text-nowrap">Par rapport &agrave; hier</span>
              </p>
            </div> <!-- end card-body-->
          </div> <!-- end card-->
        </div> <!-- end col-->

        <div class="col-lg-3">
          <div class="card widget-flat">
            <div class="card-body">
              <div class="float-end">
                <i class="mdi mdi-account-multiple widget-icon"></i>
              </div>
              <h5 class="text-muted fw-normal mt-0" title="Number of Customers">Encaissement</h5>
              <h3 class="mt-3 mb-3" nbs>${encaissement}</h3>
              <p class="mb-0 text-muted">
                <span class="text-success me-2"><i class="mdi mdi-arrow-up-bold"></i> <span nbs>5.27</span>%</span>
                <span class="text-nowrap">Par rapport &agrave; hier</span>
              </p>
            </div> <!-- end card-body-->
          </div> <!-- end card-->
        </div> <!-- end col-->

        <div class="col-lg-3">
          <div class="card widget-flat">
            <div class="card-body">
              <div class="float-end">
                <i class="mdi mdi-cart-plus widget-icon"></i>
              </div>
              <h5 class="text-muted fw-normal mt-0" title="Number of Orders">Decaissement</h5>
              <h3 class="mt-3 mb-3" nbs>${depense}</h3>
              <p class="mb-0 text-muted">
                <span class="text-danger me-2"><i class="mdi mdi-arrow-down-bold"></i> <span nbs>1.08</span>%</span>
                <span class="text-nowrap">Par rapport &agrave; hier</span>
              </p>
            </div> <!-- end card-body-->
          </div> <!-- end card-->
        </div> <!-- end col-->

      </div> <!-- end row -->


  <!-- begin row-->

  <div class="row">
    <div class="col-lg-4 box-dashboard">
      <div class="card w-100">
        <div class="card-body">
          <a href="" class="btn btn-sm btn-link float-end">Exporter
            <i class="mdi mdi-download ms-1"></i>
          </a>
          <h4 class="header-title mt-2 mb-3">Credit par client</h4>

          <div class="table-responsive">
            <%= start_content_table() %>
            <table class="table table-special-form table-liste-dette-client table-centered table-striped table-nowrap table-hover mb-0">
              <tbody>
              <c:forEach var="clt" items="${client_list}">
                <tr id="${clt.id}">
                  <td>${clt.nom}</td>
                  <td class="text-end"><span nbs>${clt.totalMontantTrosa}</span> Ar</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div> <!-- end table-responsive-->
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div>
    <div class="col-lg-4 box-dashboard">


      <!-- begin row-->
      <div class="row w-100">
        <div class="col-lg-12">
          <div class="card card-h-100">
            <div class="card-body">
              <div class="dropdown float-end">
                <a href="#" class="dropdown-toggle arrow-none card-drop" data-bs-toggle="dropdown"
                   aria-expanded="false">
                  <i class="mdi mdi-dots-vertical"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-end">
                  <!-- item-->
                  <a href="" class="dropdown-item">Exporter excel</a>
                </div>
              </div>
              <h4 class="header-title mb-3">Rapport mensuel 2022</h4>
<%--              <div dir="ltr">--%>
<%--                <div id="myPlot" class="w-100"></div>--%>

<%--                <script>--%>

<%--                    var xArray = ["Italy","France","Spain","USA","Argentina"];--%>
<%--                    var yArray = [55,49,44,24,15];--%>

<%--                    var data = [{--%>
<%--                        x:xArray,--%>
<%--                        y:yArray,--%>
<%--                        type : "bar"--%>
<%--                    }];--%>

<%--                    var layout = {title:"World Wide Wine Production"};--%>

<%--                    Plotly.newPlot("myPlot",data,layout);--%>

<%--                </script>--%>
<%--&lt;%&ndash;                <div id="high-performing-product" class="apex-charts" data-colors="#727cf5,#e3eaef"></div>&ndash;%&gt;--%>
<%--              </div>--%>
            </div> <!-- end card-body-->
          </div> <!-- end card-->

        </div> <!-- end col -->

      </div>
      <!-- end row-->


    </div>
    <div class="col-lg-4 box-dashboard">
      <div class="card w-100">
        <div class="card-body">
          <a href="" class="btn btn-sm btn-link float-end">Exporter
            <i class="mdi mdi-download ms-1"></i>
          </a>
          <h4 class="header-title mt-2 mb-3">Dette envers Fournisseur</h4>
          <div class="table-responsive">
            <%= start_content_table() %>
            <table class="table table-special-form table-liste-dette-fournisseur table-centered table-striped table-nowrap table-hover mb-0">
              <tbody>
              <c:forEach var="frs" items="${fournisseur_list}">
                <tr id="${frs.id}">
                  <td>${frs.nom}</td>
                  <td class="text-end"><span nbs>${frs.totalMontantTrosa}</span> Ar</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
            <%= end_content_table() %>
          </div> <!-- end table-responsive-->
        </div> <!-- end card-body-->
      </div> <!-- end card-->
    </div>
  </div>
  <div class="all-modal">
    <%@ include file="modal/client/info-dette-cf.jsp" %>
    <%@ include file="modal/fournisseur/payement-dette.jsp" %>
    <%@ include file="modal/fournisseur/new-dette.jsp" %>
    <%@ include file="modal/dashboard/info-payement.jsp" %>

</div>

<!-- end row -->
<!-- end row -->

</div>
<!-- container -->
<%@ include file='template/footer.jsp' %>
</div>
<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<%@ include file="template/setting.jsp" %>