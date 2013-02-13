package com.wyndhamjade.interview.test.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.PathParam;

import com.wyndhamjade.interview.test.model.Task;
import com.wyndhamjade.interview.test.model.TaskDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("tasks")
public class RestControlService {
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Task> getTasks() throws Exception {
        List<Task> tasks = new TaskDao().Get();
        return tasks;
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Task getTasks(@PathParam("id") Long id) throws Exception {
        Task task = new TaskDao().Get(id);
        return task;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteTask(@PathParam("id") Long id) throws Exception {
        new TaskDao().remove(id);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateTask(Task task) throws Exception {
        new TaskDao().update(task);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createTask(Task task) throws Exception {
        new TaskDao().add(task);
    }
}
