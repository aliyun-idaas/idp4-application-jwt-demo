
package com.idsmanager.demo.jwt.infrastructure;


import com.idsmanager.demo.jwt.commons.utils.RandomValueStringGenerator;
import com.idsmanager.demo.jwt.commons.utils.UUIDGenerator;
import com.idsmanager.demo.jwt.domain.security.Privilege;

/**
 * 2015/11/10
 *
 * @author Shengzhao Li
 */
public abstract class OAuthUtils {

    public enum GrantType {
        PASSWORD("password"),
        REFRESH_TOKEN("refresh_token"),
        CLIENT_CREDENTIALS("client_credentials");

        private final String type;

        GrantType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public String getValue() {
            return name();
        }


    }


//  ------------------------------------  EMM Server client constants ------------------------------------


    //Multi grant_type split by ','
    public static final String EMM_GRANT_TYPES = "client_credentials";

    //Grant type of password
    public static final String PASSWORD_GRANT_TYPE = GrantType.PASSWORD.getType();

    public static final String MOBILE_TOKEN_GRANT_TYPE=GrantType.PASSWORD.getType() + "," + GrantType.REFRESH_TOKEN.getType();

    //See  security.xml configuration
    public static final String EMM_RESOURCE_IDS = "emm-server-resource";

    public static final String MOBILE_API="mobile-api-resource";

    //Spring security role
//    public static final String EMM_CLIENT_PRIVILEGES = "ROLE_" + Privileges.EMM_SERVER.name();

    public static final String MOBILE_API_AUTHORITIES = "ROLE_" + Privilege.USER_ACCOUNT.name();

    //Available values:   read, read write
    public static final String DEFAULT_CLIENT_SCOPE = "read";


    /*
    * Generate client_secret
    * */
    private static final RandomValueStringGenerator clientSecretGenerator = new RandomValueStringGenerator(40);


    //singleton
    private OAuthUtils() {
    }


    public static String tokenURI(String host) {
        if (host.endsWith("/")) {
            return host + "oauth/token";
        }
        return host + "/oauth/token";
    }


    /**
     * Generate a new clientId
     *
     * @return clientId
     */
    public static String generateClientId() {
        return UUIDGenerator.generate();
    }


    /**
     * Generate a new clientSecret
     *
     * @return clientSecret
     */
    public static String generateClientSecret() {
        return clientSecretGenerator.generate();
    }


}
