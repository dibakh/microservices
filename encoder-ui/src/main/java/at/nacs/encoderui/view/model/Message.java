package at.nacs.encoderui.view.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Message {
  
  @NotEmpty
  private String plaintext;

}
