package ovh.major.githubclient.domain.githubclient.mapper;

import ovh.major.githubclient.domain.githubclient.dto.BranchDto;
import ovh.major.githubclient.domain.githubclient.dto.githubresponse.GithubResponseBranchDto;

public class BranchDtoMapper {

    public static BranchDto fromGithubResponseBranchDto(GithubResponseBranchDto source) {
        return BranchDto.builder()
                .name(source.name())
                .lastCommitSha(source.commit().sha())
                .build();
    }
}
