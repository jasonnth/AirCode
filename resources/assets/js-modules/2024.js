__d(function(e,t,r,i){Object.defineProperty(i,"__esModule",{value:!0});var n=t(379),o=babelHelpers.interopRequireDefault(n),a=t(412),u=babelHelpers.interopRequireDefault(a),s=t(271),l=(babelHelpers.interopRequireDefault(s),t(44)),p=t(790),c=t(884),d=babelHelpers.interopRequireDefault(c),f=t(1930),b=t(1936),m=t(2025),h=babelHelpers.interopRequireDefault(m),y=t(2023),R=babelHelpers.interopRequireDefault(y),H=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.props.receipt||this.props.fetchHomeReservationReceipt()},t.prototype.renderBody=function(){var e=this.props.receipt;if(!e||!e.price)return null;var t=e.price,r=t.items,i=t.total;return babelHelpers.jsx(l.View,{},void 0,r.map(function(e,t){return babelHelpers.jsx(d.default,{amountFormatted:e.total.amount_formatted,amount:e.total.amount,currency:e.total.currency,subtitle:e.subtitle,title:e.title},t)}),babelHelpers.jsx(d.default,{amount:i.amount,amountFormatted:i.amount_formatted,divider:"none",currency:i.currency,large:!0}))},t.prototype.getSubtitle=function(){var e=this.props,t=e.city,r=e.nights;return o.default.phrase("%{smart_count} night in %{city} |||| %{smart_count} nights in %{city}",{smart_count:r,city:t},"a subtitle for Home reservation receipt")},t.prototype.render=function(){var e=this.props.isLoading;return babelHelpers.jsx(R.default,{body:this.renderBody(),isLoading:e,title:o.default.phrase("Payment Breakdown",null,"a sheet header for Home reservation receipt"),subtitle:this.getSubtitle()})},t}(s.Component);H.contextTypes={theme:u.default.string},H.defaultProps={isLoading:!1},H.propTypes={city:u.default.string.isRequired,id:u.default.string.isRequired,isLoading:u.default.bool,fetchHomeReservationReceipt:u.default.func.isRequired,nights:u.default.number.isRequired,receipt:h.default},i.default=(0,p.connect)(function(e,t){return(0,b.homeReservationReceiptSheetSelector)(e,t)},function(e,t){return{fetchHomeReservationReceipt:function(){return e((0,f.fetchHomeReservationReceipt)(t.id))}}})(H)},2024);