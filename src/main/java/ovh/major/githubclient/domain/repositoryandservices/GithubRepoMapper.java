package ovh.major.githubclient.domain.repositoryandservices;

import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoResponseDto;

public class GithubRepoMapper {

    public static GithubRepoEntity toGithubRepoEntity(String owner, String name) {
        return new GithubRepoEntity(owner, name);
    }

    public static GithubRepoResponseDto toGithubRepoResponseDto(GithubRepoEntity entity) {
        return GithubRepoResponseDto.builder()
                .id(entity.getId())
                .owner(entity.getOwner())
                .name(entity.getName())
                .build();
    }
}
