__d(function(e,t,l,i){Object.defineProperty(i,"__esModule",{value:!0});var r=t(412),a=babelHelpers.interopRequireDefault(r),s=t(271),o=babelHelpers.interopRequireDefault(s),n=t(44),u=t(811),d=babelHelpers.interopRequireDefault(u),p=t(775),b=babelHelpers.interopRequireDefault(p),f=t(776),c=babelHelpers.interopRequireDefault(f),H=t(422),h=babelHelpers.interopRequireDefault(H),x=t(410),R=babelHelpers.interopRequireDefault(x),g={barType:b.default,host:a.default.shape({first_name:a.default.string.isRequired,profile_pic_path:a.default.string}).isRequired,onSubtitleLinkPress:a.default.func,subtitle:a.default.string,subtitleLink:a.default.string,title:a.default.string.isRequired,padded:a.default.bool},m={barType:R.default.Screen.BAR_TYPE.BASIC,onSubtitleLinkPress:function(){},subtitle:null,subtitleLink:null,padded:!0},y=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.host.profile_pic_path,l=e.title,i=e.onSubtitleLinkPress,r=e.subtitle,a=e.subtitleLink,s=e.padded;return babelHelpers.jsx(n.View,{style:v.container},void 0,babelHelpers.jsx(R.default.Screen,{barType:R.default.Screen.BAR_TYPE.BASIC}),babelHelpers.jsx(c.default,{}),babelHelpers.jsx(n.View,{style:[v.hostRow,s&&v.hostRowPadded]},void 0,babelHelpers.jsx(n.View,{style:v.textContainer},void 0,babelHelpers.jsx(n.Text,{style:v.title},void 0,l),!!r&&babelHelpers.jsx(n.Text,{style:v.subtitle,onPress:a?i:function(){}},void 0,r,!!a&&babelHelpers.jsx(n.Text,{style:[v.subtitle,v.subtitleLink]},void 0," ",a))),babelHelpers.jsx(n.Image,{source:(0,d.default)(t),style:v.profilePicImage})))},t}(o.default.Component);i.default=y,y.defaultProps=m,y.propTypes=g;var v=h.default.create(function(e){var t=e.bp,l=e.color,i=e.font,r=e.size;return{container:{paddingHorizontal:r.horizontal.medium,paddingBottom:r.vertical.medium,borderBottomWidth:1,borderBottomColor:l.divider},hostRow:{marginTop:7*t,flexDirection:"row",justifyContent:"space-between"},hostRowPadded:{paddingTop:r.vertical.medium,paddingBottom:r.vertical.tiny},profilePicImage:{borderRadius:30,height:60,width:60,paddingLeft:3*t},textContainer:{flexDirection:"column",justifyContent:"center",flexWrap:"wrap",flex:1},title:i.regularPlus,subtitle:babelHelpers.extends({},i.regular,{flexWrap:"wrap"}),subtitleLink:{color:l.core.babu}}})},1433);