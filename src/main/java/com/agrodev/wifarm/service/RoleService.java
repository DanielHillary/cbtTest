package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Role;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.repository.RoleRepository;
import com.agrodev.wifarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<StandardResponse> createNewRole(Role role) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.save(role));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create role");
        }
    }

    public ResponseEntity<StandardResponse> getAllRoles() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }
    public ResponseEntity<StandardResponse> updateRole(Role role) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.save(role));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }

    public ResponseEntity<StandardResponse> getRole(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", roleRepository.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }

    public ResponseEntity<StandardResponse> deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllRoles() {
        try {
            roleRepository.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all roles");
        }
    }

    public ResponseEntity<StandardResponse> assignRoleToUser(String roleName, Long userId) {
        try {
            Role role = roleRepository.findByRoleName(roleName).get();
            User user = userRepository.findById(userId).get();
            user.getRole().add(role);
            userRepository.save(user);

            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not assign role to user");
        }
    }
}
