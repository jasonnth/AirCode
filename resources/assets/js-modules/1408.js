__d(function(e,t,r,i){Object.defineProperty(i,"__esModule",{value:!0});var l=t(412),s=babelHelpers.interopRequireDefault(l),a=t(271),o=(babelHelpers.interopRequireDefault(a),t(44)),n=t(422),u=babelHelpers.interopRequireDefault(n),p=t(750),b=babelHelpers.interopRequireDefault(p),c=t(815),d=babelHelpers.interopRequireDefault(c),f=t(814),x=babelHelpers.interopRequireDefault(f),H=t(925),h=babelHelpers.interopRequireDefault(H),m=t(1409),v=babelHelpers.interopRequireDefault(m),y=t(779),R=babelHelpers.interopRequireDefault(y),g=o.Dimensions.get("window"),P=g.width,q=P/6,D=1.5*q,T=P/4,w=2*T/3,j={divider:null,onPress:function(){},orientation:"landscape"},C={divider:b.default,icon:c.IconPropType,iconSize:d.default.propTypes.size,iconColor:d.default.propTypes.color,onPress:s.default.func,orientation:s.default.oneOf(["landscape","portrait","square"]),picture:x.default,subtitle:s.default.string.isRequired,suptitle:s.default.string,title:s.default.string.isRequired},z=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.renderPicture=function(){var e=this.props,t=e.orientation,r=e.picture;return"square"===t?babelHelpers.jsx(v.default,{source:r,style:[I.picture,I.pictureSquare]}):babelHelpers.jsx(h.default,{source:r,style:[I.picture,"portrait"===t&&I.picturePortrait]})},t.prototype.render=function(){var e=u.default.theme.color,t=this.props,r=t.divider,i=t.icon,l=t.iconSize,s=t.iconColor,a=t.onPress,n=t.title,p=t.subtitle,b=t.suptitle;return babelHelpers.jsx(R.default,{divider:r,onPress:a},void 0,babelHelpers.jsx(o.View,{style:I.columns},void 0,this.renderPicture(),babelHelpers.jsx(o.View,{style:I.rows},void 0,!!b&&babelHelpers.jsx(o.Text,{style:I.suptitle},void 0,b),babelHelpers.jsx(o.View,{style:I.titleContainer},void 0,babelHelpers.jsx(o.Text,{style:I.title},void 0,n),!!i&&babelHelpers.jsx(o.View,{style:I.iconContainer},void 0,babelHelpers.jsx(d.default,{name:i,size:l,color:s||e.accent.hrGray}))),babelHelpers.jsx(o.Text,{style:I.subtitle},void 0,p))))},t}(a.PureComponent);z.defaultProps=j,z.propTypes=C,z.ORIENTATION_PORTRAIT="portrait",z.ORIENTATION_LANDSCAPE="landscape";var I=u.default.create(function(e){var t=e.font,r=e.size;return{columns:{flexDirection:"row"},iconContainer:{marginTop:3},picture:{flex:1,alignSelf:"center",marginRight:r.horizontal.small,height:w,maxWidth:T},picturePortrait:{height:1.5*D,maxWidth:D},pictureSquare:{height:D,maxWidth:D},rows:{flex:1,flexDirection:"column",flexWrap:"wrap"},subtitle:t.small,suptitle:babelHelpers.extends({},t.xsmallPlus,{marginBottom:r.vertical.micro}),titleContainer:{alignItems:"flex-start",flexDirection:"row",paddingBottom:r.vertical.tiny},title:babelHelpers.extends({},t.regularPlus,{flex:1,flexWrap:"wrap",marginRight:r.horizontal.tiny})}});i.default=z},1408);