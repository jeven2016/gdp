/*global define,ComponentType,$,requirejs*/
window.currentVersion = "v1";

/**
 * 当前支持的组件类型
 */
window.ComponentType = {
    durandal: "durandal",
    plugins: "plugins",
    transitions: "transitions",
    crossroads: "crossroads",
    is: "is",
    jquery: "jquery",
    knockout: "knockout",
    signals: "signals",
    text: "text",
    bootstrap: "bootstrap",

    /**jquery plugins**/
    chosen: "chosen",
    table: "table",
    sco_message: "sco_message",
    page: "page",
    "bootstrap-dialog": "bootstrap-dialog'", //此组件在内部定义了一个ID为“bootstrap-dialog”的模块
    validator: "validator", //jquery validation 组件
    validation_tooltip: "validation_tooltip", //当validation失败后的错误提示，使用tooltip方式显示
    menu: "menu",//左侧边栏

    /**Components**/
    ajaxUtil: "ajaxUtil", //Ajax请求工具组件
    message: "message",  //消息Pub/Sub组件
    messageTypes: "messageTypes", //消息类型定义
    userWebSetting: "userWebSetting", //web系统配置组件
    resManager: "resManager", //国际化资源管理组件
    routerDef: "routerDef", //路由信息配置组件
    customBinding: "customBinding", //自定义绑定组件
    errorUtil: "errorUtil", //错误信息显示组件
    userInfoUtil: "userInfoUtil", //用户信息工具组件
    commonUtils: "commonUtils", //公共工具组件
    validatorUtils: "validatorUtils",//validators component
    dialogUtil: "dialogUtil", //对话框工具组件
    notify: "pnotify" //消息通知组件
};

/**
 * Get the configuration of requireJs
 * @param suffix
 * @param setting
 * @returns config
 */
function getConfig(suffix, setting) {
    var config = {
        //urlArgs: "version=1.0",
        //context : "smf_spa",
        //base on directory of html page
        // baseUrl: "spa2",

        paths: {
            durandal: "dep/durandal/js",
            plugins: "dep/durandal/js/plugins",
            transitions: "dep/durandal/js/transitions",
            is: "dep/is" + suffix,
            jquery: "dep/jquery" + suffix,
            knockout: "dep/knockout" + suffix,
            text: "dep/require/text",
            bootstrap: "dep/bt/js/bootstrap" + suffix,
            lodashCore: "dep/lodash/lodash.core" + suffix,  //lodash core build
            lodash: "dep/lodash/lodash" + suffix,   //lodash full build

            /**jquery plugins**/
            chosen: "dep/jplugins/chosen/chosen.jquery" + suffix,
            table: "dep/jplugins/datatables/media/js/jquery.dataTables",
            sco_message: "dep/jplugins/message/sco.message",
            page: "dep/jplugins/pagination/twbsPagination" + suffix,
            "bootstrap-dialog": "dep/jplugins/dialog/bootstrap-dialog" + suffix,
            validator: "dep/jplugins/validation/jquery.validate" + suffix,
            validation_tooltip: "dep/jplugins/validation/validation.tooltip" + suffix,
            validation_message: "dep/jplugins/validation/validation.message",

            /**Components**/
            ajaxUtil: "common/ajaxUtil",
            commonUtils: "common/commonUtils",
            validatorUtils: "common/validatorUtils",
            message: "common/message",
            messageTypes: "common/messageTypes",
            userWebSetting: "common/userWebSetting",
            resManager: "common/resManager",
            routerDef: "common/routerDef",
            customBinding: "common/customBinding",
            errorUtil: "common/errorUtil",
            userInfoUtil: "common/userInfoUtil",
            dialogUtil: "common/dialogUtil"
        },

        //Remember: only use shim config for non-AMD scripts,
        //scripts that do not already call define(). The shim
        //config will not work correctly if used on AMD scripts,
        //in particular, the exports and init config will not
        //be triggered, and the deps config will be confusing
        //for those cases.
        shim: {
            bootstrap: {
                deps: ["jquery"]
            },

            jquery: {
                exports: ["jQuery", "$"]
            },

            text: {
                deps: ["knockout"],
                exports: "text"
            },

            dialog: {
                deps: ["bootstrap"],
                exports: "dialog"
            },

            validator: {
                deps: ["jquery"]
            },

            validation_tooltip: {
                deps: ["validator", "validation_message"]
            }

        },

        config: {
            i18n: {
                //locale: "zh-CN"
                locale: setting.language
            }
        }
    };
    return config;
}

/**
 * Get system default settings and start application
 */

var startupApp = function (result) {
    'use strict';
    var setting, suffix;

    if (!result || !result.attachedObj) {
        alert("No required data retrieved from server, please contact system administrator.")
        return;
    }

    setting = result.attachedObj;
    suffix = setting.debugEnabled ? "" : ".min";

    //register all components with requireJs
    requirejs.config(getConfig(suffix, setting));

    //global error handler
    requirejs.onError = function (err) {
        throw err;
    };

    //application startup
    requirejs(['durandal/system', 'durandal/app', 'durandal/composition', ComponentType.customBinding,
            ComponentType.routerDef, ComponentType.messageTypes],
        function (system, app, composition, binding, routerDef, messageTypes) {
            //>>excludeStart("build", true);
            //system.debug(setting.debugEnabled);
            system.debug(true);
            //>>excludeEnd("build");

            //show application name to browser title
            app.title = setting.applicationName;

            //specify which plugins to install and their configuration
            app.configurePlugins({
                router: true,
                //dialog: true,
                //widget: true,
                observable: true
                /*  widget: {
                 kinds: ['expander'] //put widgets to what folder
                 }*/
            });

            //register global event listeners
            app.on(messageTypes.LoggedOut).then(function () {
                //navigate current page to home view modal
                composition.compose($("#applicationHost")[0], {model: "views/home/login"});
                routerDef.router.navigate('#', {replace: true, trigger: false}); //just update uri to history not triggering actual router
            });

            app.on(messageTypes.LoggedIn).then(function () {
                //navigate current page to home view modal
                composition.compose($("#applicationHost")[0], {model: "views/home/home"});
            });

            app.start().then(function () {
                /**
                 * The viewLocator has an API called useConvention. This allows you to set up a very basic naming replacement
                 * between module ids and view paths. For example, if you did this: viewLocator.useConvention('viewmodels', 'views');
                 * then when the view locator attempted to conventionally find a view for a module with id viewmodels/customer
                 * it would map it to views/customer. A wide range of developers prefer to organize their code into viewmodels
                 * and views folders, so you can actually achieve this with the following viewLocator.useConvention();.
                 */
                //viewLocator.useConvention();

                routerDef.init();
                if (result.validSession && setting.loggedIn) {
                    //forward to home page while the current user has logged in and
                    app.setRoot('views/home/home', 'entrance');
                } else {
                    //forward to login model while the current user hasn't logged in
                    app.setRoot("views/home/login", "entrance");
                }
            });
        });

};

$.get(window.currentVersion + "/init", null, startupApp);



