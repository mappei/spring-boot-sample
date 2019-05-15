package com.mpp.web.controller;

import com.mpp.web.domain.User;
import com.mpp.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * spring mvn
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User findOne(@RequestParam Integer id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/save", method = { RequestMethod.POST })
    public User save(@RequestParam String name){
        User user = new User(name);
        if (userRepository.save(user)){
            System.out.println("save successful" + user);
        }
        return user;
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public User update(@RequestBody User qw) {
        User newUser = null;
        if (userRepository.update(qw)) {
            newUser = userRepository.get(qw.getId());
            System.out.println("modify successful" + newUser);
        }
        return newUser;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public User delete(@RequestParam Integer id) {
        User user = userRepository.findOne(id);
        if (userRepository.delete(id)) {
            System.out.println("delete successful " + user);
        }
        return user;
    }

    @RequestMapping
    public String test(){
        return "Hello world!";
    }

}
