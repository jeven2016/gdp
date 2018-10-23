/**
 * File: Common utils
 * Date: 2015-10-10/28/15
 * Created by root on 10/28/15.
 * Description: NA
 * Update: NA
 *        e.g. 2015-10-12:  Henry , change some code for his needs.
 */
/*global define,ComponentType,$, jQuery*/
define([ComponentType.jquery, ComponentType.is, "durandal/system"], function ($, is, system) {
    'use strict';
    var action = {add: "add", update: "update", view: "view", delete: "delete"};
    var logLevel = {error: "[error]", warn: "[warning]", info: "[info]", debug: "[debug]"};

    /*
     * 组合URI地址
     * @param
     */
    var uriJoin = function (/*link1, link2,XX,*/) {
        var args = arguments;
        var link = "";
        var isValidArgs = true;
        $(args).each(function (index, param) {
            if (!is.existy(param)) {
                isValidArgs = false;
                return false;
            }

            if (!is.endWith(link, "/") && !is.empty(link)) {
                link += "/";
            }
            link += param;

        });

        if (is.existy(link)) {
            system.log(logLevel.error, "invalid link(s)( ", args, ") from file (commonUtils.js)");
        }
        return link;
    };

    //hash for browser history
    var hash = {
        home: "#home",
        memcachedServer: "#memcached/server",
        memcachedServer_index: "#memcached/server/index",

        join: function (/*link1, link2,XX,*/) {
            return uriJoin.apply(this, arguments);
        }
    };

    //The uri for ajax request
    var serviceVersion = "v1";

    var uri = {
        login: serviceVersion + "/login",
        settings: serviceVersion + "/settings",
        logout: serviceVersion + "/logout",

        memcachedServer: serviceVersion + "/memcached/server",

        memcachedServer_new: "memcached/server/new",

        changeLanguage: "language",
        changeTheme: "theme",

        join: function (/*link1, link2,XX,*/) {
            return uriJoin.apply(this, arguments);
        }
    };

    //SmfResult Parser
    var smfResultParser = {
        parse: function (result) {
            var errorMsg,
                finalResult = {
                    globalErrors: [],
                    fieldErrors: []
                };

            if (!result) {
                return finalResult;
            }

            errorMsg = result.errorMsg;
            if (is.propertyDefined(errorMsg, "globalErrors")) {
                finalResult.globalErrors = errorMsg.globalErrors;
            }
            if (is.propertyDefined(errorMsg, "fieldErrors")) {
                finalResult.fieldErrors = errorMsg.fieldErrors;
            }
            return finalResult;
        }
    };


    return {
        //操作类型
        action: action,

        //日志级别
        logLevel: logLevel,

        //hash for browser history
        hash: hash,

        //The uri for ajax request
        uri: uri,

        //SmfResult parser
        resultParser: smfResultParser

    };
});
