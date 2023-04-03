package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Role;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createnewrole"})
    public ResponseEntity<StandardResponse> createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @GetMapping("/getallroles")
    public ResponseEntity<StandardResponse> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/updaterole")
    public ResponseEntity<StandardResponse> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
    @PostMapping("/assignroletouser")
    public ResponseEntity<StandardResponse> assignRoleToUser(@RequestParam("roleName") String roleName, @RequestParam("userId") Long userId){
        return roleService.assignRoleToUser(roleName, userId);
    }
    @GetMapping("/getrole")
    public ResponseEntity<StandardResponse> getRole(@RequestParam("id") Long id){
        return roleService.getRole(id);
    }
    @DeleteMapping("/deleterole")
    public ResponseEntity<StandardResponse> deleteRole(@RequestParam("id") Long id){
        return roleService.deleteRole(id);
    }
    @DeleteMapping("/deleteallroles")
    public ResponseEntity<StandardResponse> deleteAllRoles(){
        return roleService.deleteAllRoles();
    }

}
