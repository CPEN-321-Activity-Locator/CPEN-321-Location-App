var express = require("express");
const app = require("./app").app;
var models = require("./__models__/models");

// start server
const APP_PORT = 3000;
models.connectDb().then(async () => {
    app.listen(APP_PORT, function () {
        console.log("Listening at %s", APP_PORT);
    });
})
