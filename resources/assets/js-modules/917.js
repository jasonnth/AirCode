__d(function(e,r,o,t){Object.defineProperty(t,"__esModule",{value:!0});var l=r(412),i=babelHelpers.interopRequireDefault(l),n=r(271),a=(babelHelpers.interopRequireDefault(n),r(44)),s=r(422),d=babelHelpers.interopRequireDefault(s),p={children:i.default.node,onPress:i.default.func},u=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.children,o=e.onPress,t=d.default.theme.color,l=babelHelpers.jsx(a.View,{style:b.row},void 0,r);return o?babelHelpers.jsx(a.TouchableHighlight,{style:b.container,underlayColor:t.white,onPress:o},void 0,l):babelHelpers.jsx(a.View,{style:b.container},void 0,l)},r}(n.Component);t.default=u,u.propTypes=p;var b=d.default.create(function(e){var r=e.bp,o=e.color,t=e.size;return{container:{overflow:"hidden",marginHorizontal:1.5*r,backgroundColor:o.white,paddingHorizontal:1.5*r},row:{paddingTop:t.vertical.medium,paddingBottom:t.vertical.tiny,borderBottomWidth:1,borderBottomColor:o.divider}}})},917);