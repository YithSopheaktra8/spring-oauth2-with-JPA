package co.istad.springoauth.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/public")
    public Map<String, String> publicApi(){
        return Map.of("message", "Public API");
    }

    @GetMapping("/private")
    public Map<String,String> privateApi(){
        return Map.of("message", "Private API");
    }

}
