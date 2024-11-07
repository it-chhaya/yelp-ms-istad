package kh.edu.cstad.idenity.security;

import kh.edu.cstad.idenity.domain.Administrator;
import kh.edu.cstad.idenity.domain.Authority;
import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.domain.UserAuthority;
import kh.edu.cstad.idenity.features.administrator.AdministratorRepository;
import kh.edu.cstad.idenity.features.authority.AuthorityRepository;
import kh.edu.cstad.idenity.features.user.UserRepository;
import kh.edu.cstad.idenity.security.repository.JpaRegisteredClientRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init {

    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;

    @PostConstruct
    void initUserDetails() {

        if (userRepository.count() < 1) {

            // Authority initialization
            Authority user = new Authority();
            user.setName("USER");
            authorityRepository.save(user);

            Authority system = new Authority();
            system.setName("SYSTEM");
            authorityRepository.save(system);

            Authority admin = new Authority();
            admin.setName("ADMIN");
            authorityRepository.save(admin);

            Authority editor = new Authority();
            editor.setName("EDITOR");
            authorityRepository.save(editor);

            Authority businessOwner = new Authority();
            businessOwner.setName("BUSINESS_OWNER");
            authorityRepository.save(businessOwner);

            Authority subscriber = new Authority();
            subscriber.setName("SUBSCRIBER");
            authorityRepository.save(subscriber);

            // User initialization
            User adminUser = new User();
            adminUser.setUuid(UUID.randomUUID().toString());
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@gp.com");
            adminUser.setPassword(passwordEncoder.encode("qwerqwer"));
            adminUser.setFamilyName("Chan");
            adminUser.setGivenName("Chhaya");
            adminUser.setProfileImage("avatar.png");
            adminUser.setCoverImage("cover.png");
            adminUser.setDob(LocalDate.now());
            adminUser.setGender("Male");
            adminUser.setPhoneNumber("077459947");
            adminUser.setEmailVerified(true);
            adminUser.setIsEnabled(true);
            adminUser.setCredentialsNonExpired(true);
            adminUser.setAccountNonLocked(true);
            adminUser.setAccountNonExpired(true);

            // start setup user authorities for admin
            UserAuthority defaultUserAuthority = new UserAuthority(); // default
            defaultUserAuthority.setUser(adminUser);
            defaultUserAuthority.setAuthority(user);

            UserAuthority adminAuthority = new UserAuthority(); // admin
            adminAuthority.setUser(adminUser);
            adminAuthority.setAuthority(admin);

            UserAuthority bizAuthority = new UserAuthority();
            bizAuthority.setUser(adminUser);
            bizAuthority.setAuthority(businessOwner);

            adminUser.setUserAuthorities(Set.of(defaultUserAuthority, adminAuthority, bizAuthority));
            // end setup user authorities for admin

            Administrator administrator = new Administrator();
            administrator.setUser(adminUser);
            administratorRepository.save(administrator);
        }

    }

    @PostConstruct
    void initOAuth2() {

        TokenSettings tokenSettings = TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                .accessTokenTimeToLive(Duration.ofDays(3))
                .reuseRefreshTokens(false)
                .refreshTokenTimeToLive(Duration.ofDays(5))
                .build();

        ClientSettings clientSettings = ClientSettings.builder()
                .requireProofKey(true)
                .requireAuthorizationConsent(true)
                .build();

        var web = RegisteredClient.withId("gpweb")
                .clientId("gpweb")
                .clientSecret(passwordEncoder.encode("qwerqwer")) // store in secret manager
                .scopes(scopes -> {
                    scopes.add(OidcScopes.OPENID);
                    scopes.add(OidcScopes.PROFILE);
                    scopes.add(OidcScopes.EMAIL);
                    scopes.add("offline_access");
                })
                .redirectUris(uris -> {
                    /*uris.add("http://localhost:8080/login/oauth2/code/gpweb");
                    uris.add("http://localhost:16800/login/oauth2/code/gpweb");
                    uris.add("http://localhost:16600/login/oauth2/code/gpweb");*/
                    uris.add("http://localhost:8080");
                    uris.add("http://localhost:16800");
                    uris.add("http://localhost:16600");
                })
                .postLogoutRedirectUris(uris -> {
                    uris.add("http://localhost:8080");
                    uris.add("http://localhost:16800");
                    uris.add("http://localhost:16600");
                })
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE) //TODO: grant_type:client_credentials, client_id & client_secret, redirect_uri
                .authorizationGrantTypes(grantTypes -> {
                    grantTypes.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                .clientSettings(clientSettings)
                .tokenSettings(tokenSettings)
                .build();

        RegisteredClient registeredClient = jpaRegisteredClientRepository.findByClientId("gpweb");
        log.info("Registered client: {}", registeredClient);

        if (registeredClient == null) {
            jpaRegisteredClientRepository.save(web);
        }

    }

}
