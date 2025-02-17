package com.ashutosh.journalApp.Service;

import com.ashutosh.journalApp.DTO.UserDTO;
import com.ashutosh.journalApp.Objects.JournalEntry;
import com.ashutosh.journalApp.Objects.User;
import com.ashutosh.journalApp.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo repo;
    @Autowired
    UserService userService;

    public boolean saveEntry(@NotNull JournalEntry entry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            if(user==null)return true;
            UserDTO userDTO = userService.mapTo(user);
            entry.setUserDTO(userDTO);
            entry.setDate(LocalDateTime.now());
            user.getJournalEntries().add(entry);
            repo.save(entry);
            userService.saveUser(user);
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<JournalEntry> getEntry() {
        return repo.findAll();
    }

    public Optional<JournalEntry> getEntryById(String id){
        return repo.findById(id);
    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }

    public ResponseEntity<JournalEntry> updateJournal(String id, JournalEntry newEntry) {
        JournalEntry old = this.getEntryById(id).orElse(null);
        if (old != null) {
            if (newEntry.getTitle() != null && (!newEntry.getTitle().equals(""))) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && (!newEntry.getContent().equals(""))) {
                old.setContent(newEntry.getContent());
            }
            repo.save(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void clearAll() {
        repo.deleteAll();
    }
}
