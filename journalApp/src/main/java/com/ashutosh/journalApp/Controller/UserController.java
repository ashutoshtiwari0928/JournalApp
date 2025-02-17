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
    public ResponseEntity<?> getAllUsers(){
        List<User> list = service.getAll();
        if(list!=null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        try{
            service.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    @DeleteMapping
    public ResponseEntity<?> clearAll(){
        service.clearALl();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



