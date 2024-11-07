package kh.edu.cstad.idenity.features.emailverification;

import kh.edu.cstad.idenity.domain.EmailVerificationToken;
import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.features.emailverification.dto.EmailVerifyRequest;

public interface EmailVerificationTokenService {

    void verify(EmailVerifyRequest emailVerifyRequest);

    boolean isUsersToken(EmailVerificationToken token, User user);

    boolean isTokenInDb(EmailVerificationToken token, String tokenToVerify);

    void generate(User user);

    boolean isExpired(EmailVerificationToken token);

    void resend(String username);

}
