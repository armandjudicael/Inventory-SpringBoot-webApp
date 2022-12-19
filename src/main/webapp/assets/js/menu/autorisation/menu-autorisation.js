$(function() {
    /*
    MENU AUTORISATION JS
     */
    $fonction_id = 0;
    let namespace = "#menu-autorisation ";
    $(namespace + '#nouvelle-fonction-autorisation form').validate({
        rules : {
            libelle : {required : true}
        },
        messages : {
            libelle : {required : 'Libelle de la fonction requis'}
        }
        }
    );

    function validation_nouvelle_fonction() {
        $(namespace + '#nouvelle-fonction-autorisation form').validate();
        return $(namespace + '#nouvelle-fonction-autorisation form').valid();
    }

    $(namespace + '#nouvelle-fonction-autorisation #btn-save-function').on('click', function() {
        if (validation_nouvelle_fonction()) {
            // operation enregistremenet
            $(namespace + '#nouvelle-fonction-autorisation').modal('hide')
        }
    });

    $(document).on("click",namespace+"#fonction-tab tbody tr",function (){
        $fonction_id = $(this).attr("id");
        let url ="http://localhost:8080/api/v1/fonctions/"+$fonction_id;
        execute_ajax_request("get",url,null,(data)=>{
            let autorisationMap = data.autorisationMap;
            $(namespace+"#auto-tab tbody tr").remove();
            $.each(autorisationMap,function (key,value){
                let feature_id = key.split(",")[0].split("=")[1];
                let name = key.split(",")[1].split("=")[1].replace(')',"");
                let menu = `
               <tr fonction-id = "`+$fonction_id+`" feature-id="`+feature_id+`" class="p-0">
                <td class="p-1">`+name.replace("crud-","")+`</td>
                `+init_badge(value)+`
                <td class="p-1 d-flex justify-content-end">
                  `+init_button(value)+`
                </td>
              </tr>`;
                if(name.includes("crud")){
                    $(namespace+"#creation-tab tbody").append(menu);
                }else if (name.includes("option")){
                    $(namespace+"#option-tab tbody").append(menu);
                }else if (name.includes("menu")) $(namespace+"#auto-tab tbody").append(menu);
            })
        });
    })

    const init_badge = (value)=>{
        return value === 1 ? '<td>Status : <span class="badge badge-success-lighten">Activé</span></td>': '<td> Status : <span class="badge badge-danger-lighten">Désactivé</span></td>';
    }

    $(document).on("click",namespace+".edit-autorisation",function (){
       let feature_id = $(this).closest("tr").attr("feature-id");
       $fonction_id= $(this).closest("tr").attr("fonction-id");
       let text = $(this).text();
       let url = "http://localhost:8080/api/v1/fonctionnalities/autorization/"+feature_id+"/"+$fonction_id+"/"+(text==="Desactiver" ? 0 : 1);
       update_btn_status(this,(text==="Desactiver" ? 1 : 0));
       execute_ajax_request("put",url,null,function (data) {
           createToast('bg-success', 'uil-file-check-alt', 'Fait', ' modification enregistr&eacute; avec succ&egrave;s!')
       });
    });

    const update_btn_status = (btn,value)=>{
        if (value===1){
            $(btn).text("Activer");
            $(btn).removeClass("btn-danger");
            $(btn).addClass("btn-success")
        }else {
            $(btn).text("Desactiver");
            $(btn).removeClass("btn-success");
            $(btn).addClass("btn-danger");
        }
    }

    const  init_button = (value)=>{
        return value===1 ? '<button class="btn btn-danger edit-autorisation">Desactiver</button>':'<button class="btn btn-success edit-autorisation">Activer</button>'
    }

    /* WELCOME PAGE */

  const MENU_TAB = ["dashboard",
                    "menu article",
                    "menu vente",
                    "menu detail vente",
                    "menu magasin",
                    "menu stock",
                    "menu facture",
                    "menu prix",
                    "menu embarquement",
                    "menu peremption",
                    "menu client",
                    "menu fournisseur",
                    "menu operations",
                    "menu entreé",
                    "menu sorties",
                    "menu transfert",
                    "menu livraison",
                    "menu caisse",
                    "menu utilisateur",
                    "menu-choix-magasin",
                    "menu-autorisation",
                    "menu-transport"];

    function init_page() {
        for (let i = 0; i < MENU_TAB.length; i++) {
            let option =`
                <option value="` + i + `" >` + MENU_TAB[i] + `</option> 
                `;
            $(namespace + "#welcome-page-select").append(option);
        }
    }

    $(document).on("shown.bs.modal",namespace+"#welcome-page-modal",()=>{
        let length = $(namespace+"#welcome-page-select option").length;
        if (length===0) init_page();
    });

    $(document).on("click",namespace+"#save-welcome-page-btn",function (){
        let value = $(namespace+"#welcome-page-select").val();
        if ($fonction_id!==0){
            let url = "http://localhost:8080/api/v1/fonctions/"+$fonction_id+"/welcome-page/"+value;
            execute_ajax_request("put",url,null,(data)=>{
                createToast('bg-success', 'uil-file-check-alt', 'Enregistrement Page d\'acceuil',"Page d'acceuil enregistr&eacute; avec succ&egrave;s!")
            });
        }
        $(namespace+"#welcome-page-modal").modal("hide");
    });

})