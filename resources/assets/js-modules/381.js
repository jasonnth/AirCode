__d(function(t,r,o,n){function a(t,r,o){if(!i(r))throw new TypeError("iterator must be a function");arguments.length<3&&(o=this),"[object Array]"===f.call(t)?c(t,r,o):"string"==typeof t?e(t,r,o):l(t,r,o)}function c(t,r,o){for(var n=0,a=t.length;n<a;n++)p.call(t,n)&&r.call(o,t[n],n,t)}function e(t,r,o){for(var n=0,a=t.length;n<a;n++)r.call(o,t.charAt(n),n,t)}function l(t,r,o){for(var n in t)p.call(t,n)&&r.call(o,t[n],n,t)}var i=r(382);o.exports=a;var f=Object.prototype.toString,p=Object.prototype.hasOwnProperty},381);