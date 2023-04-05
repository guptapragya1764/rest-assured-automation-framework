package com.nagp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activities {

  private String activity_id;
  private String owner_type;
  private String owner_id;
  private String owner_value;
  private String action;
  private String trackable_id;
  private String trackable_type;
  private String trackable_value;
  private String message;
}
