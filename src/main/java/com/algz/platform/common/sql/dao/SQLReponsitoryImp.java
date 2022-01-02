package com.algz.platform.common.sql.dao;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.Transformers;

/**
 * 使用query.unwrap，必须添加事务（@Transactional(readOnly=true)），不然会报异常：com.sun.proxy.$Proxy267 cannot be cast to org.hibernate.query.internal.NativeQueryImpl
 * @author algz
 *
 */
@Repository
@Transactional(readOnly=true)
public class SQLReponsitoryImp implements SQLRepository {

//	@Autowired
//	private SessionFactory sf;
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("serial")
	public List<?> ExecuteDataTable(String sql) {
		Query query=em.createNativeQuery(sql);
		SetQueryForMap(query);
		
		List<?> list= query.getResultList();
		return list;
		
	}

	
	private Query SetQueryForMap(Query query) {
//		Query query=em.createNativeQuery(sql);
		query.unwrap(NativeQueryImpl.class).setResultTransformer(new AliasedTupleSubsetResultTransformer() {
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
		return query;
	}
	
	public BigDecimal ExecuteScalar(String sql) {
		BigDecimal i=(BigDecimal)em.createNativeQuery(sql).getSingleResult();
		return i;
	}

	@Transactional
	public Integer ExecuteNonQuery(String sql) {
		Integer i=em.createNativeQuery(sql).executeUpdate();
		return i;
	}

	public Page<?> ExecuteDataTable(String sql, Pageable pageable) {
		List<?> list=this.ExecuteDataTable(sql);
		Page<?> page=new PageImpl<>(list,pageable,list.size());
		return page;
	}



	
	
}
