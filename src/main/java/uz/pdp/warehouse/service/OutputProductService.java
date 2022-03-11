package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Output;
import uz.pdp.warehouse.entity.OutputProduct;
import uz.pdp.warehouse.entity.Product;
import uz.pdp.warehouse.payload.OutputProductDto;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.OutputProductRepository;
import uz.pdp.warehouse.repository.OutputRepository;
import uz.pdp.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDto outputProductDto){
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found!",false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output not found!",false);
        OutputProduct outputProduct=new OutputProduct();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Successfully added!",true);
    }

    public List<OutputProduct> all(){
        return outputProductRepository.findAll();
    }

    public OutputProduct getById(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return optionalOutputProduct.orElseGet(OutputProduct::new);
    }

    public Result editOutputProduct(Integer id,OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("There is no output product such an id!",false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found!",false);
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("Output not found!",false);
        OutputProduct outputProduct=optionalOutputProduct.get();
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Successfully edited!",true);
    }

    public Result deleteOutputProduct(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("There is no output product such an id!",false);
        outputProductRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}
