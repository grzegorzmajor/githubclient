package ovh.major.githubclient.domain.repositoryandservices;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoRequestDto;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoResponseDto;

@Service
@Transactional
public class GithubRepoAdder {

    private final GitHubRepoRepository gitHubRepoRepository;

    @Autowired
    public GithubRepoAdder(GitHubRepoRepository gitHubRepoRepository) {
        this.gitHubRepoRepository = gitHubRepoRepository;
    }

    public GithubRepoResponseDto addRepo(GithubRepoRequestDto githubRepoRequestDto) {
        GithubRepoEntity entity = GithubRepoMapper.toGithubRepoEntity(
                githubRepoRequestDto.owner(),
                githubRepoRequestDto.name());
        GithubRepoEntity response = gitHubRepoRepository.save(entity);
        return GithubRepoMapper.toGithubRepoResponseDto(response);
    }


}
