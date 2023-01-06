package com.algz.platform.common.file.pathencode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface APathCodeRepository extends JpaRepository<APathCode, String>{

	@Modifying
//	@Transactional
	@Query(nativeQuery = true,value="delete from ALGZ_PATHCODE where relationid=?1")
	public void delAPathCodeByRelationId(String RelationId);
}
