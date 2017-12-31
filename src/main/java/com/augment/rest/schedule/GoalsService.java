package com.augment.rest.schedule;

import com.augment.convert.schedule.GoalConverts;
import com.augment.dao.GoalsDao;
import com.augment.vo.schedule.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Component
@Path("/future")
public class GoalsService {
    private final GoalsDao goalsDao;

    @Autowired
    public GoalsService(final GoalsDao goalsDao) {
        this.goalsDao = goalsDao;
    }

    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Goal getGoal(@PathParam("title") final String title, @Context HttpServletRequest request) {
        final String username = (String) request.getSession().getAttribute("username");
        final Map<String, Object> goalMap = goalsDao.getGoal(username, title).get(0);
        return GoalConverts.makeGoal(goalMap);
    }

    @PUT
    @Path("/{title}")
    public void updateGoal(@PathParam("title") final String title, @QueryParam("status") final String status, @Context HttpServletRequest request) {
        final String username = (String) request.getSession().getAttribute("username");
        goalsDao.updateGoalStatus(username, title, status);
    }
}
