__d(function(e,t,l,a){Object.defineProperty(a,"__esModule",{value:!0});var r=t(271),i=(babelHelpers.interopRequireDefault(r),t(44)),o=t(425),s=babelHelpers.interopRequireDefault(o),n=t(752),b=t(787),u=babelHelpers.interopRequireDefault(b),p=t(910),d=babelHelpers.interopRequireDefault(p),f=t(841),c=babelHelpers.interopRequireDefault(f),H=t(773),g=babelHelpers.interopRequireDefault(H),h=t(1043),j=babelHelpers.interopRequireDefault(h),x=t(1057),v=babelHelpers.interopRequireDefault(x),y=function(e){function t(l){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,l));return a.state={flatButtonLoading:!0,primaryButtonLoading:!0},a}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this;return babelHelpers.jsx(i.View,{style:D.container},void 0,babelHelpers.jsx(u.default,{style:D.container,withInsets:[v.default]},void 0,babelHelpers.jsx(g.default,{title:"Loading states",subtitle:"For when you're waiting"}),babelHelpers.jsx(d.default,{first:!0,title:"Normal"}),babelHelpers.jsx(i.View,{style:D.section},void 0,babelHelpers.jsx(c.default,{})),babelHelpers.jsx(d.default,{title:"Light"}),babelHelpers.jsx(i.View,{style:[D.section,D.sectionDark]},void 0,babelHelpers.jsx(c.default,{light:!0})),babelHelpers.jsx(d.default,{title:"FlatButton",subtitle:"Click to toggle loading"}),babelHelpers.jsx(i.View,{style:[D.section,D.sectionDark]},void 0,babelHelpers.jsx(j.default,{loading:this.state.flatButtonLoading,onPress:function(){return e.setState({flatButtonLoading:!e.state.flatButtonLoading})}},void 0,babelHelpers.jsx(i.Text,{},void 0,"Do Something")))),babelHelpers.jsx(v.default,{loading:this.state.primaryButtonLoading,onPress:function(){return e.setState({primaryButtonLoading:!e.state.primaryButtonLoading})}},void 0,"Click me"))},t}(r.Component);a.default=y;var D=i.StyleSheet.create({container:{position:"absolute",top:0,left:0,bottom:0,right:0},section:{padding:3*n.bp,backgroundColor:s.default.white,alignItems:"center"},sectionDark:{borderColor:s.default.white,borderTopWidth:n.bp,backgroundColor:s.default.core.babu}})},1056);