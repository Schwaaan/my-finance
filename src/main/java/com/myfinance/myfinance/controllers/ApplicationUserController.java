package com.myfinance.myfinance.controllers;


import com.myfinance.myfinance.domain.ApplicationUser;
import com.myfinance.myfinance.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @PostMapping
    private ApplicationUser create(@RequestBody @Valid ApplicationUser applicationUser) {
        return applicationUserService.create(applicationUser);
    }

    @GetMapping
    private List<ApplicationUser> getUsers() {
        return applicationUserService.findAll();
    }
}
