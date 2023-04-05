package com.nagp.response;

import java.util.List;
import lombok.Getter;

@Getter
public class GetActivityResponse {

  private int page;
  private boolean last_page;
  private List<Activities> activities;
}
