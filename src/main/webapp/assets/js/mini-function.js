function appendOptionToSelect($value, $select){
    $($select)
        .append($("<option></option>")
            .attr("value", $value[0])
            .text($value[1]));
}

function create_reference(type, date) {
    let time = date.getTime();
    switch (type) {
        case "VENTE" :return "VT-" + time;
        case "INVENTAIRE" :return "INV-" + time;
        case "SORTIE" :return "SORT-" + time;
        case "ENTRE" :return "ENT-" + time;
        case "AVOIR" :return "AV-" + time;
        case "TRANSFERT":return "TF-" + time;
        case "DECAISSEMENT" :return "DEC-" + time;
        case "ENCAISSEMENT" :return "ENC-" + time;
    }
}

function set_select_option_value($array, $select) {
    let children = $($select).children();
    if (children.length === 0) {
        appendOptionToSelect($array, $select);
    } else {
        let tab = [];
        for (let i = 0; i < children.length; i++) {
            let oldValue = $(children[i]).text();
            tab.push(oldValue);
        }
        let ok = tab.every(value => value !== $array[1]);
        if (ok) appendOptionToSelect($array, $select);
        tab = [];
    }
}

function set_select_option_value_or_update_option($array, $select, $isCreate) {
    $array = convertiMultiObjectToArray($array)
    $.each($array, function (key, value) {
        if ($isCreate) {
            $($select)
                .append($("<option></option>")
                    .attr("value", value[0])
                    .text(value[1]));
        } else {
            let option = $("#menu-entree-article #select-unite-article").children()[0];
            $(option).attr("value", value[0]).text(value[1]);
        }
    });
}

function set_select_option_value_ajax($array, $select) {
    $.each($array, function (key, value) {
        $($select)
            .append($("<option></option>")
                .attr("value", value.id)
                .text(value.nomMagasin));
    });
}

function push_select_option_value($array, $select) {
    $($select)
        .append($("<option></option>")
            .attr("value", $array[0])
            .text($array[1]));
}

let ARTICLE_TAB = [];

function push_item_table(type, value,table_id){
    let tr = [];
    let tr_id = "";
    if (type === "ARTICLE") {
        let tr = `
         <tr id ="`+au.itemId+`-`+au.uniteId+`">
            <td>`+au.itemName+`</td>
            <td value-id ="`+au.uniteId+`">`+au.uniteName +`</td>
            <td>`+au.quantiteNiveau +`</td>
            <td>`+au.stock +`</td>
            <td>`+au.price +`</td>
            <td>`+au.poids +`</td>
        </tr>`;
        $(table_id+"tboby").append(tr);
    } else if (type === "CLIENT" || type === "FOURNISSEUR") {
        tr = [value.nom, value.adresse, value.numTel];
        tr_id = value.id;
        push_to_table_list(table_id,tr_id,tr);
    }
}

function append_data_to_table(table_id, type, data, tab){
    $(table_id + " tbody tr").remove();
    data.forEach(value => {
        push_item_table(type, value, table_id);
        if (tab.length === 0) tab.push(value);
        else {
            let finded_item = tab.find(create_consumer("BY_ID", type, value));
            if (finded_item === undefined) tab.push(value);
        }
    })
}

const create_consumer = (consumer_type,item_type, value) => {
    return consumer_type === "NAME" ? create_consumer_by_name(item_type, value) : create_consumer_by_id(item_type, value);
}

const create_consumer_by_name = (item_type, value) => {
    return item_type === "ARTICLE" ? (article) => article.itemName.search(value) !== -1 :
        (cf) => cf.nom.search(value) !== -1;
}

const create_consumer_by_id = (item_type, value) => {
    return item_type === "ARTICLE" ? (item) => item.itemId === value.itemId && item.uniteId === value.uniteId :
        (item) => item.id === value.id;
}

function find_item(table_id, filiale_id, item_name, type,tab){
    let url = "";
    if (type === "ARTICLE") url = "http://localhost:8080/api/v1/subsidiaries/" + filiale_id + "/itemsInfo/" + item_name;
    else url = "http://localhost:8080/api/v1/externalEntities/" + (type === "CLIENT" ? "0" : "1") + "/" + filiale_id;
   execute_ajax_request("get", url, null, (data) => append_data_to_table(table_id, type, data, tab))
}

function fetch_item(url, tab, table_id, item_type) {
    execute_ajax_request("get", url, null, (data) => append_data_to_table(table_id, item_type, data, tab))
}
const init_input_search_keyup = function (item_type,input_id,table_id, filiale_id,tab) {
    $(document).on("keyup",input_id, function (){
        let item_name = $(input_id).val().toLowerCase().trim();
        $(table_id + " tbody").empty();
        if (item_name !== ''){

            let finded_item = tab.find(create_consumer("NAME", item_type, item_name));

            if (finded_item === undefined){

                let url = "";
                if (item_type === "ARTICLE") url = "http://localhost:8080/api/v1/subsidiaries/" + filiale_id + "/itemsInfo/" + item_name;
                else url = "http://localhost:8080/api/v1/externalEntities/" + (type === "CLIENT" ? "0" : "1") + "/" + filiale_id;
                execute_ajax_request("get", url, null, (data) => append_data_to_table(table_id, item_type, data, tab))

            } else push_item_table(item_type,finded_item, table_id);

        } else if (tab.length !== 0) tab.forEach(value => push_item_table(item_type, value, table_id));

    });
}

function get_select_affect_to_input($input, $id_element, $text_element) {
    $($input).attr('value-id', $id_element)
    $($input).attr('value', $text_element)
}

function push_to_table_list($table, $id, $array_td, $array_class) {
    $tr = $('<tr></tr>').attr('id', $id);
    for (let i = 0; i < $array_td.length; i++) {
        let td_class = $array_class == undefined ? "" : $array_class[i];
        $tr.append($('<td nbs class="'+ td_class +'"></td>').html($array_td[i]))
    }
    $($table + ' tbody').append($tr);
    return $tr;
}

const init_dbl_click_table = (namespace,$table)=>{

    $(document).on('dblclick', namespace + '#'+$table+' tbody tr', function () {
        let article_id = $(this).attr("id");
        let unite_id = $(this).children().eq(1).attr("value-id");
        let designation_article = $(this).children().eq(0).text();
        let designation_unite = $(this).children().eq(1).text();
        let POIDS_ARTICLE = $(this).children().eq(3).text();
        get_select_affect_to_input(namespace + '#designation-article',article_id,designation_article);
        $(namespace+"#designation-article").attr("POIDS",POIDS_ARTICLE)
        $(namespace + '#select-unite-article option').remove();
        set_select_option_value([unite_id,designation_unite], namespace + "#select-unite-article");
        $(namespace + '#input-prix-vente-article').val("");
        $(namespace + '#modal-liste-article').modal('hide');
    });

}


const createStatus = (montant)=>{
    if (montant===0) return "payé";
    else return "non payé";
}

const createBadge = (montant)=>{
    if (montant===0) return "badge badge-success-lighten";
    return "badge badge-warning-lighten";
}

function fetch_trosa_data(namespace,$cf_id){
    let url = "http://localhost:8080/api/v1/trosas/cf/" + $cf_id;
    execute_ajax_request("get", url, null, (data) => {
        append_trosa_item(namespace,data)
    })
}

const init_btn_payer_trosa = (namespace)=>{
    $(document).on('click', namespace+'#table-dette-cf .payer-trosa',function(){
        $trosa_id = $(this).closest("tr").attr("id");
        // get montant
        $montant_reste = parseFloat($(this).closest('tr').children('td').eq(4).text());
        $(namespace + '#modal-payement-dette form').validate();
        $(namespace + '#modal-payement-dette input#Montant-payer').val($montant_reste)
        $(namespace + '#modal-info-payement-dette').modal('show')
    })
}

function init_enregistrer_trosa_btn(namespace){
    $(namespace + '#nouveau-dette #btn-enregistrer-dette-fournisseur').on('click', function () {
        if (validation_nouveau_dette(namespace)){
            let type_trosa = $(namespace+"#info-dette-cf").attr("type-trosa");
            $montant = $(namespace + '#nouveau-dette input#somme').val();
            $description = $(namespace + '#nouveau-dette textarea#description').val();
            $date_dette = $(namespace + "#date-dette").val();
            $date_echeance = $(namespace + "#date-echeance").val();
            let trosa = {};
            trosa.clientFournisseur = {id: $cf_id};
            trosa.montant = $montant;
            trosa.description = $description;
            trosa.typeTrosa = type_trosa;
            trosa.date = $date_dette;
            trosa.dateEcheance = $date_echeance;
            trosa.reste = $montant;
            let url = "http://localhost:8080/api/v1/trosas";
            execute_ajax_request("post", url, trosa, (data) => {
                let tr = ["",
                    trosa.date,
                    trosa.montant,
                    0,
                    trosa.montant,
                    '-',
                    `<span class="badge ` + createBadge($montant) + `">` + createStatus($montant) + `</span>`,
                    trosa.dateEcheance,
                    trosa.description,init_action_column(trosa.reste)];
                push_to_table_list(namespace + '#table-dette-cf', data.id, tr);
                let title = type_trosa+'enregistre';
                let content = 'Nouveau '+type_trosa+' enregistre avec success!'
                createToast('bg-success', 'uil-check-sign',  title.toLowerCase(), content.toLowerCase());
                $(namespace + '#nouveau-dette input').val('');
                $(namespace + '#nouveau-dette textarea').val('');
               // impression facture
                function impression_credit_dette(){
                    $infos = {
                        titre: $(namespace + '#nouveau-dette .modal-title').text().replace("Nouveau ",""),
                        date_facture: $date_dette,
                        nom_client: trosa.clientFournisseur,
                        magasin: '-',
                        operateur: '-',
                        montant_facture: trosa.montant,
                        montant_payer: $(namespace + '#mode-payement-modal #montant').val(),
                        description: $(namespace + '#mode-payement-modal textarea#description').val(),
                        date_echeance : $(namespace + '#mode-payement-modal #date-echeance').val()
                    }
                    generate_ticket_acceptation($infos.titre, $societe, $infos);
                    generate_facture_acceptation($infos.titre, $societe, $infos);
                };
                impression_credit_dette()
            });
            $(namespace + '#nouveau-dette').modal('hide');
        }
    })
}

const init_modal_credit_dette_btn = (namespace,filiale_id,user_id)=>{

    init_payement_dette_btn(namespace,filiale_id,user_id);

    // payement trosa
    push_Type_paiement(namespace + '#modal-payement-dette #type-paiement');

    // INIT BOUTON PAYER TROSA

    init_btn_payer_trosa(namespace);

    init_enregistrer_trosa_btn(namespace);

    supprimer_dette(namespace);
    /* ACTUALISER LISTE */
    $(namespace+"#refresh-list-btn").click(() => {
        fetch_trosa_data(namespace,$cf_id);
    });
    /* validation payement */
    init_validation_payement(namespace);
}

function supprimer_dette(namespace) {
    $(document).on('click', namespace + '#table-dette-cf .delete-trosa',()=>{
        $modalId = "suppression-dette-fournisseur"
        create_confirm_dialog('Suppression dette', 'Voulez vraiment supprimer les dettes impayes ?', $modalId, 'Oui, supprimer tout', 'btn-danger')
            .on('click', function () {
                // $(namespace + '.table-dette-fournisseur tbody tr').remove();
                hideAndRemove('#' + $modalId);
                createToast('bg-danger', 'uil-check-sign', 'Dette supprime', 'Tout les dettes fournisseur sont supprimer avec success!');
            })
    })
}

const init_payement_dette_btn = (namespace,filiale_id,user_id)=>{
    $(namespace + '#modal-payement-dette .btn-enregistrer-payement-dette').on('click',function(){
        // if (validation_payement_dette(namespace)){
            let type_trosa = $(namespace+"#info-dette-cf").attr("type-trosa");
            let montant_payer = $(namespace+"#Montant-payer").val();
            let type_payement = $(namespace+"#type-paiement option:selected").val();
            let description = $(namespace+"#description-payement").val();
            let ifc = {};
            ifc.description = description;
            ifc.montantOperation = montant_payer;
            ifc.reference =create_reference("DECAISSEMENT",new Date());
            ifc.operationCaisse = "DETTE" === type_trosa ? "DECAISSEMENT" : "ENCAISSEMENT";
            ifc.user = {id:user_id};
            ifc.modePayement = type_payement;
            ifc.filiale = {id:filiale_id};
            ifc.date = new Date();
            let url = "http://localhost:8080/api/v1/ifc";
            execute_ajax_request("post",url,ifc,(data)=>{
                $(namespace+"#Montant-payer").val("");
                $(namespace+"#description-payement").val("");
                $(namespace + '#modal-payement-dette').modal('hide');
                update_reste(namespace,montant_payer,type_trosa);
            });
    })

    function update_reste(namespace,montant_payer,type_trosa) {
        let montant_reste = $montant_reste - parseFloat(montant_payer);
        execute_ajax_request("put", "http://localhost:8080/api/v1/trosas/" + $trosa_id,montant_reste, (data) => {
            let title = type_trosa +'payé';
            let content = type_trosa +'payé avec succès';
            createToast('bg-success', 'uil-check', title.toLowerCase(), content.toLowerCase());
        });
    }

}

const init_validation_payement = (namespace)=>{
    $(namespace + "#nouveau-dette form").validate({
        rules : {
            dateDette : {required : true},
            dateEcheance : {required : true},
            montant : {required : true, min: 0.0001}
        },
        messages : {
            dateDette : {required : "Date du dette requis"},
            dateEcheance : {required : "Date d'echeance requis"},
            montant : {required : "Montant du dette requis", min: "Le montant doit être >0"}}
    })

    $(namespace + '#modal-payement-dette form').validate( {
        rules : {
            montantPayer : { required : true, min : 0.0001, max : 10000000},
        },
        messages : {
            montantPayer : { required : "Montant payer requis", min : "Montant payer doit être >0", max : "Le montant depasse le dette"},
        }
    })

}

function validation_payement_dette(namespace) {
    $(namespace + '#modal-payement-dette form').validate();
    return $(namespace + '#modal-payement-dette form').valid();
}

function validation_nouveau_dette(namespace){
    $(namespace + '#nouveau-dette form').validate();
    return $(namespace + '#nouveau-dette form').valid();
}

const  init_dblclick_table = (namespace,$table) =>{
    $(document).on('dblclick',namespace +$table+' tbody tr',function(){
        if ($table.search("client")!==-1){
            $(namespace+".btn-nouveau-dette").text(" Nouveau credit ");
            $(namespace+"#standard-modalLabel").text(" Nouveau credit ")
            $(namespace+".label-date-dette").text("Date credit");
            $(namespace+"#mySmallModalLabel").text("payement credit");
            $(namespace+"#info-dette-cf").attr("type-trosa","CREDIT");
        }else{
            $(namespace+".btn-nouveau-dette").text(" Nouvelle dette ");
            $(namespace+"#standard-modalLabel").text(" Nouvelle dette ");
            $(namespace+".label-date-dette").text("Date dette");
            $(namespace+"#mySmallModalLabel").text("payement dette");
            $(namespace+"#info-dette-cf").attr("type-trosa","DETTE");
        }
        $(namespace+"#info-dette-cf").modal("show");
        $cf_id = $(this).attr("id");
        fetch_trosa_data(namespace,$cf_id);
    })
}


function append_trosa_item(namespace, data){
    $(namespace + '#table-dette-cf tbody').empty();
    data.forEach(value => {
        let tr = [value.reference,
            value.date,
            value.montant,
            value.montant - value.reste,
            value.reste,
            value.typePayement,
            `<span class="badge ` + createBadge(value.reste) + `">` + createStatus(value.reste) + `</span>`,
            value.dateEcheance,
            value.description,init_action_column(value.reste)];
        push_to_table_list(namespace + '#table-dette-cf',value.id, tr);
    })
}

const init_action_column = (reste)=>{
    let action =  `<a  class="btn-sm btn-danger delete-trosa"><i class="uil-trash-alt"></i></a>`;
    return reste === 0 ? action : action+`<a  class="btn-sm btn-info payer-trosa"><i class="uil-money-withdrawal"></i></a>`;
}

const clear_table = ($table) => {
    $($table+" tbody tr").remove();
}

function append_cf_item(namespace, table, data,type) {
    clear_table(namespace + table);
    table = table.replace("#","");
    data.forEach(value => {
        let tr = [value.nom, value.adresse, value.numTel, value.totalMontantTrosa, $('<div class="'+init_type_class(type)+'">' +
            '                <a id="" class="btn-sm btn-info '+init_edit_class(type)+' "><i class="uil-pen"></i></a>' +
            '                <a id="" class="btn-sm btn-danger '+init_delete_class(type)+' "><i class="uil-trash-alt"></i></a>' +
            '              </div>')];
        push_to_table_list(namespace +"#"+table, value.id, tr);
    })
}

const init_type_class = (type)=>{
    return type === 0 ? 'action-client' : 'action-fournisseur';
}

const init_edit_class = (type)=>{
    return type === 0 ? 'editClient ' : 'editFournisseur ';
}

const init_delete_class = (type)=>{
    return type === 0 ? 'deleteClient ' : 'deleteFournisseur';
}

const fetch_all_cf_and_append_to_table = (type,namespace,table,$filiale_id)=>{
    let url = "http://localhost:8080/api/v1/externalEntities/"+type+"/"+$filiale_id;
    execute_ajax_request("get",url,null,(data)=> {
        append_cf_item(namespace,table,data,type)
    });
}

const init_modal_on_showing = (modal_type,namespace,$modal,$table,$filiale_id,type)=>{

    $(document).on('shown.bs.modal',"#"+$modal,function(){

        switch (modal_type){

            case "ARTICLE": {

                 let url = " ";
                 execute_ajax_request("get",url,null,(data)=>{
                     clear_table(namespace+"#"+$table);
                     data.forEach(value =>{
                         let tr = [value.itemName, value.uniteName, value.stock,value.price];
                         let tr_id = value.itemId + "-" + value.uniteId;
                         push_to_table_list(namespace+"#"+$table,tr_id, tr);
                     })
                 });

            } break;

            case "FOURNISEUR_OU_CLIENT": {

                let url = "http://localhost:8080/api/v1/externalEntities/"+type+"/"+$filiale_id;
                execute_ajax_request("get",url,null,(data)=>{
                    clear_table(namespace+"#"+$table);
                    data.forEach(value=>{
                       let tr = [value.nom, value.adresse, value.numTel];
                       let tr_id = value.id;
                        push_to_table_list(namespace+"#"+$table,tr_id, tr);
                    })
                });

            }break;

            case "MOYEN_TRANSPORT" : {

            }break;

            case "TRAJET" : {

            }break;
        }

    });

}

const  init_seach_cf_btn = (type,namespace,table,$filiale_id)=>{
    // REFRESH
    $(document).on("click",namespace+"#refresh-btn",()=> fetch_all_cf_and_append_to_table(type,namespace,table,$filiale_id));
    // RECHERCHER
    $(namespace+"#search-btn").click(()=>{
        let text = $(namespace +"#top-search").val();
        if (undefined !== text && text!==""){
            let url = "http://localhost:8080/api/v1/externalEntities/"+type+"/"+$filiale_id+"/name/"+text;
            execute_ajax_request("get",url,null,(data)=> append_cf_item(namespace,table,data,type))
        }else $(namespace+"#refresh-btn").click();
    });
    // ON TEXTFIELD KEYUP
    $(document).on("keyup",namespace+"#top-search",()=> $(namespace+"#search-btn").click());
}

function execute_ajax_request(method_type, api_url, data = null,onsuccess) {
    let requestObject = {type: method_type, url: api_url ,async: true  ,success: onsuccess, contentType: "application/json"};
    if (data !== null) requestObject.data = JSON.stringify(data);
    $.ajax(requestObject);
}

function execute_ajax_request(method_type, api_url, data = null,onsuccess,beforeSendFunction,onErrorFunction,onCompleteFunction) {
    let requestObject =  {type: method_type,
                         url: api_url ,
                         // async: true  ,
                         beforeSend : beforeSendFunction,
                         success: onsuccess,
                         contentType: "application/json",
                         error : onErrorFunction,
                         complete : onCompleteFunction
                         };
    if (data !== null) requestObject.data = JSON.stringify(data);
    $.ajax(requestObject);
}

function push_to_inventory_table_list($table, $id, $array_td) {
    $tr = $('<tr></tr>').attr('id', $id);
    for (let i = 0; i < $array_td.length; i++) {
        if (i !== 4) $tr.append($('<td></td>').html($array_td[i]))
        else {
            $a = $('<a></a>')
                .attr("type", "button")
                .attr("class", "btn-default mr-1 btn-info-stock").html($array_td[i] + " " + $array_td[i - 3])
            $tr.append($('<td></td>').append($a))
        }
    }
    $($table + ' tbody').append($tr);
    return $tr;
}

function push_to_table_list_magasin($array_td) {
    $tr = $('<tr></tr>').attr('id', $array_td[0]);
    for (let i = 1; i < $array_td.length; i++) $tr.append($('<td></td>').html($array_td[i]))
    $('#table-liste-utilisateur-magasin tbody').append($tr);
    return $tr;
}

function update_to_table_list($table, $id, $array_td) {
    $tr = $($table + ' tbody tr#' + $id);
    for (let i = 0; i < $array_td.length; i++) $tr.children().eq(i).html($array_td[i])
}

function create_confirm_dialog($title,$dialogContent,$modalId,$labelButton,$classButton){
    $modal = $('' + '<div>' + '<div id="' + $modalId + '" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="standard-modalLabel" aria-hidden="true">\n' + '    <div class="modal-dialog modal modal-dialog-centered">\n' + '        <div class="modal-content was-validated">\n' + '            <div class="modal-header">\n' + '                <h4 class="modal-title" id="standard-modalLabel">' + $title + '</h4>\n' + '                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true"></button>\n' + '            </div>\n' + '            <div class="modal-body">\n' + '                ' + $dialogContent + '\n' + '            </div>\n' + '            <div class="modal-footer">\n' + '                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Annuler</button>\n' + '                <button id="btn-' + $modalId + '" type="button" class="btn ' + $classButton + '">' + $labelButton + '</button>\n' + '            </div>\n' + '        </div><!-- /.modal-content -->\n' + '    </div><!-- /.modal-dialog -->\n' + '</div></div><!-- /.modal -->\n');
    if (!$('#' + $modalId).length) $('.all-modal').append($modal.html());
    $('#' + $modalId + ' .modal-body').html($dialogContent)
    $('#' + $modalId).modal('show');
    return $('button#btn-' + $modalId);
}

function  init_remise_btn(namespace,btn_id,type_remise) {
    $(namespace+btn_id).on('click',()=>{
        $(namespace+"#modal-prix-special").modal("show");
        $(namespace+"#modal-prix-special").attr("type-remise",type_remise)
        $(namespace+"#remise-title").text("Remise "+type_remise);
    })
}

function autoIncrementFromTableTrContent($idTable) {
    return $($idTable + ' tbody').children().length + 1;
}

// DATE FORMAT JS

function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}

function formatDate(date) {
    return [date.getFullYear(), padTo2Digits(date.getMonth() + 1), padTo2Digits(date.getDate())].join('-');
}

// CREATE TOAST

function createToast($theme, $icon, $title, $content) {
    // create toast
    $idToast = "toast-" + new Date().getTime().toString();
    $('body').append($('<div>' + '<div class="toast toast-css ' + $theme + ' text-dark" role="alert" id="' + $idToast + '">\n' + '      <div class="toast-header">\n' + $title + '\n' + '      </div>\n' + '      <div class="toast-body">\n' + '        <i class="' + $icon + '"></i>\n' + $content + '\n' + '      </div>\n' + '    </div></div>').html());
    $('#' + $idToast).toast({delay: 3000});
    $('#' + $idToast).toast('show');
    // position CSS toast
    $countToast = $('.toast').length;
    $posBottom = 5 + ($countToast - 1) * 18;
    $('#' + $idToast).css('bottom', $posBottom + 'vh');
    // dynamic toast
    setTimeout(function () {
        $('#' + $idToast).remove();
    }, 6000);
}

// CONVERSION OBJECT TO ARRAY

function convertirObjectToArray($myObj) {
    var array = $.map($myObj, function (value, index) {
        return [value];
    });
    return array;
}

function convertiMultiObjectToArray($tabMyObj) {
    let array = [];
    $.each($tabMyObj, function (key, value) {
        array.push(convertirObjectToArray(value));
    })
    return array;
}

function hideAndRemove($selector) {
    $($selector).modal('hide');
    $($selector).remove();
}

function insert_badge($bg, $label) {
    return `<span class="badge badge-` + $bg + `-lighten">` + $label + `</span>`
}

function addSplitToObject(obj, stringToSplit, splitter) {
    return stringToSplit.split(splitter)
}

function personnaliserMenu($title) {
    $('title').text('IOL - ' + $title)
}

function exportToExcel($btn, $prefix, $table) {
    $filename = 'file-' + new Date().getTime().toString() + '.xls';
    $($btn).on('click', function () {
        $($table).table2excel({
            filename: $filename
        });
    })
}

function exportToExcelCustomBtn($btn, $prefix, $table){
    $filename = 'file-' + new Date().getTime().toString() + '.xls';
    $($btn).on('click', function () {
        $($table).table2excel({
            filename: $filename
        });
    })
}


function push_Type_paiement($selectID){
    $($selectID).append('' +
        ' <option value="ESPECE">Espèces</option>' +
        ' <option value="MOBILE_MONEY">Mobile Money</option>' +
        ' <option value="CHEQUE">Chèques</option>' +
        ' <option value="VIREMENT">Virement</option>')
}


function generate_barcode_text($text) {
    /*
    BarCode format
    -04 chiffres : code societe (0-3)
    -03 chiffres : code categorie (4-6)
    -06 chiffres : code article (7-12)
    -02 chiffres : code unite (13-14)
     */
    $codeArray = addSplitToObject(null, $text, '-');
    return numberToStringZero($codeArray[0], 4) + ' ' + numberToStringZero($codeArray[1],3)
        + ' ' + numberToStringZero($codeArray[2], 6) + ' ' + numberToStringZero($codeArray[3], 2)
}

function numberToStringZero($number,$length){
    $str = '';
    while ($length >1) {
        $str += (Math.pow(10,$length-1) > $number) ? '0' : '';
        $length--;
    }
    return $str + $number;
}

function _count_table_content($table, $selector) {
    $($selector).text($($table + ' tbody tr').length);
    return $($table + ' tbody tr').length;
}

function to_js_number($n) {
    $n = $n.replaceAll(' ', '');
    $n = $n.replaceAll(',', '.');
    return parseFloat($n);
}