package mye.fisio.mak.apimak.services;

import java.util.List;

import mye.fisio.mak.apimak.domain.Client;

public interface IClientService {

    public Client createClient(Client client);

    public Client updateClient(Client client);

    public Client getClientById(Integer id);

    public Client getClientByDocument(String document);

    public Client getClientByEmail(String email);

    public Client getClientByPhone(String phone);

    public boolean deleteClient(Integer id);

    public List<Client> getAllClients();
    
}
