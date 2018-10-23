/**
 * File: views/home/login.js
 * Date: 2015-11-11/3/15
 * Created by root on 11/3/15.
 * Description: View modal for login
 * Update: NA
 *        e.g. 2015-10-12:  Henry , change some code for his needs.
 */
/*global define,ComponentType,$*/
define([ComponentType.knockout, 'durandal/system', ComponentType.ajaxUtil, ComponentType.commonUtils,
        'durandal/app', ComponentType.messageTypes, ComponentType.validation_tooltip, ComponentType.validatorUtils],
    function (ko, system, ajaxUtil, commonUtils, app, messageTypes, validation_tooltip, validatorUtils) {
        'use strict';

        var loginViewModel = {
            //userName
            userName: ko.observable(),

            //password
            password: ko.observable(),

            language: ko.observable(),

            //errors occur during logging in
            errors: ko.observableArray(),

            formId: "#loginForm",

            /**
             *User login
             */
            login: function () {
                //validate this form and stay on current page if any validation rules fail
                if (!validatorUtils.validate(this.formId)) {
                    return false;
                }

                var data = ko.toJSON(this),
                    self = this,
                    globalErrors,
                    fieldErrors,
                    req = {
                        url: commonUtils.uri.login,
                        data: data,
                        checkSession: false,
                        checkOk: false
                    };

                //callback
                req.successCallback = function (result) {
                    system.log(commonUtils.logLevel.info, "authenticate the user ", self.userName, " with result ", result);

                    //clear password
                    self.password("");

                    var isOk = result.ok;
                    if (isOk) {
                        //navigate current page to home view modal
                        app.trigger(messageTypes.LoggedIn);
                    } else {
                        //failed to login
                        self.userName(result.attachedObj.userName);
                        var errorResult = commonUtils.resultParser.parse(result);
                        globalErrors = errorResult.globalErrors;
                        fieldErrors = errorResult.fieldErrors;

                        if (globalErrors.length > 0) {
                            self.errors(globalErrors);
                        }
                        if (fieldErrors.length > 0) {
                            self.errors(globalErrors);
                        }
                    }
                };

                //send a post request to login
                ajaxUtil.post(req);
            },

            /**
             * Init validation for Add/Modify page after page loaded
             */
            attached: function () {
                $.validator.setDefaults({debug: true});//only validating rules

                //init validation rules
                $(this.formId).validate({
                    rules: {
                        userName: validatorUtils.common.nameValidator.build(),
                        password: validatorUtils.common.passwordValidator.build()
                    },
                    tooltip_options: {
                        userName: {placement: 'top'},
                        password: {placement: 'top'}
                    }
                });
                $('[data-toggle="tooltip"]').tooltip(); //enable all tooltips
            }
        };


        return loginViewModel;
    });
