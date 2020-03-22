package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;

import java.util.List;

public interface CustomerService {

    public void create(Customer customer);

    public Customer findById(Long Id);

    public List<Customer> findAllCustomers();

    public void update(Customer customer);

    public void delete(Customer customer);

    public void deleteAll();
}
