package ru.vtb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayResult {
  String resulMessage;
  String resultCode;
}
