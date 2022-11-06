$(function(){
    let namespace = "#liste-operation ";
    let url = "http://localhost:8080/api/v1/magasins/";
    let filiale_id = $(namespace + '#filiale-id').attr("value-id");
    exportToExcel('.btn-export-to-excel','liste-prix-articles', '.table-liste-operation');
    _count_table_content(namespace + '.table-liste-operation', namespace + '.text-count-operation-list');

    function append_activity_item_to_table(data){
        clear_table(namespace + ".table-liste-operation");
        data.forEach(value=>{
            let data = [value.reference,
                value.article.designation,
                value.unite.designation,
                value.typeOperation,
                value.quantiteAjout +" "+value.unite.designation,
                value.quantiteStockApresOperation +" "+value.unite.designation,
                value.date,
                value.magasin.nomMagasin,
                value.description,
                value.user.nom==null ? "inconnu" : value.user.nom
            ]
            push_to_table_list(namespace + ".table-liste-operation",data.id,data)
        })
        _count_table_content(namespace + '.table-liste-operation', namespace + '.text-count-operation-list')
    }

    function getAllActivities(url){
        execute_ajax_request("get",url,null,(data)=> append_activity_item_to_table(data));
    }
    // Magasin filter
    $(document).on('change',namespace+"#magasin-select-operation",function(){
        let magasinId = $(this).val();
        let ressource_url = "";
        let subsisdiary_id = $(namespace + '#filiale-id').attr("value-id")
        if (magasinId === "toutes")  ressource_url = "http://localhost:8080/api/v1/subsidiaries/"+subsisdiary_id+"/activities";
        else ressource_url = url+magasinId+"/activities";
        getAllActivities(ressource_url);
        _count_table_content(namespace + '.table-liste-operation', namespace + '.text-count-operation-list')
    });

    // BUTTON RECHERCHER
    $(namespace +"#search-button").click(()=>{
            let beginDate = $(namespace+"#begin-date-input").val();
            let endDate = $(namespace+"#end-date-input").val();
            if (beginDate!==undefined && beginDate!==null
                && endDate!==undefined && endDate!==null
                && beginDate!=="" && endDate!==""){
                let url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/activities/date/"+beginDate+"/"+endDate;
                getAllActivities(url);
            }
    });

})