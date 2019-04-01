package com.yci.asclepius.repository;


import com.yci.asclepius.patient.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PatientRepository extends PagingAndSortingRepository<Patient,Long> {

    List<Patient> findByLastNameStartsWithIgnoreCase(String lastName);
}
