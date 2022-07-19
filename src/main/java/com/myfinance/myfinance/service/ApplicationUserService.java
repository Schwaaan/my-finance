package com.myfinance.myfinance.service;


import com.myfinance.myfinance.domain.ApplicationUser;

import java.util.List;

public interface ApplicationUserService {
    ApplicationUser create(ApplicationUser applicationUser);

    List<ApplicationUser> findAll();

}
