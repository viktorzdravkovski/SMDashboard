package com.viktor.ttt.controller;

import com.viktor.ttt.service.TaskTrackingService;
import com.viktor.ttt.taskTracker.v1.model.AddCommentConfirmation;
import com.viktor.ttt.taskTracker.v1.model.AddCommentRequestBody;
import com.viktor.ttt.taskTracker.v1.model.AddTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.AddTaskRequestBody;
import com.viktor.ttt.taskTracker.v1.model.Comment;
import com.viktor.ttt.taskTracker.v1.model.CommentBody;
import com.viktor.ttt.taskTracker.v1.model.DeleteCommentConfirmation;
import com.viktor.ttt.taskTracker.v1.model.DeleteTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import com.viktor.ttt.taskTracker.v1.service.TaskTrackerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The REST Controller for the Task Tracker API.
 */
@RestController
public class TaskTrackerController implements TaskTrackerApi {

  private final TaskTrackingService taskTrackingService;

  /**
   * The constructor with mandatory parameter.
   *
   * @param taskTrackingService the social media service.
   */
  public TaskTrackerController(TaskTrackingService taskTrackingService) {
    this.taskTrackingService = taskTrackingService;
  }

  @Override
  public ResponseEntity<List<Task>> getTasks() {
    return ResponseEntity.ok(taskTrackingService.getAllTasks());
  }

  @Override
  public ResponseEntity<Task> getTask(@PathVariable String id) {
    return ResponseEntity.ok(taskTrackingService.getTask(id));
  }

  @Override
  public ResponseEntity<AddTaskConfirmation> addTask(@RequestBody AddTaskRequestBody body) {
    TaskRequestBody task = body.getTask();
    AddTaskConfirmation addTaskConfirmation = taskTrackingService.addTask(task);
    return ResponseEntity.ok(addTaskConfirmation);
  }

  @Override
  public ResponseEntity<DeleteTaskConfirmation> deleteTask(@PathVariable String id) {
    DeleteTaskConfirmation deleteTaskConfirmation = taskTrackingService.deleteTask(id);
    return ResponseEntity.ok(deleteTaskConfirmation);
  }

  @Override
  public ResponseEntity<AddCommentConfirmation> addComment(@RequestBody AddCommentRequestBody body) {
    CommentBody commentBody = body.getComment();
    taskTrackingService.addComment(commentBody);
    return null;
  }

  @Override
  public ResponseEntity<DeleteCommentConfirmation> deleteComment(@PathVariable String id) {
    DeleteCommentConfirmation deleteCommentConfirmation = taskTrackingService.deleteComment(id);
    return ResponseEntity.ok(deleteCommentConfirmation);
  }
}
