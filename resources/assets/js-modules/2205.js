__d(function(e,t,l,r){Object.defineProperty(r,"__esModule",{value:!0});var a=t(412),u=babelHelpers.interopRequireDefault(a),n=t(271),p=babelHelpers.interopRequireDefault(n),i=t(44),s=t(758),o=babelHelpers.interopRequireDefault(s),d=t(779),f=babelHelpers.interopRequireDefault(d),b=t(2206),c=babelHelpers.interopRequireDefault(b),h=t(1406),x=babelHelpers.interopRequireDefault(h),H=t(422),g=babelHelpers.interopRequireDefault(H),v=babelHelpers.extends({},c.default.propTypes,f.default.propTypes,{onTextChange:u.default.func,showTextInput:u.default.bool,textPlaceholder:u.default.string,textValue:u.default.string,title:u.default.string}),m=babelHelpers.extends({},c.default.defaultProps,f.default.defaultProps,{onTextChange:function(){},showTextInput:!1,textPlaceholder:null,textValue:null}),T=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.onTextChange,l=e.showTextInput,r=e.textPlaceholder,a=e.textValue,u=e.title;return p.default.createElement(f.default,(0,o.default)(this.props,f.default),!!u&&babelHelpers.jsx(i.Text,{style:y.title},void 0,u),p.default.createElement(c.default,(0,o.default)(this.props,c.default)),l&&p.default.createElement(x.default,babelHelpers.extends({},(0,o.default)(this.props,x.default),{clearButtonMode:"while-editing",onChange:function(e){var l=e.nativeEvent;return t(l.text)},placeholder:r,style:y.textInput,defaultValue:a})))},t}(n.PureComponent);r.default=T,T.defaultProps=m,T.propTypes=v;var y=g.default.create(function(e){var t=e.bp,l=e.font,r=e.size;return{textInput:babelHelpers.extends({},l.regularMuted,{flex:1,height:r.vertical.medium,marginTop:r.vertical.medium,paddingLeft:1.5*t}),title:babelHelpers.extends({},l.regular,{marginBottom:r.vertical.tiny})}})},2205);