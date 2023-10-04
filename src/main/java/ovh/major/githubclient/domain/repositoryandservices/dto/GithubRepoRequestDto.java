package ovh.major.githubclient.domain.repositoryandservices.dto;

import lombok.Builder;

@Builder
public record GithubRepoRequestDto(
        String owner,
        String name
){
}
