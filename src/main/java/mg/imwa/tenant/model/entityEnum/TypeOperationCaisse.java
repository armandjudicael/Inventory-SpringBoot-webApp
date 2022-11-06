package mg.imwa.tenant.model.entityEnum;

import java.lang.reflect.Type;

public enum TypeOperationCaisse {
    ENCAISSEMENT, DECAISSEMENT, VENTE, AVOIR, AUTRE, FACTURE;

    public static String operation2String(TypeOperationCaisse type) {
        switch (type) {
            case ENCAISSEMENT:
                return "ENCAISSEMENT";
            case DECAISSEMENT:
                return "DECAISSEMENT";
            case VENTE:
                return "VENTE";
            case AVOIR:
                return "AVOIR";
            case AUTRE:
                return "AUTRE";
            case FACTURE:
                return "FACTURE";
            default:
                return "";
        }
    }

    public static TypeOperationCaisse string2Operation(String value) {
        switch (value) {
            case "ENCAISSEMENT":
                return ENCAISSEMENT;
            case "DECAISSEMENT":
                return DECAISSEMENT;
            case "VENTE":
                return VENTE;
            case "AVOIR":
                return AVOIR;
            case "AUTRE":
                return AUTRE;
            case "FACTURE":
                return FACTURE;
            default:
                return AUTRE;
        }
    }
}
