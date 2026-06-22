package org.acme.MenuPlanning.rest;

import ai.timefold.solver.core.api.solver.SolverManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.MenuPlanning.domain.MenuPlan;

@Path("/menu")
public class MenuPlanResource {

    @Inject
    SolverManager<MenuPlan> solverManager;

    @Inject
    DemoDataGenerator demoDataGenerator;

    @POST
    @Path("/solve")
    @Produces(MediaType.APPLICATION_JSON)
    public MenuPlan solve() {
        // 1. Die 30 Tage mit den 31 Gerichten generieren
        MenuPlan problem = demoDataGenerator.generateDemoData();

        try {
            return solverManager.solveBuilder()
                    .withProblemId(1L)
                    .withProblem(problem)
                    .run()
                    .getFinalBestSolution();
        } catch (Exception e) {
            throw new RuntimeException("Fehler bei der Berechnung des Speiseplans", e);
        }
    }
}