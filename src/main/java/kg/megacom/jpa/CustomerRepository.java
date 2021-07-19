package kg.megacom.jpa;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findDistinctByLastNameAndFirstName(String lastName, String firstName);

    Customer findById(long id);

    List<Customer> findByEmailAddress(String emailAddress);

    List<Customer> findByPhoneNumber(String phoneNumber);

    List<Customer> findByLastNameOrEmailAddress(String lastName, String email);

    List<Customer> findByAgeLessThan(int age);

    List<Customer> findByAgeGreaterThan(int age);

    List<Customer> findByBirthDayBetween(LocalDate before, LocalDate after);

    List<Customer> findByFirstNameLike(String firstName);

    List<Customer> findByFirstNameNotLike(String firstName);
}