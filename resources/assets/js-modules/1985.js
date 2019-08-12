__d(function(e,t,r,n){function i(){(0,N.logTosEvent)(),(0,_.default)((0,m.getWebUrl)("/experiences/terms-of-service"))}function o(e){var t=e.endsAt,r=e.startsAt,n=e.timezone;return(0,R.getSummaryCardSubtitle)((0,R.getDate)(r,n),(0,R.getDate)(t,n))}Object.defineProperty(n,"__esModule",{value:!0});var s=t(379),l=babelHelpers.interopRequireDefault(s),a=t(412),u=babelHelpers.interopRequireDefault(a),d=t(271),c=(babelHelpers.interopRequireDefault(d),t(44)),p=t(790),f=t(654),b=t(1023),h=t(1429),g=babelHelpers.interopRequireDefault(h),v=t(410),S=babelHelpers.interopRequireDefault(v),y=t(1408),T=babelHelpers.interopRequireDefault(y),P=t(899),H=babelHelpers.interopRequireDefault(P),R=t(1937),I=t(1986),_=babelHelpers.interopRequireDefault(I),m=t(1483),x=t(1919),D=babelHelpers.interopRequireWildcard(x),w=t(1930),E=t(1818),A=t(1936),O=t(1961),M=babelHelpers.interopRequireDefault(O),q=t(1950),C=babelHelpers.interopRequireDefault(q),j=t(1946),N=t(1963),V=function(e){function t(r,n){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,r,n));i.onPressPendingAction=i.onPressPendingAction.bind(i),i.renderHeader=i.renderHeader.bind(i),i.renderRow=i.renderRow.bind(i);var o=new c.ListView.DataSource({rowHasChanged:function(e,t){return e!==t}});return i.state={dataSource:i.getDatasource(o,r.tripSchedules)},i}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){this.props.tripSchedules!==e.tripSchedules&&this.setState({dataSource:this.getDatasource(this.state.dataSource,e.tripSchedules)})},t.prototype.getDatasource=function(e,t){return e.cloneWithRows(t.concat().sort(function(e,t){return(0,j.compareDataForSection)(E.SECTION_PENDING,e,t)}))},t.prototype.onPressPendingAction=function(){var e=this.props.verification,t=e.isVerificationPending,r=e.needMtTOS,n=e.needVerification;(0,N.logPendingActionEvent)(n,r),t||(r&&this.props.acceptMtTOS(),n&&this.props.presentIdentityFlow())},t.prototype.onPressRow=function(e){var t=e.confirmationCode;switch(e.pendingType){case E.PENDING_IMMERSION:(0,N.logPendingItemEvent)(t,E.IMMERSION_CARD),D.navigateToItineraryEventCard(t,E.IMMERSION_CARD);break;case E.PENDING_HOME_RESERVATION:(0,N.logPendingItemEvent)(t,E.HOME_CARD),D.navigateToItineraryEventCard(t,E.HOME_CARD)}},t.prototype.getRowSubtitle=function(e){var t=e.pendingType,r=this.props.verification,n=r.isVerificationPending,i=r.needMtTOS,o=r.needVerification;switch(t){case E.PENDING_IMMERSION:return n?l.default.phrase("Waiting for your info to be verified",null,"a pending request title when user needs to verify their ID"):o?l.default.phrase("Please provide your info above",null,"a pending request title when user needs to verify their ID"):i?l.default.phrase("Please agree to our terms above",null,"a pending request title when user needs to accept TOS"):null;case E.PENDING_HOME_RESERVATION:return l.default.phrase("Waiting for the host\u2019s response",null,"a title when request is pending");default:return null}},t.prototype.renderPendingActionTitle=function(){var e=this.props.verification,t=e.isVerificationPending,r=e.needMtTOS,n=e.needVerification;return t?null:r&&!n?l.default.phrase("I agree",null,"a button to agree to the Magical Trip Term of Services"):n?l.default.phrase("Get started",null,"a button to launch Verified ID flow"):null},t.prototype.renderPendingSectionSubtitle=function(){var e=this.props.verification,t=e.isVerificationPending,r=e.needMtTOS,n=e.needVerification,o=e.soonestBookedAt,s=e.timezone;if(t)return babelHelpers.jsx(H.default,{context:"a description when verification process is pending",bold:babelHelpers.jsx(c.Text,{style:h.styles.subtitleBold}),default:"%{bold_start}Verification pending: %{bold_end}We\u2019re still verifying your info. You\u2019ll get an email from us shortly."});if(r||n){var l=this.props.timeLeft?this.props.timeLeft:(0,R.getIdentityTimeLeft)(o,s);return r?babelHelpers.jsx(H.default,{context:"a description for Terms of Services section",default:"%{bold_start}Action required: %{bold_end} You have %{babu_bold_start}%{timeLeft} left%{babu_bold_end} to agree to the %{link_start}Airbnb Terms of Service%{link_end} before going on your experience, or it will be canceled.",bold:babelHelpers.jsx(c.Text,{style:h.styles.subtitleBold}),link:babelHelpers.jsx(c.Text,{style:h.styles.link,onPress:i}),babu_bold:babelHelpers.jsx(c.Text,{style:h.styles.subtitleBold}),timeLeft:l}):babelHelpers.jsx(H.default,{context:"a description for verification process section",default:"%{bold_start}Action required: %{bold_end}You have %{babu_bold_start}%{timeLeft} left%{babu_bold_end} to complete our verification process before going on your upcoming experience.",bold:babelHelpers.jsx(c.Text,{style:h.styles.subtitleBold}),babu_bold:babelHelpers.jsx(c.Text,{style:h.styles.subtitleBold}),timeLeft:l})}return null},t.prototype.renderPendingSectionDescription=function(){var e=this.props.verification,t=e.isVerificationPending,r=e.needMtTOS,n=e.needVerification;return t?null:r&&n?babelHelpers.jsx(H.default,{default:"By tapping \u201cGet started\u201d I agree to the %{link_start}terms of service and traveler release and waiver%{link_end}.",link:babelHelpers.jsx(c.Text,{style:h.styles.link,onPress:i}),context:"a description for verified ID section"}):null},t.prototype.renderHeader=function(){var e=l.default.phrase("Pending",null,"a header section for a list of pending reservations (trips/home)");return babelHelpers.jsx(g.default,{actionInverse:!0,actionSmall:!0,actionTitle:this.renderPendingActionTitle(),onPressAction:this.onPressPendingAction,title:e,subtitle:this.renderPendingSectionSubtitle(),description:this.renderPendingSectionDescription()})},t.prototype.renderRow=function(e){var t=this,r=e.picture,n=e.title;return babelHelpers.jsx(T.default,{onPress:function(){return t.onPressRow(e)},picture:r,subtitle:this.getRowSubtitle(e),suptitle:(o(e)||"").toUpperCase(),title:n,orientation:"square",icon:"chevron-right",divider:"full"})},t.prototype.render=function(){var e=this;return babelHelpers.jsx(C.default,{},void 0,babelHelpers.jsx(S.default.Screen,{barType:S.default.Screen.BAR_TYPE.STATIC,title:l.default.phrase("Pending",null,"a title for pending items (reservations/experience) screen"),leftIcon:S.default.Screen.LEFT_ICON.CLOSE,onLeftPress:function(){return e.props.clearToasts()}}),babelHelpers.jsx(c.ListView,{automaticallyAdjustContentInsets:!1,dataSource:this.state.dataSource,enableEmptySections:!0,renderHeader:this.renderHeader,renderRow:this.renderRow,showsVerticalScrollIndicator:!1,style:L.container}))},t}(d.Component);V.propTypes={tripSchedules:u.default.array,verification:M.default,timeLeft:u.default.string,acceptMtTOS:u.default.func.isRequired,clearToasts:u.default.func.isRequired,presentIdentityFlow:u.default.func.isRequired},V.defaultProps={tripSchedules:[],verification:{}};var L=b.ThemedStyleSheet.create(function(e){return{container:{backgroundColor:e.color.white,flex:1}}});n.default=(0,p.connect)(function(e,t){return(0,A.pendingScreenSelector)(e,t)},function(e){return(0,f.bindActionCreators)({acceptMtTOS:w.acceptMtTOS,clearToasts:w.clearToasts,presentIdentityFlow:w.presentIdentityFlow},e)})(V)},1985);