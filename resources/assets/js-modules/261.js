__d(function(t,e,n,r){"use strict";function a(t,e){return t.reduce(function(t,e){for(var n in e)null!=e[n]&&(t[n]=e[n]);return t},babelHelpers.extends({},e))}function o(t){return{backgroundColor:null!=t.backgroundColor?{value:t.backgroundColor,animated:t.animated}:null,barStyle:null!=t.barStyle?{value:t.barStyle,animated:t.animated}:null,translucent:t.translucent,hidden:null!=t.hidden?{value:t.hidden,animated:t.animated,transition:t.showHideTransition}:null,networkActivityIndicatorVisible:t.networkActivityIndicatorVisible}}var l=e(60),i=e(46),s=(e(55),e(108)),u=e(25).StatusBarManager,d=function(t){function e(){var n,r,o;babelHelpers.classCallCheck(this,e);for(var l=arguments.length,i=Array(l),d=0;d<l;d++)i[d]=arguments[d];return n=r=babelHelpers.possibleConstructorReturn(this,t.call.apply(t,[this].concat(i))),r._stackEntry=null,r._updatePropsStack=function(){clearImmediate(e._updateImmediate),e._updateImmediate=setImmediate(function(){var t=e._currentValues,n=a(e._propsStack,e._defaultProps);t&&t.barStyle.value===n.barStyle.value||u.setStyle(n.barStyle.value),t&&t.backgroundColor.value===n.backgroundColor.value||u.setColor(s(n.backgroundColor.value),n.backgroundColor.animated),t&&t.hidden.value===n.hidden.value||u.setHidden(n.hidden.value),t&&t.translucent===n.translucent||u.setTranslucent(n.translucent),e._currentValues=n})},o=n,babelHelpers.possibleConstructorReturn(r,o)}return babelHelpers.inherits(e,t),e.setHidden=function(t,n){n=n||"none",e._defaultProps.hidden.value=t,u.setHidden(t)},e.setBarStyle=function(t,n){n=n||!1,e._defaultProps.barStyle.value=t,u.setStyle(t)},e.setNetworkActivityIndicatorVisible=function(t){},e.setBackgroundColor=function(t,n){n=n||!1,e._defaultProps.backgroundColor.value=t,u.setColor(s(t),n)},e.setTranslucent=function(t){e._defaultProps.translucent=t,u.setTranslucent(t)},e.prototype.componentDidMount=function(){this._stackEntry=o(this.props),e._propsStack.push(this._stackEntry),this._updatePropsStack()},e.prototype.componentWillUnmount=function(){var t=e._propsStack.indexOf(this._stackEntry);e._propsStack.splice(t,1),this._updatePropsStack()},e.prototype.componentDidUpdate=function(){var t=e._propsStack.indexOf(this._stackEntry);this._stackEntry=o(this.props),e._propsStack[t]=this._stackEntry,this._updatePropsStack()},e.prototype.render=function(){return null},e}(l.Component);d._propsStack=[],d._defaultProps=o({animated:!1,showHideTransition:"fade",backgroundColor:"black",barStyle:"default",translucent:!1,hidden:!1,networkActivityIndicatorVisible:!1}),d._updateImmediate=null,d._currentValues=null,d.currentHeight=u.HEIGHT,d.propTypes={hidden:l.PropTypes.bool,animated:l.PropTypes.bool,backgroundColor:i,translucent:l.PropTypes.bool,barStyle:l.PropTypes.oneOf(["default","light-content","dark-content"]),networkActivityIndicatorVisible:l.PropTypes.bool,showHideTransition:l.PropTypes.oneOf(["fade","slide"])},d.defaultProps={animated:!1,showHideTransition:"fade"},n.exports=d},261);