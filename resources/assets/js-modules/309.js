__d(function(e,i,n,t){"use strict";var o=i(240),s=i(291),a=i(275),r=i(60),p=i(82),u=i(237),d=i(113),h=r.PropTypes,l=i(23),c=a.isRTL,_=r.createClass({displayName:"SwipeableRow",_panResponder:{},_previousLeft:0,mixins:[u],propTypes:{children:h.any,isOpen:h.bool,maxSwipeDistance:h.number.isRequired,onOpen:h.func.isRequired,onSwipeEnd:h.func.isRequired,onSwipeStart:h.func.isRequired,shouldBounceOnMount:h.bool,slideoutView:h.node.isRequired,swipeThreshold:h.number.isRequired},getInitialState:function(){return{currentLeft:new o.Value(this._previousLeft),isSwipeableViewRendered:!1,rowHeight:null}},getDefaultProps:function(){return{isOpen:!1,maxSwipeDistance:0,onOpen:l,onSwipeEnd:l,onSwipeStart:l,swipeThreshold:30}},componentWillMount:function(){this._panResponder=s.create({onMoveShouldSetPanResponderCapture:this._handleMoveShouldSetPanResponderCapture,onPanResponderGrant:this._handlePanResponderGrant,onPanResponderMove:this._handlePanResponderMove,onPanResponderRelease:this._handlePanResponderEnd,onPanResponderTerminationRequest:this._onPanResponderTerminationRequest,onPanResponderTerminate:this._handlePanResponderEnd})},componentDidMount:function(){var e=this;this.props.shouldBounceOnMount&&this.setTimeout(function(){e._animateBounceBack(400)},700)},componentWillReceiveProps:function(e){this.props.isOpen&&!e.isOpen&&this._animateToClosedPosition()},shouldComponentUpdate:function(e,i){return!(this.props.shouldBounceOnMount&&!e.shouldBounceOnMount)},render:function(){var e=void 0;this.state.isSwipeableViewRendered&&(e=babelHelpers.jsx(d,{style:[m.slideOutContainer,{height:this.state.rowHeight}]},void 0,this.props.slideoutView));var i=babelHelpers.jsx(o.View,{onLayout:this._onSwipeableViewLayout,style:[m.swipeableContainer,{transform:[{translateX:this.state.currentLeft}]}]},void 0,this.props.children);return r.createElement(d,this._panResponder.panHandlers,e,i)},_onSwipeableViewLayout:function(e){this.setState({isSwipeableViewRendered:!0,rowHeight:e.nativeEvent.layout.height})},_handleMoveShouldSetPanResponderCapture:function(e,i){return i.dy<10&&this._isValidSwipe(i)},_handlePanResponderGrant:function(e,i){},_handlePanResponderMove:function(e,i){this._isSwipingExcessivelyRightFromClosedPosition(i)||(this.props.onSwipeStart(),this._isSwipingRightFromClosed(i)?this._swipeSlowSpeed(i):this._swipeFullSpeed(i))},_isSwipingRightFromClosed:function(e){var i=c?-e.dx:e.dx;return 0===this._previousLeft&&i>0},_swipeFullSpeed:function(e){this.state.currentLeft.setValue(this._previousLeft+e.dx)},_swipeSlowSpeed:function(e){this.state.currentLeft.setValue(this._previousLeft+e.dx/4)},_isSwipingExcessivelyRightFromClosedPosition:function(e){var i=c?-e.dx:e.dx;return this._isSwipingRightFromClosed(e)&&i>120},_onPanResponderTerminationRequest:function(e,i){return!1},_animateTo:function(e){var i=this,n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:300,t=arguments.length>2&&void 0!==arguments[2]?arguments[2]:l;o.timing(this.state.currentLeft,{duration:n,toValue:e}).start(function(){i._previousLeft=e,t()})},_animateToOpenPosition:function(){var e=c?-this.props.maxSwipeDistance:this.props.maxSwipeDistance;this._animateTo(-e)},_animateToOpenPositionWith:function(e,i){e=e>.3?e:.3;var n=Math.abs((this.props.maxSwipeDistance-Math.abs(i))/e),t=c?-this.props.maxSwipeDistance:this.props.maxSwipeDistance;this._animateTo(-t,n)},_animateToClosedPosition:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:300;this._animateTo(0,e)},_animateToClosedPositionDuringBounce:function(){this._animateToClosedPosition(300)},_animateBounceBack:function(e){var i=c?-30:30;this._animateTo(-i,e,this._animateToClosedPositionDuringBounce)},_isValidSwipe:function(e){return Math.abs(e.dx)>10},_shouldAnimateRemainder:function(e){return Math.abs(e.dx)>this.props.swipeThreshold||e.vx>.3},_handlePanResponderEnd:function(e,i){var n=c?-i.dx:i.dx;this._isSwipingRightFromClosed(i)?(this.props.onOpen(),this._animateBounceBack(300)):this._shouldAnimateRemainder(i)?n<0?(this.props.onOpen(),this._animateToOpenPositionWith(i.vx,n)):this._animateToClosedPosition():0===this._previousLeft?this._animateToClosedPosition():this._animateToOpenPosition(),this.props.onSwipeEnd()}}),m=p.create({slideOutContainer:{bottom:0,left:0,position:"absolute",right:0,top:0},swipeableContainer:{flex:1}});n.exports=_},309);