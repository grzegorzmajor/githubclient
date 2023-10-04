package ovh.major.githubclient.domain.repositoryandservices;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
interface GitHubRepoRepository extends Repository<GithubRepoEntity, Long> {

    GithubRepoEntity save(GithubRepoEntity repo);

    @Modifying
    @Query("DELETE FROM GithubRepoEntity r WHERE r.id = :id")
    void deleteById(Long id);

    @Query("SELECT r FROM GithubRepoEntity r")
    List<GithubRepoEntity> findAll(Pageable pageable);

    @Query("SELECT r FROM GithubRepoEntity r WHERE r.id = :id")
    Optional<GithubRepoEntity> findById(Long id);

    @Modifying
    @Query("UPDATE GithubRepoEntity r SET r.owner = :#{#repo.owner}, r.name = :#{#repo.name} WHERE r.id = :id")
    void updateById(Long id, GithubRepoEntity repo);

    Boolean existsById(Long id);

}
