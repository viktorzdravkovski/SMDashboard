package com.viktor.ttt.taskTracker.v1.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.viktor.ttt.taskTracker.v1.model.TaskRequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AddTaskRequestBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-13T14:00:57.614+02:00")

public class AddTaskRequestBody   {
  @JsonProperty("task")
  private TaskRequestBody task = null;

  public AddTaskRequestBody task(TaskRequestBody task) {
    this.task = task;
    return this;
  }

  /**
   * Get task
   * @return task
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TaskRequestBody getTask() {
    return task;
  }

  public void setTask(TaskRequestBody task) {
    this.task = task;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddTaskRequestBody addTaskRequestBody = (AddTaskRequestBody) o;
    return Objects.equals(this.task, addTaskRequestBody.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(task);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddTaskRequestBody {\n");
    
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
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

