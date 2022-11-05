package mg.imwa.admin.config;

import java.util.concurrent.atomic.AtomicReference;

public class TenantContext{

  private static ThreadLocal<String> CONTEXT = new InheritableThreadLocal<>();

  static String DEFAULT_TENANT = "default_tenant";

  private volatile static String currentTenant = "default_tenant";

  public static void setTenantId(String tenantId){
    currentTenant = tenantId;
  }

  public static String getTenantId() {
    return currentTenant;
  }

}