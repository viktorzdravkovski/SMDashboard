package com.viktor.ttt.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import org.springframework.http.HttpStatus;

public class AuthenticationResponse {

  @JsonProperty
  private String message;

  @JsonProperty
  @JsonDeserialize(using = EnumSetDeserializer.class)
  private HttpStatus status;

  @JsonCreator
  public AuthenticationResponse() {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
