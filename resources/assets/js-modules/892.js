__d(function(e,t,n,o){Object.defineProperty(o,"__esModule",{value:!0});var a=t(412),r=babelHelpers.interopRequireDefault(a),l=t(271),i=(babelHelpers.interopRequireDefault(l),t(44)),s=t(782),u=babelHelpers.interopRequireDefault(s),p=t(891),d=babelHelpers.interopRequireDefault(p),b=t(410),c=babelHelpers.interopRequireDefault(b),f={id:r.default.string.isRequired,setTimeout:r.default.func.isRequired},h=function(e){function t(n){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,n));return o.state={modal:d.default.instance.get(n.id)},o.onUpdateState=o.onUpdateState.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){var e=this;d.default.instance.addListener(this.props.id,this.onUpdateState),this.props.setTimeout(function(){e.state.modal.onModalPresented&&e.state.modal.onModalPresented()},16)},t.prototype.componentWillUnmount=function(){d.default.instance.removeListener(this.props.id,this.onUpdateState)},t.prototype.onUpdateState=function(e){this.setState({modal:e})},t.prototype.render=function(){var e=this.state.modal,t=e.children,n=e.backgroundColor;return babelHelpers.jsx(i.View,{style:[i.StyleSheet.absoluteFill,{backgroundColor:n}]},void 0,babelHelpers.jsx(c.default.Screen,{leftIcon:c.default.Screen.LEFT_ICON.CLOSE}),t)},t}(l.Component);h.propTypes=f,o.default=(0,u.default)(h)},892);