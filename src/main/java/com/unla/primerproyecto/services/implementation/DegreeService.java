package com.unla.primerproyecto.services.implementation;

import com.unla.primerproyecto.entities.Degree;
import com.unla.primerproyecto.models.DegreeModel;
import com.unla.primerproyecto.repositories.IDegreeRepository;
import com.unla.primerproyecto.services.IDegreeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("degreeService")
public class DegreeService implements IDegreeService {
    @Autowired
    @Qualifier("degreeRepository")
    private IDegreeRepository degreeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Degree> getAll(){
        return degreeRepository.findAll();
    }

    @Override
    public DegreeModel insertOrUpdate (DegreeModel degreeModel) {
        Degree degree = degreeRepository.save(modelMapper.map(degreeModel, Degree.class));
        return modelMapper.map(degree, DegreeModel.class);
    }

    @Override
    public boolean remove (int id){
        try{
            degreeRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
