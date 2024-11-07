package kh.edu.cstad.idenity.features.businessowner;

import kh.edu.cstad.idenity.base.BasedMessage;
import kh.edu.cstad.idenity.features.businessowner.dto.BusinessOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/business-owners")
@RequiredArgsConstructor
public class BusinessOwnerController {

    private final BusinessOwnerService businessOwnerService;

    @GetMapping
    Page<BusinessOwnerResponse> findList(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                         @RequestParam(required = false, defaultValue = "25") int pageSize) {
        return businessOwnerService.findList(pageNumber, pageSize);
    }

    @GetMapping("/{username}")
    BusinessOwnerResponse findByUsername(@PathVariable String username) {
        return businessOwnerService.findByUsername(username);
    }

    @GetMapping("/{username}/trashed")
    BusinessOwnerResponse findByUsernameTrashed(@PathVariable String username) {
        return businessOwnerService.findByUsernameTrashed(username);
    }

    @PutMapping("/{username}/disable")
    BasedMessage disable(@PathVariable String username) {
        return new BasedMessage(businessOwnerService.disable(username));
    }

    @PutMapping("/{username}/enable")
    BasedMessage enable(@PathVariable String username) {
        return new BasedMessage(businessOwnerService.enable(username));
    }

}
