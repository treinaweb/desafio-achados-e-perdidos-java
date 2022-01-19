package br.com.treinaweb.achadosperdidos.core.permissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface AchadosPerdidosPermissions {

    @PreAuthorize("isAuthenticated")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isAuthenticated {}

    @PreAuthorize("isAuthenticated and @securityUtilsImpl.isObjetoOwnedByAuthenticatedUser(#objetoId)")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface isObjetoOwnedByAuthenticatedUser {}

}
