__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var a=r(271),i=babelHelpers.interopRequireDefault(a),s=r(44),u=r(811),n=babelHelpers.interopRequireDefault(u),o=r(379),b=babelHelpers.interopRequireDefault(o),p=r(756),f=babelHelpers.interopRequireDefault(p),c=r(779),d=babelHelpers.interopRequireDefault(c),m=r(1023),H=r(376),h=babelHelpers.interopRequireDefault(H),v=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=h.default.user(),r=e.firstName,t=e.lastName,l=e.pictureUrl,a=b.default.phrase("%{firstName} %{lastName}",{firstName:r,lastName:t},"Full name of the user");return babelHelpers.jsx(d.default,{},void 0,babelHelpers.jsx(s.View,{style:g.container},void 0,babelHelpers.jsx(f.default,{large:!0},void 0,a),babelHelpers.jsx(s.Image,{source:(0,n.default)(l),style:g.image})))},r}(i.default.Component);l.default=v;var g=m.ThemedStyleSheet.create(function(e){var r=e.size;return{container:{alignItems:"center",flex:1,flexDirection:"row",justifyContent:"space-between"},image:{borderRadius:r.vertical.large/2,height:r.vertical.large,width:r.vertical.large}}})},1746);