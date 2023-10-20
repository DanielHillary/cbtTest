package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.Pojo.LoginRequest;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService jwtService;

    @PostMapping({"/login"})
    public ResponseEntity<StandardResponse> createJwtToken(@RequestBody LoginRequest login) throws Exception {
        return StandardResponse.sendHttpResponse(true, "Successful Operation",
                jwtService.createJwtToken(login), "200");
    }
}
