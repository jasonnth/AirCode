__d(function(e,t,a,o){function r(){return[p.default.phrase("Family Visit",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Official Government Business",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Journalistic",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Professional Research and Professional Meetings",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Educational (including People to People)",null,"One option on a list of valid govt-required reasons for Cuban travel. People to People is the name of an organization."),p.default.phrase("Religious",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Public Performances, Clinics, Workshops, Athletic and Other Competitions, and Exhibitions",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Support for the Cuban People",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Humanitarian Projects",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Private Foundations, Research, or Educational Institutes",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Information or Information Materials",null,"One option on a list of valid govt-required reasons for Cuban travel"),p.default.phrase("Certain Export Transactions under Department of Commerce Regulations and Guidelines",null,"One option on a list of valid govt-required reasons for Cuban travel")].map(function(e,t){return{label:e,value:t}})}Object.defineProperty(o,"__esModule",{value:!0});var n=t(412),l=babelHelpers.interopRequireDefault(n),i=t(271),s=(babelHelpers.interopRequireDefault(i),t(790)),u=t(654),d=t(379),p=babelHelpers.interopRequireDefault(d),f=t(44),b=t(773),v=babelHelpers.interopRequireDefault(b),c=t(410),h=babelHelpers.interopRequireDefault(c),g=t(1366),m=babelHelpers.interopRequireDefault(g),C=t(787),R=babelHelpers.interopRequireDefault(C),H=t(1438),q=babelHelpers.interopRequireDefault(H),x=t(839),T=babelHelpers.interopRequireDefault(x),y=t(1023),O=t(1640),P=babelHelpers.interopRequireDefault(O),S=t(1573),D=babelHelpers.interopRequireWildcard(S),j=t(1569),B=babelHelpers.interopRequireWildcard(j),A={saveCubaTravelReason:l.default.func.isRequired,tripTemplateId:l.default.number.isRequired},w={},I=function(e){return(0,u.bindActionCreators)({saveCubaTravelReason:D.saveCubaTravelReason},e)},V=function(e){function t(a){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,a));return o.state={selectedTravelReasonOption:null,footerBottomAnim:new f.Animated.Value(0),footerVisible:!1},o.onNext=o.onNext.bind(o),o.onSelectedReasonChange=o.onSelectedReasonChange.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.saveCubaTravelReason=function(e){this.props.saveCubaTravelReason(e.value)},t.prototype.onNext=function(){this.saveCubaTravelReason(this.state.selectedTravelReasonOption),h.default.push(B.CUBA_GUEST_ADDRESS,{tripTemplateId:this.props.tripTemplateId})},t.prototype.onSelectedReasonChange=function(e){this.setState({selectedTravelReasonOption:e}),this.state.footerVisible||(this.setState({footerVisible:!0}),this.slideUpFooter())},t.prototype.slideUpFooter=function(){f.Animated.timing(this.state.footerBottomAnim,{toValue:1,duration:500}).start()},t.prototype.render=function(){var e=this.state.footerBottomAnim.interpolate({inputRange:[0,1],outputRange:[P.default.contentInset.bottom,0]}),t={transform:[{translateY:e}]},a=p.default.phrase("Before you go to Cuba",null,"Title for sheet collecting government-required guest info for travel to Cuba"),o=p.default.phrase("Since you\u2019re headed to Cuba, we need to collect a few extra details before we can send your information to the host.",null,"subtitle for sheet collecting government-required info for travel to Cuba"),n=p.default.phrase("The purpose of travel to Cuba",null,"Header for a legal declaration on sheet collecting government-required info for Cuba travel"),l=p.default.phrase("I certify that my travel to Cuba satisfies the criteria for the general license identified below.",null,"Body of a legal declaration on a sheet collecting government-required info for Cuba travel");return babelHelpers.jsx(f.View,{style:f.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(R.default,{withInsets:[P.default]},void 0,babelHelpers.jsx(f.View,{style:E.mainContent},void 0,babelHelpers.jsx(v.default,{title:a,subtitle:o,padSubtitle:!1}),babelHelpers.jsx(m.default,{},void 0,babelHelpers.jsx(f.Text,{style:E.declarationHeader},void 0,n),babelHelpers.jsx(f.Text,{style:E.declarationBody},void 0,l)),babelHelpers.jsx(q.default,{options:r(),activeOption:this.state.selectedTravelReasonOption,onChange:this.onSelectedReasonChange}))),babelHelpers.jsx(f.Animated.View,{style:[t,E.footer]},void 0,babelHelpers.jsx(P.default,{buttonTextLike:!0,buttonText:p.default.phrase("Next",null,"Next button text"),onButtonPress:this.onNext})))},t}(i.PureComponent);V.propTypes=A,V.defaultProps=w,o.default=(0,s.connect)(null,I)(V);var E=y.ThemedStyleSheet.create(function(e){var t=e.bp,a=e.font;return{declarationHeader:a.regularPlus,declarationBody:babelHelpers.extends({},a.regular,{marginTop:3*t,marginBottom:t}),footer:babelHelpers.extends({position:"absolute",bottom:-2,left:-1,right:-1},T.default.footer,{paddingLeft:1,paddingRight:1}),mainContent:{marginBottom:-3}}})},1756);