package com.sany.airmodelManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface AirModelRepository1 extends CrudRepository<AirModel, String>  {

//	@Query(name = "select * from S_AIRMODEL where upper(modelname)=upper(?) and upper(COMPRESSIONMODE)=upper(?) "
//			+ " and upper(ZJYWZ)=upper(?) and upper(JYJYWZ)=upper(?) ",nativeQuery = true)
//	public AirModel findByAirModel(String modelname,String amplitudetype,String compressionmode,String zjywz,String jyjywz);	
	
	//AirModel findByModelname(String modelname);
}
