/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.parusrmcs.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import py.una.pol.par.parusrmcs.model.entity.User;
import py.una.pol.par.parusrmcs.repository.InMemUserRepository;
import py.una.pol.par.parusrmcs.service.UserServiceImpl;


/**
 *
 * @author Mauricio
 */
@Path("/usrmcs")
public class UserRestService {

    private final UserServiceImpl userService = new UserServiceImpl(new InMemUserRepository());
    
    @GET
    @Path("/users/{loginName}/{passWord}")
    @Produces("application/json")
    public boolean getLogin(@PathParam("loginName") String loginName,@PathParam("passwd") String passwd) {
        boolean entity =false;
        try {
            entity = userService.containsLoginPasswd(loginName, passwd);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    @GET
    @Path("/users")
    @Produces("application/json")
    public ArrayList<User> getUsers() {
        ArrayList<User> users = (ArrayList) userService.getAll();
        return users;
    }

    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") Integer id) {
        User entity = null;
        try {
            entity = (User) userService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    
    @POST
    @Path("/users")
    @Consumes("application/json")
    @Produces("application/json")
    public User addUser(User entity) {
        try {
            userService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/users")
    @Consumes("application/json")
    public void updateUser(User entity) {
        try {
            userService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/users/{id}")
    public void removeUser(@PathParam("id") Integer id) {
        try {
            userService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
