TRUNCATE TABLE company cascade;
TRUNCATE TABLE filiale_tenant_users;
TRUNCATE TABLE personne cascade ;
TRUNCATE TABLE tenant_user cascade ;
TRUNCATE TABLE company_data_source_config cascade ;
delete from admin where user_name<>'armand_judicael';
