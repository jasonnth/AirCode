__d(function(t,n,e,o){"use strict";var r=n(388),f=n(405),u="__ global cache key __";"function"==typeof Symbol&&f(Symbol())&&"function"==typeof Symbol.for&&(u=Symbol.for(u));var i=function(){if(!t[u]){var n={};n[u]={},r(t,n)}return t[u]},c=i(),l=function(t){return null===t||"object"!=typeof t&&"function"!=typeof t},y=function(t){return f(t)?("function"==typeof Symbol?Symbol.prototype:"@@prototype").valueOf.call(t):typeof t+" | "+String(t)},a=function(t){if(!l(t))throw new TypeError("key must not be an object")},s={clear:function(){delete t[u],c=i()},delete:function(t){return a(t),delete c[y(t)],!s.has(t)},get:function(t){return a(t),c[y(t)]},has:function(t){return a(t),y(t)in c},set:function(t,n){a(t);var e={};return e[y(t)]=n,r(c,e),s.has(t)},setIfMissingThenGet:function(t,n){if(s.has(t))return s.get(t);var e=n();return s.set(t,e),e}};e.exports=s},404);