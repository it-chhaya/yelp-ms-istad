package kh.edu.cstad.idenity.features.user;

import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.features.auth.dto.*;
import kh.edu.cstad.idenity.features.user.dto.UserBasicInfoRequest;
import kh.edu.cstad.idenity.features.user.dto.UserCreateRequest;
import kh.edu.cstad.idenity.features.user.dto.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    User fromUserCreationRequest(UserCreateRequest userCreateRequest);

    User fromUserBasicInfoRequest(UserBasicInfoRequest userBasicInfoRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void mapUserBasicInfoRequestToUser(UserBasicInfoRequest userBasicInfoRequest, @MappingTarget User user);

    UserCreateRequest mapRegisterRequestToUserCreationRequest(RegisterRequest registerRequest);

    UserCreateRequest mapRegisterBusinessOwnerRequestToUserCreationRequest(RegisterBusinessOwnerRequest registerBusinessOwnerRequest);

    UserCreateRequest mapRegisterSubscriberRequestToUserCreationRequest(RegisterSubscriberRequest registerSubscriberRequest);

}
