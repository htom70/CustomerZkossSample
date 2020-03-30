package hu.userrendszerhaz.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

//    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String address;
    private String phoneNumber;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String country;

//    @Enumerated(EnumType.STRING)
    private AgeCategory ageCategory;

    @ManyToOne
    private Degree degree;
    private int incomeBracketStart;
    private int incomeBracketEnd;

    public Customer() {
    }

    public Customer(String name, Gender gender,String address, String phoneNumber, String email, Date birthday,String country, AgeCategory ageCategory,Degree degree,int incomeBracketStart, int incomeBracketEnd) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.country = country;
        this.gender = gender;
        this.ageCategory = ageCategory;
        this.degree = degree;
        this.incomeBracketStart = incomeBracketStart;
        this.incomeBracketEnd  = incomeBracketEnd;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthdayString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return simpleDateFormat.format(birthday);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getIncomeBracketStart() {
        return incomeBracketStart;
    }

    public void setIncomeBracketStart(int incomeBracketStart) {
        this.incomeBracketStart = incomeBracketStart;
    }

    public int getIncomeBracketEnd() {
        return incomeBracketEnd;
    }

    public void setIncomeBracketEnd(int incomeBracketEnd) {
        this.incomeBracketEnd = incomeBracketEnd;
    }
}
