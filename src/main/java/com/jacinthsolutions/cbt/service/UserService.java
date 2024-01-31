package com.jacinthsolutions.cbt.service;

import com.jacinthsolutions.cbt.entity.AdminUser;
import com.jacinthsolutions.cbt.entity.Role;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.entity.User;
import com.jacinthsolutions.cbt.repository.AdminRepository;
import com.jacinthsolutions.cbt.repository.RoleRepository;
import com.jacinthsolutions.cbt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NotifyService NService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    public void generateOneTimePassword(String email, User user) throws UnsupportedEncodingException, MessagingException {

        Random random = new Random();

        String OTP = String.format("%04d", random.nextInt(10000));

        BCryptPasswordEncoder otppasswordEncoder = new BCryptPasswordEncoder();
        String encodeOTP = otppasswordEncoder.encode(OTP);
        user.setVerificationOtp(OTP);
        user.setOtpRequestTime(new Date());

        userRepo.save(user);
        logger.info(" Email not Null and OTP is " + user.getEmail() + " Is " + OTP + " Encoded Otp " + encodeOTP);
//        userRepository.sendNotificationByEmail(OTP, new java.util.Date(), email);
        NService.sendNotificationOTP(user, OTP);

    }

    public void generateEmailOneTimePassword(User user) throws UnsupportedEncodingException, MessagingException {

        Random random = new Random();
        String OTP = String.format("%04d", random.nextInt(10000));
        user.setVerificationOtp(passwordEncoder.encode(OTP));
        user.setOtpRequestTime(new Date());
        userRepo.save(user);
        NService.sendNotificationOTP(user, OTP);
    }

    public void initRoleAndUser() {

        if(roleRepo.findByRoleName("Admin").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleRepo.save(adminRole);
            if(userRepo.findByUserName("admin123").isEmpty()){
                User adminUser = new User();
                adminUser.setUserName("admin123");
                adminUser.setPassword(passwordEncoder.encode("admin@pass"));
                adminUser.setFirstName("AdminDan");
                adminUser.setLastName("Hillary");
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                adminUser.setRole(adminRoles);
                userRepo.save(adminUser);

                AdminUser user = new AdminUser();
                user.setUserName("admin123");
                user.setPassword(passwordEncoder.encode("admin@pass"));
                user.setFirstName("AdminDan");
                user.setLastName("Hillary");
                user.setRole(adminRoles);
                adminRepository.save(user);

            }
        }

    }

    public ResponseEntity<StandardResponse> registerNewUser(User user) {
        try {
            Role role = roleRepo.findByRoleName("User").get();
            Set<Role> roles = new HashSet<>();
            roles.add(role);

            Random customerRand = new Random();
            String vOtp = String.format("%04d", customerRand.nextInt(10000));

            boolean loggedUser = userRepo.findByUserName(user.getUserName()).isPresent();
            if(!loggedUser) {
                user.setUserId(String.format("%04d", customerRand.nextInt(10000)));
                user.setVerificationOtp(vOtp);
                user.setRole(roles);
                user.setAccountType("User");
                user.setPassword(passwordEncoder.encode(user.getPassword()));


                NService.sendNotificationOTP(user, "Thank you for signing up. <br /> Use the following OTP to Validate your email <strong> " + vOtp + "</strong>" );
                NService.sendRegistrationNotification(user);


                User savedUser = userRepo.save(user);
                return StandardResponse.sendHttpResponse(true, "Operation successful!", savedUser, "200");
            }else{
                return StandardResponse.sendHttpResponse(false, "User already exists");
            }
        } catch (Exception e) {

            return StandardResponse.sendHttpResponse(false, "Could not save user");
        }
    }



    public ResponseEntity<StandardResponse> resendOtp(String userEmail)
            throws UnsupportedEncodingException, MessagingException {
        User datas = userRepo.findByEmail(userEmail).get();

        StandardResponse sr = new StandardResponse();

        if (!ObjectUtils.isEmpty(datas)) {
//            datas.setEmail(userEmail);
            generateOneTimePassword(userEmail, datas);

            sr.setMessage("Email Successfully Sent");
            sr.setStatus(true);
            sr.setStatuscode("200");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
        } else {
            sr.setMessage("Inavlid Email");
            sr.setStatus(false);
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public ResponseEntity<StandardResponse> forgetpassword(String useremail)
            throws UnsupportedEncodingException, MessagingException {
        User datas = userRepo.findByEmail(useremail).get();

        StandardResponse sr = new StandardResponse();

        if (!ObjectUtils.isEmpty(datas)) {

            // Generate and Send an OTP that would be used for the Password Reset
            generateEmailOneTimePassword(datas);

            sr.setMessage("Email Successfully Sent");
            sr.setStatus(true);
            sr.setStatuscode("200");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
        } else {
            sr.setMessage("Inavlid Email");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public ResponseEntity<StandardResponse> verifyCode(String verificationOtp, String email) {

        User data = userRepo.findByVerificationOtp(verificationOtp).get();
//        User data = userRepository.findByOneTimePassword(oneTimePassword).get();
        StandardResponse sr = new StandardResponse();

        try {
            User userEmail = userRepo.findByUserName(email).get();

            if(!ObjectUtils.isEmpty(data)&&(data.getEmail().equalsIgnoreCase(userEmail.getEmail()))) {

                data.setValidated(true);

                userRepo.save(data);
                System.out.println("Hello ... 4 " + verificationOtp);

                sr.setData(data);
                sr.setMessage("Your email has been verified");
                sr.setStatus(true);
                sr.setStatuscode("200");

                return new ResponseEntity<>(sr, HttpStatus.OK);
            } else {
                sr.setMessage("Could Not verify your email");
                sr.setStatus(false);
                return new ResponseEntity<>(sr, HttpStatus.OK);

            }

        } catch (Exception e) {
            sr.setStatus(false);
            return new ResponseEntity<>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }

    public ResponseEntity<StandardResponse> resetpassword(String password, String email, String otp) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodepassword = passwordEncoder.encode(password);

        StandardResponse sr = new StandardResponse();

        try {
            sr.setMessage("Password Reset Successfull");
            sr.setStatus(true);
            sr.setStatuscode("200");

            User datas = new User() ;
            Optional<User> usr = userRepo.findByEmail(email);
            if(usr.isPresent()){
                datas = usr.get() ;
            }
            if (!datas.getEmail().isEmpty()) {
//                if (datas.getOneTimePassword().equals(otp)){
//                    datas.setUserPassword(encodepassword);
//                    datas.setVerificationOtp(otp);
////                    userRepository.updatePassword(encodepassword, email);
////                    userRepository.upddateVerficationCode(true, otp);
//                } else {
//                    sr.setMessage("OTP Incorrect");
//                    sr.setStatus(false);
//                    // userRepository.setOnetimePa (true, otp);
//
//                }

                datas.setPassword(encodepassword);
//                datas.setVerificationOtp(otp);

                userRepo.save(datas);
                return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);
            } else {
                sr.setMessage("Password Reset Unsuccesfull");
                sr.setStatus(false);
                return new ResponseEntity<StandardResponse>(sr, HttpStatus.OK);

            }

        } catch (Exception e) {
            sr.setStatus(false);
            sr.setMessage("A problem occurred during password reset, please try later");
            return new ResponseEntity<StandardResponse>(sr, HttpStatus.BAD_REQUEST);
        }
        // return null;
    }
}
