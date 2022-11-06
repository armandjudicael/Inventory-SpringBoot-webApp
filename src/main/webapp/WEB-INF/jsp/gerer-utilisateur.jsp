<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="template/header.jsp" %>
<div class="container-fluid" id="gerer-utilisateur">

  <!-- start page title -->
  <div class="row">
    <div class="col-12">
      <div class="page-title-box">
        <div class="page-title-right">
        </div>
        <h4 class="page-title">Gérer mon compte</h4>
      </div>
    </div>
  </div>
  <!-- end page title -->


  <div class="row">
    <div class="col-sm-12">
      <!-- Profile -->
      <div class="card bg-light">
        <div class="card-body profile-user-panel-box">
          <div class="row">
            <div class="col-sm-8">
              <div class="row align-items-center">
                <div class="col-auto">
                  <div class="avatar-lg">
                    <img src="https://picsum.photos/200/200?grayscale&blur=2" alt=""
                         class="rounded-circle img-thumbnail">
                  </div>
                </div>
                <div class="col">
                  <div>
                    <h4 class="mt-1 mb-1 text-white nom-complet-user-panel">Nom Complet d'utilisateur</h4>
                    <p class="font-13 text-white-50 fonction-user-panel"> (Fonction de l'utilisateur)</p>

                    <ul class="mb-0 list-inline text-dark">
                      <li class="list-inline-item me-3">
                        <h5 class="mb-1 magasin-user-panel">M1</h5>
                        <p class="mb-0 font-13 text-white-50">Magasin Principal</p>
                      </li>
                      <li class="list-inline-item">
                        <h5 class="mb-1 filial-user-panel">F1</h5>
                        <p class="mb-0 font-13 text-white-50">Filial d'affectation</p>
                      </li>
                      <li class="list-inline-item">
                        <h5 class="mb-1 societe-user-panel">S1</h5>
                        <p class="mb-0 font-13 text-white-50">Société d'affectation</p>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div> <!-- end col-->

            <div class="col-sm-4">
              <div class="text-center mt-sm-0 mt-3 text-sm-end d-none">
                <button type="button" class="btn btn-info">
                  <i class="mdi mdi-account-edit me-1"></i> Editer Profile
                </button>
              </div>
            </div> <!-- end col-->
          </div> <!-- end row -->

        </div> <!-- end card-body/ profile-user--box-->
      </div><!--end profile/ card -->
    </div> <!-- end col-->
  </div>
  <!-- end row -->


  <div class="row">
    <div id="infos-general" class="col-xl-8">
      <!-- Personal-Information -->
      <div class="card">
        <div class="card-body">
          <div class="row mb-2">
            <div class="col-8"><h4 class="header-title mt-0 mb-3">Information général</h4></div>
            <div class="col-4 text-end">
              <button class="btn btn-editer-infos-general btn-info"><i class="uil uil-pen"></i> Editer</button>
            </div>
          </div>
          <p class="text-muted">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet asperiores delectus est id nihil nobis
            obcaecati qui rem veritatis. Consectetur dignissimos eius illum itaque magnam necessitatibus numquam
            perspiciatis qui temporibus!
          </p>
          <hr>
          <form class="text-start">
            <table class="table-information table table-borderless">
              <tr>
                <td>Nom</td>
                <td>
                  <input name="iNom" class="input-nom form-control" type="text" disabled value="nom utilisateur">
                </td>
              </tr>
              <tr>
                <td>Prenoms</td>
                <td>
                  <input name="iPrenoms" class="input-prenoms form-control" type="text" disabled
                         value="prenoms utilisateur">
                </td>
              </tr>
              <tr>
                <td>Adresse</td>
                <td>
                  <input name="iAdresse" class="input-adresse form-control" type="text" disabled
                         value="adresse utilisateur">
                </td>
              </tr>
              <tr>
                <td>Contact</td>
                <td>
                  <input name="iContact" class="input-contact form-control" type="text" disabled
                         value="+261 00 00 000 00">
                </td>
              </tr>
              <tr>
                <td>Photo de profil</td>
                <td>
                  <input name="iPhoto" class="input-photo form-control" type="file" disabled
                         value="contact utilisateur">
                </td>
              </tr>
              <tr>
                <td>Fonction</td>
                <td>
                  fonction utilisateur
                  <%--                  <input class="input-fonction form-control" type="text" disabled value="nom utilisateur">--%>
                </td>
              </tr>
              <tr>
                <td>Magasin</td>
                <td>
                  Listes des magasins
                  <%--                  <input class="input-magasin form-control" type="text" disabled value="nom utilisateur">--%>
                </td>
              </tr>
              <tr>
                <td>
                  <button type="submit" class="btn btn-success btn-enregistrer-infos-general"><i
                          class="uil uil-check-circle"></i> Enregistrer
                  </button>
                </td>
                <td></td>
              </tr>
            </table>
          </form>
        </div>
      </div>
      <!-- Personal-Information -->
    </div>

    <div id="infos-securite" class="col-xl-4">
      <!-- Personal-Information -->
      <div class="card">
        <div class="card-body">
          <div class="row mb-2">
            <div class="col-8"><h4 class="header-title mt-0 mb-3">Information de sécurité</h4></div>
            <div class="col-4 text-end">
              <button class="btn btn-editer-infos-securite btn-info"><i class="uil uil-pen"></i> Editer</button>
            </div>
          </div>
          <p class="text-muted">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda aut beatae eius eligendi, eos
            exercitationem expedita illum, inventore perferendis quaerat quisquam, quod repellendus voluptatibus? Ipsam
            laudantium nulla possimus saepe similique.
          </p>
          <hr>

          <form class="text-start">
            <table class="table-information-utilisateur table table-borderless">
              <tr>
                <td>Nom d'utilisateur</td>
                <td class="label-username-securite">
                  nomdutilisatuer
                </td>
              </tr>
              <tr>
                <td>Ancien Mot de passe</td>
                <td>
                  <input name="iOldPassword" class="input-old-password form-control" type="password" disabled
                         value="password">
                </td>
              </tr>
              <tr>
                <td>Nouveau Mot de passe</td>
                <td>
                  <input name="iNewPassword" class="input-new-password form-control" type="password" disabled
                         value="password">
                </td>
              </tr>
              <tr>
                <td>Retapez Nouveau Mot de passe</td>
                <td>
                  <input name="iRetypeNewPassword" class="input-retype-new-password form-control" type="password"
                         disabled value="password">
                </td>
              </tr>

              <tr>
                <td>
                  <button type="submit" class="btn btn-enregistrer-infos-securite btn-success"><i
                          class="uil uil-check-circle"></i> Enregistrer
                  </button>
                </td>
                <td>
                </td>
              </tr>
            </table>
          </form>
        </div>
      </div>
      <!-- Personal-Information -->


    </div>


    <div class="col-xl-12" id="anchor-history-session">

      <div class="card">
        <div class="card-body">
          <h4 class="header-title mb-3">Historiques des sessions</h4>
          <p class="text-muted">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus aperiam culpa dicta dolore eaque earum
            eius enim eum exercitationem facilis, ipsum iste mollitia nihil numquam officiis quisquam repellendus, velit
            voluptas.
          </p>
          <hr>
          <div class="table-responsive t-list-75">
            <table class="table table-historique-session table-hover table-centered mb-0">
              <thead>
              <tr>
                <th>Session ID</th>
                <th>Debut - Fin</th>
                <th>Durée (min)</th>
                <th>Adresse IP</th>
              </tr>
              </thead>
              <tbody>
              <% for (int i = 0; i < 10; i++) { %>
              <tr>
                <td>123</td>
                <td>12/12/12 10:00 - 12/12/12 12:00</td>
                <td>120 min</td>
                <td>192.168.15.1</td>
              </tr>
              <% } %>
              </tbody>
            </table>
          </div> <!-- end table responsive-->
        </div> <!-- end col-->
      </div> <!-- end row-->
    </div>
    <!-- end col -->
  </div>
  <!-- end row -->
</div>
<!-- container -->
<%@ include file="template/setting.jsp" %>