package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.CurrencyRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("Currency already exist!",false);
        currencyRepository.save(currency);
        return new Result("Successfully added!",true);
    }

    public Set<Currency> allCurrency(){
        List<Currency> all = currencyRepository.findAll();
        return new HashSet<>(all);
    }

    public Currency getById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.orElseGet(Currency::new);
    }

    public Result editCurrency(Integer id,Currency currency){
        if (currencyRepository.existsByName(currency.getName()))
            return new Result("Currency already exist!",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("There is no Currency such an id",false);
        Currency currency1 = optionalCurrency.get();
        currency1.setName(currency.getName());
        currency1.setActive(currency1.isActive());
        currencyRepository.save(currency1);
        return new Result("Successfully edited!",true);
    }

    public Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("There is no Currency such an id",false);
        currencyRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }

}
