__d(function(e,t,n,r){"use strict";var i=t(129),u=t(130),s=t(131),o=t(132),l=t(133),a=t(18),c={},f=null,v=function(e,t){e&&(u.executeDispatchesInOrder(e,t),e.isPersistent()||e.constructor.release(e))},d=function(e){return v(e,!0)},p=function(e){return v(e,!1)},g=function(e){return"."+e._rootNodeID},E={injection:{injectEventPluginOrder:i.injectEventPluginOrder,injectEventPluginsByName:i.injectEventPluginsByName},putListener:function(e,t,n){a("function"==typeof n,"Expected %s listener to be a function, instead got type %s",t,typeof n);var r=g(e);(c[t]||(c[t]={}))[r]=n;var u=i.registrationNameModules[t];u&&u.didPutListener&&u.didPutListener(e,t,n)},getListener:function(e,t){var n=c[t],r=g(e);return n&&n[r]},deleteListener:function(e,t){var n=i.registrationNameModules[t];n&&n.willDeleteListener&&n.willDeleteListener(e,t);var r=c[t];if(r){delete r[g(e)]}},deleteAllListeners:function(e){var t=g(e);for(var n in c)if(c.hasOwnProperty(n)&&c[n][t]){var r=i.registrationNameModules[n];r&&r.willDeleteListener&&r.willDeleteListener(e,n),delete c[n][t]}},extractEvents:function(e,t,n,r){for(var u,s=i.plugins,l=0;l<s.length;l++){var a=s[l];if(a){var c=a.extractEvents(e,t,n,r);c&&(u=o(u,c))}}return u},enqueueEvents:function(e){e&&(f=o(f,e))},processEventQueue:function(e){var t=f;f=null,e?l(t,d):l(t,p),a(!f,"processEventQueue(): Additional events were enqueued while processing an event queue. Support for this has not yet been implemented."),s.rethrowCaughtError()},__purge:function(){c={}},__getListenerBank:function(){return c}};n.exports=E},128);