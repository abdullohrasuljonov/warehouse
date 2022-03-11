package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> all(){
        return supplierService.all();
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Integer id){
        return supplierService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.editSupplier(id,supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }
}
