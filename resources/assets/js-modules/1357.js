__d(function(e,t,s,a){Object.defineProperty(a,"__esModule",{value:!0});var l=t(271),r=babelHelpers.interopRequireDefault(l),i=t(44),n=t(1043),o=babelHelpers.interopRequireDefault(n),u=t(1355),b=babelHelpers.interopRequireDefault(u),d=t(751),p=babelHelpers.interopRequireDefault(d),h=t(772),f=babelHelpers.interopRequireDefault(h),x=function(e){function s(t){babelHelpers.classCallCheck(this,s);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return a.state={duration:700,progress:new i.Animated.Value(0)},a.onValueChange=a.onValueChange.bind(a),a.onPlayPress=a.onPlayPress.bind(a),a.onResetPress=a.onResetPress.bind(a),a}return babelHelpers.inherits(s,e),s.prototype.onValueChange=function(e){this.state.progress.setValue(e)},s.prototype.onPlayPress=function(){i.Animated.timing(this.state.progress,{toValue:1,duration:this.state.duration}).start()},s.prototype.onResetPress=function(){i.Animated.timing(this.state.progress,{toValue:0,duration:this.state.duration}).start()},s.prototype.render=function(){var e=this;return babelHelpers.jsx(f.default,{title:"Lottie Example",subtitle:"This example uses the Animated API"},void 0,babelHelpers.jsx(p.default,{},void 0,babelHelpers.jsx(i.View,{style:{flex:1,justifyContent:"center",alignItems:"center"}},void 0,babelHelpers.jsx(i.View,{},void 0,babelHelpers.jsx(b.default,{style:{width:200,height:200},source:t(1356),progress:this.state.progress})))),babelHelpers.jsx(p.default,{},void 0,babelHelpers.jsx(i.View,{},void 0,babelHelpers.jsx(i.Text,{},void 0,"Progress:")),babelHelpers.jsx(i.Slider,{minimumValue:0,maximumValue:1,value:0,onValueChange:this.onValueChange})),babelHelpers.jsx(p.default,{},void 0,babelHelpers.jsx(i.View,{style:{flexDirection:"row",justifyContent:"space-around"}},void 0,babelHelpers.jsx(o.default,{dark:!0,onPress:this.onPlayPress},void 0,"Play"),babelHelpers.jsx(o.default,{dark:!0,onPress:this.onResetPress},void 0,"Reset"))),babelHelpers.jsx(p.default,{},void 0,babelHelpers.jsx(i.View,{},void 0,babelHelpers.jsx(i.Text,{},void 0,"Duration: (",Math.round(this.state.duration),"ms)")),babelHelpers.jsx(i.Slider,{minimumValue:50,maximumValue:4e3,value:this.state.duration,onValueChange:function(t){return e.setState({duration:t})}})))},s}(r.default.Component);a.default=x},1357);