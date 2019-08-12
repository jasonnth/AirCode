__d(function(e,t,n,i){Object.defineProperty(i,"__esModule",{value:!0});var a=t(412),o=babelHelpers.interopRequireDefault(a),l=t(271),s=(babelHelpers.interopRequireDefault(l),t(44)),r=t(1023),u=t(1406),d=babelHelpers.interopRequireDefault(u),p=t(2225),h=60,b=s.StyleSheet.create({container:{alignItems:"flex-end",borderStyle:"solid",borderTopColor:"#d8d8d8",borderTopWidth:1,paddingBottom:8,flexDirection:"row",justifyContent:"space-between"},input:babelHelpers.extends({flex:1},r.font.large,{textAlignVertical:"center"}),inputWithoutToolbar:{paddingLeft:20},multilineInput:{paddingTop:12},button:{alignItems:"center",justifyContent:"center",paddingHorizontal:20,paddingBottom:10},buttonText:babelHelpers.extends({},r.font.largePlus,{color:"#00A699"}),buttonTextDisabled:babelHelpers.extends({},r.font.largePlus,{color:"#52C2B9"})}),g={threadType:o.default.string.isRequired,threadId:o.default.oneOfType([o.default.number,o.default.string]).isRequired,thread:o.default.object.isRequired,viewer:o.default.object.isRequired,multiline:o.default.bool,onMessageSend:o.default.func,placeholder:o.default.string,submitOnReturn:o.default.bool},f={multiline:!1,onChangeHeight:function(){},onChangeText:function(){},onMessageSend:function(){},placeholder:"Write message\u2026",submitOnReturn:!1},c=function(e){function t(n,i){babelHelpers.classCallCheck(this,t);var a=babelHelpers.possibleConstructorReturn(this,e.call(this,n,i));return a.onMessageSend=a.onMessageSend.bind(a),a.onInputHeightChange=a.onInputHeightChange.bind(a),a.onInputChangeText=a.onInputChangeText.bind(a),a.state={value:"",height:new s.Animated.Value(h)},a}return babelHelpers.inherits(t,e),t.prototype.onMessageSend=function(){this.state.value&&(this.props.onMessageSend("text",this.state.value),this.setState({value:""}),this.onInputHeightChange(0))},t.prototype.onInputChangeText=function(e){this.setState({value:e})},t.prototype.onInputHeightChange=function(e){var t=Math.min(e+20,120),n=Math.max(t,h);this.updateComponentsHeight(n)},t.prototype.updateComponentsHeight=function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:440;s.Animated.timing(this.state.height,{toValue:e,duration:t,easing:s.Easing.out(s.Easing.exp)}).start()},t.prototype.render=function(){var e=this.props,t=e.multiline,n=e.submitOnReturn,i=e.thread,a=e.threadType,o=e.threadId,l=e.viewer,r=this.state,u=r.value,h=r.height,g=p.messagingRegistry.getInputToolbar(a),f=0===u.length,c=f?b.buttonTextDisabled:b.buttonText;return babelHelpers.jsx(s.Animated.View,{style:[b.container,{height:h}]},void 0,g&&babelHelpers.jsx(g,{thread:i,threadId:o,threadType:a,viewer:l,inputValue:u,height:h}),babelHelpers.jsx(d.default,{blurOnSubmit:!1,multiline:t,onChangeText:this.onInputChangeText,onChangeHeight:this.onInputHeightChange,onSubmitEditing:n?this.onMessageSend:null,placeholder:this.props.placeholder,returnKeyType:n?"send":"default",style:[b.input,t&&b.multilineInput,!g&&b.inputWithoutToolbar],value:u}),babelHelpers.jsx(s.TouchableOpacity,{onPress:this.onMessageSend,style:b.button,disabled:f},void 0,babelHelpers.jsx(s.Text,{style:c},void 0,"Send")))},t}(l.Component);i.default=c,c.propTypes=g,c.defaultProps=f},2285);