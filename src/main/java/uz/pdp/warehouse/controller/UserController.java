package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(User user){return userService.addUser(user);}
    @GetMapping
    public Set<User> all(){
        return userService.allUser();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id,User user){
        return userService.editUser(id,user);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }
}
