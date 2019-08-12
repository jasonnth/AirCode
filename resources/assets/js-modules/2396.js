__d(function(e,t,i,n){Object.defineProperty(n,"__esModule",{value:!0});var o=t(412),s=babelHelpers.interopRequireDefault(o),r=t(271),l=babelHelpers.interopRequireDefault(r),a=t(44),p=t(790),c=t(654),u=t(379),d=babelHelpers.interopRequireDefault(u),h=t(824),f=babelHelpers.interopRequireDefault(h),S=t(1036),g=babelHelpers.interopRequireDefault(S),k=t(1352),b=babelHelpers.interopRequireDefault(k),m=t(410),C=babelHelpers.interopRequireDefault(m),O=t(1023),y=t(2390),v=babelHelpers.interopRequireWildcard(y),T=t(2393),R=t(772),x=babelHelpers.interopRequireDefault(R),P=t(2394),q=t(2391),H=t(2395),_={checkinType:s.default.number.isRequired,listingId:s.default.string.isRequired,existingInstructions:s.default.string,existingListingAmenityId:s.default.string,createSelfCheckinOption:s.default.func.isRequired,updateSelfCheckinOption:s.default.func.isRequired,removeSelfCheckinOption:s.default.func.isRequired,isUpdatingSelfCheckinOption:s.default.bool,isRemovingSelfCheckinOption:s.default.bool,updateSelfCheckinOptionRequestSucceeded:s.default.bool,removeSelfCheckinOptionRequestSucceeded:s.default.bool,errorMessage:s.default.string},I={},A=function(e){function t(i){babelHelpers.classCallCheck(this,t);var n=babelHelpers.possibleConstructorReturn(this,e.call(this,i));return n.onPrimaryButtonPress=n.onPrimaryButtonPress.bind(n),n.onSecondaryButtonPress=n.onSecondaryButtonPress.bind(n),n.validateForm=n.validateForm.bind(n),n.createSelfCheckinOption=n.createSelfCheckinOption.bind(n),n.updateSelfCheckinOption=n.updateSelfCheckinOption.bind(n),n.removeSelfCheckinOption=n.removeSelfCheckinOption.bind(n),n.state={instructions:n.props.existingInstructions,shouldShowToast:!1},n}return babelHelpers.inherits(t,e),t.prototype.componentWillReceiveProps=function(e){!0!==e.updateSelfCheckinOptionRequestSucceeded&&!0!==e.removeSelfCheckinOptionRequestSucceeded||C.default.dismiss({resultCode:C.default.RESULT_OK}),this.setState({shouldShowToast:e.errorMessage&&e.errorMessage.length>0})},t.prototype.onPrimaryButtonPress=function(){this.validateForm()&&(this.props.existingListingAmenityId?this.updateSelfCheckinOption():this.createSelfCheckinOption())},t.prototype.onSecondaryButtonPress=function(){var e=this;a.Alert.alert(d.default.phrase("Delete this check-in option?",null,"text asking hosts to confirm they want to delete a self-checkin option."),null,[{text:d.default.phrase("Cancel",null,"a button to click when users want to cancel deleting a self-checkin option."),onPress:function(){var t=e.props,i=t.checkinType,n=t.listingId;(0,P.logCancelRemove)(i,n)}},{text:d.default.phrase("Yes",null,"a button to confirm deleting a self-checkin option."),onPress:function(){e.removeSelfCheckinOption()}}]);var t=this.props,i=t.checkinType,n=t.listingId;(0,P.logTapRemove)(i,n)},t.prototype.createSelfCheckinOption=function(){var e=this.props,t=e.checkinType,i=e.listingId,n=this.state.instructions;this.props.createSelfCheckinOption(i,t,n),(0,P.logTapAdd)(t,i)},t.prototype.updateSelfCheckinOption=function(){var e=this.props,t=e.checkinType,i=e.existingListingAmenityId,n=e.listingId,o=this.state.instructions;this.props.updateSelfCheckinOption(i,o),(0,P.logTapUpdate)(t,n)},t.prototype.removeSelfCheckinOption=function(){var e=this.props,t=e.checkinType,i=e.existingListingAmenityId,n=e.listingId;this.props.removeSelfCheckinOption(i),(0,P.logConfirmRemove)(t,n)},t.prototype.validateForm=function(){return null!=this.state.instructions&&this.state.instructions.length>0},t.prototype.render=function(){var e=this,t=(0,H.GET_OPTION_TO_CONTENT_MAP)()[this.props.checkinType],i=this.props.isUpdatingSelfCheckinOption,n=this.props.isRemovingSelfCheckinOption,o=this.props.existingListingAmenityId?d.default.phrase("Update",null,"Action to update self-checkin instructions."):null,s=this.props.existingListingAmenityId?d.default.phrase("Remove",null,"Action to remove self-checkin instructions."):null;return babelHelpers.jsx(x.default,{title:t.name,subtitle:function(){return d.default.phrase("Please add specific check-in instructions for guests. Only guests with a confirmed booking will see these instructions during their stay period.",null,"Subtitle of the add self-checkin option instructions screen.")}(),footer:babelHelpers.jsx(g.default,{light:!0,onPrimaryPress:this.onPrimaryButtonPress,primaryLoading:i,primaryDisabled:!this.validateForm()||n,primaryText:o,onSecondaryPress:this.onSecondaryButtonPress,secondaryLoading:n,secondaryDisabled:i,secondaryText:s}),toastsUseRedux:!1,toasts:[this.state.shouldShowToast&&this.props.errorMessage&&babelHelpers.jsx(b.default,{id:q.SELF_CHECKIN_OPTION_REQUEST_FAILED_TOAST,error:!0,title:d.default.phrase("Error",null,"Error toast title."),description:this.props.errorMessage,onClosePress:function(){e.setState({shouldShowToast:!1})}})]},void 0,babelHelpers.jsx(a.Text,{style:L.inputTitleText},void 0,d.default.phrase("%{self_checkin_type} instructions",{self_checkin_type:t.name},"Title of the add self-checkin option instructions screen.")),babelHelpers.jsx(f.default,{autoScroll:!0,blurOnSubmit:!0,returnKeyType:"done",defaultValue:this.state.instructions,maxLength:H.MAX_INSTRUCTIONS_LENGTH,placeholder:t.instructions_prompt,onChangeText:function(t){return e.setState({instructions:t})}}))},t}(l.default.Component);A.defaultProps=I,A.propTypes=_;var L=O.ThemedStyleSheet.create(function(e){var t=e.font,i=e.size;return{container:{flex:1},inputTitleText:babelHelpers.extends({},t.regularBold,{marginHorizontal:i.baseRow.paddingHorizontal,paddingTop:i.vertical.small,textAlign:"left"})}});n.default=(0,p.connect)(function(e){var t=(0,T.listingSelfCheckinSettingsSelector)(e);return{isUpdatingSelfCheckinOption:t.get("isUpdatingSelfCheckinOption"),isRemovingSelfCheckinOption:t.get("isRemovingSelfCheckinOption"),updateSelfCheckinOptionRequestSucceeded:t.get("updateSelfCheckinOptionRequestSucceeded"),removeSelfCheckinOptionRequestSucceeded:t.get("removeSelfCheckinOptionRequestSucceeded"),errorMessage:t.get("errorMessage")}},function(e){return(0,c.bindActionCreators)({createSelfCheckinOption:v.createCheckinOption,updateSelfCheckinOption:v.updateCheckinOption,removeSelfCheckinOption:v.removeCheckinOption},e)})(A)},2396);