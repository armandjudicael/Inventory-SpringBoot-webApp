<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="impression-ticket-avoir" class="d-flex justify-content-center ticket-of index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-2 bg-blanc">
    <div class="invoice invoice-sm">
      <!-- begin invoice-company -->
      <div class="text-end text-uppercase"><span class="invoice-bordered p-1 bg-dragula">AVOIR</span></div>
      <!-- end invoice-company -->
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-6 d-flex justify-content-center">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse label-nom-societe"></strong><br>
              <strong class="label-adresse">Adresse</strong><br>
              <strong class="label-ville">Ville</strong><br>
              Tel : <strong class="label-contact">+261 00 00 000 00</strong><br>
              Client : <span class="label-nom-client">Nom du client</span><br>
            </address>
          </div>
          <div class="col-6 d-flex justify-content-center">
            <address class="m-t-5 m-b-5">
              Avoir n° : <strong class="text-inverse label-numero-avoir"></strong><br>
              Le <span class="label-date">Le <%= new Date().toLocaleString()  %></span><br>
              Magasin <span class="label-magasin"></span><br><br>

              Op : <span class="label-nom-operateur"></span>
            </address>
          </div>
        </div>
      </div>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content">
        <!-- begin table-responsive -->
        <div class="table-responsive">
          <table class="table table-liste-avoir table-invoice mb-0">
            <thead>
            <tr class="bg-dark">
              <th>Article</th>
              <th>T.Prix</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
        <!-- end table-responsive -->
        <!-- begin invoice-price -->
        <div class="invoice-price">
          <div class="invoice-price-right">
            <span class="f-w-600">Montant total : <span class="label-montant-avoir"></span></span>
          </div>
        </div>
        <!-- end invoice-price -->
      </div>
      <!-- end invoice-content -->
      <!-- begin invoice-note -->
      <div class="invoice-note">
        <span>Edition du <span class="label-date-edition"></span></span></div>
      <!-- end invoice-note -->
      <!-- begin invoice-footer -->
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