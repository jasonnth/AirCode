__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var i=t(412),s=babelHelpers.interopRequireDefault(i),o=t(271),n=(babelHelpers.interopRequireDefault(o),t(44)),u=t(1023),a=t(841),b=babelHelpers.interopRequireDefault(a),p=t(773),d=babelHelpers.interopRequireDefault(p),f=t(787),y=babelHelpers.interopRequireDefault(f),H=t(1366),c=babelHelpers.interopRequireDefault(H),h=u.ThemedStyleSheet.create(function(e){return{body:e.font.regular}}),v=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.renderBody=function(){var e=this.props.body;return"string"==typeof e&&""!==e?babelHelpers.jsx(c.default,{divider:"none"},void 0,babelHelpers.jsx(n.Text,{style:h.body},void 0,e)):e},t.prototype.render=function(){var e=this.props,t=e.isLoading,r=e.title,l=e.subtitle;return babelHelpers.jsx(y.default,{},void 0,babelHelpers.jsx(d.default,{title:r,subtitle:l}),t?babelHelpers.jsx(b.default,{}):this.renderBody())},t}(o.Component);v.propTypes={body:s.default.node,isLoading:s.default.bool,subtitle:s.default.string,title:s.default.string.isRequired},l.default=v},2023);