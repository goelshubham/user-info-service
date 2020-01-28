package com.userinfoservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userinfoservice.entity.Usage;

@Repository
public interface UsageRepository extends CrudRepository<Usage, Integer>{

	@Query("SELECT usage FROM Usage usage where usage.userId = :userId AND usage.usageType = :usageType AND usage.usageTime >= :date")
	public List<Usage> usageListForUsageType(String userId, String usageType, Date date);
	
	@Query("SELECT usage FROM Usage usage where usage.userId = :userId AND usage.usageTime >= :date")
	public List<Usage> usageListAllUsageType(String userId, Date date);
	
}
