__d(function(e,t,s,a){Object.defineProperty(a,"__esModule",{value:!0});var l=t(412),n=babelHelpers.interopRequireDefault(l),r=t(271),i=babelHelpers.interopRequireDefault(r),o=t(686),u=babelHelpers.interopRequireDefault(o),p=t(379),b=babelHelpers.interopRequireDefault(p),d=t(824),f=babelHelpers.interopRequireDefault(d),g=t(422),h=babelHelpers.interopRequireDefault(g),c=t(410),H=babelHelpers.interopRequireDefault(c),m=t(772),v=babelHelpers.interopRequireDefault(m),R={title:n.default.string.isRequired,subtitle:n.default.string,eventName:n.default.object.isRequired,eventData:n.default.object.isRequired},q={subtitle:null},D=function(e){function t(s,a){babelHelpers.classCallCheck(this,t);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,s,a));return l.state={message:null,containerHeight:null},l.onChangeText=l.onChangeText.bind(l),l.onSendMessage=l.onSendMessage.bind(l),l}return babelHelpers.inherits(t,e),t.prototype.onSendMessage=function(){this.state.message&&(u.default.logEvent({event_name:this.props.eventName,event_data:babelHelpers.extends({},this.props.eventData,{message:this.state.message})}),H.default.pop())},t.prototype.onChangeText=function(e){this.setState({message:e})},t.prototype.render=function(){return babelHelpers.jsx(v.default,{title:this.props.title,subtitle:this.props.subtitle},void 0,babelHelpers.jsx(H.default.Screen,{link:b.default.phrase("Send",null,"Send as in to send the message"),onLinkPress:this.onSendMessage}),babelHelpers.jsx(f.default,{style:x.message,autoFocus:!0,multiline:!0,onChangeText:this.onChangeText}))},t}(i.default.Component);a.default=D;var x=h.default.create(function(e){var t=e.size;return{message:{marginTop:t.vertical.small,paddingHorizontal:t.baseRow.paddingHorizontal}}});D.propTypes=R,D.defaultProps=q},2128);