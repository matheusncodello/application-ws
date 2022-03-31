package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(
            String email
    );

    User findByDocument(
            String document
    );
}
