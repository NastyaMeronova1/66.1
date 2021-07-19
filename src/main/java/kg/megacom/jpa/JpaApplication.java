package kg.megacom.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class JpaApplication {

    private static final Logger log = LoggerFactory.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer", "BauerJackBauer@gmail.com", "0552333222", 18, LocalDate.of(1999,12,1)));
            repository.save(new Customer("Chloe", "O'Brian", "chloeObrain@mail.ru", "0522999666", 22, LocalDate.of( 1997, 12, 12)));
            repository.save(new Customer("Kim", "Bauer", "kimBauer@mail.ru", "0546321789",12, LocalDate.of(2000,12,12)));
            repository.save(new Customer("Chloe", "Palmer", "kimBauer@mail.ru", "0555333666",55, LocalDate.of(2005,12,12)));
            repository.save(new Customer("David", "Palmer", "davidPalmer@gmail.com", "0555888999",37,LocalDate.of(2010,12,12)));
            repository.save(new Customer("Michelle", "Dessler", "michelleDessler@gmail.com", "0599333777",42,LocalDate.of(2000,12,12)));
            repository.save(new Customer("Jack", "Bauer", "jackBauer@gmail.com", "0552333222",3,LocalDate.of(2011,12,12)));

            log.info("Find customers by first name Like - %oe");
            log.info("--------------------------------------------");
            for (Customer customer: repository.findByFirstNameLike("%oe")){
                log.info(customer.toString());
            }
            log.info(" ");
            log.info("Find customers by first name not Like - %oe");
            log.info("--------------------------------------------");
            for (Customer customer: repository.findByFirstNameNotLike("%oe")){
                log.info(customer.toString());
            }
            log.info(" ");
            //fetch customer by birthday
            log.info("Find customers by birthday Between");
            log.info("--------------------------------------------");
            repository.findByBirthDayBetween(LocalDate.of(1999,12,1),
                    LocalDate.of( 2005, 12, 12))
                    .forEach(customer -> log.info(customer.toString()));
            log.info("");
            //find by age less than 40
            log.info("Find customers younger than 40");
            log.info("-------------------------------");
            for (Customer customer: repository.findByAgeLessThan(40)){
                log.info(customer.toString());
            }
            log.info(" ");
            //find by age greater than 40
            log.info("Find customers greater than 40");
            log.info("-------------------------------");
            for (Customer customer: repository.findByAgeGreaterThan(40)){
                log.info(customer.toString());
            }
            log.info(" ");
            // fetch customers by last name or email address
            log.info("Customers found by last name or email");
            log.info("-------------------------------");
            for (Customer customer: repository.findByLastNameOrEmailAddress("Palmer", "jackBauer@gmail.com")){
                log.info(customer.toString());
            }
            log.info(" ");
            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");
            // fetch customers by email address
            log.info("Customers found with emailAddress():");
            log.info("-------------------------------");
            for (Customer customer : repository.findByEmailAddress("kimBauer@mail.ru")) {
                log.info(customer.toString());
            }
            log.info("");
            // fetch customers by phone number
            log.info("Customers found with phone number():");
            log.info("-------------------------------");
            for (Customer customer : repository.findByPhoneNumber("0546321789")) {
                log.info(customer.toString());
            }
            log.info("");
            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");
            //fetch distinct customer by last name and first name
            log.info("Customer found distinct customer by last name and first name:");
            log.info("--------------------------------");
            for (Customer customer1 : repository.findDistinctByLastNameAndFirstName("Bauer", "Jack")) {
                log.info(customer1.toString());
            }
            log.info("");
            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
            //fetch customer by first name
            log.info("Customer found with findByFirstName('Chloe'):");
            log.info("--------------------------------------------");
            repository.findByFirstName("Chloe").forEach(chloe -> {
                log.info(chloe.toString());
            });
            log.info("");
        };
    }
}
