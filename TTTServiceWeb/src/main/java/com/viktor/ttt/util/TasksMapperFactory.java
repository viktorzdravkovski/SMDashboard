package com.viktor.ttt.util;

import com.viktor.ttt.taskTracker.v1.model.Task;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import com.viktor.ttt.taskTracker.v1.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory class for mapping task resources.
 */
public final class TasksMapperFactory {

  /**
   * Private constructor to prevent instantiation.
   */
  private TasksMapperFactory() {
    throw new AssertionError("Do not instantiate!");
  }

  /**
   * Maps a {@link Task} object from {@link com.viktor.ttt.model.Task}.
   *
   * @param taskModel the task model.
   * @return a task resource.
   */
  public static Task mapTaskResourceFromModel(com.viktor.ttt.model.Task taskModel) {

    String taskName = taskModel.getTaskName();
    String taskDescription = taskModel.getTaskDescription();
    List<com.viktor.ttt.model.User> users = taskModel.getUsers();
    List<User> usersResource = mapUserResourceListFromModelList(users);

    Task taskResource = new Task();
    taskResource.setName(taskName);
    taskResource.setDescription(taskDescription);
    taskResource.setAssignedUsers(usersResource);

    return taskResource;
  }

  /**
   * Maps a list of {@link Task} objects from a list of {@link com.viktor.ttt.model.Task}.
   *
   * @param taskList the list of task models.
   * @return a list of task resources.
   */
  public static List<Task> mapTaskResourceListFromModelList(List<com.viktor.ttt.model.Task> taskList) {
    return taskList.stream()
        .map(TasksMapperFactory::mapTaskResourceFromModel)
        .collect(Collectors.toList());
  }

  /**
   * Maps a {@link com.viktor.ttt.model.Task} object from {@link Task}.
   *
   * @param taskRequestBody the task resource.
   * @param userList        the user list.
   * @return a mapped Task model.
   */
  public static com.viktor.ttt.model.Task mapTaskModelFromResource(
      TaskRequestBody taskRequestBody,
      List<com.viktor.ttt.model.User> userList) {

    String name = taskRequestBody.getTaskName();
    String description = taskRequestBody.getTaskDescription();

    com.viktor.ttt.model.Task taskModel = new com.viktor.ttt.model.Task();
    taskModel.setTaskName(name);
    taskModel.setTaskDescription(description);
    taskModel.setUsers(userList);

    return taskModel;
  }

  /**
   * Maps a list of {@link User} objects from a list of {@link com.viktor.ttt.model.User}.
   *
   * @param userList the list of user models.
   * @return a list of user resources.
   */
  private static List<User> mapUserResourceListFromModelList(List<com.viktor.ttt.model.User> userList) {
    return userList.stream()
        .map(user -> {
          String email = user.getEmail();
          String firstName = user.getFirstName();
          String lastName = user.getLastName();
          String username = user.getUsername();
          String fullName = String.format("%s %s", firstName, lastName);

          User userResource = new User();
          userResource.setEmail(email);
          userResource.setFullName(fullName);
          userResource.setUsername(username);

          return userResource;
        })
        .collect(Collectors.toList());
  }

}
