/*global define,ComponentType,$, jQuery*/
define([ComponentType.is, ComponentType.jquery], function (is, $) {
    'use strict';
    var errorUtil = {

        /**
         * Show error message, there're 3 types of error messages as bellow:
         * Form error message: this message will show tooltips to each form widgets
         * Global error message: this message will use dialog to display
         * @param smfResult SmfResult retrieved from server
         * @param callbackObj includes two levels of callback functions . e.g.
         *       var callbackObj = {filedErrorHandler: func, globalErrorHandler: func2}
         */
        showErrors: function (smfResult, callbackObj) {
            var filedErrorsfunc = is.propertyDefined(callbackObj, "filedErrorHandler") || null;
            var globalErrorsfunc = is.propertyDefined(callbackObj, "globalErrorHandler") || null;
            this.showFormErros(smfResult, filedErrorsfunc);
            this.showGlobalErrors(smfResult, globalErrorsfunc);
        },

        /**
         * display form error message
         * @param onFiledErrorsExist 自定义Field Error 的显示处理，可在具体子模块覆盖该方法,
         * 当该方法没有返回或返回false时则由由覆盖的方法负责显示错误信息，且不再使用默认的显示方式
         */
        showFormErros: function (smfResult, filedErrorHandler) {
            if (is.object(smfResult) && !smfResult.fieldErrorsEmpty) {
                $(smfResult.fieldErrors).each(function (err) {
                    if (is.function(filedErrorHandler)) {
                        //if no custom handler or the handler exists and returns true
                        if (!is.existy(filedErrorHandler) || filedErrorHandler(err)) {
                            var widget = $("input[name=" + err.key + "]");

                            //显示popover控件
                            widget.popover({
                                container: 'body',
                                html: true,
                                content: err.message,
                                title: '<i class="glyphicon glyphicon-remove"></i>&nbsp;Error',
                                trigger: 'focus',
                                // delay: {"show": 500, "hide": 100},
                                placement: "bottom"
                            }).parent().addClass("has-error");
                        }
                    }
                })
            }
        },

        showGlobalErrors: function (smfResult,globalErrorHandler) {
            if (is.object(smfResult) && !smfResult.globalErrorsEmpty) {
                $(smfResult.globalErrors).each(function (err) {
                    if (is.function(globalErrorHandler)) {
                        //if no custom handler or the handler exists and returns true
                        if (!is.existy(globalErrorHandler) || globalErrorHandler(err)) {
                           alert(err.message)
                        }
                    }
                })
            }
        }
    }
    return errorUtil;
})