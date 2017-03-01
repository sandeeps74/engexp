System.register(['angular2/platform/browser', "./app_service.component"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var browser_1, app_service_component_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (app_service_component_1_1) {
                app_service_component_1 = app_service_component_1_1;
            }],
        execute: function() {
            console.log('Invoking AppComponent bootstap');
            browser_1.bootstrap(app_service_component_1.AppComponent);
            console.log('Finished AppComponent bootstap');
        }
    }
});
//# sourceMappingURL=service_main.js.map