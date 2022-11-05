<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="facture-A5 container d-flex justify-content-center a4-paysage index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-8 bg-blanc">
    <div class="invoice">
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-4 d-flex justify-content-center">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse">Nom de la societe</strong><br>
              <strong>Adresse</strong><br>
              <strong>Ville</strong><br>
              Tel : <strong>+261 00 00 000 00</strong><br>
            </address>
          </div>
          <div class="col-8 d-flex justify-content-center">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse">Reçu payement crédit/dette</strong><br>
              <span>Référence : XXXXX</span><br>
              <span>Le <%= new Date().toLocaleString()  %></span><br>
              <span>Opérateur : username</span>
            </address>
          </div>
        </div>
      </div>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content">
        <!-- begin table-responsive -->
        <div class="table-responsive">
          <table class="table table-invoice table-bordered">
            <tr>
              <td>Nom Client/Fournisseur</td>
              <td>client-fournisseur-name</td>
            </tr>
            <tr>
              <td>Montant payé</td>
              <td>0Ar</td>
            </tr>
            <tr>
              <td>Reste à  payé</td>
              <td>0Ar</td>
            </tr>
            <tr>
              <td>Mode de payement</td>
              <td class="text-uppercase">type-payement</td>
            </tr>
            <tr>
              <td>Description</td>
              <td>Aucun description pour cette operation</td>
            </tr>
          </table>
        </div>
        <!-- end table-responsive -->

      </div>
      <!-- end invoice-content -->

      <!-- begin invoice-footer -->
      <div class="invoice-footer">
       <div class="row">
         <div class="col-6 d-flex justify-content-end text-underline">
           <span>Le Caissier</span>
         </div>
       </div>
      </div>
      <!-- end invoice-footer -->
    </div>
  </div>

</div>