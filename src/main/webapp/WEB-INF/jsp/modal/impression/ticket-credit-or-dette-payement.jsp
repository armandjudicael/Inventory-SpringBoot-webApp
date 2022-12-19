<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="" class="d-flex justify-content-center ticket-of index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-2 bg-blanc">
    <div class="invoice invoice-sm">
      <!-- begin invoice-company -->
      <div class="text-end text-uppercase"><span class="invoice-bordered p-1 bg-dragula">recu payement crédit/dette</span></div>
      <!-- end invoice-company -->
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-12 d-flex justify-content-start ps-3">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse">Nom de la societe</strong><br>
              <strong>Adresse</strong><br>
              Tel : <strong>+261 00 00 000 00</strong><br>
              Client/Fournisseur : <span>Nom du client</span><br>
            </address>
          </div>
        </div>
      </div>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content ps-1">
        <hr>
        Date : <span><%= new Date().toLocaleString() %></span><br>
        Référence : <span>XXXXX</span><br>
        Montant : <span>0Ar</span><br>
        Reste à payer : <span>0Ar</span><br>
        Mode de payement : <span class="text-uppercase">type-payement</span><br>


      </div>
      <!-- end invoice-content -->
      <!-- begin invoice-note -->
      <div class="invoice-note ps-1">
        Description : <span>Aucun description pour cette opération</span>
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