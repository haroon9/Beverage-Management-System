<!DOCTYPE html>
<%@page import="java.util.ArrayList" %>
<%@page import="de.uniba.dsg.dsam.model.Beverage" %>
<%@page import="de.uniba.dsg.dsam.model.IncentiveDTO" %>
<%@page import="java.util.List" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Beverage</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <% Beverage beverage = (Beverage) request.getAttribute("beverage");%>
    <h1 class="display-4">Edit Beverage</h1>
    <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i class="fas fa-plus fa-2x"
                                                                                       aria-hidden="true"></i></a></span>
    <form role="form" action="/frontend/beverages/edit" method="post">
        <div class="form-group">
            <label for="name">Beverage name</label>
            <input name="name" id="name" type="text" class="form-control" value="<%=beverage.getName() %>" required>
        </div>
        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <input name="manufacturer" id="manufacturer" type="text" class="form-control"
                   value="<%= beverage.getManufacturer() %>" required>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input name="quantity" id="quantity" type="text" class="form-control" value="<%= beverage.getQuantity() %>"
                   required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input name="price" id="price" type="number" min="0.00" max="10000.00" step="0.01" class="form-control"
                   value="<%= beverage.getPrice() %>" required>
        </div>

        <div class="form-group">
            <label for="incentive">Assign Incentive</label>
            <input type="hidden" name="incentive" value="" id="incentive">
            <input type="hidden" name="b_id" value="<%= beverage.getId() %>" id="b_id">
            <%List<IncentiveDTO> list = (List<IncentiveDTO>) session.getAttribute("incentiveList"); %>
            <select onchange="val()" id="id" class="browser-default custom-select">
                <%if (beverage.getIncentiveDTO() != null) { %>
                <option selected><%= beverage.getIncentiveDTO().getName() + " (" + beverage.getIncentiveDTO().getType() + ")"%>
                </option>
                <%} else {%>
                <option selected>Select Incentive</option>
                <%}%>
                <% for (IncentiveDTO dto : list) { %>
                <option value="<%= dto.getId() %>"><%= dto.getName() + " (" + dto.getType() + ")" %>
                </option>
                <%}%>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="/frontend/beverages" class="btn btn-outline-secondary">Cancel</a>
        <!-- <a class="btn btn-primary btn-lg" role="button" type = "submit">Save</a> -->
    </form>
</div>

<script type="text/javascript">

    // this function is responsible to get the value of the select tag which will contain the selected incentive
    // and will then assign the value to the hidden input tag so when the user click save the value will be propagate to the servlet
    function val() {
        d = document.getElementById("id").value;
        document.getElementById("incentive").value = d;
    }
</script>
</body>
</html>