package com.pg.repository;

import com.pg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByMailId(@Param("mailId") String mailId);

    User findByMailIdAndPassword(@Param("mailId") String mailId, @Param("password") String password);
}
