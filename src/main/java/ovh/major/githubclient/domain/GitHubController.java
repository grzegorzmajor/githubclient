package ovh.major.githubclient.domain;

import feign.FeignException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import ovh.major.githubclient.domain.dto.RepoAndBranchesDto;
import ovh.major.githubclient.domain.dto.githubresponse.GithubResponseRepoDto;
import ovh.major.githubclient.domain.error.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
class GitHubController {

    private final GitHubClient gitHubClient;
    private final GitHubService gitHubService;

    @Autowired
    public GitHubController(GitHubClient gitHubClient, GitHubService gitHubService) {
        this.gitHubClient = gitHubClient;
        this.gitHubService = gitHubService;
    }

    @SneakyThrows
    @GetMapping("/github/{userName}")
    ResponseEntity<List<RepoAndBranchesDto>> github(@PathVariable String userName, @RequestHeader("Accept") String headerValue) {
        throwExceptionIfMediaTypeIsNotJson(headerValue);
        ArrayList<GithubResponseRepoDto> requestRepos = getRepositoriesFromGithub(userName);
        return ResponseEntity.ok().body(gitHubService.filterAndGetBranches(requestRepos));
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
