package com.ashutosh.journalApp.Service;

import com.ashutosh.journalApp.DTO.UserDTO;
import com.ashutosh.journalApp.Objects.JournalEntry;
import com.ashutosh.journalApp.Objects.User;
import com.ashutosh.journalApp.Repository.JournalEntryRepo;
import com.ashutosh.journalApp.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public void saveUser(User entry) {
        repo.save(entry);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return repo.findById(id);
    }

    public void deleteById(ObjectId id) {
        repo.deleteById(id);
    }

    public ResponseEntity<User> updateUser(ObjectId id, User newUser) {
        User old = this.getUserById(id).orElse(null);
        assert old != null;
        old.setUsername(newUser.getUsername());
        old.setPassword(newUser.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    public User findByUserName(String name){
        User user = repo.findByUsername(name);
        if(user!=null)return user;
        return null;
    }
    public UserDTO mapTo(User user){
        UserDTO k = new UserDTO(user.getUsername());
        return k;
    }

    public void clearALl() {
        repo.deleteAll();
    }
}
