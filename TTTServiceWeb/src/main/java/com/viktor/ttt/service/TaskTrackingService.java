package com.viktor.ttt.service;

import com.viktor.ttt.taskTracker.v1.model.AddTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.DeleteTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;

import java.util.List;

/**
 * Service for social media interaction.
 */
public interface TaskTrackingService {

  /**
   * Get all tasks.
   *
   * @return a list of {@link Task}.
   */
  List<Task> getAllTasks();

  /**
   * Add a task to the database.
   *
   * @param task the task.
   * @return {@link AddTaskConfirmation}.
   */
  AddTaskConfirmation addTask(TaskRequestBody task);

  /**
   * Get a task by id.
   *
   * @param id the id of the task.
   * @return {@link Task}.
   */
  Task getTask(String id);

  /**
   * Deletes a task by its id.
   *
   * @param id the id of the task.
   * @return {@link DeleteTaskConfirmation}.
   */
  DeleteTaskConfirmation deleteTask(String id);
}
