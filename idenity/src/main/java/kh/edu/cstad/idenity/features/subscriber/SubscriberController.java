package kh.edu.cstad.idenity.features.subscriber;

import kh.edu.cstad.idenity.base.BasedMessage;
import kh.edu.cstad.idenity.features.subscriber.dto.SubscriberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscribers")
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriberService subscriberService;

    @GetMapping
    Page<SubscriberResponse> findList(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                      @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return subscriberService.findList(pageNumber, pageSize);
    }

    @GetMapping("/{username}")
    SubscriberResponse findByUsername(@PathVariable String username) {
        return subscriberService.findByUsername(username);
    }

    @GetMapping("/{username}/trashed")
    SubscriberResponse findByUsernameTrashed(@PathVariable String username) {
        return subscriberService.findByUsernameTrashed(username);
    }

    @PutMapping("/{username}/disable")
    BasedMessage disable(@PathVariable String username) {
        return new BasedMessage(subscriberService.disable(username));
    }

    @PutMapping("/{username}/enable")
    BasedMessage enable(@PathVariable String username) {
        return new BasedMessage(subscriberService.enable(username));
    }
    
}
