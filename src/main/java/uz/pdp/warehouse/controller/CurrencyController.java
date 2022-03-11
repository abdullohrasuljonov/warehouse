package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Currency;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.CurrencyService;

import java.util.Set;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addCurrency(Currency currency){return currencyService.addCurrency(currency);}

    @GetMapping
    public Set<Currency> all(){
        return currencyService.allCurrency();
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        return currencyService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editCurrency(@PathVariable Integer id,Currency currency){
        return currencyService.editCurrency(id,currency);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteCurrency(id);
    }
}
