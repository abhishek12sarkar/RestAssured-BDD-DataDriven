document.addEventListener("DOMContentLoaded", () => {
    document.title = "Marketplace API Automation Report";
    document.querySelector("#navigation > div > div > p").textContent = "API Test Report";
    document.getElementById("footer").remove();
    document.querySelector("#navigation > div > div > ul > li:nth-child(3)").remove();
    document.querySelector(".carousel-inner>div:nth-child(3)").remove();
    document.querySelector(".carousel-indicators>li:nth-child(3)").remove();

    // Feature Page
    // document.querySelector("#tablesorter>thead>tr[class='header dont-sort']>th:nth-child(2)").remove();
    // document.querySelectorAll("#tablesorter>thead>tr[class='tablesorter-headerRow']>th:nth-child(-n+7):nth-child(n+2)").forEach(
    //     function stepRemover(tdSteps) {
    //         tdSteps.remove()
    //     }
    // );
    // document.querySelectorAll("#tablesorter>tbody>tr>td:nth-child(-n+7):nth-child(n+2)").forEach(
    //     function stepRemover(tdSteps) {
    //         tdSteps.remove()
    //     }
    // );
    // document.querySelectorAll("#tablesorter>tfoot>tr>td:nth-child(-n+7):nth-child(n+2)").forEach(
    //     function stepRemover(tdSteps) {
    //         tdSteps.remove()
    //     }
    // );

});