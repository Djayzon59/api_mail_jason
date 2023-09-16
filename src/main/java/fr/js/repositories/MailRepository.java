package fr.js.repositories;

import fr.js.entities.MailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MailRepository implements PanacheRepositoryBase<MailEntity, Integer> {
}
