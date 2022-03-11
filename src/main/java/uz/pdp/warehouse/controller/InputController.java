package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> all(){
        return inputService.all();
    }

    @GetMapping("/{id}")
    public Input getById(@PathVariable Integer id){
        return inputService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id,@RequestBody InputDto inputDto){
        return inputService.editInput(id,inputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }
}
