__d(function(e,t,r,i){Object.defineProperty(i,"__esModule",{value:!0});var a=t(377),l=babelHelpers.interopRequireDefault(a),s=t(379),n=babelHelpers.interopRequireDefault(s),o=t(412),u=babelHelpers.interopRequireDefault(o),p=t(271),d=(babelHelpers.interopRequireDefault(p),t(790)),v=t(44),b=t(778),c=babelHelpers.interopRequireDefault(b),f=t(1388),R=babelHelpers.interopRequireDefault(f),w=t(773),g=babelHelpers.interopRequireDefault(w),m=t(410),H=babelHelpers.interopRequireDefault(m),T=t(787),h=babelHelpers.interopRequireDefault(T),I=t(422),y=babelHelpers.interopRequireDefault(I),q=t(1844),C=babelHelpers.interopRequireDefault(q),D=t(1845),P=babelHelpers.interopRequireDefault(D),A=t(1846),_=babelHelpers.interopRequireDefault(A),j=t(1847),x=babelHelpers.interopRequireDefault(j),S=t(1795),B=t(1752),E=t(1770),k=babelHelpers.interopRequireWildcard(E),N=t(1803),V={averageReviewRating:u.default.number.isRequired,reviewCountsByRating:u.default.object.isRequired,reviewsTotalCount:u.default.number.isRequired,lastReview:x.default,lastReviewAssociatedGuestProfile:u.default.any,lastReviewAssociatedTripTemplate:u.default.any,lastReviewAssociatedTripInstance:u.default.any},W={},L=function(e){var t=(0,N.statsSelector)(e),r=t.averageReviewRating,i=t.lastReviewId,a=t.reviewCountsByRating,l=t.reviewsTotalCount,s=(0,N.reviewsSelector)(e),n=(0,N.tripInstancesSelector)(e),o=(0,N.tripTemplatesSelector)(e),u=(0,N.guestProfilesSelector)(e),p={};if(i){var d=s[i];p={lastReview:d,lastReviewAssociatedGuestProfile:u[d.guestProfileId],lastReviewAssociatedTripTemplate:o[d.associatedTripTemplateId],lastReviewAssociatedTripInstance:n[d.associatedTripInstanceId]}}return babelHelpers.extends({},p,{averageReviewRating:r,reviewCountsByRating:a,reviewsTotalCount:l})},G=function(e){function t(r){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return i.viewReviews=i.viewReviews.bind(i),i}return babelHelpers.inherits(t,e),t.prototype.viewReviews=function(e){var t=this.props,r=t.reviewCountsByRating,i=t.reviewsTotalCount,a=e?r[e]:i;a&&a>0&&H.default.push(k.VIEW_REVIEWS_LIST,{selectedReviewRating:e})},t.prototype.renderLastReview=function(){var e=this.props,t=e.lastReview,r=e.lastReviewAssociatedGuestProfile,i=e.lastReviewAssociatedTripTemplate,a=e.lastReviewAssociatedTripInstance,s=t.associatedTripInstanceId,o=t.nativePayout,u=a.endsAt,p=a.startsAt,d=i.market.timezone,v=o.amount,b=o.currency,c=l.default.priceString(v,b);return babelHelpers.jsx(P.default,{guestImage:r.pictureUrl,guestName:r.firstName,privateReview:t.privateFeedback,publicReview:t.comments,starRating:t.rating,tripPrice:c,tripTime:(0,S.formatDateRange)(p,u,d),tripTitle:(0,B.getDescription)(i).name||n.default.phrase("(Noname)",null,"placeholder for empty experience title"),onPress:function(){return H.default.push(k.VIEW_TRIP_INSTANCE,{tripInstanceIdFromEntryPoint:s})}},t.id)},t.prototype.render=function(){var e=this,t=this.props,r=t.averageReviewRating,i=t.lastReview,a=t.reviewCountsByRating,l=t.reviewsTotalCount,s=[5,4,3,2,1],o=s.map(function(e){return a[e]});return babelHelpers.jsx(h.default,{style:z.container},void 0,babelHelpers.jsx(g.default,{title:n.default.phrase("%{smart_count} review |||| %{smart_count} reviews",{smart_count:l},"page title to describe number of reviews")}),babelHelpers.jsx(c.default,{},void 0,babelHelpers.jsx(C.default,{totalCount:l,ratingCounts:o,ratingLabels:s,onRatingPress:this.viewReviews,divider:"none"}),babelHelpers.jsx(v.View,{style:z.divider}),babelHelpers.jsx(_.default,{rating:r,title:n.default.phrase("Overall",null,"row title to describe the average review rating of the host"),divider:"none"}),i&&this.renderLastReview(),babelHelpers.jsx(R.default,{title:n.default.phrase("View all reviews",null,"link title for host to click and view all reviews received"),onPress:function(){return e.viewReviews(null)}})))},t}(p.PureComponent);G.propTypes=V,G.defaultProps=W;var z=y.default.create(function(e){var t=e.bp;return{divider:{borderBottomColor:e.color.divider,borderBottomWidth:1,marginHorizontal:3*t}}});i.default=(0,d.connect)(L)(G)},1843);