package com.addressbook.service.contacts;

import com.addressbook.dto.AllContactResponseDto;
import com.addressbook.exceptions.ValidationException;
import com.addressbook.model.Contact;
import com.addressbook.dto.ContactDto;
import com.addressbook.validators.ContactValidator;

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
        try {
            List<ContactDto> contacts = ContactDtoUtils.getContacts();
            AllContactResponseDto responseDTO = new AllContactResponseDto();
            responseDTO.setData(contacts);
            return responseDTO;
        } catch (Exception ex){
            ex.printStackTrace();
            return new AllContactResponseDto();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Contact contact) {
        try {
            ContactValidator.getInstance().validateContact(contact);
            ContactsServiceImpl.getInstance().createContact(contact);
            return Response.status(Response.Status.OK).entity("Contact successfully created.").build();
        } catch (ValidationException validationException) {
            validationException.printStackTrace();
            return Response.status(Response.Status.CONFLICT).entity(validationException.getMessages().toString()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not create the contact!" + ex.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(Contact contact) {
        try {
            ContactValidator.getInstance().validateContact(contact);
            ContactsServiceImpl.getInstance().updateContact(contact);
            return Response.status(Response.Status.OK).entity("Contact successfully updated.").build();
        } catch (ValidationException validationException) {
            validationException.printStackTrace();
            return Response.status(Response.Status.CONFLICT).entity(validationException.getMessages().toString()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not update the contact." + e.getMessage()).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            ContactsServiceImpl.getInstance().deleteContact(id);
            return Response.status(Response.Status.OK).entity("Contact successfully deleted.").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Could not delete the contact." + e.getMessage()).build();
        }
    }
}
