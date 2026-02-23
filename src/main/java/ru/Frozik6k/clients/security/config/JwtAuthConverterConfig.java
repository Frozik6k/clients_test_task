package ru.Frozik6k.clients.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class JwtAuthConverterConfig {

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(rolesConverter());
        return converter;
    }

    private Converter<Jwt, Collection<GrantedAuthority>> rolesConverter() {
        return jwt -> {
            Object roles = jwt.getClaims().get("roles");
            if (roles instanceof List<?> list) {
                return list.stream()
                        .map(String::valueOf)
                        .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());
            }
            return List.of();
        };
    }
}
