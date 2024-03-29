package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.entity.User;
import com.jacinthsolutions.cbt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/registration")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

//    @PostConstruct
//    public void getWatchedPrices(){
//        calculationService.getWatchedPrices();
//    }

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<StandardResponse> registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping("/resendOTP")
    public ResponseEntity<StandardResponse> resendOTP(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        return userService.resendOtp(email);
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<StandardResponse> forgetPassword(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        return userService.forgetpassword(email);
    }

    @PostMapping("/verifycode")
    public ResponseEntity<StandardResponse> verifyCode(@RequestParam("verificationOtp") String verificationOtp, @RequestParam("email") String email){
        return userService.verifyCode(verificationOtp, email);
    }
    @GetMapping("/test")
    public ResponseEntity<String> forTesting(){
        return ResponseEntity.ok("This link is working");
    }

//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin(){
//        return "This URL is only accessible to the admin";
//    }
//
//    @GetMapping({"/forUser"})
//    @PreAuthorize("hasRole('User')")
//    public String forUser(){
//        return "This URL is only accessible to the user";
//    }
}
