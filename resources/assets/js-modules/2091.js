__d(function(e,r,t,a){Object.defineProperty(a,"__esModule",{value:!0});var i=r(753),o=babelHelpers.interopRequireDefault(i),l=r(44),n=r(412),s=babelHelpers.interopRequireDefault(n),u=r(271),b=babelHelpers.interopRequireDefault(u),p=r(1023),c=r(756),d=babelHelpers.interopRequireDefault(c),f=r(425),m=babelHelpers.interopRequireDefault(f),g=r(1919),k=babelHelpers.interopRequireWildcard(g),v={title:s.default.string.isRequired,imageURL:s.default.string.isRequired,actionKicker:s.default.string.isRequired,actionColor:s.default.string,itemId:s.default.number.isRequired,itemType:s.default.string.isRequired,queryParams:s.default.object},y={actionColor:m.default.dark.hof,queryParams:{}},P=function(e){function r(){var t,a,i;babelHelpers.classCallCheck(this,r);for(var o=arguments.length,n=Array(o),s=0;s<o;s++)n[s]=arguments[s];return t=a=babelHelpers.possibleConstructorReturn(this,e.call.apply(e,[this].concat(n))),a.onPressHandler=function(){var e=a.props,r=e.itemType,t=e.itemId,i=e.queryParams;switch(r){case"experience":k.navigateToExperiencePDP(t,i.productType);break;case"guidebook_insider":k.navigateToInsiderGuidebook(t);break;case"guidebook_detour":k.navigateToDetourPDP(t);break;case"guidebook_meetup":k.navigateToMeetupActivityPDP(t);break;case"guidebook_activity":k.navigateToPlaceActivityPDP(t);break;case"home":k.navigateToListingPDP(t);break;case"web_link_content":l.Linking.openURL(i.web_link_url);break;case"playlist":break;default:throw new Error("Attempt to navigate to unsupported item in RecommendationItemCard "+r)}},i=t,babelHelpers.possibleConstructorReturn(a,i)}return babelHelpers.inherits(r,e),r.prototype.render=function(){var e=this.props,r=e.title,t=e.imageURL,a=e.actionKicker,i=e.actionColor;return babelHelpers.jsx(o.default,{onPress:this.onPressHandler},void 0,babelHelpers.jsx(l.View,{style:R.container},void 0,babelHelpers.jsx(l.Image,{style:R.image,source:{uri:t}}),babelHelpers.jsx(l.Text,{style:[R.kicker,{color:i}]},void 0,a),babelHelpers.jsx(d.default,{smallBold:!0,numberOfLines:3},void 0,r)))},r}(b.default.PureComponent);a.default=P,P.propTypes=v,P.defaultProps=y;var R=p.ThemedStyleSheet.create(function(e){var r=e.size,t=e.font;return{container:{flex:1},image:{flex:1,aspectRatio:1,borderRadius:3},kicker:babelHelpers.extends({},t.micro,{marginTop:r.vertical.tiny})}})},2091);