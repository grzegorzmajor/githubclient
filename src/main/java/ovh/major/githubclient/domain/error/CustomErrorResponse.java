package ovh.major.githubclient.domain.error;

public record CustomErrorResponse(
        Integer status,
        String message
) {
}
