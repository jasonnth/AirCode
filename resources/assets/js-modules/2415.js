__d(function(e,t,r,s){var i=t(412),n=babelHelpers.interopRequireDefault(i),o=t(271),a=babelHelpers.interopRequireDefault(o),l=t(790),p=t(654),u=t(44),c=t(2154),d=babelHelpers.interopRequireDefault(c),f=t(2416),h=babelHelpers.interopRequireDefault(f),b=t(2418),T=babelHelpers.interopRequireDefault(b),g=t(410),H=babelHelpers.interopRequireDefault(g),C=t(422),R=babelHelpers.interopRequireDefault(C),v=t(910),S=babelHelpers.interopRequireDefault(v),y=t(830),P=babelHelpers.interopRequireDefault(y),V=t(810),m=babelHelpers.interopRequireDefault(V),q=t(778),I=babelHelpers.interopRequireDefault(q),x=t(1038),w=babelHelpers.interopRequireDefault(x),F=t(379),j=babelHelpers.interopRequireDefault(F),D=t(759),E=babelHelpers.interopRequireDefault(D),A=t(841),k=babelHelpers.interopRequireDefault(A),_=t(2419),G=babelHelpers.interopRequireWildcard(_),L=t(2421),M=t(2422),O=t(2423),Y=babelHelpers.interopRequireDefault(O),B=t(2414),N=t(1921),W=babelHelpers.interopRequireWildcard(N),U=t(2424),z={fetchHomePageContents:n.default.func.isRequired,fetchSuggestedTopics:n.default.func.isRequired,userId:n.default.oneOfType([n.default.number,n.default.string]),isHostView:n.default.bool,isFetchingTripCards:n.default.bool.isRequired,isFetchingTopics:n.default.bool.isRequired,tripsLimit:n.default.number,isGuest:n.default.bool,isHost:n.default.bool,isGuestAndHost:n.default.bool,tripCards:n.default.object,topics:n.default.array,hostTopics:n.default.array,guestTopics:n.default.array,onIsInteractive:n.default.func.isRequired},J={tripsLimit:10,tripCards:null,isGuest:!1,isHost:!1},K=function(e){function t(r){babelHelpers.classCallCheck(this,t);var s=babelHelpers.possibleConstructorReturn(this,e.call(this,r));return s.state={isHostView:r.isHostView,recentTrip:null,recentTripCategory:null,recentTripMode:null,recentReservation:null,recentReservationCategory:null,inHelpCenterV3:!(0,U.inHelpCenterReserveGroup)()&&(0,U.inHelpCenterV3Phase1Treatment)()},s.renderFirstTripCard=s.renderFirstTripCard.bind(s),s.onClickSeeAll=s.onClickSeeAll.bind(s),s.renderTopics=s.renderTopics.bind(s),s.onClickSearch=s.onClickSearch.bind(s),s.onTabChanged=s.onTabChanged.bind(s),s}return babelHelpers.inherits(t,e),t.prototype.componentWillUpdate=function(e){var t=this;if(this.props.isFetchingTripCards&&!e.isFetchingTripCards){if(e.isGuest){var r=e.tripCards,s=null,i=null;Object.keys(B.TRIP_TYPES).some(function(e){var n=B.TRIP_TYPES[e];return!!(r[n]&&r[n].length>0)&&(s=n,i=r[n][0],t.setState({recentTrip:i,recentTripCategory:s,recentTripMode:B.TRIP_MODE.TRIP}),!0)})}if(e.isHost){var n=e.tripCards,o=null,a=null;Object.keys(B.RESERVATION_TYPES).some(function(e){var r=B.RESERVATION_TYPES[e];return!!(n[r]&&n[r].length>0)&&(o=r,a=n[r][0],t.setState({recentReservation:a,recentReservationCategory:o,recentTripMode:B.TRIP_MODE.RESERVATION}),!0)})}}},t.prototype.componentDidMount=function(){this.props.fetchHomePageContents(this.props.userId,this.props.tripsLimit),this.props.fetchSuggestedTopics(this.state.isHostView,this.state.inHelpCenterV3),(0,M.logHomeScreenLoad)(this.props.userId)},t.prototype.componentDidUpdate=function(e){var t=e.isFetchingTripCards,r=e.isFetchingTopics,s=t||r,i=!this.props.isFetchingTripCards&&!this.props.isFetchingTopics;s&&i&&this.props.onIsInteractive()},t.prototype.onClickSeeAll=function(){var e=this.props.tripCards;this.state.isHostView&&e.listings&&e.listings.length>1?H.default.push(W.LISTINGS,{listings:e.listings}):H.default.push(W.TRIP_CARDS,{isHostView:this.state.isHostView})},t.onPressTripCard=function(e,t,r){H.default.present(W.TRIP_HELP,{trip:babelHelpers.extends({},e,t),tripType:r,isHostView:!1})},t.onPressReservationCard=function(e,t,r){H.default.present(W.TRIP_HELP,{trip:babelHelpers.extends({},e,t),tripType:r,isHostView:!0})},t.prototype.renderFirstTripCard=function(e){var r=this,s=this.props,i=s.isGuest;if(!s.tripCards)return null;if(void 0===e&&i||1===e){var n=this.state,o=n.recentTripCategory,l=n.recentTrip;if(o){var p=(0,M.getTripType)(o),c=(0,M.getTripStatus)(l,o),d=null;return d=null===p?j.default.phrase("Your trip",null,"section title for first trip card"):j.default.phrase("Your %{tripType} trip",{tripType:p},"section title for first trip card"),babelHelpers.jsx(u.View,{},void 0,babelHelpers.jsx(S.default,{first:!0,micro:!0,heavy:!0,babuButton:!0,button:j.default.phrase("Change",null,"title for switch trip card"),title:d,onButtonPress:function(){return r.onClickSeeAll()}}),a.default.createElement(h.default,babelHelpers.extends({},l,c,{helpFooter:!0,onPressTripCard:function(){return t.onPressTripCard(l,c,o)}})))}}else{var f=this.state,b=f.recentReservation,g=f.recentReservationCategory;if(g){var H=(0,M.getReservationStatus)(b,g),C=(0,M.getReservationType)(g);return babelHelpers.jsx(u.View,{},void 0,babelHelpers.jsx(S.default,{first:!0,micro:!0,heavy:!0,title:j.default.phrase("Your %{reservationType} reservation",{reservationType:C},"section title for first reservation card"),babuButton:!0,button:j.default.phrase("Change",null,"title for switching reservation card"),onButtonPress:function(){return r.onClickSeeAll()}}),a.default.createElement(T.default,babelHelpers.extends({},b,H,{onPressReservationCard:function(){return t.onPressReservationCard(b,H,g)},helpFooter:!0})))}}return null},t.onClickTopic=function(e){H.default.push(W.ARTICLES,{faqs:babelHelpers.extends({},e)})},t.prototype.onTabChanged=function(e){this.setState({isHostView:0===e}),this.state.inHelpCenterV3&&(0!==e||this.props.hostTopics&&0!==this.props.hostTopics.length?1!==e||this.props.guestTopics&&0!==this.props.guestTopics.length||this.props.fetchSuggestedTopics(!1,!0):this.props.fetchSuggestedTopics(!0,!0))},t.prototype.renderTopics=function(){var e=[],r=this.props,s=r.topics,i=r.guestTopics,n=r.hostTopics,o=s;return this.state.inHelpCenterV3&&(o=this.state.isHostView?n:i),0===o.length?e:(o.forEach(function(r){e.push(babelHelpers.jsx(m.default,{title:r.faqCategory.localizedName,onPress:function(){return t.onClickTopic(r.faqs)}},r.faqCategory.id))}),babelHelpers.jsx(u.View,{},void 0,babelHelpers.jsx(S.default,{first:!0,micro:!0,heavy:!0,title:j.default.phrase("Browse help topics",null,"section header for suggested topics")}),babelHelpers.jsx(I.default,{},void 0,e)))},t.prototype.onClickSearch=function(){(0,M.logSearchClick)(this.props.userId),H.default.push(W.SEARCH)},t.prototype.setTrailerRef=function(e){this.trailer=e},t.prototype.renderTabContent=function(e){var t=this.props,r=t.isFetchingTripCards;return t.isFetchingTopics||r?babelHelpers.jsx(k.default,{}):babelHelpers.jsx(u.ScrollView,{style:{flex:1}},void 0,this.renderFirstTripCard(e),this.renderTopics(),babelHelpers.jsx(Y.default,{userId:this.props.userId,trip:0===e?this.state.recentReservation:this.state.recentTrip,category:this.state.recentTripMode}))},t.prototype.renderTabSplitView=function(){var e=this;return babelHelpers.jsx(d.default,{style:u.StyleSheet.absoluteFill,tabNames:[j.default.phrase("Hosting",null,"used as tab header string to denote hosting mode"),j.default.phrase("Traveling",null,"used as tab header string to denote traveling mode")],renderTabContent:function(t){return e.renderTabContent(t)},initialFocusedTab:this.state.isHostView?0:1,onTabChanged:function(t){return e.onTabChanged(t)}})},t.prototype.renderSingleView=function(){return babelHelpers.jsx(u.ScrollView,{style:Q.container},void 0,this.renderFirstTripCard(),this.renderTopics(),babelHelpers.jsx(Y.default,{userId:this.props.userId,trip:this.state.recentTrip,category:this.state.recentTripMode}))},t.prototype.render=function(){var e=this.props,t=e.isGuestAndHost;return e.isFetchingTripCards?babelHelpers.jsx(k.default,{}):babelHelpers.jsx(u.View,{style:u.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(H.default.Screen,{barType:H.default.Screen.BAR_TYPE.BASIC}),a.default.createElement(P.default,{ref:this.setTrailerRef},babelHelpers.jsx(w.default,{icon:"search",onPress:this.onClickSearch})),t||this.state.inHelpCenterV3?this.renderTabSplitView():this.renderSingleView())},t}(a.default.Component);K.defaultProps=J,K.propTypes=z;var Q=R.default.create(function(e){return{container:{marginTop:7*e.bp,flex:1}}});r.exports=(0,l.connect)(function(e){return(0,E.default)((0,L.homeScreenSelector)(e),["isFetchingTripCards","isFetchingTopics","isGuest","isHost","isGuestAndHost","tripCards","topics","hostTopics","guestTopics"])},function(e){return(0,p.bindActionCreators)({fetchHomePageContents:G.fetchHomePageContents,fetchSuggestedTopics:G.fetchSuggestedTopics},e)})(K)},2415);