//28-01-2026 TharushiCS - TypeB Digital - HelloWorld Application

package com.tharushisonnadara.helloworld.controller;

import com.tharushisonnadara.helloworld.dto.ErrorResponse;
import com.tharushisonnadara.helloworld.dto.HelloResponse;
import com.tharushisonnadara.helloworld.service.HelloWorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    /**
     * GET /hello-world endpoint.
     *
     * @param name the name parameter (optional)
     * @return HTTP 200 with greeting if name starts with A-M,
     *         HTTP 400 with error otherwise
     */
    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(
            @RequestParam(required = false) String name) {

        if (!helloWorldService.isValidName(name)) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Invalid Input"));
        }

        String formattedName = helloWorldService.formatName(name);
        return ResponseEntity.ok(new HelloResponse("Hello " + formattedName));
    }
}
