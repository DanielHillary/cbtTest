package com.jacinthsolutions.cbt.controller;

import com.jacinthsolutions.cbt.entity.AdminUser;
import com.jacinthsolutions.cbt.entity.StandardResponse;
import com.jacinthsolutions.cbt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping({"/registerAdmin"})
    public ResponseEntity<StandardResponse> registerNewAdmin(@RequestBody AdminUser user) {
        return adminService.registerNewAdmin(user);
    }

    @GetMapping("/getalladmins")
    public ResponseEntity<StandardResponse> getAllAdmins(){
        return adminService.getAllAdmins();
    }
    @PutMapping("/updateadmin")
    public ResponseEntity<StandardResponse> updateAdmin(@RequestBody AdminUser admin){
        return adminService.updateAdmin(admin);
    }
    @DeleteMapping("/deleteadmin")
    public ResponseEntity<StandardResponse> deleteAdmin(@RequestParam("id") Long id){
        return adminService.deleteAdmin(id);
    }
}