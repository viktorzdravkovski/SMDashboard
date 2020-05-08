package com.viktor.ttt.service.impl;

import com.viktor.ttt.model.User;
import com.viktor.ttt.repository.TaskRepository;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.service.TaskTrackingService;
import com.viktor.ttt.taskTracker.v1.model.AddTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.DeleteTaskConfirmation;
import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import com.viktor.ttt.util.TasksMapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TaskTrackingService}.
 */
@Service
public class TaskTrackingServiceImpl implements TaskTrackingService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;

  /**
   * The constructor with mandatory parameter.
   *
   * @param taskRepository the task repository.
   */
  public TaskTrackingServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<Task> getAllTasks() {

    List<com.viktor.ttt.model.Task> tasks = taskRepository.findAll();
    return TasksMapperFactory.mapTaskResourceListFromModelList(tasks);
  }

  @Override
  public Task getTask(String id) {

    int taskId = Integer.parseInt(id);
    Optional<com.viktor.ttt.model.Task> optionalTask = taskRepository.findById(taskId);
    if (optionalTask.isPresent()) {

      com.viktor.ttt.model.Task task = optionalTask.get();
      return TasksMapperFactory.mapTaskResourceFromModel(task);
    }

    return null;
  }

  @Override
  public AddTaskConfirmation addTask(TaskRequestBody task) {

    List<String> assignedUsernames = task.getAssignedUsernames();
    List<User> allUsersByUsername = userRepository.findAllByUsernameIsIn(assignedUsernames);

    com.viktor.ttt.model.Task taskModel = TasksMapperFactory.mapTaskModelFromResource(task, allUsersByUsername);
    taskRepository.save(taskModel);
    AddTaskConfirmation addTaskConfirmation = new AddTaskConfirmation();
    addTaskConfirmation.setMessage("Task added successfully");

    return addTaskConfirmation;
  }

  @Override
  public DeleteTaskConfirmation deleteTask(String id) {

    int taskId = Integer.parseInt(id);
    taskRepository.deleteById(taskId);
    DeleteTaskConfirmation deleteTaskConfirmation = new DeleteTaskConfirmation();
    deleteTaskConfirmation.setMessage("Task deleted successfully");

    return deleteTaskConfirmation;
  }
}
