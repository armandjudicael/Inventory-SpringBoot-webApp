$(function() {

    /*-----------------------

           MENU PAIEMENT

     ------------------------*/

    let namespace="#menu-paiement ";


    exportToExcel(namespace + '.btn-export-to-excel','paiements', namespace + '#table-paiement')

    let IN = "ENTREE", OUT = "SORTIE";

    // données prédéfini

    $Rakoto = [
        {
            idClient : '123',
            nomClient : 'Rakoto'
        }
    ]

    $lesPaiements = [
        {
            reference : "ref-123",
            client : $Rakoto,
            operation : IN,
            modeDePaiement : 'Especes',
            montant : 500,
            datePaiement : new Date().toLocaleDateString(),
            description : 'aucun description disponible'
        },
        {
            reference : "ref-456",
            client : $Rakoto,
            operation : IN,
            modeDePaiement : 'Especes',
            montant : 1000,
            datePaiement : new Date().toLocaleDateString(),
            description : 'aucun description disponible'
        },
        {
            reference : "ref-789",
            client : $Rakoto,
            operation : OUT,
            modeDePaiement : 'Especes',
            montant : 1500,
            datePaiement : new Date().toLocaleDateString(),
            description : 'aucun description disponible'
        }
    ]

    /*
     affect to table data
     */

    $.each($lesPaiements, function(key,value) {

        $paiement = [value.reference, value.client[0].nomClient, value.operation, value.montant,value.modeDePaiement, value.datePaiement, value.description]
        push_to_table_list(namespace + "#table-paiement", "", $paiement)

    })

})