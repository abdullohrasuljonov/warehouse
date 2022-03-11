package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.WarehouseService;

import java.util.Set;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(Warehouse warehouse){return warehouseService.addWarehouse(warehouse);}

    @GetMapping
    public Set<Warehouse> all(){
        return warehouseService.allWarehouse();
    }

    @GetMapping("/{id}")
    public Warehouse getById(@PathVariable Integer id){
        return warehouseService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id,Warehouse warehouse){
        return warehouseService.editWarehouse(id,warehouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouse(id);
    }
}
