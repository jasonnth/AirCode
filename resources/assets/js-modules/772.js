__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var u=t(412),s=babelHelpers.interopRequireDefault(u),o=t(271),n=(babelHelpers.interopRequireDefault(o),t(773)),i=babelHelpers.interopRequireDefault(n),a=t(777),b=babelHelpers.interopRequireDefault(a),p=t(775),d=babelHelpers.interopRequireDefault(p),f=t(778),c=babelHelpers.interopRequireDefault(f),S=t(780),m=babelHelpers.interopRequireDefault(S),q=babelHelpers.extends({marquee:(0,b.default)(s.default.element,["title","barType","subtitle","subtitleLink","onSubtitleLinkPress"]),title:(0,b.default)(s.default.string,"marquee"),barType:(0,b.default)(d.default,"marquee"),subtitle:(0,b.default)(s.default.string,"marquee"),subtitleLink:(0,b.default)(s.default.string,"marquee"),onSubtitleLinkPress:(0,b.default)(s.default.func,"marquee"),padSubtitle:s.default.bool,bounces:s.default.bool,onMomentumScrollEnd:s.default.func,removeClippedSubviews:s.default.bool},m.default.propTypes),v={marquee:null,setScrollScreen:null,primaryButton:null,footer:null,bounces:!0,onMomentumScrollEnd:null,padSubtitle:!0,toastsUseRedux:!0,removeClippedSubviews:!0},H=function(e){function t(l){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,l));return r.setScrollScreen=r.setScrollScreen.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.setScrollScreen=function(e){this.scrollScreen=e,null!=this.props.setScrollScreen&&this.props.setScrollScreen(e)},t.prototype.render=function(){var e=this.props,t=e.marquee,l=e.title,r=e.barType,u=e.subtitle,s=e.subtitleLink,o=e.onSubtitleLinkPress,n=e.children,a=e.footer,b=e.setScrollScreen,p=e.primaryButton,d=e.toasts,f=e.toastsUseRedux,S=e.bounces,q=e.onMomentumScrollEnd,v=e.padSubtitle,H=e.removeClippedSubviews;return babelHelpers.jsx(m.default,{toasts:d,primaryButton:p,footer:a,setScrollScreen:b,bounces:S,onMomentumScrollEnd:q,toastsUseRedux:f},void 0,!t&&babelHelpers.jsx(i.default,{title:l,barType:r,subtitle:u,subtitleLink:s,onSubtitleLinkPress:o,padSubtitle:v}),t,babelHelpers.jsx(c.default,{removeClippedSubviews:H},void 0,n))},t}(o.PureComponent);r.default=H,H.propTypes=q,H.defaultProps=v},772);