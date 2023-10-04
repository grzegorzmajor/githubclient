package ovh.major.githubclient.domain.repositoryandservices;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoRequestDto;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoResponseDto;
import ovh.major.githubclient.domain.repositoryandservices.error.NotFoundException;

import java.util.Optional;

@Service
@Transactional
public class GithubRepoPatcher {

    GitHubRepoRepository gitHubRepoRepository;

    @Autowired
    public GithubRepoPatcher(GitHubRepoRepository gitHubRepoRepository) {
        this.gitHubRepoRepository = gitHubRepoRepository;
    }

    public GithubRepoResponseDto updateSong(Long id, GithubRepoRequestDto githubRepoRequestDto) {

        GithubRepoEntity entity = gitHubRepoRepository.findById(id)
                .orElseThrow(() -> getNotFoundException(id));
        if (githubRepoRequestDto.name() != null) {
            entity.setName(githubRepoRequestDto.name());
        }
        if (githubRepoRequestDto.owner() != null) {
            entity.setOwner(githubRepoRequestDto.owner());
        }
        gitHubRepoRepository.updateById(id, entity);
        GithubRepoEntity updatedRepo = gitHubRepoRepository.findById(id)
                .get();
        return GithubRepoMapper.toGithubRepoResponseDto(updatedRepo);


    }

    private static NotFoundException getNotFoundException(Long id) {
        return new NotFoundException("Repository with id " + id + " not found!");
    }


}