package com.sany.dynamic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sany.airmodelManager.AirModel;

public interface DynamicReponsitory {

	List<?> GetDynamicGK(AirModel am);
	
	String SaveDynamicTask(DynamicTask task);
	
}
