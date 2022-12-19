$(function () {
    /*---------------
        MAIN JS
    -------------- */
    const data_selector = '[nbs]';
    const data_class = '.nbs';
    const separator = ' ';
    function numberSeparator(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, separator);
    }

    function toFixed(x) {
        if (Math.abs(x) < 1.0){
            let e = parseInt(x.toString().split('e-')[1]);
            if (e){
                x *= Math.pow(10, e - 1);
                x = '0.' + (new Array(e)).join('0') + x.toString().substring(2);
            }
        } else {
            let e = parseInt(x.toString().split('+')[1]);
            if (e > 20){
                e -= 20;
                x /= Math.pow(10, e);
                x += (new Array(e + 1)).join('0');
            }
        }
        return x;
    }

    function setSeparateAllNumber() {
        $(data_selector).each(function(key,value) {
            $(this).text(toFixed(Number($(this).text())))
            $(this).text(numberSeparator($(this).text().replace('.',',')))
        })
        $(data_class).each(function(key,value) {
            $(this).text(toFixed(Number($(this).text())))
            $(this).text(numberSeparator($(this).text().replace('.',',')))
        })
    }



    setSeparateAllNumber();

    // modal clear on hide

    $('.modal-temporaire').on('hidden.bs.modal', function () {
        $(this).remove();
    });

    // conversion to excel

    $('.table-special-form').parent().addClass('table-special-form-parent');

    if ($('#navigation-ul .side-nav-link.active').text() != '')
        $('title').text('IOL - ' + $('#navigation-ul .side-nav-link.active').text())
    else
        $('title').text('IOL - ' + $('.no-title').attr('title'))

    // prevent default for all form submit

    $('form').on('submit', function (e) {
        e.preventDefault();
    })

    // (function () {
    //     'use strict'
    //     const forms = document.querySelectorAll('.was-validated')
    //     Array.from(forms)
    //         .forEach(function (form) {
    //             form.addEventListener('submit', function (event) {
    //                 if (!form.checkValidity()) {
    //                     event.preventDefault()
    //                     event.stopPropagation()
    //                 }
    //
    //                 form.classList.add('was-validated')
    //             }, false)
    //         })
    // })()
})