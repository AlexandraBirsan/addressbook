package com.addressbook.service.contacts;

import com.addressbook.dto.AllContactResponseDto;
import com.addressbook.model.Contact;
import com.addressbook.dto.ContactDto;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by birsan on 5/9/2016.
 */
@Path("/contacts")
public class ContactsWebService {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AllContactResponseDto getAll() {
        List<ContactDto> contacts = ContactDtoUtils.getContacts();
        AllContactResponseDto responseDTO = new AllContactResponseDto();
        responseDTO.setData(contacts);
        return responseDTO;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Contact contact) {
        try {
            ContactsServiceImpl.getInstance().createContact(contact);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not create the contact!").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(Contact contact){
        try {
            ContactsServiceImpl.getInstance().updateContact(contact);
            return  Response.status(Response.Status.OK).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not update the contact!").build();
        }
    }
}