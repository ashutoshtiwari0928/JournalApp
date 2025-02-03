package com.ashutosh.journalApp.Controller;


import com.ashutosh.journalApp.Objects.User;
import com.ashutosh.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping
    public List<User> getAllUsers(){
        return service.getAll();
    }
    @PostMapping
    public void addNewUser(@RequestBody User user){
        service.saveUser(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser,@PathVariable String userName){
        User old = service.findByUserName(userName);
        if(old!=null){
            old.setUsername(newUser.getUsername());
            old.setPassword(newUser.getPassword());
            service.saveUser(old);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable ObjectId id){
        service.deleteById(id);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable ObjectId id){
        Optional<User> user=service.getUserById(id);
        return user.orElse(null);
    }

}



