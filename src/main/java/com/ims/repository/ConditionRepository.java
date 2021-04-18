package com.ims.repository;

import com.ims.beans.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {

    @Query(value = "SELECT * FROM incentive.conditions WHERE incentive_id = ?1", nativeQuery = true)
    List<Condition> getConditionsByIncentive(int incentiveID);
}
