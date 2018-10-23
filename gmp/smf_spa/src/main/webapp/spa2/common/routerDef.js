/*global define,ComponentType,$, jQuery*/
/**
 * File: common/routerDef.js
 * Date: 2015-10-10/14/15
 * Created by root on 10/14/15.
 * Description: 定义应用内部的路由配置
 * Update: NA
 */
define(["plugins/router", ComponentType.resManager], function (router, resMgr) {
    'use strict';
    var routerCnfFun = {
        router: router,

        getTitle: function (key) {
            if (key) {
                return resMgr.getResource(key);
            }
            return resMgr.getResource("common.title");
        },

        createForLogin: function () {
            /* var routeConfigArray = [
             {route: ["login"], moduleId: "views/home/login", title: this.getTitle(), nav: true},
             {route: ["home", ""], moduleId: "views/home/home", title: this.getTitle(), nav: true}
             ];
             router.map(routeConfigArray).buildNavigationModel()
             // .mapUnknownRoutes('hello/index', 'not-found')
             .activate();*/
        },

        /**
         * 初始化路由信息
         */
        init: function () {
            //一级菜单对应的路由配置
            router.map([
                {
                    route: ["", "mainContent"],
                    moduleId: "views/home/mainContent",
                    title: this.getTitle(),
                    hash: "#mainContent",
                    nav: true
                },

                //当该路由由子路由时需要添加'*details'标记
                {route: "memcached*details", moduleId: "views/memcached/index", title: this.getTitle(), nav: true}

                //{route: ['', 'home'], moduleId: 'views/home/home', nav: 1}
                /* {
                 route: 'view-composition',
                 moduleId: 'viewComposition/index',
                 title: 'View Composition',
                 nav: true
                 },
                 {route: 'modal', moduleId: 'modal/index', title: 'Modal Dialogs', nav: 3},
                 {route: 'event-aggregator', moduleId: 'eventAggregator/index', title: 'Events', nav: 2},
                 {route: 'widgets', moduleId: 'widgets/index', title: 'Widgets', nav: true},
                 {route: 'master-detail', moduleId: 'masterDetail/index', title: 'Master Detail', nav: true},
                 {route: 'knockout-samples*details', moduleId: 'ko/index', title: 'Knockout Samples', nav: true},
                 {
                 route: 'keyed-master-details/:id*details',
                 moduleId: 'keyedMasterDetail/master',
                 title: 'Keyed Master Detail',
                 hash: '#keyed-master-details/:id'
                 }*/
            ]).buildNavigationModel()
                // .mapUnknownRoutes('hello/index', 'not-found')
                .activate();
        },


        /**
         * Create sub router for Memcached function
         */
        createForMemcached: function () {
            var childRouter = router.createChildRouter()
                .makeRelative({
                    moduleId: 'views/memcached',
                    router: "memcached",
                    fromParent: true
                }).map([
                    {
                        route: ['', 'cluster'],
                        moduleId: 'cluster/index',
                        title: this.getTitle('server.mgnt.cache.memcached.cluster'),
                        type: 'tab', //tab页类型
                        nav: true,
                        tabId: "cluster"
                    },
                    {
                        route: 'server/index',
                        moduleId: 'server/index',
                        //hash: "#memcached/server",
                        title: this.getTitle('server.mgnt.cache.memcached.server'),
                        type: 'tab',
                        nav: true,
                        tabId: "server"
                    },
                    {
                        route: 'server/:action(/:id)',
                        moduleId: 'server/action',
                        title: this.getTitle('server.mgnt.cache.memcached.server'),
                        type: 'details',  //详细页面类型
                        nav: true,
                        tabId: "server"
                    },
                    {
                        route: 'advance',
                        moduleId: 'advance/index',
                        title: this.getTitle('server.mgnt.cache.memcached.advance'),
                        type: 'tab',
                        nav: true,
                        tabId: "advance"  // required
                    }
                ]).buildNavigationModel();
            return childRouter;
        }


    };
    return routerCnfFun;

    //refer to http://stackoverflow.com/questions/19761261/durandal-child-router-with-parameter-from-parent
    //another  http://gotoanswer.com/?q=Multiple+levels+of+routing+in+Durandal
    //http://stephenwalther.com/archive/2013/02/08/using-durandal-to-create-single-page-apps
    /* You don't really have to implement multiple shells. We looked at this, too, in the beginning. You can have one shell, and then immediately and dynamically compose an inner shell within that. Inner shells could be swapped in with dynamic composition, which would still preserve your high-level routing. In other words, it wouldn't be necessary for every view to become a child route.

     In other words, the following would sit two levels down, not one:
     data-bind="router: { model: router.activeItem, cacheViews:false, compositionComplete: router.compositionComplete, attached: router.attached }">


     This would get moved into an inner shell:
     <div data-bind="compose: {model: currentShell()}"></div>


     where currentShell() is an observable that holds the current shell (inside the viewModel of your "super shell").*/
});

