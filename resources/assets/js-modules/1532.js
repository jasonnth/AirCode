__d(function(e,t,o,i){Object.defineProperty(i,"__esModule",{value:!0});var n=t(759),r=babelHelpers.interopRequireDefault(n),l=t(412),a=babelHelpers.interopRequireDefault(l),s=t(271),u=(babelHelpers.interopRequireDefault(s),t(654)),p=t(790),c=t(379),d=babelHelpers.interopRequireDefault(c),f=t(410),h=babelHelpers.interopRequireDefault(f),T=t(1443),I=babelHelpers.interopRequireWildcard(T),b=t(1475),_=babelHelpers.interopRequireWildcard(b),E=t(1489),N=babelHelpers.interopRequireDefault(E),O=t(1470),C=t(1502),P=t(1531),D=babelHelpers.interopRequireDefault(P),V=t(1478),m=t(1466),R=t(1472),A=t(1499),S=babelHelpers.interopRequireDefault(A),g=t(1500),H=babelHelpers.interopRequireDefault(g),v=t(684),F=function(e){function t(o){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,o));return i.phoneNumberOptions=(0,C.getPhoneNumberOptions)(i.getVerificationDetail()),i.onSelectedPhoneNumberChange=i.onSelectedPhoneNumberChange.bind(i),i.onSendCodePress=i.onSendCodePress.bind(i),i.getActiveOption=i.getActiveOption.bind(i),i.onCloseToast=i.onCloseToast.bind(i),i.selectDefaultPhoneNumberId=i.selectDefaultPhoneNumberId.bind(i),i.getTitle=i.getTitle.bind(i),i.getFooterButtonText=i.getFooterButtonText.bind(i),i}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.selectDefaultPhoneNumberId()},t.prototype.selectDefaultPhoneNumberId=function(){this.phoneNumberOptions.length>0&&!this.getActiveOption()&&this.props.selectPhoneNumberId(this.phoneNumberOptions[0].value)},t.prototype.getVerificationDetail=function(){var e=this.props,t=e.frictionType,o=e.phoneVerificationViaCallDetail,i=e.phoneVerificationViaTextDetail;return t===m.FRICTION_TYPE_PHONE_VERIFICATION_VIA_TEXT?i:o},t.prototype.onSelectedPhoneNumberChange=function(e){this.props.selectPhoneNumberId(e.value)},t.prototype.onSendCodePress=function(){var e=this,t=this.props,o=t.airlockId,i=t.actionName,n=t.userId,r=t.selectedPhoneNumberId,l=t.frictionType,a=t.requestToSendPhoneVerificationCode;(0,V.logSendPhoneVerificationCode)(o,V.PAGE_NAMES.PHONE_VERIFICATION_NUMBER_SELECTION_SCREEN,r,l),a({actionName:i,airlockId:o,userId:n,phoneNumberId:r,frictionType:l}).then(function(t){var o=t.success,i=t.payload;o?h.default.push(I.AOV_PHONE_CODE_INPUT):"rate_limit"===i.error_type?e.props.addToast(R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_RATE_LIMITED):e.props.addToast(R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_FAILED)})},t.prototype.onCloseToast=function(){this.props.removeAllToasts()},t.prototype.getActiveOption=function(){var e=this;return this.phoneNumberOptions.find(function(t){return t.value===e.props.selectedPhoneNumberId})},t.prototype.getTitle=function(){return this.props.frictionType===m.FRICTION_TYPE_PHONE_VERIFICATION_VIA_TEXT?d.default.phrase("Where should we send your code?",null,"title of a screen asking user to select a phone number to receive verification code"):d.default.phrase("Where should we call you?",null,"title of a screen asking user to select a phone number to receive verification code")},t.prototype.getFooterButtonText=function(){return this.props.frictionType===m.FRICTION_TYPE_PHONE_VERIFICATION_VIA_TEXT?d.default.phrase("Send code",null,"A button to receive a sms verification code"):d.default.phrase("Call me",null,"A button to receive a phone call verification code")},t.prototype.render=function(){var e=this.props,t=e.isFetching,o=e.phoneVerificationViaCallDetail,i=e.phoneVerificationViaTextDetail;return o||i?babelHelpers.jsx(D.default,{title:this.getTitle(),footerButtonText:this.getFooterButtonText(),toasts:[babelHelpers.jsx(H.default,{id:R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_FAILED,onPressClose:this.onCloseToast},R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_FAILED),babelHelpers.jsx(S.default,{id:R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_RATE_LIMITED,onPressClose:this.onCloseToast},R.PHONE_VERIFICATION_REQUEST_TO_SEND_CODE_RATE_LIMITED)],activeOption:this.getActiveOption(),options:this.phoneNumberOptions,isFetching:t,onSelectedOptionChange:this.onSelectedPhoneNumberChange,onFooterButtonPress:this.onSendCodePress}):null},t}(s.PureComponent);F.propTypes={isFetching:a.default.bool,airlockId:a.default.number,actionName:a.default.string,userId:a.default.number,phoneVerificationViaCallDetail:N.default,phoneVerificationViaTextDetail:N.default,selectedPhoneNumberId:a.default.number,frictionType:a.default.oneOf(m.FRICTION_GROUP_PHONE_VERIFICATION).isRequired,selectPhoneNumberId:a.default.func.isRequired,requestToSendPhoneVerificationCode:a.default.func.isRequired,addToast:a.default.func.isRequired,removeAllToasts:a.default.func.isRequired},F.defaultProps={isFetching:!1,airlockId:null,actionName:null,userId:null,phoneVerificationViaCallDetail:null,phoneVerificationViaTextDetail:null,selectedPhoneNumberId:null},i.default=(0,p.connect)(function(e){return babelHelpers.extends({phoneVerificationViaCallDetail:(0,O.phoneVerificationViaCallDetailSelector)(e),phoneVerificationViaTextDetail:(0,O.phoneVerificationViaTextDetailSelector)(e)},(0,r.default)((0,O.frictionManagerSelector)(e),["airlockId","actionName","userId"]),(0,r.default)((0,O.phoneVerificationSelector)(e),["selectedPhoneNumberId","frictionType","isFetching"]))},function(e){return(0,u.bindActionCreators)({selectPhoneNumberId:_.selectPhoneNumberId,requestToSendPhoneVerificationCode:_.requestToSendPhoneVerificationCode,removeAllToasts:_.removeAllToasts,addToast:v.addToast},e)})(F)},1532);