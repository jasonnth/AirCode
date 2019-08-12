__d(function(t,e,s,n){Object.defineProperty(n,"__esModule",{value:!0});var a=e(412),i=babelHelpers.interopRequireDefault(a),o=e(271),r=(babelHelpers.interopRequireDefault(o),e(44)),l=e(782),u=babelHelpers.interopRequireDefault(l),p=e(785),h=babelHelpers.interopRequireDefault(p),d=e(786),c=babelHelpers.interopRequireDefault(d),f=e(422),b=babelHelpers.interopRequireDefault(f),y=Object.prototype.hasOwnProperty,m={children:i.default.node,withInsets:c.default,isMounted:i.default.func.isRequired},g={withInsets:[]},A=function(t){function e(s,n){babelHelpers.classCallCheck(this,e);var a=babelHelpers.possibleConstructorReturn(this,t.call(this,s,n));return a.state={toasts:{}},a}return babelHelpers.inherits(e,t),e.prototype.componentDidMount=function(){this.reconcile(this.state.toasts,this.props.children)},e.prototype.componentWillReceiveProps=function(t){this.reconcile(this.state.toasts,t.children)},e.prototype.onToastLayout=function(t,e){babelHelpers.extends(this.state.toasts[t],{height:e.nativeEvent.layout.height}),this.animateInIfNeeded(t)},e.prototype.addToast=function(t,e){this.state.toasts[t]={isAnimatingOut:!1,shouldAnimateIn:!0,translateY:new r.Animated.Value(0),node:e}},e.prototype.animateInIfNeeded=function(t){var e=this.state.toasts[t];e.shouldAnimateIn&&e.height&&(e.shouldAnimateIn=!1,r.Animated.spring(e.translateY,{toValue:-e.height,bounciness:0}).start())},e.prototype.animateOut=function(t){var e=this,s=this.state.toasts[t];s.isAnimatingOut||(s.isAnimatingOut=!0,r.Animated.spring(s.translateY,{toValue:0,bounciness:0}).start(function(){e.props.isMounted()&&s.isAnimatingOut&&(delete e.state.toasts[t],e.setState({toasts:e.state.toasts}))}))},e.prototype.reconcile=function(t,e){var s=this,n={};o.Children.forEach(e,function(a){a&&y.call(a,"key")&&(n[a.key]=!0,t[a.key]?(babelHelpers.extends(t[a.key],{node:a}),e[a.key]&&t[a.key].isAnimatingOut&&babelHelpers.extends(t[a.key],{shouldAnimateIn:!0,isAnimatingOut:!1}),s.animateInIfNeeded(a.key)):s.addToast(a.key,a))}),Object.keys(t).forEach(function(t){n[t]||s.animateOut(t)}),this.setState({toasts:this.state.toasts})},e.prototype.render=function(){var t=this,e=this.state.toasts,s=this.props.withInsets,n=(0,h.default)(s);return babelHelpers.jsx(r.View,{style:[H.container,{height:n.bottom}]},void 0,Object.keys(e).map(function(s){var n=e[s];return babelHelpers.jsx(r.Animated.View,{onLayout:function(e){return t.onToastLayout(s,e)},style:[H.toast,{transform:[{translateY:n.translateY}]}]},s,n.node)}))},e}(o.Component);A.defaultProps=g,A.propTypes=m,n.default=(0,u.default)(A);var H=b.default.create(function(){return{container:{position:"absolute",left:0,right:0,bottom:0,height:0},toast:{position:"absolute",left:0,right:0,top:0}}})},781);