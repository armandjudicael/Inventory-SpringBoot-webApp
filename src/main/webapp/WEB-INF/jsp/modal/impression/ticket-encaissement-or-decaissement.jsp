<%@ page import="java.util.Date" %>
<!-- FACTURE A5 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="impression-ticket-encaissement-ou-decaissement" class="d-flex justify-content-center ticket-of index-none">

  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <meta charset="utf-8"/>

  <div class="col-md-2 bg-blanc">
    <div class="invoice invoice-sm">
      <!-- begin invoice-company -->
      <div class="text-end text-uppercase"><span class="invoice-bordered p-1 bg-dragula label-title"></span></div>
      <!-- end invoice-company -->
      <!-- begin invoice-header -->
      <div class="invoice-header-none">
        <div class="row">
          <div class="col-12 d-flex justify-content-start ps-3">
            <address class="m-t-5 m-b-5">
              <strong class="text-inverse label-nom-societe">(Nom de la societe)</strong><br>
              <strong class="label-adresse">(Adresse)</strong><br>
            </address>
          </div>
        </div>
      </div>
      <!-- end invoice-header -->
      <!-- begin invoice-content -->
      <div class="invoice-content ps-1">
        <hr>
        Date : <span class="label-date"><%= new Date().toLocaleString() %></span><br>
        Référence : <span class="label-reference"></span><br>
        Montant : <span class="label-montant"></span><br>


      </div>
      <!-- end invoice-content -->
      <!-- begin invoice-note -->
      <div class="invoice-note ps-1">
        Description : <span class="label-motif"></span>
      <!-- end invoice-note -->

      </div>
    </div>

  </div>

</div>