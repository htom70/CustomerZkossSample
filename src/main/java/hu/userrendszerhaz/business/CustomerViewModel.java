package hu.userrendszerhaz.business;

import hu.userrendszerhaz.converter.StringToLabelConverter;
import hu.userrendszerhaz.domain.AgeCategory;
import hu.userrendszerhaz.domain.Customer;
import hu.userrendszerhaz.domain.Degree;
import hu.userrendszerhaz.domain.Gender;
import hu.userrendszerhaz.service.CountryInfoService;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.*;
import org.zkoss.zul.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CustomerViewModel {

    private DegreeService degreeService= new DegreeServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();

    private String customerName;
    private Gender customerGender;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private Date customerBirthday;
    private String customerCountry;
    private AgeCategory customerAgeCategory;
    private String degreeItem;
    private String ageCategoryString;
    private Customer customer;
    private String dialogPage;
    private int pageSize;
    private int listIndex;
    private int entityNumberForTest;
    private ListModel<Customer> customerList;
    private int cacheSize;
    private Customer selectedCustomer;
    private AgeCategory selectedAgeCategory;
        private Map<Integer, Label> ageCategoryMap = new HashMap<>();
    private List<AgeCategory> ageCategoryList = new ArrayList<>();
    private Label ageCategoryLabel;
    private String degreeName;
    private List<Degree> degrees;
    private ListModel<Degree> degreeList;
    private Degree selectedDegree;
    private Converter stringToLabelConverter = new StringToLabelConverter();


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
        typeFromOuter = type;
        fillAgeCategoryList();
        fillAgeCategoryMap();
        loadCustomers();
        loadDegrees();
    }

    private void fillAgeCategoryList() {
        for (AgeCategory o : AgeCategory.values()) {
            ageCategoryList.add(o);
        }
    }
    private void fillAgeCategoryMap() {
        ageCategoryMap.put(0, new Label("child"));
        ageCategoryMap.put(1, new Label("adult"));
        ageCategoryMap.put(2, new Label("retired"));
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

    public AgeCategory getCustomerAgeCategory() {
        return customerAgeCategory;
    }

    public void setCustomerAgeCategory(AgeCategory customerAgeCategory) {
        this.customerAgeCategory = customerAgeCategory;
    }

    public Map<Integer, Label> getAgeCategoryMap() {
        return ageCategoryMap;
    }

    public void setAgeCategoryMap(Map<Integer, Label> ageCategoryMap) {
        this.ageCategoryMap = ageCategoryMap;
    }

    public List<AgeCategory> getAgeCategoryList() {
        return ageCategoryList;
    }

    public void setAgeCategoryList(List<AgeCategory> ageCategoryList) {
        this.ageCategoryList = ageCategoryList;
    }

    public Label getAgeCategoryString(int index) {
        return ageCategoryMap.get(index);
    }

    public void setAgeCategoryString(String ageCategoryString) {
        this.ageCategoryString = ageCategoryString;
    }

    public AgeCategory getSelectedAgeCategory() {
        return selectedAgeCategory;
    }

    public void setSelectedAgeCategory(AgeCategory selectedAgeCategory) {
        this.selectedAgeCategory = selectedAgeCategory;
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

    public Label getAgeCategoryLabel(int index) {
        return ageCategoryMap.get(index);
    }

    public void setAgeCategoryLabel(Label ageCategoryLabel) {
        this.ageCategoryLabel = ageCategoryLabel;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Degree getSelectedDegree() {
        return selectedDegree;
    }

    public void setSelectedDegree(Degree selectedDegree) {
        this.selectedDegree = selectedDegree;
    }

    public String getDegreeItem() {
        return degreeItem;
    }

    public void setDegreeItem(String degreeItem) {
        this.degreeItem = degreeItem;
    }

    public ListModel<Degree> getDegreeList() {
        return degreeList;
    }

    public void setDegreeList(ListModel<Degree> degreeList) {
        this.degreeList = degreeList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Converter getStringToLabelConverter() {
        return stringToLabelConverter;
    }

    public void setStringToLabelConverter(Converter stringToLabelConverter) {
        this.stringToLabelConverter = stringToLabelConverter;
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
        customer = new Customer(customerName, customerGender, customerAddress, customerPhoneNumber, customerEmail, customerBirthday, customerCountry,selectedAgeCategory,selectedDegree);
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

    @Command
    @NotifyChange({"dialogPage"})
    public void saveDegree(@BindingParam("page") String page) {
        Degree degree = new Degree(degreeName);
        degreeService.createDegree(degree);
        this.dialogPage = page;

    }
       private void fillListboxWithTestParameters() {
        for (int i = 0; i < entityNumberForTest; i++) {
            customer = new Customer("Test" + i, Gender.MALE, "Test Address", "Test Phone Number" + i, "test@test.hu", new Date(), "Test Country",AgeCategory.CHILD,degrees.get(0))
            ;
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

    private void loadDegrees() {
        degrees = degreeService.findAllDegrees();
        degreeList = new ListModelList<>(degrees);
    }
}
