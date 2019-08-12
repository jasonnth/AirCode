__d(function(e,t,o,i){Object.defineProperty(i,"__esModule",{value:!0});var n=t(412),r=babelHelpers.interopRequireDefault(n),s=t(271),a=babelHelpers.interopRequireDefault(s),l=t(44),d=t(425),h=babelHelpers.interopRequireDefault(d),u=t(758),p=babelHelpers.interopRequireDefault(u),c=t(840),b=t(1406),f=babelHelpers.interopRequireDefault(b),g=t(422),y=babelHelpers.interopRequireDefault(g),H=babelHelpers.extends({},f.default.propTypes,{autoFocus:r.default.bool,autoScroll:r.default.bool,initialOffsetY:r.default.number,scrollScreen:r.default.object,placeholder:r.default.string,padded:r.default.bool,onChangeText:r.default.func,contentInsetY:r.default.number}),x=babelHelpers.extends({},f.default.defaultProps,{autoFocus:!0,autoScroll:!0,contentInsetY:0,initialOffsetY:0,scrollScreen:null,padded:!0,placeholder:"",onChangeText:function(){}}),v=50,C=function(e){function t(o,i){babelHelpers.classCallCheck(this,t);var n=babelHelpers.possibleConstructorReturn(this,e.call(this,o,i));return n.state={cursorPosition:0,height:v,keyboardHeight:0,viewHeight:null,isFocused:!1,text:""},n.onChangeHeight=n.onChangeHeight.bind(n),n.onChangeText=n.onChangeText.bind(n),n.onSelectionChange=n.onSelectionChange.bind(n),n}return babelHelpers.inherits(t,e),t.prototype.componentWillMount=function(){this.keyboardDidShowListener=l.Keyboard.addListener("keyboardDidShow",this.keyboardDidShow.bind(this)),this.keyboardDidHideListener=l.Keyboard.addListener("keyboardDidHide",this.keyboardDidHide.bind(this))},t.prototype.componentWillUnmount=function(){this.keyboardDidShowListener&&this.keyboardDidShowListener.remove(),this.keyboardDidHideListener&&this.keyboardDidHideListener.remove()},t.prototype.keyboardDidHide=function(){this.props.scrollScreen&&this.props.autoScroll&&(this.setState({keyboardHeight:0}),this.props.scrollScreen.scrollTo({y:this.props.initialOffsetY,x:0,animated:!0}))},t.prototype.keyboardDidShow=function(e){var t=e.endCoordinates.height;this.setState({keyboardHeight:t})},t.prototype.onChangeText=function(e){this.props.onChangeText(e),this.setState({text:e})},t.prototype.onBlur=function(){this.setState({isFocused:!1})},t.prototype.onFocus=function(){this.setState({isFocused:!0})},t.prototype.onChangeHeight=function(e){var t=(0,c.getWindowHeight)(),o=e+v,i=t-this.props.contentInsetY-this.state.keyboardHeight;this.setState({height:o});var n=this.state.cursorPosition+v;n>i&&this.props.scrollScreen&&this.props.autoScroll&&this.props.scrollScreen.scrollTo({y:n-i,x:0,animated:!0})},t.prototype.onSelectionChange=function(e){var t=e.nativeEvent;this.setState({selectionEnd:t.selection.end})},t.prototype.renderHiddenTextForHeightComputation=function(){var e=this;return babelHelpers.jsx(l.Text,{onLayout:function(t){var o=t.nativeEvent;return e.setState({cursorPosition:o.layout.height})},style:[S.text,S.hiddenText]},void 0,this.state.text.slice(0,this.state.selectionEnd))},t.prototype.renderHiddenTextForPlaceholderHeightComputation=function(){var e=this;return babelHelpers.jsx(l.Text,{onLayout:function(t){var o=t.nativeEvent;e.state.isFocused||0!==e.state.text.length||e.onChangeHeight(o.layout.height)},style:[S.text,S.hiddenText,this.props.padded&&S.textPadded]},void 0,this.props.placeholder)},t.prototype.render=function(){var e=this;return babelHelpers.jsx(l.View,{style:{paddingBottom:this.state.keyboardHeight}},void 0,a.default.createElement(f.default,babelHelpers.extends({},(0,p.default)(this.props,f.default),{multiline:!0,autoCapitalize:"sentences",style:[S.text,{height:this.state.height},this.props.padded&&S.textPadded],onChangeText:this.onChangeText,onChangeHeight:this.onChangeHeight,onBlur:this.onBlur.bind(this),onFocus:this.onFocus.bind(this),onLayout:function(t){var o=t.nativeEvent;return e.setState({viewHeight:o.layout.y})},onSelectionChange:this.onSelectionChange,placeholder:this.props.placeholder,ref:this.setMessageInput,selectionColor:h.default.textInput,autoFocus:this.props.autoFocus})),this.renderHiddenTextForPlaceholderHeightComputation(),this.renderHiddenTextForHeightComputation())},t}(s.Component);i.default=C;var S=y.default.create(function(e){var t=e.font,o=e.size;return{text:babelHelpers.extends({},t.large),textPadded:{marginHorizontal:o.baseRow.paddingHorizontal},hiddenText:{opacity:0,position:"absolute",left:0,right:0}}});C.propTypes=H,C.defaultProps=x},1407);