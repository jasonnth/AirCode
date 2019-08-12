__d(function(e,r,t,i){Object.defineProperty(i,"__esModule",{value:!0});var a=r(790),o=r(412),l=babelHelpers.interopRequireDefault(o),n=r(271),s=(babelHelpers.interopRequireDefault(n),r(44)),u=r(654),c=r(759),d=babelHelpers.interopRequireDefault(c),p=r(379),f=babelHelpers.interopRequireDefault(p),b=r(410),h=babelHelpers.interopRequireDefault(b),_=r(1036),I=babelHelpers.interopRequireDefault(_),R=r(1427),S=babelHelpers.interopRequireDefault(R),T=r(927),H=babelHelpers.interopRequireDefault(T),m=r(1352),C=babelHelpers.interopRequireDefault(m),A=r(1482),E=babelHelpers.interopRequireWildcard(A),g=r(1443),N=babelHelpers.interopRequireWildcard(g),y=r(1483),M=r(789),P=babelHelpers.interopRequireDefault(M),O=r(1484),q=babelHelpers.interopRequireDefault(O),D=r(1470),w=r(1478),k=r(1475),v=babelHelpers.interopRequireWildcard(k),U=r(1472),x=r(1463),L=function(e){function r(t){babelHelpers.classCallCheck(this,r);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,t));return i.onPressLearnMore=i.onPressLearnMore.bind(i),i.onPressContinue=i.onPressContinue.bind(i),i}return babelHelpers.inherits(r,e),r.prototype.onPressLearnMore=function(){(0,w.logPressLearnMore)(this.props.airlockId,w.PAGE_NAMES.MICRO_AUTH_ENTRY_SCREEN),h.default.present(E.AUTHENTICATED_WEBVIEW,{url:(0,y.getWebUrl)("/help/article/1531")})},r.prototype.onPressContinue=function(){var e=this.props,r=e.actionName,t=e.airlockId,i=e.userId,a=e.frictionStatus,o=e.requestToSendMicroCharges;if((0,w.logPressContinue)(this.props.airlockId,w.PAGE_NAMES.MICRO_AUTH_ENTRY_SCREEN),a===x.IN_PROGRESS)return void h.default.push(N.MICRO_AUTHORIZATION_AMOUNTS_INPUT);o({actionName:r,airlockId:t,userId:i}).then(function(e){var r=e.success,t=e.payload;r?h.default.push(N.MICRO_AUTHORIZATION_AMOUNTS_INPUT):500===t.error_code&&h.default.push(N.MICRO_AUTHORIZATION_SEND_CHARGES_FAILED)})},r.prototype.render=function(){if(!this.props.detail)return null;var e=this.props.isFetching,r=this.props.detail,t=r.cc_localized_name,i=r.cc_last_four;return babelHelpers.jsx(s.View,{style:s.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(S.default,{babu:!0,svgIcon:H.default.SVGS.trust,title:f.default.phrase("Verify your card",null,"Title text informing users we need to verify their credit card using temporary authorizations to allow them to proceed with the booking."),subtitle:f.default.phrase("To protect your card, we will send two temporary authorizations of $1.99 or less to %{cc_localized_name} %{cc_last_four} after you click continue",{cc_localized_name:t,cc_last_four:i},"Subtitle text informing users how micro authorization works by specify the value of the authorizations and the card in use."),link:{text:f.default.phrase("Learn more",null,"click to learn more about why we need to verify the users credit card"),onPress:this.onPressLearnMore}}),babelHelpers.jsx(I.default,{onPrimaryPress:this.onPressContinue,primaryText:f.default.phrase("Continue",null,"continue to next screen"),primaryLoading:e,primaryDisabled:e}),babelHelpers.jsx(P.default,{},void 0,babelHelpers.jsx(C.default,{id:U.MICRO_AUTHORIZATION_REQUEST_TO_SEND_CHARGES_FAILED,description:f.default.phrase("Something went wrong with the upload. Please try again later.",null,"an error message")})))},r}(n.PureComponent);L.propTypes={isFetching:l.default.bool,airlockId:l.default.number,userId:l.default.number,actionName:l.default.string,detail:q.default,frictionStatus:l.default.oneOf([x.NOT_STARTED,x.IN_PROGRESS,x.SATISFIED]).isRequired,requestToSendMicroCharges:l.default.func},L.defaultProps={isFetching:!1,airlockId:0,userId:null,actionName:null,detail:null,requestToSendMicroCharges:function(){}},i.default=(0,a.connect)(function(e){return babelHelpers.extends({detail:(0,D.frictionManagerSelector)(e).frictionInitData.micro_authorization},(0,d.default)((0,D.frictionManagerSelector)(e),["airlockId","userId","actionName"]),(0,d.default)((0,D.microAuthSelector)(e),["isFetching","frictionStatus"]))},function(e){return(0,u.bindActionCreators)({requestToSendMicroCharges:v.requestToSendMicroCharges},e)})(L)},1481);