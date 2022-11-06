

    $namespace_ticket_vente = "#impression-ticket-caisse ";
    $namespace_facture_vente = "#impression-facture-vente ";
    $namespace_facture_avoir = "#impression-facture-avoir ";
    $namespace_ticket_avoir = "#impression-ticket-avoir ";
    $namespace_ticket_acceptation = "#impression-ticket-acceptation ";
    $namespace_facture_acceptation = "#impression-facture-acceptation ";
    $namespace_bon = "#impression-bon ";

    function generate_facture_A5($societe, $infos, $table, $isAvoir) {
        let space = $namespace_facture_vente;

        /*
        vider la table
         */

        $(space + '#table-liste-ventes tbody tr').remove()

        /*
       add societe
        */

        $(space + '.label-nom-societe').text($societe.nom);
        $(space + '.label-slogan').text($societe.slogan);
        $(space + '.label-adresse').text($societe.adresse);
        $(space + '.label-ville').text($societe.ville);
        $(space + '.label-contact').text($societe.contact);
        $(space + '.label-nif').text($societe.nif);
        $(space + '.label-stat').text($societe.stat);

        /*
        add information
         */

        $(space + '.no-facture').toggle($isAvoir != undefined);
        $(space + '.no-avoir').toggle($isAvoir == undefined);

        $(space + '.label-numero-facture').text($infos.numero_facture);
        $(space + '.label-date').text($infos.date_facture);
        $(space + '.label-magasin').text($infos.magasin_vente);
        $(space + '.label-nom-client').text($infos.nom_client);
        $(space + '.label-nom-operateur').text($infos.operateur);

        $somme = 0;

        $($table + ' tbody tr').each(function (index, tr) {
            // order : quantite, unite, designation, PU, montant

            if ($isAvoir != undefined) if (!$(tr).find('.avoir-checkbox').is(':checked')) return;

            $array = [$(tr).find('.pr-quantite').text(), $(tr).find('.pr-unite').text(), $(tr).find('.pr-designation').text(), $(tr).find('.pr-prix-unitaire').text(), $(tr).find('.pr-montant').text()]
            $array_class = ['nbs','','','nbs','nbs']
            push_to_table_list(space + '#table-liste-ventes', '', $array, $array_class)
            $somme += parseFloat($(tr).find('.pr-montant').text());

        })

        $(space + '.label-subtotal-vente').text($somme + 'Ar');
        $(space + '.label-remise-vente').text($infos.remise + 'Ar');
        $(space + '.label-remise-partiel-vente').text($infos.remise_partiel + 'Ar');
        $(space + '.label-total-vente').text(($somme - $infos.remise) + 'Ar');
        $(space + '.label-somme-en-lettre').text(NumberToLetter($somme - $infos.remise) + ' ariary');

        // print
        $(space).printThis()
    }

    function generate_ticket_caisse($societe, $infos, $table, $isAvoir) {
        let space = $namespace_ticket_vente;

        /*
        vider la table
         */

        $(space + '#table-liste-ventes tbody tr').remove()

        $(space + '.no-facture').toggle($isAvoir != undefined);
        $(space + '.no-avoir').toggle($isAvoir == undefined);

        /*
       add societe
        */

        $(space + '.label-nom-societe').text($societe.nom);
        $(space + '.label-adresse').text($societe.adresse);
        $(space + '.label-ville').text($societe.ville);
        $(space + '.label-contact').text($societe.contact);
        /*
        add information
         */

        $(space + '.label-numero-facture').text($infos.numero_facture);
        $(space + '.label-date').text($infos.date_facture);
        $(space + '.label-magasin').text($infos.magasin_vente);
        $(space + '.label-nom-client').text($infos.nom_client);
        $(space + '.label-nom-operateur').text($infos.operateur);

        $somme = 0;

        $($table + ' tbody tr').each(function (index, tr) {
            // order : quantite, unite, designation, PU, montant

            if ($isAvoir != undefined) if (!$(tr).find('.avoir-checkbox').is(':checked')) return;

            $array = [$(tr).find('.pr-quantite').text(), $(tr).find('.pr-unite').text(), $(tr).find('.pr-designation').text(), $(tr).find('.pr-prix-unitaire').text(), $(tr).find('.pr-montant').text()]
            $array_class = ['nbs','','','nbs','nbs']
            push_to_table_list(space + '#table-liste-ventes', '', $array, $array_class)
            $somme += parseFloat($(tr).find('.pr-montant').text());

        })

        $(space + '.label-subtotal-vente').text($somme + 'Ar');
        $(space + '.label-remise-vente').text($infos.remise + 'Ar');
        $(space + '.label-remise-partiel-vente').text($infos.remise_partiel + 'Ar');
        $(space + '.label-total-vente').text(($somme - $infos.remise) + 'Ar');
        $(space + '.label-somme-en-lettre').text(NumberToLetter($somme - $infos.remise) + ' ariary');

        // print
        $(space).printThis()
    }

    function generate_facture_acceptation($titre, $societe, $infos) {
        let space = $namespace_facture_acceptation;

        $(space + '.label-title-acceptation').text($titre);

        $(space + '.label-nom-societe').text($societe.nom);
        $(space + '.label-ville').text($societe.ville);
        $(space + '.label-adresse').text($societe.adresse);
        $(space + '.label-contact').text($societe.contact);
        $(space + '.label-slogan').text($societe.slogan);

        $(space + '.label-numero-facture').text($infos.numero_facture);
        $(space + '.label-date').text($infos.date_facture);
        $(space + '.label-magasin').text($infos.magasin);

        $(space + '.label-montant-facture').text($infos.montant_facture);
        $(space + '.label-montant-acceptation').text($infos.montant_payer);
        $(space + '.label-montant-restant').text($infos.montant_facture - $infos.montant_payer);

        $(space + '.label-date-echeance').text($infos.date_echeance);

        $(space + '.for-credit').toggle($infos.titre == 'CREDIT');

        $(space).printThis();
    }

    function generate_ticket_acceptation($titre, $societe, $infos) {
        let space = $namespace_ticket_acceptation;

        $(space + '.label-title-acceptation').text($titre);

        $(space + '.label-nom-societe').text($societe.nom);
        $(space + '.label-ville').text($societe.ville);
        $(space + '.label-adresse').text($societe.adresse);
        $(space + '.label-contact').text($societe.contact);
        $(space + '.label-nom-client').text($infos.nom_client);

        $(space + '.label-numero-facture').text($infos.numero_facture);
        $(space + '.label-date').text($infos.date_facture);
        $(space + '.label-magasin').text($infos.magasin);

        $(space + '.label-montant-facture').text($infos.montant_facture);
        $(space + '.label-montant-acceptation').text($infos.montant_payer);
        $(space + '.label-montant-restant').text($infos.montant_facture - $infos.montant_payer);

        $(space + '.label-description').text($infos.description);

        $(space + '.label-date-echeance').text($infos.date_echeance);

        $(space + '.for-credit').toggle($infos.titre == 'CREDIT');

        $(space).printThis();
    }

    function generate_bon($type, $societe, $infos, $table) {
        let space = $namespace_bon;

        $(space + '#liste-article-bon tbody tr').remove();

        $(space + '.no-' + $type).remove();

        $(space + '.title').text($type);
        $(space + '.label-numero-bon').text($infos.numero_bon);
        $(space + '.label-magasin').text($infos.magasin);
        $(space + '.label-utilisateur').text($infos.operateur);
        $(space + '.label-reference').text($infos.reference);
        $(space + '.label-nom-societe').text($societe.nom);

        switch ($type) {
            case 'entree' :
                $somme = 0;
                $($table + ' tbody tr').each(function (index, tr) {
                    // order : code , designation, unite, quantite, PU, montant, description
                    $array = [$(tr).find('.pr-designation').text(), $(tr).find('.pr-unite').text(), $(tr).find('.pr-quantite').text(), $(tr).find('.pr-prix-unitaire').text(), $(tr).find('.pr-montant').text()]
                    $array_class = ['','','','nbs','nbs','nbs']
                    push_to_table_list(space + '#liste-article-bon', '', $array, $array_class)
                    $somme += parseFloat($(tr).find('.pr-montant').text());
                })
                $(space + '.label-somme-en-lettre').text(NumberToLetter($somme) + ' ariary');
                push_to_table_list(space + '#liste-article-bon', '', ['','','','TOTAL', $somme])
                ;break
            case 'sortie' :
                $($table + ' tbody tr').each(function (index, tr) {
                    // order : code , designation, unite, quantite, PU, montant, description
                    $array = [$(tr).find('.pr-designation').text(), $(tr).find('.pr-unite').text(), $(tr).find('.pr-quantite').text(),$(tr).find('.pr-description').text()]
                    $array_class = ['','','nbs','']
                    push_to_table_list(space + '#liste-article-bon', '', $array, $array_class)
                })
                ;break
            case 'transfert' :
                $(space + '.label-magasin-transfert').text($infos.magasin_destination);
                $($table + ' tbody tr').each(function (index, tr) {
                    // order : code , designation, unite, quantite, PU, montant, description
                    $array_class = ['','','nbs','']
                    $array = [$(tr).find('.pr-designation').text(), $(tr).find('.pr-unite').text(), $(tr).find('.pr-quantite').text(),$(tr).find('.pr-description').text()]
                    push_to_table_list(space + '#liste-article-bon', '', $array, $array_class)
                })
                ;break
        }

        $(space).printThis()
    }
