package com.myfinance.myfinance.service.impl;

import com.myfinance.myfinance.domain.ApplicationUser;
import com.myfinance.myfinance.repository.ApplicationUserRepository;
import com.myfinance.myfinance.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public ApplicationUser create(ApplicationUser applicationUser) {
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public List<ApplicationUser> findAll() {
        return applicationUserRepository.findAll();
    }
}
