__d(function(e,t,n,o){"use strict";var s=t(46),r=t(49),i=(t(55),t(60)),p=t(82),a=t(113),h=t(122),l=i.createClass({displayName:"Switch",propTypes:babelHelpers.extends({},a.propTypes,{value:i.PropTypes.bool,disabled:i.PropTypes.bool,onValueChange:i.PropTypes.func,testID:i.PropTypes.string,tintColor:s,onTintColor:s,thumbTintColor:s}),getDefaultProps:function(){return{value:!1,disabled:!1}},mixins:[r],_rctSwitch:{},_onChange:function(e){this._rctSwitch.setNativeProps({on:this.props.value}),this.props.onChange&&this.props.onChange(e),this.props.onValueChange&&this.props.onValueChange(e.nativeEvent.value)},render:function(){var e=this,t=babelHelpers.extends({},this.props);return t.onStartShouldSetResponder=function(){return!0},t.onResponderTerminationRequest=function(){return!1},t.enabled=!this.props.disabled,t.on=this.props.value,t.style=this.props.style,i.createElement(u,babelHelpers.extends({},t,{ref:function(t){e._rctSwitch=t},onChange:this._onChange}))}}),u=(p.create({rctSwitchIOS:{height:31,width:51}}),h("AndroidSwitch",l,{nativeOnly:{onChange:!0,on:!0,enabled:!0}}));n.exports=l},304);