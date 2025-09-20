// src/main/java/com/gmail/merikbest2015/ecommerce/security/oauth2/CustomOAuth2UserService.java
package com.gmail.merikbest2015.ecommerce.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.gmail.merikbest2015.ecommerce.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // Use the interface to avoid tight coupling/cycles.
    private final @Lazy AuthenticationService authenticationService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Delegate to the default loader first
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Example: hook into your domain logic if you need to create/sync users.
        // Adjust these lines to your actual methods on AuthenticationService.
        // e.g., authenticationService.ensureOAuthUser(oAuth2User.getAttributes());

        // You can also inspect the registrationId/provider if needed:
        // String registrationId = userRequest.getClientRegistration().getRegistrationId();

        return oAuth2User; // return the user Spring Security should use
    }
}


