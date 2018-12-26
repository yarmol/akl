package com.yci.aesculapus.repository;


import com.yci.aesculapus.patient.Patient;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PatientRepository extends PagingAndSortingRepository<Patient,Long> {

    List<Patient> findByLastNameStartsWithIgnoreCase(String lastName);
}
