__d(function(e,t,r,o){function a(e){return(0,R.getMoji)(e.airmoji||"extras_star")}Object.defineProperty(o,"__esModule",{value:!0});var l=t(412),n=babelHelpers.interopRequireDefault(l),i=t(271),s=(babelHelpers.interopRequireDefault(i),t(44)),u=t(379),p=babelHelpers.interopRequireDefault(u),d=t(1023),c=t(654),b=t(841),h=babelHelpers.interopRequireDefault(b),f=t(787),y=babelHelpers.interopRequireDefault(f),m=t(773),g=babelHelpers.interopRequireDefault(m),P=t(790),v=t(410),H=babelHelpers.interopRequireDefault(v),S=t(778),w=babelHelpers.interopRequireDefault(S),q=t(2086),C=babelHelpers.interopRequireDefault(q),R=t(906),k=t(2054),x=babelHelpers.interopRequireDefault(k),N=t(2115),E=babelHelpers.interopRequireDefault(N),_=t(2058),j=t(2035),D=t(2046),L=t(1920),W=babelHelpers.interopRequireWildcard(L),M=t(2093),G={pressedCloseGuidebookScreen:n.default.func.isRequired,fetchGuidebookNearbyNow:n.default.func.isRequired,queryParams:n.default.shape({gpsLat:n.default.number,gpsLng:n.default.number}).isRequired,guidebookPlaces:n.default.array,searchContextString:n.default.string},T=function(e){function t(r,o){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,r,o));return a.handleLayoutWindow=a.handleLayoutWindow.bind(a),a.handleMomentumScrollEnd=a.handleMomentumScrollEnd.bind(a),a.state={windowWidth:null},a.prev=0,a}return babelHelpers.inherits(t,e),t.prototype.componentWillMount=function(){var e=this.props.queryParams,t=e.gpsLat,r=e.gpsLng;this.props.fetchGuidebookNearbyNow(t,r)},t.prototype.componentDidMount=function(){var e=this.props.queryParams,t=e.gpsLat,r=e.gpsLng;(0,M.logNearbyNowPDPImpressionEvent)(t,r,this.props.searchContextString)},t.prototype.handleLayoutWindow=function(e){var t=e.width;this.setState({windowWidth:t})},t.prototype.componentWillReceiveProps=function(e){var t=e.guidebookPlaces;t&&(this.props.guidebookPlaces||t.forEach(function(e){e.photos.forEach(function(e){s.Image.prefetch(e.picture)})}))},t.prototype.handleMomentumScrollEnd=function(e){var t=e.nativeEvent.contentOffset.y,r=t<this.prevY;this.prevY=t,(0,M.logGuidebookScrollNearbyCollectionEvent)(r)},t.prototype.render=function(){var e=this,t=this.props.guidebookPlaces,r=p.default.phrase("Nearby now",{},"The title for Nearby Now collection of places"),o=void 0;return t&&t.length>0?o=p.default.phrase("Places recommended by hosts that are currently open",{},"Subtitle for nearby places list recommended by hosts that are currenly open"):t&&0===t.length&&(o=p.default.phrase("There are no recommended businesses that are currently open near you",null,"subtitle for nearby places list when there is no open places.")),babelHelpers.jsx(x.default,{onLayoutWindow:this.handleLayoutWindow},void 0,babelHelpers.jsx(H.default.Screen,{barType:H.default.Screen.BAR_TYPE.SPECIALTY}),babelHelpers.jsx(y.default,{onMomentumScrollEnd:this.handleMomentumScrollEnd},void 0,babelHelpers.jsx(g.default,{title:r,subtitle:o}),babelHelpers.jsx(s.View,{},void 0,!t&&babelHelpers.jsx(s.View,{style:A.loaderContainer},void 0,babelHelpers.jsx(h.default,{})),t&&t.length>0&&babelHelpers.jsx(w.default,{},void 0,t.map(function(t){return babelHelpers.jsx(C.default,{title:t.primary_place_name+"\xa0"+a(t),image:t.photos.length>0&&t.photos[0].x_medium,imageShape:"square",subtitle:(0,_.getPlaceSubtitle)(t),description:"",onPress:function(){(0,M.logGuidebookClickNearbyPlaceEvent)(t.primary_place.id),H.default.push(W.PLACE,{placeId:t.primary_place.id,title:t.primary_place_name,placePhotos:t.photos.slice(0,4).map(function(e){return e.picture}),placePdpType:M.PlacePdpTypeEnums.FROM_NEARBY,searchContextString:e.props.searchContextString})}},t.primary_place.id)})))),t&&t.length>0&&babelHelpers.jsx(E.default,{windowWidth:this.state.windowWidth,onPress:function(){return H.default.push(W.MAP,{guidebookPlaces:t,title:r,searchContextString:e.props.searchContextString})}}))},t}(i.PureComponent);T.propTypes=G;var A=d.ThemedStyleSheet.create(function(e){return{loaderContainer:{paddingVertical:e.size.vertical.medium}}});o.default=(0,P.connect)(function(e,t){return{guidebookPlaces:(0,D.guidebookNearbyPlacesSelector)(e,t.queryParams)}},function(e){return(0,c.bindActionCreators)({fetchGuidebookNearbyNow:j.fetchGuidebookNearbyNow,pressedCloseGuidebookScreen:j.pressedCloseGuidebookScreen},e)})(T)},2118);