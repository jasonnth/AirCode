__d(function(e,t,l,i){Object.defineProperty(i,"__esModule",{value:!0});var o=t(412),r=babelHelpers.interopRequireDefault(o),n=t(271),a=babelHelpers.interopRequireDefault(n),s=t(44),u=t(743),p=t(750),f=babelHelpers.interopRequireDefault(p),b=t(745),c=babelHelpers.interopRequireDefault(b),d=t(751),x=babelHelpers.interopRequireDefault(d),y=t(756),h=babelHelpers.interopRequireDefault(y),T=t(422),H=babelHelpers.interopRequireDefault(T),v=(0,u.forbidExtraProps)(babelHelpers.extends({},c.default,{divider:f.default,title:r.default.string.isRequired,subtitle:r.default.string,infoText:r.default.string.isRequired,onPress:r.default.func,onInfoPress:r.default.func})),L=function(e){function t(l,i){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,l,i));return o.onSpacerLayout=o.onSpacerLayout.bind(o),o.state={clipText:!1},o}return babelHelpers.inherits(t,e),t.prototype.onSpacerLayout=function(e){this.spacerWidth=e.nativeEvent.layout.width,this.state.clipText||0!==e.nativeEvent.layout.width||this.setState({clipText:!0})},t.prototype.render=function(){var e=this.props,t=e.title,l=e.subtitle,i=e.infoText,o=e.onInfoPress,r=babelHelpers.objectWithoutProperties(e,["title","subtitle","infoText","onInfoPress"]),n=this.state.clipText,u=babelHelpers.jsx(h.default,{large:!0,style:[g.infoText,n&&g.infoTextClipped,o&&g.infoTextLink],numberOfLines:1,ellipsizeMode:"tail",onLayout:this.onInfoTextLayout},void 0,i);return o&&(u=babelHelpers.jsx(s.TouchableOpacity,{onPress:o},void 0,u)),a.default.createElement(x.default,r,babelHelpers.jsx(s.View,{style:g.titleContainer,onLayout:this.onTitleContainerLayout},void 0,babelHelpers.jsx(h.default,{large:!0,style:[g.title,n&&g.titleClipped],numberOfLines:1,ellipsizeMode:"tail",onLayout:this.onTitleLayout},void 0,t),!n&&babelHelpers.jsx(s.View,{style:g.spacer,onLayout:this.onSpacerLayout}),u),!!l&&babelHelpers.jsx(h.default,{small:!0,style:g.subtitle},void 0,l))},t}(n.PureComponent);i.default=L,L.propTypes=v;var g=H.default.create(function(e){var t=e.color,l=e.size;return{titleContainer:{flexDirection:"row",flex:10},title:{marginRight:4},titleClipped:{flex:7},spacer:{flex:1},infoText:{textAlign:"right",color:t.regular.foggy},infoTextClipped:{flex:3},infoTextLink:{color:t.linkText},subtitle:{marginTop:l.vertical.tiny}}});L.category="content/rows",L.examples=[{title:"Info Row",component:function(){return babelHelpers.jsx(L,{title:"Info row",subtitle:"Optional subtitle",infoText:"Info text",onPress:function(){}})}}]},821);