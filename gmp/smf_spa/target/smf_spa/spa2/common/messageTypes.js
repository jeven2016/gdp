/*global define,ComponentType,$, jQuery*/
define(function () {
    'use strict';
    var MessageType = {
        confirmToDelete: "confirm:delete",//confirm event
        confirmToSave: "confirm:save",//confirm event
        confirmToBack: "confirm:back",//confirm event
        confirm: "confirm:customize",//confirm event, you should specify the message
        alert: "alert",//alert event
        modal: "modalDialog", //display modal event


        LoggedOut: "LoggedOut", /*用户已经退出*/
        LoggedIn: "LoggedIn", /*用户已经登录*/

        /**Memcached Server*/
        Memcached: {
            enterToActionPage: "Memcached:enterToActionPage"  //进入操作页面的事件(对应add,update,view事件)
        }
    };
    return MessageType;
});