__d(function(e,r,o,t){Object.defineProperty(t,"__esModule",{value:!0});var l=r(412),n=babelHelpers.interopRequireDefault(l),i=r(271),a=babelHelpers.interopRequireDefault(i),d=r(44),s=r(752),u=r(750),p=babelHelpers.interopRequireDefault(u),f=r(753),b=babelHelpers.interopRequireDefault(f),c={divider:p.default,children:n.default.node,inverse:n.default.bool,fullBleed:n.default.bool,onPress:n.default.func},h={divider:"padded",inverse:!1,fullBleed:!1,onPress:null},v=function(e){function r(o){babelHelpers.classCallCheck(this,r);var t=babelHelpers.possibleConstructorReturn(this,e.call(this,o));return t.setContainer=t.setContainer.bind(t),t}return babelHelpers.inherits(r,e),r.prototype.setNativeProps=function(e){this.container.setNativeProps(e)},r.prototype.setContainer=function(e){this.container=e},r.prototype.render=function(){var e=this.props,r=e.divider,o=e.children,t=e.inverse,l=e.fullBleed,n=e.onPress,i=a.default.createElement(d.View,{ref:this.setContainer,style:[C.container,H[r],t?C.inverseBackgroundColor:C.defaultBackgroundColor,l?C.fullBleedContainer:null]},babelHelpers.jsx(d.View,{style:[C.row,B[r]]},void 0,o));return n?babelHelpers.jsx(b.default,{onPress:this.props.onPress},void 0,i):i},r}(a.default.Component);t.default=v,v.defaultProps=h,v.propTypes=c;var C=d.StyleSheet.create({container:{overflow:"hidden",paddingHorizontal:s.size.baseRow.paddingHorizontal},fullBleedContainer:{paddingHorizontal:0},defaultBackgroundColor:{},inverseBackgroundColor:{backgroundColor:s.color.clear},row:{paddingVertical:2*s.bp,borderBottomColor:s.color.divider,borderBottomWidth:1}}),H=d.StyleSheet.create({padded:{},full:{paddingHorizontal:0},hero:{}}),B=d.StyleSheet.create({padded:{},full:{paddingHorizontal:s.size.baseRow.paddingHorizontal},hero:{borderBottomWidth:0},none:{borderBottomWidth:0}})},779);