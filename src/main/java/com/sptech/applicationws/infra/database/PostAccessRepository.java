package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.PostAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAccessRepository extends JpaRepository<PostAccess, Long>{
}
