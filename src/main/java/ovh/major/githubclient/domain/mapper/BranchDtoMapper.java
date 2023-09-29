package ovh.major.githubclient.domain.mapper;

import ovh.major.githubclient.domain.dto.BranchDto;
import ovh.major.githubclient.domain.dto.githubresponse.GithubResponseBranchDto;

public class BranchDtoMapper {

    public static BranchDto fromGithubResponseBranchDto(GithubResponseBranchDto source) {
        return BranchDto.builder()
                .name(source.name())
                .lastCommitSha(source.commit().sha())
                .build();
    }
}
