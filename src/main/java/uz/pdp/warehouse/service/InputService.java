package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.Supplier;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.InputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.SupplierRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    UserService userService;

    public Result addInput(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found!", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found!", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found!", false);
        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(userService.code());
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        inputRepository.save(input);
        return new Result("Successfully added!",true);
    }

    public List<Input> all(){
        return inputRepository.findAll();
    }

    public Input getById(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.orElseGet(Input::new);
    }

    public Result editInput(Integer id,InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("There is no input such an id!",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found!", false);
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Supplier not found!", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found!", false);
        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(userService.code());
        input.setWarehouse(optionalWarehouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        inputRepository.save(input);
        return new Result("Successfully edited!",true);
    }

    public Result deleteInput(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("There is no input such an id!",false);
        inputRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}
