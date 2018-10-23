/*global define,ComponentType,$, jQuery*/

var j = {
    "invalidSession": false,
    "globalErrors": [],
    "fieldErrors": [],
    "notices": [],
    "attachedObj": {
        "theme": {
            "id": null, "defaultTheme": {
                "id": 1,
                "name": "Blue",
                "displayName": "{header.theme.blue}",
                "link": "bootstrap-cerulean.min.css",
                "enabled": true
            }
            ,
            "themesList": [{
                "id": 1,
                "name": "Blue",
                "displayName": "{header.theme.blue}",
                "link": "bootstrap-cerulean.min.css",
                "enabled": true
            }, {
                "id": 2,
                "name": "Black",
                "displayName": "{header.theme.Black}",
                "link": "bootstrap-cyborg.min.css",
                "enabled": true
            }, {
                "id": 3,
                "name": "Hoary",
                "displayName": "{header.theme.Hoary}",
                "link": "bootstrap-simplex.min.css",
                "enabled": true
            }, {
                "id": 4,
                "name": "DarkBlue",
                "displayName": "{header.theme.DarkBlue}",
                "link": "bootstrap-darkly.min.css",
                "enabled": true
            }, {
                "id": 5,
                "name": "Frenchgrey",
                "displayName": "{header.theme.Frenchgrey}",
                "link": "bootstrap-lumen.min.css",
                "enabled": true
            }, {
                "id": 5,
                "name": "Darkgrey",
                "displayName": "{header.theme.Darkgrey}",
                "link": "bootstrap-slate.min.css",
                "enabled": true
            }, {
                "id": 5,
                "name": "Gray2",
                "displayName": "{header.theme.Gray2}",
                "link": "bootstrap-spacelab.min.css",
                "enabled": true
            }, {
                "id": 5,
                "name": "Orange",
                "displayName": "{header.theme.Orange}",
                "link": "bootstrap-united.min.css",
                "enabled": true
            }]
        }
        ,
        "user": {
            "id": 1,
            "name": "admin",
            "password": null,
            "description": "Default Administrator",
            "pwdChanged": false,
            "lastLogin": 1442826444000,
            "locked": false
        }
    }
    ,
    "fieldErrorsEmpty": true,
    "globalErrorsCount": 0,
    "fieldErrorsCount": 0,
    "ok": true,
    "globalErrorsEmpty": true,
    "noticesEmpty": true
}