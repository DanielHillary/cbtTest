package com.priestdev.chumag.controller;

import com.priestdev.chumag.entity.Members;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/members")
@RestController
public class MembersController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/registermember")
    public ResponseEntity<StandardResponse> registerNewMember(@RequestBody Members members){
        return memberService.registerNewMember(members);
    }

    @GetMapping("/getmember")
    public ResponseEntity<StandardResponse> getMember(@RequestParam("id") Long id){
        return memberService.getMember(id);
    }

    @GetMapping("/getallmembers")
    public ResponseEntity<StandardResponse> getAllMembers(){
        return memberService.getAllMembers();
    }

    @PutMapping("/updatememberinfo")
    public ResponseEntity<StandardResponse> updateMemberInfo(@RequestBody Members members){
        return memberService.updateMemberInfo(members);
    }

    @DeleteMapping("/deletemember")
    public ResponseEntity<StandardResponse> deleteMember(@RequestParam("id") Long id){
        return memberService.deleteMember(id);
    }

    @DeleteMapping("/deleteallmembers")
    public ResponseEntity<StandardResponse> deleteAllMembers(){
        return memberService.deleteAllMembers();
    }
}
