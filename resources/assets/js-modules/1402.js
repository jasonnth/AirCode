__d(function(e,r,l,o){Object.defineProperty(o,"__esModule",{value:!0});var t=r(412),a=babelHelpers.interopRequireDefault(t),i=r(271),n=babelHelpers.interopRequireDefault(i),b=r(44),c=r(815),s=babelHelpers.interopRequireDefault(c),u=r(422),d=babelHelpers.interopRequireDefault(u),p={checked:a.default.bool,label:a.default.string.isRequired,onToggleCheckbox:a.default.func},h={checked:!1,onToggleCheckbox:function(){}},f=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.checked,l=e.label,o=e.onToggleCheckbox,t=d.default.theme.color;return babelHelpers.jsx(b.TouchableHighlight,{underlayColor:t.white,onPress:o},void 0,babelHelpers.jsx(b.View,{style:g.container},void 0,babelHelpers.jsx(b.Text,{style:g.label},void 0,l),babelHelpers.jsx(b.View,{style:[g.checkbox,r&&g.checkboxChecked]},void 0,babelHelpers.jsx(s.default,{name:"ok-alt",color:r?t.core.babu:t.white}))))},r}(n.default.Component);o.default=f,f.propTypes=p,f.defaultProps=h;var g=d.default.create(function(e){var r=e.color,l=e.font,o=e.size;return{container:{alignItems:"center",flexDirection:"row",justifyContent:"space-between",marginHorizontal:o.baseRow.paddingHorizontal,overflow:"hidden"},checkbox:{alignItems:"center",borderColor:"#9B9B9B",borderRadius:o.vertical.micro,borderWidth:1,height:o.vertical.medium,justifyContent:"center",marginVertical:o.vertical.small,width:o.horizontal.medium},checkboxChecked:{borderColor:r.core.babu},label:l.large}})},1402);