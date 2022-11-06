$(function () {

    /*---------------------------------------------

                JS OPERATION SORTIE

     ------------------------------------------------*/

    let namespace = "#menu-sortie-article ";
    let sortieTab = [];

    /*
     Selecter article
     */

    $(namespace + '#table-liste-article tbody tr').on('dblclick', function () {
        let article_id = $(this).attr("id");
        let unite_id = $(this).children().eq(1).attr("value-id");
        let designation_article = $(this).children().eq(0).text();
        let designation_unite = $(this).children().eq(1).text();
        get_select_affect_to_input(namespace + '#input-designation-article',article_id,designation_article);
        set_select_option_value([unite_id,designation_unite], namespace + " #select-unite-article")
        $(namespace + '#modal-liste-article').modal('hide');
        // après selection article, select * unite de l'article
    })

    /*
     Ajout des articles
     */

    /*
   validation
    */

    $(function () {
        $(namespace + 'form').validate({
            rules: {
                selectMagasin: {required: true},
                designationArticle: {required: true},
                inputQuantite: {required: true, min: 0.0001},
                selectUniteArticle: {required: true}

            },
            messages: {
                selectMagasin: {required: 'Magasin de sortie requis'},
                designationArticle: {required: ''},
                inputQuantite: {required: 'Quantite non valide', min: 'Quantite doit être >0'},
                selectUniteArticle: {required: 'Unite requis pour un article'}
            }
        })
    })

    function validation_ajout_article() {
        $(namespace + 'form').validate();

        return $(namespace + 'form').valid();
    }

    $(namespace + '#btn-ajouter-article-sortie').on('click', function(){
        if (validation_ajout_article()) {
            $ref = 0;
            let articleId = $(namespace + '#input-designation-article').attr('value-id');
            let magasinId= $(namespace + '#select-magasin').val();
            let userId = $(namespace + '#user-id').attr("value-id");
            let designation = $(namespace + '#input-designation-article').val();
            let unite = $(namespace + '#select-unite-article option:selected').text();
            let uniteId = $(namespace + '#select-unite-article option:selected').val();
            let motif = $(namespace + '#input-motif').val();
            let quantite = $(namespace + '#input-quantite-article').val();
            let sortie = {};
            let infoArticleMagasin = {};

            infoArticleMagasin.typeOperation = "SORTIE";
            infoArticleMagasin.quantiteAjout = quantite;
            infoArticleMagasin.date = new Date();
            infoArticleMagasin.reference = "ST - "+$ref;
            infoArticleMagasin.article = {
                id : articleId
            }
            infoArticleMagasin.unite = {id:uniteId};
            infoArticleMagasin.magasin = {id:magasinId};
            infoArticleMagasin.user = {id:userId};
            infoArticleMagasin.description = motif;

            sortie.infoArticleMagasin = infoArticleMagasin;
            sortieTab.push(sortie);

            $articleAjout = [
                designation,
                unite,
                quantite,
                motif,
            ];
            $article_class = ['pr-designation', 'pr-unite', 'pr-quantite', 'pr-description']
            push_to_table_list(namespace + "#table-liste-article-sortie", "", $articleAjout, $article_class);

            // vider les input

            $(namespace + '#input-designation-article').attr('value','');
            $(namespace + '#input-quantite-article').val(0);
            $(namespace + '#select-unite-article option').remove();
        }
    });

    /*
     suppression articles à la table
     */

    $(document).on('dblclick',"#table-liste-article-sortie tbody tr", function() {

        $(this).remove();
        $designation = $(this).children().eq(1).text();
        createToast('bg-danger','uil-trash-alt','Enlevement Article',$designation + ' supprim&eacute;')

    });

    function persist_sortie(){
        let url = "http://localhost:8080/api/v1/sorties";
       execute_ajax_request("POST",url,sortieTab,function (data){
           $ref = data.id;
           sortieTab = [];
           $(namespace + '#table-liste-article-sortie tbody tr').remove();
           createToast('bg-warning',
               'uil-file-check',
               'Sortie d\'article fait',
               $nArticle + ' articles sorties avec succ&egrave;s!');
       })
    }

    /*
     Enregistrement articles
     */

    $(namespace + "#btn-enregistrer-article-sortie").on('click',function(){

        $modalId = 'confirmation-d-sortie-article'
        $nArticle = $(namespace + '#table-liste-article-sortie tbody tr').length;
        $content = '' +
           'Voulez vous vraiment enregistrer les articles entr&eacute;s?' +
           '<li><strong>' + $nArticle + '</strong> Articles</li>';
        create_confirm_dialog('Confirmation de sortie des articles', $content, $modalId, 'Oui, Sortir', 'btn-warning')
            .on('click', function() {
                impression_sortie()
                persist_sortie();
                $('#' + $modalId).modal('hide');
                $('#' + $modalId).remove();

                // empty input
                $(namespace + '#input-motif').val('');
                //$(namespace + '#input-designation-article').val(' ');
                $(namespace + '#select-unite-article option').remove();
                $(namespace + '#input-quantite-article').val('');
            })

    })

    /*

    facturation sortie

     */

    function impression_sortie() {

        $societe = {
            nom : 'Manantsoa',
            slogan : 'Manantsoa, Mora, Mandroso',
            adresse : 'Morafeno, Toamasina',
            ville : 'Toamasina',
            contact : '+261 34 56 456 89',
            nif : '123 456 789 0',
            stat : '1234 5678 7 12345'
        }

        $infos = {
            numero_bon : 'n-0',
            reference : 'ref-0',
            date_facture : new Date(),
            magasin : $(namespace + "#select-magasin option:selected").text(),
            operateur : $(namespace + '#user-id').attr('value-name')
        }

        generate_bon('sortie', $societe, $infos, '#table-liste-article-sortie');
    }

})