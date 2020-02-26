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

    <title>Order Beverage</title>

    <style type="text/css">
        /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
        .show {
            display: block;
        }

        td {
            vertical-align: middle !important;
        }
    </style>
</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <h1 class="display-4">Order Beverages Here</h1>
    <div id="table" class="table-editable">
        <table class="table table-sm table-responsive-md table-striped text-center">
            <thead>
            <tr>
                <th class="text-center">Name</th>
                <th class="text-center">Manufacturer</th>
                <th class="text-center">Quantity</th>
                <th class="text-center">Price</th>
                <th class="text-center">Incentive</th>
                <th class="text-center">Select Quantity</th>
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
                <td id="quantity" class="text-right"><%= b.getQuantity() %>
                </td>
                <td class="text-right"><%= String.format("%.2f", b.getPrice()) %> &euro;</td>
                </td>

                <% if (b.getIncentiveDTO() != null) { %>
                <td class="pt-3-half"><%= b.getIncentiveDTO().getName() %>
                </td>
                <%} else { %>
                <td><i>none</i></td>
                <%}%>

                <td>
                    <input id="b_quantity" type="number" class="b_quantity form-control" min="1"
                           max="<%=b.getQuantity()%>"
                           value="0">
                </td>
                <td>
                    <input type="hidden" name="b_id" value="<%= b.getId() %>">
                    <!-- <input  class="q_val" id="q_val" type="hidden" name="q_val" value="0">  -->
                    <a href="" id="<%= b.getId() %>" type="button"
                       class="order btn btn-primary">Order now</a>

                </td>

            </tr>

            <% } %>
            </tbody>
        </table>
    </div>
</div>
</div>
</div>


<script type="text/javascript">

    $(document).ready(function () {
        $(".order").click(function () {
            event.preventDefault();
            var myRow = $(this).parents('tr');
            var quantity = $('.b_quantity', myRow).val();
            var db_quantity = $('#quantity', myRow).text();
            var result = db_quantity - quantity;
            if (db_quantity <= 0) {
                alert("Sorry the beverage is not available.");
            } else if (result < 0 || quantity <= 0) {
                alert("Invalid quantity selected.");
            } else {
                $.ajax({
                    url: '/frontend/new?b_id=' + event.target.id,
                    type: 'Post',
                    cache: true,
                    data: {
                        b_quantity: quantity
                    },
                    success: function (response) {
                        location.href = "/frontend/beverages";
                    }
                });
            }
        });
    });

</script>

</body>
</html>
