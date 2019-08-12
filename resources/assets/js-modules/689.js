__d(function(e,t,n,a){function o(e){return e&&e.__esModule?e:{default:e}}function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function u(e){if(Array.isArray(e)){for(var t=0,n=Array(e.length);t<e.length;t++)n[t]=e[t];return n}return Array.from(e)}function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function c(t){var n=void 0;return function(){for(var a=arguments.length,o=Array(a),r=0;r<a;r++)o[r]=arguments[r];e.cancelIdleCallback(n),n=e.requestIdleCallback(function(){n=null,t.apply(void 0,o)})}}Object.defineProperty(a,"__esModule",{value:!0});var l=function(){function e(e,t){for(var n=0;n<t.length;n++){var a=t[n];a.enumerable=a.enumerable||!1,a.configurable=!0,"value"in a&&(a.writable=!0),Object.defineProperty(e,a.key,a)}}return function(t,n,a){return n&&e(t.prototype,n),a&&e(t,a),t}}(),s=t(379),d=o(s),f=t(690),v=t(691),g=t(688),h=o(g),p=t(692),_=o(p),m=t(693),k=o(m),y=t(695),b=o(y),C="0.2",E="tracking_event_queue",x=null,q=function(){function e(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};if(i(this,e),this.logContext={},"function"!=typeof t.request)throw new Error("[tracking-js] EventLogger requires a `request` option to be passed in that is a function");this.request=t.request,this.setupTrackingDebounce(),this.debounceLogEvents=!!t.debounceLogEvents,this.pageURIChangeCallbacks=[]}return l(e,[{key:"setupTrackingDebounce",value:function(){function e(){var e=this,t=[],n=c(function(n){var a=n.map(function(e){return e.data}),o=n.map(function(e){return e.callback});t=[],e.sendTrackingRequest(a,function(e,t){o.filter(Boolean).forEach(function(n){return n(e,t)})})});this.sendTrackingRequestDebounced=function(e,a){if(a&&"function"!=typeof a)throw new Error("Callback provided was not of type function");t=[].concat(u(t),[{data:e,callback:a}]),n(t)}}return e}()},{key:"clearContext",value:function(){function e(){this.logContext={}}return e}()},{key:"addContext",value:function(){function e(e){babelHelpers.extends(this.logContext,e)}return e}()},{key:"addDefaultContext",value:function(){function e(){var e=this.getCookies();this.updateDynamicContext(),this.maybeAddCookie(e,"affiliate"),this.maybeAddCookie(e,"campaign"),this.maybeAddCookie(e,"bev");var t=d.default.locale();t&&this.addContext({locale:t,language:t.split("-")[0]})}return e}()},{key:"onPageURIChange",value:function(){function e(e){var t=this;this.pageURIChangeCallbacks.push(e);var n=!0;return function(){function a(){if(n){n=!1;var a=t.pageURIChangeCallbacks.indexOf(e);t.pageURIChangeCallbacks.splice(a,1)}}return a}()}return e}()},{key:"updateDynamicContext",value:function(){function e(){if("undefined"!=typeof document&&document.location){var e=!1;this.logContext.page_uri!==document.location.pathname&&(x=this.logContext.page_uri,e=null!=x),this.addContext({page_uri:document.location.pathname,page_referrer:x||document.referrer}),e&&this.pageURIChangeCallbacks.forEach(function(e){return e()})}}return e}()},{key:"logEvent",value:function(){function e(e){if(e.queue)return void this.queueEvent(e);this.validateOptions(e);var t=this.formatEventData(e);!1!==e.debounce&&(e.debounce||this.debounceLogEvents)?this.sendTrackingRequestDebounced(t,e.callback):this.sendTrackingRequest(t,e.callback)}return e}()},{key:"queueEvent",value:function(){function e(e){this.validateOptions(e);var t=this.formatEventData(e);try{var n=(0,h.default)(E)||[];n.push(t),(0,h.default)(E,n,{expires:6e4})}catch(e){(0,f.warn)("Could not add event to queue: "+String(e))}}return e}()},{key:"logJitneyEvent",value:function(){function e(e){var t=this,n=e.schema,a=e.event_data,o=e.queue;if(!n||!n.defaultProps||!n.defaultProps.schema)throw new TypeError("Need to pass schema object from airbnb-jitney-schemas");var r=n.defaultProps.schema;this.logEvent({event_name:"jitney_"+String(n.defaultProps.event_name),post_process:function(){function e(e){var o=(0,k.default)(e,n,a);o.schema=r,o.event_data.context.user_agent=window.navigator.userAgent;var u=(0,_.default)(n.propTypes,o.event_data);if(u.length>0){"undefined"!=typeof console&&console.error&&u.forEach(function(e){}),t.airdogBump("di.jitney.logging.invalid_event",1,["schema:"+String(r)]);var i=v.MalformedEvent.defaultProps.schema;return{schema:i,event_data:{schema:i,event_schema:r,event_json:JSON.stringify(o.event_data),errors:u.map(function(e){return e.toString()})}}}return o}return e}(),callback:function(){function e(e,n){e||(t.airdogBump("di.jitney.logging.tracking_error",1,["schema:"+String(r)]),t.logEvent({event_name:"jitney_tracking_error",event_data:{schema_name:r,status:n.status,responseText:n.responseText,timeout:n.timeout}}))}return e}(),queue:o,debounce:!1})}return e}()},{key:"queueJitneyEvent",value:function(){function e(e){var t=e.schema,n=e.event_data;this.logJitneyEvent({schema:t,event_data:n,queue:!0})}return e}()},{key:"flushEventQueue",value:function(){function e(){var e=this;try{var t=(0,h.default)(E)||[];"string"==typeof t&&(t=JSON.parse(t)),t.length>0&&this.sendTrackingRequest(t.map(function(t){return babelHelpers.extends({},t,{event_data:e.addContextToQueuedEvent(t.event_data)})})),(0,h.default)(E,null)}catch(e){(0,f.warn)("Could not flush event queue: "+String(e))}}return e}()},{key:"validateOptions",value:function(){function e(e){if(!e.event_name)throw new Error("event_name is a required key for logEvent")}return e}()},{key:"formatEventData",value:function(){function e(e){this.updateDynamicContext();var t={event_name:e.event_name,event_data:babelHelpers.extends({},this.logContext,{timestamp:Date.now()},e.event_data),trackingjs_logging_version:C};return e.post_process?e.post_process(t):t}return e}()},{key:"addContextToQueuedEvent",value:function(){function e(e){return babelHelpers.extends({},e,{trackingjs_queued:!0,trackingjs_queued_context:this.logContext})}return e}()},{key:"sendTrackingRequest",value:function(){function e(e,t){var n=this;this.request(e).then(function(){return t&&t(!0)}).catch(function(a){n.logEventFailed(e),t&&t(!1,a)}),(0,f.isDev)()&&(0,h.default)("log-in-dev")&&((0,f.log)("--- Tracking.logEvent ---"),(0,f.log)(e))}return e}()},{key:"logEventFailed",value:function(){function e(e){(0,f.warn)("Failed to log event (event="+String(e.event_name)+")")}return e}()},{key:"getCookies",value:function(){function e(){var e={},t=document.cookie;if(""===t)return e;for(var n=t.split("; "),a=0;a<n.length;a+=1){var o=n[a],r=o.indexOf("="),u=o.slice(0,r),i=o.slice(r+1);try{i=decodeURIComponent(i)}catch(e){this.logEvent({event_name:"cookie_decode_failed",event_data:{cookie:o}}),i=""}e[u]=i}return e}return e}()},{key:"maybeAddCookie",value:function(){function e(e,t){null!=e[t]&&this.addContext(r({},t,e[t]))}return e}()},{key:"airdogBump",value:function(){function e(e,t,n,a){var o={event_name:"airdog_bump",event_data:{datadog_key:e,queue:a}};t&&(o.event_data.datadog_count=t),n&&(o.event_data.datadog_tags=n),this.logEvent(o)}return e}()},{key:"logAirdogEvent",value:function(){function e(e){var t=e.event_data||{};this.logEvent(babelHelpers.extends({},e,{event_data:babelHelpers.extends({},t,{datadog_key:t.datadog_key||(0,b.default)(e)})}))}return e}()}]),e}();a.default=q},689);