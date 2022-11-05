$(function() {

    /*

    GERER UTILISATEUR JS

     */

    let namespace = "#gerer-utilisateur ";

    // default states

    $(namespace + '.btn-enregistrer-infos-general').hide();
    $(namespace + '.btn-enregistrer-infos-securite').hide();

    /*

    INFOS GENERAL

     */

    // mask et validation

    $(namespace + '#infos-general form .input-contact').mask('+261 99 99 999 99');

    $(namespace + '#infos-general form').validate({
        rules : {
            iNom : {required : true},
            iAdresse : {required : true},
            iContact : {required : true}
        },
        messages : {
            iNom : {required : 'Le nom de l\'utilisateur est requis'},
            iAdresse : {required : 'L\'adresse de l\'utilisateur est requis'},
            iContact : {required : 'Le contact est requis'}
        }
    })

    function validation_edition_information_general() {
        $(namespace + '#infos-general form').validate();

        return $(namespace + '#infos-general form').valid();
    }

    // editer infos generale

    $(namespace + '.btn-editer-infos-general').on('click', function() {
        $(namespace + '.btn-enregistrer-infos-general').toggle();
        $(namespace + '#infos-general input').attr('disabled',
            $(namespace + '#infos-general input').prop('disabled') == true ? false : true)
    })

    $(namespace + '.btn-enregistrer-infos-general').on('click', function() {
        if (validation_edition_information_general()) {
            // enregistrement

            $(namespace + '#infos-general input').attr('disabled', true);
            $(this).hide()
            createToast('bg-success', 'uil-check-circle', 'Modification enregistré','Modification de l\'information general utilisateur enregistré avec succès!')
        }
    })

    /*

    INFOS SECURITE

     */

    // mask et validation


    $(namespace + '#infos-securite form').validate({
        rules : {
            iOldPassword : {required : true},
            iNewPassword : {required : true, minlength: 4},
            iRetypeNewPassword : {required : true, equalTo : ".input-new-password"}
        },
        messages : {
            iOldPassword : {required : 'Ancien mot de passe est requis'},
            iNewPassword : {required : 'Nouveau mot de passe requis', minlength : 'Minimum 04 caracteres'},
            iRetypeNewPassword : {required : 'Confirmation de mot de passe requis', equalTo: 'Mot de passe non conforme'}
        }
    })

    function validation_edition_information_securite() {
        $(namespace + '#infos-securite form').validate();

        return $(namespace + '#infos-securite form').valid();
    }

    // editer infos securite

    $(namespace + '.btn-editer-infos-securite').on('click', function() {
        $(namespace + '.btn-enregistrer-infos-securite').toggle();
        $(namespace + '#infos-securite input').attr('disabled',
            $(namespace + '#infos-securite input').prop('disabled') == true ? false : true)
    })

    $(namespace + '.btn-enregistrer-infos-securite').on('click', function() {
        if (validation_edition_information_securite()) {
            // enregistrement

            $(namespace + '#infos-securite input').attr('disabled', true);
            $(this).hide()
            createToast('bg-success', 'uil-check-circle', 'Modification enregistré','Modification de l\'information de securite utilisateur enregistré avec succès!')
        }
    })

})