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
    <% List<OrdersDTO> allOrders = (List<OrdersDTO>) request.getAttribute("allOrders");%>
    <% List<OrdersDTO> ordersWithoutIncentive = (List<OrdersDTO>) request.getAttribute("ordersWithoutIncentive"); %>
    <% List<OrdersDTO> ordersWithIncentive = (List<OrdersDTO>) request.getAttribute("ordersWithIncentive"); %>
    <% List<OrdersDTO> withoutIncentiveOrders = (List<OrdersDTO>) request.getAttribute("withoutIncentive");%>
    <% List<OrdersDTO> promotionalOrders = (List<OrdersDTO>) request.getAttribute("ordersWithPromotion");%>
    <% List<OrdersDTO> trialOrders = (List<OrdersDTO>) request.getAttribute("ordersWithTrial");%>

    <% Revenue revenue = (Revenue) request.getAttribute("rev"); %>
    <% Revenue revenueWithIncentive = (Revenue) request.getAttribute("revenueWithIncentive"); %>
    <% Revenue revenueWithoutIncentive = (Revenue) request.getAttribute("revenueWithoutIncentive"); %>
    <% Revenue revenueWithPromotion = (Revenue) request.getAttribute("revenueWithPromotion"); %>
    <% Revenue revenueWithTrialPackage = (Revenue) request.getAttribute("revenueWithTrialPackage"); %>

    <div id="table" class="table-editable">
        <p><a href="/frontend/" class="btn btn-outline-primary mr-3"><i style="vertical-align: top;"
                                                                        class="material-icons">arrow_back</i></a>
        </p>
        <h1 class="display-10">All orders</h1>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Order Date</th>
                <th class="text-center">Beverage Name</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price Per Unit</th>
                <th class="text-center">Incentive Name</th>
                <th class="text-center">Incentive Type</th>
                <th class="text-center">Revenue</th>
            </tr>
            </thead>
            <tbody>
                <% for(OrdersDTO order : allOrders){%>
            <tr>
                <td><%= order.getDate() %>
                </td>
                <td><%= order.getB_name() %>
                </td>
                <td class="text-right"><%= order.getB_qunatity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getB_price()) %> &euro;
                </td>
                <td><%= order.getInc_name() != null ? order.getInc_name() : "&ndash;" %>
                </td>
                <td><%= order.getInc_type() != null ? order.getInc_type() : "&ndash;" %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getTotalRevenue()) %> &euro;</td>
            </tr>
                <%} %>
            <thead class="">
            <tr>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <%if (revenue != null) {%>
                <th class="text-center">Total Revenue</th>
                <th class="text-center"></th>
                <th class="text-right">
                    <h5><%= String.format("%.2f", revenue.getAllOrdersRevenue().get(0).doubleValue()) %> &euro;</h5>
                </th>
                <%} %>
            </tr>
            </thead>
            </tbody>
        </table>
        <br>
        <h1 class="display-10">Orders With Incentives</h1>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Order Date</th>
                <th class="text-center">Beverage Name</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price Per Unit</th>
                <th class="text-center">Incentive Name</th>
                <th class="text-center">Incentive Type</th>
                <th class="text-center">Revenue</th>
            </tr>
            </thead>
            <tbody>
                <% for(OrdersDTO order : ordersWithIncentive){%>
            <tr>
                <td><%= order.getDate() %>
                </td>
                <td><%= order.getB_name() %>
                </td>
                <td><%= order.getB_qunatity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getB_price()) %> &euro;
                </td>
                <td><%= order.getInc_name() %>
                </td>
                <td><%= order.getInc_type() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getTotalRevenue()) %> &euro;</td>
            </tr>
                <%} %>

            <thead class="">
            <tr>
                <th class="text-center"></th>
                <th class="tex-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <%if (revenueWithIncentive != null) {%>
                <th class="text-center">Total Revenue</th>
                <th class="text-center"></th>
                <th class="text-center">
                    <h5><%= String.format("%.2f", revenueWithIncentive.getAllOrdersRevenue().get(0).doubleValue()) %>
                        &euro;</h5></th>
                <%} %>
            </tr>
            </thead>
            </tbody>
        </table>

        <br>
        <h1 class="display-10">Orders Without Incentives</h1>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Order Date</th>
                <th class="tex-center">Beverage Name</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price Per Unit</th>
                <th class="text-center">Incentive Name</th>
                <th class="text-center">Incentive Type</th>
                <th class="text-center">Revenue</th>
            </tr>
            </thead>
            <tbody>
                <% for(OrdersDTO order : ordersWithoutIncentive){%>
            <tr>
                <td><%= order.getDate() %>
                </td>
                <td><%= order.getB_name() %>
                </td>
                <td><%= order.getB_qunatity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getB_price()) %> &euro;
                </td>
                <td><%= order.getInc_name() != null ? order.getInc_name() : "&ndash;" %>
                </td>
                <td><%= order.getInc_type() != null ? order.getInc_type() : "&ndash;" %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getTotalRevenue()) %> &euro;</td>
            </tr>
                <%} %>

            <thead class="">
            <tr>
                <th class="text-center"></th>
                <th class="tex-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <%if (revenueWithoutIncentive != null) {%>
                <th class="text-center">Total Revenue</th>
                <th class="text-center"></th>
                <th class="text-center">
                    <h5><%= String.format("%.2f", revenueWithoutIncentive.getAllOrdersRevenue().get(0).doubleValue()) %>
                        &euro;</h5></th>
                <%} %>
            </tr>
            </thead>
            </tbody>
        </table>
        <br>
        <h1 class="display-10">Orders With Incentive Type Promotional Gift</h1>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Order Date</th>
                <th class="tex-center">Beverage Name</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price Per Unit</th>
                <th class="text-center">Incentive Name</th>
                <th class="text-center">Incentive Type</th>
                <th class="text-center">Revenue</th>
            </tr>
            </thead>
            <tbody>
            <% for (OrdersDTO order : promotionalOrders) {%>
            <tr>
                <td><%= order.getDate() %>
                </td>
                <td><%= order.getB_name() %>
                </td>
                <td><%= order.getB_qunatity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getB_price()) %> &euro;
                </td>
                <td><%= order.getInc_name() %>
                </td>
                <td><%= order.getInc_type() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getTotalRevenue()) %> &euro;</td>
            </tr>
            <%} %>
            </tbody>
            <thead class="">
            <tr>
                <th class="text-center"></th>
                <th class="tex-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <%if (revenueWithPromotion != null) {%>
                <th class="text-center">Total Revenue</th>
                <th class="text-center"></th>
                <th class="text-center">
                    <h5><%= String.format("%.2f", revenueWithPromotion.getAllOrdersRevenue().get(0).doubleValue()) %>
                        &euro;</h5></th>
                <%} %>
            </tr>
            </thead>
        </table>
        <br>
        <h1 class="display-10">Orders With Incentive Type Trial Package</h1>
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead class="">
            <tr>
                <th class="text-center">Order Date</th>
                <th class="tex-center">Beverage Name</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price Per Unit</th>
                <th class="text-center">Incentive Name</th>
                <th class="text-center">Incentive Type</th>
                <th class="text-center">Revenue</th>
            </tr>
            </thead>
            <tbody>
                <% for(OrdersDTO order : trialOrders){%>
            <tr>
                <td><%= order.getDate() %>
                </td>
                <td><%= order.getB_name() %>
                </td>
                <td><%= order.getB_qunatity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getB_price()) %> &euro;
                </td>
                <td><%= order.getInc_name() %>
                </td>
                <td><%= order.getInc_type() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", order.getTotalRevenue()) %> &euro;</td>
            </tr>
                <%} %>
            <thead class="">
            <tr>
                <th class="text-center"></th>
                <th class="tex-center"></th>
                <th class="text-center"></th>
                <th class="text-center"></th>
                <%if (revenueWithTrialPackage != null) {%>
                <th class="text-center">Total Revenue</th>
                <th class="text-center"></th>
                <th class="text-center">
                    <h5><%= String.format("%.2f", revenueWithTrialPackage.getAllOrdersRevenue().get(0).doubleValue())  %>
                        &euro;</h5></th>
                <%} %>
            </tr>
            </thead>
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