$(function () {

    const namespace = "#menu-stock ";
    let filiale_id = $(namespace+"#filiale-id").attr("value-id");
    const TOUTES = "";
    const INVENTORY_URL = "http://localhost:8080/api/v1/inventories/"+filiale_id;
    const STORE_INVENTORY_URL = "http://localhost:8080/api/v1/inventories/stores/";
    const INVENTORY_TABLE = $(namespace+"#inventory-table tbody");
    let magasin_id = 0;
    let unite_id = 0;
    let article_id = 0;
    /*------------------------------
                 MENU STOCK
     -------------------------------*/
    exportToExcel(namespace + '.btn-export-to-excel','stocks-' , namespace + '#inventory-table')
    // CONST
    const QUANTITE_ALERT = 0;
    // css
    $('.btn-40').css('height', '40px');
    // Chargement des données de stock

    function append_inventory_item_to_table(data) {
        // Supprimer les données dans la table
        clear_table(namespace+"#inventory-table")
        // ajouter les donnés dans la table
        data.forEach(value =>{
            $tr = [value.article, value.unite, value.categorie, value.nomMagasin, value.quantite];
            $stockId = value.magasinId + "-" + value.articleId + "-" + value.uniteId;
            push_to_inventory_table_list(namespace + "#inventory-table", $stockId, $tr);
        })
        _count_table_content(namespace + '#inventory-table', namespace + '.text-count-stock')
    }

    _count_table_content(namespace + '#inventory-table', namespace + '.text-count-stock')

    $(namespace+"#btn-article-alert").click(()=>{
        $filialeId = $(namespace+"#filiale-id").attr("value-id");
        let url = "http://localhost:8080/api/v1/subsidiaries/"+$filialeId+"/inventories";
        execute_ajax_request("get",url,null,(data)=> append_inventory_item_to_table(data));
    });

    function fetchInventoryActivities(magasinId, articleId, uniteId){
        let url = "http://localhost:8080/api/v1/activities/" + magasinId + "/" + articleId + "/" + uniteId;
        magasin_id = magasinId;
        unite_id = uniteId;
        article_id = articleId;
        execute_ajax_request("GET",url,null,(data)=>{
            $(namespace + "#table-operation-stock tbody").empty();
            $.each(data, (key, value) => {
                let tr = [
                    value.reference,
                    value.typeOperation,
                    value.quantiteAjout + " " + value.unite.designation,
                    value.quantiteStockApresOperation + " " + value.unite.designation,
                    value.date,
                    value.user.nom
                ];
                push_to_table_list(namespace + "#table-operation-stock", key, tr);
            })
        })
    }

    function updateInfoLabel(storeName, article, unite, categorie){
        $('#info-stock p.label-magasin').text('Magasin : ' + storeName);
        $('#info-stock p.label-designation-article').text('Designation : ' + article);
        $('#info-stock p.label-unite-article').text('Unite : ' + unite);
        $('#info-stock p.label-categorie-article').text('Categorie : ' + categorie);
        $('#info-stock p.label-stock-initial-article').text('Stock Initial : ' + $currentArticleTr.children('.td-info-stock').text());
        $('#info-stock p.label-stock-final-article').text('Stock Final : ' + $currentArticleTr.children('.td-info-stock').text());
        $('#info-stock #date-debut').val(formatDate(new Date()));
        $('#info-stock #date-fin').val(formatDate(new Date()));
    }

    function fetchInventoryAlert(articleId,filialeId){
        let url = "http://localhost:8080/api/v1/inventories-alert/" + filialeId + "/" + articleId;
        execute_ajax_request("get", url, null, (data) => {
            $('#info-stock p.label-quantite-alerte-article').text(" Quantité alert : " + data);
        })
    }

    $(document).on('click',namespace+'.btn-info-stock', function () {
        $currentArticleTr = $(this).closest('tr');
        $filialeId = $(namespace+"#filiale-id").attr("value-id");
        let storeName = $currentArticleTr.children().eq(3).text();
        let categorie = $currentArticleTr.children().eq(2).text();
        let unite = $currentArticleTr.children().eq(1).text();
        let article = $currentArticleTr.children().eq(0).text();
        let id = $currentArticleTr.attr('id');
        let split = id.split("-");
        let magasinId = split[0];
        let articleId = split[1];
        let uniteId = split[2];
        if (magasinId!=0){
            $(namespace+"#info-stock").modal("show");
            $('#info-stock').attr('data-id',[magasinId,articleId]);
            fetchInventoryActivities(magasinId, articleId, uniteId);
            // affectation des valeur de chaque paragraphe
            updateInfoLabel(storeName, article, unite, categorie);
            fetchInventoryAlert(articleId,$filialeId);
        }
    })

    // RECHERCHER
    $(document).on("keyup",namespace+"#stock-search",()=>{
        let name = $(namespace+"#stock-search").val();
        let filialeId = $(namespace+"#filiale-id").attr("value-id");
        if (name!==""){
            let url = "http://localhost:8080/api/v1/subsidiaries/"+filialeId+"/inventories/"+name;
            execute_ajax_request("get",url,null,(data)=>{
                clear_table(namespace+"#inventory-table")
                $.each(data,(key,value)=>{
                    let tr = `
                         <tr>
                                <td>`+value.article+`</td>
                                <td>`+value.unite+`</td>
                                <td>`+value.categorie+`</td>
                                <th>`+value.nomMagasin+`</th>
                                <td class="td-info-stock">
                                       <a id="`+value.magasinId+`-`+value.articleId+`-`+value.uniteId+`" type="button" class="btn-default mr-1 btn-info-stock">`+value.quantite+``+value.unite+`</a>
                                </td>
                        </tr>
                     `;
                    INVENTORY_TABLE.append(tr);
                });
                _count_table_content(namespace + '#inventory-table', namespace + '.text-count-stock')
            })
        }
    });

    /* EVENT SELECT */
    $(document).on('change',namespace+"#magasin-select",function(){
        let val = $(this).val();
        let url = val===TOUTES ? INVENTORY_URL : "http://localhost:8080/api/v1/magasins/"+val+"/inventories";
        execute_ajax_request("get",url,null,(data)=>append_inventory_item_to_table(data));
        update_montant_lable( val==TOUTES ? "http://localhost:8080/api/v1/inventories/"+filiale_id+"/total-values" : "http://localhost:8080/api/v1/inventories/"+filiale_id+"/store/"+val+"/total-values");

    });
    /* button event */
    $(document).on('click',namespace+" #toutes-button",function () {
        execute_ajax_request("get",INVENTORY_URL,null,(data)=>append_inventory_item_to_table(data));
    });

    /* VALEUR DE STOCK */

    $('.btn-stock-valeur').click(function(){
        $('.s-value').toggle();
        $('.s-no-value').toggle();
        let  url = "http://localhost:8080/api/v1/inventories/"+filiale_id+"/total-values";
        update_montant_lable(url);
    });

    const update_montant_lable = (url) => {
        execute_ajax_request("get",url,null,(data)=> {
            $('.label-valeur-stock').text("Montant total : " + data + " Ar");
        })
    }

    /* click of tr */

    $('.table-article-stock tr').click(function(){

    })

    $('.s-value').hide();

    /* click stock en valeur  */
    $('.btn-stock-valider').click(function (){
        $('.btn-stock-valeur').click();
    });

})