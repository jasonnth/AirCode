__d(function(e,s,t,o){"use strict";var n=s(114),r=s(60),i=s(237),p=s(176),a=s(113),l=s(238),c=s(22),h={top:20,left:20,right:20,bottom:30},u=r.createClass({displayName:"TouchableWithoutFeedback",mixins:[i,p.Mixin],propTypes:{accessible:r.PropTypes.bool,accessibilityComponentType:r.PropTypes.oneOf(a.AccessibilityComponentType),accessibilityTraits:r.PropTypes.oneOfType([r.PropTypes.oneOf(a.AccessibilityTraits),r.PropTypes.arrayOf(r.PropTypes.oneOf(a.AccessibilityTraits))]),disabled:r.PropTypes.bool,onPress:r.PropTypes.func,onPressIn:r.PropTypes.func,onPressOut:r.PropTypes.func,onLayout:r.PropTypes.func,onLongPress:r.PropTypes.func,delayPressIn:r.PropTypes.number,delayPressOut:r.PropTypes.number,delayLongPress:r.PropTypes.number,pressRetentionOffset:n,hitSlop:n},getInitialState:function(){return this.touchableGetInitialState()},componentDidMount:function(){l(this.props)},componentWillReceiveProps:function(e){l(e)},touchableHandlePress:function(e){this.props.onPress&&this.props.onPress(e)},touchableHandleActivePressIn:function(e){this.props.onPressIn&&this.props.onPressIn(e)},touchableHandleActivePressOut:function(e){this.props.onPressOut&&this.props.onPressOut(e)},touchableHandleLongPress:function(e){this.props.onLongPress&&this.props.onLongPress(e)},touchableGetPressRectOffset:function(){return this.props.pressRetentionOffset||h},touchableGetHitSlop:function(){return this.props.hitSlop},touchableGetHighlightDelayMS:function(){return this.props.delayPressIn||0},touchableGetLongPressDelayMS:function(){return 0===this.props.delayLongPress?0:this.props.delayLongPress||500},touchableGetPressOutDelayMS:function(){return this.props.delayPressOut||0},render:function(){var e=r.Children.only(this.props.children),s=e.props.children;c(!e.type||"Text"!==e.type.displayName,"TouchableWithoutFeedback does not work well with Text children. Wrap children in a View instead. See "+(e._owner&&e._owner.getName&&e._owner.getName()||"<unknown>")),p.TOUCH_TARGET_DEBUG&&e.type&&"View"===e.type.displayName&&(s=r.Children.toArray(s),s.push(p.renderDebugView({color:"red",hitSlop:this.props.hitSlop})));var t=p.TOUCH_TARGET_DEBUG&&e.type&&"Text"===e.type.displayName?[e.props.style,{color:"red"}]:e.props.style;return r.cloneElement(e,{accessible:!1!==this.props.accessible,accessibilityLabel:this.props.accessibilityLabel,accessibilityComponentType:this.props.accessibilityComponentType,accessibilityTraits:this.props.accessibilityTraits,testID:this.props.testID,onLayout:this.props.onLayout,hitSlop:this.props.hitSlop,onStartShouldSetResponder:this.touchableHandleStartShouldSetResponder,onResponderTerminationRequest:this.touchableHandleResponderTerminationRequest,onResponderGrant:this.touchableHandleResponderGrant,onResponderMove:this.touchableHandleResponderMove,onResponderRelease:this.touchableHandleResponderRelease,onResponderTerminate:this.touchableHandleResponderTerminate,style:t,children:s})}});t.exports=u},236);