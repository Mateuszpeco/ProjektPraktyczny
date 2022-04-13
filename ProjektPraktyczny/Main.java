package ProjektPraktyczny;

import org.hibernate.SessionFactory;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("200.0");
        BigDecimal b = new BigDecimal("400.0");

        Car car = new Car("Skoda", "Superb", "White", "SS1234", Condition.UNDAMAGED, b);
        Car car1 = new Car("Skoda", "Octavia", "White", "SS12345", Condition.UNDAMAGED, a);
        Customers customers = new Customers("Alicja", "Kowalska");
        Rental rental = new Rental(car, customers);
        RentalService rentalService = new RentalService();
        CarService carService = new CarService();

        rental.setLoanDate(LocalDate.of(1995, 1, 1));
        rental.setReturnDate(LocalDate.of(1995, 5, 5));
        rental.setDaysAfterDeadline(5);
        rental.setPenalty("YES");
        rental.setPenaltyAmount(a);

        rentalService.persist(rental);
        carService.persist(car1);
    }
}
