__d(function(e,t,r,s){Object.defineProperty(s,"__esModule",{value:!0});var l=t(412),o=babelHelpers.interopRequireDefault(l),a=t(271),n=babelHelpers.interopRequireDefault(a),i=t(778),p=babelHelpers.interopRequireDefault(i),u=t(910),b=babelHelpers.interopRequireDefault(u),d=t(893),h=babelHelpers.interopRequireDefault(d),f=t(787),c=babelHelpers.interopRequireDefault(f),H=t(890),P=babelHelpers.interopRequireDefault(H),R=t(813),g=babelHelpers.interopRequireDefault(R),M=t(379),m=babelHelpers.interopRequireDefault(M),C=t(772),q=babelHelpers.interopRequireDefault(C),v=t(2428),D=babelHelpers.interopRequireDefault(v),N=t(2429),T=babelHelpers.interopRequireDefault(N),x=t(2422),y=t(1755),j=babelHelpers.interopRequireDefault(y),V=t(2414),E=t(2423),I=babelHelpers.interopRequireDefault(E),O={trip:V.TripPropShape.isRequired,tripType:o.default.string,isHostView:o.default.bool},U={theme:o.default.string},_=function(e){function t(r){babelHelpers.classCallCheck(this,t);var s=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return s.state={contactModalOpen:!1},s.renderMarquee=s.renderMarquee.bind(s),s.onPressMessage=s.onPressMessage.bind(s),s.onPressCall=s.onPressCall.bind(s),s.onVisibleChange=s.onVisibleChange.bind(s),s.renderContactModal=s.renderContactModal.bind(s),s}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){(0,x.logTripHelpLoad)()},t.prototype.onVisibleChange=function(e){this.setState({contactModalOpen:e})},t.prototype.onPressMessage=function(){var e="airbnb://d/inbox?id="+this.props.trip.threadId;(0,j.default)(e)},t.prototype.onPressCall=function(){var e=this.props.trip.otherUserPhoneNumbers;e&&1===e.length?(0,x.onPhoneNumberClick)(e[0]):this.setState({contactModalOpen:!0})},t.prototype.renderMarquee=function(){var e=null;return e=this.props.isHostView?m.default.phrase("Reservation Help",null,"title for help center marquee of reservation help"):m.default.phrase("Trip Help",null,"title for help center marquee of trip help"),babelHelpers.jsx(D.default,{title:e,trip:this.props.trip,isHostView:this.props.isHostView})},t.prototype.renderContactModal=function(){var e=this.props.trip,t=e.otherUserName,r=e.otherUserPhoneNumbers;return babelHelpers.jsx(P.default,{visible:this.state.contactModalOpen,dark:!1,onVisibleChange:this.onVisibleChange},void 0,babelHelpers.jsx(q.default,{title:m.default.phrase("Contact %{otherUserName}",{otherUserName:t},"title for contacting user modal section header")},void 0,r.map(function(e){return babelHelpers.jsx(g.default,{title:e,icon:"phone-outbound",onPress:function(){return(0,x.onPhoneNumberClick)(e)}},e)})))},t.prototype.render=function(){var e=this.props.trip,t=e.otherUserName,r=e.otherUserPhoneNumbers,s=this.props.isHostView,l=null;l=this.props.isHostView?m.default.phrase("Need to contact your guest?",null,"title for contacting host section header"):m.default.phrase("Contact host, %{otherUserName}",{otherUserName:t},"title for contacting guest section header");var o=null;return o=this.props.tripType!==V.TRIP_TYPES.PENDING&&this.props.tripType!==V.RESERVATION_TYPES.PENDING&&r?babelHelpers.jsx(h.default,{},void 0,babelHelpers.jsx(h.default.Button,{icon:"envelope",label:m.default.phrase("Message",null,"button for message host"),onPress:this.onPressMessage}),babelHelpers.jsx(h.default.Button,{icon:"phone-alt",label:m.default.phrase("Call",null,"button for call host"),onPress:this.onPressCall})):babelHelpers.jsx(h.default,{},void 0,babelHelpers.jsx(h.default.Button,{icon:"envelope",label:m.default.phrase("Message",null,"button for message host"),onPress:this.onPressMessage})),babelHelpers.jsx(c.default,{},void 0,this.renderMarquee(),babelHelpers.jsx(b.default,{first:!0,micro:!0,title:l}),babelHelpers.jsx(p.default,{},void 0,o),n.default.createElement(T.default,this.props),this.renderContactModal(),babelHelpers.jsx(I.default,{trip:this.props.trip,category:s?V.TRIP_MODE.RESERVATION:V.TRIP_MODE.TRIP}))},t}(n.default.Component);s.default=_,_.propTypes=O,_.contextTypes=U},2427);