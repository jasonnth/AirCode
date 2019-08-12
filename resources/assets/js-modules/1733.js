__d(function(e,t,r,l){Object.defineProperty(l,"__esModule",{value:!0});var i=t(271),o=babelHelpers.interopRequireDefault(i),n=t(834),a=t(44),s=t(379),p=babelHelpers.interopRequireDefault(s),d=t(1023),u=t(1366),b=babelHelpers.interopRequireDefault(u),c=t(756),f=babelHelpers.interopRequireDefault(c),h=t(1413),m=babelHelpers.interopRequireDefault(h),x=t(1734),y=babelHelpers.interopRequireDefault(x),T=t(1735),g=babelHelpers.interopRequireDefault(T),v=t(1680),H=t(1732),R={divider:n.Types.string,bookOneSelected:n.Types.bool,bookMoreSelected:n.Types.bool,onPressBookOne:n.Types.func,onPressBookMore:n.Types.func,scheduledTripTemplate:n.Types.object.isRequired,tripTemplate:n.Types.object.isRequired},j={divider:null,bookOneSelected:!1,bookMoreSelected:!1,onPressBookOne:function(){},onPressBookMore:function(){}},D=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.hasAdditionalSpot=function(){return this.props.scheduledTripTemplate.remaining_capacity>1},t.prototype.renderImmersionContent=function(e){var t=this.props,r=t.scheduledTripTemplate,l=t.tripTemplate.experiences,i=r.scheduled_experiences;return babelHelpers.jsx(a.View,{},void 0,babelHelpers.jsx(y.default,{collapsedContent:babelHelpers.jsx(a.View,{style:k.textContainer},void 0,babelHelpers.jsx(f.default,{large:!0,style:k.title},void 0,(0,v.localizedTripTemplateDateRange)(r)),(0,H.shouldDisplayUrgency)(r)&&babelHelpers.jsx(f.default,{small:!0},void 0,(0,H.urgencyTextOfScheduledTemplate)(r)))},void 0,i.map(function(e){return babelHelpers.jsx(g.default,{scheduledExperience:e,experiences:l},e.experience_id)})),e)},t.prototype.renderSingleExperienceContent=function(e){var t=this.props.scheduledTripTemplate,r=t.scheduled_experiences,l=p.default.phrase("%{date} \xb7 %{timeRange}",{date:(0,v.localizedExperienceDate)(r[0]),timeRange:(0,v.localizedExperienceTimeRange)(r[0],!1)},"A time range within a single day. The date and time range will be locally formatted.");return babelHelpers.jsx(a.View,{},void 0,babelHelpers.jsx(f.default,{large:!0,style:k.title},void 0,l),(0,H.shouldDisplayUrgency)(t)&&babelHelpers.jsx(f.default,{small:!0},void 0,(0,H.urgencyTextOfScheduledTemplate)(t)),e)},t.prototype.render=function(){var e=this.props,t=e.tripTemplate,r=e.divider,l=e.bookMoreSelected,i=t.experiences,o=babelHelpers.jsx(a.View,{style:k.buttonRow},void 0,babelHelpers.jsx(a.View,{style:k.buttonWrapper},void 0,babelHelpers.jsx(m.default,{small:!0,inverse:!l,onPress:this.props.onPressBookMore},void 0,p.default.phrase("Book",null,"buy more a spot on this trip for yourself and others"))));return babelHelpers.jsx(b.default,{divider:r},void 0,1===i.length?this.renderSingleExperienceContent(o):this.renderImmersionContent(o))},t}(o.default.Component);l.default=D,D.propTypes=R,D.defaulProps=j;var k=d.ThemedStyleSheet.create(function(e){var t=e.size;return{title:{marginBottom:t.vertical.tiny,marginRight:t.horizontal.small},textContainer:{flex:1,flexWrap:"wrap",flexDirection:"column"},buttonRow:{paddingTop:t.vertical.medium,flexDirection:"row",flex:1,flexWrap:"wrap",alignItems:"flex-start"},buttonWrapper:{flex:0,paddingTop:t.vertical.tiny,paddingRight:t.horizontal.tiny},firstButton:{paddingRight:t.horizontal.tiny}}})},1733);