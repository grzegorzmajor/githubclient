package ovh.major.githubclient.domain.repositoryandservices;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "repo")
class GithubRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String owner;
    @Column(nullable = false)
    private String name;

    public GithubRepoEntity(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }
}
