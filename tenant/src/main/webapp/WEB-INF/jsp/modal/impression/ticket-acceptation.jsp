<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="impression-ticket-acceptation" class="d-flex justify-content-center ticket-acceptation ticket-of index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-2 bg-blanc">
    <div class="invoice invoice-sm">
      <!-- begin invoice-company -->
      <div class="text-end text-uppercase"><span class="invoice-bordered p-1 bg-dragula">acceptation de payement <span class="label-title-acceptation"></span></span></div>
      <!-- end invoice-company -->
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-12 d-flex justify-content-start ps-3">
            <address class="m-t-5">
              <strong class="text-inverse label-nom-societe">Nom de la societe</strong><br>
              <strong class="label-adresse">Adresse</strong><br>
              Tel : <strong class="label-contact">+261 00 00 000 00</strong><br>
              Client : <span class="label-nom-client">Nom du client</span><br>
            </address>
          </div>
        </div>
      </div>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content ps-1">
        <span>
        Facture : <span class="label-numero-facture">n°_facture</span><br> </span>
        <span>
        Date : <span class="label-date"><%= new Date().toLocaleString() %></span><br> </span>
        <span>
        Magasin : <span class="label-magasin">magasin_nom</span><br> </span>
        <span>
        Montant : <span class="label-montant-facture">0Ar</span><br> </span>
        <span>
        <span class="label-title-acceptation"></span> : <span class="label-montant-acceptation">0Ar</span><br> </span>
        <span>
        Reste à payer : <span class="label-montant-restant">0Ar</span><br> </span>
        <span class="for-credit">
        Echeance : <span class="label-date-echeance"></span><br> </span>

      </div>
      <!-- end invoice-content -->
      <!-- begin invoice-note -->
      <div class="invoice-note ps-1">
        Description : <span class="label-description">Aucun description pour cette opération</span>
        <!-- end invoice-note -->

      </div>

      <div class="invoice-footer">
        <div class="row">
          <div class="col-12 d-flex justify-content-center text-underline">
            <span>Meric de votre visite, a bientôt!</span>
          </div>
        </div>
        <!-- end invoice-footer -->
      </div>
    </div>

  </div>

</div>