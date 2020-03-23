package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.domain.Gender;
import hu.userrendszerhaz.service.CountryInfoService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CustomerViewModel {

    private String customerName;
    private Gender customerGender;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private Date customerBirthday;
    private String customerCountry;
    private Customer customer;
    private String dialogPage;
    private ListModelList<Customer> customerList;
    private Customer selectedCustomer;

    @Init
    public void init() {
        loadCustomers();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Gender getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(Gender customerGender) {
        this.customerGender = customerGender;
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

    public ListModelList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ListModelList<Customer> customerList) {
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

    private CustomerService customerService = new CustomerServiceImpl();

    public List<Gender> getGenderList() {
        return Arrays.asList(Gender.values());
    }


    public List<String> getCountryList() {
        return CountryInfoService.getCountryList();
    }

    private static void initFrontend() {
    }

    @Command
    @NotifyChange({"customerList", "customerName", "customerAddress", "customerPhoneNumber", "customerEmail", "customerBirthday", "customerCountry", "dialogPage"})
    public void save(@BindingParam("page") String page) {
        customer = new Customer(customerName, customerGender, customerAddress, customerPhoneNumber, customerEmail, convertDateToLocalDate(customerBirthday), customerCountry);
        customerService.create(customer);
        this.dialogPage = page;
        loadCustomers();
    }

    @Command
    @NotifyChange({"customerList"})
    public void update(@BindingParam("page") String page) {
        selectedCustomer = customerService.update(selectedCustomer);
        loadCustomers();
        BindUtils.postNotifyChange(null, null, this, "dialogPage");
        this.dialogPage = page;
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
    @NotifyChange({"customerList"})
    public void deleteAll() {
        customerService.deleteAll();
        loadCustomers();
    }

    @Command
    @NotifyChange({"dialogPage", "selectedCustomer"})
    public void showDialog(@BindingParam("page") String page) {
        this.dialogPage = page;
    }

    public LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    private void loadCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        customerList = new ListModelList<>(customers);
    }
}
