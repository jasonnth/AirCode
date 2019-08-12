__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var a=t(377),i=babelHelpers.interopRequireDefault(a),n=t(562),s=babelHelpers.interopRequireDefault(n),o=t(379),u=babelHelpers.interopRequireDefault(o),p=t(412),d=babelHelpers.interopRequireDefault(p),f=t(271),b=(babelHelpers.interopRequireDefault(f),t(790)),c=t(44),v=t(1032),h=babelHelpers.interopRequireDefault(v),R=t(773),g=babelHelpers.interopRequireDefault(R),H=t(410),T=babelHelpers.interopRequireDefault(H),w=t(1840),D=babelHelpers.interopRequireDefault(w),y=t(778),q=babelHelpers.interopRequireDefault(y),S=t(787),m=babelHelpers.interopRequireDefault(S),x=t(910),j=babelHelpers.interopRequireDefault(x),C=t(422),I=babelHelpers.interopRequireDefault(C),E=t(1352),P=babelHelpers.interopRequireDefault(E),_=t(789),L=babelHelpers.interopRequireDefault(_),A=t(1795),k=t(1752),O=t(1788),B=babelHelpers.interopRequireDefault(O),V=t(1841),N=babelHelpers.interopRequireDefault(V),W=t(1790),G=t(1792),M=babelHelpers.interopRequireDefault(G),F=t(1791),U=babelHelpers.interopRequireDefault(F),Y=t(1797),z=t(1770),J=babelHelpers.interopRequireWildcard(z),K=t(1803),Q={loadStats:d.default.func.isRequired,isLoading:d.default.bool.isRequired,lastLoad:d.default.number.isRequired,averageReviewRating:d.default.number,payoutsTotalCount:d.default.number,reviewsTotalCount:d.default.number,totalEarned:M.default,totalPaid:M.default,tripTemplatesById:d.default.object.isRequired,unreviewedTripInstances:d.default.arrayOf(U.default)},X={averageReviewRating:null,payoutsTotalCount:null,reviewsTotalCount:null,totalEarned:null,totalPaid:null,unreviewedTripInstances:null},Z=function(e){var t=(0,K.statsSelector)(e),r=t.averageReviewRating,l=t.isLoading,a=t.lastLoad,i=t.payoutsTotalCount,n=t.reviewsTotalCount,s=t.totalEarned,o=t.totalPaid,u=(0,K.unreviewedTripInstancesSelector)(e);return{averageReviewRating:r,isLoading:l,lastLoad:a,payoutsTotalCount:i,reviewsTotalCount:n,totalEarned:s,totalPaid:o,tripTemplatesById:(0,K.tripTemplatesSelector)(e),unreviewedTripInstances:u}},$={loadStats:Y.loadStats},ee=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.shouldReload()&&this.props.loadStats()},t.prototype.shouldReload=function(){var e=this.props.lastLoad;return!e||s.default.unix(e).add(1,"hour").isBefore((0,s.default)())},t.prototype.renderReviewGuests=function(){var e=this.props,t=e.unreviewedTripInstances,r=e.tripTemplatesById;return t&&0!==t.length?babelHelpers.jsx(q.default,{},void 0,babelHelpers.jsx(j.default,{title:u.default.phrase("Leave reviews",null,"Section header for experience hosts to review guests")}),t.map(function(e){var t=(0,k.getDescription)(r[e.associatedTripTemplateId]).name||u.default.phrase("(Noname)",null,"placeholder for empty experience title"),l=(0,A.formatDateRange)(e.startsAt,e.endsAt);return babelHelpers.jsx(h.default,{title:t,subtitle:l,onPress:function(){return T.default.present(J.CONFIRM_GUEST_ATTENDANCE,{selectedTripInstanceId:e.id})}},e.id)})):null},t.prototype.renderEarnings=function(){var e=this.props,t=e.payoutsTotalCount,r=e.totalEarned,l=e.totalPaid,a=r?i.default.priceString(r.amount,r.currency):"0",n=l?i.default.priceString(l.amount,l.currency):"0",s=t&&t>0?u.default.phrase("Details",null,"button or link title for click to view details page"):null,o=t&&t>0?function(){return T.default.push(J.VIEW_PAYOUTS_LIST)}:null;return babelHelpers.jsx(c.View,{},void 0,babelHelpers.jsx(j.default,{first:!0,title:u.default.phrase("Total earnings",null,"section header for host to view earnings"),button:s,onButtonPress:o}),babelHelpers.jsx(D.default,{divider:"none",formattedNumberString:a,title:u.default.phrase("%{price} paid out",{price:n},"text to describe amount being paid to host")}))},t.prototype.renderReviews=function(){var e=this.props,t=e.averageReviewRating,r=e.reviewsTotalCount,l=r&&r>0?u.default.phrase("Details",null,"button or link title for click to view details page"):null,a=r&&r>0?function(){return T.default.push(J.VIEW_REVIEW_STATS)}:null;return babelHelpers.jsx(c.View,{},void 0,babelHelpers.jsx(j.default,{title:u.default.phrase("Overall rating",null,"section header for host to view review rating"),button:l,onButtonPress:a}),babelHelpers.jsx(N.default,{divider:"none",rating:t}))},t.prototype.render=function(){var e=this.props,t=e.averageReviewRating,r=e.isLoading,l=e.totalEarned,a=e.totalPaid,i=r||!l||!a||null===t;return babelHelpers.jsx(c.View,{style:te.container},void 0,babelHelpers.jsx(m.default,{style:te.container,refreshControl:babelHelpers.jsx(c.RefreshControl,{refreshing:!1,onRefresh:this.props.loadStats})},void 0,babelHelpers.jsx(g.default,{title:u.default.phrase("Stats",null,"page title for host to view trip hosting stats")}),i&&babelHelpers.jsx(B.default,{}),!i&&this.renderReviewGuests(),!i&&this.renderEarnings(),!i&&this.renderReviews()),babelHelpers.jsx(L.default,{},void 0,babelHelpers.jsx(P.default,{id:W.LOAD_STATS_FAILED_TOAST,description:u.default.phrase("Something went wrong when loading your stats",null,"text to tell user it fails to load stats"),action:u.default.phrase("Try again",null,"link title for click to retry"),onActionPress:this.props.loadStats})))},t}(f.PureComponent);ee.propTypes=Q,ee.defaultProps=X;var te=I.default.create(function(){return{container:{flex:1}}});l.default=(0,b.connect)(Z,$)(ee)},1839);