<!-- END wrapper -->

<!-- Right Sidebar -->
<div class="end-bar">
  <div class="rightbar-title">
    <a href="" class="end-bar-toggle float-end">
      <i class="dripicons-cross noti-icon"></i>
    </a>
    <h5 class="m-0">Paramètres</h5>
  </div>

  <div class="rightbar-content h-100" data-simplebar="">

    <div class="p-3">
      <div class="alert alert-warning" role="alert">
        <strong>Customize </strong> the overall color scheme, sidebar menu, etc.
      </div>

      <!-- Settings -->
      <h5 class="mt-3">Color Scheme</h5>
      <hr class="mt-1">

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="color-scheme-mode" value="light" id="light-mode-check"
               checked="">
        <label class="form-check-label" for="light-mode-check">Light Mode</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="color-scheme-mode" value="dark" id="dark-mode-check">
        <label class="form-check-label" for="dark-mode-check">Dark Mode</label>
      </div>


      <!-- Width -->
      <h5 class="mt-4">Width</h5>
      <hr class="mt-1">
      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="width" value="fluid" id="fluid-check" checked="">
        <label class="form-check-label" for="fluid-check">Fluid</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="width" value="boxed" id="boxed-check">
        <label class="form-check-label" for="boxed-check">Boxed</label>
      </div>


      <!-- Left Sidebar-->
      <h5 class="mt-4">Left Sidebar</h5>
      <hr class="mt-1">
      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="theme" value="default" id="default-check">
        <label class="form-check-label" for="default-check">Default</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="theme" value="light" id="light-check" checked="">
        <label class="form-check-label" for="light-check">Light</label>
      </div>

      <div class="form-check form-switch mb-3">
        <input class="form-check-input" type="checkbox" name="theme" value="dark" id="dark-check">
        <label class="form-check-label" for="dark-check">Dark</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="compact" value="fixed" id="fixed-check" checked="">
        <label class="form-check-label" for="fixed-check">Fixed</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="compact" value="condensed" id="condensed-check">
        <label class="form-check-label" for="condensed-check">Condensed</label>
      </div>

      <div class="form-check form-switch mb-1">
        <input class="form-check-input" type="checkbox" name="compact" value="scrollable" id="scrollable-check">
        <label class="form-check-label" for="scrollable-check">Scrollable</label>
      </div>

      <div class="d-grid mt-4">
        <button class="btn btn-primary" id="resetBtn">Reset to Default</button>

        <a href="../jsp/../../product/hyper-responsive-admin-dashboard-template/index.htm" class="btn btn-danger mt-3"
           target="_blank"><i class="mdi mdi-basket me-1"></i> Purchase Now</a>
      </div>
    </div> <!-- end padding-->

  </div>
</div>

<div class="rightbar-overlay"></div>
<!-- /End-bar -->


<!-- bundle -->

<script src="${pageContext.request.contextPath}/assets/js/vendor.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.min.js"></script>

<!-- third party js -->

<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-jvectormap-world-mill-en.js"></script>

<!-- third party js ends -->

<%--<script src="${pageContext.request.contextPath}/assets/js/vendor/apexcharts.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/vendor/jquery-jvectormap-world-mill-en.js"></script>

<!-- demo app -->

<%--<script src="${pageContext.request.contextPath}/assets/js/pages/demo.dashboard.js"></script>--%>

<!-- end demo js-->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/table2excel.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/nombre_en_lettre.js"></script>

<%--<script src=”https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/3.3.4/jquery.inputmask.bundle.min.js”></script>--%>

<script src="${pageContext.request.contextPath}/assets/js/mini-function.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/JsBarcode.all.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.maskedinput.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/html-content.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/impression.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/invoice-generate.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/dashboard.js"></script>,
<script src="${pageContext.request.contextPath}/assets/js/menu/gerer-utilisateur.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/autorisation/menu-autorisation.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/autorisation/autorisation.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-article.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modalJs/new-categorie.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modalJs/new-article.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-stock.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-facture.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-prix.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-peremption.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-detail-vente.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-client.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-fournisseur.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-vente.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modalJs/info-stock.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modalJs/list-article.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/operation/entree.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/operation/sortie.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/operation/liste.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/operation/transfert.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-caisse.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-livraison.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-paiement.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-utilisateur.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-voyage.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-choix-magasin.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/embarquement/menu-embarquement.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/embarquement/new-embarquement.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/modalJs/edit-prix.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/modalJs/list-fournisseur.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-magasin.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/menu-transport.js"></script>

<!-- admin client -->

<script src="${pageContext.request.contextPath}/assets/js/menu/admin-client/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/menu/administrateur/dashboard.js"></script>


<!-- third party js -->

<script src="${pageContext.request.contextPath}/assets/js/vendor/Chart.bundle.min.js"></script>

<!-- third party js ends -->

<!-- demo app -->

<script src="${pageContext.request.contextPath}/assets/js/pages/demo.dashboard-projects.js"></script>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css"/>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/additional-methods.min.js"></script>

<%--<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>--%>

</body>
</html>