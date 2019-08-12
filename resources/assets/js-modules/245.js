__d(function(t,n,e,a){"use strict";function r(t,n,e,a,r,u,i,o){var l=t;if(l<n){if("identity"===i)return l;"clamp"===i&&(l=n)}if(l>e){if("identity"===o)return l;"clamp"===o&&(l=e)}return a===r?a:n===e?t<=n?a:r:(n===-1/0?l=-l:e===1/0?l-=n:l=(l-n)/(e-n),l=u(l),a===-1/0?l=-l:r===1/0?l+=a:l=l*(r-a)+a,l)}function u(t){var n=h(t);return null===n?t:"rgba("+((4278190080&(n=n||0))>>>24)+", "+((16711680&n)>>>16)+", "+((65280&n)>>>8)+", "+(255&n)/255+")"}function i(t){var n=t.outputRange;g(n.length>=2,"Bad output range"),n=n.map(u),l(n);var e=n[0].match(m).map(function(){return[]});n.forEach(function(t){t.match(m).forEach(function(t,n){e[n].push(+t)})});var a=n[0].match(m).map(function(n,a){return v.create(babelHelpers.extends({},t,{outputRange:e[a]}))}),r=o(n[0]);return function(t){var e=0;return n[0].replace(m,function(){var n=+a[e++](t),u=r&&e<4?Math.round(n):Math.round(1e3*n)/1e3;return String(u)})}}function o(t){return"string"==typeof t&&t.startsWith("rgb")}function l(t){for(var n=t[0].replace(m,""),e=1;e<t.length;++e)g(n===t[e].replace(m,""),"invalid pattern "+t[0]+" and "+t[e])}function c(t,n){for(var e=1;e<n.length-1&&!(n[e]>=t);++e);return e-1}function p(t){g(t.length>=2,"inputRange must have at least 2 elements");for(var n=1;n<t.length;++n)g(t[n]>=t[n-1],"inputRange must be monotonically increasing "+t)}function f(t,n){g(n.length>=2,t+" must have at least 2 elements"),g(2!==n.length||n[0]!==-1/0||n[1]!==1/0,t+"cannot be ]-infinity;+infinity[ "+n)}var g=n(18),h=n(48),s=function(t){return t},v=function(){function t(){babelHelpers.classCallCheck(this,t)}return t.create=function(t){if(t.outputRange&&"string"==typeof t.outputRange[0])return i(t);var n=t.outputRange;f("outputRange",n);var e=t.inputRange;f("inputRange",e),p(e),g(e.length===n.length,"inputRange ("+e.length+") and outputRange ("+n.length+") must have the same length");var a=t.easing||s,u="extend";void 0!==t.extrapolateLeft?u=t.extrapolateLeft:void 0!==t.extrapolate&&(u=t.extrapolate);var o="extend";return void 0!==t.extrapolateRight?o=t.extrapolateRight:void 0!==t.extrapolate&&(o=t.extrapolate),function(t){g("number"==typeof t,"Cannot interpolation an input which is not a number");var i=c(t,e);return r(t,e[i],e[i+1],n[i],n[i+1],a,u,o)}},t}(),m=/[0-9\.-]+/g;e.exports=v},245);