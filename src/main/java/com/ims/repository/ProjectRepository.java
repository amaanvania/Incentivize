package com.ims.repository;

import com.ims.beans.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(value = "SELECT * FROM incentive.projects WHERE client_id = ?1", nativeQuery = true)
    List<Project> getProjectsUnderClient(int clientID);
}
