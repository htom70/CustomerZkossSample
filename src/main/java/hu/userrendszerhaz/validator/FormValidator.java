package hu.userrendszerhaz.validator;

import hu.userrendszerhaz.domain.AgeCategory;
import hu.userrendszerhaz.domain.Gender;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.util.Clients;

import java.util.Map;

public class FormValidator extends AbstractValidator {
    @Override
    public void validate(ValidationContext validationContext) {

        String nev = (String)validationContext.getProperties("name")[0].getValue();
        if(nev == null || "".equals(nev)){
            addInvalidMessage(validationContext, "name", "A név kitöltése kötelező");
        }

        AgeCategory ageCategory = (AgeCategory) validationContext.getProperties("ageCategory")[0].getValue();
        if (ageCategory == null) {
            addInvalidMessage(validationContext, "ageKey", "Hol az age?");
        }

        Gender gender= (Gender) validationContext.getProperties("gender")[0].getValue();
        if (gender == null) {
            addInvalidMessage(validationContext, "genderKey", "Invalid gender");
        }
//        Map<String, Property> beanProps = validationContext.getProperties(validationContext.getProperty().getBase());
//        String name = (String) beanProps.get("name").getValue();
//        if (name == null) {
//            validationContext.setInvalid();
//            this.addInvalidMessage(validationContext, "nameKey", "Please enter a valid Name!");
//            Clients.showNotification("Name???");
//        }


//        AgeCategory ageCategory = (AgeCategory) beanProps.get("ageCategory").getValue();
////        if (ageCategory == null) {
//            addInvalidMessage(validationContext, "ageCategoryKey", "Please enter a valid ageCategory!");
//            validationContext.setInvalid();
//            Clients.showNotification("Age???");
        }
    }

//    private void validateGender(ValidationContext validationContext, Property ageCategory) {
//        if (ageCategory.get.isEmpty()) {
//            this.addInvalidMessage(validationContext, "ageCategory", "Please enter a valid ageCategory!"); }
//    }
//}
