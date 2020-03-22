package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.service.CountryInfoService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import java.util.Date;
import java.util.List;

public class CustomerViewModel {

    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private Date customerBirthday;
    private String customerCountry;
    private Customer customer;
    private String dialogPage;
    private List<Customer> customerList = new ListModelList<>();
    private Customer selectedCustomer;

    public CustomerViewModel() {
        loadCustomers();
//        dialogPage="/pages/empty.zul";
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getDialogPage() {
        return dialogPage;
    }

    public void setDialogPage(String dialogPage) {
        this.dialogPage = dialogPage;
    }

    private Listbox customerListbox;

    //    private List<Customer> customerList = new LinkedList<>();
    private CustomerService customerService = new CustomerServiceImpl();

    public List<String> getCountryList(){
        return CountryInfoService.getCountryList();
    }

    private static void initFrontend() {
    }

    @Command
    @NotifyChange({"customerList", "customerName", "customerAddress", "customerPhoneNumber","customerEmail", "customerBirthday", "customerCountry", "dialogPage"})
    public void save(@BindingParam("page") String page) {
        customer = new Customer(customerName, customerAddress, customerPhoneNumber, customerEmail, customerBirthday,customerCountry);
        customerService.create(customer);
        this.dialogPage = page;
        loadCustomers();
//        customerName = null;
//        customerAddress = null;
//        customerPhoneNumber = null;
//        customerEmail = null;
//        customerBirthday = null;
//        customerCountry=null;

    }

    @Command
    @NotifyChange({"customerList", "selectedCustomer","dialogPage"})
    public void update(@BindingParam("page") String page) {
        customerService.update(selectedCustomer);
        this.dialogPage = page;
//        loadCustomers();
    }


    @Command
    @NotifyChange("customerList")
    public void read() {
        loadCustomers();
    }

    @Command
    @NotifyChange({"customerList", "selectedCustomer"})
    public void delete() {
        customerService.delete(selectedCustomer);
        selectedCustomer = null;
        loadCustomers();
    }

    @Command
    @NotifyChange({"dialogPage","selectedCustomer"})
    public void showDialog(@BindingParam("page") String page){

        this.dialogPage = page;
    }


    private void loadCustomers() {
        customerList = customerService.findAllCustomers();
    }
}
