package kh.edu.cstad.business.feature.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @GetMapping
    CategoryResponse findAll() {
        //System.out.println(jwt.getTokenValue());
        System.out.println("Hello");
        return new CategoryResponse("Technology");
    }

}
