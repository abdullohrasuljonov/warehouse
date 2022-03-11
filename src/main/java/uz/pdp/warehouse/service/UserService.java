package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result addUser(User user){
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            return new Result("Phone number already exist!",false);
        if (userRepository.existsByPassword(user.getPassword()))
            return new Result("Password already exist!",false);
        userRepository.save(user);
        return new Result("Successfully added!",true);
    }

    public Set<User> allUser(){
        List<User> all = userRepository.findAll();
        return new HashSet<>(all);
    }

    public User getById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseGet(User::new);
    }

    public Result editUser(Integer id,User user){
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            return new Result("Phone number already exist!",false);
        if (userRepository.existsByPassword(user.getPassword()))
            return new Result("Password already exist!",false);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("There is no User such an id",false);
        User user1 = optionalUser.get();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setActive(user.isActive());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setPassword(user.getPassword());
        user1.setCode(code());
        userRepository.save(user1);
        return new Result("Successfully edited!",true);
    }

    public Result deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("There is no user such an id",false);
        userRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }

    public String code(){
        List<User> products = userRepository.findAll();
        if (products.size() == 0)
            return String.valueOf(1);
        int code = products.size() - 1;
        int index = Integer.parseInt(products.get(code).getCode().trim());
        return String.valueOf(++index);
    }
}
