package com.hoang.controller;

import com.hoang.util.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class TestJWT {

    @RequestMapping(value = "/check-jwt", method = RequestMethod.GET)
    public ResponseEntity<?> getListBankAccountsByPortfolioCode(@RequestHeader(AppConstant.AUTHORIZATION_HEADER) String token) {
//        System.out.println(token);
        return ResponseEntity.ok(true);
    }
}
