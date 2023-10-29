package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.Members;
import com.priestdev.chumag.entity.StandardResponse;
import com.priestdev.chumag.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MembersRepository membersRepository;

    public ResponseEntity<StandardResponse> registerNewMember(Members members) {
        try {
            Optional<Members> newMember = membersRepository.findByPhoneNumber(members.getPhoneNumber());
            if(newMember.isPresent()){
                return StandardResponse.sendHttpResponse(false, "Member already exists in records");
            }else{
                return StandardResponse.sendHttpResponse(true, "Successful", membersRepository.save(members));
            }
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not register member");
        }
    }

    public ResponseEntity<StandardResponse> getMember(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", membersRepository.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> getAllMembers() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", membersRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false,  "Could not retrieve all  members");
        }
    }

    public ResponseEntity<StandardResponse> updateMemberInfo(Members members) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", membersRepository.save(members));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update member info");
        }
    }

    public ResponseEntity<StandardResponse> deleteMember(Long id) {
        try {
            membersRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllMembers() {
        try {
            membersRepository.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Something went wrong");
        }
    }
}
