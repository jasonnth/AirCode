__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var i=t(412),a=babelHelpers.interopRequireDefault(i),n=t(271),s=babelHelpers.interopRequireDefault(n),o=t(44),p=t(422),b=babelHelpers.interopRequireDefault(p),u=t(410),d=babelHelpers.interopRequireDefault(u),f=t(776),H=babelHelpers.interopRequireDefault(f),c=t(2416),x=babelHelpers.interopRequireDefault(c),v=t(2418),R=babelHelpers.interopRequireDefault(v),C=t(2414),T={title:a.default.string.isRequired,trip:C.TripPropShape,isHostView:a.default.bool},g={trip:null,isHostView:!1},h=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.trip,r=e.title,l=e.isHostView;return babelHelpers.jsx(o.View,{},void 0,babelHelpers.jsx(d.default.Screen,{barType:d.default.Screen.BAR_TYPE.OVERLAY}),babelHelpers.jsx(o.View,{style:q.marqueeContainer},void 0,babelHelpers.jsx(H.default,{}),babelHelpers.jsx(o.View,{style:q.titleTextContainer},void 0,babelHelpers.jsx(o.Text,{style:q.titleText},void 0,r),babelHelpers.jsx(o.View,{style:q.childrenContainer},void 0,t&&!l&&s.default.createElement(x.default,babelHelpers.extends({},t,{inverse:!0,divider:"none"})),t&&l&&s.default.createElement(R.default,babelHelpers.extends({},t,{inverse:!0,divider:"none"}))))))},t}(n.PureComponent);l.default=h,h.defaultProps=g,h.propTypes=T;var q=b.default.create(function(e){var t=e.bp,r=e.font,l=e.size;return{marqueeContainer:{backgroundColor:e.color.core.babu,paddingHorizontal:l.baseRow.paddingHorizontal,paddingTop:20+7*t,flexDirection:"column"},titleTextContainer:{},titleText:babelHelpers.extends({},r.title2Inverse,{textAlign:"left"}),childrenContainer:{marginLeft:-3*t,marginRight:-3*t,marginTop:-1*t,flex:2}}})},2428);