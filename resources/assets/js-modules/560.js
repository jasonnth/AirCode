__d(function(r,t,n,e){function f(r,t){for(var n in r)if(o.call(r,n))return r[n]===t[n];return!1}function u(r,t){if(!r&&!t||r===t)return!0;if(!r!=!t)return!1;if(r.length!==t.length)return!1;for(var n=0;n<r.length;n++)if(!f(r[n],t[n]))return!1;return!0}function i(r,t){var n,e=0,f=0;for(n in r)if(o.call(r,n)){switch(n){case"transform":if(!u(r[n],t[n]))return!1;break;case"shadowOffset":if(!i(r[n],t[n]))return!1;break;default:if(r[n]!==t[n])return!1}e++}for(n in t)o.call(t,n)&&f++;return e===f}function a(r,t){if(!r&&!t||r===t)return!0;if(!r!=!t)return!1;switch(typeof r){case"object":if(r instanceof Array){for(var n=0;n<r.length;n++)if(!a(r[n],t[n]))return!1;return r.length===t.length}return i(r,t);case"number":default:return r===t}}var o=Object.prototype.hasOwnProperty;n.exports=a},560);