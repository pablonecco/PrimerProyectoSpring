package com.unla.primerproyecto.services;

import com.unla.primerproyecto.entities.Degree;
import com.unla.primerproyecto.models.DegreeModel;

import java.util.List;

public interface IDegreeService {
    public List<Degree> getAll();
    public DegreeModel insertOrUpdate(DegreeModel degreeModel);
    public boolean remove (int id);
}
