package com.Management.Students.repository;

import com.Management.Students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    boolean existsByLastName(String lastName);
    Optional<Student> findByEmail(String email);
//    boolean existsByFirstNameAndLastName(String firstName , String lastName);

    @Query("SELECT s FROM Student AS s WHERE " +
            "(:firstName IS NULL OR s.firstName = :firstName) AND " +
            "(:lastName IS NULL OR s.lastName = :lastName)")
    List<Student> getStudents(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);
}
//    List<Student>getStudent(@Param("firstname") Student firstName, @Param("Lastname") Student lastName);


