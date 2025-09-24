package com.store_sample.store.app.controller.advice;

import com.store_sample.store.app.exception.TooManyRequestsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {


  @ExceptionHandler(TooManyRequestsException.class)
  @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
  public String handleTooManyRequests(TooManyRequestsException e) {
    log.info("[ログ]流量制御の閾値を超過しました。");
    return "[レスポンス]流量制御の閾値を超過しました。1ユーザー当たり、1分間に2回までしかメッセージ投稿できません。";
  }

  @ExceptionHandler(Exception.class)
  public String handleException(Exception e) {
    log.error("[log]エラーが発生しました", e);
    return "[response]エラーが発生しました";
  }
}
