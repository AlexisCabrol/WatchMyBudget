package wmb.WatchMyBudget.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wmb.WatchMyBudget.dtos.UtilisateurDTO;
import wmb.WatchMyBudget.models.Utilisateur;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static wmb.WatchMyBudget.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ConnectedUserDetailsService connectedUserDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   ConnectedUserDetailsService connectedUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.connectedUserDetailsService = connectedUserDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Utilisateur creds = new ObjectMapper()
                    .readValue(req.getInputStream(), Utilisateur.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getIdentifiant(),
                            creds.getMotDePasse()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String identifiant = ((User) auth.getPrincipal()).getUsername();
        String token = JWT.create()
                .withSubject(identifiant)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader("Content-Type", "application/json");
        String JWT = TOKEN_PREFIX + token;
        res.addHeader(HEADER_STRING, JWT);

        ObjectMapper mapper = new ObjectMapper();
        UtilisateurDTO utilisateur = connectedUserDetailsService.getUtilisateurCourantDTO(identifiant, JWT);

        res.getWriter()
                .write(mapper.writeValueAsString(utilisateur));
        res.flushBuffer();
    }
}
