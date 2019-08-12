__d(function(t,e,n,r){function i(t,e){return 12*e+t}function s(t,e){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:new Date;return[3,2,1].find(function(r){return(0,h.default)(new Date(e,t+(r-1),0)).diff(n,"days")<a})||1}function o(t){return{month:(t-1)%12+1,year:Math.floor((t-1)/12)}}Object.defineProperty(r,"__esModule",{value:!0}),r.getKey=i,r.getCountRespectingMax=s,r.getMonthYearFromKey=o;var u=e(562),h=babelHelpers.interopRequireDefault(u),c=e(726),a=1125,l=function(){function t(e){var n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null;babelHelpers.classCallCheck(this,t),this.listingId=e,this.excludeReservationId=n,this.months={},this.isFetching=!1,this.subscribers={}}return t.prototype.subscribe=function(t,e,n){var r=i(t,e);this.subscribers[r]=n},t.prototype.unsubscribe=function(t,e){var n=i(t,e);delete this.subscribers[n]},t.prototype.notify=function(t,e){var n=i(t,e),r=this.months[n],s=this.subscribers[n];s&&s(r)},t.prototype.get=function(t,e){var n=this,r=i(t,e);return this.months[r]?Promise.resolve(this.months[r]):this.fetch(t,e).then(function(){return n.months[r]})},t.prototype.fetch=function(t,e){var n=this;return this.isFetching?this.waitForFetch(t,e):(this.isFetching=!0,c.CalendarMonthsResource.collection({listing_id:this.listingId,exclude_reservation_with_id:this.excludeReservationId,count:s(t,e),month:t,year:e}).then(function(t){return t.calendar_months}).then(function(t){n.isFetching=!1,n.store(t),t.forEach(function(t){n.notify(t.month,t.year)})}).then(function(){var t=Object.keys(n.subscribers);if(t.length>0){var e=o(Math.min.apply(Math,babelHelpers.toConsumableArray(t)));n.get(e.month,e.year)}}).catch(function(t){}))},t.prototype.waitForFetch=function(t,e){var n=this;return new Promise(function(r){n.subscribe(t,e,function(i){n.unsubscribe(t,e),r(i)})})},t.prototype.store=function(t){var e=t.reduce(function(t,e){var n=i(e.month,e.year);return babelHelpers.extends(t,babelHelpers.defineProperty({},n,e.days))},{});babelHelpers.extends(this.months,e)},t}();r.default=l},1556);