__d(function(e,t,r,o){Object.defineProperty(o,"__esModule",{value:!0});var i=t(271),n=babelHelpers.interopRequireDefault(i),a=t(44),s=t(743),d=t(883),l=babelHelpers.interopRequireDefault(d),b=t(882),p=babelHelpers.interopRequireDefault(b),u=(0,s.forbidExtraProps)(l.default.propTypes),y=l.default.defaultProps,f=function(e){function t(r){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return o.state={keyboardVisible:!1},o.eventListeners=[],o.onKeyboardShow=o.onKeyboardShow.bind(o),o.onKeyboardHide=o.onKeyboardHide.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.registerKeyboardListeners()},t.prototype.componentWillUnmount=function(){this.unregisterKeyboardListeners()},t.prototype.registerKeyboardListeners=function(){var e="android"===a.Platform.OS?"keyboardDidShow":"keyboardWillShow",t="android"===a.Platform.OS?"keyboardDidHide":"keyboardWillHide";this.eventListeners=[a.Keyboard.addListener(e,this.onKeyboardShow),a.Keyboard.addListener(t,this.onKeyboardHide)]},t.prototype.unregisterKeyboardListeners=function(){this.eventListeners.forEach(function(e){return e.remove()})},t.prototype.onKeyboardShow=function(){this.setState({keyboardVisible:!0})},t.prototype.onKeyboardHide=function(){this.setState({keyboardVisible:!1})},t.prototype.render=function(){var e=this.state.keyboardVisible?l.default:p.default;return n.default.createElement(e,this.props)},t}(n.default.Component);o.default=f,f.propTypes=u,f.defaultProps=y},1509);