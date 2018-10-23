/*global define,ComponentType,$, jQuery*/
/**
 * 消息通知
 * 这里使用的是knockout的事件组件，非durandal提供的事件插件(app.on)
 */
define("message", ["knockout"], function (ko) {
    'use strict';
    var messager = new ko.subscribable();

    /**
     * 监听事件的类型
     */
    var messageType = {
        loggedIn: "user:loggedIn" //事件:用户是否登录了系统
    }

    return {
        /**
         * 获取消息通知发送者
         * @returns {ko.subscribable}
         */
        getNotifier: function () {
            return messager;
        },

        /**
         * Generate a message
         * @param data data to be passed
         * @param from where dose this event sent from
         */
        generateMsg: function (data, from) {
            return {data: data, from: from};
        },

        /**
         * 事件类型
         */
        type: messageType
    }

})
;