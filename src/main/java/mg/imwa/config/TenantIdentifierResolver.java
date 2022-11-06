package mg.imwa.config;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver{
    @Override public String resolveCurrentTenantIdentifier(){
        String currentTenantId =TenantContext.getTenantId();
        return (currentTenantId != null && !currentTenantId.equals("")) ? currentTenantId : TenantContext.DEFAULT_TENANT;
    }
    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}