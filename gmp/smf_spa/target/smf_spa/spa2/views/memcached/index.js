/*global define,ComponentType,$,fuelux*/
define([ComponentType.routerDef, "durandal/app", ComponentType.messageTypes, ComponentType.jquery, ComponentType.knockout],
    function (routerDef, app, msgTypes, $, ko) {
        'use strict';
        var childRouter = routerDef.createForMemcached(),

            viewModel = {
                router: childRouter,

                //current selected tab
                tabSelectedId: ko.observable(),

                /**
                 * whether current tab is selected
                 */
                isTabSelected: function (navigationModel) {
                    if (navigationModel.isActive()) {
                        this.tabSelectedId(navigationModel.tabId); //update current selected tab
                        return true;
                    }

                    if (navigationModel.tabId == this.tabSelectedId()) {
                        return true;
                    }
                    return false;
                }

            };

        //监听用户进入不同子页面的事件，并切换tab页选中的状态
        var subscription = app.on(msgTypes.Memcached.enterToActionPage).then(function (tabId) {
            viewModel.tabSelectedId(tabId); //update current selected tab
            $("#memcachedTab li a").each(function (index, domElement) {
                var aElem = $(domElement);
                var linkTabId = aElem.attr("tabId");

                // if the tab is which switching to
                if (tabId == linkTabId) {
                    //change to selected class
                    aElem.addClass("active");
                }
                else {
                    aElem.removeClass("active");
                }

            });
        });


        return viewModel;
    })