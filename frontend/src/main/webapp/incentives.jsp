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

    <style type="text/css">
        i.material-icons {
            vertical-align: top;
        }
    </style>

    <title>Manage Incentives</title>
</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <h1 class="display-4">Incentives Overview</h1>
    <div class="table-editable">
			      <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i
                          class="fas fa-plus fa-2x" aria-hidden="true"></i></a></span>
        <p><a href="/frontend/" class="btn btn-outline-primary mr-3"><i
                class="material-icons">arrow_back</i></a>
            <a href="/frontend/newincentive" class="float-right btn btn-primary"><i class="material-icons">add</i></a>
        </p>
    </div>
    <div class="alert alert-info" role="alert"><b>Note:</b> Incentives attached to beverages that are inside an order are shown <i>italic</i> and
        can not be deleted.
    </div>
    <table class="table table-sm table-responsive-md table-striped text-center">
        <thead>
        <tr>
            <th class="text-center">Incentive Type</th>
            <th class="text-center">Incentive Name</th>
            <th class="text-center"></th>
        </tr>
        </thead>
        <tbody>
        <%

            List<IncentiveDTO> incentives = (List<IncentiveDTO>) request.getAttribute("incentiveList");
            for (IncentiveDTO inc : incentives) {

        %>
        <tr <% if(inc.isAttached()) {%>style="font-style: italic;"<%}%>>
            <td><%= inc.getType() %>
            </td>
            <td><%= inc.getName() %>
            </td>
            <td class="text-right">
                <a href="/frontend/incentives/edit?inc_id=<%= inc.getId() %>" type="button"
                   class="btn btn-primary btn-rounded btn-sm my-0">Edit</a>
                <% if (!inc.isAttached()) {%>
                <a id="<%= inc.getId() %>" type="button" href=""
                   class="delete btn btn-danger btn-rounded btn-sm my-0"
                   onclick="if (!confirm('Are you sure?')) { return false }">Delete</a></a>
                <%}%>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</div>

<script>
    $(document).ready(function () {
        $(".delete").click(function () {
            event.preventDefault();
            $.ajax({
                url: '/frontend/incentives?inc_id=' + event.target.id,
                type: 'DELETE',
                success: function (response) {
                    location.reload();
                },
                fail: function (exception) {
                    alert(exception);
                }
            });
        });
    });
</script>

</body>
</html>
