package kh.edu.cstad.idenity.features.subscriber;

import kh.edu.cstad.idenity.domain.Subscriber;
import kh.edu.cstad.idenity.domain.User;
import kh.edu.cstad.idenity.features.subscriber.dto.SubscriberResponse;
import kh.edu.cstad.idenity.features.user.UserRepository;
import kh.edu.cstad.idenity.features.subscriber.SubscriberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriberServiceImpl implements SubscriberService {

    private final UserRepository userRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscriberMapper subscriberMapper;

    @Override
    public Page<SubscriberResponse> findList(int pageNumber, int pageSize) {

        log.info("Page<SubscriberResponse> findList(int pageNumber={}, int pageSize={})", pageNumber, pageSize);

        Sort sortByCreatedDate = Sort.by(Sort.Direction.DESC, "createdDate");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortByCreatedDate);

        Page<Subscriber> subscribers = subscriberRepository.findAll(pageRequest);

        return subscribers.map(subscriberMapper::toSubscriberResponse);
    }

    @Override
    public SubscriberResponse findByUsername(String username) {

        log.info("SubscriberResponse findByUsername(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User has not been found"));

        Subscriber subscriber = subscriberRepository
                .findByUserIdAndUserIsEnabledTrue(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber has not been found"));

        return subscriberMapper.toSubscriberResponse(subscriber);
    }

    @Override
    public SubscriberResponse findByUsernameTrashed(String username) {

        log.info("SubscriberResponse findByUsernameTrashed(String username={})", username);

        User user = userRepository
                .findByUsernameAndIsEnabledFalse(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User has not been found"));

        Subscriber subscriber = subscriberRepository
                .findByUserIdAndUserIsEnabledFalse(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber has not been found"));

        return subscriberMapper.toSubscriberResponse(subscriber);
    }

    @Override
    public String disable(String username) {

        log.info("String disable(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber has not been found"));

        user.setIsEnabled(false);
        userRepository.save(user);

        return String.format("Subscriber: %s has been disabled", username);
    }

    @Override
    public String enable(String username) {

        log.info("String enable(String username={})", username);

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber has not been found"));

        user.setIsEnabled(true);
        userRepository.save(user);

        return String.format("Subscriber: %s has been enabled", username);
    }
}
