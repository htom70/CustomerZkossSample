package hu.userrendszerhaz.dao;

import hu.userrendszerhaz.domain.Customer;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;


public class CustomerDao {

//    private static final String HIBERNATE_CFG = "../../../resources/hibernate.cfg.xml";

    private SessionFactory sessionFactory;

    private Session currentSession;
    private Transaction currentTransaction;

    public CustomerDao() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeSessionFactory() {

    }

    public void openCurrentSession() {
        currentSession = sessionFactory.openSession();
    }

    public void openCurrentSessionwithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void saveCustomer(Customer customer) {
        openCurrentSessionwithTransaction();
        currentSession.save(customer);
        closeCurrentSessionwithTransaction();
    }

    public Customer modifyCustomer(Customer customer) {
        openCurrentSessionwithTransaction();
        Customer resultCustomer = (Customer) currentSession.merge(customer);
        closeCurrentSessionwithTransaction();
        return resultCustomer;
    }

    public void deleteCustomer(Customer customer) {
        openCurrentSessionwithTransaction();
        currentSession.delete(customer);
        closeCurrentSessionwithTransaction();
    }

    public void deleteAllCustomers() {
        openCurrentSessionwithTransaction();
        String stringQuery = "DELETE FROM Customer";
        Query query = currentSession.createQuery(stringQuery);
        query.executeUpdate();
        closeCurrentSessionwithTransaction();
    }

    public Customer findCustomerById(Long Id) {
        openCurrentSession();
        Customer resultCustomer = currentSession.get(Customer.class, Id);
        closeCurrentSession();
        return resultCustomer;
    }

    public List<Customer> getAllCustomers() {
        openCurrentSession();
        Query query = currentSession.createQuery("select c from Customer c");
        List<Customer> customers = query.list();
        closeCurrentSession();
        return customers;
    }

    public List<Customer> findCustomersFromIndexAndPageSize(int firstIndex, int lastIndex) {
        openCurrentSession();
        Criteria criteria = currentSession.createCriteria(Customer.class);
        criteria.setFirstResult(firstIndex);
        criteria.setMaxResults(lastIndex);
        List<Customer> customers = criteria.list();
        closeCurrentSession();
        return customers;
    }

    public Long getSize() {
        openCurrentSession();
        Criteria criteriaCount = currentSession.createCriteria(Customer.class);
        criteriaCount.setProjection(Projections.rowCount());
        Long result = (Long) criteriaCount.uniqueResult();
        closeCurrentSession();
        return result;
    }
}
