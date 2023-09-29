package ovh.major.githubclient.domain.dto.githubresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubOwnerDto(
    String login
) {
}
