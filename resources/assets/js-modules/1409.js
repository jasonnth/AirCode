__d(function(e,t,r,a){Object.defineProperty(a,"__esModule",{value:!0});var o=t(271),n=(babelHelpers.interopRequireDefault(o),t(44)),i=t(811),l=babelHelpers.interopRequireDefault(i),s=t(814),p=babelHelpers.interopRequireDefault(s),u=t(422),d=babelHelpers.interopRequireDefault(u),c="android"===n.Platform.OS,b={style:n.View.propTypes.style,source:p.default.isRequired},f=function(e){function t(r,a){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,r,a));return o.onLoadImageEnd=o.onLoadImageEnd.bind(o),o.state={opacity:new n.Animated.Value(c?1:0)},o}return babelHelpers.inherits(t,e),t.prototype.onLoadImageEnd=function(){c||n.Animated.timing(this.state.opacity,{toValue:1}).start()},t.prototype.render=function(){var e=this.props.source;return babelHelpers.jsx(n.View,{style:[y.container,this.props.style]},void 0,babelHelpers.jsx(n.Animated.Image,{onLoadEnd:this.onLoadImageEnd,resizeMode:"cover",source:(0,l.default)(e),style:[y.picture,!c&&{opacity:this.state.opacity}]}))},t}(o.Component);f.propTypes=b;var y=d.default.create(function(){return{container:{flex:1,overflow:"hidden"},picture:{flex:1}}});a.default=f},1409);