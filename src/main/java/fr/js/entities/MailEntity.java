package fr.js.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "MAILS")
public class MailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAIL")
    private int id;

    @Column(name = "DATE_MESSAGE")
    private LocalDateTime date;

    @Column(name = "OBJET_MESSAGE")
    private String objet;

    @Column(name = "DESTINATAIRE")
    private String destinataire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "API_KEY")
    private ClientEntity client;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
