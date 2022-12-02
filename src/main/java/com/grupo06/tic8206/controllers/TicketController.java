package com.grupo06.tic8206.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.grupo06.tic8206.repos.TicketDAO;
import com.grupo06.tic8206.mysql.entities.Ticket;

import java.util.List;
@CrossOrigin
@Controller
public class TicketController {
    static final String basePath = "/restful";

    // DAO (Data Access Object) / CRUD
    @Autowired
    TicketDAO tDao;

    // GET - Extraer info de bbdd (listar todos los usuarios)
    @RequestMapping(value = basePath + "/tickets", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Ticket>> getAllUsers() {
        List<Ticket> ticketList = null;
        try {
            ticketList = tDao.findAll();
            if (ticketList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    // GET (by id) - Extraer info de bbdd (listar un usuario por id)
    @RequestMapping(value = basePath + "/tickets/{num_referencia}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Ticket> getUser(@PathVariable int num_referencia) {
        Ticket fromDbTicket = null;
        try {
            fromDbTicket = tDao.findById(num_referencia).orElse(null);
            if(fromDbTicket == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fromDbTicket, HttpStatus.OK);
    }

    //POST
    @RequestMapping(value = basePath + "/tickets", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Ticket> insertUser(@RequestBody Ticket t) {
        try {
            if(t.equals(null)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            tDao.save(t);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // PUT - Actualización de info en BBDD
    @RequestMapping(value = basePath + "/tickets/{num_referencia}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Ticket> updateUser(@PathVariable Integer num_referencia, @RequestBody Ticket toUpdate) {
        Ticket updatedTicket = null;
        try {
            if((num_referencia == null) || (toUpdate == null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            Ticket fromDBTicket = tDao.findById(num_referencia).orElse(null);

            if (fromDBTicket != null) {
                tDao.save(toUpdate);
                updatedTicket = toUpdate;
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    // DELETE - Eliminación de info en BBDD
    @RequestMapping(value = basePath + "/tickets/{num_referencia}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Ticket> deleteOne(@PathVariable Integer num_referencia) {
        Ticket toDeleteTicket = null;
        try {
            if(num_referencia == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Buscamos por nombre de usuario y en caso de no encontrar nada, obtenemos null
            toDeleteTicket = tDao.findById(num_referencia).orElse(null);
            tDao.delete(toDeleteTicket);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(toDeleteTicket, HttpStatus.OK);
    }
}
