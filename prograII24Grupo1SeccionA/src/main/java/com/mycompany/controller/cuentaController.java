/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.model.Cuentas;
import com.mycompany.repository.cuentaRepository;
import java.util.List;
import javax.inject.Inject;
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
@Path("/cuenta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class cuentaController {

    @Inject
    cuentaRepository cuentaRepo;

    @POST

    public Response SaveCuenta(Cuentas nuevaCuenta) {

        JSONObject JsonResponse = new JSONObject();

        try {
            cuentaRepo.crate(nuevaCuenta);
            System.out.println("nuevo " + nuevaCuenta.toString());
            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Cuenta creada!!");

            return Response.ok().entity(JsonResponse).build();

        } catch (Exception e) {
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "¡Cuenta no creada!");
            return Response.serverError().entity(JsonResponse).build();

        }
    }

    @GET
    public Response findCuenta() {
        JSONObject JsonResponse = new JSONObject();
        try {

            List<Cuentas> lstCuentas = cuentaRepo.findCuentas();

            ObjectMapper objMapper = new ObjectMapper();
            String jsonArray = objMapper.writeValueAsString(lstCuentas);

            JSONParser parser = new JSONParser();

            JSONArray jsonCuentas = (JSONArray) parser.parse(jsonArray);

            JsonResponse.put("Estado", 1);
            JsonResponse.put("Mensaje", "Consulta Exitosa");
            JsonResponse.put("Cuentas", jsonCuentas);

            return Response.ok().entity(JsonResponse).build();
        } catch (Exception e) {
            JsonResponse.put("Estado", 0);
            JsonResponse.put("Mensaje", "Consulta No Exitosa");
            return Response.serverError().build();
        }

    }

    @PUT
    @Path("/{idCuenta}")
    public Response updateCuenta(@PathParam("idCuenta") long idCuenta, Cuentas cuentaActualizada) {
        JSONObject jsonResponse = new JSONObject();
        try {
            // Obtener cuenta existente por id
            Cuentas cuentaExistente = cuentaRepo.findById(idCuenta);

            if (cuentaExistente == null) {
                jsonResponse.put("Estado", 0);
                jsonResponse.put("Mensaje", "Cuenta no encontrada.");
                return Response.status(Response.Status.NOT_FOUND).entity(jsonResponse).build();
            }

            // Actualizar los campos
            cuentaExistente.setNumCuenta(cuentaActualizada.getNumCuenta());
            cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
            cuentaExistente.setSaldo(cuentaActualizada.getSaldo());

            // Guardar los cambios
            cuentaRepo.updateCuenta(cuentaExistente);

            jsonResponse.put("Estado", 1);
            jsonResponse.put("Mensaje", "Cuenta actualizada exitosamente.");
            return Response.ok().entity(jsonResponse).build();

        } catch (Exception e) {
            jsonResponse.put("Estado", 0);
            jsonResponse.put("Mensaje", "Error al actualizar el cliente");
            return Response.serverError().entity(jsonResponse).build();
        }
    }
}
