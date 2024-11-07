package kh.edu.cstad.idenity.features.emailverification.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailResendTokenRequest(
        @NotBlank(message = "Username is required")
        String username
) {
}
