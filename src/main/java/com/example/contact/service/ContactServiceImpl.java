package com.example.contact.service;

import com.example.contact.domain.Contact;
import com.example.contact.domain.GitRepositories;
import com.example.contact.repository.ContactRepository;
import com.example.contact.repository.GitRepositoriesRepository;
import com.example.contact.rest.dto.ReqContactDTO;
import com.example.contact.rest.dto.ResSaveContactDTO;
import lombok.RequiredArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.contact.rest.dto.ResSaveContactDTO.Response.GIT_REPOSITORY_NOT_FOUND;
import static com.example.contact.rest.dto.ResSaveContactDTO.Response.REPOSITORIES_ADDED_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final GitRepositoriesRepository gitRepositoriesRepository;
    private final HttpClient httpClient;
    private final HttpRequest.Builder httpRequest;

    @Override
    public ResponseEntity<List<Contact>> searchByDetail(ReqContactDTO entity) {
        return ResponseEntity.ok(
                contactRepository.searchByDetail(entity.getName(), entity.getPhoneNumber(), entity.getEmail(), entity.getOrganization(), entity.getGitHub())
        );
    }

    @Override
    public ResponseEntity<ResSaveContactDTO> saveContact(ReqContactDTO entity) {
        Contact contact = contactRepository.save(entity.map());

        ResSaveContactDTO resSaveContactDTO = new ResSaveContactDTO();
        resSaveContactDTO.setContact(contact);

        String uri = "https://api.github.com/users/" + entity.getGitHub() + "/repos";
        HttpRequest request = httpRequest.uri(URI.create(uri)).build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    List<GitRepositories> repositories = new ArrayList<>();
                    try {
                        JSONArray albums = new JSONArray(response);
                        for (int i = 0; i < albums.length(); i++) {
                            JSONObject album = albums.getJSONObject(i);
                            String name = album.getString("name");
                            repositories.add(new GitRepositories(name, contact));
                        }
                        gitRepositoriesRepository.saveAll(repositories);
                        resSaveContactDTO.setResponse(REPOSITORIES_ADDED_SUCCESSFULLY);
                    } catch (Exception e) {
                        resSaveContactDTO.setResponse(GIT_REPOSITORY_NOT_FOUND);
                    }
                })
                .join();

        return ResponseEntity.ok(resSaveContactDTO);
    }

}
