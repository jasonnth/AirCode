__d(function(e,t,i,r){Object.defineProperty(r,"__esModule",{value:!0});var l=t(412),n=babelHelpers.interopRequireDefault(l),o=t(271),a=(babelHelpers.interopRequireDefault(o),t(44)),s=t(743),b=t(422),u=babelHelpers.interopRequireDefault(b),c=t(911),d=babelHelpers.interopRequireDefault(c),p=t(425),H=babelHelpers.interopRequireDefault(p),f=(0,s.forbidExtraProps)({title:n.default.string.isRequired,subtitle:n.default.string,button:n.default.string,onButtonPress:n.default.func,babuButton:n.default.bool,first:n.default.bool,micro:n.default.bool,heavy:n.default.bool,kicker:n.default.bool,inverse:n.default.bool}),v={first:!1,micro:!1,heavy:!1,kicker:!1,inverse:!1,babuButton:!1},x=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.micro,i=e.heavy,r=e.title,l=e.subtitle,n=e.first,o=e.button,s=e.onButtonPress,b=e.babuButton,u=e.kicker,c=e.inverse;return babelHelpers.jsx(a.View,{style:y.outer},void 0,!n&&babelHelpers.jsx(a.View,{style:y.topPadding}),babelHelpers.jsx(a.View,{style:y.container},void 0,babelHelpers.jsx(a.View,{style:y.row},void 0,babelHelpers.jsx(a.View,{style:y.titleContainer},void 0,babelHelpers.jsx(a.Text,{style:[y.title,c&&y.titleInverse,t&&y.titleMicro,i&&y.titleHeavy,i&&c&&y.titleInverseHeavy,t&&i&&y.titleMicroHeavy,u&&y.titleKicker]},void 0,r)),!!o&&babelHelpers.jsx(a.View,{style:y.buttonContainer},void 0,babelHelpers.jsx(d.default,{border:!1,onPress:s,chevron:!0,color:b?H.default.core.babu:void 0},void 0,o))),!!l&&babelHelpers.jsx(a.View,{style:y.subtitleContainer},void 0,babelHelpers.jsx(a.Text,{style:[y.subtitle,c&&y.subtitleInverse]},void 0,l))))},t}(o.PureComponent);r.default=x,x.defaultProps=v,x.propTypes=f;var y=u.default.create(function(e){var t=e.font,i=e.size,r=e.bp;return{outer:{overflow:"hidden"},container:{paddingHorizontal:i.baseRow.paddingHorizontal},row:{flex:1,flexDirection:"row",justifyContent:"space-between",alignItems:"flex-start",paddingTop:i.vertical.medium},titleContainer:{flex:1},title:babelHelpers.extends({},t.title3,{flexWrap:"wrap",flex:1}),titleInverse:t.title3Inverse,titleMicro:babelHelpers.extends({},t.large,{color:H.default.regular.foggy}),titleHeavy:t.title3Bold,titleInverseHeavy:t.title3InversePlus,titleMicroHeavy:t.largePlus,buttonContainer:{flex:0,marginLeft:1.25*r},buttonBabu:{color:H.default.core.babu},subtitleContainer:{paddingTop:i.vertical.tiny},subtitle:t.small,subtitleInverse:t.smallInverse,topPadding:{height:i.vertical.small},titleKicker:t.kickerBold}});x.category="content/sectionHeaders",x.examples=[{title:"Standard Section Header",component:function(){return babelHelpers.jsx(x,{title:"Section Header"})}},{title:"First Section Header",component:function(){return babelHelpers.jsx(x,{first:!0,title:"First Section Header"})}},{title:"Heavy First Section Header",component:function(){return babelHelpers.jsx(x,{first:!0,heavy:!0,title:"Heavy First Section Header"})}},{title:"Wrapping Text",component:function(){return babelHelpers.jsx(x,{title:"Section Header with really really really long text"})}},{title:"Micro Section Header",component:function(){return babelHelpers.jsx(x,{micro:!0,title:"Micro Section Header"})}},{title:"Micro First Section Header",component:function(){return babelHelpers.jsx(x,{micro:!0,first:!0,title:"Micro First Section Header"})}},{title:"Micro First Heavy Section Header",component:function(){return babelHelpers.jsx(x,{micro:!0,first:!0,heavy:!0,title:"Micro First Heavy Section Header"})}},{title:"Section Header with subtitle",component:function(){return babelHelpers.jsx(x,{title:"Section Header",subtitle:"Section Headers can also have a title"})}},{title:"Section Header with title and button",component:function(){return babelHelpers.jsx(x,{title:"Section Header",subtitle:"Section Headers can also have a title",button:"See all"})}},{title:"Section Header with title and babu button",component:function(){return babelHelpers.jsx(x,{title:"Section Header",subtitle:"Section Headers can also have a title",button:"See all",babuButton:!0})}}]},910);