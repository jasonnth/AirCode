__d(function(e,r,t,a){function o(e){var r=e.threadID,t=e.withScopedAuth,a=e.limit,o=e.forwardCursor,n=e.backwardCursor,i=d({},p,{message_thread_id:r,_with_scoped_auth:String(t),_limit:a});return o&&(i._before=o),n&&(i._after=n),i}function n(e){return d({},h,{query:o(e)})}function i(e,r,t){var a={};return r||(a[c.BACKWARD_CURSOR]=t),e||(a[c.FORWARD_CURSOR]=t),a}function u(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},t=arguments[2],a=arguments[3],o=e.messages,n=void 0===o?[]:o,u=e.metadata,s=void 0===u?{}:u,d=r.message_thread_id,l=r._after,c=r._before,h={};if(n.length){s.cursor&&(h.meta=i(l,c,s.cursor)),!l&&c||(h.olderItemsAvailable=n.length===s.limit);var p=n.map(function(e){return(0,_.toItem)(d,e,t,a)}),v=(0,f.default)(p);l?h.olderItemsToUpsert=v:h.newerItemsToUpsert=v}return h}function s(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return u(e.response,e.query,arguments[1],arguments[2])}Object.defineProperty(a,"__esModule",{value:!0});var d=Object.assign||function(e){for(var r=1;r<arguments.length;r++){var t=arguments[r];for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&(e[a]=t[a])}return e},_=r(2338),l=r(2341),f=function(e){return e&&e.__esModule?e:{default:e}}(l),c=r(2332),h={method:"GET",path:"/messages"},p={_order_by:"created_at",_order:"DESC"};a.default={buildQuery:o,buildOperation:n,parseOperation:s,parseResponse:u}},2340);