package com.viktor.ttt.repository;

import com.viktor.ttt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository for {@link Comment}.
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
}
