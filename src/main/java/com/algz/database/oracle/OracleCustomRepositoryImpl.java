package com.algz.database.oracle;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OracleCustomRepositoryImpl implements OracleCustomRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public int ExecSQL(String sql) {
		return em.createNativeQuery(sql).executeUpdate();
	}

	@Override
	public List<Map<String, Object>> QuerySQL(String sql) {
		return em.createNativeQuery(sql).getResultList();
	}

	

}
