package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Listbox;

import java.util.LinkedList;
import java.util.List;

public class CustomerViewModel {

    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private Customer customer;
    private List<Customer> customerList = new LinkedList<>();
    private Customer selectedCustomer;

    public CustomerViewModel() {
        loadCustomers();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Listbox customerListbox;

//    private List<Customer> customerList = new LinkedList<>();
    private CustomerService customerService = new CustomerServiceImpl();

    private static void initFrontend() {
    }

    @Command
    @NotifyChange({"customerList","customerName","customerAddress","customerPhoneNumber"})
    public void create() {
        customer = new Customer(customerName, customerAddress, customerPhoneNumber);
        customerService.create(customer);
        loadCustomers();
        customerName = null;
        customerAddress = null;
        customerPhoneNumber = null;
            }

    @Command
    @NotifyChange({"customerList", "selectedCustomer"})
    public void update() {
        customerService.update(selectedCustomer);
        selectedCustomer = null;
        loadCustomers();
    }


    @Command
    @NotifyChange("customerList")
    public void read(){
        loadCustomers();
    }

    @Command
    @NotifyChange({"customerList", "selectedCustomer"})
    public void delete() {
        customerService.delete(selectedCustomer);
        selectedCustomer = null;
        loadCustomers();
    }

    private void loadCustomers() {
        customerList = customerService.findAllCustomers();
    }
}
