'use strict';

app.service('loginService',
    function loginService($resource) {
        return $resource('services/ldapServicioResource/:login/:password', {
        	login: '@login',password:'@password'
        }, {
            userInfo: {
                method: 'POST'
            }
        });
    });


 