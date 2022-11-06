$(function () {

    /*------------------------------

        JS NOUVEAU CATEGORIE

     -------------------------------*/

    let namespace = "#standard-modal2 ";

    let isUpdateOperation = false;
    let editBtnId = 1;
    let selectedVal = "";
    let siblings;
    let div = $("#categorieTabList tbody tr td div");
    let tr = $("#categorieTabList tbody tr");

    div.hide();

    tr.mouseenter(function () {

        $(this).children().last().children().first().show();

    });

    tr.mouseleave(function () {

        $(this).children().last().children().first().hide();

    });

    /*

    mask et validation

     */

    $(function() {
        $(namespace + 'form').validate( {
            rules : {
                nomCategorie : {required : true}
            },
            messages : {
                nomCategorie : {required : 'Nom du categorie requis'}
            }
        })
    })

    function validation_nouveau_categorie() {
        $(namespace + 'form').validate();

        return $(namespace + 'form').valid()
    }

    $(namespace + "#saveCategorieBtn").click(function () {
        if (validation_nouveau_categorie()) {

            $(this).closest('.modal').modal('hide')

            let newVal = $("#nomCategorie").val();
            if (!isUpdateOperation) {
                let categoriesUrl = 'http://localhost:8080/api/v1/categories';
                let jsonData = {
                    libelle: newVal
                };
                $.ajax({
                    type: 'POST',
                    url: categoriesUrl,
                    contentType: 'application/json',
                    data: JSON.stringify(jsonData),
                    success: function (data) {
                        //reset the input
                        $("#nomCategorie").val("");
                        /* ACTION */
                        $tdActionContent = $(' ' + '<div class="d-inline-flex justify-content-center">' +
                            '<a id="' + data.id + '" href="#" class="editCategorie"><i class="uil-pen"></i></a> ' +
                            '<a id="' + data.id + '" href="#" class="deleteCategorie"><i class="uil-trash-alt"></i></a>' + '</div>');
                        $oneCategorie = [data.libelle, $tdActionContent];
                        push_to_table_list("#categorieTabList", data.id, $oneCategorie)

                    }
                });
                createToast('bg-success', 'uil-file-check', 'Creation Fait', 'Creation du nouveau cat&eacute;gorie effectu&eacute; avec succ&egrave;s!')
            } else {
                if (selectedVal !== newVal) {
                    let url = "http://localhost:8080/api/v1/categories/" + editBtnId;
                    let jsonData = {
                        libelle: newVal
                    };
                    $.ajax({
                        type: 'PUT',
                        url: url,
                        contentType: 'application/json',
                        data: JSON.stringify(jsonData),
                        success: function (data) {

                            //reset the input

                            $("#nomCategorie").val("");
                            siblings.html(newVal)
                        }
                    });
                    createToast('bg-success', 'uil-pen', 'Modification Fait', 'Modification du cat&eacute;gorie effectu&eacute; avec succ&egrave;s!')
                }
                isUpdateOperation = false;
            }
        }
    });

    /*
     editer categorie
     */

    $(document).on('click', ".editCategorie", function () {

        $(namespace).modal('show')
        isUpdateOperation = true;
        editBtnId = $(this).attr("id");
        siblings = $(this).parent().parent().siblings();
        let text = siblings.html();
        selectedVal = $("#nomCategorie").val(text);
    });

    /*
     supprimer categorie
     */

    $(document).on('click', ".deleteCategorie", function () {

        let btn = $(this);
        let deleteBtnId = btn.attr("id");
        let url = "http://localhost:8080/api/v1/categories/" + deleteBtnId;
        $modalId = "delete-categorie-article";
        create_confirm_dialog('Supprimer categorie', 'Voulez vraiment supprimer cette categorie ? ', $modalId, "Oui, supprimer", "btn-warning")
            .on('click', function () {
                $.ajax({
                    type: 'DELETE', url: url, success: function () {
                        // Supprimer l'element
                        btn.parent().parent().parent().detach();
                        hideAndRemove('#' + $modalId);
                        createToast('bg-danger', 'uil-trash-alt', 'Suppression Fait', 'Suppression du cat&eacute;gorie effectu&eacute; avec succ&egrave;s!')
                    }
                });
            })
    });
});