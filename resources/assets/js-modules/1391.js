__d(function(e,l,t,r){Object.defineProperty(r,"__esModule",{value:!0});var i=l(412),a=babelHelpers.interopRequireDefault(i),o=l(271),n=(babelHelpers.interopRequireDefault(o),l(44)),s=l(743),d=l(422),b=babelHelpers.interopRequireDefault(d),u=l(1392),p=babelHelpers.interopRequireDefault(u),x=(0,s.forbidExtraProps)({allowedOvershoot:a.default.number,body:a.default.string,maxSize:a.default.number,onExpandText:a.default.func,title:a.default.string,truncateBody:a.default.bool,titleInline:a.default.bool,large:a.default.bool,small:a.default.bool,children:a.default.node}),f={allowedOvershoot:void 0,body:null,maxSize:void 0,onExpandText:function(){},large:!1,titleInline:!1,truncateBody:!0},v=function(e){function l(){return babelHelpers.classCallCheck(this,l),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(l,e),l.prototype.renderInline=function(){var e=this.props,l=e.allowedOvershoot,t=e.body,r=e.maxSize,i=e.onExpandText,a=e.title,o=e.truncateBody,s=e.large;return babelHelpers.jsx(n.Text,{},void 0,!!a&&babelHelpers.jsx(n.Text,{style:[h.inlineTitle,s&&h.inlineTitleLarge]},void 0,a+"  "),!!t&&babelHelpers.jsx(n.Text,{style:h.inlineBody},void 0,babelHelpers.jsx(p.default,{large:s,text:t,expanded:!o,allowedOvershoot:l,maxSize:r,onExpandText:i})))},l.prototype.renderTitle=function(){var e=this.props,l=e.large,t=e.small;return babelHelpers.jsx(n.Text,{style:[h.title,l&&h.titleLarge,t&&h.titleSmall]},void 0,this.props.title)},l.prototype.render=function(){var e=this.props,l=e.allowedOvershoot,t=e.body,r=e.onExpandText,i=e.maxSize,a=e.title,o=e.truncateBody,s=e.titleInline,d=e.children,b=e.small,u=e.large;return babelHelpers.jsx(n.View,{},void 0,s&&this.renderInline(),!s&&babelHelpers.jsx(n.View,{},void 0,d&&babelHelpers.jsx(n.View,{style:h.viewWithChildren},void 0,!!a&&babelHelpers.jsx(n.View,{style:h.flex1},void 0,this.renderTitle()),babelHelpers.jsx(n.View,{},void 0,d)),!d&&!!a&&this.renderTitle(),!!t&&t.length>0&&babelHelpers.jsx(n.Text,{style:[h.body,b&&h.bodySmall]},void 0,babelHelpers.jsx(p.default,{large:u,maxSize:i,allowedOvershoot:l,text:t.trim(),expanded:!o,onExpandText:r}))))},l}(o.PureComponent);r.default=v;var h=b.default.create(function(e){var l=e.font,t=e.size;return{container:{overflow:"hidden"},title:babelHelpers.extends({},l.largePlus,{marginBottom:t.vertical.medium}),titleLarge:l.title3Plus,titleSmall:babelHelpers.extends({},l.regularPlus,{marginBottom:t.vertical.micro}),body:l.regular,bodySmall:l.small,viewWithChildren:{flexDirection:"row",alignItems:"flex-start"},inlineTitle:l.regularBold,inlineTitleLarge:l.largeBold,inlineBody:l.regular,flex1:{flex:1}}});v.defaultProps=f,v.propTypes=x},1391);