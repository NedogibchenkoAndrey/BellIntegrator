package ru.bellintegrator.task.response.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.task.response.exception.DataNotFoundException;
import ru.bellintegrator.task.response.view.ErrorView;

import java.util.UUID;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorView notValidError(MethodArgumentNotValidException ex) {
//        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        return new ErrorView(ex.getMessage() + "; Status code: " + HttpStatus.BAD_REQUEST);
    }


    //404
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorView dataNotFound(DataNotFoundException ex) {
        return new ErrorView(ex.getMessage() + "; Status code: " + HttpStatus.NOT_FOUND);
    }


    //500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView internalServerError(Throwable e) {
        String errorUUID = UUID.randomUUID().toString();
        log.error("Error UUID: " + errorUUID + "; " + e.getCause().fillInStackTrace());
        return new ErrorView("Internal Server Error!; Error code: " + errorUUID);
    }
}
