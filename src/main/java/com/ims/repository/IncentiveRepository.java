package com.ims.repository;

import com.ims.beans.Incentive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncentiveRepository extends JpaRepository<Incentive, Integer> {

    @Query(value = "SELECT * FROM incentive.incentives WHERE projectID = ?1", nativeQuery = true)
    List<Incentive> getIncentivesByProject(int projectID);

    @Query(value = "SELECT * FROM incentive.incentives WHERE id = ?1", nativeQuery = true)
    List<Incentive> getIncentivesByID(int id);


    @Query(value = "SELECT i.id \n" +
            "FROM incentive.projects p join incentive.incentives i \n" +
            "on p.id = i.projectid\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Integer> getIncentivesIDsByclientID(int clientID);

    @Query(value = "SELECT i.id, i.category, i.name, i.projectid\n" +
            "FROM incentive.projects p join incentive.incentives i\n" +
            "on p.id = i.projectid\n" +
            "where i.id = ?1", nativeQuery = true)
    List<Incentive> getIncentivesByclientID(int clientID);

}
