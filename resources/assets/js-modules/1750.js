__d(function(e,t,o,r){function i(){h.default.dismiss(),(0,L.default)(N.TRIPS)}function a(){E.default.presentIdentityFlow(E.default.VERIFICATION_FLOW.MAGICAL_TRIPS_BOOKING).then(function(e){e&&i()})}Object.defineProperty(r,"__esModule",{value:!0});var n=t(271),l=babelHelpers.interopRequireDefault(n),s=t(44),u=t(834),d=t(790),c=t(654),p=t(787),f=babelHelpers.interopRequireDefault(p),b=t(410),h=babelHelpers.interopRequireDefault(b),y=t(379),m=babelHelpers.interopRequireDefault(y),v=t(811),g=babelHelpers.interopRequireDefault(v),x=t(1023),T=t(1640),H=babelHelpers.interopRequireDefault(T),w=t(841),_=babelHelpers.interopRequireDefault(w),k=t(1390),R=babelHelpers.interopRequireDefault(k),S=t(839),I=babelHelpers.interopRequireDefault(S),q=t(562),D=babelHelpers.interopRequireDefault(q),C=t(1751),j=t(1749),U=t(1657),B=t(376),E=babelHelpers.interopRequireDefault(B),F=t(1578),P=t(1573),V=t(1636),M=babelHelpers.interopRequireDefault(V),Y=t(1754),N=babelHelpers.interopRequireWildcard(Y),O=t(1755),L=babelHelpers.interopRequireDefault(O),A=Math.min(250,Math.round((0,U.getWindowDimension)().height/3)),W={additionalTravelers:u.Types.array.isRequired,currentUser:M.default,loadUser:u.Types.func,loadUserFailed:u.Types.bool,tripTemplate:u.Types.object.isRequired,scheduledTripTemplate:u.Types.object.isRequired,templateId:u.Types.number.isRequired},z={currentUser:null,loadUser:function(){},loadUserFailed:!1},G=function(e){return{additionalTravelers:(0,F.additionalTravelersSelector)(e),tripTemplate:(0,F.rootSelector)(e).get("bookingData").tripTemplate,scheduledTripTemplate:(0,F.rootSelector)(e).get("bookingData").scheduledTripTemplate,currentUser:(0,F.currentUserSelector)(e),loadUserFailed:(0,F.currentUserFailedToLoadSelector)(e)}},K=function(e){return{loadUser:(0,c.bindActionCreators)(P.loadUser,e)}},J=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.props.loadUser("me")},t.prototype.render=function(){var e=this.props,t=e.additionalTravelers,o=e.currentUser,r=e.loadUserFailed,n=e.tripTemplate,l=e.scheduledTripTemplate,u=(0,C.experienceTemplatePhoto)(n.experiences[0])||(0,C.tripTemplatePhoto)(n),d=n.experience_host_profile.host.first_name,c=n.title,p=m.default.phrase("It\u2019s official! Get ready for %{tripTitle}.",{hostName:d,tripTitle:c},"Header for screen shown after a booking is confirmed"),b=l.scheduled_experiences.sort(function(e,t){return(0,D.default)(e.starts_at).diff(t.starts_at,"minutes")}),y=n.booking_lead_hours,v=(0,D.default)(b[0].starts_at),x=v.diff((0,D.default)(),"hours")-y+2,T=y>=72?3:Math.floor(Math.min(x/24,3)),w=void 0;w=t.length>0?T>=1?m.default.phrase("Everyone in your group has to complete our verification process before going on an experience. You have %{smart_count} day to do this, if not your experience will be canceled. |||| Everyone in your group has to complete our verification process before going on an experience. You have %{smart_count} days to do this, if not your experience will be canceled.",{smart_count:T},"Shown to multi guest booker after booking confirmation before redirect to complete verification if they have at least one day to do so."):m.default.phrase("Everyone in your group has to complete our verification process before going on an experience. You have %{smart_count} hour to do this, if not your experience will be canceled. |||| Everyone in your group has to complete our verification process before going on an experience. You have %{smart_count} hours to do this, if not your experience will be canceled.",{smart_count:x},"Shown to multi guest booker after booking confirmation before redirect to complete verification if they have less than a day to do so."):T>=1?m.default.phrase("Everyone has to complete our verification process before going on an experience. You have %{smart_count} day to do this, if not your experience will be canceled. |||| Everyone has to complete our verification process before going on an experience. You have %{smart_count} days to do this, if not your experience will be canceled.",{smart_count:T},"Shown to multi guest booker after booking confirmation before redirect to complete verification if they have at least one day to do so."):m.default.phrase("Everyone has to complete our verification process before going on an experience. You have %{smart_count} hour to do this, if not your experience will be canceled. |||| Everyone has to complete our verification process before going on an experience. You have %{smart_count} hours to do this, if not your experience will be canceled.",{smart_count:x},"Shown to multi guest booker after booking confirmation before redirect to complete verification if they have less than a day to do so.");var k=(0,j.requireIdVerification)(n,o),S=k?m.default.phrase("Next, view your itinerary",null,"Section header for viewing your itinerary"):m.default.phrase("View your itinerary",null,"Section header for viewing your itinerary"),I=m.default.phrase("Check out the times and details for your experiences. And message your host to say hi!",null,"Shown to user after they have completed verified ID and can view the itinerary");return o?babelHelpers.jsx(s.View,{style:s.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(h.default.Screen,{barType:"basic",leadingButtonVisible:!1,leftIcon:h.default.Screen.LEFT_ICON.NONE}),babelHelpers.jsx(f.default,{withInsets:[H.default]},void 0,babelHelpers.jsx(s.View,{},void 0,babelHelpers.jsx(s.Image,{source:(0,g.default)(u),style:Q.coverImage,resizeMode:"cover"}),babelHelpers.jsx(s.View,{style:Q.textContent},void 0,babelHelpers.jsx(s.View,{style:Q.titleContainer},void 0,babelHelpers.jsx(s.Text,{style:Q.title},void 0,p))),(k||r)&&babelHelpers.jsx(R.default,{truncateBody:!1,title:m.default.phrase("First, complete verification",null,"Section header for complete verification info"),body:w,divider:k||r?"padded":"none"}),babelHelpers.jsx(R.default,{truncateBody:!1,title:S,body:I,divider:"none"}))),babelHelpers.jsx(s.View,{style:Q.buttonContainer},void 0,babelHelpers.jsx(H.default,{buttonText:m.default.phrase("Continue",null,"Button text to finish booking and go to ID flow"),onButtonPress:k?a:i,title:k?m.default.phrase("Skip for now",null,"Skip this step for now as in skip the verified id step and come back later"):null,onInfoTextPress:i,infoTextLinkLike:!0}))):babelHelpers.jsx(_.default,{})},t}(l.default.Component),Q=x.ThemedStyleSheet.create(function(e){var t=e.font,o=e.color,r=e.bp,i=e.size;return{buttonContainer:I.default.footer,contentHeader:{paddingBottom:r},contentHeaderText:t.regularPlus,firstContentRow:{paddingBottom:i.vertical.small},coverImage:{backgroundColor:o.coverImageBackground,height:A},titleContainer:{paddingBottom:i.vertical.small},title:t.title2,textContent:{paddingTop:3*r,paddingHorizontal:3*r},finishButton:babelHelpers.extends({},t.regular,{color:o.core.rausch,fontWeight:"bold"})}});J.propTypes=W,J.defaultProps=z,r.default=(0,d.connect)(G,K)(J)},1750);