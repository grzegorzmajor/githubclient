package ovh.major.githubclient.domain.githubclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ovh.major.githubclient.domain.githubclient.dto.githubresponse.GithubResponseBranchDto;
import ovh.major.githubclient.domain.githubclient.dto.githubresponse.GithubResponseRepoDto;

import java.util.ArrayList;


@Component
@FeignClient(value = "github-client", url = "${app.url}")
interface GitHubClient {

    @GetMapping("/users/{userName}/repos")
    ArrayList<GithubResponseRepoDto> getRepos(@PathVariable String userName);

    @GetMapping("{branchUrl}")
    ArrayList<GithubResponseBranchDto> getBranches(@PathVariable String branchUrl);

}
