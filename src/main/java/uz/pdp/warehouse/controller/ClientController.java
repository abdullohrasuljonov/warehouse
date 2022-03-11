package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;
import java.util.Set;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClient(@RequestBody Client client){
       return clientService.addClient(client);
    }

    @GetMapping
    public Set<Client> all(){
        return clientService.allClient();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id,Client client){
        return clientService.editClient(id,client);
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }
}
