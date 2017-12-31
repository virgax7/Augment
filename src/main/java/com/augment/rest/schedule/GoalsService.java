package com.augment.rest.schedule;

import com.augment.dao.GoalsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@Component
@Path("/future")
public class GoalsService {
    private final GoalsDao goalsDao;

    @Autowired
    public GoalsService(final GoalsDao goalsDao) {
        this.goalsDao = goalsDao;
    }

    @PUT
    @Path("/{title}")
    public void updateGoal(@PathParam("title") final String title, @QueryParam("status") final String status, @Context HttpServletRequest request) {
        final String username = (String) request.getSession().getAttribute("username");
        goalsDao.updateGoalStatus(username, title, status);
    }
}
