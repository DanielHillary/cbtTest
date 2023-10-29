package com.priestdev.chumag.service;

import com.priestdev.chumag.entity.*;
import com.priestdev.chumag.entity.Pojo.CombinedMembers;
import com.priestdev.chumag.repository.FirstTimerRepo;
import com.priestdev.chumag.repository.MembersRepository;
import com.priestdev.chumag.repository.NewConvertRepo;
import com.priestdev.chumag.repository.SecondTimerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private FirstTimerRepo firstTimerRepo;
    @Autowired
    private SecondTimerRepo secondTimerRepo;
    @Autowired
    private NewConvertRepo newConvertRepo;

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

    public ResponseEntity<StandardResponse> getAllCombinedMembers() {
        try {
            List<NewConverts> allNewConverts = newConvertRepo.findAll();
            List<FirstTimer> allFirstTimers = firstTimerRepo.findAll();
            List<SecondTimer> allSecondTimers = secondTimerRepo.findAll();

            CombinedMembers combinedMembers = new CombinedMembers();

            combinedMembers.setId(combinedMembers.getId() == 0 ? 1 : combinedMembers.getId() + 1);
            combinedMembers.setAllFirstTimers(allFirstTimers);
            combinedMembers.setAllNewConverts(allNewConverts);
            combinedMembers.setAllSecondTimers(allSecondTimers);

            return StandardResponse.sendHttpResponse(true, "Successful", combinedMembers);
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
