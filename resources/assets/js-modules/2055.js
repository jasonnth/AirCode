__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var a=r(412),s=babelHelpers.interopRequireDefault(a),u=r(271),o=babelHelpers.interopRequireDefault(u),i=r(44),n=r(1059),p=babelHelpers.interopRequireDefault(n),b=r(1060),f=babelHelpers.interopRequireDefault(b),c={categories:s.default.array.isRequired,onPressGuidebook:s.default.func.isRequired},d=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.categories,t=e.onPressGuidebook;return babelHelpers.jsx(p.default,{style:H.carousel},void 0,r.map(function(e){return babelHelpers.jsx(i.TouchableOpacity,{onPress:function(){return t(e)}},e.title,babelHelpers.jsx(f.default,{title:e.localized_name_for_guest_page,image:e.photo_url}))}))},r}(o.default.Component);l.default=d;var H=i.StyleSheet.create({carousel:{paddingLeft:24}});d.propTypes=c},2055);