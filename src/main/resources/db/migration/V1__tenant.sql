-- we don't know how to generate root <with-no-name> (class Root) :(
create table categorie
(
    id bigserial not null
        constraint categorie_pkey
            primary key,
    libelle text
);
alter table categorie owner to postgres;
create table article
(
    article_id bigserial not null
        constraint article_pkey
            primary key,
    designation text,
    image oid,
    is_perishable boolean,
    status varchar(15),
    categorie_id bigint not null
        constraint article_categorie_key_constraint
            references categorie
);
alter table article owner to postgres;
create table fonctionnalite
(
    id bigserial not null
        constraint fonctionnalite_pkey
            primary key,
    nom text
);
alter table fonctionnalite owner to postgres;
create table materiel_transport(
    id bigserial not null
        constraint materiel_transport_pkey
            primary key,
    reference text,
    type_materiel text,
    is_location boolean
);
alter table materiel_transport owner to postgres;
create table personne
(
    id bigserial not null
        constraint personne_pkey
            primary key,
    adresse text,
    email text,
    nom text,
    num_tel text,
    photo oid
);

alter table personne owner to postgres;

create table filiale
(
    active boolean not null,
    id bigint not null
        constraint filiale_pkey
            primary key
        constraint fkf9jvt24bbipxisi457rl2hpl7
            references personne
);

alter table filiale owner to postgres;

create table client_fournisseur
(
    type integer not null,
    id bigint not null
        constraint client_fournisseur_pkey
            primary key
        constraint fkfofmm3l4qkmi4k9sl1nt2h3x0
            references personne,
    filiale_id bigint not null
        constraint fk8gfv4wed4y8gg2y1hg5ot6s83
            references filiale
);
alter table client_fournisseur owner to postgres;
create table fonction
(
    id bigserial not null
        constraint fonction_pkey
            primary key,
    default_page integer,
    nom_fonction text
);
alter table fonction owner to postgres;
create table fonction_autorisation
(
    fonction_id bigint not null
        constraint fkq7n56ttiak9qet37qbka9tkmh
            references fonction,
    status bigint,
    autorisation_map_key bigint not null
        constraint fkg488nhw14s1aldc3bydktadc2
            references fonctionnalite,
    constraint fonction_autorisation_pkey
        primary key (fonction_id, autorisation_map_key)
);
alter table fonction_autorisation owner to postgres;
create table inventory_alert
(
    article_id bigint not null
        constraint fk_article_id
            references article
            on delete cascade,
    filiale_id bigint not null
        constraint fknscwajb2pg27h37thymgn90jt
            references filiale,
    quantite double precision,
    constraint inventory_alert_pkey
        primary key (article_id, filiale_id)
);
alter table inventory_alert owner to postgres;
create table magasin
(
    id_magasin bigserial not null
        constraint magasin_pkey
            primary key,
    adresse text,
    nom_magasin text,
    filiale_id bigint
        constraint magasin_filiale_key_constraint
            references filiale
);
alter table magasin owner to postgres;
create table personne_physique
(
    cin text,
    date_delivrance date,
    lieu_delivrance text,
    sexe varchar(255),
    situation_matrimoniale varchar(255),
    id bigint not null
        constraint personne_physique_pkey
            primary key
        constraint fkdntqhp735wc4j2lt9wd0es7gc
            references personne,
    fonction_id bigint
        constraint user_fonction_key_constraint
            references fonction
);
alter table personne_physique owner to postgres;
create table _user
(
    enabled boolean,
    password text,
    username text,
    id bigint not null
        constraint _user_pkey
            primary key
        constraint fk378jh9bywdkcmkkcx2pujjplp
            references personne_physique,
    filiale_id bigint
        constraint user_filiale_key_constraint
            references filiale
);

alter table _user owner to postgres;

create table info_filiale_caisse
(
    id bigserial not null
        constraint info_filiale_caisse_pkey
            primary key,
    date date,
    description varchar(255),
    mode_payement varchar(255),
    montant_operation double precision,
    operation_caisse varchar(255),
    reference text,
    filiale_id bigint
        constraint fk216rqgw3u6o30dcr79curuhf5
            references filiale,
    user_id bigint
        constraint fkt09390mitpvlwwkeeyv9h31d3
            references _user
);

alter table info_filiale_caisse owner to postgres;

create table info_caisse_magasin
(
    magasin_id_magasin bigint
        constraint fkmc4yvtklffai4ffs2drjtudcr
            references magasin,
    id bigint not null
        constraint info_caisse_magasin_pkey
            primary key
        constraint fkhei30itrdhm5xrgcavhpli0yg
            references info_filiale_caisse
);

alter table info_caisse_magasin owner to postgres;

create table personne_morale
(
    cif text,
    nif text,
    rcs text,
    stat text,
    id bigint not null
        constraint personne_morale_pkey
            primary key
        constraint fkd0argsea72cd00cwspg44xv7c
            references personne
);

alter table personne_morale owner to postgres;

create table transfert
(
    id bigserial not null
        constraint transfert_pkey
            primary key,
    chauffeur text,
    magasin_dest_id bigint
        constraint transfert_magasin_receveur_key_constraint
            references magasin,
    magasin_source_id bigint
        constraint transfert_magasin_origine_key_constraint
            references magasin
);

alter table transfert owner to postgres;

create table transfert_article
(
    article_id bigint not null
        constraint ta_article_key_constraint
            references article,
    transfert_id bigint not null
        constraint ta_transfert_key_constraint
            references transfert,
    date timestamp,
    quantite double precision,
    constraint transfert_article_pkey
        primary key (article_id, transfert_id)
);

alter table transfert_article owner to postgres;

create table trosa
(
    id bigserial not null
        constraint trosa_pkey
            primary key,
    date date,
    date_echeance date,
    description text,
    mode_payement integer,
    montant double precision,
    reference text,
    reste double precision,
    type_trosa integer,
    cf_id bigint
        constraint fk39x2taxfbjx67kctge329f4qi
            references client_fournisseur
);

alter table trosa owner to postgres;

create table unite
(
    id bigserial not null
        constraint unite_pkey
            primary key,
    code text,
    designation text
);

alter table unite owner to postgres;

create table article_unite
(
    id bigserial not null
        constraint article_unite_pkey
            primary key,
    barcode varchar(255),
    niveau integer not null,
    poids double precision not null,
    quantite_niveau double precision not null,
    article_id bigint
        constraint fk_article_id
            references article
            on delete cascade,
    unite_id bigint
        constraint fk_unite_id
            references unite
            on delete cascade
);

alter table article_unite owner to postgres;

create table info_article_magasin
(
    id bigserial not null
        constraint info_article_magasin_pkey
            primary key,
    date date,
    description text,
    montant_article double precision,
    quantite_ajout double precision,
    quantite_stock_apres_operation double precision,
    reference text,
    type_operation varchar(255),
    article_id bigint
        constraint fk_article_id
            references article
            on delete cascade,
    magasin_id bigint
        constraint fk_magasin_id
            references magasin
            on delete cascade,
    unite_id bigint
        constraint fk_unite_id
            references unite
            on delete cascade,
    user_id bigint
        constraint fkmq7t1rb4f0x9tvei5np310gv8
            references _user
);

alter table info_article_magasin owner to postgres;

create table approv
(
    id bigserial not null
        constraint approv_pkey
            primary key,
    date_peremption date,
    description text,
    montant_approv double precision,
    quantite_peremption double precision,
    info_article_magasin_id bigint
        constraint fkee9l9phtchq0t6ydgwvj94em7
            references info_article_magasin
);

alter table approv owner to postgres;

create table approvisionement_fournisseur(

    fournisseur_id bigint
        constraint fk7ek5k7lqe5ir888sy1dmvnhkr
            references client_fournisseur,
    id bigint not null
        constraint approvisionement_fournisseur_pkey
            primary key
        constraint fkm4wotbvl3uojnskj2e8cm7s2f
            references approv
);

alter table approvisionement_fournisseur owner to postgres;

create table prix_article_filiale(

    id bigserial not null
        constraint prix_article_filiale_pkey
            primary key,
    date_enregistrement timestamp,
    prix_vente double precision,
    article_id bigint
        constraint fk_article_id
            references article
            on delete cascade,
    filiale_id bigint
        constraint fk_filiale_id
            references filiale
            on delete cascade,
    unite_id bigint
        constraint fk_unite_id
            references unite
            on delete cascade,
    user_id bigint
        constraint fk_user_id
            references _user
);

alter table prix_article_filiale owner to postgres;

create table sortie
(
    id bigserial not null
        constraint sortie_pkey
            primary key,
    info_article_magasin_id bigint
        constraint fkc94a1h5i9rpp20hx5emeesscs
            references info_article_magasin
);

alter table sortie owner to postgres;

create table stock(

    article_id bigint not null
        constraint fk_stock_article_id
            references article
            on delete cascade,
    magasin_id bigint not null
        constraint fk_stock_magasin_id
            references magasin
            on delete cascade,
    unite_id bigint not null
        constraint fk_stock_unite_id
            references unite
            on delete cascade,
    count double precision,
    constraint stock_pkey
        primary key (article_id, magasin_id, unite_id)
);

alter table stock owner to postgres;

create table transfert_info
(
    transfert_id bigint not null
        constraint fk6wo1relp2s61u7xtrih7vc2k8
            references transfert,
    info_article_magasin_list_id bigint not null
        constraint uk_los6chfe359tfxa6bxjpa089h
            unique
        constraint fkcb76basile3u07xevpbq221g4
            references info_article_magasin
);

alter table transfert_info owner to postgres;

create table user_magasin
(
    user_id    bigint not null
        constraint fk_um_user_id
            references personne_physique,
    magasin_id bigint not null
        constraint fk_um_magasin_id
            references magasin
            on delete cascade
);


alter table user_magasin owner to postgres;

create table vente(

    id bigserial not null
        constraint vente_pkey
            primary key,
    date date,
    is_concerned_by_invoice_regulation boolean,
    is_payement_mode_changed boolean,
    montant_vente double precision,
    ref_vente varchar(255),
    remise double precision,
    reste double precision,
    client_id bigint
        constraint fk_client_id
            references client_fournisseur,
    filiale_id bigint not null
        constraint fkl1974he4065ewa9p14ygunqql
            references filiale,
    user_id bigint
        constraint fkmrrjkylypib7vm3w0g7yagjpx
            references _user,
    date_vente date,
    montant_avec_remise double precision
);

alter table vente owner to postgres;

create table avoir
(
    id bigserial not null
        constraint avoir_pkey
            primary key,
    date date,
    montant double precision,
    ref_avoir text,
    info_id bigint
        constraint fklxyeflsx4nsd24pu55b21983v
            references info_filiale_caisse,
    vente_id bigint
        constraint avoir_vente_key_constraint
            references vente
);

alter table avoir owner to postgres;

create table avoir_info_article_magasin
(
    avoir_id bigint not null
        constraint fknpq71662t998nxr6im4qumbel
            references avoir,
    info_article_magasin_id bigint not null
        constraint uk_7rx02dwwnpsbg8cebsm2l5iox
            unique
        constraint fkt3ywq6qas4o4r8f5t8lajtjje
            references info_article_magasin
);

alter table avoir_info_article_magasin owner to postgres;

create table info_vente
(
    article_id bigint not null
        constraint info_vente_article_key_constraint
            references article,
    vente_id bigint not null
        constraint info_vente_vente_key_constraint
            references vente,
    date_vente timestamp,
    prix_vente double precision,
    quantite double precision,
    reference varchar(255),
    constraint info_vente_pkey
        primary key (article_id, vente_id)
);

alter table info_vente owner to postgres;

create table livraison
(
    id bigserial not null
        constraint livraison_pkey
            primary key,
    numero_bon text,
    statut_voyage varchar(255),
    vente_id bigint
        constraint livraison_vente_key_constraint
            references vente
);

alter table livraison owner to postgres;

create table livraison_materiel_transport
(
    mat_trans_id bigint
        constraint fkbhshi8ku04cyvqncbo0mqv4mw
            references materiel_transport,
    livraison_id bigint not null
        constraint livraison_materiel_transport_pkey
            primary key
        constraint fkhq0l73v1wwnhaehguiysr39dn
            references livraison
);

alter table livraison_materiel_transport owner to postgres;

create table vente_info_article_magasin
(
    vente_id bigint not null
        constraint fkl3393ropsrxx0fpudinv40s52
            references vente,
    info_article_magasin_id bigint not null
        constraint uk_1s5er6fxs8luu8hqefkoe5t34
            unique
        constraint fk73gflwri91ipwthq9qt64gqk6
            references info_article_magasin
);

alter table vente_info_article_magasin owner to postgres;

create table trajet
(
    id bigserial not null
        constraint trajet_pkey
            primary key,
    depart varchar(255),
    destination varchar(255),
    distance double precision
);

alter table trajet owner to postgres;

create table materiel_responsable
(
    personne_physique_id bigint
        constraint fkt9fi1e25869ibbp7u3n2sqy8x
            references personne_physique,
    id bigint not null
        constraint materiel_responsable_pkey
            primary key
        constraint fktfhtpg17bmdgm898b1bb3urnb
            references materiel_transport,
    responsable_id bigint
        constraint fkk9nc1h9d4p50ffq0s07962hs7
            references personne_physique
);

alter table materiel_responsable owner to postgres;

create table voyage
(
    id bigserial not null
        constraint voyage_pkey
            primary key,
    date_arrive date,
    date_creation date,
    date_depart date,
    reference text,
    statut_voyage integer,
    materiel_de_transport_id bigint
        constraint voyage_materiel_transport_key_contraint
            references materiel_transport,
    trajet_id bigint
        constraint fkfkkl28of6k6b0yk0h7bh7aipc
            references trajet,
    description varchar(255)
);

alter table voyage owner to postgres;

create table embarquement_article
(
    id bigserial not null
        constraint embarquement_article_pkey
            primary key,
    date timestamp,
    quantite double precision,
    article_id bigint
        constraint ea_article_key_constraint
            references article,
    user_id bigint
        constraint voyage_responsable_key_constraint
            references _user,
    voyage_id bigint
        constraint ea_voyage_key_constraint
            references voyage
);

alter table embarquement_article owner to postgres;

create table voyage_article_fournisseur
(
    fournisseur_id bigint
        constraint fkliq46bnw1ol82h1dadl1hkjni
            references client_fournisseur,
    va_id bigint not null
        constraint voyage_article_fournisseur_pkey
            primary key
        constraint fkeh2o7ims06p0l0ybatyxxxy83
            references embarquement_article
);

alter table voyage_article_fournisseur owner to postgres;

create table voyage_article_magasin
(
    magasin_id bigint
        constraint fkm3q21a6hmx6usjxps4fwroy4t
            references magasin,
    va_id bigint not null
        constraint voyage_article_magasin_pkey
            primary key
        constraint fkjyfq8ygywkh1w58u8v1jykkh4
            references embarquement_article
);

alter table voyage_article_magasin owner to postgres;

create table info_article_voyage
(
    id bigserial not null
        constraint info_article_voyage_pkey
            primary key,
    date date,
    description text,
    is_transfered boolean,
    num_facture varchar(255),
    poids double precision,
    prix_achat double precision,
    prix_transport double precision,
    prix_vente double precision,
    quantite_ajout double precision,
    reference varchar(255),
    article_id bigint
        constraint fk_article_id
            references article
            on delete cascade,
    fournisseur_id bigint
        constraint fko2fb7g8vsh5vocredoqcwyp4a
            references client_fournisseur,
    unite_id bigint
        constraint fk_unite_id
            references unite
            on delete cascade,
    user_id bigint
        constraint fkiwkpeqnbvxaqo3qi5eppym45b
            references _user,
    voyage_id bigint
        constraint fkqbiamwi8sfb5hw0hrtlxgl38j
            references voyage,
    date_peremption date,
    materiel_transport_id bigint
        constraint fkoe2hmnqy8rwcfelkjy6k0r6vc
            references materiel_transport
);

alter table info_article_voyage owner to postgres;

create table caisse
(
    id bigserial not null
        constraint caisse_pkey
            primary key,
    value double precision,
    filiale_id bigint
        constraint fkfi0bmo3ltebryjb2qfu9v9ybq
            references filiale,
    mode_payement varchar(255)
);

alter table caisse owner to postgres;

create table peremption
(
    id bigserial not null
        constraint peremption_pkey
            primary key,
    date_peremption date,
    quantite_peremption double precision,
    article_id bigint
        constraint fk_article_id
            references article
            on delete cascade,
    magasin_id bigint
        constraint fk_magasin_id
            references magasin
            on delete cascade,
    unite_id bigint
        constraint fk_unite_id
            references unite
            on delete cascade
);

alter table peremption owner to postgres;

create table fonction_filiale
(
    filiale_id bigint
        constraint fkojblgjae933vkoi93xypxib4n
            references filiale,
    id bigint not null
        constraint fonction_filiale_pkey
            primary key
        constraint fkd2hew22h3qdb19pb51mp2fvby
            references fonction
);

alter table fonction_filiale owner to postgres;

create table payement
(
    id bigserial not null
        constraint payement_pkey
            primary key,
    date_payement date,
    is_valid boolean,
    mode_payement integer,
    montant_payement double precision,
    montant_restant double precision,
    ifc_id bigint
        constraint fkbvp9mp3pjymly51xyiubpata3
            references info_filiale_caisse,
    user_id bigint
        constraint fkq88evq9tx2p13ss7va4bhlu20
            references _user
);

alter table payement owner to postgres;

create table payement_trosa
(
    trosa_id bigint
        constraint fkb2526f59dc0c9oml61dam6ye9
            references trosa,
    id bigint not null
        constraint payement_trosa_pkey
            primary key
        constraint fk5u3q6yc86k8foce909bd28f2n
            references payement
);

alter table payement_trosa owner to postgres;

create table payement_vente
(
    vente_id bigint
        constraint fkbvl3nwk74cdeunyr28343tfmp
            references vente,
    id bigint not null
        constraint payement_vente_pkey
            primary key
        constraint fkja4c97ku8ehkk3efiny6d88h4
            references payement
);

alter table payement_vente owner to postgres;

create table trosa_payements
(
    trosa_id bigint not null
        constraint fk5tvd0q6owdj5oeevkwwoqr3jh
            references trosa,
    payements_id bigint not null
        constraint uk_bi7hj23qmxw0uy3vfawi9gs8u
            unique
        constraint fk5a1fb6pi4ltlfj9q468f9mexc
            references payement
);

alter table trosa_payements owner to postgres;

create procedure mettre_a_jour_date_peremption(IN id_magasin bigint, IN id_article bigint, IN id_unite bigint, IN new_date timestamp without time zone, IN old_date timestamp without time zone)
    language plpgsql
as $$
DECLARE
    row RECORD;
BEGIN

    for row in select ap1.id from approv ap1 join info_article_magasin iam on iam.id = ap1.info_article_magasin_id where iam.magasin_id=id_magasin and iam.article_id= id_article and iam.unite_id = id_unite and ap1.date_peremption =old_date
        loop
            update approv set  date_peremption =  new_date where id = row.id;
        end loop;

END;
$$;

alter procedure mettre_a_jour_date_peremption(bigint, bigint, bigint, timestamp, timestamp) owner to postgres;

create procedure mettre_a_jour_quantite_en_peremption(IN id_magasin bigint, IN id_article bigint, IN id_unite bigint, IN nouveau_quantite double precision)
    language plpgsql
as $$
DECLARE
    row RECORD;
    QUANTITE_AJOUT_TEMP double precision = 0;
BEGIN

    QUANTITE_AJOUT_TEMP := nouveau_quantite;

    FOR row IN select ap.id,ap.quantite_peremption from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id
               where iam.magasin_id =id_magasin and iam.unite_id=id_unite and iam.article_id = id_article and ap.quantite_peremption > 0 order by ap.date_peremption asc
        LOOP

            if QUANTITE_AJOUT_TEMP > 0 then

                if QUANTITE_AJOUT_TEMP > row.quantite_peremption then

                    update approv set quantite_peremption = 0 where id = row.id;

                end if;

                if QUANTITE_AJOUT_TEMP < row.quantite_peremption then

                    update approv set quantite_peremption = ( row.quantite_peremption - QUANTITE_AJOUT_TEMP)  where id = row.id;

                end if;

                QUANTITE_AJOUT_TEMP := ( QUANTITE_AJOUT_TEMP - row.quantite_peremption );

            end if;

        END LOOP;
end;
$$;

alter procedure mettre_a_jour_quantite_en_peremption(bigint, bigint, bigint, double precision) owner to postgres;

create function before_insert_on_info_article_unite_magasin() returns trigger
    language plpgsql
as $$
DECLARE
    quantite_en_stock_actuelement DOUBLE PRECISION = 0.0;
    quantite_niveau_unite DOUBLE PRECISION = 0.0;
    nouveau_quantite_en_stock DOUBLE PRECISION =0.0;
    primary_unite_id BIGINT =0;
    item_count INT =0;
    nombre_quantite_alert INT = 0;
    ALERT_FILIALE_ID BIGINT = 0;
BEGIN
    -- recuperer l'unite primaire de l'article
    SELECT au.unite_id into primary_unite_id FROM article_unite au where article_id = new.article_id and au.niveau = 1;

    -- RECUPERATION DE LA QUANTITE NIVEAU
    SELECT au.quantite_niveau into quantite_niveau_unite FROM  article_unite au WHERE au.article_id = new.article_id AND au.unite_id = new.unite_id;

    -- RECUPERER LE NOMBRE EN STOCK
    SELECT count(article_id) into item_count FROM  stock  WHERE article_id = new.article_id AND unite_id = primary_unite_id AND magasin_id = new.magasin_id;

    -- RECUPERER LE STOCK ACTUEL
    SELECT count into quantite_en_stock_actuelement FROM  stock  WHERE article_id = new.article_id AND unite_id = primary_unite_id AND magasin_id = new.magasin_id;

    if item_count = 0 then

        nouveau_quantite_en_stock := new.quantite_ajout*quantite_niveau_unite;

        new.quantite_stock_apres_operation := new.quantite_ajout;

        -- INSERTION DANS LA TABLE STOCK

        insert into stock(article_id,unite_id,magasin_id,count) values (NEW.article_id,primary_unite_id,NEW.magasin_id,nouveau_quantite_en_stock);

        -- INITIALISATION DE LA QUANTITE EN ALERT DE CHAQUE ARTICLE ET FILIALE

        select m.filiale_id into ALERT_FILIALE_ID from magasin m where m.id_magasin=new.magasin_id;

        select count(ia.filiale_id) into nombre_quantite_alert from inventory_alert ia where ia.article_id = new.article_id and ia.filiale_id = ALERT_FILIALE_ID;

        if nombre_quantite_alert = 0 then
            insert into inventory_alert(article_id, filiale_id, quantite) values (new.article_id,ALERT_FILIALE_ID,0.0);
        end if;

    end if;

    if item_count > 0 then

        if new.type_operation = 'ENTRE' or new.type_operation like '%TRANSFERT%' or new.type_operation = 'AVOIR' then

            nouveau_quantite_en_stock := quantite_en_stock_actuelement + (new.quantite_ajout*quantite_niveau_unite) ;

            new.quantite_stock_apres_operation := (quantite_en_stock_actuelement/quantite_niveau_unite) + new.quantite_ajout;


            if new.type_operation like '%TRANSFERT%' AND  new.type_operation like '%VERS%' then

                nouveau_quantite_en_stock := quantite_en_stock_actuelement - (new.quantite_ajout*quantite_niveau_unite);

                new.quantite_stock_apres_operation :=  (quantite_en_stock_actuelement/quantite_niveau_unite) - quantite_niveau_unite;
            end if;

            -- INITIALISATION DE LA QUANTITE EN ALERT DE CHAQUE ARTICLE ET FILIALE

            if new.type_operation = 'ENTRE' then

                select m.filiale_id into ALERT_FILIALE_ID from magasin m where m.id_magasin=new.magasin_id;

                select count(ia.filiale_id) into nombre_quantite_alert from inventory_alert ia where ia.article_id = new.article_id and ia.filiale_id = ALERT_FILIALE_ID;

                if nombre_quantite_alert = 0 then
                    insert into inventory_alert(article_id, filiale_id, quantite) values (new.article_id,ALERT_FILIALE_ID,0.0);
                end if;

            end if;

        end if;

        if new.type_operation = 'VENTE' or new.type_operation = 'SORTIE'then

            nouveau_quantite_en_stock := quantite_en_stock_actuelement - (new.quantite_ajout*quantite_niveau_unite) ;

            new.quantite_stock_apres_operation :=  (quantite_en_stock_actuelement/quantite_niveau_unite) - new.quantite_ajout;

        end if;

        if new.type_operation = 'INVENTAIRE' then

            nouveau_quantite_en_stock := new.quantite_ajout;

            new.quantite_stock_apres_operation := new.quantite_ajout;

            -- ENREGISTRER L'ANNULATION
            insert into info_article_magasin (date, description, quantite_ajout,quantite_stock_apres_operation,reference, type_operation, article_id, magasin_id, unite_id, user_id)

            values (new.date,'Modification de la quantité en stock suite a un inventaire',quantite_en_stock_actuelement,new.quantite_ajout,new.reference,'ANNULATION',new.article_id,new.magasin_id,new.unite_id,new.user_id);

        end if;

        -- Mis-a-jour du stock

        update stock set count = nouveau_quantite_en_stock where article_id = NEW.article_id AND unite_id = primary_unite_id AND magasin_id = NEW.magasin_id;

        quantite_en_stock_actuelement :=0;

    end if;

    -- MIS A JOUR DU QUANTITE PEREMPTION

    if     new.type_operation = 'VENTE'
        or (new.type_operation like '%TRANSFERT%' AND  new.type_operation like '%VERS%')
        or  new.type_operation = 'SORTIE'  then

        call mettre_a_jour_quantite_en_peremption(new.magasin_id,new.article_id,new.unite_id,new.quantite_ajout);

    end if;
    RETURN NEW; --ignored since this is after trigger
END;
$$;

alter function before_insert_on_info_article_unite_magasin() owner to postgres;

create trigger info_article_trigger
    before insert
    on info_article_magasin
    for each row
execute procedure before_insert_on_info_article_unite_magasin();

create function on_insert_caisse_filiale() returns trigger
    language plpgsql
as $$
DECLARE
    montant_caisse double precision=0.0;
    nombre_element INT = 0.0;
BEGIN

    select count(c.id) into nombre_element from caisse c where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement;

    if nombre_element = 0 then

        insert into caisse (value,filiale_id,mode_payement)  values (new.montant_operation,new.filiale_id,new.mode_payement);

    end if;

    if nombre_element >= 1 then


        if new.operation_caisse = 'FACTURE' or new.operation_caisse = 'ENCAISSEMENT' then

            update caisse c set value = value + new.montant_operation where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement ;

        end if;

        if new.operation_caisse = 'DECAISSEMENT' or new.operation_caisse = 'AVOIR' then
            if montant_caisse > 0.0 then
                update caisse c set value = caisse.value - new.montant_operation where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement;
            end if;
        end if;
    end if;
    RETURN NEW; --ignored since this is after trigger
END;
$$;

alter function on_insert_caisse_filiale() owner to postgres;

create trigger on_insert_on_info_filiale_caisse
    before insert
    on info_filiale_caisse
    for each row
execute procedure on_insert_caisse_filiale();

insert into fonctionnalite(nom) values ('menu-dashboard');
insert into fonctionnalite(nom) values ('menu-article');
insert into fonctionnalite(nom) values ('menu-vente');
insert into fonctionnalite(nom) values ('menu-detail-vente');
insert into fonctionnalite(nom) values ('menu-magasin');
insert into fonctionnalite(nom) values ('menu-stock');
insert into fonctionnalite(nom) values ('menu-facture');
insert into fonctionnalite(nom) values ('menu-prix');
insert into fonctionnalite(nom) values ('menu-embarquement');
insert into fonctionnalite(nom) values ('menu-peremption');
insert into fonctionnalite(nom) values ('menu-client');
insert into fonctionnalite(nom) values ('menu-fournisseur');
insert into fonctionnalite(nom) values ('menu-operation');
insert into fonctionnalite(nom) values ('menu-operation-liste');
insert into fonctionnalite(nom) values ('menu-operation-entree');
insert into fonctionnalite(nom) values ('menu-operation-sortie');
insert into fonctionnalite(nom) values ('menu-operation-transfert');
insert into fonctionnalite(nom) values ('menu-livraison');
insert into fonctionnalite(nom) values ('menu-caisse');
insert into fonctionnalite(nom) values ('menu-utilisateur');
insert into fonctionnalite(nom) values ('menu-choix-magasin');
insert into fonctionnalite(nom) values ('menu-autorisation');
insert into fonctionnalite(nom) values ('menu-transports');

insert into fonctionnalite(nom) values ('crud-article');
insert into fonctionnalite(nom) values ('crud-magasin');
insert into fonctionnalite(nom) values ('crud-prix');
insert into fonctionnalite(nom) values ('crud-facture');
insert into fonctionnalite(nom) values ('crud-peremption');
insert into fonctionnalite(nom) values ('crud-fournisseur');
insert into fonctionnalite(nom) values ('crud-embarquement');
insert into fonctionnalite(nom) values ('crud-caisse');
insert into fonctionnalite(nom) values ('crud-utilisateur');
insert into fonctionnalite(nom) values ('crud-client');

insert into fonctionnalite(nom) values ('notification-facture');
insert into fonctionnalite(nom) values ('notification-livraison');
insert into fonctionnalite(nom) values ('notification-commande');
insert into fonctionnalite(nom) values ('option-configuration systeme');

insert into fonctionnalite(nom) values ('option-bloquer compte client');
insert into fonctionnalite(nom) values ('impression-facture');
insert into fonctionnalite(nom) values ('impression-livraison');

insert into fonctionnalite(nom) values ('imprimer-facture');
insert into fonctionnalite(nom) values ('imprimer-avoir');
insert into fonctionnalite(nom) values ('imprimer-cheque');
insert into fonctionnalite(nom) values ('imprimer-any maka vola');
insert into fonctionnalite(nom) values ('imprimer-credit');
insert into fonctionnalite(nom) values ('imprimer-recette depense');
insert into fonctionnalite(nom) values ('imprimer-recu any maka vola');
insert into fonctionnalite(nom) values ('imprimer-recu credit');
insert into fonctionnalite(nom) values ('imprimer-Bon entre');
insert into fonctionnalite(nom) values ('imprimer-Bon sortie');
insert into fonctionnalite(nom) values ('imprimer-Bon transfert');
insert into fonctionnalite(nom) values ('imprimer-Bon changement code article');


