package ProjektPraktyczny;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customers", cascade = {CascadeType.ALL}, orphanRemoval = true)
//    private Set<Rental> rentals = new HashSet<>();

    public Customers() {
    }

    public Customers(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getCustomer_id() {
        return customerId;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customerId = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Set<Rental> getRentals() {
//        return rentals;
//    }
//
//    public void setRentals(Set<Rental> rentals) {
//        this.rentals = rentals;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customers{");
        sb.append("customerId=").append(customerId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
//        sb.append(", rentals=").append(rentals);
        sb.append('}');
        return sb.toString();
    }
}
