__d(function(e,r,t,i){Object.defineProperty(i,"__esModule",{value:!0});var l=r(271),o=babelHelpers.interopRequireDefault(l),a=r(44),n=r(834),s=r(790),p=r(654),u=r(562),b=babelHelpers.interopRequireDefault(u),d=r(379),c=babelHelpers.interopRequireDefault(d),f=r(1023),v=r(841),R=babelHelpers.interopRequireDefault(v),h=r(773),w=babelHelpers.interopRequireDefault(h),H=r(410),m=babelHelpers.interopRequireDefault(H),q=r(778),T=babelHelpers.interopRequireDefault(q),S=r(1701),g=babelHelpers.interopRequireDefault(S),y=r(787),D=babelHelpers.interopRequireDefault(y),M=r(1573),_=babelHelpers.interopRequireWildcard(M),E=r(1609),x=r(1608),C=babelHelpers.interopRequireWildcard(x),P=r(1634),j=babelHelpers.interopRequireDefault(P),F=r(1578),I=r(1668),O=babelHelpers.interopRequireDefault(I),W=r(1482),k=babelHelpers.interopRequireWildcard(W),A=function(e,r){var t=r.tripTemplateId;return{isFetchingMoreReviews:(0,F.rootSelector)(e).get("isFetchingMoreReviews"),reviews:(0,F.reviewsSelector)(e,t),tripTemplate:(0,F.tripTemplateSelector)(e,t)}},U=function(e){return(0,p.bindActionCreators)({loadMoreReviews:_.loadMoreReviews},e)},V={isFetchingMoreReviews:n.Types.bool.isRequired,loadMoreReviews:n.Types.func.isRequired,reviews:n.Types.arrayOf(O.default),tripTemplate:j.default.isRequired,tripTemplateId:n.Types.number.isRequired},z={reviews:[]},B=function(e){function r(t,i){babelHelpers.classCallCheck(this,r);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,t,i));return l.onScroll=l.onScroll.bind(l),l}return babelHelpers.inherits(r,e),r.prototype.onScroll=function(e){var r=this.props,t=r.isFetchingMoreReviews,i=r.reviews,l=r.tripTemplate,o=r.tripTemplateId;if(!(t||i.length>=l.review_count)){e.nativeEvent.contentOffset.y+e.nativeEvent.layoutMeasurement.height+500>=e.nativeEvent.contentSize.height&&this.props.loadMoreReviews(o,i.length)}},r.prototype.render=function(){var e=this.props,r=e.isFetchingMoreReviews,t=e.reviews,i=e.tripTemplate;return babelHelpers.jsx(D.default,{onScroll:this.onScroll,scrollEventThrottle:200},void 0,babelHelpers.jsx(w.default,{title:c.default.phrase("Reviews",null,"title for reviews sheet")}),babelHelpers.jsx(T.default,{},void 0,t.map(function(e){var r=e.author,t=r.id,l=r.picture_url,o=r.first_name,a=e.comments,n=e.created_at;return babelHelpers.jsx(g.default,{onExpandText:function(){(0,E.logPDPClickInfoSection)(C.REVIEW_READ_MORE,i)},reviewerPictureUrl:l,reviewerName:o,reviewText:a,reviewerSubtitle:(0,b.default)(n).format("ll"),onProfilePress:function(){return m.default.push(k.USER_PROFILE,{user_id:t})}},t+","+n)})),r&&babelHelpers.jsx(a.View,{style:L.loaderContainer},void 0,babelHelpers.jsx(R.default,{})))},r}(o.default.Component),L=f.ThemedStyleSheet.create(function(e){var r=e.bp;return{loaderContainer:{paddingTop:2*r,paddingBottom:4*r}}});B.defaultProps=z,B.propTypes=V,i.default=(0,s.connect)(A,U)(B)},1727);