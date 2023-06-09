package com.unla.primerproyecto.repositories;

import com.unla.primerproyecto.entities.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("degreeRepository")
public interface IDegreeRepository extends JpaRepository<Degree, Serializable> {
    public abstract Degree findByName(String name);
    public abstract Degree findByInstitutionAndYear(String institution, int year);
    public abstract List<Degree> findByInstitutionAndYearOrderByYearDesc(String institution, int year);
}
