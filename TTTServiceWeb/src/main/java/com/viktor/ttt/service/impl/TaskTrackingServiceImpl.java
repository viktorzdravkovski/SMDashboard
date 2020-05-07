package com.viktor.ttt.service.impl;

import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.service.TaskTrackingService;
import com.viktor.ttt.taskTracker.v1.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link TaskTrackingService}.
 */
@Service
public class TaskTrackingServiceImpl implements TaskTrackingService {

  @Autowired
  private final UserRepository userRepository;

  /**
   * The constructor with mandatory parameter.
   *
   * @param userRepository the user repository.
   */
  public TaskTrackingServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<Task> getAllTasks() {
    return null;
  }
}
