__d(function(e,t,l,i){Object.defineProperty(i,"__esModule",{value:!0});var r=t(412),o=babelHelpers.interopRequireDefault(r),a=t(271),n=(babelHelpers.interopRequireDefault(a),t(44)),s=t(422),c=babelHelpers.interopRequireDefault(s),d=t(750),b=babelHelpers.interopRequireDefault(d),u=t(779),p=babelHelpers.interopRequireDefault(u),f=t(815),h=babelHelpers.interopRequireDefault(f),v=t(901),y=babelHelpers.interopRequireDefault(v),H={title:o.default.string.isRequired,subtitle:o.default.string,checked:o.default.bool,onToggle:o.default.func,divider:b.default,avatarUrl:o.default.string,actionTitle:o.default.string,onActionPress:o.default.func,disabled:o.default.bool},g={checked:!1,disabled:!1,avatarUrl:null,onToggle:function(){},onActionPress:function(){}},x=function(e){function t(l,i){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,l,i));return r.state={opacity:new n.Animated.Value(l.checked?1:0)},r.setChecked=r.setChecked.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){this.props.checked!==e.checked&&this.setChecked(e.checked)},t.prototype.setChecked=function(e){var t=e?1:0;n.Animated.timing(this.state.opacity,{toValue:t,duration:200}).start()},t.prototype.renderActionTitle=function(){var e=this.props,t=e.actionTitle,l=e.onActionPress,i=e.disabled,r=babelHelpers.jsx(n.Text,{style:m.action},void 0,t);return i?r:babelHelpers.jsx(n.TouchableOpacity,{onPress:l},void 0,r)},t.prototype.render=function(){var e=this.props,t=e.title,l=e.subtitle,i=e.divider,r=e.avatarUrl,o=e.onToggle,a=e.actionTitle,s=e.disabled,d=c.default.theme,b=d.color,u=d.size;return babelHelpers.jsx(p.default,{divider:i,onPress:o},void 0,babelHelpers.jsx(n.View,{style:m.container},void 0,r&&babelHelpers.jsx(n.View,{style:m.profilePicContainer},void 0,babelHelpers.jsx(y.default,{size:y.default.SIZES.SMALL,image:r})),babelHelpers.jsx(n.View,{style:m.titleContainer},void 0,babelHelpers.jsx(n.Text,{style:m.title},void 0,t),!!l&&babelHelpers.jsx(n.Text,{style:m.subtitle},void 0,l),!!a&&this.renderActionTitle()),babelHelpers.jsx(n.TouchableWithoutFeedback,{onPress:s?null:o},void 0,babelHelpers.jsx(n.View,{style:m.iconContainer},void 0,babelHelpers.jsx(n.View,{style:m.uncheckedIcon}),babelHelpers.jsx(h.default.Animated,{size:u.icon.small,name:"ok-fill",color:b.core.babu,style:[m.checkedIcon,{opacity:this.state.opacity}]})))))},t}(a.PureComponent);i.default=x,x.defaultProps=g,x.propTypes=H;var m=c.default.create(function(e){var t=e.color,l=e.size,i=e.font;return{container:{flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start"},iconContainer:{padding:l.icon.small/2+1},checkedIcon:{position:"absolute",top:0,right:0},uncheckedIcon:{position:"absolute",top:1,right:1,width:l.icon.small,height:l.icon.small,borderRadius:l.icon.small/2,borderWidth:1,borderColor:t.accent.hrGray,backgroundColor:t.accent.bgGray},title:babelHelpers.extends({},i.large,{marginTop:l.vertical.tiny}),titleContainer:{flex:1},subtitle:babelHelpers.extends({},i.small,{marginTop:l.vertical.tiny}),action:babelHelpers.extends({},i.small,{marginTop:l.vertical.tiny,color:t.textLink}),profilePicContainer:{marginRight:l.vertical.small}}})},1862);