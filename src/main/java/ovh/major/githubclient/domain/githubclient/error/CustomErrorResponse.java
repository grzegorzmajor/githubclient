package ovh.major.githubclient.domain.githubclient.error;

public record CustomErrorResponse(
        Integer status,
        String message
) {
}
