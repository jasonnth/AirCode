__d(function(t,e,a,n){function r(t,e){var a=!(arguments.length>2&&void 0!==arguments[2])||arguments[2],n=a?"+":"",r=b.default.priceString(t,e);return t>=0?""+n+r:r}function u(t,e){return[t,e].map(function(t){return t.format(g.default.format("ss"))}).join(" - ")}function i(t,e){return u((0,p.default)(t),(0,p.default)(e))}function o(t,e){return u((0,p.default)(t),(0,p.default)(t).add(e,"days"))}function s(t){return t.start_date?o(t.start_date,t.nights):i(t.check_in,t.check_out)}function l(t,e){return(0,p.default)(t).add(e,"days").format("YYYY-MM-DD")}function f(t,e){return{startDate:t.start_date,endDate:l(t.start_date,t.nights),numberOfGuests:t.number_of_guests,listingId:t.listing.id,price:e?t.subtotal_price_host_native:t.subtotal_price_guest_native}}function d(t){return null==t?{}:{startDate:t.check_in,endDate:t.check_out,numberOfGuests:t.guests}}function c(t,e,a){var n=l(e,a);return t.map(function(t){return t.date<e||t.date>=n?t:babelHelpers.extends({},t,{available:!0})})}Object.defineProperty(n,"__esModule",{value:!0}),n.formatAmount=r,n.formatDateRange=i,n.formatDateRangeWithNights=o,n.formatDateRangeForObj=s,n.calculateEndDate=l,n.stateFromReservation=f,n.stateFromReservationAlteration=d,n.markReservationDatesAvailable=c;var _=e(377),b=babelHelpers.interopRequireDefault(_),m=e(562),p=babelHelpers.interopRequireDefault(m),D=e(1372),g=babelHelpers.interopRequireDefault(D)},1543);