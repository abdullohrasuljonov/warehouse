package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @GetMapping
    public List<OutputProduct> all(){
        return outputProductService.all();
    }

    @GetMapping("/{id}")
    public OutputProduct getById(@PathVariable Integer id){
        return outputProductService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id,outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        return outputProductService.deleteOutputProduct(id);
    }
}
