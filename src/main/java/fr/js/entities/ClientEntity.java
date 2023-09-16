package fr.js.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "CLIENT")
public class ClientEntity {

    @Id
    @Column(name = "API_KEY")
    private String ApiKey;

    @Column(name = "NOM_CLIENT")
    private String NomClient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<MailEntity> listMailEntity;

    public ClientEntity() {
    }

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String apiKey) {
        ApiKey = apiKey;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }

    public List<MailEntity> getListMailEntity() {
        return listMailEntity;
    }

    public void setListMailEntity(List<MailEntity> listMailEntity) {
        this.listMailEntity = listMailEntity;
    }
}
