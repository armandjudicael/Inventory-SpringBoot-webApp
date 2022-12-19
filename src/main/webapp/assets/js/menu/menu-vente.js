$(function () {
    /* -------------------------------------------------------------------------------
                                       MENU VENTE SCRIPT
    -------------------------------------------------------------------------------- */
    $quantite_stock = 0;
    $remise_global = 0;
    $remise_article = 0;
    $prix_article_reel = 0;
    $prix_remise_article = 0;
    let namespace = "#menu-vente ";
    $filiale_id = $(namespace + '#filiale-id').attr("value-id");
    push_Type_paiement(namespace + "#select-payement");
    _count_table_content(namespace + '#table-liste-client', namespace + '.text-count-client-vente');
    /* Selecter client */
    function clearForm() {
        $(namespace + '#nouveau-client input#cif').val("");
        $(namespace + '#nouveau-client input#stat').val("");
        $(namespace + '#nouveau-client input#nif').val("");
        $(namespace + '#nouveau-client input#contact').val("");
        $(namespace + '#nouveau-client input#adresse').val("");
        $(namespace + '#nouveau-client input#numCIN').val("");
        $(namespace + '#nouveau-client input#nomClient').val("");
    }
    // ENREGISTRER NOUVEAU CLIENT
    /* mask et validation */
    $(function() {
        $(namespace + 'form#form-nouveau-client').validate({
            rules: {
                adresse : {required:true},
                numCIN : {required:true},
                nomClient: {required:true},
                contact : {required: true},
            },
            messages : {
                adresse: {required: 'Adresse requise'},
                numCIN : {required: 'Numero CIN requis'},
                nomClient: {required: 'Nom client requis'},
                contact : {required: 'Contact requis'}
            }
        })
        $(namespace + 'form#form-nouveau-client #numCIN').mask('999 999 999 999')
        $(namespace + 'form#form-nouveau-client #contact').mask('+261 99 99 999 99')
    })

    function validation_nouveau_client_article(){
        $(namespace + 'form#form-nouveau-client').validate();
        return $(namespace + 'form#form-nouveau-client').valid();
    };

    $(namespace + '#nouveau-client #btn-enregistrer-client').on('click', function(){
        if (validation_nouveau_client_article()) {
            let filialeId = $(namespace + '#filiale-id').attr("value-id");
            let nomClient = $(namespace + '#nouveau-client input#nomClient').val();
            let cin = $(namespace + '#nouveau-client input#numCIN').val();
            let adresse = $(namespace + '#nouveau-client input#adresse').val();
            let contact = $(namespace + '#nouveau-client input#contact').val();
            let nif = $(namespace + '#nouveau-client input#nif').val();
            let stat = $(namespace + '#nouveau-client input#stat').val();
            let cif = $(namespace + '#nouveau-client input#cif').val();
            let client = {};
            client.nom = nomClient;
            client.cin = cin;
            client.adresse = adresse;
            client.numTel = contact;
            client.nif = nif;
            client.stat = stat;
            client.cif = cif;
            client.typeCf = 0;
            client.filiale = {id: filialeId};
            let url = "http://localhost:8080/api/v1/externalEntities";
            execute_ajax_request("post", url, client, (data) => {
                get_select_affect_to_input(namespace + '#name-client',data.id,data.nom)
                $(namespace+"#nouveau-client").modal("hide");
                // insertion dans la liste des clients du nouveau client enregistrer
                let tr= [data.nom,data.adresse,data.numTel];
                push_to_table_list(namespace+"#table-liste-client",data.id,tr);
            });
            clearForm();
        }
    })

    $(document).on('dblclick',namespace +'#table-liste-client tbody tr', function () {
        get_select_affect_to_input(namespace + '#name-client', $(this).attr('id'),$(this).children().eq(0).text());
        $(namespace + '#modal-liste-client').modal('hide');
    })
    /*------------------------------------------------------------------------------
                                            SELECTER ARTICLE
    -------------------------------------------------------------------------------*/
    $(document).on('dblclick', namespace + '#table-liste-article tbody tr', function () {
        let tr_id = $(this).attr("id");
        let article_id = tr_id.split("-")[0];
        let unite_id = tr_id.split("-")[1];
        let quantite_stock = parseFloat($(this).children().eq(3).text());
        $prix_article = $(this).children().eq(4).text();
        let filialeId = $(namespace + '#filiale-id').attr("value-id");
        get_select_affect_to_input(namespace + '#designation-article',article_id,$(this).children().eq(0).text());
        $(namespace + '#input-unite-article option').remove();
        set_select_option_value([unite_id, $(this).children().eq(1).text()], namespace + "#input-unite-article");
        /* AFFECTATION DU PRIX UNITAIRE */
        $(namespace + "#input-prix-unitaire").val($prix_article);
        get_select_affect_to_input(namespace + "#input-prix-unitaire", "", $(this).children().eq(5).text());
        $prix_article_reel = $(namespace + "#input-prix-unitaire").val();
        $(namespace + '#modal-liste-article').modal('hide');
        // new rules for quantité article
        $(namespace + 'form#form-vente #input-quantite-article').rules('remove', 'max');
        $(namespace + 'form#form-vente #input-quantite-article').rules('add',{ max: quantite_stock});
    });
    /*------------------------------------------------------------------------------
                                            PRIX- SPECIAL ARTICLE
     -------------------------------------------------------------------------------*/
    $(namespace + '#btn-prix-special').on('click', function() {
        $(namespace + '#modal-prix-special-article').modal('show');
        $(namespace + '#input-prix-special-article').val($(namespace + '#input-prix-unitaire').val());
    })

    $(namespace + '.btn-enregistrer-modal-article').on('click', function() {
        $new_price_article = $(namespace + '#input-prix-special-article').val();
        $prix_remise_article = $new_price_article;
        $(namespace + '#input-prix-unitaire').val($prix_remise_article);
        $(namespace + '#modal-prix-special-article').modal('hide');
    })

    $(namespace + '#modal-prix-special .btn-enregistrer-modal').on('click',function (){
        $sommeMontant = 0;
        $(namespace + '#table-liste-article-vente tbody tr').each(function (key, value) {
            $sommeMontant += parseFloat($(value).children().eq(4).text());
        })
        let type_remise = $(namespace+"#modal-prix-special").attr("type-remise");
        $isPourcent = $(namespace + '#check-prix-special-remise').is(':checked');
        $current_price = $(namespace + '#input-prix-unitaire').val();
        $special_value = $(namespace + '#input-prix-special').val();
        $special_price_final = $isPourcent ? $sommeMontant * ($special_value / 100) : $special_value;
        if (type_remise === "global"){
            $(namespace+"#remise-global").text(" Montant remise global :"+$special_price_final+" Ar");
            $remise_global = $special_price_final;
            $sommeMontant -= $special_price_final;
        }else get_select_affect_to_input(namespace + '#input-prix-unitaire', null, $special_price_final);
        $(namespace + '#modal-prix-special').modal('hide');
    })

    /*------------------------------------------------------------------------------
                                            SUPPRESSION D'UN ARTICLE
     -------------------------------------------------------------------------------*/

    $(namespace + '#table-liste-article-vente tbody tr').on('dblclick', function () {
        $(this).remove();
    })

    /* mask et validation */
    $(namespace + 'form#form-vente').validate({
        rules: {
            designation: {required: true},
            unite: {required: true},
            inputQuantiteArticle: {required: true, min: 0.0001,max : 0.0001, number: true},
            inputPrixUnitaire: {required: true, min: 0.0001, number: true},
        },messages: {
            designation: {required: ''},
            unite: {required: 'Unite d\'article requis'},
            inputQuantiteArticle: {required: 'Quantite non valide', min: "Quantite doit d\'être >0", number: true},
            inputPrixUnitaire: {required: '', min: '', number: true},
        }
    });

    function validation_ajout_article() {
        $(namespace + 'form#form-vente').validate();
        return $(namespace + 'form#form-vente').valid();
    }

    $('.btn-ajouter-article-vente').on('click',function (){
        if (validation_ajout_article()) {
            $articleId = $(namespace + '#designation-article').attr('value-id');
            $designation = $(namespace + '#designation-article').val();
            $unite = $(namespace + '#input-unite-article option:selected').text();
            $uniteId = $(namespace + '#input-unite-article option:selected').val();
            $quantite = $(namespace + '#input-quantite-article').val();
            $prix_unitaire = $(namespace + '#input-prix-unitaire').val();
            $magasinId = $(namespace + '#select-magasin').val();
            $montant = $quantite * $prix_unitaire;
            $article_vente = [$designation, $unite, $quantite, $prix_unitaire, $montant];
            $article_class = ['pr-designation', 'pr-unite', 'pr-quantite', 'pr-prix-unitaire', 'pr-montant']
            $row_id = $magasinId+"-"+$articleId+"-"+$uniteId;

            $prix_remise_article = $prix_article_reel - $prix_unitaire;
            $remise_article += $prix_remise_article * $quantite;

            push_to_table_list(namespace + '#table-liste-article-vente',$row_id, $article_vente, $article_class);
            // vider form vente
            $('.form-vente input').each(function () {
                if ($(this).attr('id') !== 'name-client') $(this).attr('value', '');
                if ($(this).attr('type') === 'number') $(this).val(0);
            });
            // vide option
            $(namespace + "#input-unite-article option").remove();
            //impression_vente()
            updateLabelFooter()
        }
    });

    /* enregistrement du vente */
    $(namespace + '.form-vente .btn-enregistrer-vente').on('click', function () {
        if (validation_enregistrement_vente()) {
            $(namespace + '#validation-vente-modal').modal('show')
            $countArticl = $(namespace + '#table-liste-article-vente tbody tr').length;
            $sommeMontant = 0;
            $(namespace + '#table-liste-article-vente tbody tr').each(function (key, value) {
                $sommeMontant += parseFloat($(value).children().eq(4).text());
            });
            $(namespace + "#nb-article").text(" Nombre d'article : " + $countArticle);
            $(namespace + "#montant-vente").text(" Montant vente : " + $sommeMontant + " Ar");
            $(namespace + "#remise-global").text(" Montant remise global : " + $remise_global + " Ar");
            $(namespace + "#remise-partiel").text(" Montant remise partiel : " + $remise_article + " Ar");
            $nArticle = $(namespace + '#table-liste-article-vente tbody tr').length;
            if ($remise_article !== 0) $(namespace + '#btn-remise-global').hide();
        } else createToast('bg-warning', 'uil-check-file', 'Enregistrement impossible', 'Veuillez vérifier que le client, Magasin et les articles enregistrées sont valides!')
    });

    $(namespace+"#enregistrer-vente-btn").on('click',()=> enregistrer_vente());

    const getDataFromTable = (ref,date,user_id)=>{
        let tr_tab = $(namespace + '#table-liste-article-vente tbody tr');
        let tab = [];
        $.each(tr_tab,(key,value)=>{
            let row_id = $(value).attr("id");
            let split = row_id.split("-");
            $magasin_id = split[0];
            let article_id = split[1];
            let unite_id = split[2];
            let info = {};
            let quantite = $(value).children().eq(2).text();
            let montant = $(value).children().eq(4).text();
            info.typeOperation = "VENTE";
            info.magasin = {id: $magasin_id};
            info.user = {id: user_id};
            info.unite = {id: unite_id};
            info.article = {id: article_id}
            info.quantiteAjout = quantite;
            info.date = date;
            info.reference = ref;
            info.montantArticle = montant;
            tab.push(info);
        })
        return tab;
    };

    const enregistrer_vente = ()=>{

            $clientId = $(namespace + '#name-client').attr("value-id");
            $filiale_id = $(namespace + '#filiale-id').attr("value-id");

            let nom_client = $(namespace + '#name-client').val();
            let user_id = $(namespace + '#user-id').attr("value-id");
            let date = new Date();
            let ref = create_reference("VENTE",date);

            let ifc = {};
            ifc.operationCaisse = "FACTURE";
            ifc.montantOperation = $sommeMontant;
            ifc.date = date;
            ifc.modePayement ="ESPECE";
            ifc.user = {id:user_id};
            ifc.filiale = {id : $filiale_id};
            ifc.magasin = {id :$magasinId};
            ifc.reference = ref;
            ifc.description = " Facture N°"+ref+" du client " + nom_client;

            let pm = {};
            pm.modePayement = "ESPECE";
            pm.user = {id:user_id};
            pm.filiale = {id : $filiale_id};
            pm.montantRestant =0;
            pm.montantPayement = $sommeMontant;
            pm.datePayement = new Date();
            pm.isValid = true;
            pm.ifc = ifc;

            let $vente = {};
            $vente.infoArticleMagasin = getDataFromTable(ref,date,user_id);
            $vente.client = {id: $clientId};
            $vente.montantVente = $sommeMontant;
            $vente.refVente = ref;
            $vente.remise = $remise_global;
            $vente.isPayementModeChanged = false;
            $vente.filiale = {id : $filiale_id};
            $vente.user = {id:user_id};
            $vente.date = date;
            $vente.payements = [pm];

            let url = "http://localhost:8080/api/v1/sales";
            execute_ajax_request("post",url,$vente, (data) =>{
                // persist_payements(data.id,pm)
              //  impresion
                impression_vente();
                empty_all()
                $(namespace + '#table-liste-article-vente tbody tr').remove();
                updateLabelFooter();
                createToast('bg-success', 'uil-file-check-alt', 'Vente Fait', 'Vente enregistr&eacute; avec succ&egrave;s!')
            });

            $(namespace+"#validation-vente-modal").modal("hide");
            $(namespace+"#input-prix-special").val(0);
    }

    const persist_payements = (vente_id,pm)=>{
        pm.vente = { id : vente_id};
        let url = "http://localhost:8080/api/v1/payments";
        execute_ajax_request("post",url,pm,(data)=>{
            console.log(data);
        })
    }

    function impression_vente() {
        $societe = {
            nom : 'Manantsoa',
            slogan : 'Manantsoa, Mora, Mandroso',
            adresse : 'Morafeno, Toamasina',
            ville : 'Toamasina',
            contact : '+261 34 56 456 89',
            nif : '123 456 789 0',
            stat : '1234 5678 7 12345'
        };
        $infos = {
            numero_facture : '-',
            date_facture : new Date(),
            magasin_vente : $magasin = $(namespace + "#select-magasin option:selected").text(),
            nom_client : $(namespace + "#name-client").val(),
            operateur : $(namespace + '#user-id').attr('value-name'),
            remise : $remise_global,
            remise_partiel : $remise_article
        }
        generate_facture_A5($societe, $infos, namespace + '#table-liste-article-vente');
        generate_ticket_caisse($societe, $infos, namespace + '#table-liste-article-vente');
    };

    function updateLabelFooter(){
        $countArticle = $(namespace + '#table-liste-article-vente tbody tr').length;
        $sommeMontant = 0
        $(namespace + '#table-liste-article-vente tbody tr').each(function (key, value) {
            $sommeMontant += parseFloat($(value).children().eq(4).text());
        })
        $(namespace + '.label-nombre-article').text($countArticle)
        $(namespace + '.label-somme-ariary').text($sommeMontant)
        $(namespace + '.label-somme-fmg').text($sommeMontant * 5)
    };

    function empty_all() {
        $(namespace + '#btn-remise-global').show();
        $remise_article = 0;
        $remise_global = 0;
        $prix_article_reel = 0;
        $prix_remise_article = 0;
    }

    function validation_enregistrement_vente() {
        return !(!$(namespace + '#name-client').val() || !$(namespace + '#select-magasin').val() || $(namespace + '#table-liste-article-vente tbody tr').length === 0)
    }

    init_remise_btn(namespace,"#btn-remise-global",'global');

    /* INITIALISATION DES EVENT DES MODAL */
    init_modal_on_showing("FOURNISEUR_OU_CLIENT",namespace,"modal-liste-client","table-liste-client",$filiale_id,0);

    let ARTICLE_TAB = [];

    // init_input_search_keyup("ARTICLE",
    //     namespace+"#input-article-search",
    //     namespace+"#table-liste-article",$filiale_id,ARTICLE_TAB);

    /* INIT LIST ARTICLE MODAL */

    $(document).on("shown.bs.modal",namespace+"#modal-liste-article",(function (){

        let filiale_id = $(namespace + '#filiale-id').attr("value-id");

        if (ARTICLE_TAB.length===0){
            let url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/itemsInfo";
            execute_ajax_request("get",url,null,(data)=> {
                data.forEach(au=>{
                    let tr = `
                     <tr id ="`+au.itemId+`-`+au.uniteId+`">
                        <td>`+au.itemName+`</td>
                        <td value-id ="`+au.uniteId+`">`+au.uniteName +`</td>
                        <td>`+au.quantiteNiveau +`</td>
                        <td>`+au.stock +`</td>
                        <td nbs>`+au.price +`</td>
                        <td>`+au.poids +`</td>
                    </tr>`;
                    $(namespace+"#table-liste-article tbody").append(tr);
                    ARTICLE_TAB.push(au);
                })
            });

        }else {
            ARTICLE_TAB.forEach(au => {
                let tr = `
                     <tr id ="`+au.itemId+`-`+au.uniteId+`">
                        <td>`+au.itemName+`</td>
                        <td value-id ="`+au.uniteId+`">`+au.uniteName +`</td>
                        <td>`+au.quantiteNiveau +`</td>
                        <td>`+au.stock +`</td>
                        <td nbs>`+au.price +`</td>
                        <td>`+au.poids +`</td>
                    </tr>`;

                $(namespace+"#table-liste-article tboby").append(tr);

            })
        }
    }))

})