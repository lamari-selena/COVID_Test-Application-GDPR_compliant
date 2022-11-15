package covidalert.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;

public class KeyCloakConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://keycloak:8080/auth";
    public final static String realm = "microservices-realm";
    final static String clientId = "user-service";
    final static String clientSecret = "3a54ebd0-2b66-4ce9-8b14-a569b14281e1";

    public KeyCloakConfig() {

    }

    public static Keycloak getInstance() {
        if(keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .grantType(OAuth2Constants.PASSWORD)
                    .realm("master")
                    .clientId("admin-cli")
                    .username("admin")
                    .password("admin")
                    .resteasyClient(
                            new ResteasyClientBuilder()
                                    .connectionPoolSize(10).build()
                    )
                    .build();
            keycloak.tokenManager().getAccessToken();
        }
        return keycloak;
    }
}
