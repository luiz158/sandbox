package org.touchdata.sec.util;

public final class SecurityConstants {

    // to be cleared out

    public static final String ADMIN_EMAIL = "admin@gmail.com";
    public static final String ADMIN_PASSWORD = "adminPass";

    public static final String END_USER_EMAIL = "enduser1@gmail.com";
    public static final String END_USER_PASS = "enduser1Pass";

    public static final String NAME = ADMIN_EMAIL;
    public static final String PASS = ADMIN_PASSWORD;
    public static final String EMAIL = ADMIN_EMAIL;

    // privileges

    public static final class Privileges {

        /** A placeholder role for administrator. */
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        /** A placeholder role for enduser. */
        public static final String ROLE_ENDUSER = "ROLE_ENDUSER";

        // User
        public static final String CAN_USER_WRITE = "ROLE_USER_WRITE";
        public static final String CAN_USER_READ = "ROLE_USER_READ";
        public static final String CAN_PRINCIPAL_WRITE = "ROLE_PRIVILEGE_WRITE";

        // Role
        public static final String CAN_ROLE_WRITE = "ROLE_ROLE_WRITE";

        // Privilege
        public static final String CAN_PRIVILEGE_WRITE = "ROLE_PRIVILEGE_WRITE";

    }

    private SecurityConstants() {
        throw new AssertionError();
    }

}
