$(function () {
    $fonctionUrl = "http://localhost:8080/api/v1/fonctions";
    $userUrl = "http://localhost:8080/api/v1/users";
    $NEW_USER = true;
    let namespace = "#menu-utilisateur "
    let filiale_id = $(namespace +'#filiale-id').attr("value-id");
    let user_id = 0;

        /*--------------------------------------------------------------------------
                               MENU UTILISATEUR
        ------------------------------------------------------------------------- */

    /*----------------------------------------------------------------------------
                        NOUVELLE FONCTION
    ------------------------------------------------------------------------ */

    exportToExcel(namespace + '.btn-export-to-excel','utilisateurs', namespace + '#table-liste-utilisateur')

    let NEW = 'nouveau', EDIT = 'editer';

    $(namespace + '.btn-nouvelle-fonction').on('click', function () {
        $(namespace + '#nouvelle-fonction').modal('show');
        $(namespace + '#nouvelle-fonction').attr('data-value', NEW);
        $(namespace + '#nouvelle-fonction .modal-title').text('Nouvelle Fonction');

        $(namespace + '#nouvelle-fonction input#libelle-fonction').val('')
    })

    /* enregistrement du nouvelle fonction */

    /* mask et validation */

    $(namespace + '#nouvelle-fonction form').validate({
        rules : {
            libelle : {required : true}
        },
        messages : {
            libelle : {required : 'Libelle de la fonction requis'}
        }
    })

    function validation_nouvelle_fonction() {
        $(namespace + '#nouvelle-fonction form').validate()
        return $(namespace + '#nouvelle-fonction form').valid()
    }


    $(namespace + '#btn-enregistrer-fonction').on('click', function(){
        if (validation_nouvelle_fonction()) {
            let libelle = $(namespace + '#nouvelle-fonction input#libelle-fonction').val();
            let dataValue = $(namespace + '#nouvelle-fonction').attr('data-value');
            let filialeId = $(namespace + '#filiale-id').attr("value-id");
            $isNew = dataValue === NEW;
            $fonctionResourcesUrl = $isNew ? $fonctionUrl :$fonctionUrl+"/"+$idfonction;
            $methodType = $isNew ? "POST" : "PUT";
            $nouveauFonction = {
                nomFonction : libelle,
                filiale : {id : filialeId},
                defaultPage : 0
            }
            execute_ajax_request($methodType,$fonctionResourcesUrl,$nouveauFonction,function (data){
                if ($isNew){
                    $td = [libelle, $actionNouvelleFonctionMenuUtilisateur]
                    push_to_table_list(namespace + '#table-liste-fonction',data.id,$td);
                    createToast('bg-success', 'uil-check-sign', 'Enregistement Fait', 'Nouvelle fonction enregistre avec success!')
                }else{
                    $trEdit.children().eq(0).text(libelle)
                    createToast('bg-success', 'uil-check-sign', 'Modification fait', 'Modification du fonction fait!')
                }
                $(namespace + '#nouvelle-fonction input#libelle-fonction').val('')
                load_select_fonction()
            });
            $(namespace + '#nouvelle-fonction').modal('hide')
        }
    })
    /* editer fonction, click */

    $(document).on('click', namespace + '.edit-fonction', function () {

        $trEdit = $(this).closest('tr');
        $idfonction = $trEdit.attr('id');
        $(namespace + '#nouvelle-fonction').modal('show');
        $(namespace + '#nouvelle-fonction').attr('data-value', EDIT);
        $(namespace + '#nouvelle-fonction .modal-title').text('Editer Fonction');
        $(namespace + '#nouvelle-fonction input#libelle-fonction').val($trEdit.children().eq(0).text());
    })

    /*
     supprimer foonction, click
     */

    $(document).on('click', namespace + '.delete-fonction', function () {
        $trDelete = $(this).closest('tr')
        $modalId = 'suppression-fonction'
        $idfonction = $trDelete.attr('id');
        create_confirm_dialog('Suppression Fonction', 'Voulez vous vraiment supprimer cette fonction ?<li>' + $trDelete.children().eq(0).text() + '</li>', $modalId, 'Oui, supprimer!', 'btn-danger')
            .on('click', function () {
                let url =$fonctionUrl+"/"+$idfonction;
                execute_ajax_request("DELETE",url,null,()=>{
                    $trDelete.remove();
                    hideAndRemove(namespace + '#' + $modalId)
                    load_select_fonction()
                    createToast('bg-danger', 'uil-trash-alt', 'Suppression fait!', 'Fonction supprime avec success!')
                })
            })
    })

    /*----------------------------------------------------------------------
                         NOUVEAU UTILISATEUR
     ----------------------------------------------------------------------*/

    function load_select_fonction() {

        $(namespace + '#nouveau-utilisateur #select-fonction option').remove();
        $(namespace + '#table-liste-fonction tbody tr').each(function(index, tr) {
            push_select_option_value([$(tr).attr('id'), $(tr).children().eq(0).text()], namespace + '#nouveau-utilisateur #select-fonction')
        })

    }
    load_select_fonction();
    /*
     enregistrement d'un nouveau utilisateur
     */

    /*
   mask et validation
    */

    $(namespace + '#nouveau-utilisateur form #input-contact').mask('+261 99 99 999 99')

    $(namespace + '#nouveau-utilisateur form').validate({
        rules : {
            nomEtPrenoms : {required : true},
            adresse : {required : true},
            contact : {required : true},
            fonction : {required : true},
            magasin : {required : true},
            username : {required : true, minlength : 4},
            password : {required : true, minlength : 4}
        },
        messages : {
            nomEtPrenoms : {required : 'Nom et prenoms requis'},
            adresse : {required : 'Adresse requis'},
            contact : {required : 'Contact requis'},
            fonction : {required : 'Fonction requis'},
            magasin : {required : 'Magasin requis'},
            username : {required : 'Nom d\'utilisateur requis', minlength : 'Nom d\'utilisateur trop court (min 04 caractères)'},
            password : {required : 'Mot de passe requis', minlength : 'Mot de passe trop court (min 04 caractères)'}
        }
    })

    function validation_nouveau_utilisateur() {
        $(namespace + '#nouveau-utilisateur form').validate();
        return $(namespace + '#nouveau-utilisateur form').valid();
    }

    let magasinIdTab = [];
    $(namespace + "#nouveau-utilisateur #btn-enregistrer-utilisateur").on('click', function() {
        if (validation_nouveau_utilisateur()) {
            $nom = $(namespace + "#nouveau-utilisateur #input-nom").val();
            $adresse = $(namespace + "#nouveau-utilisateur #input-adresse").val();
            $contact = $(namespace + "#nouveau-utilisateur #input-contact").val();
            $username = $(namespace + "#nouveau-utilisateur #input-username").val();
            $password = $(namespace + "#nouveau-utilisateur #input-password").val();
            $(namespace + "#nouveau-utilisateur #select-magasin option:selected").each(function (key, value){
                magasinIdTab.push({id: $(value).val()});
            });
            $filiale_id = $(namespace + '#filiale-id').attr("value-id");
            $fonctionId = $(namespace + "#nouveau-utilisateur #select-fonction option:selected").val();
            $fonctionNom = $(namespace + "#nouveau-utilisateur #select-fonction option:selected").text();
            $statut = $(namespace + "#nouveau-utilisateur #check-statut").is(':checked')
            $newUser = {
                nom: $nom,
                adresse: $adresse,
                numTel: $contact,
                username: $username,
                password: $password,
                enabled: $statut,
                fonction: {
                    id: $fonctionId,
                },
                magasin: magasinIdTab,
                filiale: {id: $filiale_id},
                enabled : $statut
            };
            let methodType = $NEW_USER ? "POST" : "PUT";
            let userRessourceUrl = $NEW_USER ? $userUrl : $userUrl + '/' + user_id;
            execute_ajax_request(methodType,userRessourceUrl,$newUser, (data) => {
                let title  = $NEW_USER ? 'Utilisateur enregistré' : 'Utilisateur modifié';
                let content = $NEW_USER ? 'Nouveau utilisateur enregistré avec success!' : 'Modification enregistré avec succès ';
                if ($NEW_USER) append_user_item([data]);
                createToast('bg-success', 'uil-check-sign', title, content)
                // empty all
                $(namespace + "#nouveau-utilisateur input").val("");
                $('#nouveau-utilisateur select#select-fonction option:first').prop('selected', true);
                $('#nouveau-utilisateur select#select-magasin option:first').prop('selected', true);
                $('#nouveau-utilisateur #check-statut').prop('checked', true)
                $NEW_USER = true;
            });
            $(namespace + '#nouveau-utilisateur').modal('hide')
        }
    })

    /*
     suppression utilisateur
     */

    $(document).on('click', namespace + "#table-liste-utilisateur .delete-utilisateur", function(){

        $trUtilisateur = $(this).closest('tr');
        $userId = $trUtilisateur.attr("id");
        let nomEtPrenoms = $trUtilisateur.children().eq(1).text();
        $modalId = 'supprimer-utilisateur';
        create_confirm_dialog('Suppression Utilisateur', 'Voulez vraiment supprimer cet utilisateur ? <li>' + nomEtPrenoms + '</li>', $modalId, 'Oui, supprimer', 'btn-danger')
            .on('click', function() {
                $methodType = "DELETE";
                execute_ajax_request($methodType,$userUrl+"/"+$userId,null,(data)=>{
                    $trUtilisateur.remove();
                    hideAndRemove(namespace + '#' + $modalId);
                    createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait!', 'Utilisateur supprime avec success!');
                });
            })
    })

    /* NOUVEAU UTILISATEUR */
    $(namespace+"#btn-nouveau-utilisateur").click(()=>{
        $(namespace+".modal-title").text(" Nouveau utilisateur ");
        $(namespace+"#username-and-password").show();
        $(namespace + "#nouveau-utilisateur #input-nom").val("");
        $(namespace + "#nouveau-utilisateur #input-adresse").val("");
        $(namespace + "#nouveau-utilisateur #input-contact").val("");
        $(namespace + "#nouveau-utilisateur #input-username").val("");
        $(namespace + "#nouveau-utilisateur #input-password").val("");
        $NEW_USER = true;
    });

    /*
     MODIFIER UTILISATEUR
     */

    $(document).on('click', namespace + "#table-liste-utilisateur .edit-utilisateur",function(){
        $NEW_USER  = false;
        $trEdit = $(this).closest('tr');
        user_id= $(this).closest('tr').attr("id");
        init_form(user_id);
    });

    const init_form = ($userId)=>{
        let url = "http://localhost:8080/api/v1/users/"+$userId;
        execute_ajax_request("get",url,null,(data)=>{
            $(namespace + "#nouveau-utilisateur #input-nom").val(data.nom);
            $(namespace + "#nouveau-utilisateur #input-adresse").val(data.adresse);
            $(namespace + "#nouveau-utilisateur #input-contact").val(data.numTel);
            $(namespace + "#nouveau-utilisateur #input-username").val(data.username);
            $(namespace + "#nouveau-utilisateur #input-password").val(data.password);
            init_magasin(data.magasin)
        });
        $(namespace+".modal-title").text(" Editer utilisateur ");
        // $(namespace+"#username-and-password").hide();
        $(namespace + '#nouveau-utilisateur').modal('show');
    }

    const init_magasin = (magasinTab)=>{
        let options =  $(namespace + "#nouveau-utilisateur #select-magasin option");
        $.each(options,(key,option_value)=>{
            let option_value_id = $(option_value).val();
            magasinTab.forEach(user_magasin =>{
                   if (user_magasin.id==option_value_id){
                       $(option_value).click();
                   }
            })
        })
    }

    /*
     on click function for filter
     */

    $(document).on('click', namespace + '#table-liste-fonction tbody tr' ,function() {
        filterFunctionEvent($(this).children().eq(0).text());
    })

    $(document).on('click', namespace + '.toutes-fonctions', function() {
        $(namespace + '#table-liste-utilisateur tbody tr').show();
    })

    function filterFunctionEvent($dataFilter){
        $(namespace + '#table-liste-utilisateur tbody tr').hide();
        if ($dataFilter === null) $(namespace + '#table-liste-utilisateur tbody tr').show();
        else {
            $(namespace + '#table-liste-utilisateur tbody tr').each(function(key, value) {
                if ($(value).children('.function-user').text() === $dataFilter) $(value).show();
            })
        }
    }
    /*
     filter all
     */
    $(namespace + '.function-filter-all').on('click', function() {
        filterFunctionEvent(null)
    });

    const init_status = (status) =>{
        return status ? `<span class="badge badge-success-lighten">activé</span>` : `<span class="badge badge-danger-lighten">desactivé</span>`;
    }

    $(document).on("click",namespace+"#search-btn",()=>{
        let text = $(namespace+"#top-search").val();
        if (text!==""){
            let url = "http://localhost:8080/api/v1/users/name/"+filiale_id+"/"+text;
            execute_ajax_request("get",url,null,(data)=> {
                clear_table(namespace+"#table-liste-utilisateur");
                append_user_item(data);
            });
        }else $(namespace+"#refresh-btn").click();
    })

    $(document).on("keyup",namespace+"#top-search",()=>$(namespace+"#search-btn").click())

    const check_value = (value) =>{
        return value === null ? "" : value;
    }

    const append_user_item = (data)=>{
        data.forEach(value=>{
          let tr = `
              <tr id="${value.id}">
              <td>`+check_value(value.nom)+`</td>
              <td>`+value.username+`</td>
              <td>`+check_value(value.numTel)+`</td>
              <td class="function-user">`+value.fonction.nomFonction+`</td>
              <td>`+init_status(value.enabled)+`</td>
              <td class="td-action crud-utilisateur">
                <div class="d-flex justify-content-center">
                  <a class="btn-sm btn-primary edit-utilisateur"><i class="uil-pen"></i></a>&nbsp;
                  <a class="btn-sm btn-danger delete-utilisateur"><i class="uil-trash-alt"></i></a>
                </div>
              </td>
            </tr>
            `;
            $(namespace+"#table-liste-utilisateur tbody").append(tr);
        })
    }

    $(document).on("click",namespace+"#refresh-btn",()=>{
        let url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/users";
        execute_ajax_request("get",url,null,(data)=> {
            clear_table(namespace+"#table-liste-utilisateur");
            append_user_item(data)
        });
    });

})