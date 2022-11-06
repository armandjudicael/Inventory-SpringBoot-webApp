$(function() {

    let namespace = "#modal-liste-article ";

    $lesArticles = [
        {
            code : "124",
            designation : "Fromage",
            unite : ['piece', 'boite de 8', 'boite de 20', 'carton'],
            stock : 0,
            prix : [1000, 2000, 3000, 0]
        },
        {
            code : "456",
            designation : "Lait Concentré",
            unite : ['Boite de 1Kg', 'Boite de 2Kg', 'Boite de 5Kg', 'carton'],
            stock : 0,
            prix : [100, 200, 300, 400]
        },
        {
            code : "789",
            designation : "Oeuf",
            unite : ['piece', 'paquet de 10', 'paquet de 50', 'carton'],
            stock : 0,
            prix : [10, 20, 30, 40]
        }
    ]

    // $.each($lesArticles, function (keyA, valueA) {
    //     $.each(valueA.unite, function (keyU, valueU) {
    //         $tdArray = [valueA.code + '-' + keyU, valueA.designation, valueA.unite[keyU], "0", "0", valueA.prix[keyU]]
    //         push_to_table_list( namespace + "#table-liste-article",valueA.code + "-" + keyU, $tdArray);
    //     })
    // })

})