__d(function(e,r,t,i){Object.defineProperty(i,"__esModule",{value:!0});var a=r(412),o=babelHelpers.interopRequireDefault(a),d=r(271),l=babelHelpers.interopRequireDefault(d),s=r(44),n=r(927),p=babelHelpers.interopRequireDefault(n),u=r(425),b=babelHelpers.interopRequireDefault(u),h=r(1026),g=r(906),f=r(1021),m=babelHelpers.interopRequireDefault(f),c={beer:{paddingBottom:1},coffee:{paddingLeft:2},drink:{paddingLeft:2},fitness:{paddingBottom:1},galleries:{paddingBottom:2},heart:{paddingTop:2},library:{paddingTop:1},movieTheater:{paddingBottom:1},music:{paddingRight:2},shopping:{paddingBottom:2},sightseeing:{paddingBottom:1},star:{paddingBottom:1},water:{paddingBottom:1}},k={beer:16,bike:18,bus:14,drink:16,galleries:19,heart:14,library:14,movieTheater:14,music:17,sightseeing:17,star:19,water:13},B={airmoji:g.AirmojiPropType,isSelected:o.default.bool,markerWithBorder:o.default.bool,svg:m.default},w={isSelected:!1,markerWithBorder:!1},y=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.isSelected,t=e.markerWithBorder,i=e.airmoji,a=e.svg,o=a||(0,h.getSvgForAirmoji)(i)||"extras";return babelHelpers.jsx(s.View,{},void 0,babelHelpers.jsx(s.View,{style:v.shadow}),babelHelpers.jsx(s.View,{style:[v.marker,r&&v.markerSelected,t&&v.markerWithBorder,c[o]]},void 0,babelHelpers.jsx(p.default,{name:o,size:k[o]||15,color:r?b.default.white:b.default.dark.hof})))},r}(l.default.PureComponent);i.default=y;var H=Math.round(15),v=s.StyleSheet.create({shadow:{position:"absolute",top:2,left:0,backgroundColor:b.default.regular.foggy,width:30,height:30,borderRadius:H,opacity:.1},marker:{backgroundColor:b.default.white,marginBottom:2,width:30,height:30,borderRadius:H,alignItems:"center",justifyContent:"center"},markerSelected:{backgroundColor:b.default.core.babu},markerWithBorder:{width:38,height:38,borderColor:b.default.white,borderWidth:6,borderRadius:19}});y.propTypes=B,y.defaultProps=w},1025);