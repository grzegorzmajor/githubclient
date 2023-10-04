package ovh.major.githubclient.domain.repositoryandservices.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }


}
