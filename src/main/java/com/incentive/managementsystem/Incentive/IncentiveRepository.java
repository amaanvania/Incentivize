package com.incentive.managementsystem.Incentive;

import com.incentive.managementsystem.Client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncentiveRepository extends JpaRepository<Incentive, Integer> {

    @Query(value = "SELECT * FROM INCENTIVES WHERE projectID = ?1", nativeQuery = true)
    List<Incentive> getIncentivesByProject(int projectID);


}
