package mg.imwa.config;


import java.util.concurrent.atomic.AtomicReference;
public class TenantContext{
  static String DEFAULT_TENANT = "default_tenant";
  private static AtomicReference<String> stringAtomicReference = new AtomicReference<>();
  public static void setTenantId(String tenantId){
       stringAtomicReference.set(tenantId);
  }
  public static String getTenantId() {
    return stringAtomicReference.get();
  }
}