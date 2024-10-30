/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Cuentas;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


/**
 *
 * @author Freddy Elias
 */
@Path("/cuenta")
public class cuentaController {
    public Response nuevaCuenta(Cuentas nuevaCuenta) {
        if (nuevaCuenta == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("La cuenta es nula").build();
        }
        System.out.println("nuevo Cliente -->" + nuevaCuenta.toString());
        return Response.ok().build();
    }
}
