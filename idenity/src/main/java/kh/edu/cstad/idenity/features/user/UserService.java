package kh.edu.cstad.idenity.features.user;

import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.features.user.dto.UserBasicInfoRequest;
import kh.edu.cstad.idenity.features.user.dto.UserCreateRequest;
import kh.edu.cstad.idenity.features.user.dto.UserPasswordResetResponse;
import kh.edu.cstad.idenity.features.user.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    UserResponse updateBasicInfo(String username, UserBasicInfoRequest userBasicInfoRequest);

    UserPasswordResetResponse resetPassword(String username);

    void enable(String username);

    void disable(String username);

    void createNewSubscriber(UserCreateRequest userCreateRequest);

    void createNewBusinessOwner(UserCreateRequest userCreateRequest);

    void createNewUser(UserCreateRequest userCreateRequest);

    Page<UserResponse> findList(int pageNumber, int pageSize);

    UserResponse findByUsername(String username);

    void checkForPasswords(String password, String confirmPassword);

    void checkTermsAndConditions(String value);

    void existsByUsername(String username);

    void existsByEmail(String email);

    void verifyEmail(User user);

    void checkForOldPassword(String username, String oldPassword);

}
