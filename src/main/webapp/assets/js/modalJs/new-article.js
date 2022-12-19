$(function (){
    /*--------------------------
        JS NOUVEAU ARTICLE
     ---------------------------*/
    let namespace = "#new-article ";
    let isCreateArticle = true;
    let editedArticleId = 1;
    // Tout les champ non editable par default
    let categorieUrl = 'http://localhost:8080/api/v1/categories';
    let filiale_id = $(namespace + '#filiale-id').attr("value-id");
    $articleUrl = 'http://localhost:8080/api/v1/articles';
    $deleted = true;
    $articleTable = $("#articleTable tbody");

    function init_table_unite(){
        $('#table-unite input').attr('disabled', '')
        // ajout du nouveau unite
        $("#btn-new-unite").click(
            function () {
                // valider tout
                let type = $("#new-article").attr("operation-type");
                if (type === "CREATE") $('#table-unite .btn-add-unite').trigger('click');
                let table = $("#table-unite tbody");
                let length = $("#table-unite tbody tr").length;
                // cacher le enregistrement
                $("#table-unite tbody tr th a.btn-success").hide();
                $('#table-unite input').attr('disabled', '');
                // cacher edition et suppression du dernier
                $("#table-unite tbody tr th #edit_" + length).hide();
                $("#table-unite tbody tr th #del_" + length).hide();
                let count = $('#table-unite tbody tr').length+1;
                let tr = `<tr status="NEW" >
                            <td><input disabled type="text" required  class="form-control input-sm not-editable" value="`+count+`"></td>
                            <td><input type="text" required  class="form-control input-sm" value="designation"></td>
                            <td><input type="text" required  class="form-control input-sm" value="1"></td>
                            <td><input type="text" required  class="form-control input-sm" value="1"></td>
                            <td class="d-inline-flex">
                                <a class="btn btn-primary btn-sm btn-edit-unite"><i class="uil-pen"></i></a>&nbsp;
                                <a class="btn btn-danger btn-sm btn-del-unite"><i class="uil-trash-alt"></i></a>&nbsp;
                                <a class="btn btn-success btn-sm btn-add-unite"><i class="uil-check-square"></i></a>
                            </td>
                        </tr>`;
                table.append(tr);
            }
        )
        /* Edition ou enregistrement enregistrement */
        $('#table-unite').on('click', '.btn-add-unite', (function (){
            let type = $("#new-article").attr("operation-type");
            let au_id = $(this).closest('tr').attr("id");
            let status = $(this).closest('tr').attr("status");
            if (type==="EDIT"){
                let inputTab = $(this).closest('tr').find('input');
                let niveau = $(inputTab[0]).val();
                let designation_unite = $(inputTab[1]).val();
                let quantite_niveau = $(inputTab[2]).val();
                let poids = $(inputTab[3]).val();
                if (status==="NEW"){
                    let url = "http://localhost:8080/api/v1/unites";
                    let au = {};
                    au.article = {id : editedArticleId};
                    au.niveau = niveau;
                    au.unite = {designation : designation_unite};
                    au.quantiteNiveau = quantite_niveau;
                    au.poids = poids;
                    execute_ajax_request("post",url,[au],(data)=> {
                        $(this).closest('tr').removeAttr("status");
                        $(this).closest('tr').attr("id",data[0].id);
                        createToast('bg-success', 'uil-trash-alt', 'Enregistrement Fait', 'Unité enregistré avec succes!');
                    });
                }else{
                    let au_wrapper = {};
                    au_wrapper.poids = poids;
                    au_wrapper.quantiteNiveau = quantite_niveau;
                    au_wrapper.designationUnite = designation_unite;
                    let url = "http://localhost:8080/api/v1/unites/item-unity/"+au_id;
                    execute_ajax_request("put",url,au_wrapper,(data)=>{
                        createToast('bg-success', 'uil-trash-alt', 'Enregistrement Fait', 'Modification unité enregistré avec succes !');
                    });
                }
            }
            $(this).closest('tr').find('input').attr('disabled', '');
            $(this).hide();
        }))
        /*  suppression unite */
        $('#table-unite').on('click', '.btn-del-unite', (function(){
            let type = $("#new-article").attr("operation-type");
            if (type==="EDIT"){
                let au_id = $(this).closest('tr').attr("id");
                let unite_id = $(this).closest('tr').attr("unite-id");
                let url = "http://localhost:8080/api/v1/info/check/"+unite_id;
                execute_ajax_request("GET",url,null,(data)=>{
                    if (data===true){
                        let url = "http://localhost:8080/api/v1/articles/item-unite/"+au_id;
                        $(this).closest('tr').remove();
                        execute_ajax_request("DELETE",url,null,()=>{
                            createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait', 'Suppression de l\' article effectu&eacute; avec succ&egrave;s!')
                        });
                    }else createToast('bg-danger', 'uil-trash-alt', 'Echec de la Suppression', " L'unité est déja concerné par une operation ");
                })
            }else $(this).closest('tr').remove();
        }))
        /*  edition d'une unite */
        $('#table-unite').on('click', '.btn-edit-unite', (function () {
            $(this).closest('tr').find('input').removeAttr('disabled');
            $(this).closest('tr').find('.btn-add-unite').show();
        }));
    }

    function fetch_categorie(select){
        execute_ajax_request("get",categorieUrl,null,(categories)=>{
            for (let i = 0; i < categories.length; i++) {
                let option = `<option value="` + categories[i].id + `">` + categories[i].libelle + `</option>`
                select.append(option);
            }
        })
    };

    function init_add_and_save_article_btn(){
        // chargement des categories lors de l'affichage du formulaire de categorie
        $("#newArticleBtn").click(() => {
            let tdElement = $("#categorieTabList tbody tr td:first-child");
            let select = $(".form-select");
            select.empty();
            $("#new-article").attr("operation-type","CREATE");
            if (tdElement.length !== 0){
                for (let i = 0; i < tdElement.length; i++) {
                    let text = tdElement[i].innerText;
                    let id = $(tdElement[i]).parent().attr("id");
                    if (text !== "Toutes") {
                        let option = `<option value="` + id + `">` + text + `</option>`
                        select.append(option);
                    }
                }
            }else fetch_categorie(select);
        });

        function get_all_unite_on_table(data){
            let tr = $('#table-unite tbody tr');
            let articleUniteTab = [];
            for (let i = 0; i < tr.length; i++){
                let uniteRow = [];
                let td = $(tr[i]).children();
                let input = td.find("input");
                for (let j = 0; j < input.length; j++) uniteRow.push(input[j].value);
                // UNITE
                let unite = {};
                unite.designation = uniteRow[1];
                // ARTICLE UNITE
                let au = {};
                au.article = data;
                au.unite = unite;
                au.niveau = uniteRow[0];
                au.quantiteNiveau = uniteRow[2];
                au.poids = uniteRow[3];
                articleUniteTab.push(au);
            }
            return articleUniteTab;
        };

        function persit_default_price(data){
            let pvuafTab = [];
            $.each(data,(key,value)=>{
                let fuap = {};
                fuap.user = {
                    id: $userId
                }
                fuap.filiale = {id : filiale_id }
                fuap.unite = { id : value.unite.id }
                fuap.article = {id: value.article.id}
                fuap.dateEnregistrement = new Date();
                fuap.prixVente = "0";
                pvuafTab.push(fuap);
            });
            let url = "http://localhost:8080/api/v1/prices";
            execute_ajax_request("POST",url,pvuafTab,null);
        };

        function persist_article_and_unite(){
            let designation = $("#designation").val();
            let categorieId = $("#categorie option:selected").val();
            let categorieLibelle = $("#categorie option:selected").text();
            $userId = $(namespace + '#user-id').attr("value-id");
            let articleStatus = "USED";
            let article = {}
            article.designation = designation;
            article.categorie = {id: categorieId, libelle: categorieLibelle};
            article.status = articleStatus;
            article.isPerishable = true;
            if (!isCreateArticle) article.id = editedArticleId;
            execute_ajax_request('POST',$articleUrl,article,(data)=>save_all_unite(data));
            // vider champ
            $("#designation").val('')
        }

        function save_all_unite(data){
            let articleUniteTab = get_all_unite_on_table(data);
            execute_ajax_request("POST","http://localhost:8080/api/v1/unites",articleUniteTab,(data)=>{
               data.forEach(function (au){
                    let tableRow = `
                             <tr id=` + au.article.id + `>
                                <td>` + au.article.designation + `</td>
                                <td>` + au.article.categorie.libelle + `</td>
                                <td>` + au.poids + `</td>
                                <td>` + au.unite.designation + `</td>
                                <td>` + au.quantiteNiveau + `</td>
                                <td>
                                            <div>
                                                <a id="` + au.article.id + `" class="btn-sm btn-info editArticleBtn"><i class="uil-pen"></i></a>
                                                <a id="` + au.article.id + `"  class="btn-sm btn-danger deleteArticleBtn "><i class="uil-trash-alt"></i></a>
                                                <a id="` + au.article.id + `"  class="btn-sm btn-warning hideArticleBtn"><i class="uil-eye-slash"></i></a>
                                            </div>
                                </td>
                            </tr>`;
                    $articleTable.append(tableRow);
                });
                // INSERTION DES PRIX
                persit_default_price(data);
                // Clear the form
                $("#designation").text("");
                // Supprimer la liste des unités
                $('#table-unite tbody tr:not(.default-unite)').remove()
                // reinialiser valeur defaut
                $('#table-unite tbody tr:first').children().eq(1).children('input').val('designation');
                $('#table-unite tbody tr:first').children().eq(2).children('input').val('1');
            });
        };
        /* mask et validation */
        $(function() {
            $(namespace + 'form').validate({
                rules : {
                    designation : {required: true},
                    categorie : {required: true}
                },
                messages : {
                    designation : {required : 'designation requis pour une article'},
                    categorie : {required : 'categorire requis pour une article'}
                }
            });
        });

        function validation_nouveau_article(){
            $(namespace + 'form').validate();
            return $(namespace + 'form').valid();
        };

        const persist_item = ()=>{
            if (validation_nouveau_article()){
                   persist_article_and_unite();
                   createToast('bg-success', 'uil-file-check', 'Creation Fait', 'Creation d\'un nouveau article effectu&eacute; avec succ&egrave;s!')
            }
        };
        // enregistrement de l'article
        $("#saveArticleBtn").click(() => {
            let type = $("#new-article").attr("operation-type");
            if (type==="CREATE") persist_item();
            else{
                let new_item_name = $("#designation").val();
                let url = "http://localhost:8080/api/v1/items/"+editedArticleId+"/"+new_item_name;
                execute_ajax_request("put",url,null,(data)=>{
                });
            }
            $(namespace).modal('hide');
        });
    };

    function init_add_article_modal(){
        init_table_unite();
        init_add_and_save_article_btn();
    };

    function init_table_row_event(){
        // Initialisation de l'evenement des tr
        let div = $("#articleTable tbody tr td div");
        let tr = $("#articleTable tbody tr");
        div.hide();
        tr.mouseenter(function () {
            $(this).children().last().children().first().show();
        });
        tr.mouseleave(function () {
            $(this).children().last().children().first().hide();
        });
    };

    function init_table_action(){

        function init_categorie_select() {
            let tdElement = $("#categorieTabList tbody tr td:first-child");
            let select = $(".form-select");
            // Supprimer toutes les elements dans la select
            $(namespace + "select#categorie").empty();

            if (tdElement.length !== 0) {
                for (let i = 0; i < tdElement.length; i++) {
                    let text = tdElement[i].innerText;
                    let id = $(tdElement[i]).parent().attr("id");
                    if (text !== "Toutes") {
                        let option = `<option value="` + id + `">` + text + `</option>`
                        select.append(option);
                    }
                }
            }
        }

        $(document).on("click",".editArticleBtn",function (){
            $("#new-article").attr("operation-type","EDIT");
            editedArticleId = $(this).attr("id");
            $tr = $(this).closest('tr');
            designation = $tr.children()[1].innerText;
            categorie = $tr.children()[2].innerText;
            // affectation dans la formulaire
            $('input#designation').val(designation)
            $('select#categorie').append('<option>' + categorie + '</option>');
            $('select#categorie').attr('disabled','true');
            let url = 'http://localhost:8080/api/v1/articles/' + editedArticleId + "/unites";
            execute_ajax_request('get',url,null,(data)=> append_item_unite(data));
        });

        const append_item_unite = (data)=>{
            let table = $("#table-unite tbody");
            // SUPRIMER TOUTES LES DONNE
            table.empty();
            data.forEach((a)=> {
                let tr = `<tr unite-id="`+a.unite.id+`"  id="` + a.id + `">
                                    <td><input type="text" disabled required  class="form-control input-sm not-editable" value="` + a.niveau+ `"></td>
                                    <td><input type="text" required  class="form-control input-sm" value="` + a.unite.designation+ `"></td>
                                    <td><input type="text" required  class="form-control input-sm" value="` + a.quantiteNiveau+ `"></td>
                                    <td><input type="text" required  class="form-control input-sm" value="` + a.poids + `"></td>
                                    <td class="d-inline-flex">
                                        <a class="btn btn-primary btn-sm btn-edit-unite"><i class="uil-pen"></i></a>&nbsp;
                                        ` + (a.niveau === 1 ? '' : '<a class="btn btn-danger btn-sm btn-del-unite"><i class="uil-trash-alt"></i></a>&nbsp; ') + `
                                        <a class="btn btn-success btn-sm btn-add-unite"><i class="uil-check-square"></i></a>
                                    </td>
                         </tr>`;
                table.append(tr);
            });
        };

        function updateItem($deleted, $trCurrent){
            $status = $deleted ? "DELETED" : "HIDDEN";
            $url = $articleUrl + "/" + $id + "/" + $status;
            execute_ajax_request('PUT',$url,null,(data)=>{
                $trCurrent.remove();
                hideAndRemove('#' + $modalId + '');
                createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait', 'Suppression de l\' article effectu&eacute; avec succ&egrave;s!')
            })
        };

        function updateArticle($trCurrent, $deleted, $codeArticle) {
            $id = $($trCurrent).attr("id");
            $modalId = $deleted ? "suppression-article" : "masquer-article";
            $modalText = $deleted ? ' Supprimer article ' : 'Masquer Article';
            $modalTitle = $deleted ? 'Voulez vraiment supprimer cet article (Designation:' + $codeArticle + ') ?'
                : 'Voulez vraiment masquer cet article (id:' + $codeArticle + ') ?';
            $labelButton = $deleted ? "supprimer" : "masquer";
            $classButton = $deleted ? "btn-danger" : "bg-warning";
            create_confirm_dialog($modalText, $modalTitle, $modalId, "Oui ,"+$labelButton, $classButton).on('click', function (){
                    updateItem($deleted, $trCurrent);
                })

        };

        $(document).on('click', '#articleTable .deleteArticleBtn', function (){
            $codeArticle = $(this).closest('tr').children().eq(1).text();
            $trCurrent = $(this).closest('tr');
            updateArticle($trCurrent, $deleted, $codeArticle);
        })

        $(".hideArticleBtn").click(function () {
            $codeArticle = $(this).closest('tr').children().eq(0).text();
            $trCurrent = $(this).closest('tr');
            updateArticle($trCurrent, !$deleted, $codeArticle);
        })
    };

    function init_article_table(){
        init_table_action();
        init_table_row_event();
    }

    init_article_table();

    init_add_article_modal();
});