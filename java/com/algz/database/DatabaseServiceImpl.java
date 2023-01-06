package com.algz.database;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algz.database.oracle.OracleRepository;

@Service
public class DatabaseServiceImpl implements DatabaseService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private DatabaseRepository repository;
	
	public void a() {
		repository.ExecSQL(null);
//		oracleRepository.
//		repository.findAllUser();
	}

	@Override
	public List<Map<String, Object>> findAllUsers() {
		return repository.findAllUsers();
	}

	@Override
	public List<Map<String, Object>> findAllTableSpaces() {
		return repository.findAllTableSpaces();
	}

	@Override
	public List<Map<String, Object>> findAllTables(String owner) {
		return repository.findAllTables(owner);
	}

	@Override
	public List<Map<String, Object>> findAllViews(String owner) {
		return repository.findAllViews(owner);
	}

	@Override
	public int ExecSQL(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}
}
