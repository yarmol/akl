package com.gtm.lukspay.devices.repository;

import com.gtm.lukspay.devices.model.DeviceEntryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceEntityEntryRepository extends PagingAndSortingRepository<DeviceEntryEntity,Long> {

}
