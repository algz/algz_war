package com.sany.other;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.common.file.pathencode.APathCodeRepository;

@Repository
public class OtherReponsitoryImp implements OtherReponsitory {

	@Autowired
	private APathCodeRepository pcRepository;
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public List<?> GetTaskState(String taskid, String kind) {
		String sql="";
		if(kind.equals("D")) {
			//动力学
			sql="select state,stateinfo from S_DYNAMICTASK where id='"+taskid+"'";
		}else {
			//静力学
			sql="select state,stateinfo from S_STATICTASK where id='"+taskid+"'";
		}
		
		return em.createNativeQuery(sql).getResultList();
	}


	@Override
	public List<?> GetSimulationParts() {
		String sql="select id,partname,partcode from S_PART";
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
		//return em.createNativeQuery(sql).getResultList();
	}

	@Override
	public List<?> GetWholeParamTemplate(String templateId) {
		String sql="select * from S_WHOLE_TEMPLATE where parentid='"+templateId+"'";
		return em.createNativeQuery(sql).getResultList();
	}
	
	@Override
	public List<?> GetWholeParamTemplateParam(String templateId) {
		String sql="select name,cname,valtype,unit from S_WHOLE_TEMPLATEPARAM where templateid='"+templateId+"'";
		return em.createNativeQuery(sql).getResultList();
	}
	
	@Override
	public String GetSimulationReport(String taskid, String category) {
		String sql="";
		if(category.equals("D")) {
			//动力学
			sql="select ID,REPORTPATH,REPORTSUMMARY from S_DYNAMICTASK where id='"+taskid+"'";
		}else {
			//静力学
			sql="select ID,REPORTPATH,REPORTSUMMARY from S_STATICTASK where id='"+taskid+"'";
		}
		
		Object[] report=(Object[])em.createNativeQuery(sql).getSingleResult();
		
		APathCode pc=new APathCode();
		pc.setRelationId(report[0]+"");
		Example<APathCode> example = Example.of(pc);
		List<APathCode> list=pcRepository.findAll(example);
		if(list.size()==0) {
			pc.setRelationId(report[0]+"");
			pc.setFilePath(report[1]+"");
			pc.setRemark(report[2]+"");
			pcRepository.save(pc);
		}else {
			pc=pcRepository.findAll(example).get(0);
		}


		return pc.getId();
	}





	
}
