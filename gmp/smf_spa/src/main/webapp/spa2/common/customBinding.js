/*global define,ComponentType,$, jQuery*/
/**
 * Knockout 自定义绑定
 */
define([ComponentType.knockout, ComponentType.is, ComponentType.resManager, ComponentType.jquery], function (ko, is, resManager, $) {
    'use strict';
    function setText(value, element) {
        if (is.string(value)) {
            $(element).html(resManager.getResource(value));
        }
        else if (is.object(value)) {
            var jElem = $(element);
            for (var property in value) {
                jElem.attr(property, resManager.getResource(value[property]));
            }
        }
    }

    function addAttribute(name, value, element) {
        value = resManager.getResource(value);
        $(element).attr(name, value);
    }

    //国际化资源自定义绑定
    ko.bindingHandlers.i18n = {

        init: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            // This will be called when the binding is first applied to an element
            // Set up any initial state, event handlers, etc. here
            var value = valueAccessor();
            setText(value, element);
        },
        update: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            // This will be called once when the binding is first applied to an element,
            // and again whenever any observables/computeds that are accessed change
            // Update the DOM element based on the supplied values here.
            // First get the latest data that we're bound to
            var value = valueAccessor();
            setText(value, element);
        }
    };

    ko.bindingHandlers.placeholder = {
        init: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            var value = valueAccessor();
            addAttribute("placeholder", value, element);
        },
        update: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
            var value = valueAccessor();
            addAttribute("placeholder", value, element);
        }
    };
    return ko.bindingHandlers;
})