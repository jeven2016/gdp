/**
 * 顶部导航栏 View Model
 */
define([ComponentType.knockout, ComponentType.ajaxUtil, "durandal/system", ComponentType.commonUtils, "durandal/app",
        ComponentType.messageTypes, ComponentType.is, ComponentType.jquery, ComponentType.resManager],
    function (ko, ajaxUtil, system, commonUtils, app, messageTypes, is, $, resManager) {
        'use strict';
        function TopNav() {
            var self = this;
            self.themes = ko.observableArray();
            self.currentTheme = ko.observable();
            self.user = ko.observable({name: ""});

            /**
             * switch user's system theme
             * @param theme
             */
            self.switchTheme = function (theme) {
                if (is.equal(self.currentTheme().name, theme.name)) {
                    return;
                }
                var uriUtil = commonUtils.uri, url, req;
                url = uriUtil.join(uriUtil.changeTheme, theme.name);

                req = {
                    url: url,
                    successCallback: function (data) {
                        system.log(commonUtils.logLevel.info, "changing theme to ", theme.name, " , the result is ", data);
                        if (data.ok) {
                            //load new css file
                            var nodes,
                                link = $("head link[themeLink]"),
                                linkUrl = link.attr("href");
                            linkUrl = linkUrl.replace(self.currentTheme().link, theme.link);
                            link.attr("href", linkUrl);
                            self.currentTheme(theme);//update current theme

                            //update selected theme
                            nodes = $("#themes a i");
                            nodes.each(function () {
                                var iNode = $(this),
                                    themeName = $(this).attr("name");
                                if (is.existy(themeName)) {
                                    if (is.equal(themeName, theme.name)) {
                                        iNode.removeClass().addClass("glyphicon glyphicon-ok");
                                    } else {
                                        iNode.removeClass().addClass("whitespace");
                                    }
                                }
                            });
                        }
                    }
                };
                ajaxUtil.get(req);
            };

            /**
             * switch user's current language
             * @param lan  the language will be changed to
             */
            self.switchLan = function (lan) {
                var uriUtil = commonUtils.uri, url, req;
                url = uriUtil.join(uriUtil.changeLanguage, lan);
                req = {
                    url: url,
                    successCallback: function (data) {
                        system.log(commonUtils.logLevel.info, "changing the language to ", lan, " , the result is ", data);
                        if (data.ok) {
                            window.location.reload();
                        }
                    }
                };
                ajaxUtil.get(req);
            };

            /**
             * logout then the page will forward to login page
             */
            self.logout = function () {
                var req = {
                    url: commonUtils.uri.logout,
                    checkOk: false,
                    checkSession: false,
                    successCallback: function (data) {
                        system.log(commonUtils.logLevel.info, self.user().name, " try to log out and the result is ", data);
                        if (data.ok) {
                            //navigate current page to login view modal
                            app.trigger(messageTypes.LoggedOut);
                        }
                    }
                };
                ajaxUtil.get(req);
            };

            /*
             *dispaly languages and current user info
             */
            self.init = function () {
                var user, theme,
                    req = {
                        url: commonUtils.uri.settings,
                        successCallback: function (data) {
                            try {
                                user = data.attachedObj.user;
                                theme = data.attachedObj.theme;

                                //update user info
                                self.user(user);

                                //the current theme must be handled before themesList,because
                                //the name of currentTheme will be used within loop function of themesList.
                                //if don't ,a error indicates that no name of currentTheme exists.
                                self.currentTheme(theme.defaultTheme);

                                //reset displayName if it relates to one of international resources
                                $(theme.themesList).each(function (index, themeItem) {
                                    var displayName = themeItem.displayName;
                                    displayName = $.trim(displayName);
                                    if (is.startWith(displayName, "{") && is.endWith(displayName, "}")) {
                                        displayName = displayName.replace(/[({|}]/ig, "");
                                        themeItem.displayName = resManager.getResource(displayName);
                                    }
                                });

                                //display list of themes
                                self.themes(theme.themesList);
                            } catch (e) {
                                system.log(commonUtils.logLevel.error, "Error occurs while requesting url '/setting' in topNav.js: ",
                                    e.message, " and the data is ", data);
                            }
                        }
                    };
                ajaxUtil.get(req);
            };

            self.activate = function () {
                self.init();
            };

            self.attached = function () {
                $('.dropdown-toggle').dropdown();  //enable all dropdown lists

            };
        }

        return TopNav;
    });