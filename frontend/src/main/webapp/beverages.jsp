<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ page import="de.uniba.dsg.dsam.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <title>Manage Beverages</title>

    <style type="text/css">
        /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
        .show {
            display: block;
        }
    </style>
</head>
<body>
<div class="container shadow rounded mt-3 p-3">

    <%
        List<IncentiveDTO> list = (List<IncentiveDTO>) request.getAttribute("incentiveList");
        session.setAttribute("incentiveList", list);
    %>

    <h1 class="display-4">Beverages Overview</h1>
    <div id="table" class="table-editable">
        <p><a href="/frontend/" class="btn btn-outline-primary mr-3"><i style="vertical-align: top;"
                                                                        class="material-icons">arrow_back</i></a>
            <a href="/frontend/newbeverage" data-toggle="tooltip" title="Create new Beverage"
               class="float-right btn btn-primary"><i
                    style="vertical-align: top;"
                    class="material-icons">add</i></a>
        </p>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Name</th>
                <th class="text-center">Manufacturer</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price</th>
                <th class="text-center">Incentive</th>
                <th class="text-center"></th>
            </tr>
            </thead>
            <tbody>
            <%

                List<Beverage> beverages = (List<Beverage>) request.getAttribute("beverageList");
                for (Beverage b : beverages) {

            %>
            <tr>
                <td><%= b.getName() %>
                </td>
                <td><%= b.getManufacturer() %>
                </td>
                <td class="text-right"><% if(b.getQuantity()==0){%>
                    <i class="material-icons text-danger" style="vertical-align: middle;">error</i>
                    <%}%><%= b.getQuantity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", b.getPrice()) %> &euro;</td>

                <% if (b.getIncentiveDTO() != null) { %>
                <td><%= b.getIncentiveDTO().getName() %>
                </td>
                <%} else { %>
                <td>
                    <a id="assign" href="/frontend/beverages/assign?b_id=<%= b.getId() %>" type="button"
                       class=" btn btn-primary btn-rounded btn-sm my-0">Assign</a>
                </td>
                <%}%>
                <td class="text-right">
                    <a id="edit" href="/frontend/beverages/edit?b_id=<%= b.getId() %>" type="button"
                       class="btn btn-primary btn-rounded btn-sm my-0">Edit</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>

</body>
</html>
