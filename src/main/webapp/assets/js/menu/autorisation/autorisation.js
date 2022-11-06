$(function() {
    // autorisation
    let namespace = "#topbar ";
   /*
   ⌛⌛⌛ chargement autorisation après Login
    */
    const init_autorisation = ()=>{
        let fonction_id = $(namespace+"#fonction-id").text();
        let url ="http://localhost:8080/api/v1/fonctions/"+fonction_id;
        execute_ajax_request("get",url,null,(data)=>{
            let autorisationMap = data.autorisationMap;
            let nomFonction = data.nomFonction;
            if (nomFonction === "admin"){
                $(".marge-beneficiaire").removeClass("d-none");
            }else $(".marge-beneficiaire").addClass("d-none");

            $.each(autorisationMap,function (key,value){
                let name = key.split(",")[1].split("=")[1].replace(')',"");
                if (value === 1) $('.' + name).show();
                else $('.' + name).hide();
            });

        });
    }
    init_autorisation();
})