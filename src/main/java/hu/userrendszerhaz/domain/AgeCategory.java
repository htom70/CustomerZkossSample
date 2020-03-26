package hu.userrendszerhaz.domain;

import org.zkoss.util.resource.Labels;

public enum AgeCategory {
    CHILD("child"),ADULT("adult"), RETIRED("retired");

    private String category;

    AgeCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
