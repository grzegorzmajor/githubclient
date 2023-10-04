package ovh.major.githubclient.domain.githubclient.error.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.githubclient.domain.githubclient.error.CustomErrorResponse;

@ControllerAdvice
class InvalidHeaderErrorHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(406, e.getMessage());
        return "{\"status\":" + customErrorResponse.status() + ", \"message\":\"" + customErrorResponse.message() + "\"}";
    }
}