package fr.js.dto;

import fr.js.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;

public class ClientDto {

    String apiKey;
    String nomClient;

    public ClientDto(){}
    public ClientDto(ClientEntity clientEntity){
        this.apiKey = clientEntity.getApiKey();
        this.nomClient = clientEntity.getNomClient();
    }

    public static List<ClientDto> toDoList (List<ClientEntity> listeClientEntity){
        List<ClientDto> listeClientDto = new ArrayList<>();
        for(ClientEntity clientEntity : listeClientEntity){
            listeClientDto.add(new ClientDto(clientEntity));
        }
        return listeClientDto;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
