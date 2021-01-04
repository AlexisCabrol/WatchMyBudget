package wmb.WatchMyBudget.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getUsername() {
        final SecurityContext ctx = SecurityContextHolder.getContext();
        return ctx.getAuthentication().getPrincipal().toString();
    }
}
