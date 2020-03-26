package hu.userrendszerhaz.dao;

import hu.userrendszerhaz.domain.Degree;
import hu.userrendszerhaz.listener.ZKContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class DegreeDao {

    private Session currentSession;
    private Transaction currentTransaction;

    private static Logger LOGGER = (Logger) LogManager.getLogger(CustomerDao.class.getName());

    public DegreeDao() {
        currentSession = ZKContextListener.getHibernateUtil().getSession();
        LOGGER.debug("get Session");
    }

    private void openTransaction() {
        currentTransaction = currentSession.getTransaction();
        LOGGER.debug("get Transaction");
    }

    public void saveDegree(Degree degree) {
        openTransaction();
        currentTransaction.begin();
        currentSession.save(degree);
        currentTransaction.commit();
    }

    public List<Degree> getAllDegrees() {
        Query query = currentSession.createQuery("select d from Degree d");
        List<Degree> degrees = ((org.hibernate.query.Query) query).list();
        return degrees;
    }

    public Degree findDegreeById(Long Id) {
        return currentSession.get(Degree.class, Id);
    }

}
