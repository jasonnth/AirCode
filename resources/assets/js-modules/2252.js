__d(function(e,l,t,r){Object.defineProperty(r,"__esModule",{value:!0});var a=l(412),s=babelHelpers.interopRequireDefault(a),i=l(271),n=(babelHelpers.interopRequireDefault(i),l(44)),o=l(815),b=babelHelpers.interopRequireDefault(o),p=l(2245),u=babelHelpers.interopRequireDefault(p),d=l(890),c=babelHelpers.interopRequireDefault(d),f=l(410),H=babelHelpers.interopRequireDefault(f),v=l(422),x=babelHelpers.interopRequireDefault(v),y={content:s.default.string,isMarkdown:s.default.bool,label:s.default.string,style:n.View.propTypes.style},R={isMarkdown:!1},h=function(e){function l(t){babelHelpers.classCallCheck(this,l);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return r.state={modalVisible:!1},r}return babelHelpers.inherits(l,e),l.prototype.render=function(){var e=this,l=this.props,t=l.label,r=l.content,a=l.isMarkdown,s=l.style;return babelHelpers.jsx(n.TouchableOpacity,{onPress:function(){e.setState({modalVisible:!0})},style:s},void 0,babelHelpers.jsx(c.default,{visible:this.state.modalVisible,onVisibleChange:function(l){return e.setState({modalVisible:l})}},void 0,babelHelpers.jsx(H.default.Screen,{barType:H.default.Screen.BAR_TYPE.BASIC}),babelHelpers.jsx(n.ScrollView,{style:j.content},void 0,a?babelHelpers.jsx(u.default,{content:r}):babelHelpers.jsx(n.Text,{},void 0,r))),babelHelpers.jsx(n.View,{style:j.iconRow},void 0,babelHelpers.jsx(b.default,{name:"description",size:16}),babelHelpers.jsx(n.Text,{style:j.label},void 0,t)))},l}(i.PureComponent);r.default=h,h.propTypes=y,h.defaultProps=R;var j=x.default.create(function(e){var l=e.size,t=e.font;return{iconRow:{alignItems:"center",flexDirection:"row",flexWrap:"wrap"},label:babelHelpers.extends({},t.largeActionablePlus),content:{padding:l.vertical.medium,paddingTop:l.vertical.large}}})},2252);