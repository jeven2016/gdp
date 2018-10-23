/*global define,ComponentType,$, jQuery*/
define(["i18n!nls/res", ComponentType.is], function (res, is) {
    'use strict';

    return {

        defaultRes: "N/A",

        /**
         * Get a text resource
         * @param key  only one string key allowed
         * @param values an array contains mulitple values need to be inserted
         * @returns a combinated text resource
         */
        getResource: function (/*key,value1,value2*/) {
            if (is.empty(arguments)) {
                return this.defaultRes;
            }

            //only one key exists
            if (arguments.length == 1) {
                if (is.propertyDefined(res, arguments[0])) {
                    return res[arguments[0]];
                }
            }

            //a key and its value(s) exist
            var text = arguments[0];
            if (is.propertyDefined(res, text)) {
                var resource = res[text];
                for (var i = 1; i < arguments.length; i++) {
                    if (is.existy(arguments[i])) {
                        resource = resource.replace("{" + (i - 1) + "}", arguments[i])
                    }
                }
                return resource;
            }

            return this.defaultRes;
        }
    }
})