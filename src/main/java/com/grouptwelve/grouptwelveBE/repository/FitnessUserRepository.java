package com.grouptwelve.grouptwelveBE.repository;

import com.grouptwelve.grouptwelveBE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FitnessUserRepository extends JpaRepository<User, Long> {

    // save() = insert or update,
    // findAll() = select * from
    // findById() = select * from where id=?
    // deleteById() = delete from where id=? 

    User findByEmail(String email);                  // WHERE email = ?
    List<User> findByName(String name);              // WHERE name = ?

    boolean existsByEmail(String email);             // SELECT COUNT(*) WHERE email = ?
    void deleteByEmail(String email);                // DELETE WHERE email = ?
}  
