package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping
    public List<InputProduct> all(){
        return inputProductService.all();
    }

    @GetMapping("/{id}")
    public InputProduct getById(@PathVariable Integer id){
        return inputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(id,inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }
}
