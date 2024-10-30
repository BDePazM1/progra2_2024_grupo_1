/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.model.Clientes;
import com.mycompany.repository.clienteRepository;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Freddy Elias
 */
@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class clienteController {

    @Inject
    clienteRepository clienteRepo;

    @POST

    public Response SaveCliente(Clientes nuevoCliente) {

        JSONObject JsonResponse = new JSONObject();

        try {
            clienteRepo.crate(nuevoCliente);
            System.out.println("nuevo " + nuevoCliente.toString());
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cliente creado!!");

            return Response.ok().entity(JsonResponse).build();

        } catch (Exception e) {
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Cliente NO creado!!");
            return Response.serverError().entity(JsonResponse).build();

        }
    }

    @GET
    public Response findClientes() {
        JSONObject JsonResponse = new JSONObject();
        try {

            List<Clientes> lstClientes = clienteRepo.findClientes();

            ObjectMapper objMapper = new ObjectMapper();
            String jsonArray = objMapper.writeValueAsString(lstClientes);

            JSONParser parser = new JSONParser();

            JSONArray jsonClientes = (JSONArray) parser.parse(jsonArray);

            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Consulta Exitosa");
            JsonResponse.put("Clientes", jsonClientes);

            return Response.ok().entity(JsonResponse).build();
        } catch (Exception e) {
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Consulta No Exitosa");
            return Response.serverError().build();
        }

    }

    @PUT
    @Path("/{dni}")
    public Response updateCliente(@PathParam("dni") long dni, Clientes clienteActualizado) {
        JSONObject jsonResponse = new JSONObject();
        try {
            // Obtener el cliente existente por DNI
            Clientes clienteExistente = clienteRepo.findById(dni);

            if (clienteExistente == null) {
                jsonResponse.put("Estado", 0);
                jsonResponse.put("Mensaje", "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND).entity(jsonResponse).build();
            }

            // Actualizar los campos
            clienteExistente.setNombre(clienteActualizado.getNombre());
            clienteExistente.setApellidos(clienteActualizado.getApellidos());
            clienteExistente.setDomicilio(clienteActualizado.getDomicilio());
            clienteExistente.setTelefono(clienteActualizado.getTelefono());

            // Guardar los cambios
            clienteRepo.updateCliente(clienteExistente);

            jsonResponse.put("Estado", 1);
            jsonResponse.put("Mensaje", "Cliente actualizado exitosamente");
            return Response.ok().entity(jsonResponse).build();

        } catch (Exception e) {
            jsonResponse.put("Estado", 0);
            jsonResponse.put("Mensaje", "Error al actualizar el cliente");
            return Response.serverError().entity(jsonResponse).build();
        }
    }
}
