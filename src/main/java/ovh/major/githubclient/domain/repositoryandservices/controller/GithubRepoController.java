package ovh.major.githubclient.domain.repositoryandservices.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoAdder;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoPatcher;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoRemover;
import ovh.major.githubclient.domain.repositoryandservices.GithubRepoRetriever;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoRequestDto;
import ovh.major.githubclient.domain.repositoryandservices.dto.GithubRepoResponseDto;

import java.util.List;

@RestController
@RequestMapping("/repodb")
public class GithubRepoController {

    GithubRepoAdder githubRepoAdder;
    GithubRepoPatcher githubRepoPatcher;
    GithubRepoRetriever githubRepoRetriever;
    GithubRepoRemover githubRepoRemover;

    @Autowired

    public GithubRepoController(GithubRepoAdder githubRepoAdder,
                                GithubRepoPatcher githubRepoPatcher,
                                GithubRepoRetriever githubRepoRetriever,
                                GithubRepoRemover githubRepoRemover) {
        this.githubRepoAdder = githubRepoAdder;
        this.githubRepoPatcher = githubRepoPatcher;
        this.githubRepoRetriever = githubRepoRetriever;
        this.githubRepoRemover = githubRepoRemover;
    }

    @GetMapping
    public ResponseEntity<List<GithubRepoResponseDto>> getRepos(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok(githubRepoRetriever.getRepos(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GithubRepoResponseDto> getRepo(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(githubRepoRetriever.getRepoBtId(id));
    }

    @PostMapping
    public ResponseEntity<GithubRepoResponseDto> addRepo(@RequestBody @Valid @NotBlank @NotEmpty GithubRepoRequestDto requestDto) {
        return ResponseEntity.ok(githubRepoAdder.addRepo(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRepo(@PathVariable(name = "id") Long id) {
        githubRepoRemover.deleteRepoById(id);
        return ResponseEntity.ok("Song deleted");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GithubRepoResponseDto> updateRepo(@PathVariable(name = "id") Long id, @RequestBody GithubRepoRequestDto requestDto){
        return ResponseEntity.ok(githubRepoPatcher.updateSong(id,requestDto));
    }

}
