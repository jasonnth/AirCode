__d(function(e,n,t,a){Object.defineProperty(a,"__esModule",{value:!0});var i=n(1257),o=babelHelpers.interopRequireDefault(i),r=n(44),s=n(1279),l=n(1071),p=n(1302),d=babelHelpers.interopRequireDefault(p),h=r.StyleSheet.create({responder:{flex:1,alignItems:"center"}}),u=function(e){function n(t){babelHelpers.classCallCheck(this,n);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return a.panResponder=a.getResponder(),a}return babelHelpers.inherits(n,e),n.prototype.getEvents=function(){return[]},n.prototype.getResponder=function(){var e=function(){return!0},n=function(){return!1};return r.PanResponder.create({onStartShouldSetPanResponder:e,onStartShouldSetPanResponderCapture:n,onMoveShouldSetPanResponder:e,onMoveShouldSetPanResponderCapture:e,onShouldBlockNativeResponder:e,onPanResponderTerminationRequest:e,onPanResponderGrant:this.handleResponderGrant.bind(this),onPanResponderMove:this.handleResponderMove.bind(this),onPanResponderRelease:this.handleResponderEnd.bind(this),onPanResponderTerminate:this.handleResponderEnd.bind(this)})},n.prototype.handleResponderGrant=function(){this.targetBounds={left:0,width:this.chartRef.props.width},this.lastDomain=this.previousDomain=this.state.domain,this.isPanning=!0},n.prototype.handleResponderMove=function(e,n){var t=e.nativeEvent.touches;t&&2===t.length?this.handlePinchZoom(t[0].locationX,t[1].locationX):this.isPanning&&this.handlePan(n.dx)},n.prototype.handleResponderEnd=function(){this.isPanning=!1,this.pinchState=null},n.prototype.handlePinchZoom=function(e,n){var t=Math.abs(e-n),a=this.pinchState||(this.pinchState={initialDomain:this.lastDomain,initialDistance:t}),i=r.Dimensions.get("window").width,o=t-a.initialDistance,s=1;if(o<0){var l=babelHelpers.slicedToArray(a.initialDomain.x,2),p=l[0],h=l[1],u=babelHelpers.slicedToArray(this.getDataDomain().x,2),c=u[0],b=u[1];p!==h&&(s=Math.abs(b-c)/Math.abs(h-p))}var R=1-o/i*s,f=d.default.scale(a.initialDomain.x,this.getDataDomain().x,R);this.setDomain({x:f})},n.prototype.handlePan=function(e){var n=-e/this.getDomainScale(),t=d.default.pan(this.lastDomain.x,this.getDataDomain().x,n);this.setDomain({x:t})},n.prototype.renderChart=function(e,n){return o.default.createElement(r.View,babelHelpers.extends({},this.panResponder.panHandlers,{style:h.responder}),o.default.cloneElement(e,n))},n}(s.VictoryZoom);u.defaultProps=babelHelpers.extends({},s.VictoryZoom.defaultProps,{clipContainerComponent:babelHelpers.jsx(l.VictoryClipContainer,{})}),a.default=u},1318);