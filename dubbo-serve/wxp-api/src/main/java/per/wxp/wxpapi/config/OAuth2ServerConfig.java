package per.wxp.wxpapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import per.wxp.wxpapi.utils.SysRedisTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuth2ServerConfig {


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
            http.
                    authorizeRequests()
                    // swagger start
                    .antMatchers(
                            "/webjars/**",
                            "/resources/**",
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/v2/api-docs")
                    .permitAll()
                    .antMatchers("/oauth/token").permitAll()
                    .antMatchers("/users/**","/menus/**","/roles/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated();
            }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;
        @Autowired
        private DataSource dataSource;

        @Bean
        public ClientDetailsService clientDetailsService() {
            return new JdbcClientDetailsService(dataSource);
        }


        /**
         *数据暂放入内存中
         * @param clients
         * @throws Exception
         */
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
//            //配置两个客户端,一个用于password认证一个用于client认证
//            clients.inMemory().withClient("client_1")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("select")
//                    .authorities("client")
//                    .secret(finalSecret)
//                    .and().withClient("client_2")
//                    .resourceIds(DEMO_RESOURCE_ID)
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .scopes("select")
//                    .authorities("client")
//                    .secret(finalSecret);
//        }

        /**
         *数据放入数据库中
         * @param clients
         * @throws Exception
         */
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(clientDetailsService());
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            /*jwt方式+redis存储token*/
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
            endpoints
                    .tokenStore(new SysRedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        }


        /**
         * jwt 设置
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            /*设置签名*/
            accessTokenConverter.setSigningKey("wxp");
            return accessTokenConverter;
        }
    }



}
