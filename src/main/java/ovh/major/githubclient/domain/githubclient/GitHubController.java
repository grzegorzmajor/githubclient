package ovh.major.githubclient.domain.githubclient;

import com.sun.jdi.BooleanValue;
import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import ovh.major.githubclient.domain.githubclient.dto.RepoAndBranchesDto;
import ovh.major.githubclient.domain.githubclient.dto.githubresponse.GithubResponseRepoDto;
import ovh.major.githubclient.domain.githubclient.error.exception.UserNotFoundException;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoAdder;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoMapper;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
class GitHubController {

    private final GitHubClient gitHubClient;
    private final GitHubService gitHubService;
    private final GithubRepoAdder githubRepoAdder;

    @Autowired
    public GitHubController(GitHubClient gitHubClient, GitHubService gitHubService, GithubRepoAdder githubRepoAdder) {
        this.gitHubClient = gitHubClient;
        this.gitHubService = gitHubService;
        this.githubRepoAdder = githubRepoAdder;
    }

    @SneakyThrows
    @GetMapping("/github/{userName}")
    ResponseEntity<List<RepoAndBranchesDto>> github(@PathVariable String userName, @RequestHeader("Accept") String headerValue) {
        throwExceptionIfMediaTypeIsNotJson(headerValue);
        ArrayList<GithubResponseRepoDto> requestRepos = getRepositoriesFromGithub(userName);
        return ResponseEntity.ok().body(gitHubService.filterAndGetBranches(requestRepos));
    }

    @SneakyThrows
    @GetMapping("/github/{userName}/{copyConfirmation}")
    ResponseEntity<List<RepoAndBranchesDto>> github(@PathVariable String userName, @PathVariable(name="copyConfirmation") Boolean copyConfirmed) {
        ArrayList<GithubResponseRepoDto> requestRepos = getRepositoriesFromGithub(userName);
        List<RepoAndBranchesDto> reposAndBranches = gitHubService.filterAndGetBranches(requestRepos);
        if (copyConfirmed) {
            reposAndBranches.stream().forEach( repo -> {
                GithubRepoRequestDto githubRepoRequestDto = new GithubRepoRequestDto(repo.ownerLogin(), repo.name());
                githubRepoAdder.addRepo(githubRepoRequestDto);
            });
        }
        return ResponseEntity.ok().body(reposAndBranches);
    }

    private ArrayList<GithubResponseRepoDto> getRepositoriesFromGithub(String userName) {
        try {
            return gitHubClient.getRepos(userName);
        } catch (FeignException.NotFound exception) {
            throw new UserNotFoundException("ERROR: User " + userName + " not found!");
        }
    }

    private static void throwExceptionIfMediaTypeIsNotJson(String headerValue) throws HttpMediaTypeNotAcceptableException {
        if (!Objects.equals(headerValue, "application/json")) {
            throw new HttpMediaTypeNotAcceptableException("ERROR: " + headerValue + " is not accepted response data format!");
        }
    }
}
