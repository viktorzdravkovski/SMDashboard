package com.viktor.ttt.controller;

import com.viktor.ttt.service.TaskTrackingService;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.service.TaskTrackerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for the social media accounts API.
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
}
