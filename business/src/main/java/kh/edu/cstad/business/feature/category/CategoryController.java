package kh.edu.cstad.business.feature.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @GetMapping
    Map<String, String> findAll() {
        return Map.of(
                "name", "Technology"
        );
    }

}
