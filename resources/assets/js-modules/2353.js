__d(function(e,t,r,a){function l(){x.default.present(T.SUPPORT_CHAT_NEW_THREAD,{})}Object.defineProperty(a,"__esModule",{value:!0});var s=t(271),n=(babelHelpers.interopRequireDefault(s),t(44)),i=t(778),u=babelHelpers.interopRequireDefault(i),o=t(1032),b=babelHelpers.interopRequireDefault(o),p=t(773),d=babelHelpers.interopRequireDefault(p),f=t(787),H=babelHelpers.interopRequireDefault(f),h=t(1366),c=babelHelpers.interopRequireDefault(h),g=t(410),x=babelHelpers.interopRequireDefault(g),R=t(2352),T=babelHelpers.interopRequireWildcard(R),v={},D={},C=function(e){function t(r){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return a.state={text:""},a}return babelHelpers.inherits(t,e),t.prototype.navigateToMessagingFramework=function(){x.default.push(T.SUPPORT_CHAT_THREAD_SCREEN,{threadId:Number(this.state.text)})},t.prototype.render=function(){var e=this;return babelHelpers.jsx(H.default,{},void 0,babelHelpers.jsx(d.default,{title:"Launchpad",subtitle:"CX Chat Launch"}),babelHelpers.jsx(u.default,{},void 0,babelHelpers.jsx(c.default,{large:!0},void 0,babelHelpers.jsx(n.Text,{},void 0,"MESSAGE THREAD ID:"),babelHelpers.jsx(n.TextInput,{style:{height:40,borderColor:"gray",borderWidth:1},onChangeText:function(t){return e.setState({text:t})},value:this.state.text})),babelHelpers.jsx(b.default,{title:"Support Messaging",onPress:function(){e.navigateToMessagingFramework()},subtitle:"(Launch Support Messaging w/ Messaging Framework and ID above)"}),babelHelpers.jsx(b.default,{title:"New Thread",onPress:function(){l()},subtitle:"launch a new cx chat thread"})))},t}(s.Component);a.default=C,C.defaultProps=D,C.propTypes=v},2353);