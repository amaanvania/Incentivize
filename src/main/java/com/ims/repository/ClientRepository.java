package com.ims.repository;

import com.ims.beans.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {


    @Query(value = "SELECT * FROM incentive.clients WHERE user_name = ?1", nativeQuery = true)
    List<Client> getClientByUserName(String userName);

    @Query(value = "SELECT * FROM incentive.clients WHERE auth_code = ?1", nativeQuery = true)
    List<Client> getClientByAuthCode(String authCode);


}
