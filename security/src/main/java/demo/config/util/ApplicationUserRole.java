package demo.config.util;

import static demo.config.util.ApplicationUserPermission.COURSE_READ;
import static demo.config.util.ApplicationUserPermission.COURSE_WRITE;
import static demo.config.util.ApplicationUserPermission.PRODUCT_READ;
import static demo.config.util.ApplicationUserPermission.PRODUCT_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
    AGENT(Sets.newHashSet(PRODUCT_READ)),//agent can read product
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, PRODUCT_READ, PRODUCT_WRITE)), //admin can read and write
    CUSTOMER(Sets.newHashSet(COURSE_READ, PRODUCT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
    
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
