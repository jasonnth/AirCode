__d(function(e,r,t,u){function a(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:c,r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},t=r.type,u=r.payload;switch(t){case d.LOAD_TRIP_INSTANCE:return(0,o.handle)(e,r,{success:function(){var r=u.rawTripInstance.booked_trips,t=(0,s.default)(r.map(function(e){return e.guest_profiles})),a=t.map(function(e){return(0,f.formatGuestProfile)(e,{detailsFetched:!0})});return babelHelpers.extends({},e,(0,i.default)(a,"guestProfileId"))}});case d.LOAD_REVIEWS:return(0,o.handle)(e,r,{success:function(){var r=u.rawReviews.map(function(e){return e.author_guest_profile}),t=r.map(function(e){return(0,f.formatGuestProfile)(e,{detailsFetched:!1})});return babelHelpers.extends({},e,(0,i.default)(t,"guestProfileId"))}});case d.LOAD_STATS:return(0,o.handle)(e,r,{success:function(){var r=u.rawLastReview;if(r){var t=r.author_guest_profile,a=(0,f.formatGuestProfile)(t,{detailsFetched:!1});return babelHelpers.extends({},e,babelHelpers.defineProperty({},a.guestProfileId,a))}return e}});default:return e}}Object.defineProperty(u,"__esModule",{value:!0}),u.default=a;var n=r(765),s=babelHelpers.interopRequireDefault(n),l=r(678),i=babelHelpers.interopRequireDefault(l),o=r(667),f=r(1773),d=r(1774),c={}},1782);