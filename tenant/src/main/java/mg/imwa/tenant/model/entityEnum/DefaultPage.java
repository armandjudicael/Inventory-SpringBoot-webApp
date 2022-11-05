package mg.imwa.tenant.model.entityEnum;

import lombok.Getter;

@Getter
public enum DefaultPage {
    DASHBOARD("dashboard"),
    MENU_ARTICLE("menu-article"),
    MENU_VENTE("menu-vente"),
    MENU_DETAIL_VENTE("menu-detail-vente"),
    MENU_MAGASIN("menu-magasin"),
    MENU_STOCK("menu-stock"),
    MENU_FACTURE("menu-facture"),
    MENU_PRIX("menu-prix"),
    MENU_EMBARQUEMENT("embarquement/menu-embarquement"),
    MENU_PEREMPTION("menu-peremption"),
    MENU_CLIENT("menu-client"),
    MENU_FOURNISSEUR("menu-fournisseur"),
    MENU_OPERATION_LISTE("operation/liste"),
    MENU_OPERATION_ENTRE("operation/entree"),
    MENU_OPERATION_SORTIE("operation/sortie"),
    MENU_OPERATION_TRANSFERT("operation/transfert"),
    MENU_LIVRAISON("menu-livraison"),
    MENU_CAISSE("menu-caisse"),
    MENU_UTILISATEUR("menu-utilisateur"),
    MENU_CHOIX_MAGASIN("menu-choix-magasin"),
    MENU_AUTORISATION("menu-autorisation"),
    MENU_TRANSPORT("menu-transport");
    String viewUrl;
    DefaultPage(String viewUrl) {
        this.viewUrl = viewUrl;
    }
}
