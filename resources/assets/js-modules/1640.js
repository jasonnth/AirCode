__d(function(e,t,n,i){Object.defineProperty(i,"__esModule",{value:!0});var o=t(271),r=babelHelpers.interopRequireDefault(o),l=t(834),a=t(379),s=babelHelpers.interopRequireDefault(a),u=t(44),b=t(840),d=t(841),p=babelHelpers.interopRequireDefault(d),c=t(1413),f=babelHelpers.interopRequireDefault(c),x=t(1641),y=babelHelpers.interopRequireDefault(x),m=t(839),g=babelHelpers.interopRequireDefault(m),h=t(422),T=babelHelpers.interopRequireDefault(h),v=t(905),H=babelHelpers.interopRequireDefault(v),w=t(811),C=babelHelpers.interopRequireDefault(w),V=t(814),j=babelHelpers.interopRequireDefault(V),k={currentlyViewingCount:l.Types.number,showCurrentlyViewingCount:l.Types.bool,title:l.Types.string,secondaryTitle:l.Types.string,subtitle:l.Types.string,subtitleNumberOfLines:l.Types.number,buttonActive:l.Types.bool,buttonText:l.Types.string.isRequired,buttonTextLike:l.Types.bool,infoTextLinkLike:l.Types.bool,onButtonPress:l.Types.func,onInfoTextPress:l.Types.func,loading:l.Types.bool,buttonDark:l.Types.bool,buttonMedium:l.Types.bool,starRating:l.Types.number,iconForTitle:j.default,buttonSvg:l.Types.string},R={buttonActive:!0,buttonTextLike:!1,currentlyViewingCount:null,infoTextLinkLike:!1,onButtonPress:function(){},onInfoTextPress:function(){},loading:!1,showCurrentlyViewingCount:!1,subtitle:null,subtitleNumberOfLines:1,title:null,buttonDark:!1,buttonMedium:!1},S=function(e){function t(n){babelHelpers.classCallCheck(this,t);var i=babelHelpers.possibleConstructorReturn(this,e.call(this,n));return i.mounted=!1,i.state={animatedValueForInterestedText:new u.Animated.Value(0),animatedValueForSubtitle:new u.Animated.Value(1)},i}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){var e=this.props.showCurrentlyViewingCount;this.mounted=!0,e&&this.startAnimating()},t.prototype.componentWillUnmount=function(){this.mounted=!1},t.prototype.startAnimating=function(){var e=this;this.mounted&&u.Animated.sequence([u.Animated.delay(1800),u.Animated.timing(this.state.animatedValueForSubtitle,{duration:300,toValue:0}),u.Animated.timing(this.state.animatedValueForInterestedText,{duration:300,toValue:1}),u.Animated.delay(1800),u.Animated.timing(this.state.animatedValueForInterestedText,{duration:300,toValue:0}),u.Animated.timing(this.state.animatedValueForSubtitle,{duration:300,toValue:1})]).start(function(){e.startAnimating()})},t.prototype.animatedStyleForSubtitle=function(){return{opacity:this.state.animatedValueForSubtitle.interpolate({inputRange:[0,1],outputRange:[0,1]})}},t.prototype.animatedStyleForInterestedText=function(){return{opacity:this.state.animatedValueForInterestedText.interpolate({inputRange:[0,1],outputRange:[0,1]})}},t.prototype.renderSubtitle=function(){var e=this.props,t=e.currentlyViewingCount,n=e.showCurrentlyViewingCount,i=e.subtitle,o=e.starRating,r=e.subtitleNumberOfLines,l=s.default.phrase("%{smart_count} person is interested||||%{smart_count} people are interested",{smart_count:t},"How many people are currently viewing the PDP");return i?babelHelpers.jsx(u.View,{style:L.subtitleContainer},void 0,n&&babelHelpers.jsx(u.Animated.View,{style:[L.textContainer,this.animatedStyleForInterestedText(),{position:"absolute"}]},void 0,babelHelpers.jsx(u.Text,{numberOfLines:r,style:[L.subtitle]},void 0,l)),!!o&&babelHelpers.jsx(u.Animated.View,{style:[L.starRatingContainer,this.animatedStyleForSubtitle()]},void 0,babelHelpers.jsx(H.default,{rating:o,useAltStar:!0,showHalfStars:!0,size:"sm"})),babelHelpers.jsx(u.View,{style:L.textContainer},void 0,babelHelpers.jsx(u.Animated.Text,{numberOfLines:r,style:[L.subtitle,this.animatedStyleForSubtitle()]},void 0,i))):null},t.prototype.renderTitle=function(){var e=this.props,t=e.title,n=e.secondaryTitle,i=e.iconForTitle,o=e.infoTextLinkLike;return babelHelpers.jsx(u.View,{style:L.titleView},void 0,!!i&&babelHelpers.jsx(u.View,{style:L.iconView},void 0,babelHelpers.jsx(u.Image,{source:(0,C.default)(i)})),babelHelpers.jsx(u.Text,{numberOfLines:1,style:L.textContainer},void 0,babelHelpers.jsx(u.Text,{style:o?L.infoTextLink:L.title},void 0,t),"  ",babelHelpers.jsx(u.Text,{style:L.secondaryTitle},void 0,n)))},t.prototype.renderRectangularButton=function(){var e=this.props,t=e.buttonActive,n=e.onButtonPress,i=e.buttonDark,o=e.buttonMedium,r=e.buttonText;return babelHelpers.jsx(f.default,{primary:!0,inactive:!t,onPress:n,dark:i,medium:o,dlsRole:"footer"},void 0,babelHelpers.jsx(u.Text,{},void 0,r))},t.prototype.renderSvgRectangularButton=function(){var e=this.props,t=e.buttonActive,n=e.onButtonPress,i=e.buttonDark,o=e.buttonMedium,r=e.buttonText,l=e.buttonSvg;return babelHelpers.jsx(y.default,{primary:!0,inactive:!t,onPress:n,dark:i,medium:o,dlsRole:"footer",buttonText:r,svgType:l})},t.prototype.render=function(){var e=this.props,t=e.title,n=e.subtitle,i=e.buttonActive,o=e.buttonText,r=e.onButtonPress,l=e.loading,a=e.onInfoTextPress,s=e.buttonTextLike,b=e.buttonSvg;return babelHelpers.jsx(u.View,{style:L.container},void 0,babelHelpers.jsx(u.View,{style:L.leftContainer},void 0,l&&babelHelpers.jsx(u.View,{style:L.loaderContainer},void 0,babelHelpers.jsx(p.default,{})),!l&&babelHelpers.jsx(u.TouchableWithoutFeedback,{onPress:a},void 0,babelHelpers.jsx(u.View,{style:L.leftSubContainer},void 0,!!t&&this.renderTitle(),!!n&&this.renderSubtitle()))),s&&babelHelpers.jsx(u.View,{style:L.rightContainerTextLike},void 0,babelHelpers.jsx(u.TouchableWithoutFeedback,{onPress:i?r:null},void 0,babelHelpers.jsx(u.View,{style:L.buttonTextWrapper},void 0,babelHelpers.jsx(u.Text,{style:[L.buttonLink,!i&&L.inactiveButtonLink]},void 0,o)))),!s&&babelHelpers.jsx(u.View,{style:[L.rightContainer]},void 0,babelHelpers.jsx(u.View,{style:L.rightSubContainer},void 0,!b&&this.renderRectangularButton(),!!b&&this.renderSvgRectangularButton())))},t}(r.default.Component);i.default=S;var L=T.default.create(function(e){var t=e.font,n=e.size,i=e.bp,o=e.color,r=(0,b.getWindowWidth)()>=375;return{container:{alignItems:"center",justifyContent:"space-between",backgroundColor:o.white,flexDirection:"row",paddingHorizontal:n.baseRow.paddingHorizontal,height:10*i},containerShadow:babelHelpers.extends({},g.default.light,{shadowColor:"#000",shadowOpacity:.15,borderColor:"#e2e2e2",borderWidth:2*u.StyleSheet.hairlineWidth}),leftContainer:{flex:1,paddingRight:n.horizontal.micro},leftSubContainer:{flex:1,justifyContent:"center",minWidth:r?116:void 0},rightContainer:{flex:1,flexDirection:"row",alignItems:"center",justifyContent:"flex-end"},rightSubContainer:{maxWidth:r?219:(0,b.getWindowWidth)()-116-24-24,flex:1},starRatingContainer:{paddingTop:1,paddingRight:.5*i},subtitleContainer:{flexDirection:"row",alignItems:"center",flex:0},textContainer:{flexDirection:"column",alignItems:"flex-start",flex:1},rightContainerTextLike:{flex:1,alignItems:"flex-end"},title:t.footerButtonDark,secondaryTitle:t.footerButtonDarkLight,subtitle:babelHelpers.extends({},t.footerSubtitle,{paddingTop:3}),infoTextLink:babelHelpers.extends({},t.smallActionablePlus,{color:o.core.rausch}),buttonLink:babelHelpers.extends({},t.footerButton,{color:o.core.rausch}),inactiveButtonLink:{color:o.primaryButtonInactive},buttonTextWrapper:{paddingVertical:4*i,paddingLeft:4*i},loaderContainer:{paddingVertical:1.75*i},titleView:{flexDirection:"row",alignItems:"center",flex:0},iconView:{height:18,width:18}}});S.defaultProps=R,S.propTypes=k,S.contentInset={top:0,bottom:80}},1640);