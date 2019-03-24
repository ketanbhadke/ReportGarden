package com.reportgarden.finalproject.controller;

import com.reportgarden.finalproject.dto.Repository;
import com.reportgarden.finalproject.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GithubController {

    @Autowired
    private GithubService githubService;

    //que 1 & 2
    // 1. list repositories by keyword
    // 2. list stars and fork counts against corresponding repositories
    @GetMapping("/search/repositories/{keyword}")
    public List<Repository> getAllReposByKeyword(@PathVariable String keyword){

        System.out.println("endpoint was hit + " + keyword);
        return githubService.getAllReposByKeyword(keyword);
    }

    @GetMapping("/track/packages/{user}/{repo}")
    public void trackPackagesOfRepo(@PathVariable String user, @PathVariable String repo){

        githubService.trackPackagesOfRepo(user, repo);
    }
}
