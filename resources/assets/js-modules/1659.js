__d(function(e,r,t,l){Object.defineProperty(l,"__esModule",{value:!0});var a=r(751),p=babelHelpers.interopRequireDefault(a),s=r(379),u=babelHelpers.interopRequireDefault(s),i=r(412),o=babelHelpers.interopRequireDefault(i),b=r(271),n=(babelHelpers.interopRequireDefault(b),r(1660)),f=babelHelpers.interopRequireDefault(n),d=r(1661),h=r(1662),H=babelHelpers.interopRequireDefault(h),R=r(1668),c=babelHelpers.interopRequireDefault(R),v={reviews:o.default.arrayOf(c.default),tripTemplateId:o.default.number.isRequired},q=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.reviews,t=e.tripTemplateId;return babelHelpers.jsx(p.default,{},void 0,babelHelpers.jsx(f.default,{},void 0,u.default.phrase("Past guest photos",null,"photos from guests who have been on this trip")),babelHelpers.jsx(H.default,{photoUrls:(0,d.extractPhotosFromReviews)(r),tripTemplateId:t}))},r}(b.PureComponent);l.default=q,q.propTypes=v},1659);