__d(function(e,t,n,i){Object.defineProperty(i,"__esModule",{value:!0});var s=t(412),o=babelHelpers.interopRequireDefault(s),l=t(271),r=(babelHelpers.interopRequireDefault(l),t(44)),a=t(743),u=t(890),p=babelHelpers.interopRequireDefault(u),d=t(1035),b=babelHelpers.interopRequireDefault(d),h=t(422),f=babelHelpers.interopRequireDefault(h),c=t(1032),v=babelHelpers.interopRequireDefault(c),S=t(779),C=babelHelpers.interopRequireDefault(S),P=(0,a.forbidExtraProps)({divider:C.default.propTypes.divider,name:o.default.string.isRequired,subtitle:o.default.string,valueLabel:o.default.node,sheetTitle:o.default.string,sheetSubtitle:o.default.string,nextButtonTitle:o.default.string,optionalButton:o.default.string,onSheetPresented:o.default.func,onSheetClosed:o.default.func,onSheetCompleted:o.default.func,children:o.default.node}),H={onSheetCompleted:function(){},onSheetClosed:function(){},onSheetPresented:function(){}},g=function(e){function t(n,i){babelHelpers.classCallCheck(this,t);var s=babelHelpers.possibleConstructorReturn(this,e.call(this,n,i));return s.state={sheetVisible:!1},s.onPressInputRow=s.onPressInputRow.bind(s),s.onNextPress=s.onNextPress.bind(s),s.onVisibleChange=s.onVisibleChange.bind(s),s}return babelHelpers.inherits(t,e),t.prototype.onPressInputRow=function(){this.props.onSheetPresented(),this.setState({sheetVisible:!0})},t.prototype.onNextPress=function(){this.setState({sheetVisible:!1})},t.prototype.onVisibleChange=function(e){var t=this;this.setState({sheetVisible:e},function(){e||t.props.onSheetClosed()})},t.prototype.renderSheet=function(){var e=this.props,t=e.sheetTitle,n=e.sheetSubtitle,i=e.optionalButton,s=e.nextButtonTitle,o=e.children,l=f.default.theme.color;return babelHelpers.jsx(p.default,{visible:this.state.sheetVisible,backgroundColor:l.sheetBackground,onVisibleChange:this.onVisibleChange,onManualClose:this.props.onSheetCompleted},void 0,babelHelpers.jsx(b.default,{title:t,subtitle:n,optionalButton:i,nextButtonTitle:s,onNextPress:this.onNextPress},void 0,o))},t.prototype.render=function(){var e=this.props,t=e.divider,n=e.name,i=e.subtitle,s=e.valueLabel;return babelHelpers.jsx(r.View,{style:x.container},void 0,this.renderSheet(),babelHelpers.jsx(v.default,{title:n,subtitle:i,actionTitle:s,divider:t,onPress:this.onPressInputRow}))},t}(l.Component);i.default=g;var x=r.StyleSheet.create({container:{overflow:"hidden"}});g.defaultProps=H,g.propTypes=P},1034);