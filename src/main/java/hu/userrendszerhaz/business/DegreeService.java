package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.domain.Degree;

import java.util.List;

public interface DegreeService {

    public void createDegree(Degree degree);

    public Degree findDegreeById(Long Id);

    public List<Degree> findAllDegrees();
}
