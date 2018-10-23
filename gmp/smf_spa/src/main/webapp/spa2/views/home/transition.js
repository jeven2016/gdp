/**
 * File:
 * Date: 2015-11-11/3/15
 * Created by root on 11/3/15.
 * Description: NA
 * Update: NA
 *        e.g. 2015-10-12:  Henry , change some code for his needs.
 */
/*global define,ComponentType,$*/
define([ComponentType.routerDef], function (routerDef) {
    'use strict';
    routerDef.createForLogin();
    return {
        router: routerDef.router
    };
});
