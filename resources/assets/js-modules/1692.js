__d(function(e,l,i,r){Object.defineProperty(r,"__esModule",{value:!0});var o=l(751),a=babelHelpers.interopRequireDefault(o),t=l(750),n=babelHelpers.interopRequireDefault(t),s=l(379),u=babelHelpers.interopRequireDefault(s),p=l(756),b=babelHelpers.interopRequireDefault(p),d=l(410),f=babelHelpers.interopRequireDefault(d),c=l(271),R=babelHelpers.interopRequireDefault(c),H=l(899),O=babelHelpers.interopRequireDefault(H),_=l(1660),g=babelHelpers.interopRequireDefault(_),h=l(1607),D=babelHelpers.interopRequireDefault(h),P=l(1608),A=l(1609),q=l(1569),v=babelHelpers.interopRequireWildcard(q),C=l(1634),m=babelHelpers.interopRequireDefault(C),x={tripTemplate:m.default.isRequired,divider:n.default},G={divider:null},y=function(e){function l(){return babelHelpers.classCallCheck(this,l),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(l,e),l.prototype.render=function(){var e=this.props,l=e.divider,i=e.tripTemplate,r=i.social_good_organization,o=i.description_native,t=o&&o.contribution;return babelHelpers.jsx(a.default,{divider:l},void 0,babelHelpers.jsx(g.default,{},void 0,babelHelpers.jsx(b.default,{largeActionablePlus:!0,onPress:function(){(0,A.logPDPClickInfoSection)(P.ABOUT_SOCIAL_GOOD_PROGRAM,i),f.default.push(v.SOCIAL_IMPACT_PROGRAM,{socialGoodOrganization:r,programming:t})}},void 0,u.default.phrase("Social impact experience",null,"Section header for information about a social good experience"),"\xa0",D.default["social-good"])),babelHelpers.jsx(b.default,{},void 0,babelHelpers.jsx(O.default,{default:"100% of what you pay for this experience goes to %{socialGoodOrganization}. %{link_start}Learn how your money helps.%{link_end}",link:babelHelpers.jsx(b.default,{regularActionable:!0,onPress:function(){(0,A.logPDPClickInfoSection)(P.ABOUT_SOCIAL_GOOD_PROGRAM,i),f.default.push(v.SOCIAL_IMPACT_PROGRAM,{socialGoodOrganization:r,programming:t})}}),socialGoodOrganization:r,context:"Describes how funds will be used for social impact experiences."})))},l}(R.default.Component);r.default=y,y.propTypes=x,y.defaultProps=G},1692);