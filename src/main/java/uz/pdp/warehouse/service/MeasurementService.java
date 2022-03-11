package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.Measurement;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.MeasurementRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Measurement already exist!",false);
        measurementRepository.save(measurement);
        return new Result("Succesfully added!",true);
    }

    public Set<Measurement> allMeasurement(){
        List<Measurement> all = measurementRepository.findAll();
        return new HashSet<>(all);
    }

    public Measurement getById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElseGet(Measurement::new);
    }

    public Result editMeasurement(Integer id,Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName()))
            return new Result("Measurement already exist!",false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("There is no measurement such an id",false);
        Measurement measurement1 = optionalMeasurement.get();
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Result("Succesfully edited!",true);
    }

    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("There is no measurement such an id",false);
        measurementRepository.deleteById(id);
        return new Result("Succesfully deleted!",true);
    }
}











