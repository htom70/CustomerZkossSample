package hu.userrendszerhaz.business;

import hu.userrendszerhaz.dao.DegreeDao;
import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.domain.Degree;

import java.util.List;

public class DegreeServiceImpl implements DegreeService{

    private DegreeDao degreeDao;

    public DegreeServiceImpl() {
        degreeDao = new DegreeDao();
    }

    @Override
    public void createDegree(Degree degree) {
        degreeDao.saveDegree(degree);
    }

    @Override
    public Degree findDegreeById(Long Id) {
        return degreeDao.findDegreeById(Id);
    }

    @Override
    public List<Degree> findAllDegrees() {
        return degreeDao.getAllDegrees();
    }
}
