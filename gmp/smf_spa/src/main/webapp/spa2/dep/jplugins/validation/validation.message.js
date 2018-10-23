define([ComponentType.jquery, ComponentType.resManager, ComponentType.validator], function ($, resManager) {
    'use strict';
    $.extend($.validator.messages,
        {
            required: resManager.getResource("validation.required"),
            remote: resManager.getResource("validation.remote"),
            email: resManager.getResource("validation.email"),
            url: resManager.getResource("validation.url"),
            date: resManager.getResource("validation.date"),
            dateISO: resManager.getResource("validation.dateISO"),
            dateDE: resManager.getResource("validation.dateDE"),
            number: resManager.getResource("validation.number"),
            numberDE: resManager.getResource("validation.numberDE"),
            digits: resManager.getResource("validation.digits"),
            creditcard: resManager.getResource("validation.creditcard"),
            equalTo: resManager.getResource("validation.equalTo"),
            accept: resManager.getResource("validation.accept"),
            maxlength: $.validator.format(resManager.getResource("validation.maxlength")),
            minlength: $.validator.format(resManager.getResource("validation.minlength")),
            rangelength: $.validator.format(resManager.getResource("validation.rangelength")),
            range: $.validator.format(resManager.getResource("validation.range")),
            max: $.validator.format(resManager.getResource("validation.max")),
            min: $.validator.format(resManager.getResource("validation.min"))
        });
    return $;
});