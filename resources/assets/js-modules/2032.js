__d(function(e,n,r,l){function a(e,n,r){return babelHelpers.extends({},e,babelHelpers.defineProperty({},n,babelHelpers.extends({},e[n]||o,r)))}function t(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:i,n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{},r=n.type,l=n.payload,t=function(n){return a(e,l.viewId,n)};switch(r){case s.ADD_VIEW:return t({mapRegion:l.mapRegion,url:l.url,loadingStatus:l.status});case s.FETCH:return(0,u.handle)(e,n,{start:function(){return t({loadingStatus:d.LOADING,nextUrl:l.nextUrl,url:l.url||null})},failure:function(){return t({loadingStatus:null!=l.url?d.LOADED:d.ERROR,url:l.url||null})},success:function(){return t({loadingStatus:d.LOADED,mapNeedsRefresh:!1,url:l.url||null,mapRegion:l.mapRegion||null})}});case s.GUIDEBOOK_REOPENED:return t({loadingStatus:d.LOADED,mapNeedsRefresh:!1,url:l.url||null});case s.MAP_REGION_CHANGED:return t({mapRegion:l.mapRegion,mapNeedsRefresh:!!e[l.viewId].mapRegion});case s.TOGGLE_ROW_REVIEWS:var o=e[l.viewId],p=l.placeId;return t({expandedReviews:babelHelpers.extends({},o.expandedReviews,babelHelpers.defineProperty({},p,!o.expandedReviews[p]))});default:return e}}Object.defineProperty(l,"__esModule",{value:!0}),l.default=t;var u=n(667),s=n(2029),d=n(2033),i={},o={expandedReviews:{}}},2032);