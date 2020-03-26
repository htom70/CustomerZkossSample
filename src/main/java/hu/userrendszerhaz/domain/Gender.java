package hu.userrendszerhaz.domain;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Label;

public enum Gender {
    MALE("male"), FEMALE("female");

    private String genderType;

    Gender(String genderType) {
        this.genderType = genderType;
    }

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    //    public String getLabelString() {
//        String string= Labels.getLabel(this.genderType) ;
//        return string;
//    }

//    public void setLabelString(String labelString) {
//        this.labelString = Labels.getLabel(labelString);
//    }
}
