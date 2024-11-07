package kh.edu.cstad.idenity.features.businessowner;

import kh.edu.cstad.idenity.domain.BusinessOwner;
import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.features.businessowner.dto.BusinessOwnerResponse;
import kh.edu.cstad.idenity.features.user.UserRepository;
import kh.edu.cstad.idenity.features.businessowner.BusinessOwnerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusinessOwnerServiceImpl implements BusinessOwnerService {

    private final UserRepository userRepository;
    private final BusinessOwnerRepository businessOwnerRepository;
    private final BusinessOwnerMapper businessOwnerMapper;

    @Override
    public Page<BusinessOwnerResponse> findList(int pageNumber, int pageSize) {

        log.info("Page<BusinessOwnerResponse> findList(int pageNumber={}, int pageSize={})", pageNumber, pageSize);

        Sort sortByCreatedDate = Sort.by(Sort.Direction.DESC, "createdDate");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortByCreatedDate);

        Page<BusinessOwner> businessOwners = businessOwnerRepository.findAll(pageRequest);

        return businessOwners.map(businessOwnerMapper::toBusinessOwnerResponse);
    }

    @Override
    public BusinessOwnerResponse findByUsername(String username) {

        log.info("BusinessOwnerResponse findByUsername(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User has not been found"));

        BusinessOwner businessOwner = businessOwnerRepository
                .findByUserIdAndUserIsEnabledTrue(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business owner has not been found"));

        return businessOwnerMapper.toBusinessOwnerResponse(businessOwner);
    }

    @Override
    public BusinessOwnerResponse findByUsernameTrashed(String username) {

        log.info("BusinessOwnerResponse findByUsernameTrashed(String username={})", username);

        User user = userRepository
                .findByUsernameAndIsEnabledFalse(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User has not been found"));

        BusinessOwner businessOwner = businessOwnerRepository
                .findByUserIdAndUserIsEnabledFalse(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business owner has not been found"));

        return businessOwnerMapper.toBusinessOwnerResponse(businessOwner);
    }

    @Override
    public String disable(String username) {

        log.info("String disable(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business owner has not been found"));

        user.setIsEnabled(false);
        userRepository.save(user);

        return String.format("Business owner: %s has been disabled", username);
    }

    @Override
    public String enable(String username) {

        log.info("String enable(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Business owner has not been found"));

        user.setIsEnabled(true);
        userRepository.save(user);

        return String.format("Business owner: %s has been enabled", username);
    }
}
