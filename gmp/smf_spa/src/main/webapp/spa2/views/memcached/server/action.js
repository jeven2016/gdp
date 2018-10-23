/*global define,ComponentType,$*/
define(["plugins/router", ComponentType.knockout, ComponentType.jquery, ComponentType.table,
        ComponentType.page, ComponentType.ajaxUtil, ComponentType.commonUtils, ComponentType.is,
        "durandal/system", "durandal/app", ComponentType.messageTypes, ComponentType.validation_tooltip, ComponentType.validatorUtils],
    function (router, ko, $, table, page, ajaxUtil, commonUtils, is, system, app, msgTypes, validation_tooltip, validatorUtils) {
        'use strict';
        var viewModel = {
            uri: commonUtils.uri.memcachedServer,//请求的uri
            action: ko.observable(), //当前的action
            formId: "#memcachedServerForm", //form id

            id: ko.observable(),
            name: ko.observable(),
            address: ko.observable(),
            port: ko.observable(),
            enable: ko.observable(true),
            description: ko.observable(),

            //更新绑定的值
            updateValue: function (vo) {
                this.id(vo.id);
                this.name(vo.name);
                this.address(vo.address);
                this.port(vo.port);
                this.enable(vo.enable);
                this.description(vo.description);
            },

            //update Yes option for 'enable' widget
            updateYesOptionCss: ko.pureComputed(function () {
                if (is.equal(viewModel.enable(), true)) {
                    return "btn btn-primary active";
                }
                return "btn btn-default";
            }),

            //update 'No' option for 'enable' widget
            updateNoOptionCss: ko.pureComputed(function () {
                if (is.equal(viewModel.enable(), false)) {
                    return "btn btn-primary active";
                }
                return "btn btn-default";
            }),

            //switch 'enable' widget
            switchEnableWidget: function (selectedValue) {
                this.enable(selectedValue);
            },

            /*
             * Back to last page
             */
            back: function () {
                app.trigger(msgTypes.confirmToBack, {
                    okCallback: function (dialogRef) {
                        dialogRef.close();
                        router.navigate(commonUtils.hash.memcachedServer_index);
                    }
                });
            },

            /**
             * 测试连接
             */
            testConnection: function () {

            },

            /**
             * 保存
             */
            save: function (model, event) {
                if (!validatorUtils.validate(this.formId)) {
                    return false;
                }

                var data = ko.toJSON(model),
                    uri = this.uri,
                    objName = this.name(),
                    self = this,
                    req = {
                        url: uri,
                        data: data,
                        processFieldErrors: self.displayFieldErrors,
                        successCallback: self.postSave
                    };


                //confirm and begin to save
                app.trigger(msgTypes.confirmToSave, {
                    objName: objName,
                    okCallback: function (dialogRef) {
                        dialogRef.close();

                        //submit put request to save data
                        ajaxUtil.put(req);
                    }
                });

            },

            postSave: function (result) {
                if (result.ok) {
                    //navigate to index page
                    router.navigate(commonUtils.hash.memcachedServer_index);
                }
            },

            /*
             * display the field errors returned from server
             * @param fieldErrors  field errors
             */
            displayFieldErrors: function (fieldErrors) {
                var ids = ["#name", "#address", "#port", "#disabled", "#description"],
                    errorContainerSuffix = "_error";

                //first clear existed errors
                $.each(ids, function (index, id) {
                    $(id + errorContainerSuffix).html("");
                });

                $.each(fieldErrors, function (index, errorObj) {
                    var msg = '<div class="alert alert-danger">'
                        + '<strong>' + errorObj.message + '</strong>'
                        + '</div>';
                    $("#" + errorObj.key + errorContainerSuffix).html(msg);
                });
            },

            /**
             * Init validation for Add/Modify page after page loaded
             */
            attached: function () {
                var action = this.action(),
                    actionType = commonUtils.action;
                if (!is.equal(action, actionType.add) && !is.equal(action, actionType.update)) {
                    return;
                }

                $.validator.setDefaults({debug: true});//only validating rules

                //init validation rules
                $(this.formId).validate({
                    rules: {
                        name: validatorUtils.common.nameValidator.build(),
                        address: validatorUtils.common.addressValidator.build(),
                        port: validatorUtils.common.portValidator.build(),
                        description: validatorUtils.common.despValidator.build()
                    },
                    tooltip_options: {
                        name: {placement: 'top'},
                        address: {placement: 'top'},
                        port: {placement: 'top'},
                        description: {placement: 'top'}
                    }
                });

                $('[data-toggle="tooltip"]').tooltip(); //enable all tooltips
            },

            /**
             * 显示ViewModel时的callback
             * @param action 操作类型
             * @param id 对象的id
             */
            activate: function (action, id) {
                var self = this,
                    url,
                    req,
                    actionType = commonUtils.action,
                    uriUtil = commonUtils.uri;
                if (is.empty(action) && is.empty(id)) {
                    system.log(commonUtils.logLevel.error, "invalid action or id for memcached server. action=", action, ", id=", id);
                }

                //更新当前操作类型
                this.action(action);

                if (is.equal(action, actionType.add)) {
                    return;
                }
                if (is.equal(action, actionType.update) || is.equal(action, actionType.view)) {
                    url = uriUtil.join(this.uri, id);
                }

                if (!is.existy(url)) {
                    //无效路由
                    system.log(commonUtils.logLevel.error,
                        "invalid url (", action, ") for memcached server.");
                    return;
                }


                //从服务器加载一个对象，用于创建
                req = {
                    url: url,
                    successCallback: function (data) {
                        if (data.ok) {
                            self.updateValue(data.attachedObj);
                        }
                    }
                };
                ajaxUtil.get(req);
            }
        };

        return viewModel;
    }
);