$(function () {
    let namespace = "#menu-facture ";
    /*---------------------------------
                MENU FACTURE
     ----------------------------------*/
    $filiale_id = $(namespace + '#filiale-id').attr("value-id");
    $user_id = $(namespace + "#user-id").attr("value-id");
    $client_id = 1;
    $montant = 0;
    $is_modified = false;
    $societe = {
        nom: 'Manantsoa',
        adresse: 'Morafeno',
        slogan: 'MMM',
        contact: '+261',
        ville: 'Toamasina'
    };

    exportToExcel(namespace + '.btn-export-to-excel', 'factures-', namespace + '.table-facture');

    function refreshView() {
        refreshLabelSum()
        _count_table_content(namespace + '.table-facture', namespace + '.text-count-facture');
    }


    _count_table_content(namespace + '.table-facture', namespace + '.text-count-vente');
    /** MENU FACTURE **/

    function on_invoice_info_fetched($vente){
        $(namespace + "#table-facture-avoir tbody").empty();
        let info_article_magasin_tab = $vente.infoArticleMagasin;
        $.each(info_article_magasin_tab,(key, info) =>{
            let tr =[
                `
                          <div class="form-checkbox form-checkbox-danger">
                            <input id="" type="checkbox" class="form-check-input avoir-checkbox" style="display: none" checked>
                          </div>
                `,
                info.article.designation,
                info.unite.designation,
                info.quantiteAjout,
                (info.montantArticle / info.quantiteAjout),
                info.montantArticle
            ];
            let td_class = ['', 'pr-designation', 'pr-unite', 'pr-quantite', 'pr-prix-unitaire', 'pr-montant']
            let row_id = info.magasin.id + "-" + info.article.id + "-" + info.unite.id;
            push_to_table_list(namespace + "#table-facture-avoir",row_id,tr,td_class);
        });
        _count_table_content(namespace + '.table-facture', namespace + '.label-count-facture');
        $(namespace + "#info-facture").attr("vente-id",$vente.id);
    };

    // click of tr, open infos list articles in facture
    function fetchFactureInfo(reference) {
        var url = "http://localhost:8080/api/v1/sales/"+reference;
        execute_ajax_request("get", url, null, (vente)=>{
            if (vente.infoArticleMagasin.length > 0) on_invoice_info_fetched(vente);
            // initialisation de information du detail facture
            $(namespace + "#client-facture").text(vente.client.nom);
            $(namespace + "#date-facture").text(vente.date);
            $(namespace + "#operateur-facture").text(vente.user.nom);
            $(namespace + "#num-facture").text($reference);
            $montant_vente = vente.montantVente;
            $(namespace + "#montant-facture").text($montant_vente);
            $(namespace+".montant-avec-remise").text(vente.montantAvecRemise);
            $(namespace+"#reference").text(vente.refVente);
            clear_table(namespace+"#table-liste-payement");
            append_payement_item(vente.payements,false);
        });
    }

    const append_payement_item = (pa,isNew)=>{
        $total_paye=0;
        pa.forEach(value=>{
            let tr = `
                   <tr id="`+value.id+`">
                        <td nbs>`+value.reference+`</td>
                        <td nbs>`+value.montantPayement+`  `+create_percentage(value.montantPayement)+`</td>
                        <td nbs>`+value.montantRestant+`</td>
                        <td>`+value.modePayement+`</td>
                        <td>`+value.datePayement+`</td>
                        <td>`+create_badge(value.isValid)+`</td>
                        <td>`+value.user.nom+`</td>
                        <td class="td-action crud-article">
                           `+init_cancel_button(value.isValid)+`
                           <a class="btn-sm btn-danger delete-payement"><i class="uil-trash-alt"></i></a>
                        </td>
                   </tr>
                `;
            if (value.modePayement!=="CREDIT") $total_paye+=value.montantPayement;
            $(namespace+"#table-liste-payement tbody").append(tr);
        });
        if (!isNew){
            $(namespace+".montant-total-payer").text($total_paye);
            $montant_total_restant = $montant_vente-$total_paye;
            $(namespace+".montant-total-restant").text($montant_total_restant);
            // INITIALISATION DES POURCENTAGE
            init_label_percentage();
        }
    }

    const init_label_percentage = ()=>{
        $(namespace+".montant-total-payer-pourcentage").text(create_percentage($total_paye));
        $(namespace+".montant-total-restant-pourcentage").text(create_percentage($montant_total_restant));
    };

    const create_percentage = (value)=>{
        return "( "+((value*100)/$montant_vente)+"% )";
    };

    const init_cancel_button = (status)=>{
        return status === true ? '<a class="btn-sm btn-info info-facture cancel-payement"><i class="uil-cancel"></i></a>' : '';
    }

    $(document).on('click',namespace+".cancel-payement",function(){

        let tr = $(this).closest("tr");
        let id = $(tr).attr("id");
        let ref = $(tr).children().eq(0).text();
        let montant = $(tr).children().eq(1).text();
        let typePayement = $(tr).children().eq(3).text();

        let ifc = {};
        ifc.operationCaisse ="DECAISSEMENT";
        ifc.montantOperation = montant;
        ifc.date = new Date();
        ifc.modePayement = typePayement;
        ifc.user = {id:$user_id};
        ifc.filiale = {id : $filiale_id};
        ifc.description = "Annulation du payement "+ref;

        create_confirm_dialog(' Annulation payement ', " Voulez vous annulé le payement ( ref = "+ref+") , cette operation effectue un décaissement d'une montant de "+montant+" Ar", "annulation-payement", "Oui ,"+"supprimer", "btn-danger").on('click', function (){

            let url = "http://localhost:8080/api/v1/payments/"+id+"/status/0";

            execute_ajax_request("PUT",url,null,()=>{
                effectuer_decaissement(ifc);
                hideAndRemove('#annulation-payement');
            });

        });
    });

    const effectuer_decaissement = (ifc)=>{
        let url = "http://localhost:8080/api/v1/ifc";
        execute_ajax_request("post",url,ifc,(data)=>{
            createToast('bg-danger', 'uil-trash-alt', 'Annulation payement', "Payement annul&eacute; avec succ&egrave;s!");
        });
    }

    $(document).on('click',namespace+".delete-payement",function () {
        let tr = $(this).closest("tr");
        let status = $(tr).children().eq(5).text();
        let ref = $(tr).children().eq(0).text();
        if (status==="annulé"){
            create_confirm_dialog('Suppression payement ', " Voulez vous supprim&eacute; le payement ( ref = "+ref+") ", "suppression-payement", "Oui ,"+"supprimer", "btn-danger").on('click', function (){
                let id = $(tr).attr("id");
                let url = "http://localhost:8080/api/v1/payments/"+id;
                execute_ajax_request("delete",url,null,()=>{
                    tr.remove();
                    createToast('bg-success', 'uil-file-check-alt', 'Suppression payement', 'payement supprim&eacute; avec succ&egrave;s!');
                    hideAndRemove('#suppression-payement');
                });
            });
        }else createToast('bg-danger', 'uil-trash-alt', 'Suppression payement', " Impossible de supprimer un payement valid&eacute; ");
    });

    const create_badge =(status)=>{
        return status === true ? `<span class="badge badge-success-lighten">Validé</span>` : `<span class="badge badge-warning-lighten">annulé</span>`;
    }

    $(document).on('click', namespace + '.table-facture tbody tr td a.info-facture', function () {
        $(this).closest('tr').trigger('dblclick')
    })

    function init_payement_and_invoice_btn(selected_tr){
        // INITIALISATION DU BOUTON AVOIR
        let concerned_by_invoice_regulation = $(selected_tr).attr("concerned-by-invoice-regulation");
        if (concerned_by_invoice_regulation === "true") $(namespace + ".btn-creer-avoir").hide();
        else $(namespace + ".btn-creer-avoir").show();
    }

    $(document).on('dblclick', namespace + '.table-facture tbody tr',function(){
        let type_facture = $(this).attr("type-facture");
        if(type_facture==="AVOIR"){

        }else{
            init_payement_and_invoice_btn(this);
            // get reference of dblcliked facture
            $reference = $(this).children()[0].innerText;
            // get reference of selected facture
            let client_td = $(this).children()[1];
            $client_id = $(client_td).attr("client-id");
            let operateur = $(this).children()[3].innerText;
            $date = $(this).children()[4].innerText;
            fetchFactureInfo($reference);
            // hide avoir
            $(namespace + '.avoir-checkbox').hide();
            $(namespace + '.avoir-checkbox-all').hide();
            $(namespace + '.avoir-checkbox').prop('checked', true);
            $(namespace + '.avoir-checkbox-all').prop('checked', true);
            $(namespace + '.btn-valider-avoir').hide();

            $(namespace + '#info-facture').modal('show');
        }
    });

    /** créer un avoir **/
    $(namespace + '.btn-creer-avoir').on('click', function () {
        $(namespace + '.avoir-checkbox-all').toggle();
        $(namespace + '.avoir-checkbox').toggle();
        $(namespace + '.btn-valider-avoir').toggle();
    });

    /** changer mode de payement **/
    $(namespace + '.btn-new-payement').on('click', function() {
        // INITIALISATION DU MONTANT RESTANT
        $(namespace+"#montant").val($montant_total_restant);
        $(namespace + '#mode-payement-modal').modal('show');
    })

    $(namespace + '.avoir-checkbox-all').on('change', function () {
        $(namespace + '.avoir-checkbox').prop('checked', $(this).is(':checked'))
    });

    function get_data_from_table_avoir(ref,date){
        let tab = [];
        let tr_tab = $(namespace + '#table-facture-avoir tbody tr');
        total_montant_avoir = 0;
        $.each(tr_tab,(key, value) => {
            if ($(value).find('.avoir-checkbox').is(':checked')) {
                let info = {};
                let row_id = $(value).attr("id");
                let split = row_id.split("-");
                $magasin_id = split[0];
                let article_id = split[1];
                let unite_id = split[2];
                let quantite = $(value).children().eq(3).text();
                let montant = $(value).children().eq(5).text();
                info.typeOperation = "AVOIR";
                info.magasin = {id: $magasin_id};
                info.user = {id: $user_id};
                info.unite = {id: unite_id};
                info.article = {id: article_id}
                info.montantArticle = montant;
                info.quantiteAjout = quantite;
                info.description = " Avoir sur la facture N°" + $reference;
                info.date = date;
                info.reference = ref;
                tab.push(info);
            }
        })
        return tab;
    }

    function persit_invoice(){

        let date = new Date();
        let ref = create_reference("AVOIR",date);
        $filiale_id = $(namespace + '#filiale-id').attr("value-id");
        $user_id = $(namespace + "#user-id").attr("value-id");
        let invoice = {};

        let ifc = {};
        ifc.operationCaisse = "AVOIR";
        ifc.montantOperation = $sommeAvoir;
        ifc.date = date;
        ifc.reference = ref;
        ifc.modePayement = "ESPECE";
        ifc.user = {id: $user_id};
        ifc.filiale = {id: $filiale_id};
        ifc.description = " Avoir sur la facture "+ ref;

        let vente_id = $(namespace + "#info-facture").attr("vente-id");
        invoice.vente = {id: vente_id};
        invoice.refAvoir = ref;
        invoice.infoArticleMagasin = get_data_from_table_avoir(ref, date);
        invoice.montant = $sommeAvoir;
        invoice.infoFilialeCaisse = ifc;
        invoice.date = date;

        let url = "http://localhost:8080/api/v1/regulations";
        execute_ajax_request("post", url, invoice, (data) => {
            // effectué operation AVOIR
            createToast('bg-danger', 'uil-trash-alt', 'Avoir valid&eacute;', 'Avoir effectu&eacute; avec success!')
            hideAndRemove('#' + $modalId)
            $(namespace + ".btn-creer-avoir").click();
            $(namespace + ".btn-creer-avoir").hide();
        })

    }

    /** VALIDATION AVOIR **/

    $(namespace + '.btn-valider-avoir').on('click',function (){
        $sommeAvoir = 0;
        $(namespace +'#table-facture-avoir tbody tr').each(function (key, value) {
            if ($(value).find('.avoir-checkbox').is(':checked'))
                $sommeAvoir += parseFloat($(value).children().eq(5).text().replace('Ar', ''))
        })
        $content = 'Voulez vous vraiment valider cette avoir ?' +
            '<li> Nombre article annulé: ' + $(namespace + '.avoir-checkbox:checked').length + '</li>' +
            '<li> Somme à rembourser: ' + $sommeAvoir + ' Ar</li>';
        $modalId = "modal-confirm-avoir";
        create_confirm_dialog('Confirmation avoir', $content, $modalId, 'Oui, valider!', 'btn-danger')
            .on("click", function () {
                persit_invoice();
            });
    });

    /** ENREGISTRER MODE DE PAYEMENT **/

    $(namespace + "#save-payement-mode-btn").click(() => {

        $user_id = $(namespace + "#user-id").attr("value-id");
        $filiale_id = $(namespace + '#filiale-id').attr("value-id");

        let type_payement = $(namespace + "#type-payement option:selected").val();
        let vente_id = $(namespace + "#info-facture").attr("vente-id");
        let description = $(namespace + "#description").val();

        $isPourcent = $(namespace + '#montant-pourcentage').is(':checked');

        if ($isPourcent) $montant = $(namespace+"#montant").val();

        let ifc = {};
        ifc.operationCaisse = "ENCAISSEMENT";
        ifc.montantOperation = $montant;
        ifc.date = new Date();
        ifc.modePayement =type_payement;
        ifc.user = {id:$user_id};
        ifc.filiale = {id : $filiale_id};
        ifc.description = description+" (Payement de la facture N°"+$reference+" ) ";

        let pm = {};
        pm.modePayement = type_payement;
        pm.user = {id:$user_id};
        pm.filiale = {id : $filiale_id};
        pm.montantRestant= 0 ;
        pm.montantPayement = $montant;
        pm.datePayement = new Date();
        pm.isValid = true;
        pm.vente = {id : vente_id};

        if (type_payement!=="CREDIT") pm.ifc = ifc;
        let url = "http://localhost:8080/api/v1/payments";

        $(namespace+"#mode-payement-modal").modal("hide");

        execute_ajax_request("post",url,pm, (newPm)=>{
            append_payement_item([newPm],true)
            if (type_payement==="CREDIT") persist_trosa();
            impression_acceptation();
            createToast('bg-success', 'uil-file-check-alt', 'Enregistrement mode de payement', 'payement enregistr&eacute; avec succ&egrave;s!');
        });

    });

    function persist_trosa(){
        $date_echeance = $(namespace + "#date-echeance").val();
        let montant_a_credit = $(namespace + "#montant").val();
        let trosa = {};
        trosa.clientFournisseur = {id: $client_id};
        trosa.montant = $montant;
        trosa.description = "";
        trosa.typeTrosa = "CREDIT";
        trosa.date = $date;
        trosa.dateEcheance = $date_echeance;
        trosa.reste = $montant-montant_a_credit;
        let url = "http://localhost:8080/api/v1/trosas";
        execute_ajax_request("post", url, trosa, (data) => {
            createToast('bg-success', 'uil-check-sign', 'Dette enregistre', 'Nouveau dette enregistre avec success!');
        });
    }

    function impression_acceptation(){
        $infos = {
            titre: $(namespace + '#mode-payement-modal #type-payement option:selected').val(),
            numero_facture: $(namespace + '#info-facture #num-facture').text(),
            date_facture: $(namespace + '#info-facture #date-facture').text(),
            nom_client: $(namespace + '#info-facture #client-facture').text(),
            magasin: $(namespace + "#info-facture #select-magasin option:selected").text(),
            operateur: $('#user-id').attr('value-name'),
            montant_facture: $(namespace + '#info-facture #montant-facture').text(),
            montant_payer: $(namespace + '#mode-payement-modal #montant').val(),
            description: $(namespace + '#mode-payement-modal textarea#description').val(),
            date_echeance : $(namespace + '#mode-payement-modal #date-echeance').val()
        }
        generate_ticket_acceptation($infos.titre, $societe, $infos);
        generate_facture_acceptation($infos.titre, $societe, $infos);
    }

    // impression facture
    $(document).on('click', namespace + '#info-facture .btn-print-facture', function () {
        $infos = {
            nom_client: $(namespace + '#info-facture #client-facture').text(),
            magasin: $(namespace + "#select-magasin option:selected").text(),
            operateur: $(namespace + '#info-facture #operateur-facture').text(),
            numero_facture: $(namespace + '#info-facture #num-facture').text(),
            date_facture: $(namespace + '#info-facture #date-facture').text(),
            remise: 0,
        }
        generate_facture_A5($societe, $infos, namespace + '#table-facture-avoir');
        generate_ticket_caisse($societe, $infos, namespace + '#table-facture-avoir');
    });

    /*
    *       FILTRE
    */

    $(document).on('change',namespace+"#magasin-select",function(){
        let magasin_id = $(this).val();
        if (magasin_id==="TOUTE") $(namespace+"#refresh-btn").click();
        else {
            $filiale_id = $(namespace + '#filiale-id').attr("value-id");
            let url = "http://localhost:8080/api/v1/sales/subsdiary/"+$filiale_id+"/store/"+magasin_id;
            execute_ajax_request("get",url,null,(data)=> {
                append_item(data);
            });
        }
    });

    $(document).on('click',namespace+'#btn-mode-payement',function () {
        $(namespace+"#credit-echeance").hide();
    });

    const init_user_name = (v)=>{
        return   (v===null || v===undefined) ? "inconnu" : v;
    }

    function append_item(data){
        $(namespace + ".table-facture tbody tr").remove();
        data.forEach(value =>{
            let tr = `  
                  <tr type-facture ="FACTURE" concerned-by-invoice-regulation="` + value.isConcernedByInvoiceRegulation + `" payement-mode-modified ="` + value.isPayementModeChanged + `"  id="` + value.refVente + `">
                          <td>` + value.refVente + `</td>
                          <td client-id =` + value.client.id + `>` + value.client.nom + `</td>
                          <td nbs>` + value.montantVente + `</td>
                          <td>` + init_user_name(value.user.nom) + `</td>
                          <td>` + value.date + `</td>
                          <td class="d-flex justify-content-center td-action d-none">
                            <div><a class="btn-sm btn-info info-facture"><i class="uil-info-circle"></i></a></div>
                          </td>
                  </tr>
            `;
            $(namespace + ".table-facture tbody").append(tr);
        });
        _count_table_content(namespace + '.table-facture', namespace + '.text-count-vente');
        refreshLabelSum();
    }

    // ACTUALISER
    $(document).on("click",namespace+"#refresh-btn",()=>{
        let filiale_id = $(namespace + '#filiale-id').attr("value-id");
        let url = "http://localhost:8080/api/v1/sales/subsdiary/"+ filiale_id;
        execute_ajax_request("get",url,null,(data)=> {
            append_item(data);
        });
    });

    $(document).on("keyup",namespace+"#top-search",()=>{
        $(namespace+"#search-btn").click();
    });

    // RECHERCHER PAR DATE
    $(document).on("click",namespace+"#btn-search-filter",()=>{
        let date_debut = $(namespace+"#input-date-debut").val();
        let date_fin = $(namespace+"#input-date-fin").val();
        if (date_debut!==""&& date_fin!==""){
            $filiale_id = $(namespace + '#filiale-id').attr("value-id");
            url = " http://localhost:8080/api/v1/sales/subsdiary/"+$filiale_id+"/between-date/"+date_debut+"/"+date_fin;
            execute_ajax_request("get",url,null,(data)=>append_item(data));
        }
    });

    $(document).on("click",namespace+"#search-btn",function(){
        let text = $(namespace+"#top-search").val();
        let url = "";
        let filiale_id = $(namespace + '#filiale-id').attr("value-id");
        let typeFilter = $(namespace+"#type-filter option:selected").val();

        if (text!==undefined && text!=="") url = "http://localhost:8080/api/v1/sales/subsdiary/"+filiale_id+"/filter-type/"+typeFilter+"/"+text;
        else url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/sales"

        execute_ajax_request("get",url,null,(data)=>{
            append_item(data);
        });

    });

    // montant de vente
    function countSommeVentes(){
        $somme = 0;
        $(namespace + '.table-facture tbody tr').each(function(key, value) {
            $somme += parseFloat(to_js_number($(this).children().eq(2).text()));
        })
        return $somme;
    };

    function refreshLabelSum() {
        $(namespace + '.label-sum-facture').text(countSommeVentes())
    };

    // on change mode de payment
    $(namespace + '#mode-payement-modal #type-payement').on('change',function() {
        if ($(this).val() === 'CREDIT') $(namespace + '#mode-payement-modal #credit-echeance').show()
        else $(namespace + '#mode-payement-modal #credit-echeance').hide()
    })

    // RAFRAICHIR LA LISTE DES PAYEMENTS
    $(document).on('click',namespace+".btn-refresh-payement-list",function(){
        var url = "http://localhost:8080/api/v1/sales/"+$reference;
        execute_ajax_request("get", url, null, (vente)=>{
            clear_table(namespace+"#table-liste-payement");
            append_payement_item(vente.payements,false);
        });
    });

    /*
    *   DEBUT EVENT RADIO BUTTON
    */

    // SELECTION POURCENTAGE

    $(document).on("click",namespace+"#montant-pourcentage",function (){
        $(namespace+"#montant-en-pourcentage-div").show();
    })

    $(document).on("click",namespace+"#montant-valeur",function () {
        $(namespace+"#montant-en-pourcentage-div").hide()
    })

    /*
    *  FIN EVENT RADIO BUTTON
    */

    /**
     *  DEBUT EVENT INPUT
     * */

    $(document).on("keyup",namespace+"#montant",function () {
        $isPourcent = $(namespace + '#montant-pourcentage').is(':checked');
        if ($isPourcent){
           let pourcentage = $(namespace+"#montant").val();
           $(namespace+"#pourcentage-entrer").text(pourcentage);
           $montant = (pourcentage*$montant_total_restant)/100;
           $(namespace+"#valeur-restant-en-pourentage").text($montant);
        }
    });

    /**
     *  FIN EVENT INPUT
     * */
    $(namespace+"#montant-en-pourcentage-div").hide();

    refreshView();
})