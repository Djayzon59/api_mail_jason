package fr.js.services;

import fr.js.dto.ClientDto;
import fr.js.entities.ClientEntity;
import fr.js.repositories.ClientRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;
import java.util.List;

@Path("/clients/")
@Produces(MediaType.APPLICATION_JSON)
public class ClientService {

    @Inject
    private ClientRepository clientRepository;


    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500", description = "Le serveur a rencontré un problème !")
    public Response createCLient(ClientDto clientDto, @Context UriInfo uriInfo){
        ClientEntity newClient = new ClientEntity();
        newClient.setApiKey(clientDto.getApiKey());
        newClient.setNomClient(clientDto.getNomClient());
        clientRepository.persist(newClient);
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        uriBuilder.path(newClient.getApiKey());

        return Response.created(uriBuilder.build()).build();
    }

    @GET
    public Response getAll(){
        List <ClientEntity> listClientEntity = clientRepository.listAll();
        ArrayList <ClientDto> listClientDto = new ArrayList<>(ClientDto.toDoList(listClientEntity));
        return Response.ok(listClientDto).build();
    }


}
