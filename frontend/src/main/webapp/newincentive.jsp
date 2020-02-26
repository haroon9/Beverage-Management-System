<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Incentives Management</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

</head>
<body>
<div class="container shadow rounded mt-3 p-3">
    <h1 class="display-4">New Incentive</h1>
    <span class="table-add float-right mb-3 mr-2"><a href="#!" class="text-success"><i
            class="fas fa-plus fa-2x" aria-hidden="true"></i></a></span>

    <form role="form" action="/frontend/incentives" method="post">
        <div class="form-group">
            <label for="name">Incentive name</label>
            <input name="name" id="name" type="text" class="form-control"
                   placeholder="Enter incentive name" required>
        </div>
        <fieldset class="form-group">
            <label for="inc_type">Incentive type</label>
            <div class="form-check">
                <input class="form-check-input" id="p" value="Promotional Gift" type="radio" name="type"
                       checked>
                <label for="p" class="form-check-label">Promotional Gift</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" id="t" value="Trial Package" type="radio" name="type" checked>
                <label for="t" class="form-check-label">Trial Package</label>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary" role="button">Save</button>
        <a href="/frontend/incentives" class="btn btn-outline-secondary" role="button">Cancel</a>
    </form>
</div>
</body>
</html>
