__d(function(e,s,t,n){"use strict";var o=s(114),i=s(49),r=(s(55),s(60)),p=s(119),l=s(120),a=s(105),h=s(176),d=s(124),u=s(181),c=l(a),b={validAttributes:u(p.UIView,{isHighlighted:!0,numberOfLines:!0,ellipsizeMode:!0,allowFontScaling:!0,selectable:!0,adjustsFontSizeToFit:!0,minimumFontScale:!0,textBreakStrategy:!0}),uiViewClassName:"RCTText"},P=r.createClass({displayName:"Text",propTypes:{ellipsizeMode:r.PropTypes.oneOf(["head","middle","tail","clip"]),numberOfLines:r.PropTypes.number,textBreakStrategy:r.PropTypes.oneOf(["simple","highQuality","balanced"]),onLayout:r.PropTypes.func,onPress:r.PropTypes.func,onLongPress:r.PropTypes.func,pressRetentionOffset:o,selectable:r.PropTypes.bool,suppressHighlighting:r.PropTypes.bool,style:c,testID:r.PropTypes.string,allowFontScaling:r.PropTypes.bool,accessible:r.PropTypes.bool,adjustsFontSizeToFit:r.PropTypes.bool,minimumFontScale:r.PropTypes.number},getDefaultProps:function(){return{accessible:!0,allowFontScaling:!0,ellipsizeMode:"tail"}},getInitialState:function(){return u(h.Mixin.touchableGetInitialState(),{isHighlighted:!1})},mixins:[i],viewConfig:b,getChildContext:function(){return{isInAParentText:!0}},childContextTypes:{isInAParentText:r.PropTypes.bool},contextTypes:{isInAParentText:r.PropTypes.bool},_handlers:null,_hasPressHandler:function(){return!!this.props.onPress||!!this.props.onLongPress},touchableHandleActivePressIn:null,touchableHandleActivePressOut:null,touchableHandlePress:null,touchableHandleLongPress:null,touchableGetPressRectOffset:null,render:function(){var e=this,s=this.props;return(this.props.onStartShouldSetResponder||this._hasPressHandler())&&(this._handlers||(this._handlers={onStartShouldSetResponder:function(){var s=e.props.onStartShouldSetResponder&&e.props.onStartShouldSetResponder(),t=s||e._hasPressHandler();if(t&&!e.touchableHandleActivePressIn){for(var n in h.Mixin)"function"==typeof h.Mixin[n]&&(e[n]=h.Mixin[n].bind(e));e.touchableHandleActivePressIn=function(){!e.props.suppressHighlighting&&e._hasPressHandler()&&e.setState({isHighlighted:!0})},e.touchableHandleActivePressOut=function(){!e.props.suppressHighlighting&&e._hasPressHandler()&&e.setState({isHighlighted:!1})},e.touchableHandlePress=function(s){e.props.onPress&&e.props.onPress(s)},e.touchableHandleLongPress=function(s){e.props.onLongPress&&e.props.onLongPress(s)},e.touchableGetPressRectOffset=function(){return this.props.pressRetentionOffset||g}}return t},onResponderGrant:function(e,s){this.touchableHandleResponderGrant(e,s),this.props.onResponderGrant&&this.props.onResponderGrant.apply(this,arguments)}.bind(this),onResponderMove:function(e){this.touchableHandleResponderMove(e),this.props.onResponderMove&&this.props.onResponderMove.apply(this,arguments)}.bind(this),onResponderRelease:function(e){this.touchableHandleResponderRelease(e),this.props.onResponderRelease&&this.props.onResponderRelease.apply(this,arguments)}.bind(this),onResponderTerminate:function(e){this.touchableHandleResponderTerminate(e),this.props.onResponderTerminate&&this.props.onResponderTerminate.apply(this,arguments)}.bind(this),onResponderTerminationRequest:function(){var e=this.touchableHandleResponderTerminationRequest();return e&&this.props.onResponderTerminationRequest&&(e=this.props.onResponderTerminationRequest.apply(this,arguments)),e}.bind(this)}),s=babelHelpers.extends({},this.props,this._handlers,{isHighlighted:this.state.isHighlighted})),h.TOUCH_TARGET_DEBUG&&s.onPress&&(s=babelHelpers.extends({},s,{style:[this.props.style,{color:"magenta"}]})),this.context.isInAParentText?r.createElement(f,s):r.createElement(T,s)}}),g={top:20,left:20,right:20,bottom:30},T=d(b),f=T;f=d({validAttributes:u(p.UIView,{isHighlighted:!0}),uiViewClassName:"RCTVirtualText"}),t.exports=P},175);