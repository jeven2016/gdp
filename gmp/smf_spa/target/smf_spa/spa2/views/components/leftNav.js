/**
 * 左侧导航栏 View Model
 */
/*global define,ComponentType,$, jQuery*/
define([ComponentType.knockout,
        ComponentType.message, ComponentType.is, "plugins/router"],
    function (ko, message, is, router) {
        'use strict';
        function LeftNav() {
            var self = this;

        }

        LeftNav.prototype = {
            clickItem: function (url, event) {
                router.navigate(url);
            },

            activate: function () {

            }
        };

        return LeftNav;
    });