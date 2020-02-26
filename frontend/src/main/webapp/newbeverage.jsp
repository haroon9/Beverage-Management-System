<!DOCTYPE html>
<%@page import="de.uniba.dsg.dsam.model.IncentiveDTO" %>
<%@page import="java.util.List" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Beverages Management</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <h1 class="display-4">New Beverage</h1>
    <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i
            class="fas fa-plus fa-2x" aria-hidden="true"></i></a></span>

    <form role="form" action="/frontend/beverages" class="mt-3" method="post">
        <div class="form-group">
            <label for="name">Beverage name</label>
            <input name="name" id="name" type="text" class="form-control" placeholder="Enter beverage name" required>
        </div>
        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <input name="manufacturer" id="manufacturer" type="text" class="form-control"
                   placeholder="Enter manufacturer" required>
        </div>
        <div class="form-group">
            <label for="quantitiy">Quantity</label>
            <input name="quantity" id="quantitiy" type="number" min="1" max="10000" step="1" class="form-control" placeholder="Enter quantity"
                   required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input name="price" id="price" type="number" min="0.00" max="10000.00" step="0.01" class="form-control"
                   placeholder="Enter price" required>
        </div>
        <div class="form-group">
            <label for="incentive">Assign Incentive</label>
            <input type="hidden" name="incentive" value="" id="incentive">
            <%List<IncentiveDTO> list = (List<IncentiveDTO>) session.getAttribute("incentiveList"); %>
            <select onchange="val()" id="id" class="browser-default custom-select">
                <option selected value="">Select Incentive</option>
                <% for (IncentiveDTO dto : list) { %>
                <option value="<%= dto.getId() %>"><%= dto.getName() + " (" + dto.getType() + ")" %>
                </option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
        <a href="/frontend/beverages" class="btn btn-outline-secondary">Cancel</a>
    </form>
</div>
<!-- <a class="btn btn-primary btn-lg" role="button" type = "submit">Save</a> -->
</form>
</div>

<script type="text/javascript">

    // this function is responsible to get the value of the select tag which will contain the select incentive
    // and will then assign the value to the hidden input tag so when the user click save the value will be propagate to the servlet
    function val() {
        d = document.getElementById("id").value;
        document.getElementById("incentive").value = d;
    }
</script>
</body>
</html>
