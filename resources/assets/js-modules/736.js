__d(function(e,n,t,r){function o(e,n){if(!(e instanceof n))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(r,"__esModule",{value:!0});var u=function(){function e(e,n){for(var t=0;t<n.length;t++){var r=n[t];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(n,t,r){return t&&e(n.prototype,t),r&&e(n,r),n}}(),i=n(735),c=function(e){return e&&e.__esModule?e:{default:e}}(i),s=function(){function e(){o(this,e),this.operations=[],this.requests=[]}return u(e,[{key:"collect",value:function(){function e(e,n,t){this.operations.push(e),this.requests.push({resolve:n,reject:t})}return e}()},{key:"resolve",value:function(){function e(e){this.requests.forEach(function(n,t){var r=e.operations[t].response;r.error_code?n.reject(new c.default(r)):n.resolve(r)})}return e}()},{key:"reject",value:function(){function e(e){this.requests.forEach(function(n){n.reject(e)})}return e}()}]),e}();r.default=s},736);