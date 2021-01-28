package com.lys.lys_admin_api.config.security;

//import com.sugon.beatles.admin.model.User;
//import com.sugon.beatles.admin.service.UserService;
//import com.sugon.beatles.admin.web.dto.UserDto;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 认证与授权
 **/
@Component
public class CasRealm extends Pac4jRealm {

//    @Autowired
//    @Lazy
//    private UserService userService;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final Pac4jToken pac4jToken = (Pac4jToken) authenticationToken;
        final List<CommonProfile> profiles = pac4jToken.getProfiles();
        final CommonProfile commonProfile = profiles.get(0);

//        User user = userService.getByUsername(commonProfile.getId());
//        UserDto userDto = userService.getOne(user.getId());
//        commonProfile.addAttribute("userDto", userDto);
//        commonProfile.setRoles(new HashSet(userDto.getRoles()));
//        commonProfile.setPermissions(new HashSet(userDto.getPermissions()));

        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection(new Pac4jPrincipal(profiles, super.getPrincipalNameAttribute()), getName());

        return new SimpleAuthenticationInfo(simplePrincipalCollection, profiles.hashCode());
    }

}