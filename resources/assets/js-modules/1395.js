__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(412),a=babelHelpers.interopRequireDefault(i),s=t(271),u=(babelHelpers.interopRequireDefault(s),t(44)),n=t(750),b=babelHelpers.interopRequireDefault(n),p=t(1396),o=babelHelpers.interopRequireDefault(p),f=t(814),d=babelHelpers.interopRequireDefault(f),H=t(779),v=babelHelpers.interopRequireDefault(H),c=t(422),x=babelHelpers.interopRequireDefault(c),y=t(1397),R=babelHelpers.interopRequireDefault(y),q={divider:b.default,size:o.default,avatars:a.default.arrayOf(d.default),subtitle:a.default.string,title:a.default.string.isRequired},D=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.avatars,l=e.divider,r=e.title,i=e.subtitle,a=e.size;return babelHelpers.jsx(v.default,{divider:l},void 0,babelHelpers.jsx(u.View,{style:j.content},void 0,babelHelpers.jsx(u.View,{style:j.titleContainer},void 0,babelHelpers.jsx(u.Text,{style:j.title},void 0,r),!!i&&babelHelpers.jsx(u.Text,{style:j.subtitle},void 0,i)),babelHelpers.jsx(R.default,{avatars:t.slice(0,3),size:a})))},t}(s.PureComponent);r.default=D,D.propTypes=q;var j=x.default.create(function(e){var t=e.font,l=e.size;return{content:{flexDirection:"row",alignItems:"center"},titleContainer:{flex:1},title:t.regularPlus,subtitle:babelHelpers.extends({},t.small,{marginTop:l.vertical.tiny})}})},1395);