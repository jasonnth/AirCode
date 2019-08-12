__d(function(e,r,o,t){Object.defineProperty(t,"__esModule",{value:!0});var i=r(271),a=babelHelpers.interopRequireDefault(i),l=r(44),n=r(834),s=r(1646),d=babelHelpers.interopRequireDefault(s),u=r(1023),p=r(420),b=babelHelpers.interopRequireDefault(p),h=r(815),f=babelHelpers.interopRequireDefault(h),c=r(755),g=babelHelpers.interopRequireDefault(c),m=r(1347),w=babelHelpers.interopRequireDefault(m),y=r(845),R=babelHelpers.interopRequireDefault(y),v=r(1548),H=babelHelpers.interopRequireDefault(v),C=r(559),k=r(1644),x=r(1657),q=r(1658),j=babelHelpers.interopRequireDefault(q),P={coordinates:n.Types.arrayOf(j.default).isRequired,children:n.Types.node,fullscreen:n.Types.bool,homeReservations:n.Types.array,onPress:n.Types.func},M={children:null,fullscreen:!1,homeReservations:[],onPress:function(){}},S=(0,x.getWindowDimension)().height,T=function(e,r){return e.latitude===r.latitude&&e.longitude===r.longitude},D=function(e,r){return e.listing.id===r.listing.id},O="android"===l.Platform.OS,E=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.shouldComponentUpdate=function(e,r){return!((0,C.customArrayEquals)(this.props.coordinates,e.coordinates,T)&&(0,C.customArrayEquals)(this.props.homeReservations,e.homeReservations,D)&&(0,H.default)(this.state,r)&&(0,w.default)((0,R.default)(this.props,["coordinates","homeReservations"]),(0,R.default)(e,["coordinates","homeReservations"])))},r.prototype.getInitialRegion=function(){var e=this.props.homeReservations.map(function(e){return{latitude:parseFloat(e.listing.lat),longitude:parseFloat(e.listing.lng)}});return(0,k.computeInitialRegion)(this.props.coordinates.concat(e))},r.prototype.singleCoordinator=function(){return 1===this.props.coordinates.length},r.prototype.renderHomeMarkers=function(){return this.props.homeReservations.map(function(e,r){var o={latitude:parseFloat(e.listing.lat),longitude:parseFloat(e.listing.lng)};return babelHelpers.jsx(d.default.Marker,{coordinate:o,label:"Home",type:"pin"},r,babelHelpers.jsx(l.View,{style:V.marker},void 0,babelHelpers.jsx(f.default,{backgroundColor:"transparent",size:19,name:"home"})))})},r.prototype.render=function(){var e=this.props,r=e.coordinates,o=e.children,t=e.fullscreen,i=e.onPress,a=this.singleCoordinator();return babelHelpers.jsx(l.View,{},void 0,!b.default.isMissingGooglePlayServices()&&babelHelpers.jsx(d.default,{style:[V.mapContainer,t&&V.fullscreenMapContainer],initialRegion:this.getInitialRegion(),scrollEnabled:t,zoomEnabled:t,rotateEnabled:t,pitchEnabled:t,moveOnMarkerPress:!1,onPress:i},void 0,r&&r.map(function(e,o){return babelHelpers.jsx(d.default.Marker,{coordinate:e,label:r.length>1?""+(o+1):null,type:r.length>1?"pin":"region",onPress:i},o,babelHelpers.jsx(l.View,{style:[V.marker,a&&V.markerLarge]},void 0,!a&&babelHelpers.jsx(l.Text,{style:V.markerText},void 0,o+1)))}),this.renderHomeMarkers()),!t&&o&&babelHelpers.jsx(l.View,{style:V.infoPanel},void 0,babelHelpers.jsx(l.View,{style:[V.infoBubble,O&&V.infoBubbleAndroid]},void 0,o),a&&babelHelpers.jsx(l.View,{style:V.arrowContainer},void 0,babelHelpers.jsx(l.View,{style:[V.arrow,O&&V.arrowShadowAndroid]}))))},r}(a.default.PureComponent);t.default=E,E.propTypes=P,E.defaultProps=M;var V=u.ThemedStyleSheet.create(function(e){var r=e.bp,o=e.color,t=e.font,i=e.size,a=4*r,n=8*r,s=20/Math.sqrt(2),d=b.default.isTablet?i.baseRow.paddingHorizontal-1.5*r:i.horizontal.medium,u=g.default.supportsElevation(),p=u?1:void 0,h=u?o.white:o.mapRefreshShadow;return{arrowContainer:{alignSelf:"center",height:0,width:s},arrow:{position:"absolute",bottom:0,borderWidth:l.StyleSheet.hairlineWidth,borderColor:o.white,width:s,height:s,transform:[{rotate:"45deg"},{translateY:s/Math.sqrt(8)},{translateX:s/Math.sqrt(8)}],backgroundColor:"white",shadowOffset:{width:1,height:1},shadowRadius:.5,shadowColor:o.dark.hof,shadowOpacity:.4},arrowShadowAndroid:{elevation:p,borderBottomColor:h,borderRightColor:h},infoBubble:{padding:1.5*r,backgroundColor:o.white,borderRadius:5,borderWidth:l.StyleSheet.hairlineWidth,borderColor:o.white,shadowRadius:.5,shadowColor:o.dark.hof,shadowOpacity:.4,shadowOffset:{height:1}},infoBubbleAndroid:{elevation:p,borderColor:h},infoPanel:{position:"absolute",left:d,right:d,marginTop:1.5*r},mapContainer:{height:32*r},fullscreenMapContainer:{height:S},marker:{alignItems:"center",backgroundColor:o.white,borderColor:o.core.babu,borderRadius:a/2,borderWidth:r/3,flex:1,flexDirection:"row",height:a,justifyContent:"center",width:a},markerLarge:{backgroundColor:o.opacity(o.core.babu,.3),borderRadius:n/2,height:n,width:n},markerText:babelHelpers.extends({},t.regularPlus,{backgroundColor:"transparent",color:o.core.babu})}})},1645);