$(function () {
    let namespace = "#dashboard-administrateur ";
    $(namespace+"#company-creation").hide();
    /*
    Tableau de bord societe
     */
    let NEW = "nouveau", EDIT = "editer";
    /* Event create new societe */
    $(namespace + "#btn-nouveau-societe").on('click', function () {
        $(namespace +"#nouveau-societe .modal-title").text("Nouveau Societe")
        $(namespace +"#nouveau-societe").modal('show')
        $(namespace +"#nouveau-societe").attr('data-id', NEW)
    })
    /* Edit societe */
    $(document).on('click', namespace + ' .btn-editer-societe', function () {
        $(namespace + "#nouveau-societe .modal-title").text('Editer Societe')
        $(namespace + "#nouveau-societe").modal('show')
        $(namespace + "#nouveau-societe").attr('data-id', EDIT)
        $cardCurrent = $(this).closest('.item-societe').attr('id');
        // affecter les valeur
        $(namespace + '#nouveau-societe input#input-nom').val($(namespace + '#' + $cardCurrent + ' .label-nom').text())
        $(namespace + '#nouveau-societe input#input-adresse').val($(namespace + '#' + $cardCurrent + ' .label-adresse').text())
        $(namespace + '#nouveau-societe input#input-contact').val($(namespace + '#' + $cardCurrent + ' .label-contact').text())
        $(namespace + '#nouveau-societe input#input-slogan-i').val($(namespace + '#' + $cardCurrent + ' .label-slogan-i').text())
        $(namespace + '#nouveau-societe input#input-slogan-ii').val($(namespace + '#' + $cardCurrent + ' .label-slogan-ii').text())
    })
    /* Enregsitrement societe */
    /*
    mask et validation
     */
    $(namespace + 'form #input-contact').mask('+261 99 99 999 99')
    $(namespace + 'form#form-nouveau-societe').validate({
        rules : {
            nom : {required : true},
            adresse : {required : true},
            contact : {required : true},
            username : {required : true},
            password : {required : true, minlength : 4},
            sloganI : {required : true},
            sloganII : {required : true},
            logo : {required : true}
        },
        messages : {
            nom : {required : 'Nom du filial requis'},
            adresse : {required : 'Adresse du filial requis'},
            contact : {required : 'Contact du filial requis'},
            username : {required : 'Nom d\'utilisateur requis'},
            password : {required : 'Mot de passe requis', minlength : 'Mot de passe devrait avoir minimum 04 caractères'},
            sloganI : {required : 'Slogan du societe requis'},
            sloganII : {required : 'Slogan du societe requis'},
            logo : {required : true}
        }
    })
    function validation_nouveau_societe() {
        $(namespace + 'form#form-nouveau-societe').validate()
        return $(namespace + 'form#form-nouveau-societe').valid()
    }

    $(namespace + "#nouveau-societe #btn-enregistrer-societe").on('click', function () {

        if (validation_nouveau_societe()) {

            $nom = $(namespace + '#nouveau-societe input#input-nom').val();
            $adresse = $(namespace + '#nouveau-societe input#input-adresse').val()
            $contact = $(namespace + '#nouveau-societe input#input-contact').val()

            $username = $(namespace + '#nouveau-societe input#input-username').val()
            $password = $(namespace + '#nouveau-societe input#input-password').val()

            $slogan = $(namespace + '#slogan').val();
            $verset=$(namespace + '#verset').val();
            $logo=$(namespace + '#input-logo').val();
            $typeOperation=$(namespace + "#nouveau-societe").attr('data-id');


            let company={};
            company.nom=$nom;
            company.slogan=$slogan;
            company.verset=$verset;
            company.adresse=$adresse;
            company.numTel=$contact;

            let cdc={};
            cdc.username="postgres";
            cdc.password="root";
            cdc.driverClassName = "org.postgresql.Driver";
            cdc.host = "localhost";
            cdc.port = "5432";
            cdc.databaseType = "POSTGRESQL";
            cdc.databaseName = $nom.trim()+"_db";
            company.companyDataSourceConfig = cdc;

            let admin = {};
            admin.userName= $username;
            admin.password = $password;
            admin.key = $nom;
            admin.userType =0;
            company.admin = admin;

            let spinner = `
                    <div class="spinner-border" style="width: 3rem; height: 3rem;" role="status">
                      <span class="visually-hidden">Loading...</span>
                    </div>
                    <div class="spinner-grow" style="width: 3rem; height: 3rem;" role="status">
                      <span class="visually-hidden">Loading...</span>
                    </div>
            `;

            let url = "http://localhost:8080/api/imwa/v1/companies";

            // modal hidden
            $(namespace + '#nouveau-societe').modal('hide');

            execute_ajax_request("post",url,company,function (data){
                $(namespace + '.liste-societe').append(createItemSociete(new Date().toLocaleTimeString(),$nom,$adresse, $contact,$verset,$slogan))
                createToast('bg-success', 'uil-file-check', 'Nouveau Societe cree', 'Creation d\'un nouveau societe fait!');
                // empty input text
                $(namespace + '#nouveau-societe input#input-nom').val(' ')
                $(namespace + '#nouveau-societe input#input-adresse').val(' ')
                $(namespace + '#nouveau-societe input#input-contact').val(' ')
                $(namespace + '#nouveau-societe input#input-username').val(' ')
                $(namespace + '#nouveau-societe input#input-password').val('')
                $(namespace + '#nouveau-societe input#verset').val('')
                $(namespace + '#nouveau-societe input#slogan').val('')
                $(namespace+"#logo").val("");
            } ,()=>{
                $(namespace+"#company-creation").html(spinner);
                $(namespace+"#company-creation").show();
            },()=>{
               console.log(" :::: Database initialisation error :::: ");
            },()=>{
                $(namespace+"#company-creation").hide();
            });

        };
    })
    /*
     suppression societe
     */
    $(document).on('click', '.btn-desactiver-societe', function(){

        $cardCurrent = $(this).closest('.item-societe').attr('id');

        $idModal = 'desactiver-societe';

        create_confirm_dialog('Suppression Filial', 'Voulez vous vraiment desactiver le societe', $idModal, 'Oui, desactiver', 'btn-danger').on('click', function () {
                $(namespace + '.label-statut').toggleClass('bg-danger')
                $(namespace + '.label-statut').toggleClass('bg-success')

                $(this).text($(this).text() == 'Desactive' ? 'Active' : 'Desactive');
                hideAndRemove($(namespace + '#' + $idModal))
            })

    })
    /*
    HTML CONTENT FILIAL
     */
    function createItemSociete($id, $nom, $adresse, $contact, $sloganI, $sloganII) {
        return `<div id='item-societe-` + $id + `' class="col-3 item-societe">
    <div class="card d-block">
      <div class="card-body">
        <div class="dropdown card-widgets">
          <a href="#" class="dropdown-toggle arrow-none" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="dripicons-dots-3"></i>
          </a>
          <div class="dropdown-menu dropdown-menu-end">
            <!-- item-->
            <a class="dropdown-item"><i class="mdi mdi-cog me-1"></i>Gerer</a>
            <!-- item-->
            <a class="dropdown-item btn-editer-societe"><i class="mdi mdi-pencil me-1"></i>Modifie</a>
            <!-- item-->
            <a class="dropdown-item btn-desactiver-societe"><i class="mdi mdi-delete me-1"></i>Desactive</a>
          </div>
        </div>
        <!-- project title-->
        <h4 class="mt-0">
          <img src="http://localhost:8080/assets/images/logo.png" alt="" class="img-circle logo-entreprise img-fluid">
          <a href="" class="text-title label-nom">` + $nom + `</a>
        </h4>
        <div class="badge bg-danger mb-3 label-statut">Suspendu (activation requis)</div>

        <p class="text-muted font-13 mb-3"><span class="label-adresse">` + $adresse + `</span> - <span class="label-contact">` + $contact + `</span>
        <p class="text-muted font-13 mb-3"><span class="label-slogan-i">"` + $sloganI + `"</span> <br> <span class="label-slogan-ii">"` + $sloganII + `"</span>
        </p>
        <!-- project detail-->
        
        <p class="mb-1">
                                            <span class="pe-2 text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-format-list-bulleted-type text-muted"></i>
                                                <b>00</b> Filial
                                            </span>
                                            
                                            <span class="text-nowrap mb-2 d-inline-block">
                                                <i class="mdi mdi-comment-multiple-outline text-muted"></i>
                                                <b>00</b> Magasin
                                            </span>
        </p>
        
      </div> <!-- end card-body-->
      
      <ul class="list-group list-group-flush">
        <li class="list-group-item p-3">
          <!-- project progress-->
          <p class="mb-2 fw-bold">Recette <span class="float-end">100%</span></p>
          <div class="progress progress-sm">
            <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                 style="width: 100%;">
            </div><!-- /.progress-bar -->
          </div><!-- /.progress -->
        </li>
      </ul>
      
    </div> <!-- end card-->
  </div>
`;

    }
})