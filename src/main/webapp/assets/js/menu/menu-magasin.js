
$(function () {
    let namespace = "#menu-magasin ";
    let clientUrl = "http://localhost:8080/api/v1/magasins";
    let NOUVEAU_UTILISATEUR = true;
    /*-------------------------
            MENU MAGASIN
     --------------------------*/
    // export

    exportToExcel(namespace + '.btn-export-to-excel','listes-utilisateurs-magasin', namespace + '#table-liste-utilisateur-magasin')

    // constante
   const NOUVEAU = 'nouveau', EDITION = 'edition';
    /*
     click de bouton nouveau
     */

    $(namespace + '#btn-nouveau-magasin').on('click', function () {
        $(namespace + '#new-magasin').attr('data-type', NOUVEAU);
        $(namespace + '#new-magasin .modal-title').html('Nouveau magasin');
    })


    /*-------------------------------------------------------

    Enregistrement nouveau magasin ou edition d'un magasin

    --------------------------------------------------------- */

    /*

     mask et validation

      */

    $(function () {
        $(namespace + 'form').validate({
            rules: {
                nomMagasin: {required: true},
                adresseMagasin: {required: true}
            },
            messages: {
                nomMagasin: {required: "Nom du magasin requis"},
                adresseMagasin: {required: "Adresse du magasin requis"}
            }
        })
    })

    function validation_nouveau_magasin() {
        $(namespace + 'form').validate();

        return $(namespace + 'form').valid();
    }

    $(namespace + '#btn-enregistrer-magasin').on('click', function () {
        if (validation_nouveau_magasin()) {
            let nomMagasin = $(namespace + '#nom-magasin').val();
            let adresseMagasin = $(namespace + '#adresse-magasin').val();
            let filialeId = $(namespace + '#filiale-id').attr("value-id");
            $newMagasin = {
                adresse : adresseMagasin,
                nomMagasin : nomMagasin,
                filiale : {
                    id : filialeId
                }
            };
            NOUVEAU_UTILISATEUR = $(namespace + '#new-magasin').attr('data-type') === NOUVEAU;
            let magasinResourcesUrl = NOUVEAU_UTILISATEUR ? clientUrl :clientUrl+"/"+$idCf;
            $methodType = NOUVEAU_UTILISATEUR ? "POST" : "PUT";
            $.ajax({
                type: $methodType,
                url: magasinResourcesUrl,
                contentType: 'application/json',
                data: JSON.stringify($newMagasin),
                success: function (data) {
                    $newMagasin = data;
                    /* ACTION */
                    $tdActionContent = $(' ' + '<div class="d-inline-flex justify-content-center">' + '<a href="#" class="delete-magasin"><i class="uil-trash-alt"></i></a>' + '<a href="#" class="edit-magasin"><i class="uil-pen"></i></a>' + '</div>');
                    $oneMagasin = [nomMagasin,adresseMagasin, $tdActionContent];
                    if (NOUVEAU_UTILISATEUR) {
                        push_to_table_list("#table-liste-magasin",data.id,$oneMagasin);
                        createToast('bg-success', 'uil-file-check', 'Creation Fait', 'Creation d\'un nouveau magasin effectu&eacute; avec succ&egrave;s!')
                    }

                    // EDITION MAGASIN OPERATION

                    else{
                        update_to_table_list(namespace + '#table-liste-magasin', $(namespace + '#new-magasin').attr('data-id'), $oneMagasin);
                        createToast('bg-success', 'uil-pen', 'Modification Fait', 'Modification du magasin effectu&eacute; avec succ&egrave;s!')
                    }

                    $(namespace + '#new-magasin input').val(''); // empty input
                    $(namespace + '#new-magasin').modal('hide'); // close modal
                }
            });
        }
    });

    /*
     EDITION MAGASIN ON-CLICK
     */

    $(document).on('click', namespace + 'a.edit-magasin', function () {
        $(namespace + '#new-magasin').modal('show');
        $trContent = $(this).closest('tr')
        $idCf = $trContent.attr('id');
        $(namespace + '#new-magasin .modal-title').html('Edition d\'un magasin');
        $(namespace + '#new-magasin input#nom-magasin').val($trContent.children().eq(0).text());
        $(namespace + '#new-magasin input#adresse-magasin').val($trContent.children().eq(1).text());
        $(namespace + '#new-magasin').attr('data-type', EDITION);
        $(namespace + '#new-magasin').attr('data-id', $trContent.attr('id')); // id of current tr element
    })

    /*
     SUPPRESSION MAGASIN ON-CLICK
     */

    $(document).on('click', namespace + 'a.delete-magasin',function (){

        $currentTR = $(this).closest('tr');
        $modalID = 'suppression-modal';
        let idMagasin = $currentTR.attr('id');
        $contentDialog = '' + 'Voulez vous vraiment supprimez ce magasin ??' + '<li>' + $currentTR.children().eq(0).text() + ' (' + $currentTR.children().eq(1).text() + ')</li>';
        create_confirm_dialog('Suppression magasin', $contentDialog, $modalID, 'Oui , Supprimer', 'btn-outline-danger')
            .on('click', function () {
                $.ajax({
                    type: "DELETE",
                    url: clientUrl+"/"+idMagasin,
                    contentType: 'application/json',
                    success: function (data) {
                        $currentTR.remove()
                        $('#table-liste-utilisateur-magasin tbody tr').remove();
                        $(namespace + '#' + $modalID + '').modal('hide');
                        $(namespace + '#' + $modalID + '').remove();
                        createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait', 'Suppression du magasin effectu&eacute; avec succ&egrave;s!')
                    }
                    });
            })

    })

    /*
    CLICK MAGASIN EVENT, SHOW ALL USERS OF THIS MAGASIN
     */

    function getAllUserByMagasinId($idMagasin){
        let magasinResourcesUrl = clientUrl+"/"+$idMagasin+"/users";
        $.ajax({
            type: "GET",
            url: magasinResourcesUrl,
            contentType: 'application/json',
            success : function (data) {
                $(namespace+"#table-liste-utilisateur-magasin tbody").empty();
                 $.each(data,(key,value)=>{
                     let tr = [value.nom,value.fonction.nomFonction];
                     push_to_table_list(namespace+"#table-liste-utilisateur-magasin",key,tr);
                 })
            }
        });
    }

    $(document).on('click', namespace + '#table-liste-magasin tbody tr', function () {
        // Charger les listes d'utilisateur de ce magasin
        let idMagasin = $(this).attr('id');

        // Supprimer toutes les elements dans la table

        $(namespace + '#table-liste-utilisateur-magasin tbody tr').remove();

        // affichage des utilisateur

        getAllUserByMagasinId(idMagasin);

    })
})