package ovh.major.githubclient.domain.dto.githubresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;


@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record GithubResponseRepoDto(
        String name,
        GithubOwnerDto owner,
        Boolean fork,
        String branches_url
) {
}
