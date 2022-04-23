package ProjektPraktyczny;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer carId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
    @Column(name = "registration_number")
    private String registrationNumber;
    @Column(name = "vehicle_condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;
    @Column(name = "cost")
    private BigDecimal cost;

    @OneToOne(mappedBy = "car", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Rental rental;

    public Car() {
    }

    public Car(String brand, String model, String color, String registrationNumber, Condition condition, BigDecimal cost) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.condition = condition;
        this.cost = cost;
    }

    public Car(String brand, String model, String color, String registrationNumber, String condition, String cost) {

    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return null;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

//    public Rental getRental() {
//        return rental;
//    }
//
//    public void setRental(Rental rental) {
//        this.rental = rental;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("carId=").append(carId);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", condition=").append(condition);
        sb.append(", cost=").append(cost);
//        sb.append(", rental=").append(rental);
        sb.append('}');
        return sb.toString();
    }

    public void setCondition(String s) {
    }
}
