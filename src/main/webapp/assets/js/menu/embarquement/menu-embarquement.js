$(function(){
    let namespace = "#menu-embarquement ";
    let url = "http://localhost:8080/api/v1/voyages";
    $NEW_VOYAGE = true;
    /* MENU EMBARQUEMENT */
    exportToExcel(namespace + '.btn-export-to-excel','embarquements', namespace + '#table-voyage');
    exportToExcel(namespace + '.btn-export-to-excel-info','embarquements', namespace + '#table-liste-article-embarquement-info');
    _count_table_content(namespace + '#table-voyage', namespace + '.text-count-embarquement');

    let filiale_id = $(namespace + '#filiale-id').attr("value-id");

    let user_id = $(namespace + '#user-id').attr("value-id");

    const init_badge = (status)=>{
        switch (status) {
            case 'CHARGEMENT_EN_COURS' : return '<span class="badge badge-info-lighten"> Chargement en cours</span>';
            case 'CHARGEMENT_TERMINER' : return '<span class="badge badge-warning-lighten">Chargement terminer</span>';
            case 'ARRIVER_DESTINATION' : return '<span class="badge badge-success-lighten">Arriver a destination</span>';
            case 'TRANSFERER' : return '<span class="badge badge-secondary-lighten">Transferer</span>';
            case 'EN_ROUTE' : return '<span class="badge badge-primary-lighten">En mer (route)</span>';
        }
    }

    const append_item = (voyages)=>{
        voyages.forEach(v => {
            let tr= `
             <tr class="voyage" id="`+v.id+`">
                  <td>`+v.reference+`</td>
                  <td>`+v.trajet.depart+` - `+v.trajet.destination+` </td>
                  <td>`+check_value(v.poidsTotal)+`</td>
                  <td class="d-none marge-beneficiaire">`+check_value(v.montantMarge)+`</td>
                  <td>`+check_date(v.dateDepart)+`</td>
                  <td>`+check_date(v.dateArrive) +`</td>
                  <td>`+init_badge(v.statutVoyage)+`</td>
                  <td>${v.materielTransport.reference}</td>
                  <td class="td-action justify-content-center crud-embarquement">
                    <div class="d-flex" >
                      <a data-bs-toggle="modal" data-bs-target="#nouveau-embarquement" class="btn-sm btn-info edit-voyage"><i class="uil-pen"></i></a>
                      <a data-bs-toggle="modal" class="btn-sm btn-info about-voyage"><i class="uil-info-circle"></i></a>
                      <a class="btn-sm btn-danger delete-voyage "><i class="uil-trash-alt"></i></a>
                    </div>
                  </td>
                </tr>
            `;
            $(namespace+"#table-voyage tbody").append(tr);
        })
        _count_table_content(namespace + '#table-voyage', namespace + '.text-count-embarquement');
    }

   /* INFO EMBARQUEMENT */
 $(document).on("click",namespace+".about-voyage",function () {
    fetch_travel_and_show_modal(this);
 })

    const check_date = (date)=>{
        return date === null ? "" : date;
    };

    /* REFRESH BUTTON */
    $(document).on("click",namespace+"#refresh-btn",function (){
        execute_ajax_request("get",url,null,(voyages)=> {
            clear_table(namespace+"#table-voyage");
            append_item(voyages);
        })
    });

    // RECHERCHER PAR DATE
    $(document).on("click",namespace+"#search-button",()=>{
        let date_debut = $(namespace+"#begin-date-input").val();
        let date_fin = $(namespace+"#end-date-input").val();
        let date_type = $(namespace+"#select-date-type option:selected").val();
        if (date_debut!==""&& date_fin!==""){
            let url = "http://localhost:8080/api/v1/voyages/"+date_type+"/"+date_debut+"/"+date_fin;
            execute_ajax_request("get",url,null,(data)=>{
                clear_table(namespace+"#table-voyage");
                append_item(data);
            });
        }
    });

    /* SUPRIMMER VOYAGE */
    $(document).on('click',namespace+".delete-voyage",function (){
        let tr = $(this).closest("tr");
        let reference = $(tr).children().eq(0).text();
        let trajet = $(tr).children().eq(1).text();
        create_confirm_dialog(' Supprimer voyage ', " Voulez vous supprimer le voyage ("+reference + ") sur le trajet  ("+trajet+")", "suppression-article", "Oui ,"+"supprimer", "btn-danger").on('click', function (){
            let id = $(tr).closest("tr").attr("id");
            let url = "http://localhost:8080/api/v1/voyages/"+id;
            execute_ajax_request("DELETE",url,null,()=>{
                tr.remove();
                hideAndRemove('#suppression-article ');
                createToast('bg-danger', 'uil-trash-alt', 'Suppression Voyage', "Suppression voyage ("+reference+") effectu&eacute; avec succ&egrave;s!");
            });
        })
    });

    const check_value=(value)=>{
        return value==null ? "" : value;
    }

    const fetch_travel_and_show_modal = (tr_or_button)=>{

        $current_tr_id = $(tr_or_button).closest("tr").attr("id");
        $statut_voyage = $(tr_or_button).closest("tr").attr("id");
        let url = "http://localhost:8080/api/v1/voyages/"+$current_tr_id;

        execute_ajax_request("get",url,null,(voyage)=>{

            $reference_voyage = voyage.reference;
            let text = "                  "+voyage.reference + '('+voyage.trajet.depart +'-'+voyage.trajet.destination+') ';
            $(namespace+"#info-embarquement .modal-title").text(text);
            $(namespace+"#info-embarquement").modal("show");
            $statut_voyage = voyage.statutVoyage;
            clear_table(namespace+"#table-liste-article-embarquement-info");
            append_travel_item(voyage.infoArticleVoyages,"table-liste-article-embarquement-info");

        });

    }

    $(document).on('dblclick',namespace+"#table-voyage tbody tr",function (){
        fetch_travel_and_show_modal(this);
    });

    // RECHERCHER
    $(document).on('keyup',namespace+"#top-search",function (){
        let text = $(namespace+"#top-search").val();
        if (text!=="") execute_ajax_request("get","http://localhost:8080/api/v1/voyages/reference/"+text,null,(voyages)=> {
            clear_table(namespace+"#table-voyage");
            append_item(voyages);
        });
        else $(namespace+"#refresh-btn").click();
    });

    /* EDIT VOYAGE BUTTON */
    $(document).on("click",namespace+".edit-voyage",function(){
        $NEW_VOYAGE = false;
        $current_tr_id = $(this).closest("tr").attr("id");
        execute_ajax_request("get",url+"/"+$current_tr_id,null,(data)=>{
            $(namespace+"#observation").text(data.description);
            $(namespace+"#nouveau-embarquement-title").text(" Editer embarquement  ( "+data.reference+" )");
        })
    })

    /* SUPPRIMER ARTICLE DANS VOYAGE */
    $(document).on('click',namespace+"#info-embarquement .delete-article-voyage",function (){
        let id = $(this).closest("tr").attr("id");
        let tr = $(this).closest("tr");
        let designation_article = $(tr).children().eq(2).text();
        let designation_unite = $(tr).children().eq(3).text();
        let reference = $(tr).children().eq(0).text();
        let url = "http://localhost:8080/api/v1/iav/"+id;
        create_confirm_dialog(' Supprimer article (embarquement) ', " Voulez vous vraiment supprimer l'article ("+designation_article+"-"+designation_unite+") de l'embarquement ("+reference+")", "suppression-article-embarquement", "Oui ,supprimer", "btn-danger").on('click', function (){
            execute_ajax_request("delete",url,null,()=>{
                tr.remove();
                hideAndRemove('#suppression-article-embarquement');
                createToast('bg-danger', 'uil-trash-alt', 'Suppression article(embarquement)', "Suppression article de l'embarquement effectu&eacute; avec succ&egrave;s!");
            });
        })
    });

    $(document).on('click',namespace+"#enregistrer-nouveau-article-voyage",function (){
        // FOURNISSEUR
        $fId = $(namespace + '#select-fournisseur option:selected').val();
        $fournisseur_nom = $(namespace + '#select-fournisseur option:selected').text();
        // FACTURE
        $numFacture = $(namespace+"#input-facture").val();
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
        $poids = $(namespace+"#designation-article").attr("POIDS");
        let camion_id = $(namespace+"#select-camion option:selected").val();
        let date_peremption = $(namespace+"#datePeremption").val();
        let info = {};
        info.fournisseur = {id: $fId};
        info.user = {id: $userId};
        info.unite = {id: $uniteId};
        info.article = {id: $articleId}
        info.quantite = $quantite;
        info.date = new Date();
        info.poids = $poids;
        info.prixVente = $prix_vente_article;
        info.prixAchat = $prix_achat_article;
        info.numFacture = $numFacture;
        info.voyage = {id : $current_tr_id};
        info.materielTransport = {id : camion_id};
        info.datePeremption = date_peremption;
        let url = "http://localhost:8080/api/v1/iav";
        execute_ajax_request("post",url,info,(iav)=>{
            createToast('bg-success', 'uil-file-check-alt', 'Enregistrement article dans embarquement', 'article enregistr&eacute; avec succ&egrave;s!')
            append_travel_item([iav],"table-liste-article-embarquement-info");
        });
        $(namespace+"#nouveau-article-embarquement").modal("hide");
    })

    $(document).on('click',namespace+"#refresh-item-voyage-btn",function (){
        let url = "http://localhost:8080/api/v1/voyages/"+$current_tr_id;
        execute_ajax_request("get",url,null,(voyage)=>{
            clear_table(namespace+"#table-liste-article-embarquement-info");
            append_travel_item(voyage.infoArticleVoyages,"table-liste-article-embarquement-info");
        });
    })

    /* ENREGISTRER NOUVEAU VOYAGE */
    $(document).on('click',namespace+"#enregistrer-update-voyage-btn",function (){
        let voyage = {};
        let materiel_transport_id = $(namespace+"#input-moyen-de-transport option:selected").val();
        let trajet_id = $(namespace+"#select-trajet option:selected").val();
        let statut = $(namespace+"#status-embarquement option:selected").val();
        let observation = $(namespace+"#observation").val();
        voyage.materielTransport = {id : materiel_transport_id};
        voyage.statutVoyage =parseInt(statut);
        voyage.trajet = {id : trajet_id};
        voyage.dateCreation = new Date();
        voyage.description = observation;
        save_or_update_voyage(voyage);
    })

    /* NOUVEAU EMBARQUEMENT */
    $(document).on("click",namespace+"#nouveau-embarquement-btn",function (){
        $NEW_VOYAGE = true;
        $(namespace+"#nouveau-embarquement-title").text(" Nouveau embarquement ");
    });

    const save_or_update_voyage = (voyage)=>{
        let method = $NEW_VOYAGE ? "POST" : "PUT";
        let url = $NEW_VOYAGE ? "http://localhost:8080/api/v1/voyages/" : "http://localhost:8080/api/v1/voyages/"+$current_tr_id;
        let title = $NEW_VOYAGE ? 'Enregister voyage':'Modification voyage';
        let content = $NEW_VOYAGE ? ' Enregistrement voyage enregistr&eacute; avec succ&egrave;s!' : 'Modification voyage enregistr&eacute; avec succ&egrave;s!';
        execute_ajax_request(method,url,voyage,(v)=>{
            if($NEW_VOYAGE) append_item([v]);
            createToast('bg-success', 'uil-file-check-alt', title, content);
            $(namespace+"#nouveau-embarquement").modal('hide');
            $NEW_VOYAGE = false;
        })
    }

    // INITIALISATION LISTE ARTICLE
    init_dbl_click_table(namespace,"table-liste-article");

    /* INITIALISATION DU MODAL TRANSFERT VOYAGE */
    $(document).on('shown.bs.modal',"#transfert-voyage-modal",function(){
        let url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/stores";
        execute_ajax_request('get',url,null,(data)=>{
            $(namespace+"#select-magasin option").remove();
            data.forEach(value =>{
                let tr = `<option value="`+value.id+`">`+value.nomMagasin+` </option>`;
                $(namespace+"#select-magasin").append(tr);
            });
        });
    });

    /* TRANSFERER ARTICLE VOYAGE */
    $(document).on("click",namespace+"#info-embarquement .transfert-article-voyage", function (){
        init_tr_info_action(this);
    });

    /* ENREGISTREMENT DU TRANSFERT L'ARTICLE DANS LE MAGASIN */
    $(document).on("click",namespace+"#enregistrer-transfert-voyage",function (){
        let magasinId = $(namespace+"#select-magasin option:selected").val();
        let quantite = $(namespace+'#input-quantite-article-voyage').val();
        let iam = {};
        iam.typeOperation = "ENTRE";
        iam.magasin = {
            id: magasinId
        }
        iam.user = {id : user_id};
        iam.unite = {id : $unite_id};
        iam.article = {id : $article_id};
        iam.quantiteAjout = quantite;
        iam.date = new Date();
        iam.reference = $facture;
        iam.description = " Transfert venant l'embarquement " +$reference_voyage;

        let fuap = {};
        fuap.filiale = {
            id: filiale_id
        };
        fuap.unite = iam.unite;
        fuap.article = iam.article;
        fuap.user = iam.user;
        fuap.dateEnregistrement = new Date();
        fuap.prixVente = $prix_vente;

        let supply = {};
        supply.infoArticleMagasin = iam;

        supply.fournisseur = {
            id: $fr_id
        };

        supply.montantApprov = $prix_achat;

        if ($datePeremption!==null) supply.datePeremption = $datePeremption;
        else supply.datePeremption = new Date();
        supply.datePeremption = $datePeremption;
        supply.quantitePeremption = quantite;
        supply.quantiteAjout= quantite;

        let supplyWrapper = {};
        supplyWrapper.supplies = [supply];
        supplyWrapper.prixArticleFiliales = [fuap];

        let url = "http://localhost:8080/api/v1/supplies";
        execute_ajax_request("post", url, supplyWrapper, (data) =>{
            update_iav_quantity(quantite,$iav_id);
        });
    });

    const update_iav_quantity = (quantite,iav_id)=>{
        let url = "http://localhost:8080/api/v1/iav/"+iav_id;
        let info = {};
        info.quantite = quantite;
        execute_ajax_request("put",url,info,(data)=>{
            createToast('bg-success', 'uil-file-check-alt', 'Enregistrement transfert', ' Transfert enregistr&eacute; avec succ&egrave;s!')
            $(namespace+"#transfert-voyage-modal").modal("hide");
        });
    };

    function append_travel_item(infoArticleVoyages,$table){
        infoArticleVoyages.forEach(info => {
            let mat_transport =  info.materielTransport;
            let tr = `
                <tr  id="` + info.id + `">
                  <td>` + info.numFacture +`</td>
                  <td is-perishable = "`+info.isPerishable+`" value-id = "`+info.fournisseur.id+`">` + info.fournisseur.nom + `</td>
                  <td value-id = "`+info.article.id+`">` + info.article.designation + `</td>
                  <td value-id = "`+info.unite.id+`" >` + info.unite.designation + ` </td>
                  <td>` + info.quantite +`</td>
                  <td>` + info.poids +`</td>
                  <td>` + info.prixAchat +`</td>
                  <td class="marge-beneficiaire" >`+ check_value(info.prixVente)+`</td>
                  <td class="marge-beneficiaire" >`+ check_value(info.marge)+`</td>
                  <td>` + info.user.nom +`</td>
                  <td>` + info.date + `</td>
                  <td>` + check_value(info.datePeremption) + `</td>
                  <td class="marge-beneficiaire">` + check_value(info.prixTransport) +`</td>
                  <td>` + check_value(mat_transport.reference) + `</td>
                  <td>` + check_value(mat_transport.responsable.nom) +`</td>
                  <td class="td-action justify-content-center crud-embarquement">
                    <div class="d-flex" >
                      <a class="btn-sm btn-info marge-beneficiaire edit-article-transfert" data-bs-toggle ="modal" data-bs-target="#prix-article-voyage-modal" ><i class="uil-money-insert"></i></a>
                      <a class="btn-sm btn-danger ` +check_quantite(info.quantite)+` delete-article-voyage "><i class="uil-trash-alt"></i></a>
                      `+init_transfert_btn($statut_voyage)+`
                    </div>
                  </td>
                </tr>            
            `;
            $(namespace + "#"+$table+" tbody").append(tr);
        });
    }

    const init_transfert_btn = (value)=>{
        if (value==="ARRIVER_DESTINATION") return '<a class="btn-sm  btn-success transfert-article-voyage" data-bs-target="#transfert-voyage-modal" data-bs-toggle="modal" ><i class="uil-arrow-circle-down"></i></a>';
        return '';
    }

    const check_quantite = (value)=>{
        if (value===0) return 'd-none';
        return '';
    }

    const init_tr_info_action = (button)=>{
        $iav_id = $(button).closest("tr").attr("id");
        let tr = $(button).closest("tr");
        $article_id = $(tr).children().eq(2).attr("value-id");
        $is_persisable = $(tr).children(2).attr("is-perishable");
        $unite_id = $(tr).children().eq(3).attr("value-id");
        $fr_id = $(tr).children().eq(1).attr("value-id");
        $quantite = $(tr).children().eq(4).text();
        $prix_vente = $(tr).children().eq(7).text();
        $marge = $(tr).children().eq(8).text();
        $prix_achat = $(tr).children().eq(6).text();
        $datePeremption = $(tr).children().eq(11).text();
        $prix_transport = $(tr).children().eq(12).text();
        $facture = $(tr).children().eq(0).text();

        let nom_fournisseur = $(tr).children().eq(1).text();
        let designation_article = $(tr).children().eq(2).text();
        let designation_unite = $(tr).children().eq(3).text();

        $(namespace+".fournisseur-transfert").text(nom_fournisseur);
        $(namespace+".article-transfert").text(designation_article);
        $(namespace+".unite-transfert").text(designation_unite);

        $(namespace+".quantite-transfert").text($quantite)
        $(namespace+".prix-vente-transfert").text($prix_vente)
        $(namespace+".peremption-transfert").text($datePeremption)
        $(namespace+".facture-transfert").text($facture);
        $(namespace+".prix-achat-transfert").text($prix_achat);
        $(namespace+".prix-transport-transfert").text($prix_transport);
        $(namespace+".marge-commercial").text($marge);

        $(namespace + '#input-prix-vente-article-voyage').val($prix_vente);
        $(namespace + '#input-prix-achat-article-voyage').val($prix_achat);
        $(namespace + '#input-prix-transport-article-voyage').val($prix_transport);
    }

    $(document).on('click',namespace+".edit-article-transfert",function () {
        init_tr_info_action(this);
    });

    /* ENREGISTREMENT PRIX DE VENTE OU PRIX ACHAT OU TRANSPORT */
    $(document).on('click',namespace+"#enregistrer-prix-article-voyage",function (){
        let prix_vente = $(namespace + '#input-prix-vente-article-voyage').val();
        let prix_achat = $(namespace + '#input-prix-achat-article-voyage').val();
        let prix_transport = $(namespace + '#input-prix-transport-article-voyage').val();
        let url = "http://localhost:8080/api/v1/iav/"+$iav_id+"/price";
        let info = {};
        info.prixVente = prix_vente;
        info.prixAchat = prix_achat;
        info.prixTransport = prix_transport;
        execute_ajax_request("put",url,info,(data)=>{
            createToast('bg-success', 'uil-file-check-alt', ' Modification Prix ', ' Prix enregistr&eacute; avec succ&egrave;s!')
            $(namespace+"#prix-article-voyage-modal").modal('hide');
        });
    })

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

    /* RECHERCHER ARTICLE DANS LE VOYAGE */
    $(document).on('keyup',namespace+"#top-search-iav",function () {
        let text = $(namespace+"#top-search-iav").val();
        if (text!==""){
            let url = "http://localhost:8080/api/v1/voyages/"+$current_tr_id+"/iavs/"+text;
            execute_ajax_request("get",url,null,(data)=>{
                clear_table(namespace+"#table-liste-article-embarquement-info");
                append_travel_item(data,"table-liste-article-embarquement-info");
            });
        }else $(namespace+"#refresh-item-voyage-btn").click();
    })

    /* filtre vue */
    $(namespace + '#filtre-table').on('change', function() {

        $(namespace + '#table-liste-article-embarquement-info td').hide();
        $(this).find(':selected').each(function(key,value) {
            $(namespace + '#table-liste-article-embarquement-info td.' + $(value).attr('class')).show();
        })
    })

})