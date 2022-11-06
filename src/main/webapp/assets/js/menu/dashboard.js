$(function () {

    let namespace = "#dashboard  ";
    let filiale_id = $(namespace + '#filiale-id').attr("value-id");
    let user_id = $(namespace+"#user-id").attr("value-id");

    // INIT DOUBLE CLICK

    init_dblclick_table(namespace,".table-liste-dette-fournisseur");

    init_dblclick_table(namespace,".table-liste-dette-client");

    init_modal_credit_dette_btn(namespace,filiale_id,user_id);

})