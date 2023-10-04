package ovh.major.githubclient.domain.githubclient.dto;

import lombok.Builder;

@Builder
public record BranchDto(
        String name,
        String lastCommitSha
) {
}
