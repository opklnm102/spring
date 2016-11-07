package me.dong.github;

import lombok.RequiredArgsConstructor;
import me.dong.exception.NotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class GithubClient {

    private final RestTemplate restTemplate;

    private final static String GITHUB_URL = "https://api.github.com";

    @Cacheable("github.user")
    public GithubUser getUser(String githubId){
        return invoke(createRequestEntity(
                String.format(GITHUB_URL + "/users/%s", githubId)), GithubUser.class).getBody();
    }

    private <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> type){
        try {
            return this.restTemplate.exchange(request, type);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new NotFoundException("not found");
            }
            throw e;
        }
    }

    private RequestEntity<?> createRequestEntity(String url){
        try{
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        }catch (URISyntaxException e){
            throw new IllegalStateException("Invalid URL " + url, e);
        }
    }
}
