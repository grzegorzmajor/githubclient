package ovh.major.githubclient.domain.dto;

import lombok.Builder;

@Builder
public record BranchDto(
        String name,
        String lastCommitSha
) {
}
