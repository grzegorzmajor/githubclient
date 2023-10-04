package ovh.major.githubclient.domain.repositoryandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import ovh.major.githubclient.domain.repositoryandservices.error.NotFoundException;

@Service
@Transactional
public class GithubRepoRemover {

    GitHubRepoRepository gitHubRepoRepository;

    @Autowired
    public GithubRepoRemover(GitHubRepoRepository gitHubRepoRepository) {
        this.gitHubRepoRepository = gitHubRepoRepository;
    }

    public void deleteRepoById(Long id) {
        if (!gitHubRepoRepository.existsById(id)) {
            throw getNotFoundException(id);
        }
        gitHubRepoRepository.deleteById(id);
    }

    private static NotFoundException getNotFoundException(Long id) {
        return new NotFoundException("Repository with id " + id + " not found!");
    }
}
