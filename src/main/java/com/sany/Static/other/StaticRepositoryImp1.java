package com.sany.Static.other;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.springframework.stereotype.Repository;

import com.sany.Static.StaticTask;

@Repository
public class StaticRepositoryImp1 implements StaticReponsitory1 {

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<?> GetDynamicZH(String modelId) {
		String sql = "SELECT Dynamicid,mbdname,state  FROM S_STATICTASK_DYNAMICTASK_VIEW where modelid='" + modelId + "' order by mbdname desc";
		Query query=em.createNativeQuery(sql);

		query.unwrap(NativeQueryImpl.class).setResultTransformer(new AliasedTupleSubsetResultTransformer() {
			private static final long serialVersionUID = 6730108100501285753L;
	
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Map<String,Object> result = new LinkedHashMap<String,Object>(tuple.length);
				for ( int i=0; i<tuple.length; i++ ) {
					String alias = aliases[i];
					if ( alias!=null ) {
						result.put( alias, tuple[i] );
					}
				}
				return result;
			}
	
			@Override
			public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
				return false;
			}
		});
	
		return query.getResultList();
	}

	
	public String SaveStaticTask(StaticTask task) {
		String sql="";
		em.createNativeQuery(sql).executeUpdate();
		return null;
	}

}
