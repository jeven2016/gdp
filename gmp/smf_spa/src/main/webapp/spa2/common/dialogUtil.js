/*global define,ComponentType,$, jQuery*/
define(["bootstrap-dialog", ComponentType.jquery, "durandal/app", "durandal/system", ComponentType.messageTypes,
        ComponentType.is, ComponentType.resManager, ComponentType.commonUtils],
    function (dialog, $, app, system, messageTypes, is, resManager, commonUtils) {
        'use strict';
        //alert(BootstrapDialog);

        var dialogUtil = {

            //progress dialog
            progressDialog: null,

            init: function () {
                var self = this;

                //init default text resources
                dialog.DEFAULT_TEXTS[dialog.TYPE_DEFAULT] = resManager.getResource("common.confirm");
                dialog.DEFAULT_TEXTS[dialog.TYPE_INFO] = resManager.getResource("common.info");
                dialog.DEFAULT_TEXTS[dialog.TYPE_PRIMARY] = resManager.getResource("common.info");
                //dialog.DEFAULT_TEXTS[dialog.TYPE_SUCCESS] = 'Success';
                dialog.DEFAULT_TEXTS[dialog.TYPE_WARNING] = resManager.getResource("common.warning");
                //dialog.DEFAULT_TEXTS[dialog.TYPE_DANGER] = 'Danger';
                dialog.DEFAULT_TEXTS['OK'] = resManager.getResource("common.button.ok");
                dialog.DEFAULT_TEXTS['CANCEL'] = resManager.getResource("common.button.cancel");
                dialog.DEFAULT_TEXTS['CONFIRM'] = resManager.getResource("common.confirm");

                //subscribe a saving event
                app.on(messageTypes.confirmToSave).then(function (config) {
                    self.updateMessageByAction(config, messageTypes.confirmToSave, "common.confirm.save");
                    self.confirm(config);
                });

                //subscribe a deletion event
                //@param config :  message and objName field, one of them should be configured
                app.on(messageTypes.confirmToDelete).then(function (config) {
                    self.updateMessageByAction(config, messageTypes.confirmToDelete, "common.confirm.delete");
                    self.confirm(config);
                });

                //subscribe a event for backing to last viewed page
                //@param config :  message and objName field, one of them should be configured
                app.on(messageTypes.confirmToBack).then(function (config) {
                    config.message = resManager.getResource("info.back");
                    self.confirm(config);
                });

                //subscribe a alert event
                app.on(messageTypes.alert).then(function (config) {
                    self.updateMessageByAction(config, messageTypes.alert, null);
                    self.alert(config);
                });
                return this;
            },

            /*
             * update the message by action
             * @param config  config object. For message and objName field, one of them should be configured.
             * @param eventName     event name
             * @param actionResource  the of text resource action
             */
            updateMessageByAction: function (config, eventName, actionResource) {
                if (!config.message) {
                    if (!config.objName) {
                        system.log(commonUtils.logLevel.error, eventName, ": The message and objName one of them needs to be configured.(message=",
                            config.message, ",objName=", config.objName, ")");
                        config.objName = "";
                    }

                    if (actionResource) {
                        var actionMsg = resManager.getResource(actionResource),
                            msg = resManager.getResource("common.confirm.message", actionMsg, config.objName);
                        config.message = msg;
                    }
                }
            },

            /**
             * show alert dialog
             *  @param config the fields include {title,message,okCallback}
             */
            alert: function (config) {
                var self = this,
                    initConfig = {
                        title: resManager.getResource("common.warning"),
                        message: '',
                        type: dialog.TYPE_WARNING,
                        draggable: true,
                        //size: dialog.SIZE_LARGE,
                        buttons: [
                            {
                                label: resManager.getResource("common.button.ok"),
                                icon: 'glyphicon glyphicon-check',
                                cssClass: 'btn-primary',
                                hotkey: 13, // Enter.
                                autospin: true,
                                action: self._cancelCallback
                            }
                        ]

                    };

                self.copyConfiguration(initConfig, config);
                dialog.show(initConfig);
            },

            /**
             * Display error messages
             * @param config the fields include {title,message,okCallback}
             */
            showError: function (config) {
                if (config) {
                    config.type = dialog.TYPE_DANGER;
                    config.title = resManager.getResource("common.error");
                }
                this.alert(config);
            },

            /**
             *show confirm dialog
             * @param config the fields include {title,message,okCallback,cancelCallback}
             */
            confirm: function (config) {
                var self = this,
                    initConfig = {
                        title: resManager.getResource("common.confirm"),
                        message: '',
                        type: dialog.TYPE_WARNING,
                        draggable: true,
                        //size: dialog.SIZE_LARGE,
                        buttons: [
                            {
                                label: resManager.getResource("common.button.ok"),
                                icon: 'glyphicon glyphicon-check',
                                cssClass: 'btn-primary',
                                hotkey: 13, // Enter.
                                autospin: true,
                                action: self._okCallback
                            },
                            {
                                label: resManager.getResource("common.button.cancel"),
                                icon: 'glyphicon glyphicon-back',
                                action: self._cancelCallback
                            }
                        ]

                    };

                self.copyConfiguration(initConfig, config);

                dialog.show(initConfig);
            },

            /*
             * update the configuration
             */
            copyConfiguration: function (initConfig, config) {
                if (is.object(config)) {
                    if (is.object(config.buttons)) {
                        //discard the buttons in order to use default buttons defined above
                        delete config.buttons;
                    }
                    initConfig = $.extend(initConfig, config);
                }

                if (is.function(config.okCallback)) {
                    initConfig.buttons[0].action = config.okCallback;
                }

                if (is.function(config.cancelCallback)) {
                    initConfig.buttons[1].action = config.cancelCallback;
                }
            },

            /**
             * Display a progress dialog
             */
            showProgress: function () {
                if (!is.object(this.progressDialog)) {
                    var progressDialog = new dialog({
                        message: function (dialogRef) {
                            var $message = $('<div class="splash"><i class="fa fa-spinner fa-spin"></i></div>');
                            var $button = $('<h3 style="color: #ffffff;">System Loading......</h3>');
                            $button.on('click', {dialogRef: dialogRef}, function (event) {
                                event.data.dialogRef.close();
                            });
                            $message.append($button);

                            return $message;
                        },
                        closable: false
                    });
                    progressDialog.realize();
                    progressDialog.getModalHeader().hide();
                    progressDialog.getModalFooter().hide();
                    progressDialog.getModalBody().css('background-color', '#0088cc');
                    //progressDialog.getModalBody().css('color', '#fff');
                    this.progressDialog = progressDialog;
                }
                this.progressDialog.open();
            },

            /**
             * Close current progress dialog
             */
            closeProgress: function () {
                if (is.object(this.progressDialog)) {
                    this.progressDialog.close();
                    this.progressDialog = null;
                }
            },

            /**
             * Default callback corresponds to cancel button
             * @param dialogRef
             */
            _cancelCallback: function (dialogRef) {
                dialogRef.close();
            },

            /**
             * Default callback corresponds to ok button
             * @param dialogRef
             */
            _okCallback: function (dialogRef) {
                dialogRef.close();
            }

        };

        return dialogUtil.init();

    });
