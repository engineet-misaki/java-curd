package com.store_sample.store.app.controller.exception;

import lombok.Getter;

public enum ResponseCode {
  OK("OK001", ""),
  CHANNEL_NOT_FOUND("NF0001", "チャンネルが存在しません。"),
  DUPLICATE_CHANNEL_MEMBER("DU0001", "%sと%sの組み合わせは既に存在しています。"),
  ;

  @Getter
  private String detailCode;
  private String message;

  private ResponseCode(String df, String msg) {
    detailCode = df;
    message = msg;
  }

  public String getMessage(Object... args) {
    return String.format(message, args);
  }

}
