package com.ashutosh.journalApp.Controller;


import com.ashutosh.journalApp.Objects.JournalEntry;
import com.ashutosh.journalApp.Objects.User;
import com.ashutosh.journalApp.Service.JournalEntryService;
import com.ashutosh.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Journals")
public class JournalEntryController {
    @Autowired
    JournalEntryService journalService;
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> viewJournals(){
        List<JournalEntry> list = journalService.getEntry();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<?> viewJournalsOfAUser(@PathVariable String userName){
        User newUser = userService.findByUserName(userName);
        List<JournalEntry> list = newUser.getJournalEntries();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{userName}")
    public ResponseEntity<?> addNewJournal(@RequestBody JournalEntry Entry, @PathVariable String userName){
        try {
            if(journalService.saveEntry(Entry,userName))return new ResponseEntity<>("No such user found. ",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(Entry,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable String id, @RequestBody JournalEntry entry){
        return journalService.updateJournal(id,entry);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id) {
//        Optional<JournalEntry> entry = Optional.ofNullable(journalService.getEntryById(id).orElse(null));
//        return entry.isPresent()? new ResponseEntity<>(entry.get(),HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable String id){
        journalService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> clearAll(){
        journalService.clearAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



