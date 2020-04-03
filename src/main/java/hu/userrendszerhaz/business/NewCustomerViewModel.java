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
import org.zkoss.zk.ui.util.Notification;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NewCustomerViewModel {


    private DegreeService degreeService = new DegreeServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
    private boolean newCustomerFormVisibleFlag;
    private Customer currentCustomer;
    private Customer selectedCustomer = new Customer();
    private ListModel<Customer> customerList;
    private List<Degree> degrees;
    private ListModel<Degree> degreeList;
    private List<AgeCategory> ageCategoryList = new ArrayList<>();
    private String dialogPage;
    private Converter stringToLabelConverter = new StringToLabelConverter();


    public DegreeService getDegreeService() {
        return degreeService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }

    public ListModel<Degree> getDegreeList() {
        return degreeList;
    }

    public void setDegreeList(ListModel<Degree> degreeList) {
        this.degreeList = degreeList;
    }

    public List<AgeCategory> getAgeCategoryList() {
        return ageCategoryList;
    }

    public void setAgeCategoryList(List<AgeCategory> ageCategoryList) {
        this.ageCategoryList = ageCategoryList;
    }

    public String getDialogPage() {
        return dialogPage;
    }

    public void setDialogPage(String dialogPage) {
        this.dialogPage = dialogPage;
    }

    public boolean isNewCustomerFormVisibleFlag() {
        return newCustomerFormVisibleFlag;
    }

    public void setNewCustomerFormVisibleFlag(boolean newCustomerFormVisibleFlag) {
        this.newCustomerFormVisibleFlag = newCustomerFormVisibleFlag;
    }

    public List<Gender> getGenderList() {
        return Arrays.asList(Gender.values());
    }

    public Converter getStringToLabelConverter() {
        return stringToLabelConverter;
    }

    public void setStringToLabelConverter(Converter stringToLabelConverter) {
        this.stringToLabelConverter = stringToLabelConverter;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    @Init
    public void init() {
        currentCustomer = new Customer();
        fillAgeCategoryList();
        loadDegrees();
    }

    @Command
    public void save() {
        customerService.create(currentCustomer);
        BindUtils.postGlobalCommand(null, null, "refresh", null);
    }



    @Command
    public void update() {
        selectedCustomer = customerService.update(selectedCustomer);
        BindUtils.postGlobalCommand(null, null, "refresh", null);
    }

    @Command
    public void cancel() {
        BindUtils.postGlobalCommand(null, null, "refresh", null);
    }

    @GlobalCommand
    @NotifyChange("selectedCustomer")
    public void settingSelectedCustomer(@BindingParam("item") Customer customer) {
       selectedCustomer = customer;
    }

    @GlobalCommand
    public void showDefault(@BindingParam("item") Customer item){
        Notification.show("Customer: "+item.getName() );
    }

    private void loadDegrees() {
        degrees = degreeService.findAllDegrees();
        degreeList = new ListModelList<>(degrees);
    }

    private void fillAgeCategoryList() {
        for (AgeCategory o : AgeCategory.values()) {
            ageCategoryList.add(o);
        }
    }

    public List<String> getCountryList() {
        return CountryInfoService.getCountryList();
    }
}