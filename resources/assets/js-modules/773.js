__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(412),u=babelHelpers.interopRequireDefault(i),n=t(271),a=babelHelpers.interopRequireDefault(n),s=t(44),b=t(743),o=t(774),p=babelHelpers.interopRequireDefault(o),f=t(775),d=babelHelpers.interopRequireDefault(f),c=t(758),H=babelHelpers.interopRequireDefault(c),q=t(422),R=babelHelpers.interopRequireDefault(q),k=t(410),D=babelHelpers.interopRequireDefault(k),P=t(756),S=babelHelpers.interopRequireDefault(P),h=(0,b.forbidExtraProps)({barType:d.default,onSubtitleLinkPress:u.default.func,subtitle:u.default.string,subtitleLink:u.default.string,title:u.default.string.isRequired,padSubtitle:u.default.bool}),m={barType:D.default.Screen.BAR_TYPE.BASIC,onSubtitleLinkPress:function(){},subtitle:null,subtitleLink:null,padSubtitle:!0},y=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.onSubtitleLinkPress,l=e.subtitle,r=e.subtitleLink,i=e.padSubtitle;return a.default.createElement(p.default,(0,H.default)(this.props,p.default),(!!l||!!r)&&babelHelpers.jsx(s.View,{style:[i&&L.subtitleContainer]},void 0,babelHelpers.jsx(S.default,{},void 0,!!l&&l,!!r&&babelHelpers.jsx(S.default,{regularActionable:!0,onPress:t},void 0,"\xa0",r))))},t}(n.PureComponent);r.default=y,y.defaultProps=m,y.propTypes=h;var L=R.default.create(function(e){return{subtitleContainer:{paddingRight:10*e.bp}}});y.category="marquees/standard",y.examples=[{title:"Document Marquee",component:function(){return babelHelpers.jsx(y,{title:"Document marquee",subtitle:"Optional subtitle",subtitleLink:"Optional link",onSubtitleLinkPress:function(){}})}}]},773);