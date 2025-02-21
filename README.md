This is a simple Spring Boot application demonstrating Spring Security for authentication and authorization.

**Features**
✅ User authentication with Spring Security
✅ Secure API endpoints
✅ In-memory user authentication

**Technologies Used**
*Java 17+
*Spring Boot 3.x
*Spring Security
*Maven
*H2 Database (optional)

**Getting Started**
#Prerequisites
*Java 17+
*Maven 3+

**Security Configuration**
#Spring Security is configured in SecurityConfig.java:

@Configuration

@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
                  .csrf(customizer -> customizer.disable())
                  .authorizeHttpRequests(request -> request
                          .requestMatchers("register", "login")
                          .permitAll()
                          .anyRequest().authenticated())
                  .httpBasic(Customizer.withDefaults())
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                  .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
    }
}
