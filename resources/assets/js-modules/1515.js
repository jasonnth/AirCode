__d(function(e,t,r,i){Object.defineProperty(i,"__esModule",{value:!0});var a=t(412),l=babelHelpers.interopRequireDefault(a),o=t(271),n=babelHelpers.interopRequireDefault(o),s=t(44),u=t(1023),d=t(750),p=babelHelpers.interopRequireDefault(d),b=t(814),c=babelHelpers.interopRequireDefault(b),f=t(927),h=babelHelpers.interopRequireDefault(f),g=t(422),y=babelHelpers.interopRequireDefault(g),H=t(751),m=babelHelpers.interopRequireDefault(H),v=t(746),x=t(756),C=babelHelpers.interopRequireDefault(x),R=babelHelpers.extends({},v.AccessibilityPropTypes,{title:l.default.string.isRequired,divider:p.default,imageSource:c.default,onPressImageUpload:l.default.func}),j={divider:null,onPressImageUpload:function(){}},q=u.bp,D=function(e){function t(r){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return i.onLayoutUpdate=i.onLayoutUpdate.bind(i),i}return babelHelpers.inherits(t,e),t.prototype.onLayoutUpdate=function(e){var t=e.nativeEvent.layout;this.setState({imageHeight:t.height-q,imageWidth:t.width-q})},t.prototype.render=function(){var e=this,t=y.default.theme,r=t.color,i=t.size,a=this.props,l=a.title,o=a.imageSource,u=a.divider,d=a.onPressImageUpload;return n.default.createElement(m.default,babelHelpers.extends({divider:u,onPress:d},(0,v.a11y)(this.props)),babelHelpers.jsx(s.View,{style:w.content},void 0,babelHelpers.jsx(s.View,{style:w.titleContainer},void 0,babelHelpers.jsx(C.default,{regularBold:!0},void 0,l)),babelHelpers.jsx(s.View,{style:w.imageContainer,onLayout:function(t){return e.onLayoutUpdate(t)}},void 0,o?babelHelpers.jsx(s.Image,{source:o,resizeMode:"cover",style:{height:this.state.imageHeight,width:this.state.imageWidth}}):babelHelpers.jsx(h.default,{name:h.default.SVGS.plus,color:r.core.babu,size:i.icon.small}))))},t}(o.PureComponent);i.default=D,D.propTypes=R,D.defaultProps=j;var w=y.default.create(function(e){var t=e.color,r=e.size;return{content:{flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start",marginVertical:r.vertical.tiny},titleContainer:{flex:1},imageContainer:{height:r.vertical.huge,width:r.horizontal.huge,justifyContent:"center",alignItems:"center",borderColor:t.accent.hrGray,borderWidth:2,borderRadius:1,borderStyle:"dashed"}}})},1515);