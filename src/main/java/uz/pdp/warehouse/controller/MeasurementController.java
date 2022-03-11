package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.MeasurementService;

import java.util.Set;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurement(measurement);
    }

    @GetMapping
    public Set<Measurement> all(){
       return measurementService.allMeasurement();
    }

    @GetMapping("/{id}")
    public Measurement getById(@PathVariable Integer id){
        return measurementService.getById(id);
    }

    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id,Measurement measurement){
        return measurementService.editMeasurement(id,measurement);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurement(id);
    }

}
