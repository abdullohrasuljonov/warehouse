package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Input;
import uz.pdp.warehouse.entity.InputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.InputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.InputProductRepository;
import uz.pdp.warehouse.repository.InputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public Result addInputProduct(InputProductDto inputProductDto){
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input not found!",false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found!",false);
        InputProduct inputProduct=new InputProduct();
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully added!",true);
    }

    public List<InputProduct> all(){
        return inputProductRepository.findAll();
    }

    public InputProduct getById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElseGet(InputProduct::new);
    }

    public Result editInputProduct(Integer id,InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("There is no input product such an id!",false);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Input not found!",false);
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found!",false);
        InputProduct inputProduct=optionalInputProduct.get();
        inputProduct.setInput(optionalInput.get());
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Successfully edited!",true);
    }

    public Result deleteInputProduct(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("There is no input product such an id!",false);
        inputProductRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }

}
