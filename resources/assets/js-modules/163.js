__d(function(t,r,e,s){"use strict";function n(){if(void 0===v){var t=l.scriptURL,r=t&&t.match(/^https?:\/\/.*?\//);v=r?r[0]:null}return v}function i(){if(void 0===p){var t=l.scriptURL;if(!t)return p=null;if(t.startsWith("assets://"))return p=null;p=t.startsWith("file://")?t.substring(7,t.lastIndexOf("/")+1):t.substring(0,t.lastIndexOf("/")+1)}return p}function u(t){d=t}function o(t){if("object"==typeof t)return t;var r=f.getAssetByID(t);if(!r)return null;var e=new c(n(),i(),r);return d?d(e):e.defaultAsset()}var f=r(164),c=r(165),a=r(25),l=a.SourceCode,d=void 0,v=void 0,p=void 0;e.exports=o,e.exports.pickScale=c.pickScale,e.exports.setCustomSourceTransformer=u},163);