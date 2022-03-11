package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.Warehouse;
import uz.pdp.warehouse.payload.OutputDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;
import uz.pdp.warehouse.repository.CurrencyRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    UserService userService;

    public Result addOutput(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found!",false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found!",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found!",false);
        Output output=new Output();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setDate(outputDto.getDate());
        output.setCode(userService.code());
        outputRepository.save(output);
        return new Result("Successfully added!",true);
    }

    public List<Output> all(){
        return outputRepository.findAll();
    }

    public Output getById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.orElseGet(Output::new);
    }

    public Result editOutput(Integer id,OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("There is no output such an id!",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Warehouse not found!",false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Client not found!",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found!",false);
        Output output = optionalOutput.get();
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setDate(outputDto.getDate());
        output.setCode(userService.code());
        outputRepository.save(output);
        return new Result("Successfully edited!!",true);
    }

    public Result deleteOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("There is no output such an id!",false);
        outputRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}











