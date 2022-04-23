package ProjektPraktyczny;

        import java.math.BigDecimal;
        import java.time.LocalDate;
        import java.util.Date;
        import java.util.List;
        import java.util.Scanner;

        import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("200.0");
        BigDecimal b = new BigDecimal("400.0");
        Condition u = Condition.DAMAGED;
        Condition s = Condition.UNDAMAGED;
        boolean isWorking = true;
        Integer year;
        Integer month;
        Integer day;
        Rental rental = new Rental();
        BigDecimal less3 = new BigDecimal("100.0");
        BigDecimal less7 = new BigDecimal("200.0");
        BigDecimal less21 = new BigDecimal("5000.0");

//        Car car2 = new Car("Skoda", "Superb", "White", "SS1234", Condition.UNDAMAGED, b);
//        Car car1 = new Car("Skoda", "Octavia", "White", "SS12345", Condition.UNDAMAGED, a);
//        Customers customers = new Customers("Alicja", "Kowalska");
//        Rental rental = new Rental(car1, customers);
        RentalService rentalService = new RentalService();
        CarService carService = new CarService();
        CustomerService customerService = new CustomerService();

//        carService.delete(1);
//        rental.setLoanDate(LocalDate.of(1995, 1, 1));
//        rental.setReturnDate(LocalDate.of(1995, 5, 5));
//        rental.setDaysAfterDeadline(5);
//        rental.setPenalty("YES");
//        rental.setPenaltyAmount(a);
//
//        rentalService.persist(rental);
//        carService.persist(car1);
//        Scanner scanner = new Scanner(System.in);
//        String reading = scanner.nextLine();


        do {
            String menu = "Witaj w wypożyczalni. Wybierz, co chcesz zrobić (wpisz numer): \n" +
                    "1. Wyświetl informacje o samochodach.\n" +
                    "2. Wyświetl informacje o klientach.\n" +
                    "3. Wyświetl informacje o aktualnych wypożyczeniach.\n" +
                    "4. Wypożycz samochód.\n" +
                    "5. Dodaj samochód.\n" +
                    "6. Usuń samochód.\n" +
                    "7. Dodaj informacje o uszkodzeniach.\n" +
                    "8. Dodaj klienta.\n" +
                    "9. Usuń klienta.\n" +
                    "10. Przedłuż okres wypożyczenia.\n" +
                    "11. Zwróć samochód.\n" +
                    "12. Wyjście.";
            System.out.println(menu);
            Scanner scanner = new Scanner(System.in);
            String reading = scanner.nextLine();

            switch (reading) {
                case "1":
                    List<Car> cars = carService.findAll();
                    System.out.println("Samochody: ");
                    for (Car c : cars) {
                        System.out.println(c.toString());
                    }
                    break;
                case "2":
                    List<Customers> customers1 = customerService.findAll();
                    System.out.println("Klienci: ");
                    for (Customers cu : customers1) {
                        System.out.println(cu.toString());
                    }
                    break;
                case "3":
                    List<Rental> rentals = rentalService.findAll();
                    System.out.println("Aktualne wypożyczenia: ");
                    for (Rental r : rentals) {
                        System.out.println(r.toString());
                    }
                    break;
                case "4":
                    //wyświetlenie listy klientów
                    List<Customers> customers2 = customerService.findAll();
                    System.out.println("Klienci: ");
                    for (Customers cu : customers2) {
                        System.out.println(cu.toString());
                    }
                    System.out.println("Podaj id użytkownika do wypożyczenia:");
                    rental.setIdCustomer(scanner.nextInt());
                    rentalService.persist(rental);

                    System.out.println("Wybrałeś użytkownika: " + customerService.findById(rental.getIdCustomer()));
                    //wyświetlenie listy samochodów
                    List<Car> cars2 = carService.findAll();
                    System.out.println("Samochody: ");
                    for (Car c : cars2) {
                        System.out.println(c.toString());
                    }
                    System.out.println("Podaj id samochodu, który chcesz wypożyczyć:");
                    rental.setIdCar(scanner.nextInt());
                    System.out.println("Wybrałeś samochód: " + carService.findById(rental.getIdCar()));
                    System.out.println(rental.getIdCar());
                    System.out.println("Podaj datę wypożyczenia (RRRR-MM-DD): ");
                    scanner.nextLine();
                    rental.setLoanDate(LocalDate.parse(scanner.nextLine()));
                    System.out.println("Wybrałeś datę wypożyczenia: " + rental.getLoanDate());
                    System.out.println("Podaj datę zwrotu (RRRR-MM-DD): ");
                    rental.setReturnDate(LocalDate.parse(scanner.nextLine()));
                    System.out.println("Wybrałeś datę zwrotu" + rental.getReturnDate());
                    int possibleDeadLine = (int) DAYS.between(rental.getReturnDate(), LocalDate.now());
                    rental.setDaysAfterDeadline(possibleDeadLine);
                    if (possibleDeadLine < 0) {
                        rental.setPenalty("NO");
                    } else if (possibleDeadLine > 0 && possibleDeadLine < 4) {
                        rental.setPenalty("YES");
                        rental.setPenaltyAmount(less3);
                    } else if (possibleDeadLine > 3 && possibleDeadLine < 8) {
                        rental.setPenalty("YES");
                        rental.setPenaltyAmount(less7);
                    } else {
                        rental.setPenalty("YES");
                        rental.setPenaltyAmount(less21);
                    }
                    rentalService.update(rental);
                    System.out.println("Nowe wypożyczenie: " + customerService.findById(rental.getIdCustomer()) + " " +
                            carService.findById(rental.getIdCar()) + " Data wypożyczenia: " + rental.getLoanDate() + " Data zwrotu: " + rental.getReturnDate());
                    break;
                case "5":
                    Car car = new Car();
                    carService.persist(car);
                    System.out.println("Podaj markę:");
                    car.setBrand(scanner.nextLine());
                    System.out.println("Podaj model:");
                    car.setModel(scanner.nextLine());
                    System.out.println("Podaj kolor:");
                    car.setColor(scanner.nextLine());
                    System.out.println("Podaj numer rejestracyjny:");
                    car.setRegistrationNumber(scanner.nextLine());
                    System.out.println("Podaj stan pojazdu (u - uszkodzony, s - sprawny):");
                    if (scanner.nextLine().equals("u")) {
                        car.setCondition(u);
                    } else if (scanner.nextLine().equals("s")) {
                        car.setCondition(s);
                    } else {
                        System.out.println("Podałeś błędny symbol.");
                    }
                    System.out.println("Podaj koszt:");
                    car.setCost(BigDecimal.valueOf(scanner.nextDouble()));
                    carService.update(car);
                    System.out.println("Dodałeś: " + car);
                    break;
                case "6":
                    List<Car> cars1 = carService.findAll();
                    System.out.println("Samochody: ");
                    for (Car c : cars1) {
                        System.out.println(c.toString()); }
                    System.out.println("Podaj id samochodu, który chcesz usunąć:");
                    carService.delete(scanner.nextInt());
                    break;
                case "7":
                    List<Car> cars3 = carService.findAll();
                    System.out.println("Samochody: ");
                    for (Car c : cars3) {
                    System.out.println(c.toString()); }
                    System.out.println("Podaj id samochodu, którego stan chcesz zmienić:");
                    Car car3 = carService.findById(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Podaj nowy stan pojazdu (u - uszkodzony, s - sprawny):");
                    String s1 = scanner.nextLine();
                    if (s1.equals("u")) {
                        car3.setCondition(u);
                        carService.update(car3);
                    } else if (s1.equals("s")) {
                        car3.setCondition(s);
                        carService.update(car3);
                    } else {
                    System.out.println("Podałeś błędny symbol.");
                    }
                    System.out.println("Zmieniłeś stan pojazdu :" + car3.toString() + " na: " + car3.getCondition());
                    break;
                case "8":
                    System.out.println("Podaj imię:");
                    Customers customers3 = new Customers();
                    customers3.setFirstName(scanner.nextLine());
                    System.out.println("Podaj nazwisko:");
                    customers3.setLastName(scanner.nextLine());
                    customerService.persist(customers3);
                    System.out.println("Dodałeś użytkownika: " + customers3);
                    break;
                case "9":
                    List<Customers> customers4 = customerService.findAll();
                    System.out.println("Klienci: ");
                    for (Customers cu : customers4) {
                        System.out.println(cu.toString()); }
                    System.out.println("Podaj id klienta, którego chcesz usunąć:");
                    int idDeleteCustomer = scanner.nextInt();
                    System.out.println("Usunąłeś klienta: " + customerService.findById(idDeleteCustomer));
                    customerService.delete(idDeleteCustomer);
                    break;
                case "10":
                    List<Rental> rentals2 = rentalService.findAll();
                    System.out.println("Aktualne wypożyczenia: ");
                    for (Rental r : rentals2) {
                        System.out.println(r.toString());
                    }
                    System.out.println("Podaj id wypożyczenia, dla którego chcesz zmienić datę zwrotu: ");
                    Rental rental1 = rentalService.findById(scanner.nextInt());
                    System.out.println("Podaj nową datę zwrotu samochodu: ");
                    scanner.nextLine();
                    rental1.setReturnDate(LocalDate.parse(scanner.nextLine()));
                    rentalService.update(rental1);
                    System.out.println("Wybrałeś datę zwrotu: " + rental1.getReturnDate());
                    break;
                case "12":
                    isWorking = false;
                    System.exit(5);
                    break;
            }
        } while (isWorking = true);
    }
}