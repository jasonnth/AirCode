__d(function(e,l,r,t){Object.defineProperty(t,"__esModule",{value:!0});var i=l(412),s=babelHelpers.interopRequireDefault(i),n=l(271),a=(babelHelpers.interopRequireDefault(n),l(44)),o=l(750),u=babelHelpers.interopRequireDefault(o),p=l(779),b=babelHelpers.interopRequireDefault(p),d=l(814),f=babelHelpers.interopRequireDefault(d),c=l(901),h=babelHelpers.interopRequireDefault(c),m=l(422),H=babelHelpers.interopRequireDefault(m),v={onActionPress:s.default.func,onImagePress:s.default.func,onNamePress:s.default.func,name:s.default.string.isRequired,image:f.default.isRequired,subtitle:s.default.string,divider:u.default,children:s.default.node},P={onActionPress:null,onImagePress:null,onNamePress:null,subtitle:null,divider:null,children:null},g=function(e){function l(){return babelHelpers.classCallCheck(this,l),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(l,e),l.prototype.render=function(){var e=H.default.theme.color,l=this.props,r=l.divider,t=l.image,i=l.name,s=l.subtitle;return babelHelpers.jsx(b.default,{divider:r},void 0,babelHelpers.jsx(a.View,{style:x.rowContainer},void 0,babelHelpers.jsx(h.default,{size:h.default.SIZES.SMALL,image:t,onPress:this.props.onImagePress}),babelHelpers.jsx(a.TouchableHighlight,{underlayColor:e.white,style:x.textContainer,onPress:this.props.onNamePress},void 0,babelHelpers.jsx(a.View,{},void 0,babelHelpers.jsx(a.Text,{style:x.name},void 0,i),!!s&&babelHelpers.jsx(a.Text,{style:x.subtitle},void 0,s))),this.props.children))},l}(n.PureComponent);t.default=g,g.propTypes=v,g.defaultProps=P;var x=H.default.create(function(e){var l=e.font,r=e.size;return{rowContainer:{flexDirection:"row",marginVertical:r.vertical.tiny},textContainer:{flex:1,justifyContent:"center",paddingLeft:r.horizontal.small},name:l.largePlus,subtitle:l.small}})},1419);