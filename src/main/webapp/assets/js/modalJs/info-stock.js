$(function() {
    /* -------------------------
            INFO STOCK
     ---------------------------*/
    let namespace = "#info-stock ";
    // update info stock
    $(namespace + '.btn-update-stock').on('click',function(){
        $filialeId = $(namespace+"#filiale-id").attr("value-id");
        let data = $('#info-stock').attr('data-id').split(",");
        $magasinId = data[0];
        $articleId = data[1];
        $userId = $(namespace+"#user-id").attr("value-id");
        let url = "http://localhost:8080/api/v1/articles/"+$articleId+"/"+$filialeId;
        execute_ajax_request("get",url,null,(data)=>{
            $("#inventaire-table-unite tbody").empty();
            $.each(data,(key,value)=>{
                $tr = [
                    value.unite.designation,
                    `<input type="number" required  class="form-control input-group-sm" value="0">`
                ]
                push_to_table_list("#inventaire-table-unite",value.unite.id,$tr);
            });
            let magasin = $('#info-stock p.label-magasin').text();
            let article = $('#info-stock p.label-designation-article').text();
            $("#label-magasin").text(magasin);
            $("#label-designation-article").text(" Nombre d'unité : "+data.length);
            $("#label-nb-unite").text(article);
        });
        $('#modal-inventaire-stock').modal('show');
    })
    // ENREGISTRER INVENTAIRE
    $("#btn-enregistrer-inventaire-stock").click(()=>{
       let tr = $("#inventaire-table-unite tbody tr");
       let tab = [];
       for (let i = 0; i < tr.length; i++){
            let td = $(tr[i]).children();
            let input = td.find("input");
            $uniteId = $(tr[i]).attr("id");
            let quantite = $(input).val();
            let infoArticleMagasin = {};
            infoArticleMagasin.typeOperation = "INVENTAIRE";
            infoArticleMagasin.magasin = {
                id: $magasinId
            }
            infoArticleMagasin.user = {id : $userId };
            infoArticleMagasin.unite = {id: $uniteId};
            infoArticleMagasin.article = {id : $articleId };
            infoArticleMagasin.quantiteAjout = quantite;
            infoArticleMagasin.date = new Date();
            infoArticleMagasin.reference = "INV-"+infoArticleMagasin.date.getUTCDate();
            tab.push(infoArticleMagasin);
        }
       let url = "http://localhost:8080/api/v1/info";
       execute_ajax_request("post",url,tab,(data)=>{
           $('#modal-inventaire-stock').modal('hide');
       })
    })

    // UPDATE QUANTITE ALERT
    $(".btn-update-alert").click(()=>{
        $filialeId = $(namespace+"#filiale-id").attr("value-id");
        let data = $('#info-stock').attr('data-id').split(",");
        $magasinId = data[0];
        $articleId = data[1];
        $('#modal-qtt-alert-stock').modal('show');
        let quantite = $('#info-stock p.label-quantite-alerte-article').text().split(":")[1];
        $("#input-quantite-stock").val(quantite);
    });

    // ENREGISTRER QUANTITE EN ALERT
    $(".btn-enregistrer-qtt-alert").click(()=>{
        $filialeId = $(namespace+"#filiale-id").attr("value-id");
        let data = $('#info-stock').attr('data-id').split(",");
        $magasinId = data[0];
        $articleId = data[1];
        let qtt_alert = $("#input-quantite-stock").val();
        let url = "http://localhost:8080/api/v1/inventories-alert/"+$filialeId+"/"+$articleId+"/"+qtt_alert;
        execute_ajax_request("put",url,null,(data)=>{
             $('#modal-qtt-alert-stock').modal('hide');
        });
    });
})