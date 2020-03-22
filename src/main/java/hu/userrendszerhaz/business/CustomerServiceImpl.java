package hu.userrendszerhaz.business;

import hu.userrendszerhaz.dao.CustomerDao;
import hu.userrendszerhaz.domain.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private static CustomerDao customerDao;

    public CustomerServiceImpl() {
        customerDao = new CustomerDao();
    }

    @Override
    public void create(Customer customer) {
        customerDao.openCurrentSessionwithTransaction();
        customerDao.saveCustomer(customer);
        customerDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public Customer findById(Long Id) {
        customerDao.openCurrentSession();
        Customer customer = customerDao.findCustomerById(Id);
        customerDao.closeCurrentSession();
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        customerDao.openCurrentSession();
        List<Customer> customers = customerDao.getAllCustomers();
        customerDao.closeCurrentSession();
        return customers;
    }

    @Override
    public void update(Customer customer) {
        customerDao.openCurrentSessionwithTransaction();
        customerDao.modifyCustomer(customer);
        customerDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public void delete(Customer customer) {
        customerDao.openCurrentSessionwithTransaction();
        customerDao.deleteCustomer(customer);
        customerDao.closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteAll() {
        customerDao.openCurrentSessionwithTransaction();
        customerDao.deleteAllCustomers();
        customerDao.closeCurrentSessionwithTransaction();
    }
}
