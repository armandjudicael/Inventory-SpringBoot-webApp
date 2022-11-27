// $(function () {
//     /* NEW EMBARQUEMENT */
//     let namespace = "#nouveau-embarquement ";
//     let filialeId = $(namespace + '#filiale-id').attr("value-id");
//     let VOYAGE_TAB = [];
//     /*------------------------------------------------------------------------------
//                                            SELECTER ARTICLE
//     -------------------------------------------------------------------------------*/
//     init_dbl_click_table(namespace,"table-liste-article");
//     /*------------------------------------------------------------------------------
//         AJOUT DUN ARTICLE
//     -------------------------------------------------------------------------------*/
//     $('.btn-ajouter-article').on('click', function (){
//
//         $fId = $(namespace + '#select-fournisseur option:selected').val();
//         $userId = $(namespace + '#user-id').attr("value-id");
//         // Unité
//         $unite = $(namespace + '#select-unite-article option:selected').text();
//         $uniteId = $(namespace + '#select-unite-article option:selected').val();
//         // ARTICLE
//         $designation = $(namespace + '#designation-article').val();
//         $articleId = $(namespace + '#designation-article').attr("value-id");
//
//         $quantite = $(namespace + '#input-quantite-article').val();
//         $prix_achat_article = $(namespace + '#input-prix-achat-article').val();
//
//         $prix_vente_article = $(namespace + '#input-prix-vente-article').val();
//
//         $datePeremption = $(namespace+"#input-date-peremption").val();
//
//         let poid = $(namespace+"#designation-article").attr("POIDS");
//
//         $poids = parseFloat(poid) * parseInt($quantite);
//
//         $article_embarquement = [$designation, $unite, $quantite,$poids,$prix_achat_article,$prix_vente_article,$datePeremption];
//
//         $row_id = $articleId+"-"+$uniteId+"-"+$fId+"-"+$userId;
//
//         push_to_table_list(namespace + '#table-liste-article-embarquement',$row_id, $article_embarquement);
//
//         // vider form vente
//         $('.form-vente input').each(function () {
//             if ($(this).attr('id') != 'name-client') $(this).attr('value', '');
//             if ($(this).attr('type') === 'number') $(this).val(0);
//         });
//
//         $(namespace + '#designation-article').text("");
//         $(namespace + '#input-quantite-article').val("");
//         $(namespace + '#input-prix-achat-article').val("");
//         $(namespace + '#input-prix-vente-article').val("");
//         $(namespace + '#select-unite-article option').remove();
//
//         // vide option
//         $(namespace + "#input-unite-article option").remove();
//     })
//
//     /* QUANTITE POIDS CALCUL */
//
//     $(namespace + '#input-quantite-article').on('change', function () {
//         let default_poids = 1.01;
//         $poids = default_poids * parseInt($(this).val());
//         $(namespace + '.label-poids-article').text($poids)
//     })
//
//     /*------------------------------------------------------------------------------
//                                             SUPPRESSION D'UN ARTICLE
//      -------------------------------------------------------------------------------*/
//
//     $(namespace + '#table-liste-article-embarquement tbody tr').on('dblclick', function () {
//         $(this).remove();
//     })
//
//     // enregistrement du vente
//
//     $sommeVente = 0;
//     $countArticle = 0;
//
//     $content = '' +
//         'Voulez vous vraiment enregistrer ce vente? <br><br>' +
//         '<li>Nombre d\'article : <strong>' + $countArticle + '</strong></li>' +
//         '<li>Somme : <strong>' + $sommeVente + ' Ar</strong></li>' +
//         '';
//
//     const getInfoFromVoyageTable = (ref,date,numFacture)=>{
//         let tr_tab = $(namespace + '#table-liste-article-embarquement tbody tr');
//         let tab = [];
//         $.each(tr_tab,(key,value)=>{
//
//             let row_id = $(value).attr("id");
//             let split = row_id.split("-");
//
//             let article_id = split[0];
//             let unite_id = split[1];
//             let frs_id = split[2];
//             let user_id = split[3];
//
//             let quantite = $(value).children().eq(2).text();
//             let poids = $(value).children().eq(3).text();
//             let prix_transport = $(value).children().eq(4).text();
//             let prix_achat = $(value).children().eq(5).text();
//             let peremption = $(value).children().eq(6).text();
//
//             let info = {};
//             info.fournisseur = {id: frs_id};
//             info.user = {id: user_id};
//             info.unite = {id: unite_id};
//             info.article = {id: article_id};
//             info.quantite = quantite;
//             info.date = date;
//             info.poids = poids;
//             info.prixTransport = prix_transport;
//             info.prixAchat = prix_achat;
//             info.reference = ref;
//             info.numFacture = numFacture;
//             info.datePeremption = peremption;
//             console.log(info);
//             tab.push(info);
//         })
//         return tab;
//     };
//
//     $(namespace + '.form-embarquement .btn-enregistrer-embarquement').on('click', function () {
//         $modalId = 'confirmation-embarquement';
//         create_confirm_dialog('Confirmation embarquement', $content, $modalId, 'Enregistrer', 'btn-primary')
//             .on('click', function () { // button de validation
//                 let voyage = {};
//                 let materiel_transport_id = $(namespace+"#input-moyen-de-transport option:selected").val();
//                 let trajet_id = $(namespace+"#select-trajet option:selected").val();
//                 let reference = $(namespace+"#input-reference").val();
//                 let numFacture = $(namespace+"#input-facture").val();
//                 let date = new Date();
//                 voyage.materielTransport = {id : materiel_transport_id};
//                 voyage.reference = reference;
//                 voyage.infoArticleVoyages = getInfoFromVoyageTable(reference,date,numFacture);
//                 voyage.statutVoyage = 0;
//                 voyage.trajet = {id : trajet_id};
//                 voyage.dateCreation = date;
//                 let url = " http://localhost:8080/api/v1/voyages";
//                 execute_ajax_request("post",url,voyage,(voyage)=>{
//                     createToast('bg-success', 'uil-file-check-alt', 'Enregistrement voyage', 'Voyage enregistr&eacute; avec succ&egrave;s!')
//                 });
//                 // vider table
//                 $(namespace + '#table-liste-article-embarquement tbody tr').remove();
//                 $(namespace + '#' + $modalId).modal('hide');
//             });
//     });
//     /*--------------------------------------------
//                         TRANSPORT
//      --------------------------------------------*/
//     /* Nouveau materiel de transport */
//
//     $(namespace + "#btn-nouveau-moyen-de-transport").on('click', function () {
//         $(namespace + "#nouveau-materiel-de-transport").modal('show');
//     })
//
//     /* Click button enregistrer materiel de transport */
//     $(namespace + "#btn-enregistrer-materiel-de-transport").on('click', function () {
//         $reference = $(namespace + '#reference').val();
//         $typeMateriel = $(namespace + '#type-materiel option:selected').val();
//         $materielTransport = {};
//         $materielTransport.reference = $reference;
//         $materielTransport.typeMateriel = $typeMateriel;
//         enregistrerMaterielTransport($materielTransport);
//     })
//
//     // Enregistrement materiel de transport
//     function enregistrerMaterielTransport($object){
//         let materieltransportURL = 'http://localhost:8080/api/v1/materieltransport';
//         execute_ajax_request("post",materieltransportURL,$object,(data)=>{
//             //reset the input
//             $(namespace + '#nouveau-materiel-de-transport input').val('');
//             set_select_option_value([data.id, data.reference], namespace + '#input-moyen-de-transport');
//             $(namespace + '#input-moyen-de-transport').val(data.id).change();
//             createToast('bg-success', 'uil-file-check-alt', ' Enregistrement moyen de transport ', 'Moyen enregistr&eacute; avec succ&egrave;s!')
//         });
//     };
//     /*------------------------------------------
//                     FOURNISSEUR
//     --------------------------------------------*/
//     // Nouveau Fournisseur
//     $(namespace + "#btn-nouveau-fournisseur").on('click', function () {
//         $(namespace + "#nouveau-fournisseur").modal('show')
//     })
//
//     // Click button enregistrer fournisseur
//     $(namespace + "#btn-enregistrer-fournisseur").on('click', function () {
//         $nom = $(namespace + '#nom').val();
//         $adresse = $(namespace + '#adresse').val();
//         $contact = $(namespace + '#contact').val();
//         $fournisseur = {};
//         $fournisseur.nom = $nom;
//         $fournisseur.adresse = $adresse;
//         $fournisseur.numTel = $contact;
//         $fournisseur.type = 1;
//         $fournisseur.filiale = {id : filialeId };
//         enregistrerClientOuFournisseur($fournisseur);
//     })
//
//     /* Enregistrement materiel de transport */
//     function enregistrerClientOuFournisseur($object) {
//         let clientOuFournisseurURL = 'http://localhost:8080/api/v1/externalEntities';
//         execute_ajax_request("post",clientOuFournisseurURL,$object,(data)=>{
//             //reset the input
//             $(namespace + '#nouveau-fournisseur input').val('');
//             set_select_option_value([[data.id,data.nom]], namespace + '#select-fournisseur');
//             $(namespace + '#select-fournisseur').val(data.id).change();
//         })
//     }
//
//     /* TRAJET */
//     $(document).on("click",namespace+"#btn-enregistrer-trajet",()=>{
//                 let t_destination = $(namespace+"#destination-input").val();
//                 let t_depart = $(namespace+"#depart-input").val();
//                 let trajet = {};
//                 trajet.depart = t_depart;
//                 trajet.destination = t_destination;
//                 let url = "http://localhost:8080/api/v1/trajets";
//                 execute_ajax_request("post",url,trajet,(trajet)=>{
//                     let option = `<option value="`+trajet.id+`">`+trajet.depart+` - `+trajet.destination+`</option>`;
//                     $(namespace+"#select-trajet").append(option);
//                     $(namespace + '#select-trajet').val(trajet.id).change();
//                     createToast('bg-success', 'uil-file-check-alt', ' Enregistrement Trajet ', 'Trajet enregistr&eacute; avec succ&egrave;s!')
//                 });
//     });
//
// })

$(function () {
    /* NEW EMBARQUEMENT */
    let namespace = "#nouveau-embarquement ";
    let filialeId = $(namespace + '#filiale-id').attr("value-id");
    let VOYAGE_TAB = [];
    /*------------------------------------------------------------------------------
                                           SELECTER ARTICLE
    -------------------------------------------------------------------------------*/
    init_dbl_click_table(namespace,"table-liste-article");
    /*------------------------------------------------------------------------------
        AJOUT DUN ARTICLE
    -------------------------------------------------------------------------------*/
    $('.btn-ajouter-article').on('click', function (){

        $fId = $(namespace + '#select-fournisseur option:selected').val();
        $userId = $(namespace + '#user-id').attr("value-id");
        // Unité
        $unite = $(namespace + '#select-unite-article option:selected').text();
        $uniteId = $(namespace + '#select-unite-article option:selected').val();
        // ARTICLE
        $designation = $(namespace + '#designation-article').val();
        $articleId = $(namespace + '#designation-article').attr("value-id");

        $quantite = $(namespace + '#input-quantite-article').val();
        $prix_achat_article = $(namespace + '#input-prix-achat-article').val();

        $prix_vente_article = $(namespace + '#input-prix-vente-article').val();

        $datePeremption = $(namespace+"#input-date-peremption").val();

        let poid = $(namespace+"#designation-article").attr("POIDS");

        $poids = parseFloat(poid) * parseInt($quantite);

        $article_embarquement = [$designation, $unite, $quantite,$poids,$prix_achat_article,$prix_vente_article,$datePeremption];

        $row_id = $articleId+"-"+$uniteId+"-"+$fId+"-"+$userId;

        push_to_table_list(namespace + '#table-liste-article-embarquement',$row_id, $article_embarquement);

        // vider form vente
        $('.form-vente input').each(function () {
            if ($(this).attr('id') != 'name-client') $(this).attr('value', '');
            if ($(this).attr('type') === 'number') $(this).val(0);
        });

        $(namespace + '#designation-article').text("");
        $(namespace + '#input-quantite-article').val("");
        $(namespace + '#input-prix-achat-article').val("");
        $(namespace + '#input-prix-vente-article').val("");
        $(namespace + '#select-unite-article option').remove();

        // vide option
        $(namespace + "#input-unite-article option").remove();
    })

    /* QUANTITE POIDS CALCUL */

    $(namespace + '#input-quantite-article').on('change', function () {
        let default_poids = 1.01;
        $poids = default_poids * parseInt($(this).val());
        $(namespace + '.label-poids-article').text($poids)
    })

    /*------------------------------------------------------------------------------
                                            SUPPRESSION D'UN ARTICLE
     -------------------------------------------------------------------------------*/

    $(namespace + '#table-liste-article-embarquement tbody tr').on('dblclick', function () {
        $(this).remove();
    })

    // enregistrement du vente

    $sommeVente = 0;
    $countArticle = 0;

    $content = '' +
        'Voulez vous vraiment enregistrer ce vente? <br><br>' +
        '<li>Nombre d\'article : <strong>' + $countArticle + '</strong></li>' +
        '<li>Somme : <strong>' + $sommeVente + ' Ar</strong></li>' +
        '';

    const getInfoFromVoyageTable = (ref,date,numFacture)=>{
        let tr_tab = $(namespace + '#table-liste-article-embarquement tbody tr');
        let tab = [];
        $.each(tr_tab,(key,value)=>{

            let row_id = $(value).attr("id");
            let split = row_id.split("-");

            let article_id = split[0];
            let unite_id = split[1];
            let frs_id = split[2];
            let user_id = split[3];

            let quantite = $(value).children().eq(2).text();
            let poids = $(value).children().eq(3).text();
            let prix_transport = $(value).children().eq(4).text();
            let prix_achat = $(value).children().eq(5).text();
            let peremption = $(value).children().eq(6).text();

            let info = {};
            info.fournisseur = {id: frs_id};
            info.user = {id: user_id};
            info.unite = {id: unite_id};
            info.article = {id: article_id};
            info.quantite = quantite;
            info.date = date;
            info.poids = poids;
            info.prixTransport = prix_transport;
            info.prixAchat = prix_achat;
            info.reference = ref;
            info.numFacture = numFacture;
            info.datePeremption = peremption;
            console.log(info);
            tab.push(info);
        })
        return tab;
    };

    $(namespace + '.form-embarquement .btn-enregistrer-embarquement').on('click', function () {
        $modalId = 'confirmation-embarquement';
        create_confirm_dialog('Confirmation embarquement', $content, $modalId, 'Enregistrer', 'btn-primary')
            .on('click', function () { // button de validation
                let voyage = {};
                let materiel_transport_id = $(namespace+"#input-moyen-de-transport option:selected").val();
                let trajet_id = $(namespace+"#select-trajet option:selected").val();
                let reference = $(namespace+"#input-reference").val();
                let numFacture = $(namespace+"#input-facture").val();
                let date = new Date();
                voyage.materielTransport = {id : materiel_transport_id};
                voyage.reference = reference;
                voyage.infoArticleVoyages = getInfoFromVoyageTable(reference,date,numFacture);
                voyage.statutVoyage = 0;
                voyage.trajet = {id : trajet_id};
                voyage.dateCreation = date;
                let url = " http://localhost:8080/api/v1/voyages";
                execute_ajax_request("post",url,voyage,(voyage)=>{
                    createToast('bg-success', 'uil-file-check-alt', 'Enregistrement voyage', 'Voyage enregistr&eacute; avec succ&egrave;s!')
                });
                // vider table
                $(namespace + '#table-liste-article-embarquement tbody tr').remove();
                $(namespace + '#' + $modalId).modal('hide');
            });
    });
    /*--------------------------------------------
                        TRANSPORT
     --------------------------------------------*/
    /* Nouveau materiel de transport */

    $(namespace + "#btn-nouveau-moyen-de-transport").on('click', function () {
        $(namespace + "#nouveau-materiel-de-transport").modal('show');
    })

    /* Click button enregistrer materiel de transport */
    $(namespace + "#btn-enregistrer-materiel-de-transport").on('click', function () {
        $reference = $(namespace + '#reference').val();
        $typeMateriel = $(namespace + '#type-materiel option:selected').val();
        $materielTransport = {};
        $materielTransport.reference = $reference;
        $materielTransport.typeMateriel = $typeMateriel;
        enregistrerMaterielTransport($materielTransport);
    })

    // Enregistrement materiel de transport
    function enregistrerMaterielTransport($object){
        let materieltransportURL = 'http://localhost:8080/api/v1/materieltransport';
        execute_ajax_request("post",materieltransportURL,$object,(data)=>{
            //reset the input
            $(namespace + '#nouveau-materiel-de-transport input').val('');
            set_select_option_value([data.id, data.reference], namespace + '#input-moyen-de-transport');
            $(namespace + '#input-moyen-de-transport').val(data.id).change();
            createToast('bg-success', 'uil-file-check-alt', ' Enregistrement moyen de transport ', 'Moyen enregistr&eacute; avec succ&egrave;s!')
        });
    };
    /*------------------------------------------
                    FOURNISSEUR
    --------------------------------------------*/
    // Nouveau Fournisseur
    $(namespace + "#btn-nouveau-fournisseur").on('click', function () {
        $(namespace + "#nouveau-fournisseur").modal('show')
    })

    // Click button enregistrer fournisseur
    $(namespace + "#btn-enregistrer-fournisseur").on('click', function () {
        $nom = $(namespace + '#nom').val();
        $adresse = $(namespace + '#adresse').val();
        $contact = $(namespace + '#contact').val();
        $fournisseur = {};
        $fournisseur.nom = $nom;
        $fournisseur.adresse = $adresse;
        $fournisseur.numTel = $contact;
        $fournisseur.type = 1;
        $fournisseur.filiale = {id : filialeId };
        enregistrerClientOuFournisseur($fournisseur);
    })

    /* Enregistrement materiel de transport */
    function enregistrerClientOuFournisseur($object) {
        let clientOuFournisseurURL = 'http://localhost:8080/api/v1/externalEntities';
        execute_ajax_request("post",clientOuFournisseurURL,$object,(data)=>{
            //reset the input
            $(namespace + '#nouveau-fournisseur input').val('');
            set_select_option_value([[data.id,data.nom]], namespace + '#select-fournisseur');
            $(namespace + '#select-fournisseur').val(data.id).change();
        })
    }

    /* TRAJET */
    $(document).on("click",namespace+"#btn-enregistrer-trajet",()=>{
        let t_destination = $(namespace+"#destination-input").val();
        let t_depart = $(namespace+"#depart-input").val();
        let trajet = {};
        trajet.depart = t_depart;
        trajet.destination = t_destination;
        let url = "http://localhost:8080/api/v1/trajets";
        execute_ajax_request("post",url,trajet,(trajet)=>{
            let option = `<option value="`+trajet.id+`">`+trajet.depart+` - `+trajet.destination+`</option>`;
            $(namespace+"#select-trajet").append(option);
            $(namespace + '#select-trajet').val(trajet.id).change();
            createToast('bg-success', 'uil-file-check-alt', ' Enregistrement Trajet ', 'Trajet enregistr&eacute; avec succ&egrave;s!')
        });
    });

})