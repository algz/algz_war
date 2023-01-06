package com.sany.Static;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.algz.platform.common.sql.dao.SQLRepository;

@Repository
public class StaticReponsitoryImp implements StaticReponsitory {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<?> GetCondinate(String partid) {
		String sql="select AXISCNAME,AXISNAME from S2_GK_AXIS where partid='"+partid+"'";
		return em.createNativeQuery(sql).getResultList();
	}

	@Override
	public List<?> ReceiveStaticGK(String partid) {
		String sql="select NAME,CNAME from S2_GK_STATIC_view where partid='"+partid+"'";
		Query query=em.createNativeQuery(sql);
		
		//query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		return query.getResultList();
	}

	@Override
	public String SaveStaticTask(StaticTask task) {
		String sql="";
		em.createNativeQuery(sql).executeUpdate();
		return null;
	}

	@Override
	public String GetSimulationReport(String taskid, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
