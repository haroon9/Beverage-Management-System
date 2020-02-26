<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="de.uniba.dsg.dsam.model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Incentive</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <h1 class="display-4">Edit Incentive</h1>
    <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i
            class="fas fa-plus fa-2x" aria-hidden="true"></i></a></span>

    <% IncentiveDTO incentiveDTO = (IncentiveDTO) request.getAttribute("inc"); %>

    <form role="form" action="/frontend/incentives/edit" method="post">
        <input type="hidden" name="inc_id" value="<%= incentiveDTO.getId() %>">
        <div class="form-group">
            <label for="inc_name">Incentive name</label>
            <input name="inc_name" id="inc_name" type="text" class="form-control"
                   value="<%= incentiveDTO.getName() %>" required>
        </div>
        <fieldset class="form-group">
            <label for="inc_type">Incentive type</label>
            <div class="form-check">
                <input id="p" class="form-check-input" value="Promotional Gift" type="radio" name="inc_type">
                <label for="p" id="prom">Promotional Gift</label>
            </div>
            <div class="form-check">
                <input id="t" class="form-check-input" value="Trial Package" type="radio" name="inc_type">
                <label for="t" id="trial">Trial Package</label>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary" role="button">Save</button>
        <a href="/frontend/incentives" class="btn btn-outline-secondary" role="button">Cancel</a>
    </form>


    <script type="application/javascript">
        var labProm = document.getElementById("prom");
        var labTrial = document.getElementById("trial");
        if ((labProm.innerText || labProm.textContent) == "<%= incentiveDTO.getType() %>") {
            document.getElementById("p").checked = true;
        } else if ((labTrial.innerText || labProm.textContent) == "<%= incentiveDTO.getType() %>") {
            document.getElementById("t").checked = true;
        }
    </script>
</div>

</body>
</html>
