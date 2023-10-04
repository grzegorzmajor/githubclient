package ovh.major.githubclient.domain.repositoryandservices.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.githubclient.domain.repositoryandservices.controller.GithubRepoController;

@ControllerAdvice(assignableTypes = GithubRepoController.class)
@Log4j2
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleException(NotFoundException exception){
        log.warn("NotFoundException while accessing song!");
        return new ErrorResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}