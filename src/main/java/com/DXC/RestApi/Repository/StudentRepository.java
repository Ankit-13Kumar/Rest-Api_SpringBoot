package com.DXC.RestApi.Repository;

import com.DXC.RestApi.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//It is an interface of entity not class
    //JPA QUERIES in interface don't need body after that go to service layer
    List<Student> findByFirstName(String FirstName);
    //depend u want list or single value
 //Student findByFirstName(String FirstName);

    //................................................//
    //select * from student where first_name ='ankit' last_name='rajvanshi'
    //we will use And method proxy
    //order matter
    Student findByLastNameAndFirstName(String lastName,String firstName);

    //.....................................................//
    //Or Proxy
    //After go to service layer-3
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    //InQuery
    List<Student> findByFirstNameIn (List<String> firstNames);

    //like query step-3
    List<Student>findByFirstNameContains(String firstName);

    //start with query
    List<Student> findByFirstNameStartsWith(String firstName);

    //JPQL select query
    @Query("From Student where firstName=:firstName and lastName=:lastName")
    List<Student> gettingByLastNameAndFirstName(String firstName, String lastName);

    //JPQL Update Query
    //step-1
    //if ur query modify anything use both modifying and transactional
    //Interger will count
    @Modifying
    @Transactional
    @Query("Update Student set firstName=:firstName where id=:id")
    Integer updateFirstName (Long id,String firstName);

    //JPQL DELETE QUERY
    //STEP-3 after go to service layer
    //integer will provide count
    @Modifying
    @Transactional
    @Query("Delete From Student where firstName=:firstName")
    Integer deleteByFirstName(String firstName);

    //join relationship
    //step-4 after go to service layer
    List<Student> findByAddressCity (String city);
    //same as JPQL - JOIN ABOVE
    // address-object, city-feild ,Student-entity
    @Query("From Student where address.city = :city")
    List<Student> getByAddressCity (String city);
}
