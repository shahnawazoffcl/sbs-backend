package com.shah.sbsbackend.globalExceptionHandler;

import com.shah.sbsbackend.exceptions.MessageNotSentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestGlobalExceptionHandler {

    @ExceptionHandler(value = {MessageNotSentException.class})
    protected ResponseEntity<String> handleMessageNotSent(MessageNotSentException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidTokenException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

}
