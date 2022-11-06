$(function() {
    /*

    MENU LIVRAISON

     */

    let namespace="#menu-livraison ";


    exportToExcel(namespace + '.btn-export-to-excel','livraisons', namespace + '#table-livraison')

    let IN = "ENTREE", OUT = "SORTIE";

    // données prédéfini


    $lesLivraisons = [
        {
            reference : "ref-123",
            designation : "bonbon",
            operation : IN,
            quantite : 10,
            dateLivraison : new Date().toLocaleDateString()
        },
        {
            reference : "ref-456",
            designation : "biscuit",
            operation : IN,
            quantite : 10,
            dateLivraison : new Date().toLocaleDateString()
        },
        {
            reference : "ref-789",
            designation : "fromage",
            operation : OUT,
            quantite : 10,
            dateLivraison : new Date().toLocaleDateString()
        }
    ]

    /*
     affect to table data
     */

    $.each($lesLivraisons, function(key,value) {
        $livraison = [value.reference, value.designation, value.operation, value.operation === IN ? value.quantite : '-',value.operation === OUT ? value.quantite : '-', value.dateLivraison]
        push_to_table_list(namespace + "#table-livraison", "", $livraison)
    })

})