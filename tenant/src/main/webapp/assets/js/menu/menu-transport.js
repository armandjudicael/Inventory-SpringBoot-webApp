// $(function (){
//
//     let namespace = "#menu-transport ";
//
//     _count_table_content(namespace + '#table-materiel-transport', namespace + '.text-count-utilisateurs');
//
//     exportToExcel(namespace + '.btn-export-to-excel', 'factures-', namespace + '#table-materiel-transport');
//
//     /* ENREGISTREMENT DU RESPONSABLE MATERIEL TRANSPORT */
//     $(document).on("click",namespace+'#btn-enregistrer-responsable',function(){
//         let numCin = $(namespace+"#numCIN").val();
//         let nom = $(namespace+"#nom").val();
//         let adresse = $(namespace+"#adresse").val();
//         let contact = $(namespace+"#contact").val();
//         $ps = {
//             nom: nom,
//             adresse: adresse,
//             numTel: contact,
//             cin : numCin
//         };
//         let url = "http://localhost:8080/api/v1/persons/fonction/chauffeur";
//         execute_ajax_request("post",url,$ps,(ps)=>{
//             let option = `<option value="`+ps.id+`" >`+ps.nom+`</option>`;
//             $(namespace+"#select-responsable").append(option);
//             $(namespace+"#select-responsable").val(ps.id).change();
//             $(namespace+"#nouveau-responsable-mt-modal").modal("hide");
//         });
//
//     });
//
//     /* Click button enregistrer materiel de transport */
//
//     $(namespace + "#btn-enregistrer-materiel-de-transport").on('click', function () {
//         $reference = $(namespace + '#reference').val();
//         $typeMateriel = $(namespace + '#type-materiel option:selected').val();
//         $responsableId = $(namespace+"#select-responsable option:selected").val();
//         $location = $(namespace + "#check-statut").is(':checked');
//         $mt = {};
//         $mt.reference = $reference;
//         $mt.typeMateriel = $typeMateriel;
//         $mt.responsable = {id : $responsableId};
//         $mt.isLocation = $location;
//         enregistrerMaterielTransport($mt);
//     })
//
//     /* ACTUALISER LISTE */
//     $(document).on("click",namespace+"#refresh-btn",function () {
//         let url = "http://localhost:8080/api/v1/materieltransport";
//         execute_ajax_request("get",url,null,(data)=> {
//             clear_table("#table-materiel-transport");
//             append_item(data)
//         })
//     })
//
//     const create_badge = (isLocation)=>{
//         return isLocation===false ? '<span class="badge badge-success-lighten">Propriété</span>' :'<span class="badge badge-primary-lighten">Location</span>';
//     }
//
//     const append_item = (data)=>{
//         data.forEach(mt=>{
//             let tr = `
//              <tr id="`+mt.id+`">
//               <td>`+mt.reference+`</td>
//               <td>`+mt.typeMateriel+`</td>
//               <td>`+mt.responsable.nom+`</td>
//               <td>`+create_badge(mt.isLocation)+`</td>
//               <td class="td-action justify-content-center crud-embarquement">
//                 <div class="d-flex" >
//                   <a data-bs-toggle="modal" data-bs-target="#nouveau-materiel-de-transport"  class="btn-sm btn-info edit-materiel"><i class="uil-pen"></i></a>
//                   <a class="btn-sm btn-danger delete-materiel "><i class="uil-trash-alt"></i></a>
//                 </div>
//               </td>
//             </tr>
//             `;
//             $(namespace+"#table-materiel-transport tbody").append(tr);
//             _count_table_content(namespace + '#table-materiel-transport', namespace + '.text-count-utilisateurs');
//         })
//     }
//
//     $NEW_MT = true;
//
//     // Enregistrement materiel de transport
//     function enregistrerMaterielTransport($object){
//         let materieltransportURL = 'http://localhost:8080/api/v1/materieltransport';
//         let methodType = $NEW_MT ? "POST" : "PUT";
//         let userRessourceUrl = $NEW_MT ? materieltransportURL : materieltransportURL + '/' +  $id;
//         execute_ajax_request(methodType,userRessourceUrl,$object, (data) => {
//             let title  = $NEW_MT ? ' Enregistrement materiel de transport ' : 'Modification materiel de transport';
//             let content = $NEW_MT ? 'Materiel de transport enregistr&eacute; avec succ&egrave;s!' : 'Modification enregistré avec succès ';
//             if ($NEW_MT) append_item([data]);
//             createToast('bg-success', 'uil-check-sign', title, content)
//             $NEW_MT = true;
//             //reset the input
//             $(namespace + '#nouveau-materiel-de-transport input').val('');
//         });
//     };
//
//     /* SUPPRIMER MATERIEL DE TRANSPORT */
//     $(document).on('click',namespace+".delete-materiel",function(){
//
//         let tr = $(this).closest("tr");
//         let id = $(this).closest("tr").attr('id');
//         let url = 'http://localhost:8080/api/v1/materieltransport/'+id;
//         let reference = $(tr).children().eq(0).text();
//
//         create_confirm_dialog(' supprimer materiel de transport ', " Voulez-vous supprimer "+reference, "supprimer-materiel", "Oui ,supprimer", "btn-danger").on('click', function (){
//             execute_ajax_request("delete",url,null,()=>{
//                 tr.remove();
//                 hideAndRemove('#supprimer-materiel');
//                 createToast('bg-danger', 'uil-trash-alt', 'Suppression materiel de transport', " Materiel supprim&eacute;r avec succ&egrave;s!");
//             });
//         })
//
//     })
//
//     /* EDITER MATERIEL DE TRANSPORT */
//     $(document).on('click',namespace+".edit-materiel",function () {
//         $NEW_MT = false;
//         let tr = $(this).closest("tr");
//         $id = $(this).closest("tr").attr('id');
//         $ref = $(tr).children().eq(0).text();
//         $(namespace + '#reference').val($ref);
//     });
//
// })
$(function (){

    let namespace = "#menu-transport ";

    _count_table_content(namespace + '#table-materiel-transport', namespace + '.text-count-utilisateurs');

    exportToExcel(namespace + '.btn-export-to-excel', 'factures-', namespace + '#table-materiel-transport');

    /* ENREGISTREMENT DU RESPONSABLE MATERIEL TRANSPORT */
    $(document).on("click",namespace+'#btn-enregistrer-responsable',function(){
        let numCin = $(namespace+"#numCIN").val();
        let nom = $(namespace+"#nom").val();
        let adresse = $(namespace+"#adresse").val();
        let contact = $(namespace+"#contact").val();
        $ps = {
            nom: nom,
            adresse: adresse,
            numTel: contact,
            cin : numCin
        };
        let url = "http://localhost:8080/api/v1/persons/fonction/chauffeur";
        execute_ajax_request("post",url,$ps,(ps)=>{
            let option = `<option value="`+ps.id+`" >`+ps.nom+`</option>`;
            $(namespace+"#select-responsable").append(option);
            $(namespace+"#select-responsable").val(ps.id).change();
            $(namespace+"#nouveau-responsable-mt-modal").modal("hide");
        });

    });

    /* Click button enregistrer materiel de transport */

    $(namespace + "#btn-enregistrer-materiel-de-transport").on('click', function () {
        $reference = $(namespace + '#reference').val();
        $typeMateriel = $(namespace + '#type-materiel option:selected').val();
        $responsableId = $(namespace+"#select-responsable option:selected").val();
        $location = $(namespace + "#check-statut").is(':checked');
        $mt = {};
        $mt.reference = $reference;
        $mt.typeMateriel = $typeMateriel;
        $mt.responsable = {id : $responsableId};
        $mt.isLocation = $location;
        enregistrerMaterielTransport($mt);
    })

    /* ACTUALISER LISTE */
    $(document).on("click",namespace+"#refresh-btn",function () {
        let url = "http://localhost:8080/api/v1/materieltransport";
        execute_ajax_request("get",url,null,(data)=> {
            clear_table("#table-materiel-transport");
            append_item(data)
        })
    })

    const create_badge = (isLocation)=>{
        return isLocation===false ? '<span class="badge badge-success-lighten">Propriété</span>' :'<span class="badge badge-primary-lighten">Location</span>';
    }

    const append_item = (data)=>{
        data.forEach(mt=>{
            let tr = `
             <tr id="`+mt.id+`">
              <td>`+mt.reference+`</td>
              <td>`+mt.typeMateriel+`</td>
              <td>`+mt.responsable.nom+`</td>
              <td>`+create_badge(mt.isLocation)+`</td>
              <td class="td-action justify-content-center crud-embarquement">
                <div class="d-flex" >
                  <a data-bs-toggle="modal" data-bs-target="#nouveau-materiel-de-transport"  class="btn-sm btn-info edit-materiel"><i class="uil-pen"></i></a>
                  <a class="btn-sm btn-danger delete-materiel "><i class="uil-trash-alt"></i></a>
                </div>
              </td>
            </tr>
            `;
            $(namespace+"#table-materiel-transport tbody").append(tr);
            _count_table_content(namespace + '#table-materiel-transport', namespace + '.text-count-utilisateurs');
        })
    }

    $NEW_MT = true;

    // Enregistrement materiel de transport
    function enregistrerMaterielTransport($object){
        let materieltransportURL = 'http://localhost:8080/api/v1/materieltransport';
        let methodType = $NEW_MT ? "POST" : "PUT";
        let userRessourceUrl = $NEW_MT ? materieltransportURL : materieltransportURL + '/' +  $id;
        execute_ajax_request(methodType,userRessourceUrl,$object, (data) => {
            let title  = $NEW_MT ? ' Enregistrement materiel de transport ' : 'Modification materiel de transport';
            let content = $NEW_MT ? 'Materiel de transport enregistr&eacute; avec succ&egrave;s!' : 'Modification enregistré avec succès ';
            if ($NEW_MT) append_item([data]);
            createToast('bg-success', 'uil-check-sign', title, content)
            $NEW_MT = true;
            //reset the input
            $(namespace + '#nouveau-materiel-de-transport input').val('');
        });
    };

    /* SUPPRIMER MATERIEL DE TRANSPORT */
    $(document).on('click',namespace+".delete-materiel",function(){

        let tr = $(this).closest("tr");
        let id = $(this).closest("tr").attr('id');
        let url = 'http://localhost:8080/api/v1/materieltransport/'+id;
        let reference = $(tr).children().eq(0).text();

        create_confirm_dialog(' supprimer materiel de transport ', " Voulez-vous supprimer "+reference, "supprimer-materiel", "Oui ,supprimer", "btn-danger").on('click', function (){
            execute_ajax_request("delete",url,null,()=>{
                tr.remove();
                hideAndRemove('#supprimer-materiel');
                createToast('bg-danger', 'uil-trash-alt', 'Suppression materiel de transport', " Materiel supprim&eacute;r avec succ&egrave;s!");
            });
        })

    })

    /* EDITER MATERIEL DE TRANSPORT */
    $(document).on('click',namespace+".edit-materiel",function () {
        $NEW_MT = false;
        let tr = $(this).closest("tr");
        $id = $(this).closest("tr").attr('id');
        $ref = $(tr).children().eq(0).text();
        $(namespace + '#reference').val($ref);
    });

})