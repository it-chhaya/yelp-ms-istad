package kh.edu.cstad.business.feature.category;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    Map<String, String> findAll(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getTokenValue());
        return Map.of(
                "name", "Technology"
        );
    }

}
