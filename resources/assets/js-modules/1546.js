__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var n=r(412),a=babelHelpers.interopRequireDefault(n),o=r(271),u=(babelHelpers.interopRequireDefault(o),r(44)),s=r(377),i=babelHelpers.interopRequireDefault(s),p=r(705),b=babelHelpers.interopRequireDefault(p),d=r(422),c=babelHelpers.interopRequireDefault(d),f={amount:a.default.number,currency:a.default.string,amountFormatted:a.default.string,showSign:a.default.bool},h={showSign:!1,amountFormatted:null},m=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.amount,t=e.currency,l=e.amountFormatted,n=e.showSign,a=null;return a=b.default.launch("rn.dollars_to_cents")&&null!=l?l:i.default.priceString(n?Math.abs(r):r,t),babelHelpers.jsx(u.View,{style:y.container},void 0,n&&babelHelpers.jsx(u.Text,{style:y.sign},void 0,null==l&&0!==r&&(r>0?"+":"-")),babelHelpers.jsx(u.Text,{style:y.amount},void 0,a),babelHelpers.jsx(u.Text,{style:y.currency},void 0,t))},r}(o.PureComponent);l.default=m,m.defaultProps=h,m.propTypes=f;var y=c.default.create(function(e){var r=e.font;return{container:{overflow:"hidden",flexDirection:"row",alignItems:"flex-start"},amount:r.header2,currency:babelHelpers.extends({marginTop:6},r.header5),symbol:r.header2,sign:r.header2}})},1546);