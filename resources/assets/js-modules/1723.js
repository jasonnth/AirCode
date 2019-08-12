__d(function(e,t,s,a){Object.defineProperty(a,"__esModule",{value:!0});var n=t(412),r=babelHelpers.interopRequireDefault(n),i=t(271),o=babelHelpers.interopRequireDefault(i),l=t(790),u=t(654),p=t(379),d=babelHelpers.interopRequireDefault(p),b=t(1352),f=babelHelpers.interopRequireDefault(b),g=t(824),h=babelHelpers.interopRequireDefault(g),T=t(422),c=babelHelpers.interopRequireDefault(T),S=t(1433),m=babelHelpers.interopRequireDefault(S),H=t(410),_=babelHelpers.interopRequireDefault(H),q=t(772),M=babelHelpers.interopRequireDefault(q),E=t(684),x=t(1573),k=t(1724),A=t(1569),R=t(1483),y=t(1575),D={sendMessageToHost:r.default.func.isRequired,templateId:r.default.number.isRequired,isSendingMessage:r.default.bool.isRequired,tripTemplate:r.default.object.isRequired,addToast:r.default.func.isRequired},P=function(e,t){return babelHelpers.extends({},(0,k.getTripTemplateFromTripTemplateId)(e,t.templateId),{isSendingMessage:(0,k.isSendingMessageSelector)(e)})},C=function(e){return(0,u.bindActionCreators)({sendMessageToHost:x.sendMessageToHost,addToast:E.addToast},e)},F=function(e){function t(s,a){babelHelpers.classCallCheck(this,t);var n=babelHelpers.possibleConstructorReturn(this,e.call(this,s,a));return n.state={message:null,attemptedToSendEmptyMessage:!1},n.onChangeText=n.onChangeText.bind(n),n.onSendMessage=n.onSendMessage.bind(n),n.onFaqLinkPress=n.onFaqLinkPress.bind(n),n}return babelHelpers.inherits(t,e),t.prototype.onChangeText=function(e){this.setState({message:e})},t.prototype.onFaqLinkPress=function(){_.default.push(A.SIMPLE_WEB_VIEW,{uri:(0,R.getWebUrl)("/experiences/faq",{template_id:this.props.templateId})})},t.prototype.onSendMessage=function(){var e=this.props,t=e.isSendingMessage,s=e.templateId,a=this.state.message;if(!a)return void this.props.addToast(y.EMPTY_MESSAGE_TOAST);t||this.props.sendMessageToHost({template_id:s,message:a})},t.prototype.render=function(){var e=this.props.tripTemplate.experience_host_profile.host,t=d.default.phrase("Introduce yourself to %{host_name}",{host_name:e.first_name},"Title for contact host page"),s=d.default.phrase("Have general questions about how experiences work?",{},"Text that asks users if they have questions about how experiences work. Followed by Visit our FAQ"),a=d.default.phrase("Visit our FAQ",{},"Call to action link to that links user to frequently asked questions page"),n=d.default.phrase("Try again",null,"a button asking user to try again after something went wrong");return babelHelpers.jsx(M.default,{marquee:babelHelpers.jsx(m.default,{title:t,subtitle:s,subtitleLink:a,onSubtitleLinkPress:this.onFaqLinkPress,host:e}),toasts:[babelHelpers.jsx(f.default,{id:y.SEND_MESSAGE_FAILED_TOAST,description:d.default.phrase("Send message failed",null,"an error message"),action:n,onActionPress:this.onSendMessage},y.SEND_MESSAGE_FAILED_TOAST),babelHelpers.jsx(f.default,{id:y.EMPTY_MESSAGE_TOAST,description:d.default.phrase("Please write a message before sending",null,"an error message")},y.EMPTY_MESSAGE_TOAST)]},void 0,babelHelpers.jsx(_.default.Screen,{link:d.default.phrase("Send",{},"Send as in to send the message"),onLinkPress:this.onSendMessage}),babelHelpers.jsx(h.default,{style:I.message,autoFocus:!0,multiline:!0,onChangeText:this.onChangeText}))},t}(o.default.Component),I=c.default.create(function(e){return{message:{marginTop:2*e.bp,paddingHorizontal:e.size.baseRow.paddingHorizontal}}});F.propTypes=D,a.default=(0,l.connect)(P,C)(F)},1723);