package com.algz.platform.common.file.pathencode;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algz.platform.security.authority.permissionManager.APermission;

public interface APathCodeRepository extends JpaRepository<APathCode, String>{

}
