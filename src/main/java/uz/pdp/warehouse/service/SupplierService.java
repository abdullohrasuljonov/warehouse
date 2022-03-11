package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier){
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exist!",false);
        Supplier supplier1=new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Successfully added!",true);
    }

    public List<Supplier> all(){
        return supplierRepository.findAll();
    }

    public Supplier getById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.orElseGet(Supplier::new);
    }

    public Result editSupplier(Integer id,Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found!",false);
        Supplier supplier1 = optionalSupplier.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new Result("Successfully edited!",true);
    }

    public Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found!",false);
        supplierRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}
