package ProjektPraktyczny;

import ProjektPraktyczny.Car;
import ProjektPraktyczny.Customers;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer rentalId;
//    @Column(name = "car_id")
//    private Integer idCar;
//    @Column(name = "customer_id")
//    private Integer idCustomer;
    @Column(name = "loan_date")
    private LocalDate loanDate;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "days_after_deadline")
    private Integer daysAfterDeadline;
    @Column(name = "penalty")
    private String penalty;
    @Column(name = "penalty_amount")
    private BigDecimal penaltyAmount;


    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "customerId")
    private Customers customer;

    public Rental(Car car, Customers customers) {
        this.car = car;
        this.customer = customers;
    }

    public Rental() {
    }

    public Integer getRental_id() {
        return rentalId;
    }

    public void setRental_id(Integer rental_id) {
        this.rentalId = rental_id;
    }

//    public Integer getIdCar() {
//        return idCar;
//    }
//
//    public void setIdCar(Integer idCar) {
//        this.idCar = idCar;
//    }
//
//    public Integer getIdCustomer() {
//        return idCustomer;
//    }
//
//    public void setIdCustomer(Integer idCustomer) {
//        this.idCustomer = idCustomer;
//    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getDaysAfterDeadline() {
        return daysAfterDeadline;
    }

    public void setDaysAfterDeadline(Integer daysAfterDeadline) {
        this.daysAfterDeadline = daysAfterDeadline;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rental{");
        sb.append("rental_id=").append(rentalId);
//        sb.append(", idCar=").append(idCar);
//        sb.append(", idCustomer=").append(idCustomer);
        sb.append(", loanDate=").append(loanDate);
        sb.append(", returnDate=").append(returnDate);
        sb.append(", daysAfterDeadline=").append(daysAfterDeadline);
        sb.append(", penalty='").append(penalty).append('\'');
        sb.append(", penaltyAmount=").append(penaltyAmount);
        sb.append(", car=").append(car);
        sb.append(", customer=").append(customer);
        sb.append('}');
        return sb.toString();
    }
}
