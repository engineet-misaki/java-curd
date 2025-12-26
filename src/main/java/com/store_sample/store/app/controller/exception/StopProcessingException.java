package com.store_sample.store.app.controller.exception;

import lombok.Getter;

public class StopProcessingException extends RuntimeException {

  @Getter
  private ResponseCode err;

  public StopProcessingException(ResponseCode errCode, Object... args) {
    super(errCode.getMessage(args));
    err = errCode;
  }

}
