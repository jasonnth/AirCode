__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var a=t(412),n=babelHelpers.interopRequireDefault(a),o=t(271),s=(babelHelpers.interopRequireDefault(o),t(44)),i=t(1040),p=babelHelpers.interopRequireDefault(i),u=t(422),b=babelHelpers.interopRequireDefault(u),d=t(841),c=babelHelpers.interopRequireDefault(d),f={style:s.View.propTypes.style,onPress:n.default.func,children:n.default.node,loading:n.default.bool},y={loading:!1},H=function(e){function t(r,l){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,r,l));return a.state={press:new s.Animated.Value(0)},a}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=b.default.theme.color,t=this.props,r=t.style,l=t.onPress,a=t.loading,n=this.state.press.interpolate({inputRange:[0,1],outputRange:[e.buttonBarButton,e.buttonBarButtonTapped]});return babelHelpers.jsx(p.default,{press:this.state.press,onPress:l},void 0,babelHelpers.jsx(s.Animated.View,{style:[g.container,r,{backgroundColor:n}]},void 0,!a&&babelHelpers.jsx(s.Text,{style:g.text},void 0,this.props.children),a&&babelHelpers.jsx(s.View,{style:g.loader},void 0,babelHelpers.jsx(c.default,{light:!0}))))},t}(o.Component);l.default=H,H.defaultProps=y,H.propTypes=f,H.contentInset={top:0,bottom:57.5};var g=b.default.create(function(e){var t=e.bp,r=e.color,l=e.font,a=e.size,n=7*t;return{container:{justifyContent:"center",alignItems:"center",paddingVertical:a.vertical.small,borderRadius:n/2},text:babelHelpers.extends({},l.largeBold,{color:r.primaryButtonText}),loader:{flex:1,paddingVertical:a.vertical.tiny,alignItems:"center",justifyContent:"center",backgroundColor:r.core.babu}}})},1918);