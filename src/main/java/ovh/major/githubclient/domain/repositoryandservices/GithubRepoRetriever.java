package ovh.major.githubclient.domain.repositoryandservices;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoResponseDto;
import ovh.major.githubclient.domain.repositoryandservices.error.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GithubRepoRetriever {

    private final GitHubRepoRepository gitHubRepoRepository;
    @Autowired
    public GithubRepoRetriever(GitHubRepoRepository gitHubRepoRepository) {
        this.gitHubRepoRepository = gitHubRepoRepository;
    }

    public List<GithubRepoResponseDto> getRepos(Pageable pageable) {
        List<GithubRepoEntity> entities = gitHubRepoRepository.findAll(pageable);
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(GithubRepoMapper::toGithubRepoResponseDto)
                .toList();
    }

    public GithubRepoResponseDto getRepoBtId(Long id) {
        return GithubRepoMapper.toGithubRepoResponseDto(
                gitHubRepoRepository.findById(id)
                        .orElseThrow(() -> getNotFoundException(id)));
    }

    private static NotFoundException getNotFoundException(Long id) {
        return new NotFoundException("Repository with id " + id + " not found!");
    }


}
