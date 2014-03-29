$(function() {
    console.info("Document is ready");

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
});
