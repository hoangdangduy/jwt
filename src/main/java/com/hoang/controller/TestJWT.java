package com.hoang.controller;

import com.hoang.util.AppConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class TestJWT {

    @RequestMapping(value = "/check-jwt", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<?> getListBankAccountsByPortfolioCode(@RequestHeader(AppConstant.AUTHORIZATION_HEADER) String token) {
//        System.out.println(token);
        return ResponseEntity.ok(true);
    }
}
