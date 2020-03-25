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
        customerDao.saveCustomer(customer);
    }

    @Override
    public Customer findById(Long Id) {
        return customerDao.findCustomerById(Id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public List<Customer> findCustomersFromIndexAndPageSize(int index, int pageSize) {
        return customerDao.findCustomersFromIndexAndPageSize(index, pageSize);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.modifyCustomer(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDao.deleteCustomer(customer);
    }

    @Override
    public void deleteAll() {
        customerDao.deleteAllCustomers();
    }

    @Override
    public int getSize() {
     return Math.toIntExact(customerDao.getSize());
    }
}
