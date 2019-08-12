__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var i=t(44),a=t(1023),n=t(834),o=t(906),s=babelHelpers.interopRequireDefault(o),p=t(751),u=babelHelpers.interopRequireDefault(p),d=t(420),b=babelHelpers.interopRequireDefault(d),f=t(379),c=babelHelpers.interopRequireDefault(f),m=t(1390),h=babelHelpers.interopRequireDefault(m),_=t(271),x=babelHelpers.interopRequireDefault(_),H=t(778),T=babelHelpers.interopRequireDefault(H),I=t(765),y=babelHelpers.interopRequireDefault(I),R=t(1680),v=t(1686),D=t(1609),E=t(1669),L=babelHelpers.interopRequireDefault(E),g=t(1687),S=babelHelpers.interopRequireDefault(g),W=t(1690),j=babelHelpers.interopRequireDefault(W),C=t(1691),w=babelHelpers.interopRequireDefault(C),O=t(1692),A=babelHelpers.interopRequireDefault(O),P=t(1608),q=babelHelpers.interopRequireWildcard(P),M={scheduledTripTemplate:n.Types.object,tripTemplate:n.Types.object.isRequired,showAboutHost:n.Types.bool},N={scheduledTripTemplate:null,showAboutHost:!1},z=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.renderLabeledLine=function(e,t){return babelHelpers.jsx(i.View,{style:J.labeledLine},void 0,babelHelpers.jsx(i.Text,{style:J.labeledLineMoji},void 0,""+(0,o.getMoji)(e)),babelHelpers.jsx(i.Text,{style:J.labeledLineText},void 0,t))},t.prototype.renderListedSummary=function(e){var t=this.props,l=t.scheduledTripTemplate,r=t.tripTemplate,a=r.offered_languages,n=!e&&l&&(0,R.displayImmersionDurationXExperiencesOverYDays)(r,l),o=(0,R.displayHoursTotal)(r),p=(0,y.default)(r.experiences.map(function(e){return e.amenities})),d=(0,v.enumerateAllAmenities)(p),b=(0,v.offeredLanguagesDescription)(a);return babelHelpers.jsx(i.View,{style:J.listedSummaryContainer},void 0,babelHelpers.jsx(u.default,{},void 0,babelHelpers.jsx(i.View,{style:J.listedSummaryInnerContainer},void 0,!e&&n&&this.renderLabeledLine(s.default.AIRMOJIS.AIRMOJI_DESCRIPTION_CALENDAR,n),this.renderLabeledLine(s.default.AIRMOJIS.AIRMOJI_DESCRIPTION_CLOCK,o),d&&this.renderLabeledLine(s.default.AIRMOJIS.AIRMOJI_DESCRIPTION_MENU,d),b&&this.renderLabeledLine(s.default.AIRMOJIS.AIRMOJI_DESCRIPTION_DIALOG,b))))},t.prototype.renderSocialGoodRows=function(e){var t=this.props.tripTemplate,l=t.description_native,r=t.social_good_organization,a=t.is_social_good,n=l&&l.about_organization,o=a&&r&&n;return n?babelHelpers.jsx(i.View,{},void 0,o&&!!n&&babelHelpers.jsx(w.default,{description:n,organizationName:r,isSingleExperience:e,tripTemplate:t}),a&&!!t.social_good_organization&&babelHelpers.jsx(A.default,{tripTemplate:t})):null},t.prototype.renderWhatToExpect=function(){var e=this.props.tripTemplate,t=e.description_native,l=t&&t.what_to_expect,r=c.default.phrase("What to expect",null,"Header for a blurb about what to expect on this trip");return l?babelHelpers.jsx(h.default,{title:r,body:l,maxSize:b.default.isTablet?250:150,onExpandText:function(){(0,D.logPDPClickInfoSection)(q.WHAT_TO_EXPECT,e)}}):null},t.prototype.renderWhatWeWillDo=function(){var e=this.props.tripTemplate,t=e.experiences[0].description_native,l=t&&t.what_you_will_do,r=c.default.phrase("What we\u2019ll do",null,"a description of all the activities the guests on the trip will do");return l?babelHelpers.jsx(h.default,{title:r,body:l,maxSize:b.default.isTablet?250:150,onExpandText:function(){(0,D.logPDPClickInfoSection)(q.SINGLE_EXPERIENCE_WHAT_WE_WILL_DO,e)}}):null},t.prototype.renderWhereWeWillBe=function(){var e=this.props.tripTemplate,t=e.experiences[0].description_native,l=t&&t.where_i_will_take_you,r=c.default.phrase("Where we\u2019ll be",null,"the location of where all the activities take place");return l?babelHelpers.jsx(h.default,{title:r,body:l,maxSize:b.default.isTablet?250:150,onExpandText:function(){(0,D.logPDPClickInfoSection)(q.SINGLE_EXPERIENCE_WHERE_WE_WILL_GO,e)}}):null},t.prototype.render=function(){var e=this.props,t=e.showAboutHost,l=e.tripTemplate,r=l.product_type,i=l.experiences,a=1===r;return babelHelpers.jsx(T.default,{divider:a?"none":"padded"},void 0,this.renderListedSummary(a),t&&babelHelpers.jsx(L.default,{tripTemplate:l,isSingleExperience:a}),a&&this.renderWhatWeWillDo(),a&&babelHelpers.jsx(S.default,{amenities:i[0].amenities}),a&&babelHelpers.jsx(j.default,{notes:i[0].notes,amenities:i[0].amenities}),a&&this.renderWhereWeWillBe())},t}(x.default.Component);r.default=z;var J=a.ThemedStyleSheet.create(function(e){var t=e.bp,l=e.font,r=e.size;return{title:babelHelpers.extends({},l.largePlus,{marginBottom:r.vertical.medium}),body:l.regular,labeledLine:{flexDirection:"row",paddingBottom:r.vertical.small},labeledLineMoji:babelHelpers.extends({},l.regular,{width:2.5*t}),labeledLineText:babelHelpers.extends({},l.regular,{flex:1,paddingLeft:1*t}),listedSummaryContainer:{marginTop:3*-t},listedSummaryInnerContainer:{marginBottom:-r.vertical.small}}});z.propTypes=M,z.defaultProps=N},1679);