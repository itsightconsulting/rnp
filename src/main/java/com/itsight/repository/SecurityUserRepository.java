package com.itsight.repository;

import com.itsight.domain.SecurityUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Integer> {

    @Override
    @EntityGraph(value = "securityUser")
    List<SecurityUser> findAll();

    @Query(value = "SELECT DISTINCT S FROM SecurityUser S JOIN FETCH S.roles R LEFT JOIN FETCH R.privileges P WHERE S.username = ?1")
    SecurityUser findByUsername(String username);

    @Query("UPDATE SecurityUser SET enabled = ?1  WHERE username = ?2")
    @Modifying
    void saveUserStatusByUsername(boolean status, String username);

    @Modifying
    @Query(value = "UPDATE SecurityUser SU SET SU.enabled =?2 WHERE SU.username = ?1")
    void updateEstadoByUsername(String username, boolean flag);

    @Query(value = "SELECT SU.password FROM SecurityUser SU WHERE SU.id = ?1")
    String findPasswordById(int id);

    @Modifying
    @Query(value = "UPDATE SecurityUser SU SET SU.password = ?1 WHERE SU.id = ?2")
    void actualizarPassword(String password, int id);

    @Query("SELECT S.id FROM SecurityUser S WHERE S.username = ?1")
    Integer findIdByUsername(String username);
}
