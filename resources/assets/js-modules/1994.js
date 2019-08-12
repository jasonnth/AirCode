__d(function(e,t,n,o){Object.defineProperty(o,"__esModule",{value:!0});var r=t(412),i=babelHelpers.interopRequireDefault(r),s=t(271),a=babelHelpers.interopRequireDefault(s),l=t(44),c=t(654),u=t(790),d=t(1023),p=t(841),f=babelHelpers.interopRequireDefault(p),R=t(410),P=babelHelpers.interopRequireDefault(R),h=t(787),C=babelHelpers.interopRequireDefault(h),v=t(828),b=babelHelpers.interopRequireDefault(v),E=t(705),T=babelHelpers.interopRequireDefault(E),S=t(1919),w=babelHelpers.interopRequireWildcard(S),A=t(1963),g=t(708),m=babelHelpers.interopRequireDefault(g),D=t(725),H=babelHelpers.interopRequireDefault(D),y=t(1930),I=t(1989),q=t(1818),N=t(1995),_=babelHelpers.interopRequireDefault(N),L=t(1936),k=t(1922),M=t(1996),x=babelHelpers.interopRequireDefault(M),O=t(2012),W=babelHelpers.interopRequireDefault(O),U=t(2016),j=babelHelpers.interopRequireDefault(U),Y=t(2018),G=babelHelpers.interopRequireDefault(Y),K=t(2020),B=babelHelpers.interopRequireDefault(K),F=t(1950),V=babelHelpers.interopRequireDefault(F),X=function(e){function t(n,o){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,n,o));return r.updateCard=r.updateCard.bind(r),r.onPressReviewRow=r.onPressReviewRow.bind(r),r.onPressCancelRequestRow=r.onPressCancelRequestRow.bind(r),r.refresh=r.refresh.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){var e=this;this.fetchCard(),m.default.emitter.addListener(m.default.RESERVATION_UPDATED,this.updateCard),T.default.launch("react_native_ro_native_datasource")&&(this.subscription=H.default.subscribe(this.props.id,function(t,n){return e.props.updateReservationObjectContent(e.props.cardType,t,n)},this.props.onShowNativeNetworkErrorToast))},t.prototype.componentWillUnmount=function(){m.default.emitter.removeListener(m.default.RESERVATION_UPDATED,this.updateCard),this.subscriptions&&H.default.unsubscribe(this.subscriptions)},t.prototype.updateCard=function(e){var t=this.props.item||{},n=t.confirmationCode;e.confirmationCode===n&&this.fetchCard()},t.prototype.fetchCard=function(){switch(this.props.cardType){case q.EXPERIENCE_CARD:this.props.fetchExperienceReservation();break;case q.CHECKIN_CARD:case q.CHECKOUT_CARD:this.props.fetchHomeReservation();break;case q.HOME_CARD:this.props.fetchHomeReservation({forPending:!0});break;case q.IMMERSION_CARD:this.props.fetchImmersion();break;case q.PLACE_CARD:this.props.fetchPlaceReservation()}},t.prototype.onPressReviewRow=function(e,t,n){var o=this,r=!(arguments.length>3&&void 0!==arguments[3])||arguments[3],i=arguments.length>4&&void 0!==arguments[4]?arguments[4]:null,s=this.props,a=s.id,l=s.cardType;(0,A.logReviewEvent)(a,l,e),w.navigateToReviewSheet(e,{isTrip:t,recipientFirstName:n,showAllCategories:r,templateName:i}).then(function(e){e&&o.fetchCard()})},t.prototype.refresh=function(e){this.props.fetchHomeReservation({loadingKey:e}),this.props.tripScheduleId&&(this.props.fetchTripSchedule(this.props.tripScheduleId),this.props.fetchTripScheduleCards(this.props.tripScheduleId))},t.prototype.onPressCancelRequestRow=function(e){var t=this;(0,A.logInfoSectionEvent)("Cancel",this.props.id,this.props.cardType,this.props.tripScheduleId),w.navigateToCancelationFlow(e,!0).then(function(e){var n=e.payload;n?n.alreadyAccepted?(t.props.onShowNativeCancelationAlreadyAcceptedToast(),t.refresh(q.LOADING_KEY_RETRACT)):n.networkError?(t.props.onShowNativeNetworkErrorToast(),t.refresh(q.LOADING_KEY_RETRACT)):n.didCancelFlowFinishedSuccessfully?P.default.dismiss({resultStatus:q.RESERVATION_WAS_CANCELLED}):t.refresh(q.LOADING_KEY_RETRACT):t.refresh(q.LOADING_KEY_RETRACT)})},t.prototype.renderCard=function(){var e=this,t=this.props,n=t.isAltering,o=t.isAppleWalletLoading,r=t.isCanceling,i=t.isRetracting,s=t.isDeleting,c=t.item,u=t.openHouseManual,d=t.onCardScreenImpression,p=t.onPressActionEvent,f=t.onPressAddToAppleWallet,R=t.onPressCancelSchedulablePlaceReservation,h=t.onPressCancellationPolicyRow,C=t.onPressCall,v=t.onPressContact,b=t.onPressDirections,E=t.onPressCheckinInstructions,S=t.onPressExperienceRow,A=t.onPressHomeReservationReceiptRow,g=t.onPressHostRow,m=t.onPressMapRow,D=t.onPressRemovePlace,H=t.onPressSheetLinkRow,y=t.onShowNativeAlterationDisabledToast,I=t.onShowNativeCancelationDisabledToast;if(!c)return null;if(!c.id&&!c.confirmationCode)return null;switch(this.parseCardType()){case q.EXPERIENCE_CARD:return a.default.createElement(x.default,babelHelpers.extends({},c,{onCardScreenImpression:d,onPressActionEvent:p,onPressCallHost:C,onPressContact:v,onPressDirections:b,onPressHostRow:g,onPressMapRow:m,onPressProductRow:function(e){return w.navigateToExperiencePDP(e.id,e.productType)},onPressReviewRow:this.onPressReviewRow,onPressSheetLinkRow:H}));case q.CHECKIN_CARD:case q.CHECKOUT_CARD:return a.default.createElement(W.default,babelHelpers.extends({},c,{isAltering:n,isAppleWalletLoading:o,isCanceling:r,isRetracting:i,openHouseManual:u,onCardScreenImpression:d,onCancelReservationUnavailable:I,onChangeReservationUnavailable:y,onCompleteCancelReservation:function(){P.default.dismiss({resultStatus:q.RESERVATION_WAS_CANCELLED})},onCompleteChangeReservation:function(){return e.refresh(q.LOADING_KEY_ALTER)},onPressActionEvent:p,onPressAddToAppleWallet:f,onPressCallHost:C,onPressCancelRequestRow:this.onPressCancelRequestRow,onPressCancellationPolicyRow:h,onPressCheckinGuide:E,onPressContact:v,onPressDirections:b,onPressHostRow:g,onPressMapRow:m,onPressProductRow:function(e){return w.navigateToListingPDP(e.id)},onPressReceiptRow:function(e){return A(e)},onPressReviewRow:this.onPressReviewRow,onPressSheetLinkRow:H}));case q.HOME_CARD:return a.default.createElement(W.default,babelHelpers.extends({},c,{isAltering:n,isCanceling:r,isRetracting:i,onCancelReservationUnavailable:I,onChangeReservationUnavailable:y,onPressCancelRequestRow:this.onPressCancelRequestRow,onPressCancellationPolicyRow:h,onPressContact:v,onPressHostRow:g,onPressMapRow:m,onPressProductRow:function(e){return w.navigateToListingPDP(e.id)},onPressReceiptRow:function(e){return A(e)},onPressSheetLinkRow:H,showActions:!1}));case q.IMMERSION_CARD:return a.default.createElement(j.default,babelHelpers.extends({},c,{onPressActionEvent:p,onPressCallHost:C,onPressContact:v,onPressExperienceRow:S,onPressProductRow:function(e){return w.navigateToExperiencePDP(e.id,e.productType)},onPressReviewRow:this.onPressReviewRow,onPressSheetLinkRow:H}));case q.RESY_CARD:return T.default.launch("react_native_reservation_object_refactor")?a.default.createElement(B.default,babelHelpers.extends({},c,{isDeleting:s,onCardScreenImpression:d,onPressCallPlace:C,onPressCancelReservation:R,onPressDirections:b,onPressMapRow:m,onPressRemove:D,onPressWebsite:function(e){return l.Linking.openURL(e)}})):a.default.createElement(G.default,babelHelpers.extends({},c,{isDeleting:s,onCardScreenImpression:d,onPressCallPlace:C,onPressCancelSchedulablePlaceReservation:R,onPressDirections:b,onPressMapRow:m,onPressProductRow:function(e){return w.navigateToGuidebookPlacePDP(e.id)},onPressRemove:D,onPressWebsite:function(e){return l.Linking.openURL(e)}}));case q.PLACE_CARD:return a.default.createElement(G.default,babelHelpers.extends({},c,{isDeleting:s,onCardScreenImpression:d,onPressCallPlace:C,onPressCancelSchedulablePlaceReservation:R,onPressDirections:b,onPressMapRow:m,onPressProductRow:function(e){return w.navigateToGuidebookPlacePDP(e.id)},onPressRemove:D,onPressWebsite:function(e){return l.Linking.openURL(e)}}));default:return null}},t.prototype.parseCardType=function(){var e=this.props,t=e.cardType,n=e.item,o=n.schedulablePlaceType;return t===q.PLACE_CARD&&o===q.RESY_TYPE?q.RESY_CARD:t},t.prototype.renderLoading=function(){var e=this.props.picture;return babelHelpers.jsx(C.default,{},void 0,babelHelpers.jsx(b.default,{image:e,suptitleWillBeProvided:!0,hideStatusBarUntilFoldOffset:!0}),babelHelpers.jsx(f.default,{}))},t.prototype.render=function(){var e=this.props,t=e.isLoading,n=e.item;return babelHelpers.jsx(V.default,{style:z.container},void 0,babelHelpers.jsx(P.default.Screen,{barType:P.default.Screen.BAR_TYPE.OVERLAY,leftIcon:"close",onLeftPress:this.props.clearToasts}),t&&!n?this.renderLoading():this.renderCard())},t}(s.Component);X.propTypes={isSourceNative:i.default.bool.isRequired,cardType:i.default.oneOf(q.CARD_TYPES).isRequired,clearToasts:i.default.func.isRequired,tripScheduleId:i.default.string,picture:i.default.string,confirmationCode:i.default.string,id:_.default,isAltering:i.default.bool,isAppleWalletLoading:i.default.bool,isCanceling:i.default.bool,isDeleting:i.default.bool,isLoading:i.default.bool,isRetracting:i.default.bool,item:i.default.object,lastSyncedAt:i.default.number,openHouseManual:i.default.bool,fetchExperienceReservation:i.default.func.isRequired,fetchHomeReservation:i.default.func.isRequired,fetchImmersion:i.default.func.isRequired,fetchPlaceReservation:i.default.func.isRequired,fetchTripSchedule:i.default.func.isRequired,fetchTripScheduleCards:i.default.func.isRequired,onPressAddToAppleWallet:i.default.func.isRequired,onPressCall:i.default.func.isRequired,onPressCancelSchedulablePlaceReservation:i.default.func.isRequired,onPressCancellationPolicyRow:i.default.func.isRequired,onPressContact:i.default.func.isRequired,onPressDirections:i.default.func.isRequired,onPressCheckinInstructions:i.default.func.isRequired,onPressExperienceRow:i.default.func.isRequired,onPressHomeReservationReceiptRow:i.default.func.isRequired,onPressHostRow:i.default.func.isRequired,onPressMapRow:i.default.func.isRequired,onPressRemovePlace:i.default.func.isRequired,onPressSheetLinkRow:i.default.func.isRequired,onShowNativeAlterationDisabledToast:i.default.func.isRequired,onShowNativeCancelationDisabledToast:i.default.func.isRequired,onShowNativeCancelationAlreadyAcceptedToast:i.default.func.isRequired,onShowNativeNetworkErrorToast:i.default.func.isRequired,onCardScreenImpression:i.default.func.isRequired,onPressActionEvent:i.default.func.isRequired,updateReservationObjectContent:i.default.func.isRequired},X.defaultProps={isSourceNative:!1,isAltering:!1,isAppleWalletLoading:!1,isCanceling:!1,isDeleting:!1,isLoading:!1,isRetracting:!1,lastSyncedAt:null,openHouseManual:!1};var z=d.ThemedStyleSheet.create(function(){return{container:{backgroundColor:"transparent",flex:1}}});o.default=(0,u.connect)(function(e,t){return(0,L.cardScreenSelector)(e,t)},function(e,t){return{clearToasts:(0,c.bindActionCreators)(y.clearToasts,e),fetchExperienceReservation:function(){return e((0,y.fetchExperienceReservation)(t.id))},updateReservationObjectContent:function(n,o,r){return e((0,y.updateReservationObjectContent)(t.cardType,o,r))},fetchHomeReservation:function(){var n=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return e((0,y.fetchHomeReservation)(t.id,n))},fetchImmersion:function(){return e((0,y.fetchImmersion)(t.id))},fetchPlaceReservation:function(){return e((0,y.fetchPlaceReservation)(t.id))},fetchTripSchedule:function(){return e((0,y.fetchTripSchedule)(t.tripScheduleId))},fetchTripScheduleCards:function(){return e((0,y.fetchTripScheduleCards)(t.tripScheduleId))},onCardScreenImpression:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:null;(0,A.logItineraryCardImpressionEvent)(t.cardType,t.id,e)},onPressAddToAppleWallet:function(n){(0,A.logActionButtonEvent)("Add to Apple Wallet button pressed.",t.id,t.cardType),e((0,y.addToAppleWallet)(t.id,n))},onPressCall:function(e){(0,A.logActionButtonEvent)("Call",t.id,t.cardType),(0,I.openPhoneActionSheet)(e,t.id,t.cardType,"t2")},onPressActionEvent:function(e,n){(0,A.logInfoSectionEvent)(e,t.id,t.cardType,t.tripScheduleId),n()},onPressCheckinInstructions:function(e){(0,A.logActionButtonEvent)("Launched Checkin Guide",t.id,t.cardType),w.navigateToCheckinGuide(e)},onPressCancelSchedulablePlaceReservation:function(n){(0,A.logClickCancelSchedulablePlaceReservationEvent)(t.id,t.tripScheduleId,n),H.default.onPlaceRemoved(t.id),e((0,y.removePlaceFromTimeline)(t.tripScheduleId,t.id,{isSourceNative:t.isSourceNative,source:q.SCREEN_T2}))},onPressCancellationPolicyRow:function(e){(0,A.logInfoSectionEvent)("Cancellation Policy",t.id,t.cardType,t.tripScheduleId),w.navigateToHomeCancellationPolicy(e)},onPressContact:function(e,n){(0,A.logActionButtonEvent)("Message Host",t.id,t.cardType),(0,I.openContactActionSheet)(e,n,t.id,t.cardType,"t2")},onPressDirections:function(e){(0,A.logActionButtonEvent)("Directions",t.id,t.cardType),(0,I.openMapProvidersActionSheet)(e,t.id,t.cardType,"t2",!0)},onPressExperienceRow:function(e){(0,A.logInfoSectionEvent)("Experience PDP",t.id,t.cardType,t.tripScheduleId),w.navigateToItineraryEventCard(e,q.EXPERIENCE_CARD)},onPressHomeReservationReceiptRow:function(e){(0,A.logInfoSectionEvent)("Reservation receipt",t.id,t.cardType,t.tripScheduleId),w.navigateToHomeReservationReceiptSheet(e.id,e)},onPressHostRow:function(e){(0,A.logHostEvent)(e,t.id,t.cardType,t.tripScheduleId,"t2"),w.navigateToUser(e)},onPressMapRow:function(e){(0,A.logActionButtonEvent)("Map",t.id,t.cardType),(0,I.openMapProvidersActionSheet)(e,t.id,t.cardType,"t2")},onPressRemovePlace:function(){H.default.onPlaceRemoved(t.id),(0,A.logRemovePlaceEvent)(t.id,t.tripScheduleId),e((0,y.removePlaceFromTimeline)(t.tripScheduleId,t.id,{isSourceNative:t.isSourceNative,source:q.SCREEN_T2}))},onPressSheetLinkRow:function(e){return P.default.present(k.SIMPLE_SHEET,e)},onShowNativeAlterationDisabledToast:function(){e((0,y.showNativeAlterationDisabledToast)())},onShowNativeCancelationDisabledToast:function(){e((0,y.showNativeCancelationDisabledToast)())},onShowNativeCancelationAlreadyAcceptedToast:function(){e((0,y.showNativeCancelationAlreadyAcceptedToast)())},onShowNativeNetworkErrorToast:function(){e((0,y.showNativeNetworkErrorToast)())}}})(X)},1994);