package com.grouptwelve.grouptwelveBE.security;

import com.grouptwelve.grouptwelveBE.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        
        // Extract GitHub user information
        Long githubId = oAuth2User.getAttribute("id");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String login = oAuth2User.getAttribute("login"); // GitHub username
        
        // Use login as name if name is null
        if (name == null || name.isEmpty()) {
            name = login;
        }
        
        // TODO: Save or update user in database here
        // Example:
        // User user = userRepository.findByGithubId(githubId)
        //     .orElseGet(() -> {
        //         User newUser = new User();
        //         newUser.setGithubId(githubId);
        //         newUser.setEmail(email);
        //         newUser.setName(name);
        //         newUser.setUsername(login);
        //         return userRepository.save(newUser);
        //     });
        
        // Generate JWT token (convert githubId to String)
        String jwtToken = jwtUtil.createToken(String.valueOf(githubId), email, name);
        
        // Redirect to frontend with JWT token
        String frontendUrl = "http://localhost:3000/auth/callback?token=" + jwtToken;
        
        response.sendRedirect(frontendUrl);
    }
}
