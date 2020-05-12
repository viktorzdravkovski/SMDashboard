package com.viktor.ttt.service;

import com.viktor.ttt.taskTracker.v1.model.AddCommentConfirmation;
import com.viktor.ttt.taskTracker.v1.model.AddTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.Comment;
import com.viktor.ttt.taskTracker.v1.model.CommentBody;
import com.viktor.ttt.taskTracker.v1.model.DeleteCommentConfirmation;
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

  /**
   * Adds a comment to the task.
   *
   * @param commentBody the {@link Comment}.
   * @return {@link AddCommentConfirmation}.
   */
  AddCommentConfirmation addComment(CommentBody commentBody);

  /**
   * Deletes a comment by its id.
   *
   * @param id the id of the comment.
   * @return {@link DeleteCommentConfirmation}.
   */
  DeleteCommentConfirmation deleteComment(String id);
}
