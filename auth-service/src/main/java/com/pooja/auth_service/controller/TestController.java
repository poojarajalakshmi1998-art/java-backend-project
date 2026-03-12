package com.pooja.auth_service.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class TestController {

    @GetMapping("/test")
    public String test(@RequestHeader("X-User-Name") String name) {
/*After adding API gateway removed the below and using name set in header
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = auth.getName();
*/


        return "Hello " + name;
    }
}
