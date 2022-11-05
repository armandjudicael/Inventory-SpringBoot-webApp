$(function () {
    let namespace = "#menu-voyage ";
    voyageURL = "http://localhost:8080/api/v1/voyages";

    /*-----------------------

            MENU VOYAGE

    ------------------------- */


    exportToExcel(namespace + '.btn-export-to-excel','voyages', namespace + '#table-liste-voyage')


    const NOUVEAU = 'nouveau', EDITION = 'edition';

    /*
     click de bouton nouveau
     */

    $(namespace + '#btn-nouveau-voyage').on('click', function () {

        $(namespace + '#nouveau-voyage').modal('show')
        $(namespace + '#nouveau-voyage').attr('data-type', NOUVEAU);
        $(namespace + '#nouveau-voyage .modal-title').html('Nouveau voyage');

    })


    /*-------------------------------------------------------

    Enregistrement nouveau voyage ou edition d'un voyage

    --------------------------------------------------------- */


    /*

    mask et validation

     */

    $(namespace + '#nouveau-voyage form').validate({
        rules : {
            reference : {required : true},
            materielDeTransport : {required : true},
            dateVoyage : {required : true}
        },
        messages : {
            reference : {required : 'Reference du voyage requis'},
            materielDeTransport  : {required : 'Materiel de transport requis'},
            dateVoyage : {required : 'Date du voyage requis'}
        }
    })

    function validation_nouveau_voyage() {
        $(namespace + '#nouveau-voyage form').validate();

        return $(namespace + '#nouveau-voyage form').valid();
    }

    $(namespace + '#btn-enregistrer-voyage').on('click', function () {

        if (validation_nouveau_voyage()) {
            // $reference = $(namespace + '#nouveau-voyage #reference').val()
            // $materielTransport = $(namespace + '#nouveau-voyage #materielDeTransport option:selected').val()
            // $dateVoyage = $(namespace + '#nouveau-voyage #dateVoyage').val()
            // $description = $(namespace + '#nouveau-voyage #description').val()
            //
            // $voyage = {};
            //
            //
            // $newVoyage = {
            //     adresse : $adresseVoyage,
            //     nomVoyage : $nomVoyage,
            //     filiale : {
            //         id : $filialeId
            //     }
            // };
            // NOUVEAU_UTILISATEUR = $(namespace + '#nouveau-voyage').attr('data-type') === NOUVEAU;
            // $voyageResourcesUrl = NOUVEAU_UTILISATEUR ? voyageURL :voyageURL+"/"+$idCf;
            // $methodType = NOUVEAU_UTILISATEUR ? "POST" : "PUT";
            // $.ajax({
            //     type: $methodType,
            //     url: $voyageResourcesUrl,
            //     contentType: 'application/json',
            //     data: JSON.stringify($newVoyage),
            //     success: function (data) {
            //         $newVoyage = data;
            //         /* ACTION */
            //         $tdActionContent = $(' ' + '<div class="d-inline-flex justify-content-center">' + '<a href="#" class="delete-voyage"><i class="uil-trash-alt"></i></a>' + '<a href="#" class="edit-voyage"><i class="uil-pen"></i></a>' + '</div>');
            //         $oneVoyage = [$nomVoyage, $adresseVoyage, $tdActionContent];
            //         if (NOUVEAU_UTILISATEUR) {
            //             push_to_table_list("#table-liste-voyage",data.id,$oneVoyage);
            //             createToast('bg-success', 'uil-file-check', 'Creation Fait', 'Creation d\'un nouveau voyage effectu&eacute; avec succ&egrave;s!')
            //         }
            //         // EDITION MAGASIN OPERATION
            //         else{
            //             console.log(" UPDATE ");
            //             update_to_table_list(namespace + '#table-liste-voyage', $(namespace + '#nouveau-voyage').attr('data-id'), $oneVoyage);
            //             createToast('bg-success', 'uil-pen', 'Modification Fait', 'Modification du voyage effectu&eacute; avec succ&egrave;s!')
            //         }
            //         $(namespace + '#nouveau-voyage input').val(''); // empty input
            //         $(namespace + '#nouveau-voyage').modal('hide'); // close modal
            //     }
            // });

            $(namespace + '#nouveau-voyage').modal('hide')
        }

    });

    /*
     EDITION MAGASIN ON-CLICK
     */

    $(document).on('click', namespace + 'a.edit-voyage', function () {

        $(namespace + '#nouveau-voyage').modal('show');
        $trContent = $(this).closest('tr');
        $idCf = $trContent.attr('id');
        $(namespace + '#nouveau-voyage .modal-title').html('Edition d\'un voyage');
        $(namespace + '#nouveau-voyage input#nom-voyage').val($trContent.children().eq(0).text());
        $(namespace + '#nouveau-voyage input#adresse-voyage').val($trContent.children().eq(1).text());
        $(namespace + '#nouveau-voyage').attr('data-type', EDITION);
        $(namespace + '#nouveau-voyage').attr('data-id', $trContent.attr('id')); // id of current tr element

    })

    // SUPPRESSION MAGASIN ON-CLICK

    $(document).on('click', namespace + 'a.delete-voyage',function (){

        $currentTR = $(this).closest('tr');
        $modalID = 'suppression-modal';
        let idVoyage = $currentTR.attr('id');
        $contentDialog = '' + 'Voulez vous vraiment supprimez ce voyage ??' + '<li>' + $currentTR.children().eq(0).text() + ' (' + $currentTR.children().eq(1).text() + ')</li>';
        create_confirm_dialog('Suppression voyage', $contentDialog, $modalID, 'Oui , Supprimer', 'btn-outline-danger')
            .on('click', function () {
                $.ajax({
                    type: "DELETE",
                    url: voyageURL+"/"+idVoyage,
                    contentType: 'application/json',
                    success: function (data) {
                        $currentTR.remove()
                        $('#table-liste-utilisateur-voyage tbody tr').remove();
                        $(namespace + '#' + $modalID + '').modal('hide');
                        $(namespace + '#' + $modalID + '').remove();
                        createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait', 'Suppression du voyage effectu&eacute; avec succ&egrave;s!')
                    }
                });
            })

    })

    /*
    CLICK MAGASIN EVENT, SHOW ALL USERS OF THIS MAGASIN
     */

    function getAllUserByVoyageId($idVoyage){

        $voyageResourcesUrl = voyageURL+"/"+$idVoyage+"/users";
        $.ajax({
            type: "GET",
            url: $voyageResourcesUrl,
            contentType: 'application/json',
            success : function (data) {voyage
                for (let i = 0; i < data.length; i++) {
                    $userInfo = data[i].split(",");
                    push_to_table_list_voyage($userInfo);
                }
            }
        });
    }

    $(document).on('click', namespace + '#table-liste-voyage tbody tr', function () {

        // Charger les listes d'utilisateur de ce voyage

        $idVoyage = $(this).attr('id');

        // Supprimer toutes les elements dans la table

        $(namespace + '#table-liste-utilisateur-voyage tbody tr').remove();

        // affichage des utilisateur

        getAllUserByVoyageId($idVoyage);

    })
})