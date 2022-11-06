package mg.imwa.tenant.model.wrapper;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpirationWrapper {
    private String nomArticle;
    private String nomUnite;
    private String datePeremption;
    private String quantitePeremetion;
    private String articleId;
    private String uniteId;
    private String magasinId;
    private String approvId;
    private String expirationStatus;
}
