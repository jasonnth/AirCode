__d(function(e,a,r,s){function l(e){var a=e.state,r=e.placeId,s=e.bookmarkId;return babelHelpers.extends({},a,{guidebookPlaces:babelHelpers.extends({},a.guidebookPlaces,babelHelpers.defineProperty({},r,babelHelpers.extends({},a.guidebookPlaces[r],{place_bookmark_id_for_current_user:s})))})}function t(e){var a=e.state,r=e.placeId,s=e.status;return babelHelpers.extends({},a,{guidebookPlacesMeta:babelHelpers.extends({},a.guidebookPlacesMeta,babelHelpers.defineProperty({},r,babelHelpers.extends({},a.guidebookPlacesMeta[r],{scheduledPlaceStatus:s})))})}function n(e,a,r){return babelHelpers.extends({},e,{guidebookPlaceMeetups:babelHelpers.extends({},e.guidebookPlaceMeetups,babelHelpers.defineProperty({},a,babelHelpers.extends({},e.guidebookPlaceMeetups[a],r)))})}function c(e){var a=parseFloat(e.primary_place.lat),r=parseFloat(e.primary_place.lng);return babelHelpers.extends({},e,{coordinate:{latitude:a,longitude:r},location:{lat:a,lng:r}})}function u(e){var a={};return e.forEach(function(e){a[e.primary_place.id]=e}),a}function d(e){return e.map(function(e){return e.primary_place.id})}function o(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:H,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},r=a.payload,s=a.meta;switch(a.type){case p.CLOSE_ERROR_MESSAGE_CLICKED:return babelHelpers.extends({},e,{errorMessage:null});case p.FETCH:return(0,b.handle)(e,a,{start:function(){var r=a.payload,s=r.guidebook,l=r.url;return l&&s?babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},l,babelHelpers.extends({},e.cache[l],{guidebook:s})))}):e},success:function(){var a=r.guidebook,s=r.guidebookPlaces,l=r.url;return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},l,{guidebook:a,guidebookPlaceIds:d(s),isFullyLoaded:!0})),guidebookPlaces:babelHelpers.extends({},e.guidebookPlaces,u(s.map(c)))})}});case p.CREATE_PLACE_BOOKMARK:var o=r.placeId,i=r.bookmarkId;return(0,b.handle)(e,a,{start:function(){return l({state:e,placeId:o,bookmarkId:-1})},failure:function(){return l({state:e,placeId:o,bookmarkId:null})},success:function(){return l({state:e,placeId:o,bookmarkId:i})}});case p.REMOVE_PLACE_BOOKMARK:return(0,b.handle)(e,a,{start:function(){var a=r.placeId;return l({state:e,placeId:a,bookmarkId:null})},failure:function(){var a=s.startPayload,r=a.placeId,t=a.bookmarkId;return l({state:e,placeId:r,bookmarkId:t})}});case p.CREATE_SCHEDULED_PLACE:var _=r.placeId;return(0,b.handle)(e,a,{start:function(){return t({state:e,placeId:_,status:E.CREATE_SCHEDULED_PLACE_STARTED})},failure:function(){return t({state:e,placeId:s.startPayload.placeId,status:E.CREATE_SCHEDULED_PLACE_FAILED})},success:function(){return t({state:e,placeId:_,status:E.CREATE_SCHEDULED_PLACE_SUCCEEDED})}});case p.MAP_MARKER_SELECTED:var I=r.placeId;return babelHelpers.extends({},e,{selectedMapMarkerPlaceId:I});case p.MAP_MARKER_DESELECTED:var f=r.placeId;return e.selectedMapMarkerPlaceId!==f?e:babelHelpers.extends({},e,{selectedMapMarkerPlaceId:null});case p.FETCH_PLACE:return(0,b.handle)(e,a,{success:function(){var a=r.response,s=r.cacheKey,l=r.placeId,t=a.guidebook_place;return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},s,babelHelpers.extends({},e.cache[s],{isFullyLoaded:!0,places:babelHelpers.defineProperty({},l,c(t))})))})}});case p.FETCH_MEETUP:return(0,b.handle)(e,a,{success:function(){var a=r.response,s=r.placeId;return babelHelpers.extends({},e,{guidebookPlaceMeetups:babelHelpers.extends({},e.guidebookPlaceMeetups,babelHelpers.defineProperty({},s,a))})}});case p.FETCH_MEETUP_COLLECTION:return(0,b.handle)(e,a,{success:function(){var a=r.response,s=r.city;return babelHelpers.extends({},e,{guidebookPlaceMeetupsByCity:babelHelpers.defineProperty({},s,a)})}});case p.JOIN_PLACE_MEETUP:var k=r.placeId,C=r.placeReservationId;return(0,b.handle)(e,a,{start:function(){return n(e,r.placeId,{status:P.JOIN_PLACE_MEETUP_STARTED})},failure:function(){return n(e,r.placeId,{status:P.JOIN_PLACE_MEETUP_FAILED})},success:function(){return n(e,r.placeId,{joined:!0,status:P.JOIN_PLACE_MEETUP_SUCCEEDED,guest_count:e.guidebookPlaceMeetups[k].guest_count+1,place_reservation_id:C})}});case p.UNJOIN_PLACE_MEETUP:return(0,b.handle)(e,a,{start:function(){return n(e,r.placeId,{status:P.UNJOIN_PLACE_MEETUP_STARTED})},failure:function(){return n(e,r.placeId,{status:P.UNJOIN_PLACE_MEETUP_FAILED})},success:function(){return n(e,r.placeId,{joined:!1,status:P.UNJOIN_PLACE_MEETUP_SUCCEEDED,guest_count:e.guidebookPlaceMeetups[r.placeId].guest_count-1,place_reservation_id:null})}});case p.FETCH_GUIDEBOOK_INSIDER:return(0,b.handle)(e,a,{success:function(){var a=r.guidebook,s=r.places,l=r.cacheKey;return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},l,babelHelpers.extends({},e.cache[l],{isFullyLoaded:!0,guidebook:a,places:s.map(c)})))})}});case p.FETCH_RECOMMENDATIONS:return(0,b.handle)(e,a,{success:function(){var a=r.response,s=r.cacheKey,l=r.placeId,t=Object.values(a.place_recommendations);return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},s,{isFullyLoaded:!0})),placeRecommendations:babelHelpers.extends({},e.placeRecommendations,babelHelpers.defineProperty({},l,[].concat(babelHelpers.toConsumableArray(e.placeRecommendations[l]||[]),babelHelpers.toConsumableArray(t))))})}});case p.FETCH_DETOURDATA:return(0,b.handle)(e,a,{success:function(){var a=r.detour,s=r.cacheKey,l=r.detourId;return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},s,{isFullyLoaded:!0})),detours:babelHelpers.extends({},e.detours,babelHelpers.defineProperty({},l,a))})}});case p.FETCH_GUIDEBOOK_NEARBY:case p.FETCH_LISTING_GUIDEBOOK:return(0,b.handle)(e,a,{success:function(){var a=r.response,s=r.cacheKey;return babelHelpers.extends({},e,{cache:babelHelpers.extends({},e.cache,babelHelpers.defineProperty({},s,{isFullyLoaded:!0,places:a.map(c)}))})}});case p.FETCH_RESERVATIONS:return(0,b.handle)(e,a,{success:function(){var a=r.reservations;return babelHelpers.extends({},e,{reservations:a})}});default:return e}}Object.defineProperty(s,"__esModule",{value:!0}),s.default=o;var b=a(667),p=a(2029),i=a(2030),E=babelHelpers.interopRequireWildcard(i),_=a(2031),P=babelHelpers.interopRequireWildcard(_),H={cache:{},errorMessage:null,guidebookPlaces:{},guidebookPlacesMeta:{},guidebookPlaceMeetups:{},guidebookPlaceMeetupsByCity:{},selectedMapMarkerPlaceId:null,guidebookInsider:{},placeRecommendations:{},detours:{},guidebookNearbyPlaces:{},listingGuidebook:{}}},2028);