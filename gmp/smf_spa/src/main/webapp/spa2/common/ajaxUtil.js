/*global define,ComponentType,$, jQuery*/
define([ComponentType.jquery, ComponentType.is, "durandal/system", ComponentType.commonUtils,
        ComponentType.dialogUtil, ComponentType.resManager, "durandal/app", ComponentType.messageTypes],
    function ($, is, system, commonUtils, dialogUtil, resManager, app, messageTypes) {
        'use strict';
        $.ajaxSetup({
            async: true //使用异步请求
        });
        var util = {
            list: function (paramObj) {
                paramObj.type = "get";
                this.sendRequest(paramObj);
            },

            /**
             * /XX/1  -GET  获取一个存在的对象
             */
            get: function (paramObj) {
                paramObj.type = "get";
                this.sendRequest(paramObj);
            },

            /**
             * 执行post请求
             */
            post: function (paramObj) {
                paramObj.type = "post";
                this.sendRequest(paramObj);
            },

            /**
             * 执行put请求
             */
            put: function (paramObj) {
                paramObj.type = "put";
                this.sendRequest(paramObj);
            },

            /**
             * /XX/1  -GET  删除一个存在的对象
             */
            delete: function (paramObj) {
                paramObj.type = "delete";
                this.sendRequest(paramObj);
            },

            /**
             *  /XX?batch={"ids":["aa","bb","cc"]}  -DELETE  删除多个对象
             */
            bulkDelete: function () {
            },

            sendRequest: function (config) {
                system.log(commonUtils.logLevel.info, "send a request and the parameter object is ", config);

                dialogUtil.showProgress();
                var req = {
                        url: null,
                        type: "get",
                        dataType: "json",
                        contentType: "application/json",
                        data: null,
                        successCallback: null,
                        errorCallback: null,
                        processFieldErrors: null, //处理某个字段值错误的回调函数
                        processGlobalErrors: null, //处理全局错误的回调函数
                        checkSession: true,
                        checkOk: true
                    },
                    self = this;
                req = $.extend(req, config);
                req.success = function (jsonData) {
                    dialogUtil.closeProgress();

                    if (!self.validateSession(req, jsonData)) {
                        return;
                    }

                    //if field or global errors occur
                    if (req.checkOk && is.object(jsonData) && jsonData.ok == false) {
                        system.log(commonUtils.logLevel.error, "Error message(s) appear in requesting to url ", req.url,
                            ". isOk=", jsonData.ok, ",globalErrors=", jsonData.globalErrors, ",fieldErrors=", jsonData.fieldErrors, ",notices=", jsonData.notices);

                        self.displayAllErrors(req, jsonData);
                    }

                    //if customized success callback defined
                    if (is.function(req.successCallback)) {
                        req.successCallback(jsonData);
                    }
                };

                req.error = function (errorInfo) {
                    system.log(commonUtils.logLevel.error, "Failed to request to url ", req.url,
                        ". The error message is: (", errorInfo.status, ")", errorInfo.statusText);
                    dialogUtil.closeProgress();
                    if (is.function(req.errorCallback)) {
                        req.error = req.errorCallback;
                    } else {
                        self.displayError(errorInfo);
                    }
                };
                $.ajax(req);
            },

            /*
             * Display error
             */
            displayError: function (errorInfo) {
                var specifiedError = errorInfo.status + "," + errorInfo.statusText,
                    msg = {message: resManager.getResource("error.internal", specifiedError)};
                dialogUtil.showError(msg);
            },

            /*
             * validate whether the user has logged in
             * @param request  Request object
             * @param jsonData The response json data
             * @returns boolean whether the session is expired
             */
            validateSession: function (request, jsonData) {
                //validate whether user's session is expired
                if (request.checkSession && (!jsonData.validSession || is.equal(jsonData.validSession, false))) {
                    var res = resManager.getResource("error.loggedout"),
                        alertConf = {
                            message: res,
                            okCallback: function (dialogRef) {
                                //navigate current page to home view modal
                                app.trigger(messageTypes.LoggedOut);
                                dialogRef.close();
                            }
                        };
                    dialogUtil.alert(alertConf);
                    return false;
                }
                return true;
            },

            /*
             * Display all errors including field and global errors
             * @param jsonData the response json data returned from server
             */
            displayAllErrors: function (req, jsonData) {
                var errorResult = commonUtils.resultParser.parse(jsonData),
                    globalErrors = errorResult.globalErrors,
                    fieldErrors = errorResult.fieldErrors;

                //jsonData.fieldErrors
                //jsonData.globalErrors
                //jsonData.notices
                if (!is.empty(globalErrors)) {
                    if (is.function(req.processGlobalErrors)) {
                        req.processGlobalErrors(globalErrors);
                    } else {
                        $(globalErrors).each(function (index, errorObj) {
                            var msg = {message: errorObj.message};
                            dialogUtil.showError(msg);
                        });
                    }
                }

                if (!is.empty(fieldErrors)) {
                    if (is.function(req.processFieldErrors)) {
                        req.processFieldErrors(fieldErrors);
                    } else {
                        $(fieldErrors).each(function (index, errorObj) {
                            var msg = {message: "(" + errorObj.key + ") " + errorObj.message};
                            dialogUtil.showError(msg);
                        });
                    }
                }

            }
        };
        return util;
    });
