package com.ashutosh.journalApp.Service;

import com.ashutosh.journalApp.Objects.JournalEntry;
import com.ashutosh.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo repo;

    public void saveEntry(JournalEntry entry) {
        repo.save(entry);
    }

    public List<JournalEntry> getEntry() {
        return repo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id){
        return repo.findById(id);
    }

    public void deleteById(ObjectId id) {
        repo.deleteById(id);
    }

    public ResponseEntity<JournalEntry> updateJournal(ObjectId id, JournalEntry newEntry) {
        JournalEntry old = this.getEntryById(id).orElse(null);
        if(old!=null){
            if (newEntry.getTitle()!=null && (!newEntry.getTitle().equals(""))){
                old.setTitle(newEntry.getTitle());
            }
            if(newEntry.getContent()!=null && (!newEntry.getContent().equals(""))){
                old.setContent(newEntry.getContent());
            }
            repo.save(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
