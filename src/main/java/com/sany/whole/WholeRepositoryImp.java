package com.sany.whole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class WholeRepositoryImp implements WholeRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String AddWholeTask(WholeTask wt) {
		em.persist(wt);
		return null;
	}

	@Override
	public String AddWeightFocusParam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddJDZB_Param() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddJDJL_Param() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddWorkParam() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
