/*global define,ComponentType,$*/
define([ComponentType.jquery, ComponentType.routerDef, "durandal/app", ComponentType.messageTypes,
        ComponentType.table, ComponentType.page, ComponentType.ajaxUtil, ComponentType.knockout,
        ComponentType.commonUtils, ComponentType.resManager],
    function ($, routerDef, app, msgTypes, table, page, ajaxUtil, ko, commonUtils, resManager) {
        'use strict';
        function initPage() {
            //show table
            var tbl = $('#tbl');
            tbl.dataTable({
                "bPaginate": false,
                "bLengthChange": true,
                "bFilter": false,
                "bSort": true,
                "bInfo": false,
                "bAutoWidth": true,
                bSearch: false,
                //scrollY: 50,
                select: true,

                "oLanguage": {
                    "sInfoEmpty": resManager.getResource("common.search.no.records"),
                    "sZeroRecords": resManager.getResource("common.search.no.data.matched")
                }
            });


            $('#pagination').twbsPagination({
                totalPages: 35,
                visiblePages: 5,
                first: resManager.getResource("common.button.first"),
                prev: resManager.getResource("common.button.prev"),
                next: resManager.getResource("common.button.next"),
                last: resManager.getResource("common.button.last"),

//            href: '?page={{number}}',
                onPageClick: function (event, page) {
                    $('#page-content').text('Page ' + page);
                }
            });
        }

        return {
            //server list
            list: ko.observableArray([]),

            name: ko.observable(),

            //callback on view loaded
            //refer to http://durandaljs.com/documentation/Using-Composition.html
            //The lifecycle is as follows: getView, activate, binding, bindingComplete, attached,
            // compositionComplete, detached.
            attached: function () {
                initPage();
                $('.dropdown-toggle').dropdown();  //enable all dropdown lists
            },

            //进入增加/修改/查看页面,删除操作不进入子页面，直接删除成功后刷新列表
            action: function (actionType, serverObj) {
                var hashUtil = commonUtils.hash;
                var actionTypes = commonUtils.action;

                var link = hashUtil.memcachedServer;

                //create
                if (actionType == actionTypes.add) {
                    link = hashUtil.join(link, actionType);
                    //切换显示
                    routerDef.router.navigate(link);

                    //发送进入add页面的监听事件
                    app.trigger(msgTypes.Memcached.enterToActionPage, 'server');
                    return;
                }

                //update
                if (actionType == actionTypes.update) {
                    link = hashUtil.join(link, actionType, serverObj.id);

                    //切换显示
                    routerDef.router.navigate(link);

                    //发送进入add页面的监听事件
                    app.trigger(msgTypes.Memcached.enterToActionPage, 'server');
                    return;
                }

                //delete
                if (actionType == actionTypes.delete) {
                    var uri = commonUtils.uri;
                    var link = uri.join(uri.memcachedServer, serverObj.id);
                    var self = this;

                    //confirm and begin to delete
                    app.trigger(msgTypes.confirmToDelete,
                        {
                            objName: serverObj.name,
                            okCallback: function (dialogRef) {
                                dialogRef.close();
                                self.deleteById(link);
                            }
                        }
                    );

                    return;
                }
            },

            deleteById: function (link) {
                var self = this,
                    req = {
                        url: link,
                        successCallback: function (data) {
                            //clear servers list by using jquery instead of self.list.removeAll()
                            //have no idea why the servers cannot be removed ,maybe a bug exists here.
                            //so this is a workaround.
                            if (data.ok) {
                                $("#tbl tbody").html("");
                                self.list.removeAll();
                                self.showList();
                            }
                        }
                    };
                ajaxUtil.delete(req);
            },

            /**
             * show servers
             */
            showList: function () {
                var self = this,
                    req = {
                        url: commonUtils.uri.memcachedServer,
                        successCallback: function (data) {
                            //clear servers list by using jquery instead of self.list.removeAll()
                            //have no idea why the servers cannot be removed ,maybe a bug exists here.
                            //so this is a workaround.
                            if (data.ok) {
                                var serverList = data.attachedObj;
                                self.list(serverList);
                            }
                        }
                    };
                ajaxUtil.list(req);
            },

            activate: function () {
                //show servers on page
                this.showList();   //does it relate to promise?? need a investigation here.
            }
        };
    })