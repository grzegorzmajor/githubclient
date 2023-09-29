package ovh.major.githubclient.domain.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.githubclient.domain.error.CustomErrorResponse;
import ovh.major.githubclient.domain.error.exception.UserNotFoundException;

@ControllerAdvice
class UserNotFoundErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public CustomErrorResponse handleUserNotFoundException(Exception e) {
        return new CustomErrorResponse(404, e.getMessage());
    }
}
