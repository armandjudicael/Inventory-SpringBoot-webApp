package mg.imwa.admin.model;

public enum DatabaseType {
    MYSQL,POSTGRESQL,H2,SQL_SERVER,MONGODB,REDIS;
    public String dbType2String(){
        switch (this){
            case MYSQL: return "mysql";
            default: return "postgresql";
        }
    }
}
