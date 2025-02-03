package com.ashutosh.journalApp.Controller;


import com.ashutosh.journalApp.Objects.JournalEntry;
import com.ashutosh.journalApp.Service.JournalEntryService;
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
    JournalEntryService service;

    @GetMapping
    public ResponseEntity<?> viewJournals(){
        List<JournalEntry> list = service.getEntry();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<JournalEntry> addNewJournal(@RequestBody JournalEntry Entry){
        try {
            Entry.setDate(LocalDateTime.now());
            service.saveEntry(Entry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
        return service.updateJournal(id,entry);
    }
    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id) {
        Optional<JournalEntry> entry = Optional.ofNullable(service.getEntryById(id).orElse(null));
        return entry.isPresent()? new ResponseEntity<>(entry.get(),HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable ObjectId id){
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



