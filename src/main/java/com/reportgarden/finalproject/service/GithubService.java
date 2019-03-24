package com.reportgarden.finalproject.service;

import com.reportgarden.finalproject.dto.PackageContent;
import com.reportgarden.finalproject.dto.Repository;
import com.reportgarden.finalproject.dto.Response;
import com.reportgarden.finalproject.entity.DependencyCount;
import com.reportgarden.finalproject.entity.RepoContent;
import com.reportgarden.finalproject.repository.DependencyCountRepository;
import com.reportgarden.finalproject.repository.PackagesRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GithubService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private DependencyCountRepository dependencyCountRepository;

    @Value("${github.server.url}")
    private String githubBaseUrl;


    public List<Repository> getAllReposByKeyword(String keyword) {

        String url = githubBaseUrl + "/search/repositories?q=" + keyword;

        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(url, Response.class);

        return ((null != responseEntity) && (null != responseEntity.getBody())) ? responseEntity.getBody().getItems() : new ArrayList<>();
    }

    public void trackPackagesOfRepo(String user, String repo) {

        String url = githubBaseUrl + "/repos/" + user + "/" + repo + "/contents/package.json" ;

        ResponseEntity<PackageContent> responseEntity = restTemplate.getForEntity(url, PackageContent.class);

        byte[] decoder = Base64.decodeBase64(responseEntity.getBody().getContent());
        String dStr = new String(decoder);
//        System.out.println("Decoded string: "+dStr);

        JSONObject jsonObj = new JSONObject(dStr);
        JSONObject dependencies = jsonObj.getJSONObject("dependencies");

        Iterator keys = dependencies.keys();
        List<String> keyList = new ArrayList<>();

        Map<String, String> dependenciesMap = new HashMap<>();
        StringBuilder str = new StringBuilder();
        while(keys.hasNext()){
            String key = (String)keys.next();
            str.append(key + " , ");
            keyList.add(key);
            dependenciesMap.put(key, dependencies.getString(key));


            DependencyCount dependencyCount = new DependencyCount();
            dependencyCount.setDependencyName(key);
            boolean isDependencyPresent = dependencyCountRepository.existsById(key);
            if(isDependencyPresent){
                dependencyCount.setDependencyCount(dependencyCountRepository.findById(key).get().getDependencyCount() + 1);
            }
            else{
                dependencyCount.setDependencyCount(1);
            }

            dependencyCountRepository.save(dependencyCount);
        }
        System.out.println("Saving !!!");

        RepoContent repoContent = new RepoContent();
//        repoContent.setId(1);
        repoContent.setRepo(repo);
        repoContent.setDependenciesMap(str.toString());
        packagesRepository.save(repoContent);

        System.out.println("SAved !!!");

        packagesRepository.findAll()
        .forEach(item -> System.out.println("repo : " + item.getDependenciesMap()) );

        dependencyCountRepository.findAll()
                .forEach(item -> System.out.println("dependency name :" + item.getDependencyName() + " count: " + item.getDependencyCount()));

    }
}
