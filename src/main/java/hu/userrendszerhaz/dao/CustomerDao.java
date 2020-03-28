package hu.userrendszerhaz.dao;

import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.listener.ZKContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;

import javax.persistence.Query;
import java.util.List;


public class CustomerDao {

    //    private static final String HIBERNATE_CFG = "../../../resources/hibernate.cfg.xml";
    private static Logger LOGGER = (Logger) LogManager.getLogger(CustomerDao.class.getName());


    private Session currentSession;
    private Transaction currentTransaction;

    public CustomerDao() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
    }

    private void openTransaction() {
        currentTransaction = currentSession.getTransaction();
    }


//    protected void setUp() throws Exception {
//        // A SessionFactory is set up once for an application!
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//            // so destroy it manually.
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }

    public void saveCustomer(Customer customer) {
        openTransaction();
        currentTransaction.begin();
        currentSession.save(customer);
        currentTransaction.commit();
    }

    public Customer modifyCustomer(Customer customer) {
        openTransaction();
        currentTransaction.begin();
        Customer resultCustomer = (Customer) currentSession.merge(customer);
        currentTransaction.commit();
        return resultCustomer;
    }

    public void deleteCustomer(Customer customer) {
        openTransaction();
        currentTransaction.begin();
        currentSession.delete(customer);
        currentTransaction.commit();
    }

    public void deleteAllCustomers() {
        openTransaction();
        currentTransaction.begin();
        String stringQuery = "DELETE FROM Customer";
        Query query = currentSession.createQuery(stringQuery);
        query.executeUpdate();
        currentTransaction.commit();
    }

    public Customer findCustomerById(Long Id) {
       return currentSession.get(Customer.class, Id);
    }

    public List<Customer> getAllCustomers() {
        Query query = currentSession.createQuery("select c from Customer c");
       return  ((org.hibernate.query.Query) query).list();
    }

    public List<Customer> findCustomersFromIndexAndPageSize(int firstIndex, int lastIndex) {
        Criteria criteria = currentSession.createCriteria(Customer.class);
        criteria.setFirstResult(firstIndex);
        criteria.setMaxResults(lastIndex);
        return criteria.list();
    }

    public Long getSize() {
        Criteria criteriaCount = currentSession.createCriteria(Customer.class);
        criteriaCount.setProjection(Projections.rowCount());
        return (Long) criteriaCount.uniqueResult();
    }
}
