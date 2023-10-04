package ovh.major.githubclient.domain.repositoryandservices.dto;

import lombok.Builder;

@Builder
public record GithubRepoResponseDto(
        Long id,
        String owner,
        String name
){
}
