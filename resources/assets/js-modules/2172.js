__d(function(e,t,n,i){Object.defineProperty(i,"__esModule",{value:!0});var r=t(412),o=babelHelpers.interopRequireDefault(r),u=t(271),l=(babelHelpers.interopRequireDefault(u),t(44)),a=t(773),s=babelHelpers.interopRequireDefault(a),p=t(2170),d=babelHelpers.interopRequireDefault(p),b=t(422),f=babelHelpers.interopRequireDefault(b),c=t(2173),y=babelHelpers.interopRequireDefault(c),g=t(815),P=t(756),m=babelHelpers.interopRequireDefault(P),h={allowPrimaryPressWhileEditing:o.default.bool,autoFocus:o.default.bool,title:o.default.string.isRequired,onBlur:o.default.func,onChangeText:o.default.func,onFocus:o.default.func,onPrimaryPress:o.default.func,onSubtitleLinkPress:o.default.func,primaryButtonDisabled:o.default.bool,primaryButtonLabel:o.default.string,primaryButtonLoading:o.default.bool,primaryButtonIcon:g.IconPropType,showEditButtonLabelIcon:o.default.bool,subtitle:o.default.string,subtitleLink:o.default.string,guidance:o.default.string,value:o.default.string,placeholder:o.default.string},B={allowPrimaryPressWhileEditing:!1,autoFocus:!1,onBlur:function(){},onChangeText:function(){},onFocus:function(){},onPrimaryPress:function(){},onSubtitleLinkPress:function(){},primaryButtonDisabled:null,primaryButtonLabel:null,primaryButtonLoading:!1,primaryButtonIcon:null,showEditButtonLabelIcon:!0,subtitle:"",subtitleLink:null,guidance:"",value:""},L=function(e){function t(n,i){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,n,i));return r.state={isEditing:!1},r.textInput=null,r.setInput=r.setInput.bind(r),r.onBlur=r.onBlur.bind(r),r.onFocus=r.onFocus.bind(r),r.onPrimaryPress=r.onPrimaryPress.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.onBlur=function(){this.setState({isEditing:!1})},t.prototype.onFocus=function(){this.setState({isEditing:!0})},t.prototype.onPrimaryPress=function(){this.state.isEditing&&!this.props.allowPrimaryPressWhileEditing||this.props.onPrimaryPress()},t.prototype.setInput=function(e){this.textInput=e},t.prototype.render=function(){var e=this,t=this.props,n=t.autoFocus,i=t.onSubtitleLinkPress,r=t.primaryButtonDisabled,o=t.primaryButtonLabel,u=t.primaryButtonLoading,a=t.primaryButtonIcon,p=t.onChangeText,b=t.subtitle,f=t.subtitleLink,c=t.guidance,g=t.title,P=t.value,h=t.placeholder,B=this.state.isEditing;return babelHelpers.jsx(y.default,{inputRef:this.setInput,onChangeText:p,value:P,placeholder:h,autoFocus:n,renderAboveInput:function(){return babelHelpers.jsx(l.View,{},void 0,babelHelpers.jsx(s.default,{title:g,subtitle:b,subtitleLink:f,onSubtitleLinkPress:i}),!!c&&babelHelpers.jsx(l.View,{style:H.guidanceContainer},void 0,babelHelpers.jsx(m.default,{},void 0,c)))},renderFooter:function(){return babelHelpers.jsx(d.default,{onPrimaryPress:e.onPrimaryPress,primaryDisabled:!B&&r,primaryLoading:u,primaryText:o,primaryIcon:a})}})},t}(u.PureComponent);i.default=L,L.propTypes=h,L.defaultProps=B;var H=f.default.create(function(e){var t=e.bp;return{button:{marginTop:2*t,paddingLeft:1.5*t},guidanceContainer:{paddingRight:10*t,paddingLeft:3*t}}})},2172);