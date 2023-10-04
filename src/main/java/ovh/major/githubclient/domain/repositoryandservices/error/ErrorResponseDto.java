package ovh.major.githubclient.domain.repositoryandservices.error;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(String message, HttpStatus httpStatus) {
}
