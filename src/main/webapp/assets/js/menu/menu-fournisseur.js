$(function () {
    /*--------------------------
        MENU FOURNISSEUR
     ----------------------------*/
    let namespace = "#menu-fournisseur ";
    let cfUrl = "http://localhost:8080/api/v1/externalEntities";
    let idCf = 1;
    let NOUVEAU_FOURNISSEUR = true;
    $montant_reste = 0;
    $filialeId = $(namespace + '#filiale-id').attr("value-id");
    $user_id = $(namespace+"#user-id").attr("value-id");
    exportToExcel(namespace + '.btn-export-to-excel','fournisseurs', namespace + '#table-fournisseur')

    // $(document).bind('keydown', 'enter', function(){
    //     $(namespace + '.btn-nouveau-fournisseur').trigger('click')
    // });

    /* fermer l'info listes article facture */
    $(namespace + '.btn-close-info-credit').click(function () {
        $(namespace + '#info-credit').removeClass("show")
    })
    /* nouveau fournisseur */
    $(namespace + '.btn-nouveau-fournisseur').on('click', function () {
        $(namespace + '#nouveau-fournisseur').attr('data-value', 'nouveau-fournisseur');
        $(namespace + '#nouveau-fournisseur').modal('show');
        $(namespace + '#nouveau-fournisseur .modal-title').text('Nouveau Fournisseur');
        // REINITIALISATION DU FORMULAIRE
        $(namespace + '#nouveau-fournisseur input#nom').val("");
        $(namespace + '#nouveau-fournisseur input#adresse').val("");
        $(namespace + '#nouveau-fournisseur input#contact').val("");
        NOUVEAU_FOURNISSEUR = true;
    })
    /* editer fournisseur */
    $(document).on('click', namespace + '.editFournisseur', function (){
        $(namespace + '#nouveau-fournisseur').attr('data-value', 'editer-fournisseur');
        $(namespace + '#nouveau-fournisseur').modal('show')

        $(namespace + '#nouveau-fournisseur .modal-title').text('Editer Fournisseur');
        $trFournisseur = $(this).closest('tr');
        idCf = $trFournisseur.attr("id");
        $(namespace + '#nouveau-fournisseur input#nom').val($trFournisseur.children().eq(0).text());
        $(namespace + '#nouveau-fournisseur input#adresse').val($trFournisseur.children().eq(1).text());
        $(namespace + '#nouveau-fournisseur input#contact').val($trFournisseur.children().eq(2).text());
        NOUVEAU_FOURNISSEUR = false;
    });

    function enregistrer_client_ou_fournisseur(fournisseur){
        let cfResourceUrl = NOUVEAU_FOURNISSEUR ? cfUrl :cfUrl+"/"+idCf;
        let methodType = NOUVEAU_FOURNISSEUR ? "POST" : "PUT";
        execute_ajax_request(methodType,cfResourceUrl,fournisseur,(data)=>{
            if (NOUVEAU_FOURNISSEUR){
                $fournisseur = [data.nom,data.adresse,data.numTel,0, $('<div class="action-fournisseur">\n' +
                    '                <a id="" class="btn-sm btn-info editFournisseur "><i class="uil-pen"></i></a>\n' +
                    '                <a id="" class="btn-sm btn-danger deleteFournisseur "><i class="uil-trash-alt"></i></a>\n' +
                    '              </div>')];
                push_to_table_list(namespace + '#table-fournisseur',data.id, $fournisseur);
                createToast('bg-success', 'uil-icon-check', 'Fournisseur enregistre', 'Fournisseur enregistre avec succes!');
            }else {
                $trFournisseur.children().eq(0).text(fournisseur.nom);
                $trFournisseur.children().eq(1).text(fournisseur.adresse);
                $trFournisseur.children().eq(2).text(fournisseur.numTel);
                createToast('bg-success', 'uil-icon-check', 'Modification Fournisseur enregistre', 'Modification Fournisseur enregistre avec succes!');
            }
            $(namespace + '#nouveau-fournisseur input').val('');
            NOUVEAU_FOURNISSEUR = true;
        })
    }
    /* enregistrement nouveau fournisseur */
    $(function() {
        $(namespace + 'form').validate({
            rules : {
                nom : {required : true},
                adresse : {required : true},
                contact : {required : false}
            },
            messages : {
                nom : {required : 'Nom du fournisseur requis'},
                adresse: {required : 'Adresse du fournisseur requis'},
                contact : {required : 'Contact requis'}
            }
        })
        $(namespace + 'form input#contact').mask('+261 99 99 999 99')
    })

    function validation_nouveau_founisseur() {
        $(namespace + 'form').validate();
        return $(namespace + 'form').valid();
    }

    $(namespace + '#nouveau-fournisseur #btn-enregistrer-fournisseur').on('click', function () {
        if (validation_nouveau_founisseur()) {
            let nomFournisseur = $(namespace + '#nouveau-fournisseur input#nom').val();
            let adresse = $(namespace + '#nouveau-fournisseur input#adresse').val();
            let contact = $(namespace + '#nouveau-fournisseur input#contact').val();
            let fr = {};
            fr.nom = nomFournisseur;
            fr.adresse = adresse;
            fr.numTel = contact;
            fr.type = 1;
            fr.filiale = {id : $filialeId };
            enregistrer_client_ou_fournisseur(fr)
            $("#nouveau-fournisseur").modal('hide')
        }

    })
    /* click fournisseur tr */
    $(document).on('click', namespace + '#table-fournisseur tbody tr', function () {
        // get reference of selected facture
        $trFournisseur = $(this);
        $(namespace + "#info-credit").addClass("show")
    })

    /* suppression fournisseur */
    $(document).on('click', namespace + '#table-fournisseur .deleteFournisseur', function () {
        $trFournisseur = $(this).closest('tr');
        let name = $($trFournisseur).children().eq(0).text();
        let id = $(this).closest('tr').attr("id");
        $idModalDelete = "suppression-fournisseur";
        create_confirm_dialog('Suppression Fournisseur', 'Voulez vous vraiment supprimer le fournisseur '+name+' ?', $idModalDelete, 'Oui,supprimer', 'btn-danger')
            .on('click', function () {
                let url = "http://localhost:8080/api/v1/externalEntities/"+id;
                execute_ajax_request('DELETE',url,null,(data)=>{
                    $trFournisseur.remove();
                    createToast('bg-danger', 'uil-trash-alt', 'Suppression fait', 'Le fournisseur est supprime avec success!');
                    hideAndRemove('#' + $idModalDelete);
                    $(namespace + "#info-credit").removeClass("show")
                })
            })
    });


    /* NOUVEAU DETTE */
    $(namespace + '.btn-nouveau-dette').on('click', function() {
        $(namespace + '#nouveau-dette input#nomFournisseur').val($trFournisseur.children().eq(0).text())
    });

    // INIT DOUBLE CLICK
    init_dblclick_table(namespace,"#table-fournisseur");

    init_modal_credit_dette_btn(namespace,$filialeId,$user_id);
    /*
    *  RECHERCHER FOURNISSEUR
    * */
    init_seach_cf_btn(1,namespace,"#table-fournisseur",$filialeId);
})