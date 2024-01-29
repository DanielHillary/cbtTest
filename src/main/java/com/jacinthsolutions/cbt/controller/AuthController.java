package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.Pojo.LoginRequest;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.service.AuthService;
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
