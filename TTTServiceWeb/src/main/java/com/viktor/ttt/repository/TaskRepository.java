package com.viktor.ttt.repository;

import com.viktor.ttt.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for {@link Task}.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
