$(function () {

    /*

    MENU CHOIX MAGASIN

     */

    let namespace = "#choix-magasin ";

    /*
    validation
     */

    $(namespace + 'form').validate({
        rules: {
            choixMagasin: {required: true}
        },
        messages: {
            choixMagasin: {required: 'Magasin requis'}
        }
    })

    function validation_choix_magasin() {
        $(namespace + 'form').validate();

        return $(namespace + 'form').valid();
    }

    $(namespace + ".btn-choix-magasin").on('click', function () {

        if (validation_choix_magasin()) {
            $magasin = $(namespace + '#choixMagasin option:selected');

            createToast('bg-success', 'uil-check-file', 'Magasin changer', 'Votre magasin a ete change a ' + $magasin.text() + '!!')
            $(namespace).modal('hide')
        }

    })
})