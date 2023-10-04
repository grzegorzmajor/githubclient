package ovh.major.githubclient.domain.githubclient.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record RepoAndBranchesDto(
        String name,
        String ownerLogin,

        List<BranchDto> branches

) {
}
