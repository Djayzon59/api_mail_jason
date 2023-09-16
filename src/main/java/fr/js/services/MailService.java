package fr.js.services;

import fr.js.dto.Mail;
import fr.js.entities.ClientEntity;
import fr.js.entities.MailEntity;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import fr.js.repositories.ClientRepository;
import fr.js.repositories.MailRepository;
import jakarta.ws.rs.*;
import fr.js.tools.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Path("/mails/")
public class MailService {

    @Inject
    Mailer mailer;
    @Inject
    ClientRepository clientRepository;
    @Inject
    MailRepository mailRepository;

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMail(Mail mail, @HeaderParam("ApiKey") String apikey){

        ClientEntity clientEntity = clientRepository.findById(apikey);

        if(clientEntity == null || (clientEntity = clientRepository.findById(apikey)) == null)
            return Response.status(Response.Status.UNAUTHORIZED).entity("Clef d'api non valide !").build();
        if(!Validator.isValideMail(mail.getTo()))
            return Response.ok("Adresse mail invalide ! ").status(400).build();
        if(clientEntity.getListMailEntity()
                .stream()
                .filter(m -> m.getDate().toLocalDate().equals(LocalDate.now()))
                .count() > 100)
            return Response.ok("Quota dépassé !").status(400).build();

        MailEntity mailEntity = new MailEntity();
        mailEntity.setClient(clientEntity);
        mailEntity.setDate(LocalDateTime.now());
        mailEntity.setObjet(mail.getSubject());
        mailRepository.persist(mailEntity);

        mailer.send(io.quarkus.mailer.Mail.withText(mail.getTo(), mail.getSubject(), mail.getText()));

        return Response.ok(String.format("Le Message : %s, a été envoyé à : %s", mail.getSubject(), mail.getTo())).build();
    }
}
