package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("Warehouse name already exist!",false);
        warehouseRepository.save(warehouse);
        return new Result("Successfully added!",true);
    }

    public Set<Warehouse> allWarehouse(){
        List<Warehouse> all = warehouseRepository.findAll();
        return new HashSet<>(all);
    }

    public Warehouse getById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.orElseGet(Warehouse::new);
    }

    public Result editWarehouse(Integer id,Warehouse warehouse){
        if (warehouseRepository.existsByName(warehouse.getName()))
            return new Result("Warehouse name already exist!",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("There is no Warehouse such an id",false);
        Warehouse warehouse1 = optionalWarehouse.get();
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse);
        return new Result("Successfully edited!",true);
    }

    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result("There is no Warehouse such an id",false);
        warehouseRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }

}
