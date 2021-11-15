package com.cf611.regulationManager;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegulationRepository extends JpaRepository<Regulation, String> {

	
	@Query(nativeQuery = true, value = "SELECT * FROM CF_REGULATION_VIEW where modelId=?1",
			countQuery="SELECT COUNT(1) FROM CF_REGULATION_VIEW where modelId=?1")
	public Page<Map<String,Object>> getRegulations(String modelId,Pageable pageable);

}
