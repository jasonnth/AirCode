__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(412),a=babelHelpers.interopRequireDefault(i),s=t(271),n=(babelHelpers.interopRequireDefault(s),t(44)),u=t(779),b=babelHelpers.interopRequireDefault(u),o=t(905),p=babelHelpers.interopRequireDefault(o),d=t(422),f=babelHelpers.interopRequireDefault(d),H={title:a.default.string.isRequired,rating:a.default.number.isRequired,subtitle:a.default.string,divider:a.default.string},c={subtitle:null,divider:null},v=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.title,l=e.subtitle,r=e.rating,i=e.divider;return babelHelpers.jsx(b.default,{divider:i},void 0,babelHelpers.jsx(n.View,{style:g.content},void 0,babelHelpers.jsx(n.View,{style:g.titleContainer},void 0,babelHelpers.jsx(n.Text,{style:g.title},void 0,t),l&&babelHelpers.jsx(n.Text,{style:g.subtitle},void 0,l)),babelHelpers.jsx(n.View,{style:g.starRatingContainer},void 0,babelHelpers.jsx(p.default,{rating:Math.floor(r)}))))},t}(s.PureComponent);r.default=v,v.propTypes=H,v.defaultProps=c;var g=f.default.create(function(e){var t=e.bp,l=e.font;return{content:{flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start",marginVertical:1*t},titleContainer:{flex:1},title:l.large,subtitle:babelHelpers.extends({},l.small,{marginTop:1*t}),starRatingContainer:{marginTop:.5*t}}})},1846);