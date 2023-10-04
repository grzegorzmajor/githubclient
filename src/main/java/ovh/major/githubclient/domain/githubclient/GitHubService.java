package ovh.major.githubclient.domain.githubclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ovh.major.githubclient.domain.githubclient.dto.BranchDto;
import ovh.major.githubclient.domain.githubclient.dto.RepoAndBranchesDto;
import ovh.major.githubclient.domain.githubclient.dto.githubresponse.GithubResponseRepoDto;
import ovh.major.githubclient.domain.githubclient.mapper.BranchDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
class GitHubService {

    private final GitHubClient gitHubClient;

    @Autowired
    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<RepoAndBranchesDto> filterAndGetBranches(ArrayList<GithubResponseRepoDto> repos) {
        return repos.stream()
                .filter(repo -> !repo.fork())
                .map(this::getBuildRepo)
                .toList();
    }

    private RepoAndBranchesDto getBuildRepo(GithubResponseRepoDto repo) {
        return RepoAndBranchesDto.builder()
                .name(repo.name())
                .ownerLogin(repo.owner().login())
                .branches(getBranchesFromGithub(cleanBranchUrl(repo.branches_url())))
                .build();
    }

    private List<BranchDto> getBranchesFromGithub(String branchesUrl) {
        return gitHubClient.getBranches(branchesUrl)
                .stream()
                .map(BranchDtoMapper::fromGithubResponseBranchDto)
                .collect(Collectors.toList());
    }

    private String cleanBranchUrl(String string) {
        return string.replace("{/branch}", "").replace("https://api.github.com/", "");
    }
}
