package hu.userrendszerhaz.domain;

import org.zkoss.util.resource.Labels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degreeName;

    @OneToMany(mappedBy = "degree",cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    public Degree() {
    }

    public Degree(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
