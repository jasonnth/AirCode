__d(function(n,e,t,r){"use strict";function o(n){var e=Function.prototype.toString,t=Object.prototype.hasOwnProperty,r=RegExp("^"+e.call(t).replace(/[\\^$.*+?()[\]{}|]/g,"\\$&").replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g,"$1.*?")+"$");try{var o=e.call(n);return r.test(o)}catch(n){return!1}}function u(n){var e=f(n);if(e){var t=e.childIDs;l(n),t.forEach(u)}}function a(n,e,t){return"\n    in "+(n||"Unknown")+(e?" (at "+e.fileName.replace(/^.*[\\\/]/,"")+":"+e.lineNumber+")":t?" (created by "+t+")":"")}function i(n){return null==n?"#empty":"string"==typeof n||"number"==typeof n?"#text":"string"==typeof n.type?n.type:n.type.displayName||n.type.name||"Unknown"}function p(n){var e,t=w.getDisplayName(n),r=w.getElement(n),o=w.getOwnerID(n);return o&&(e=w.getDisplayName(o)),a(t,r&&r._source,e)}var c,f,l,s,y,d,g,m=e(65),v=e(58),D=(e(18),e(22),"function"==typeof Array.from&&"function"==typeof Map&&o(Map)&&null!=Map.prototype&&"function"==typeof Map.prototype.keys&&o(Map.prototype.keys)&&"function"==typeof Set&&o(Set)&&null!=Set.prototype&&"function"==typeof Set.prototype.keys&&o(Set.prototype.keys));if(D){var I=new Map,M=new Set;c=function(n,e){I.set(n,e)},f=function(n){return I.get(n)},l=function(n){I.delete(n)},s=function(){return Array.from(I.keys())},y=function(n){M.add(n)},d=function(n){M.delete(n)},g=function(){return Array.from(M.keys())}}else{var h={},k={},C=function(n){return"."+n},S=function(n){return parseInt(n.substr(1),10)};c=function(n,e){var t=C(n);h[t]=e},f=function(n){var e=C(n);return h[e]},l=function(n){var e=C(n);delete h[e]},s=function(){return Object.keys(h).map(S)},y=function(n){var e=C(n);k[e]=!0},d=function(n){var e=C(n);delete k[e]},g=function(){return Object.keys(k).map(S)}}var b=[],w={onSetChildren:function(n,e){var t=f(n);t||m("144"),t.childIDs=e;for(var r=0;r<e.length;r++){var o=e[r],u=f(o);u||m("140"),null==u.childIDs&&"object"==typeof u.element&&null!=u.element&&m("141"),u.isMounted||m("71"),null==u.parentID&&(u.parentID=n),u.parentID!==n&&m("142",o,u.parentID,n)}},onBeforeMountComponent:function(n,e,t){c(n,{element:e,parentID:t,text:null,childIDs:[],isMounted:!1,updateCount:0})},onBeforeUpdateComponent:function(n,e){var t=f(n);t&&t.isMounted&&(t.element=e)},onMountComponent:function(n){var e=f(n);e||m("144"),e.isMounted=!0,0===e.parentID&&y(n)},onUpdateComponent:function(n){var e=f(n);e&&e.isMounted&&e.updateCount++},onUnmountComponent:function(n){var e=f(n);if(e){e.isMounted=!1;0===e.parentID&&d(n)}b.push(n)},purgeUnmountedComponents:function(){if(!w._preventPurging){for(var n=0;n<b.length;n++){u(b[n])}b.length=0}},isMounted:function(n){var e=f(n);return!!e&&e.isMounted},getCurrentStackAddendum:function(n){var e="";if(n){var t=i(n),r=n._owner;e+=a(t,n._source,r&&r.getName())}var o=v.current,u=o&&o._debugID;return e+=w.getStackAddendumByID(u)},getStackAddendumByID:function(n){for(var e="";n;)e+=p(n),n=w.getParentID(n);return e},getChildIDs:function(n){var e=f(n);return e?e.childIDs:[]},getDisplayName:function(n){var e=w.getElement(n);return e?i(e):null},getElement:function(n){var e=f(n);return e?e.element:null},getOwnerID:function(n){var e=w.getElement(n);return e&&e._owner?e._owner._debugID:null},getParentID:function(n){var e=f(n);return e?e.parentID:null},getSource:function(n){var e=f(n),t=e?e.element:null;return null!=t?t._source:null},getText:function(n){var e=w.getElement(n);return"string"==typeof e?e:"number"==typeof e?""+e:null},getUpdateCount:function(n){var e=f(n);return e?e.updateCount:0},getRootIDs:g,getRegisteredIDs:s};t.exports=w},159);