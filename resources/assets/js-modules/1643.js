__d(function(e,t,r,l){function i(e){var t=e.map(function(e){return e.neighborhood_name}).filter(function(e){return!!e});return t.filter(function(e,r){return t.indexOf(e)===r})}function a(e,t,r){var l=babelHelpers.slicedToArray(e,3),i=l[0],a=l[1],n=l[2];if(1===r.length){var o=r[0],s=o.description_native&&o.description_native.map_heading,u=i?h.default.phrase("%{neighborhood}, %{city}",{neighborhood:i,city:t},"Describes the location where an experience will take place."):t;return s?s+" \xb7 "+u:u}switch(e.length){case 1:return h.default.phrase("%{neighborhood} in %{city}",{neighborhood:i,city:t},"Describes the location where an experience will take place.");case 2:return h.default.phrase("%{firstNeighborhood} and %{secondNeighborhood} in %{city}",{firstNeighborhood:i,secondNeighborhood:a,city:t},"Describes the location where an experience will take place.");case 3:return h.default.phrase("%{firstNeighborhood}, %{secondNeighborhood}, and %{thirdNeighborhood} in %{city}",{firstNeighborhood:i,secondNeighborhood:a,thirdNeighborhood:n,city:t},"Describes the location where an experience will take place.");default:return null}}Object.defineProperty(l,"__esModule",{value:!0}),l.locationDescriptionPhrase=a;var n=t(412),o=babelHelpers.interopRequireDefault(n),s=t(271),u=babelHelpers.interopRequireDefault(s),p=t(44),d=t(379),h=babelHelpers.interopRequireDefault(d),c=t(1023),b=t(1020),f=babelHelpers.interopRequireDefault(b),g=t(890),v=babelHelpers.interopRequireDefault(g),H=t(410),y=babelHelpers.interopRequireDefault(H),R=t(420),m=babelHelpers.interopRequireDefault(R),w=t(1644),M=t(1645),x=babelHelpers.interopRequireDefault(M),D=t(1609),q=t(1608),F=babelHelpers.interopRequireWildcard(q),C=t(1634),P=babelHelpers.interopRequireDefault(C),T=t(376),N=babelHelpers.interopRequireDefault(T),j={experiences:o.default.array.isRequired,futureReservations:o.default.array,tripTemplate:P.default.isRequired},S={futureReservations:[]},_=function(e){function t(r){babelHelpers.classCallCheck(this,t);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return l.state={showFullscreenMap:!1},l.showFullscreenMap=l.showFullscreenMap.bind(l),l}return babelHelpers.inherits(t,e),t.prototype.showFullscreenMap=function(){(0,D.logPDPClickInfoSection)(F.MAP,this.props.tripTemplate),this.state.showFullscreenMap||this.setState({showFullscreenMap:!0})},t.prototype.relevantHomeReservations=function(e){var t=(0,w.computeInitialRegion)(e),r=t.latitude,l=t.longitude,i=t.latitudeDelta,a=t.longitudeDelta;return this.props.futureReservations.filter(function(e){return e.listing&&e.listing.lat&&e.listing.lng&&Math.abs(e.listing.lat-r)<.1+i&&Math.abs(e.listing.lng-l)<.1+a})},t.prototype.renderMapCard=function(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0],t=this.props,r=t.experiences,l=t.tripTemplate,n=a(i(r),l.market.name,r),o=r.map(function(e){return{latitude:parseFloat(e.lat),longitude:parseFloat(e.lng)}}),s=this.relevantHomeReservations(o);if(m.default.isMissingGooglePlayServices())return null;var u=h.default.phrase("Meeting locations",null,"header text for map of meeting locations"),d=h.default.phrase("Where we\u2019ll meet",null,"header text for map of meeting locations");return e||1!==o.length||1!==r.length?babelHelpers.jsx(x.default,{coordinates:o,homeReservations:s,onPress:this.showFullscreenMap,fullscreen:e},void 0,babelHelpers.jsx(p.Text,{style:k.title},void 0,1===r.length?d:u),!!n&&babelHelpers.jsx(p.Text,{numberOfLines:1,style:k.subtitle},void 0,n)):babelHelpers.jsx(f.default,{latitude:o[0].latitude,longitude:o[0].longitude,title:d,subtitle:n,onPress:this.showFullscreenMap,locale:N.default.locale(),countryCode:N.default.localeCountry()})},t.prototype.renderModal=function(){var e=this;return babelHelpers.jsx(v.default,{visible:this.state.showFullscreenMap,onVisibleChange:function(t){return e.setState({showFullscreenMap:t})}},void 0,babelHelpers.jsx(y.default.Screen,{barType:y.default.Screen.BAR_TYPE.OVERLAY,hideStatusBarUntilFoldOffset:!0}),babelHelpers.jsx(p.View,{},void 0,this.renderMapCard(!0)))},t.prototype.render=function(){return babelHelpers.jsx(p.View,{},void 0,this.renderMapCard(),this.renderModal())},t}(u.default.Component);l.default=_,_.propTypes=j,_.defaultProps=S;var k=c.ThemedStyleSheet.create(function(e){var t=e.font,r=e.bp;return{title:t.regularPlus,subtitle:babelHelpers.extends({},t.small,{paddingTop:.25*r})}})},1643);