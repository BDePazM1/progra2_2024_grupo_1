/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Clientes;
import com.mycompany.repository.clienteRepository;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

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
}
