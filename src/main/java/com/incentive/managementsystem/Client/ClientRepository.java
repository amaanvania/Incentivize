package com.incentive.managementsystem.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    @Query(value = "SELECT * FROM clients WHERE user_name = ?1", nativeQuery = true)
    List<Client> getClientByUserName(String userName);

    @Query(value = "SELECT * FROM clients WHERE auth_code = ?1", nativeQuery = true)
    List<Client> getClientByAuthCode(String authCode);


}
