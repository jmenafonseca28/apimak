package mye.fisio.mak.apimak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mye.fisio.mak.apimak.data.ClientServiceJpa;
import mye.fisio.mak.apimak.data.CommentsServiceJpa;
import mye.fisio.mak.apimak.domain.Client;
import mye.fisio.mak.apimak.domain.Comments;
import mye.fisio.mak.apimak.security.Validations;

@RestController
@RequestMapping("/fisiomak/api/v1")
public class ControllerApi {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientServiceJpa clientServiceJpa;

    @Autowired
    private CommentsServiceJpa commentsServiceJpa;

    @Autowired
    private Validations validations;

    // GET, POST, PUT, DELETE for Clients

    @PostMapping("/register")
    public ResponseEntity<Client> register(@RequestBody Client client) {

        System.out.println("register: " + client);

        if (!validations.registerValidation(client)) {
            return ResponseEntity.badRequest().build();
        }

        Client clientInDB = clientServiceJpa.getClientByEmail(client.getEmail());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        clientInDB = clientServiceJpa.getClientByDocument(client.getCedula());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        clientInDB = clientServiceJpa.getClientByPhone(client.getPhone());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        if (client.getRole() == null || client.getRole().isBlank()) {
            client.setRole("user");
        }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Client clientCreated = clientServiceJpa.createClient(client);
        return clientCreated != null ? ResponseEntity.ok(clientCreated) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/clients/username/{username}")
    public ResponseEntity<Client> getClientByUsername(@PathVariable String username) {
        Client client = clientServiceJpa.getClientByEmail(username);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientServiceJpa.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        if (!validations.registerValidation(client)) {
            return ResponseEntity.badRequest().build();
        }

        if (client.getRole() == null || client.getRole().isBlank()) {
            client.setRole("user");
        }

        Client clientInDB = clientServiceJpa.getClientByEmail(client.getEmail());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        clientInDB = clientServiceJpa.getClientByDocument(client.getCedula());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        clientInDB = clientServiceJpa.getClientByPhone(client.getPhone());
        if (clientInDB != null) {
            return ResponseEntity.badRequest().build();
        }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        Client clientCreated = clientServiceJpa.createClient(client);
        return clientCreated != null ? ResponseEntity.ok(clientCreated) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/clients")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client clientUpdated = clientServiceJpa.updateClient(client);
        return clientUpdated != null ? ResponseEntity.ok(clientUpdated) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/clients")
    public ResponseEntity<Client> deleteClient(@RequestBody Client client) {

        return clientServiceJpa.deleteClient(client.getId()) ? ResponseEntity.ok(client)
                : ResponseEntity.badRequest().build();
    }

    // GET, POST, PUT, DELETE for Comments

    @GetMapping("/comments")
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentsServiceJpa.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        Comments commentCreated = commentsServiceJpa.createComment(comment);
        return commentCreated != null ? ResponseEntity.ok(commentCreated) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/comments")
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comment) {
        Comments commentUpdated = commentsServiceJpa.updateComment(comment);
        return commentUpdated != null ? ResponseEntity.ok(commentUpdated) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/comments")
    public ResponseEntity<Comments> deleteComment(@RequestBody Comments comment) {
        return commentsServiceJpa.deleteComment(comment) ? ResponseEntity.ok(comment)
                : ResponseEntity.badRequest().build();
    }

}
