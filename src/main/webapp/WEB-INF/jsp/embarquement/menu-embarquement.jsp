<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../template/header.jsp" %>
<!-- Start Content-->
<div class="container-fluid" id="menu-embarquement">
  <input type="hidden" id="user-id" value-id="${connectedUser.id}">
  <input type="hidden" id="filiale-id" value-id="${connectedUser.filiale.id}">
  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
          <div class="input-group">
            <input type="text" required class="form-control dropdown-toggle" placeholder="Search..." id="top-search">
            <button class="input-group-text btn-primary" type="submit"><i class="uil-search"></i></button>
          </div>
        </div>
        <h4 class="page-title">Embarquement</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->
  <div class="row mr-2">
    <div class="col-12">
      <div class="d-block mt-1">

        <a type="button" id="nouveau-embarquement-btn" class="btn btn-success btn-mobile crud-embarquement" data-bs-target="#nouveau-embarquement"
           data-bs-toggle="modal"><i
                class="uil-file-plus">&nbsp;</i>Nouveau Embarquement</a>

        <a type="button" id="refresh-btn"  class="btn btn-success refresh-list-btn btn-mobile"><i
                class="uil-refresh">&nbsp;</i>Actualiser</a>

        <div class="d-inline-flex">
          <select required name="select-date-type" id="select-date-type" class="form-select">
              <option value="date-creation">creation</option>
              <option value="date-depart">depart</option>
              <option value="date-arrive">Arrive</option>
          </select>
          <input type="date" id="begin-date-input" class="form-control ">
          <input type="date" id="end-date-input" class="form-control ">
          <a type="button" id="search-button" class="btn btn-success wm-100"><i class="uil-search-alt"></i></a>
        </div>

        <div class="float-end">
          <a class="btn btn-success btn-export-to-excel bg-forest"><img src="${pageContext.request.contextPath}/assets/images/excel.png" alt="user-image" class="icon-excel">&nbsp;</a>&nbsp;
        </div>
      </div>

    </div>
  </div>
  <!-- suite -->
  <div><br>
    <div class="row">
      <div class="col-lg-12">
        <%= start_content_table() %>
        <table id="table-voyage" class="table table-sm table-hover">
          <thead>
              <th> reference </th>
              <th> trajet </th>
              <th> Tonage (Tonne)</th>
              <th  class="marge-beneficiaire" > Marge Beneficiaire</th>
              <th> Date depart </th>
              <th> Date Arrivé </th>
              <th> status </th>
              <th> Materiel de transport </th>
              <th> Action </th>
          </thead>
          <tbody>
              <c:forEach var="v" items="${voyages}" >
                <tr class="voyage" id="${v.id}">
                  <td>${v.reference}</td>
                  <td>${v.trajet.depart}-${v.trajet.destination} </td>
                  <td>${v.poidsTotal}</td>
                  <td class="d-none marge-beneficiaire">${v.montantMarge}</td>
                  <td>${v.dateDepart}</td>
                  <td>${v.dateArrive}</td>
                  <td>
                    <c:if test = "${v.statutVoyage eq 'CHARGEMENT_EN_COURS'}">
                      <span class="badge badge-info-lighten"> Chargement en cours</span>
                    </c:if>
                    <c:if test = "${v.statutVoyage eq 'CHARGEMENT_TERMINER' }">
                      <span class="badge badge-warning-lighten">Chargement terminer</span>
                    </c:if>
                    <c:if test = "${v.statutVoyage eq 'ARRIVER_DESTINATION'}">
                      <span class="badge badge-success-lighten">Arriver a destination</span>
                    </c:if>
                    <c:if test = "${v.statutVoyage eq 'TRANSFERER'}">
                      <span class="badge badge-secondary-lighten">Transferer</span>
                    </c:if>

                    <c:if test = "${v.statutVoyage eq 'EN_ROUTE'}">
                      <span class="badge badge-primary-lighten">En mer (route)</span>
                    </c:if>

                  </td>
                  <td>${v.materielTransport.reference}</td>
                  <td class="td-action justify-content-center crud-embarquement">
                    <div class="d-flex" >
                      <a data-bs-toggle="modal" data-bs-target="#nouveau-embarquement" class="btn-sm btn-info edit-voyage"><i class="uil-pen"></i></a>
                      <a data-bs-toggle="modal" class="btn-sm btn-info about-voyage"><i class="uil-info-circle"></i></a>
                      <a class="btn-sm btn-danger delete-voyage "><i class="uil-trash-alt"></i></a>
                    </div>
                  </td>
                </tr>
              </c:forEach>
          </tbody>
        </table>
        <%= end_content_table() %>
      </div>
    </div>
  </div>

  <div class="text-end float-end mt-1 label-count-footer">
    <span><span class="text-count-embarquement">00</span> voyage enregistrés </span>
  </div>

  <div class="all-modal">
    <%@ include file="../modal/embarquement/info-embarquement.jsp" %>
    <%@ include file="../modal/embarquement/new-embarquement.jsp" %>
    <%@ include file="../modal/embarquement/prix-article-embarquement.jsp" %>
    <%@ include file="../modal/embarquement/nouveau-article-embarquement.jsp" %>
    <%@ include file="../modal/vente/list-article.jsp" %>
    <%@ include file="../modal/Transport/new-materiel-de-transport.jsp"%>
    <%@ include file="../modal/embarquement/nouveau-trajet.jsp" %>
    <%@ include file="../modal/fournisseur/new-fournisseur.jsp" %>
    <%@ include file="../modal/vente/prix-special.jsp" %>
    <%@ include file="../modal/new-chauffeur.jsp" %>
    <%@ include file="../modal/embarquement/transfert-voyage.jsp" %>
  </div>

</div>
</div> <!-- container -->
<%@ include file="../template/footer.jsp" %>
<%@ include file="../template/setting.jsp" %>