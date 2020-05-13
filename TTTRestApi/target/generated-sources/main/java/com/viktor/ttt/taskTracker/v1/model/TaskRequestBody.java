package com.viktor.ttt.taskTracker.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TaskRequestBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-13T00:43:09.136+02:00")

public class TaskRequestBody   {
  @JsonProperty("taskName")
  private String taskName = null;

  @JsonProperty("taskDescription")
  private String taskDescription = null;

  @JsonProperty("assignedUsernames")
  @Valid
  private List<String> assignedUsernames = null;

  public TaskRequestBody taskName(String taskName) {
    this.taskName = taskName;
    return this;
  }

  /**
   * Get taskName
   * @return taskName
  **/
  @ApiModelProperty(example = "taskName", value = "")


  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public TaskRequestBody taskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
    return this;
  }

  /**
   * Get taskDescription
   * @return taskDescription
  **/
  @ApiModelProperty(example = "Task Description", value = "")


  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public TaskRequestBody assignedUsernames(List<String> assignedUsernames) {
    this.assignedUsernames = assignedUsernames;
    return this;
  }

  public TaskRequestBody addAssignedUsernamesItem(String assignedUsernamesItem) {
    if (this.assignedUsernames == null) {
      this.assignedUsernames = new ArrayList<>();
    }
    this.assignedUsernames.add(assignedUsernamesItem);
    return this;
  }

  /**
   * Get assignedUsernames
   * @return assignedUsernames
  **/
  @ApiModelProperty(value = "")


  public List<String> getAssignedUsernames() {
    return assignedUsernames;
  }

  public void setAssignedUsernames(List<String> assignedUsernames) {
    this.assignedUsernames = assignedUsernames;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskRequestBody taskRequestBody = (TaskRequestBody) o;
    return Objects.equals(this.taskName, taskRequestBody.taskName) &&
        Objects.equals(this.taskDescription, taskRequestBody.taskDescription) &&
        Objects.equals(this.assignedUsernames, taskRequestBody.assignedUsernames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskName, taskDescription, assignedUsernames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskRequestBody {\n");
    
    sb.append("    taskName: ").append(toIndentedString(taskName)).append("\n");
    sb.append("    taskDescription: ").append(toIndentedString(taskDescription)).append("\n");
    sb.append("    assignedUsernames: ").append(toIndentedString(assignedUsernames)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

