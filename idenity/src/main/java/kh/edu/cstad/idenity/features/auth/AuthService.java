package kh.edu.cstad.idenity.features.auth;

import kh.edu.cstad.idenity.features.auth.dto.*;
import kh.edu.cstad.idenity.features.user.dto.UserBasicInfoRequest;
import kh.edu.cstad.idenity.features.user.dto.UserCoverImageResponse;
import kh.edu.cstad.idenity.features.user.dto.UserProfileImageResponse;
import kh.edu.cstad.idenity.features.user.dto.UserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

	UserResponse registerSubscriber(RegisterSubscriberRequest registerSubscriberRequest);

	UserResponse registerBusinessOwner(RegisterBusinessOwnerRequest registerBusinessOwnerRequest);

	UserResponse register(RegisterRequest registerRequest);

	UserResponse findMe(Authentication authentication);

	UserResponse updateMeBasicInfo(Authentication authentication, UserBasicInfoRequest userBasicInfoRequest);

	void changePassword(Authentication authentication, ChangePasswordRequest changePasswordRequest);

	void isNotAuthenticated(Authentication authentication);

}
