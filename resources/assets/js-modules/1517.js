__d(function(t,e,o,n){Object.defineProperty(n,"__esModule",{value:!0});var r=e(759),a=babelHelpers.interopRequireDefault(r),i=e(412),s=babelHelpers.interopRequireDefault(i),u=e(271),l=babelHelpers.interopRequireDefault(u),c=e(790),p=e(654),m=e(1497),f=babelHelpers.interopRequireDefault(m),h=e(377),d=babelHelpers.interopRequireDefault(h),A=e(379),b=babelHelpers.interopRequireDefault(A),I=e(1352),T=babelHelpers.interopRequireDefault(I),_=e(410),O=babelHelpers.interopRequireDefault(_),R=e(881),S=babelHelpers.interopRequireDefault(R),g=e(772),M=babelHelpers.interopRequireDefault(g),N=e(1475),C=babelHelpers.interopRequireWildcard(N),H=e(1484),U=babelHelpers.interopRequireDefault(H),P=e(1472),E=e(1470),y=e(1443),w=e(684),F=e(1518),x=babelHelpers.interopRequireDefault(F),D=e(1502),v=e(1478),z=function(t){function e(o){babelHelpers.classCallCheck(this,e);var n=babelHelpers.possibleConstructorReturn(this,t.call(this,o));return n.state={amount1:0,amount2:0,isSubmitting:!1,nextDisabled:!1,wrongAttempts:0},n.submitAmounts=n.submitAmounts.bind(n),n.onCloseWrongAmountToast=n.onCloseWrongAmountToast.bind(n),n.onSubmitPress=n.onSubmitPress.bind(n),n.onCloseFixAmountsBeforeSubmitToast=n.onCloseFixAmountsBeforeSubmitToast.bind(n),n.onPressHowLongDoesItTake=n.onPressHowLongDoesItTake.bind(n),n.onPressVerifyAnotherWay=n.onPressVerifyAnotherWay.bind(n),n}return babelHelpers.inherits(e,t),e.prototype.onSubmitPress=function(){var t=this.state,e=t.amount1,o=t.amount2;(0,v.logPressContinue)(this.props.airlockId,v.PAGE_NAMES.AMOUNTS_INPUT_SCREEN,{amounts:[e,o]}),this.isAmountsValid()?((0,f.default)(),this.submitAmounts()):this.props.addToast(P.MICRO_AUTHORIZATION_FIX_AMOUNTS_BEFORE_SUBMIT)},e.prototype.isAmountsValid=function(){var t=this.state,e=t.amount1,o=t.amount2;return(0,D.isMicroAuthAmountValid)(e)&&(0,D.isMicroAuthAmountValid)(o)},e.prototype.submitAmounts=function(){var t=this,e=this.state,o=e.amount1,n=e.amount2,r=this.props,a=r.userId,i=r.actionName,s=r.airlockId;this.setState({isSubmitting:!0}),this.props.submitAmountsForMicroAuthorization({userId:a,actionName:i,airlockId:s,amounts:[o,n]}).then(function(e){var o=e.success,n=e.payload;return t.handleSubmissionResult(o,n.airlock)})},e.prototype.handleSubmissionResult=function(t,e){if(this.setState({isSubmitting:!1}),t){if(!0===(e.friction_statuses||{}).micro_authorization)O.default.push(y.MICRO_AUTHORIZATION_SUCCESS);else{var o=this.state.wrongAttempts+1;this.setState({wrongAttempts:o}),o>3?O.default.push(y.MICRO_AUTHORIZATION_FAILURE):this.props.addToast(P.MICRO_AUTHORIZATION_WRONG_AMOUNTS)}}},e.prototype.getWrongAmountsToastTitle=function(){var t="";switch(this.state.wrongAttempts){case 1:t=b.default.phrase("Incorrect amounts.",null,"toast title after user input wrong amounts in micro authorization for the first time");break;case 2:t=b.default.phrase("You have 2 more attempts",null,"toast title after user input wrong amounts in micro authorization for the second time");break;case 3:t=b.default.phrase("You have 1 more attempt",null,"toast title after user input wrong amounts in micro authorization for the third time");break;default:t=b.default.phrase("Incorrect amounts.",null,"toast title after user input wrong amounts in micro authorization")}return t},e.prototype.focusOnFirstWrongAmountInput=function(){var t=this.state,e=t.amount1,o=t.amount2;(0,D.isMicroAuthAmountValid)(e)?(0,D.isMicroAuthAmountValid)(o)||this.amountInputRow2Ref.focus():this.amountInputRow1Ref.focus()},e.prototype.onCloseWrongAmountToast=function(){this.props.removeToast(P.MICRO_AUTHORIZATION_WRONG_AMOUNTS)},e.prototype.onCloseFixAmountsBeforeSubmitToast=function(){this.props.removeToast(P.MICRO_AUTHORIZATION_FIX_AMOUNTS_BEFORE_SUBMIT),this.focusOnFirstWrongAmountInput()},e.prototype.onPressHowLongDoesItTake=function(){(0,v.logPressLearnMore)(this.props.airlockId,v.PAGE_NAMES.AMOUNTS_INPUT_SCREEN),O.default.push(y.MICRO_AUTHORIZATION_EXPLANATION)},e.prototype.onPressVerifyAnotherWay=function(){(0,v.logLaunchChargebackAppeal)(this.props.airlockId,v.PAGE_NAMES.AMOUNTS_INPUT_SCREEN),O.default.push(y.UPLOAD_CARD_STATEMENT)},e.prototype.render=function(){var t=this;if(!this.props.detail)return null;var e=this.state.isSubmitting,o=this.props.detail,n=o.cc_localized_name,r=o.cc_last_four,a=o.currency,i=this.getWrongAmountsToastTitle();return babelHelpers.jsx(M.default,{title:b.default.phrase("Enter the amounts",null,"title of a screen to input authorization amounts"),subtitle:b.default.phrase("We sent two temporary authorizations from Airbnb of %{priceString} or less to %{cc_localized_name} %{cc_last_four}",{priceString:d.default.priceString(1.99,a),cc_localized_name:n,cc_last_four:r},"a text explaining which amounts the user should input to authorize"),footer:babelHelpers.jsx(S.default,{babu:!0,loading:e,buttonDisabled:e,title:e?null:b.default.phrase("How long does it take?",null,"a button linking to a page explaining how long micro authorization takes to verify"),buttonText:b.default.phrase("Finish",null,"Button text to finish micro authorization"),onButtonPress:this.onSubmitPress,onSecondaryActionPress:e?null:this.onPressHowLongDoesItTake}),toasts:[babelHelpers.jsx(T.default,{id:P.MICRO_AUTHORIZATION_SUBMISSION_FAILED,description:b.default.phrase("Something went wrong with the upload. Please try again later.",null,"an error message")},P.MICRO_AUTHORIZATION_SUBMISSION_FAILED),babelHelpers.jsx(T.default,{error:!0,id:P.MICRO_AUTHORIZATION_WRONG_AMOUNTS,title:i,description:b.default.phrase("One or both amounts are not correct",null,"Toast text warning the user about the amounts for micro authorization not being correct"),action:b.default.phrase("Change",null,"A button to let user change the amounts for micro authorization"),onActionPress:this.onCloseWrongAmountToast,onClosePress:this.onCloseWrongAmountToast},P.MICRO_AUTHORIZATION_WRONG_AMOUNTS),babelHelpers.jsx(T.default,{id:P.MICRO_AUTHORIZATION_FIX_AMOUNTS_BEFORE_SUBMIT,description:b.default.phrase("You need to fix the amounts before you can continue.",null,"Toast text in micro authorization warning user to fix charge amounts before submit"),action:b.default.phrase("Fix",null,"A button to let user fix the charge amounts"),onClosePress:this.onCloseFixAmountsBeforeSubmitToast,onActionPress:this.onCloseFixAmountsBeforeSubmitToast},P.MICRO_AUTHORIZATION_FIX_AMOUNTS_BEFORE_SUBMIT)]},void 0,l.default.createElement(x.default,{ref:function(e){t.amountInputRow1Ref=e},currency:a,onChangeAmount:function(e){return t.setState({amount1:e})}}),l.default.createElement(x.default,{ref:function(e){t.amountInputRow2Ref=e},currency:a,onChangeAmount:function(e){return t.setState({amount2:e})}}))},e}(u.PureComponent);z.propTypes={detail:U.default,actionName:s.default.string,userId:s.default.number,airlockId:s.default.number,submitAmountsForMicroAuthorization:s.default.func,addToast:s.default.func,removeToast:s.default.func},z.defaultProps={detail:null,actionName:null,userId:null,airlockId:null,submitAmountsForMicroAuthorization:function(){},addToast:function(){},removeToast:function(){}},n.default=(0,c.connect)(function(t){return babelHelpers.extends({},(0,a.default)((0,E.frictionManagerSelector)(t),["userId","actionName","airlockId"]),{detail:(0,E.frictionManagerSelector)(t).frictionInitData.micro_authorization})},function(t){return(0,p.bindActionCreators)({submitAmountsForMicroAuthorization:C.submitAmountsForMicroAuthorization,addToast:w.addToast,removeToast:w.removeToast},t)})(z)},1517);