$(function() {
    console.info("Document is ready");

    $("#login-user-button").on("click touchstart", login);
    $("#logout-user-button").on("click touchstart", logout);

    function login() {
        console.info("In authenticate user onClick handler");

        var auth = {
            username : $('#auth-username-input').val(),
            password : $('#auth-password-input').val()
        };

        console.log("Object " + JSON.stringify(auth));

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: 'application/json',
            url: "./login",
            data: JSON.stringify(auth)
        }).done(function(msg) {
            console.info("Successfully authenticated.");
            $(".auth-block").html(Mustache.to_html(logged_in_template, msg.body));
            $("#logout-user-button").on("click touchstart", logout);
            $('#login-modal').foundation('reveal', 'close');
        }).fail(function(msg) {
            console.info("Error caught when authenticating");
            console.info(JSON.stringify(msg));
        });
    }

    function logout() {
        console.info("In logout user onClick handler");

        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: 'application/json',
            url: "./logout"
        }).done(function() {
            console.info("Succesfully logged out");
            // Change template here?
            $(".auth-block").html(Mustache.to_html(logged_out_template));
            $("#login-user-button").on("click touchstart", login);
        }).fail(function(msg) {
            console.error("Error caught when logging out " + msg);
        });
    }

    $("#register-user-button").on("click touchstart", function() {
        console.info('In register user onclick handler');

        var user = {
            username : $('#username-input').val(),
            password : $('#password-input').val(),
            user_details: {
                firstname: $('#firstname-input').val(),
                lastname: $('#lastname-input').val(),
                email: $('#email-input').val(),
                bio: $('#bio-input').val()
            }
        };

        console.log("Object: " + JSON.stringify(user));

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: 'application/json',
            url: "./rest/registration",
            data: JSON.stringify(user)
        }).done(function(msg) {
            console.info('Success: ' + msg.status);
        }).fail(function(msg) {
            console.error("Error caught when registering customer");
        });
    });

    $("#add-product-button").on("click touchstart", function() {
        console.info("In add Product click handler");


    });


    /* Still needed? */
    $("#product-button").on("click touchstart", function() {
        console.info("In on click handler");

        var product = {
        "title" : $("#title").val(),
        "description" : $("#description").val()
        };

        console.info("Object is " + product);
        console.info("JSON is " + JSON.stringify(product));
        //alert("Product: " + product);

        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            url: "./product",
            data: JSON.stringify(product)

        }).done(function(msg) {
            console.info("Returned in done");

            console.info(msg);
            console.info("Title: " + msg.title);
            console.info("Status: " + msg.status);
            console.info("Body " + msg.body);
            alert("Done - " + msg);
        }).fail(function(msg) {
            console.info("Failed - " + msg);
            alert("Fail - " + msg);
        })
    });

    $("#product-count").on("click touchstart", function() {
        console.info("Getting count");

        $.ajax({
            type: "GET",
            //dataType: "json",
            //contentType: "application/json; charset=utf-8",
            url: "./products/count"
        }).done(function(msg) {
            console.info("Result: " + msg.body);
        }).fail(function(msg) {
            console.info("Failed - " + msg);

        })
    });

    $("#all-products").on("click touchstart", function() {
        console.info("Getting all products");

        $.ajax({
            type: "GET",
            //dataType: "json",
            //contentType: "application/json; charset=utf-8",
            url: "./products"
        }).done(function(msg) {
            console.info("Result: " + msg.body);
        }).fail(function(msg) {
            console.info("Failed - " + msg);

        })
    });
});


/* Moustache templates */
logged_in_template = "<div class='medium-4 columns right'><a href='#' class='button small radius right' id='logout-user-button'><span class='icon el-icon-user'></span> Log out</a><p id='welcome'>Welcome {{user_details.firstname}}</p>";
logged_out_template = "<div class='medium-4 columns right'><a href='#' data-reveal-id='login-modal' class='button small radius right'><span class='icon el-icon-user'></span> Log In</a></div>";