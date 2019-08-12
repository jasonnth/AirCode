__d(function(t,e,i,n){"use strict";function s(t,e){return new h(t,e).panHandlers}function o(t){return s(l.HORIZONTAL,t)}function a(t){return s(l.VERTICAL,t)}var r=e(240),p=e(275),_=e(341),u=e(293),d=function(){},l={HORIZONTAL:"horizontal",VERTICAL:"vertical"},h=function(t){function e(i,n){babelHelpers.classCallCheck(this,e);var s=babelHelpers.possibleConstructorReturn(this,t.call(this));return s._isResponding=!1,s._isVertical=i===l.VERTICAL,s._props=n,s._startValue=0,s._addNativeListener(s._props.layout.width),s._addNativeListener(s._props.layout.height),s._addNativeListener(s._props.position),s}return babelHelpers.inherits(e,t),e.prototype.onMoveShouldSetPanResponder=function(t,e){var i=this._props;if(i.navigationState.index!==i.scene.index)return!1;var n=i.layout,s=this._isVertical,o=i.navigationState.index,a=e[s?"dy":"dx"],r=e[s?"moveY":"moveX"],p=s?n.height.__getValue():n.width.__getValue(),_=s?i.gestureResponseDistance:i.gestureResponseDistance||30;return!(null!=_&&r>_)&&(Math.abs(a)>15&&p>0&&o>0)},e.prototype.onPanResponderGrant=function(){var t=this;this._isResponding=!1,this._props.position.stopAnimation(function(e){t._isResponding=!0,t._startValue=e})},e.prototype.onPanResponderMove=function(t,e){if(this._isResponding){var i=this._props,n=i.layout,s=this._isVertical,o=s?"dy":"dx",a=i.navigationState.index,r=s?n.height.__getValue():n.width.__getValue(),_=p.isRTL&&"dx"===o?this._startValue+e[o]/r:this._startValue-e[o]/r,d=u(a-1,_,a);i.position.setValue(d)}},e.prototype.onPanResponderRelease=function(t,e){var i=this;if(this._isResponding){this._isResponding=!1;var n=this._props,s=this._isVertical,o=s?"dy":"dx",a=n.navigationState.index,r=p.isRTL&&"dx"===o?-e[o]:e[o];n.position.stopAnimation(function(t){i._reset(),n.onNavigateBack&&(r>100||t<=a-.3333333333333333)&&n.onNavigateBack()})}},e.prototype.onPanResponderTerminate=function(){this._isResponding=!1,this._reset()},e.prototype._reset=function(){var t=this._props;r.timing(t.position,{toValue:t.navigationState.index,duration:250,useNativeDriver:t.position.__isNative}).start()},e.prototype._addNativeListener=function(t){t.__isNative&&0===Object.keys(t._listeners).length&&t.addListener(d)},e}(_);i.exports={ANIMATION_DURATION:250,DISTANCE_THRESHOLD:100,POSITION_THRESHOLD:.3333333333333333,RESPOND_THRESHOLD:15,Directions:l,forHorizontal:o,forVertical:a}},340);