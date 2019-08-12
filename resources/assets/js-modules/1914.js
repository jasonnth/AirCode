__d(function(e,t,a,r){function s(e){return e?m.default.phrase("Edit",null,"CTA to edit text input row content"):m.default.phrase("Add",null,"CTA to add text input row content")}Object.defineProperty(r,"__esModule",{value:!0});var i=t(790),l=t(412),n=babelHelpers.interopRequireDefault(l),o=t(271),u=(babelHelpers.interopRequireDefault(o),t(44)),d=t(759),p=babelHelpers.interopRequireDefault(d),c=t(379),m=babelHelpers.interopRequireDefault(c),f=t(377),h=babelHelpers.interopRequireDefault(f),b=t(422),g=babelHelpers.interopRequireDefault(b),y=t(787),x=babelHelpers.interopRequireDefault(y),H=t(814),v=babelHelpers.interopRequireDefault(H),P=t(816),R=babelHelpers.interopRequireDefault(P),C=t(410),A=babelHelpers.interopRequireDefault(C),T=t(1050),_=babelHelpers.interopRequireDefault(T),q=t(773),S=babelHelpers.interopRequireDefault(q),D=t(1352),w=babelHelpers.interopRequireDefault(D),E=t(781),M=babelHelpers.interopRequireDefault(E),G=t(832),F=babelHelpers.interopRequireDefault(G),j=t(824),I=babelHelpers.interopRequireDefault(j),k=t(1482),U=babelHelpers.interopRequireWildcard(k),z=t(1870),L=t(376),N=babelHelpers.interopRequireDefault(L),B=t(705),O=babelHelpers.interopRequireDefault(B),V=t(1875),K=t(1874),Q={categories:n.default.array.isRequired,maxPurchaseAmount:n.default.number.isRequired,maxPurchaseCurrency:n.default.string.isRequired,stepperAmount:n.default.number.isRequired,termsUrl:n.default.string.isRequired,image:v.default.isRequired,overlayId:n.default.number.isRequired,recipientName:n.default.string.isRequired,selectedCategoryIndex:n.default.number.isRequired,videoId:n.default.number.isRequired},W={},Y=function(e){function t(a){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,a));r.onPrimaryButtonPress=r.onPrimaryButtonPress.bind(r),r.getPriceString=r.getPriceString.bind(r);var s=O.default.launch("mobile.giftcards_minimal_value_testing")?1:r.props.stepperAmount,i=m.default.phrase("Recipient\u2019s email",null,"Default label to prompt user to add the recipient\u2019s email address");return r.state={selectedAmount:s,emailTitle:i,email:null,personalMessage:"",toastMessage:null,hasTextFocus:!1},r}return babelHelpers.inherits(t,e),t.prototype.getPriceString=function(){return h.default.priceString(this.state.selectedAmount,this.props.maxPurchaseCurrency)},t.prototype.validateEmail=function(){var e=null;return this.state.email?-1===this.state.email.indexOf("@")?e=m.default.phrase("Please enter a valid email address.",null,"error message when there is an incorrectly formatted email address"):N.default.user().email&&this.state.email.toUpperCase()===N.default.user().email.toUpperCase()&&(e=m.default.phrase("We do not currently support sending gift cards to yourself.",null,"error message when you enter your own email as the gift recipient")):e=m.default.phrase("Please enter an email address to send a gift card.",null,"error message when there is no email address"),e},t.prototype.validateForm=function(){var e=this,t=this.validateEmail();return this.state.toastMessage?this.setState({toastMessage:null},function(){e.setState({toastMessage:t})}):this.setState({toastMessage:t}),(0,V.logGiftingFlowClickCheckOutEvent)(t,this.props.maxPurchaseCurrency,this.state.selectedAmount,this.state.personalMessage),!t},t.prototype.startQuickPay=function(){var e={amount:this.state.selectedAmount,currency:this.props.maxPurchaseCurrency},t={cart_item:{title:m.default.phrase("%{giftCreditAmount} gift card",{giftCreditAmount:this.getPriceString()},"Title to describe that you are purchasing a gift card worth %{giftCreditAmount}"),description:this.state.email,thumbnail_url:this.props.image,billing_parameters:{recipient_name:this.props.recipientName,recipient_email:this.state.email,recipient_message:this.state.personalMessage,gift_amount:e,category_type:this.props.categories[this.props.selectedCategoryIndex].type,locale:N.default.localeLanguage()+"_"+N.default.localeCountry(),overlay_id:this.props.overlayId,video_id:this.props.videoId,product_type:K.GIFT_CREDIT_PRODUCT_TYPE}},quickpay_client:U.GIFTCARDS_QUICK_PAY,terms_url:this.props.termsUrl};A.default.push(U.GIFTCARDS_QUICK_PAY,t)},t.prototype.onPrimaryButtonPress=function(){this.validateForm()&&this.startQuickPay()},t.prototype.render=function(){var e=this,t=this.props.recipientName;return babelHelpers.jsx(u.View,{style:X.container},void 0,babelHelpers.jsx(x.default,{style:X.container},void 0,babelHelpers.jsx(A.default.Screen,{link:m.default.phrase("Cancel",null,"Navigator link text to cancel gift card creation and return to home page."),onLinkPress:function(){return A.default.dismiss()}}),babelHelpers.jsx(S.default,{title:m.default.phrase("Almost finished...",null,"Page title for the gift cards details page to prompt user for gift card amount, recipient email, and personal message")}),babelHelpers.jsx(R.default,{divider:"full",title:this.getPriceString(),subtitle:m.default.phrase("Gift amount",null,"Label text to prompt user to select a gift card amount (via an inline stepper)"),value:this.state.selectedAmount,onChange:function(t){return e.setState({selectedAmount:t})},increment:this.props.stepperAmount,minValue:this.props.stepperAmount,maxValue:this.props.maxPurchaseAmount}),babelHelpers.jsx(_.default,{divider:"full",returnKeyType:"done",name:this.state.emailTitle,label:m.default.phrase("Email Address",null,"Sheet subtitle to prompt user to add the recipient\u2019s email address"),sheetTitle:m.default.phrase("What\u2019s %{name}\u2019s email?",{name:t},"Sheet title to prompt user to add the %{name}\u2019s email address"),valueLabel:s(this.state.email),value:this.state.email,onChangeText:function(t){(0,V.logGiftingFlowClickNextRecipientEmailEvent)(),e.setState({email:t,emailTitle:t})},onSheetPresented:function(){(0,V.logGiftingFlowAddRecipientEmailAddressEvent)(),(0,V.logGiftingFlowRecipientEmailImpressionEvent)("row")}}),babelHelpers.jsx(u.Text,{style:X.charactersText},void 0,K.MAX_MESSAGE_LENGTH-this.state.personalMessage.length),babelHelpers.jsx(I.default,{style:X.descriptionText,multiline:!0,blurOnSubmit:!0,returnKeyType:"done",maxLength:K.MAX_MESSAGE_LENGTH,placeholder:m.default.phrase("Write a note to %{name}",{name:t},"Placeholder label to prompt user to add a personalized message to their gift card"),onChangeText:function(t){return e.setState({personalMessage:t})},onFocus:function(){e.setState({hasTextFocus:!0}),(0,V.logGiftingFlowAddPersonalMessageEvent)()},onBlur:function(){return e.setState({hasTextFocus:!1})}})),babelHelpers.jsx(u.View,{},void 0,!this.state.hasTextFocus&&babelHelpers.jsx(u.Text,{style:X.disclosureText},void 0,m.default.phrase("Gift Cards can only be purchased by or gifted to individuals in the United States. Gift Cards do not expire.",null,"Legal terms and conditions disclosure")),babelHelpers.jsx(M.default,{withInsets:[F.default],style:X.toast},void 0,this.state.toastMessage&&babelHelpers.jsx(w.default,{description:this.state.toastMessage,onClosePress:function(){return e.setState({toastMessage:null})}})),babelHelpers.jsx(F.default,{title:this.getPriceString(),subtitle:"Gift amount",buttonText:m.default.phrase("Confirm and Pay",null,"Primary button CTA to navigate to payments checkout page after setting gift card details"),onButtonPress:this.onPrimaryButtonPress})))},t}(o.PureComponent);Y.defaultProps=W,Y.propTypes=Q;var X=g.default.create(function(e){var t=e.color,a=e.font,r=e.size;return{container:{flex:1},charactersText:babelHelpers.extends({},a.smallMuted,{marginHorizontal:r.baseRow.paddingHorizontal,paddingTop:r.vertical.small,textAlign:"right"}),disclosureText:babelHelpers.extends({},a.small,{paddingHorizontal:r.baseRow.paddingHorizontal,paddingVertical:r.vertical.small}),toast:{backgroundColor:t.white},descriptionText:{paddingHorizontal:r.baseRow.paddingHorizontal}}});r.default=(0,i.connect)(function(e){return(0,p.default)((0,z.creationSelector)(e),["categories","maxPurchaseAmount","maxPurchaseCurrency","stepperAmount","termsUrl"])})(Y)},1914);