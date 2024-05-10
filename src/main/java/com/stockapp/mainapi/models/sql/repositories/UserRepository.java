package com.stockapp.mainapi.models.sql.repositories;

import com.stockapp.mainapi.models.sql.entitites.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findById(int id);
    UserModel findByUsername(final String username);
    UserModel findByEmail(final String email);
}
