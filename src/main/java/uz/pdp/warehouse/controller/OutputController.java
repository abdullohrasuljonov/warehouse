package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    @GetMapping
    public List<Output> all(){
        return outputService.all();
    }

    @GetMapping("/{id}")
    public Output getById(@PathVariable Integer id){
        return outputService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id,@RequestBody OutputDto outputDto){
        return outputService.editOutput(id,outputDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }
}
