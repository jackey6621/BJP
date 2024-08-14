package com.revature.P1.controller;

import com.revature.P1.model.Reimb;
import com.revature.P1.repo.ReimbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReimbController {

    @Autowired
    ReimbRepository reimbRepository;

    @GetMapping("/getAllReimb")
    public ResponseEntity<List<Reimb>> getAllReimb() {
        try {
            List<Reimb> reimbList = new ArrayList<>();
            reimbRepository.findAll().forEach(reimbList::add);

            if (reimbList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reimbList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getReimbByStatus/{status}")
    public ResponseEntity<List<Reimb>> getReimbByStatus(@PathVariable String status) {
        List<Reimb> reimbList = reimbRepository.findByStatus(status);
        if (!reimbList.isEmpty()) {
            return new ResponseEntity<>(reimbList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getReimbByUserid/{userid}")
    public ResponseEntity<List<Reimb>> getReimbByUserid(@PathVariable int userid) {
        List<Reimb> reimbList = reimbRepository.findByUserid(userid);
        //reimbList = reimbRepository.findByStatus("PENDING");
        if (!reimbList.isEmpty()) {
            return new ResponseEntity<>(reimbList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getReimbByUseridandstatus/{userid}/{status}")
    public ResponseEntity<List<Reimb>> getReimbByUseridAndStatus(@PathVariable int userid, @PathVariable String status) {
        List<Reimb> reimbList = reimbRepository.findByUseridAndStatus(userid, status);
        if (!reimbList.isEmpty()) {
            return new ResponseEntity<>(reimbList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addReimb")
    public ResponseEntity<Reimb> addReimb(@RequestBody Reimb reimb) {
        try {
            Reimb reimbObj = reimbRepository.save(reimb);
            return new ResponseEntity<>(reimbObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateReimb/{id}/{status}")
    public ResponseEntity<Reimb> updateReimb(@PathVariable int id, @PathVariable String status) {
        try {
            Optional<Reimb> reimbData = reimbRepository.findById(id);
            if (reimbData.isPresent()) {
                Reimb updatedReimbData = reimbData.get();
                updatedReimbData.setStatus(status);

                Reimb reimbObj = reimbRepository.save(updatedReimbData);
                return new ResponseEntity<>(reimbObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PostMapping("/updateReimb/{id}")
//    public ResponseEntity<Reimb> updateReimb(@PathVariable Long id, @RequestBody Reimb reimb) {
//        try {
//            Optional<Reimb> reimbData = reimbRepository.findById(id);
//            if (reimbData.isPresent()) {
//                Reimb updatedReimbData = reimbData.get();
//                updatedReimbData.setTitle(reimb.getTitle());
//                updatedReimbData.setAuthor(reimb.getAuthor());
//
//                Reimb reimbObj = reimbRepository.save(updatedReimbData);
//                return new ResponseEntity<>(reimbObj, HttpStatus.CREATED);
//            }
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/deleteReimbById/{id}")
    public ResponseEntity<HttpStatus> deleteReimb(@PathVariable Integer id) {
        try {
            reimbRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllReimb")
    public ResponseEntity<HttpStatus> deleteAllReimb() {
        try {
            reimbRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
