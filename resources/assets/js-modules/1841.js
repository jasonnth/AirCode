__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var i=t(412),n=babelHelpers.interopRequireDefault(i),s=t(271),a=(babelHelpers.interopRequireDefault(s),t(44)),o=t(779),u=babelHelpers.interopRequireDefault(o),b=t(422),p=babelHelpers.interopRequireDefault(b),d=t(905),f=babelHelpers.interopRequireDefault(d),c={rating:n.default.number.isRequired,title:n.default.string,description:n.default.string,divider:n.default.string},x={title:null,description:null,divider:null},H=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.rating,r=e.title,l=e.description,i=e.divider;return babelHelpers.jsx(u.default,{divider:i},void 0,babelHelpers.jsx(a.View,{style:v.content},void 0,babelHelpers.jsx(a.View,{style:v.contentContainer},void 0,babelHelpers.jsx(a.View,{style:v.numberContainer},void 0,babelHelpers.jsx(a.Text,{style:v.number},void 0,t,"\xa0"),babelHelpers.jsx(f.default,{rating:t,size:"title2"})),r&&babelHelpers.jsx(a.Text,{style:v.title},void 0,r),l&&babelHelpers.jsx(a.Text,{style:v.description},void 0,l))))},t}(s.PureComponent);l.default=H,H.propTypes=c,H.defaultProps=x;var v=p.default.create(function(e){var t=e.bp,r=e.color,l=e.font;return{content:{flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start",marginVertical:1*t},contentContainer:{flex:1},numberContainer:{flexDirection:"row"},number:l.title2,title:l.large,description:babelHelpers.extends({},l.small,{marginTop:2*t,color:r.textMuted})}})},1841);