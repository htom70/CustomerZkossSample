package hu.userrendszerhaz.validator;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;

public class GenderValidator implements Validator {
    @Override
    public void validate(ValidationContext validationContext) {
        validationContext.getProperty().getValue();
    }
}
