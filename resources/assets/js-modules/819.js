__d(function(e,t,o,r){Object.defineProperty(r,"__esModule",{value:!0});var a=t(412),i=babelHelpers.interopRequireDefault(a),n=t(271),l=(babelHelpers.interopRequireDefault(n),t(44)),s=t(743),u=t(815),p=babelHelpers.interopRequireDefault(u),d=t(422),b=babelHelpers.interopRequireDefault(d),c=(0,s.forbidExtraProps)({value:i.default.bool.isRequired,onChange:i.default.func.isRequired,dark:i.default.bool}),h={dark:!1},f=function(e){return 4*e},g=function(e){return 6*e},m=function(e){return f(e)-2},v=function(e){return g(e)-m(e)-2},k=function(e){function t(o,r){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,o,r)),i=b.default.theme.bp,n=new l.Animated.Value(o.value?1:0),s=n.interpolate({inputRange:[0,1],outputRange:[1,0]}),u=n.interpolate({inputRange:[0,1],outputRange:[0,v(i)]});return a.state={position:n,antiPosition:s,translateX:u},a.onPress=a.onPress.bind(a),a}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){this.props.value!==e.value&&this.setValue(e.value)},t.prototype.onPress=function(){this.props.onChange(!this.props.value)},t.prototype.setValue=function(e){var t=e?1:0;this.state.position.stopAnimation(),l.Animated.timing(this.state.position,{toValue:t,duration:200}).start()},t.prototype.render=function(){var e=b.default.theme.color,t=this.state,o=t.translateX,r=t.position,a=t.antiPosition;return babelHelpers.jsx(l.TouchableWithoutFeedback,{onPress:this.onPress},void 0,babelHelpers.jsx(l.View,{style:[C.container,this.props.dark&&C.dark]},void 0,babelHelpers.jsx(l.Animated.View,{style:[C.switch,this.props.dark&&C.dark,{transform:[{translateX:o}]}]},void 0,babelHelpers.jsx(p.default.Animated,{color:e.core.babu,size:10,backgroundColor:e.clear,style:[C.off,{opacity:a}],name:"remove"}),babelHelpers.jsx(p.default.Animated,{color:e.core.babu,size:14,backgroundColor:e.clear,style:[C.on,{opacity:r}],name:"ok-alt"}))))},t}(n.PureComponent);r.default=k,k.defaultProps=h,k.propTypes=c;var C=b.default.create(function(e){var t=e.bp,o=e.color;return{container:{backgroundColor:o.clear,borderWidth:1,borderColor:o.white,height:f(t),width:g(t),borderRadius:f(t)/2},onBackground:{},switch:{backgroundColor:o.white,borderWidth:1,borderColor:o.white,width:m(t),height:m(t),borderRadius:f(t)/2,justifyContent:"center",alignItems:"center"},off:{position:"absolute",width:m(t)-2,height:m(t)-2,top:0,marginHorizontal:8,marginVertical:9,backgroundColor:o.clear},on:{position:"absolute",width:m(t)-2,height:m(t)-2,top:0,margin:7},dark:{borderColor:o.accent.hrGray}}})},819);