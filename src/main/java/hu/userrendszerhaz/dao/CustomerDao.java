package hu.userrendszerhaz.dao;

import com.fasterxml.classmate.AnnotationConfiguration;
import hu.userrendszerhaz.domain.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;



public class CustomerDao {

//    private static final String HIBERNATE_CFG = "../../../resources/hibernate.cfg.xml";

    private SessionFactory sessionFactory;

    private Session currentSession;
    private Transaction currentTransaction;

    public CustomerDao()  {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
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
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
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
        getCurrentSession().save(customer);
    }

    public Customer modifyCustomer(Customer customer) {
        return (Customer)getCurrentSession().merge(customer);
    }

    public void deleteCustomer(Customer customer) {
        getCurrentSession().delete(customer);
    }

    public void deleteAllCustomers() {
        String stringQuery = "DELETE FROM Customer";
        Query query = getCurrentSession().createQuery(stringQuery);
        query.executeUpdate();
        getCurrentSession();
    }

    public Customer findCustomerById(Long Id) {
        return (Customer)getCurrentSession().get(Customer.class, Id);
    }

    public List<Customer> getAllCustomers() {
        Query query = getCurrentSession().createQuery("select c from Customer c");
        return query.list();
    }
}
