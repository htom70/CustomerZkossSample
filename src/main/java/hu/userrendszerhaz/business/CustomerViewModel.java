package hu.userrendszerhaz.business;

import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.domain.Gender;
import hu.userrendszerhaz.service.CountryInfoService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.ListModel;
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
    private int pageSize;
    private int listIndex;
    private int entityNumberForTest;
    private ListModel<Customer> customerList;
    private int cacheSize;
    private Customer selectedCustomer;
    private CustomerService customerService;

    private String typeFromOuter;

    public String getTypeFromOuter() {
        return typeFromOuter;
    }

    public void setTypeFromOuter(String typeFromOuter) {
        this.typeFromOuter = typeFromOuter;
    }

    @Init
    public void init(@ExecutionArgParam("type") String type) {
        pageSize = 5;
        customerService = new CustomerServiceImpl();
        typeFromOuter = type;
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

    public ListModel<Customer> getCustomerList() {
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

    public List<Gender> getGenderList() {
        return Arrays.asList(Gender.values());
    }


    public List<String> getCountryList() {
        return CountryInfoService.getCountryList();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public int getEntityNumberForTest() {
        return entityNumberForTest;
    }

    public void setEntityNumberForTest(int entityNumberForTest) {
        this.entityNumberForTest = entityNumberForTest;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    private static void initFrontend() {
    }

    @Command
    @NotifyChange({"customerList", "customerName", "customerAddress", "customerPhoneNumber", "customerEmail", "customerBirthday", "customerCountry", "dialogPage"})
    public void save(@BindingParam("page") String page) {
        customer = new Customer(customerName, customerGender, customerAddress, customerPhoneNumber, customerEmail, customerBirthday, customerCountry);
        customerService.create(customer);
        loadCustomers();
        this.dialogPage = page;
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

    @Command
    @NotifyChange({"dialogPage", "customerList"})
    public void saveTestParameters(@BindingParam("page") String page) {
        fillListboxWithTestParameters();
        this.dialogPage = page;
    }

    private void fillListboxWithTestParameters() {
        for (int i = 0; i < entityNumberForTest; i++) {
            customer = new Customer("Test" + i, Gender.MALE, "Test Address", "Test Phone Number" + i, "test@test.hu", new Date(), "Test Country");
            customerService.create(customer);
        }
        loadCustomers();
    }

    public LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    private void loadCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        customerList = new CustomerListModel(customers,customerService,cacheSize);
    }
}
