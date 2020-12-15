package com.sany.dynamic;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.springframework.stereotype.Repository;

import com.sany.airmodelManager.AirModel;

@Repository
public class DynamicRepositoryImp implements DynamicReponsitory {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<?> GetDynamicGK(AirModel am) {
		String sql="select name,cname from S2_GK_DYNAMIC_VIEW "
				+ "where amplitudetype=? and compressionmode=? and zjywz=? and jyjywz=?";
		Query query=em.createNativeQuery(sql)
				    .setParameter(1, am.getAmplitudetype())
				    .setParameter(2, am.getCompressionmode())
				    .setParameter(3, am.getZjywz())
				    .setParameter(4, am.getJyjywz());
//		query.setParameter(param, value)

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

	@Transactional
	@Override
	public String SaveDynamicTask(DynamicTask task) {
		String sql="INSERT INTO S_DYNAMICTASK (id,TASKNAME,modelId,editor,zg_type,wholeTaskId)values(?,?,?,?,?,?)";
		Query query=em.createNativeQuery(sql)
				    .setParameter(1, task.getId())
				    .setParameter(2, task.getTaskName())
				    .setParameter(3, task.getModelId())
				    .setParameter(4, task.getEditor())
				    .setParameter(5, task.getZg_type())
				    .setParameter(6, task.getWholeTaskId());
		query.executeUpdate();
		return null;
	}

	


}
