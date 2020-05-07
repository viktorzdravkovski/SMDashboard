package com.viktor.ttt.service;

import com.viktor.ttt.taskTracker.v1.model.Task;

import java.util.List;

/**
 * Service for social media interaction.
 */
public interface TaskTrackingService {

  /**
   * Get all accounts.
   *
   * @return a list of {@link Task}.
   */
  List<Task> getAllTasks();
}
