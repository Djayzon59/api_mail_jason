package fr.js.repositories;

import fr.js.entities.ClientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<ClientEntity, String> {
}
