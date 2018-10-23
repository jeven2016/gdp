/**
 * File: Validators utils
 * Date: 2016-06-01
 * Created by root
 * Description: NA
 * Update: NA
 *        e.g. 2015-10-12:  Henry , change some code for his needs.
 */
/*global define,ComponentType,$, jQuery*/
define(function () {
    //Validator
    var validator = {};

    //validate current form by form id
    validator.validate = function (formId) {
        var form = $(formId);
        if (!form) {
            return true;
        }
        form.trigger("submit.validate");
        $('[data-toggle="tooltip"]').tooltip();

        //if validation result is false
        if (!form.valid()) {
            return;
        }
        return true;
    };

    /*Common validator*/
    validator.common = {};

    //name validator
    validator.common.nameValidator = {
        minLen: 1,
        maxLen: 20,
        required: true,
        build: function () {
            return {required: this.required, rangelength: [this.minLen, this.maxLen]}
        }
    };

    //port validator
    validator.common.portValidator = {
        min: 1000,
        max: 65535,
        required: true,
        build: function () {
            return {required: this.required, digits: true, min: this.min, max: this.max};
        }
    };

    //description validator
    validator.common.despValidator = {
        maxlength: 250,
        required: false,
        build: function () {
            return {required: this.required, maxlength: this.maxlength};
        }
    };

    //description validator
    validator.common.addressValidator = {
        rangelength: [1, 50],
        required: true,
        build: function () {
            return {required: this.required, rangelength: this.rangelength};
        }
    };

    //password validator
    validator.common.passwordValidator = validator.common.nameValidator;

    return validator;
});
