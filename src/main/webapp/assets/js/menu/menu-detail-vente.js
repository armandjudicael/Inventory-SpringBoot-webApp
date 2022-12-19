 $(function(){
    let namespace = "#menu-detail-vente ";
    exportToExcel(namespace + '.btn-export-to-excel','detail-ventes-' , namespace + '.table-detail-vente');

    let appendDataToTable = (data) =>{
        clear_table(namespace+"#details-vente-table");
        data.forEach(salesValue=>{
            let info = salesValue.infoArticleMagasin;
            let avoir = salesValue.avoir;
            if (avoir !== null){
                let infoAvoir = avoir.infoArticleMagasin;
                append_sales_or_invoice_item(infoAvoir,salesValue,false);
            }
            append_sales_or_invoice_item(info,salesValue,true);
        });
    }

    const  append_sales_or_invoice_item = (info,salesValue,is_sales)=>{
        info.forEach(infoValue=>{
            let tr =[
                infoValue.reference,
                salesValue.client.nom,
                infoValue.article.designation,
                infoValue.unite.designation,
                infoValue.quantiteAjout,
                infoValue.montantArticle/infoValue.quantiteAjout,
                infoValue.montantArticle,
                infoValue.magasin.nomMagasin,
                infoValue.date
            ];
            push_to_table_list(namespace+"#details-vente-table", is_sales ? salesValue.id : infoValue.id,tr);
        });
    }

    _count_table_content(namespace + '#details-vente-table', namespace + '.text-count-vente');

    let getAllSales = (url) =>{
        execute_ajax_request("get",url,null,(data)=>{
            appendDataToTable(data);
            _count_table_content(namespace + '#details-vente-table', namespace + '.text-count-vente');
        });
    };

    // Magasin filter
    $(document).on('change',namespace+"#magasin-select-operation",function(){
        let magasinId = $(this).val();
        let url = "http://localhost:8080/api/v1/stores/"+magasinId+"/sales"
        getAllSales(url);
    });
    // NAME FILTER
    $(namespace+"#btn-search-operation").click(()=>{
        let text = $(namespace+"#input-search-operation").val();
        let url = "";
        let filiale_id = $(namespace + '#filiale-id').attr("value-id");
        let typeFilter = $(namespace+"#type-filter option:selected").val();
        if (text!==undefined && text!=="") url = "http://localhost:8080/api/v1/sales/subsdiary/"+filiale_id+"/filter-type/"+typeFilter+"/"+text.toLowerCase();
        else url = "http://localhost:8080/api/v1/subsidiaries/"+filiale_id+"/sales";
        getAllSales(url);
    });
    // SEARCH BUTTON
    $(namespace+"#search-button").click(()=>{
        let beginDate = $(namespace+"#begin-date-input").val();
        let endDate = $(namespace+"#end-date-input").val();
        let filiale_id = $(namespace + '#filiale-id').attr("value-id");
        let url = "http://localhost:8080/api/v1/sales/subsdiary/"+filiale_id+"/between-date/"+beginDate+"/"+endDate;
        getAllSales(url);
    });

    $(document).on("keyup",namespace+"#input-search-operation",()=>{
        $(namespace+"#btn-search-operation").click();
    });

})
