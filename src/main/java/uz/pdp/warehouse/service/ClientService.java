package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.ClientRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client){
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("Phone number already exist!",false);
        clientRepository.save(client);
        return new Result("Successfully added!",true);
    }

    public Set<Client> allClient(){
        List<Client> all = clientRepository.findAll();
        return new HashSet<>(all);
    }

    public Client getById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElseGet(Client::new);
    }

    public Result editClient(Integer id,Client client){
        if (clientRepository.existsByPhoneNumber(client.getPhoneNumber()))
            return new Result("Phone number already exist!",false);
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("There is no client such an id",false);
        Client client1 = optionalClient.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client);
        return new Result("Successfully edited!",true);
    }

    public Result deleteClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent())
            return new Result("There is no client such an id",false);
        clientRepository.deleteById(id);
        return new Result("Successfully deleted!",true);
    }
}
