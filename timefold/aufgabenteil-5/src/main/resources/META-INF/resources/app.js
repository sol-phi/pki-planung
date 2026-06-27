$(document).ready(function () {
    $("#solveButton").click(function () {
        solveMenuPlan();
    });
});

function solveMenuPlan() {
    $("#solveButton").prop("disabled", true).text("Calculating Menu Plan...");
    $("#score").text("Score: Calculating...");
    $("#info").text("The Timefold solver is currently optimizing the meal distribution...");

    $.ajax({
        url: "/menu/solve",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        success: function (menuPlan) {
            $("#solveButton").prop("disabled", false).text("Generate Menu Plan");

            $("#score").text("Score: " + (menuPlan.score ? menuPlan.score : "Optimal"));
            $("#info").text("Optimization successfully completed!");

            renderMenuPlanTable(menuPlan);
        },
        error: function (xhr) {
            $("#solveButton").prop("disabled", false).text("Generate Menu Plan");
            $("#score").text("Error");
            $("#info").text("Error retrieving the menu schedule: " + xhr.statusText);
            console.error(xhr);
        }
    });
}

function renderMenuPlanTable(menuPlan) {
    const container = $("#menuPlanTableContainer");
    container.empty();

    if (!menuPlan.assignments || menuPlan.assignments.length === 0) {
        container.html("<div class='alert alert-warning'>No menu data available.</div>");
        return;
    }

    menuPlan.assignments.sort((a, b) => a.day - b.day);

    let tableHtml = `
        <div class="card shadow-sm mt-4">
            <div class="card-header bg-dark text-white">
                <h3 class="mb-0"><i class="fas fa-utensils me-2"></i>Monthly Menu Schedule — VHS Waldfischbach</h3>
            </div>
            <div class="card-body p-0">
                <table class="table table-hover mb-0">
                    <thead class="table-light">
                        <tr class="text-center">
                            <th style="width: 15%">Day</th>
                            <th style="width: 42.5%; border-right: 1px solid #dee2e6;">Option: Menu 1</th>
                            <th style="width: 42.5%">Option: Menu 2</th>
                        </tr>
                    </thead>
                    <tbody>
    `;

    let daysMap = {};
    menuPlan.assignments.forEach(assignment => {
        if (!daysMap[assignment.day]) daysMap[assignment.day] = {};
        daysMap[assignment.day][assignment.menuOption] = assignment.meal;
    });

    for (let day = 1; day <= 30; day++) {
        let meal1 = daysMap[day] && daysMap[day][1] ? daysMap[day][1] : null;
        let meal2 = daysMap[day] && daysMap[day][2] ? daysMap[day][2] : null;

        tableHtml += `
            <tr class="align-middle">
                <td class="text-center fw-bold bg-light">Day ${day}</td>
                <td class="p-3" style="border-right: 1px solid #dee2e6;">
                    ${renderMealCard(meal1)}
                </td>
                <td class="p-3">
                    ${renderMealCard(meal2)}
                </td>
            </tr>
        `;
    }

    tableHtml += "</tbody></table></div></div>";
    container.html(tableHtml);
}

function renderMealCard(meal) {
    if (!meal) return `<div class="text-muted">Unassigned</div>`;

    const vegBadge = meal.vegetarian
        ? '<span class="badge text-white" style="background-color: #28a745;"><i class="fas fa-leaf"></i> Veggie</span>'
        : '<span class="badge bg-secondary text-white"><i class="fas fa-meat"></i> Classic</span>';

    return `
        <div class="d-flex flex-column">
            <div class="fw-bold text-dark" style="font-size: 1.05rem;">${meal.name}</div>
            <div class="mt-1">
                ${vegBadge}
                <span class="badge bg-light text-dark border ms-1">No. ${meal.mealIndex}</span>
            </div>
        </div>
    `;
}