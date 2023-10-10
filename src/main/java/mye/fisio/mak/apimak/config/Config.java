package mye.fisio.mak.apimak.config;

import org.springframework.stereotype.Component;

@Component
public class Config {
    
    public static final String BD_NAME = "fisiomak";
    public static final String CLIENTS_TABLE = "clients";
    public static final String COMMENTS_TABLE = "comments";
    
    public class CLIENTS_COLUMNS {
        public static final String ID = "id";
        public static final String CEDULA = "cedula";
        public static final String ROLE = "role";
        public static final String NAME = "name";
        public static final String LAST_NAME = "last_name";
        public static final String ENTERPRISE_NAME = "enterprise_name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String PHONE = "phone";
    }

    public class COMMENTS_COLUMNS {
        public static final String ID = "id";
        public static final String ID_CLIENT = "id_client";
        public static final String COMMENTARY = "commentary";
    }

}
