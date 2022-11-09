package br.com.api.oauth;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

public class OAuth2AuthServerConfiguration /*extends AuthorizationServerConfigurerAdapter*/  {
/*
	  @Value("${user.oauth.clientId}")
	    private String clientID;

	    @Value("${user.oauth.clientSecret}")
	    private String clientSecret;

	    @Value("${user.oauth.redirectUris}")
	    private String redirectURLs;

	    @Value("${user.oauth.accessTokenValidity}")
	    private int accessTokenValidity;

	    @Value("${user.oauth.refreshTokenValidity}")
	    private int refreshTokenValidity;

	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.tokenKeyAccess("permitAll()")
	            .checkTokenAccess("isAuthenticated()");
	    }

	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients
	            .inMemory()
	            .withClient(clientID)
	            .secret(passwordEncoder.encode(clientSecret))
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
	            .scopes("user_info")
	            .authorities("READ_ONLY_CLIENT")
	            .redirectUris(redirectURLs)
	            .accessTokenValiditySeconds(accessTokenValidity)
	            .refreshTokenValiditySeconds(refreshTokenValidity);
	    }
	   */
	}
	

