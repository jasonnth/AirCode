__d(function(e,n,t,r){function i(e,n,t){return[v.DeviceEventEmitter.addListener("onTripEventDataChanged:"+e,function(e){return u(e,n,t)}),v.DeviceEventEmitter.addListener("onTripEventDataFailed:"+e,t)]}function o(e){e.forEach(v.DeviceEventEmitter.removeSubscription)}function c(e){return new Promise(function(n,t){var r=i(e,function(e){o(r),n(e)},function(e){o(r),t(e)})})}function u(e,n,t){try{n(JSON.parse(e))}catch(e){t(e)}}Object.defineProperty(r,"__esModule",{value:!0});var a=n(42),f=babelHelpers.interopRequireDefault(a),v=n(44),s=function(){return new Promise(function(){})},d=f.default.module({moduleName:"ItineraryBridge",mock:{onPlaceRemoved:function(){},onExperienceRemoved:function(){},fetchHomeReservation:s,fetchPlaceReservation:s,fetchExperienceReservation:s}}),m={onPlaceRemoved:function(e){return d.onPlaceRemoved(+e)},onExperienceRemoved:function(e){return d.onExperienceRemoved(+e)},fetchHomeReservation:function(e,n){return d.fetchHomeReservation(e,n),c(e)},fetchPlaceReservation:function(e){return d.fetchPlaceReservation(String(e)),c(e)},fetchExperienceReservation:function(e){return d.fetchExperienceReservation(String(e)),c(e)},subscribe:i,unsubscribe:o};r.default=m},725);