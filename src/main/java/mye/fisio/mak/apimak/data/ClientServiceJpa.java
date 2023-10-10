package mye.fisio.mak.apimak.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import mye.fisio.mak.apimak.config.ErrorMessages;
import mye.fisio.mak.apimak.domain.Client;
import mye.fisio.mak.apimak.repository.ClientRepository;
import mye.fisio.mak.apimak.services.IClientService;

@Service
@Primary
public class ClientServiceJpa implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {

        if (client == null) {
            System.out.println(ErrorMessages.CLIENT_IS_NULL);
            return null;
        }

        if (clientRepository.findById(client.getId()).isPresent()) {
            System.out.println("Client with id " + client.getId() + " " + ErrorMessages.CLIENT_ALREADY_EXISTS);
            return null;
        }

        return clientRepository.save(client);
    }

    @Override
    public Client getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    @Override
    public boolean deleteClient(Integer id) {

        if (id == null) {
            System.out.println(ErrorMessages.CLIENT_ID_IS_NULL);
            return false;
        }

        if (!clientRepository.findById(id).isPresent()) {
            System.out.println("Client with id " + id + " " + ErrorMessages.CLIENT_NOT_FOUND);
            return false;
        }

        clientRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientByDocument(String document) {
        return clientRepository.findByCedula(document);
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client getClientById(Integer id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client updateClient(Client client) {

        if (client == null) {
            System.out.println(ErrorMessages.CLIENT_IS_NULL);
            return null;
        }

        Client clientFound = clientRepository.findByCedula(client.getCedula());

        if (clientFound == null) {
            System.out.println("Client with id " + client.getId() + " " + ErrorMessages.CLIENT_NOT_FOUND);
            return null;
        }

        if (clientFound.equals(client)) {
            System.out.println("Client with id " + client.getId() + " " + ErrorMessages.CLIENT_NO_HAS_CHANGES);
            return client;
        }

        return clientRepository.save(client);
    }

}