<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="impression-facture-acceptation" class="container d-flex justify-content-center a4-paysage index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-5 bg-blanc">
    <div class="invoice">
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-12 table-bordered text-center invoice-bordered">
            <div class="text-center text-uppercase label-slogan">("Slogan de la société")</div>
          </div>
        </div>
        <br>
        <div class="row">
          <div class="col-6 d-flex justify-content-start invoice-bordered">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse label-nom-societe">Nom de la societe</strong><br>
              <strong class="label-adresse">Adresse</strong><br>
              <strong class="label-ville">Ville</strong><br>
              Tel : <strong class="label-contact">+261 00 00 000 00</strong><br>
            </address>
          </div>
        </div>
      </div>
      <br>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content">
        <!-- begin table-responsive -->
        <div class="row text-center">
          <div class="col">
            <span class="invoice-bordered">Acceptation de payement <span class="label-title-acceptation"></span></span>
          </div>
        </div>
        <br><br>
        <div class="table-responsive">
          <table class="table table-invoice table-borderless">
            <tr>
              <td>N° BLF</td>
              <td class="label-numero-facture">-</td>
            </tr>
            <tr>
              <td>Date</td>
              <td class="label-date"></td>
            </tr>
            <tr>
              <td>Montant BLF</td>
              <td class="label-montant-facture">0Ar</td>
            </tr>
            <tr>
              <td>Montant <span class="label-title-acceptation"></span></td>
              <td class="label-montant-acceptation"></td>
            </tr>
            <tr>
              <td>Montant restant</td>
              <td class="label-montant-restant">0Ar</td>
            </tr>
            <tr class="for-credit">
              <td>Date échéance</td>
              <td class="label-date-echeance"></td>
            </tr>

          </table>
        </div>
        <!-- end table-responsive -->

      </div>
      <!-- end invoice-content -->

      <!-- begin invoice-footer -->
      <div class="invoice-footer">
       <div class="row">
         <div class="col-6 d-flex justify-content-center text-underline">
           <span>Le Fournisseur</span>
         </div>
         <div class="col-6 d-flex justify-content-center text-underline">
           <span>Le Client</span>
         </div>
       </div>
      </div>
      <!-- end invoice-footer -->
    </div>
  </div>

</div>