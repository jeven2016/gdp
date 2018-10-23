/**
 * View model for index.html
 */
/*global define,ComponentType,$*/
define([ComponentType.knockout, "message", "ajaxUtil", "is", "plugins/router", "durandal/system",
        ComponentType.resManager, ComponentType.routerDef],
    function (ko, message, ajaxUtil, is, router, system, resMgr, routerDef) {
        'use strict';

        function IndexVewModel() {
            var self = this;

            self.router = router;
        }

        IndexVewModel.prototype = {

            //activate 只有在view model中才生效，其他由requireJs加载的model，都无法生效
            activate: function () {

            }
        };

        var viewModel = new IndexVewModel();
        return viewModel;
    });