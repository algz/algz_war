package com.sany.airmodelManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AirModelRepositoryImp implements AirModelRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AirModel> getAirModelList(AirModel am) {
		String sql="select * from S_AIRMODEL";
		List<AirModel> list=em.createNativeQuery(sql,AirModel.class).getResultList();
		return list;
	}
}
